package com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DryingCupboardTileEntity extends TileEntity implements ITickableTileEntity {
    public static final int INPUT_SLOTS = 8;
    public static final int OUTPUT_SLOTS = 8;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
    private ItemStackHandler inputItemHandler = createInputHandler();
    private ItemStackHandler outputItemHandler = createOutputHandler();
    private CombinedInvWrapper combinedItemHandler = new CombinedInvWrapper(inputItemHandler, outputItemHandler);
    private CuboidEnergyStorage energyStorage;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> inputItemHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<IItemHandler> combinedHandler = LazyOptional.of(() -> combinedItemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int energyCapacity = Config.dryingCupboardEnergyCapacity.get();
    private int maxEnergyReceivedPerTick = Config.dryingCupboardMaxEnergyInputPerTick.get();
    private int energyRequiredPerTick = Config.dryingCupboardEnergyRequiredPerTick.get();

    private int[] processingTimes = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
    private int[] recipeTimes = new int[]{-1, -1, -1, -1, -1, -1, -1, -1};
    private DryingRecipe[] recipes = new DryingRecipe[INPUT_SLOTS];

    private boolean isTicking = false;
    private int slotTicker = 0;
    private int litCounter = 0;

    private boolean isDirty = false;

    public DryingCupboardTileEntity() {
        super(ModTileEntities.DRYING_CUPBOARD.get());
        energyStorage = createEnergy();
        for (int i = 0; i < INPUT_SLOTS; i++) recipes[i] = null;
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.drying_cupboard");
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide)
            return;

        if (isTicking)
            return;

        isTicking = true;

        slotTicker++;
        litCounter--;
        if (slotTicker >= INPUT_SLOTS)
            slotTicker = 0;

        boolean didWorkThisTick = false;
        boolean busyProcessing = false;

        isDirty = false;

        if (energyStorage.getEnergyStored() < energyRequiredPerTick) {
            // not enough energy to do anything - do nothing!
            for (int i = 0; i < INPUT_SLOTS; i++)
                stopWork(i);
            isDirty = true;
        } else {
            boolean hasUpdated = false;

            // find recipes
            DryingRecipe recipe = getRecipe(slotTicker);

            if (recipe == null) {
                // no valid recipe in this slot - make sure we aren't doing anything for it
                if (processingTimes[slotTicker] > 0) {
                    stopWork(slotTicker);
                    hasUpdated |= true;
                }
            }

            if (recipe != null) {
                // recipe is valid for this slot - waiting or working?
                if (processingTimes[slotTicker] <= 0) {
                    // start working!
                    this.recipeTimes[slotTicker] = recipe.getWorkTicks();
                    this.processingTimes[slotTicker] = this.recipeTimes[slotTicker];
                }
            } else {
                this.recipeTimes[slotTicker] = -1;
            }

            busyProcessing = busyProcessing || processingTimes[slotTicker] > 0;

            didWorkThisTick = doWork();
            hasUpdated |= didWorkThisTick;

            if (busyProcessing) {
                // consume the energy requirement (per tick regardless of how many being dried at same time)
                int energyToConsume = Math.min(energyRequiredPerTick, energyStorage.getEnergyStored());
                energyStorage.consumeEnergy(energyToConsume);
                litCounter = INPUT_SLOTS;
            }

            // update client
            if (hasUpdated)
                isDirty = true;
        }

        if (isDirty) {
            setChanged();
        }

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        boolean shouldBeLit = busyProcessing || didWorkThisTick || litCounter > 0;
        if (blockState.getValue(BlockStateProperties.LIT) != shouldBeLit) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LIT, shouldBeLit),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }

        isTicking = false;
    }

    private boolean doWork() {
        boolean workDone = false;

        DryingRecipe recipe = recipes[slotTicker];
        if (recipe != null) {
            ItemStack recipeOutput = getWorkOutput(recipe);

            ItemStack outputSlot = this.outputItemHandler.getStackInSlot(slotTicker);
            boolean hasRoomForOutputs = outputSlot.isEmpty() ||
                    (!recipeOutput.isEmpty() &&
                            recipeOutput.sameItem(outputSlot) &&
                            recipeOutput.getCount() + outputSlot.getCount() <= recipeOutput.getMaxStackSize());

            if (!hasRoomForOutputs) {
                stopWork(slotTicker);
            } else {
                if (processingTimes[slotTicker] > 0) {
                    processingTimes[slotTicker] = Math.max(0, processingTimes[slotTicker] - INPUT_SLOTS);
                }

                workDone |= true;
            }
        }

        return finishWork() || workDone;
    }

    private void stopWork(int slotIndex) {
        this.processingTimes[slotIndex] = 0;
        this.recipeTimes[slotIndex] = -1;
    }

    private boolean finishWork() {
        boolean workDone = false;

        if (processingTimes[slotTicker] <= 0 && recipeTimes[slotTicker] > 0) {
            DryingRecipe recipe = recipes[slotTicker];
            if (recipe != null) {
                ItemStack recipeOutput = getWorkOutput(recipe);

                ItemStack outputStack = outputItemHandler.getStackInSlot(slotTicker).copy();
                if (outputStack.sameItem(recipeOutput) && outputStack.getCount() + recipeOutput.getCount() <= outputStack.getMaxStackSize()) {
                    outputStack.setCount(outputStack.getCount() + recipeOutput.getCount());
                    outputItemHandler.setStackInSlot(slotTicker, outputStack);
                } else if (outputStack.isEmpty()) {
                    outputItemHandler.setStackInSlot(slotTicker, recipeOutput);
                } else {
                    throw new IllegalStateException("Attempted to move stack to output slot without checking if there is room first!");
                }

                // consume the ingredients
                ItemStack inputStack = inputItemHandler.getStackInSlot(slotTicker).copy();
                inputStack.shrink(1);
                inputItemHandler.setStackInSlot(slotTicker, inputStack);

                this.processingTimes[slotTicker] = 0;
                this.recipeTimes[slotTicker] = -1;

                // return true so that we don't get "flicker" for the core between recipes
                workDone = true;
            }
        }

        return workDone;
    }

    @Nullable
    public DryingRecipe getRecipe(int slotIndex) {
        if (this.level == null
                || this.inputItemHandler.getStackInSlot(slotIndex).isEmpty()) {
            recipes[slotIndex] = null;
            return null;
        }

        if (recipes[slotIndex] != null) {
            // we're already tracking a recipe for this slot.
            if (recipes[slotIndex].getIngredient().getItems()[0].sameItem(this.inputItemHandler.getStackInSlot(slotIndex)))
                // same input item as is currently in the slot, so we'll keep using it.
                return recipes[slotIndex];
        }

        // not tracking a recipe, or the one we are tracking has a different input item...

        // make an inventory from the input slot content
        IInventory inv = getInputsAsInventory(slotIndex);

        // look for a specific recipe and use it if found
        DryingRecipe recipe = this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.DRYING, inv, this.level).orElse(null);

        // track the recipe being used for this slot to save time next tick
        recipes[slotIndex] = recipe;

        return recipe;
    }

    private Inventory getInputsAsInventory(int slotIndex) {
        return new Inventory(this.inputItemHandler.getStackInSlot(slotIndex).copy());
    }

    private ItemStack getWorkOutput(@Nullable DryingRecipe recipe) {
        if (recipe != null) {
            return recipe.getResultItem();
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            // if side is null, then it's not via automation, so provide access to everything
            if (side == null)
                return combinedHandler.cast();

            // if side is not null, then it's automation
            // BOTTOM = output, UP & sides = input
            switch (side) {
                case DOWN:
                    return outputHandler.cast();

                default:
                    return inputHandler.cast();
            }
        }

        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        inputItemHandler.deserializeNBT(tag.getCompound("invIn"));
        outputItemHandler.deserializeNBT(tag.getCompound("invOut"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        processingTimes = tag.getIntArray("procTimes");
        recipeTimes = tag.getIntArray("recTimes");

        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("invIn", inputItemHandler.serializeNBT());
        tag.put("invOut", outputItemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putIntArray("procTimes", processingTimes);
        tag.putIntArray("recTimes", recipeTimes);

        return super.save(tag);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputHandler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
        energy.invalidate();
    }

    /**
     * When the world loads from disk, the server needs to send the TileEntity information to the client
     * It uses getUpdatePacket(), getUpdateTag(), onDataPacket(), and handleUpdateTag() to do this:
     * getUpdatePacket() and onDataPacket() are used for one-at-a-time TileEntity updates
     * getUpdateTag() and handleUpdateTag() are used by vanilla to collate together into a single chunk update packet
     *
     * @return the packet
     */
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        this.save(nbtTag);
        this.setChanged();
        return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        CompoundNBT tag = pkt.getTag();
        this.load(level.getBlockState(worldPosition), tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    /* Creates a tag containing all of the TileEntity information, used by vanilla to transmit from server to client
     */
    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
     */
    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        this.load(blockState, parentNBTTagCompound);
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(energyCapacity, maxEnergyReceivedPerTick, 0) {
            @Override
            protected void onEnergyChanged() {
                isDirty = true;
            }
        };
    }

    private ItemStackHandler createInputHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                isDirty = true;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return this.getStackInSlot(slot).isEmpty() || this.getStackInSlot(slot).sameItem(stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private ItemStackHandler createOutputHandler() {
        return new ItemStackHandler(OUTPUT_SLOTS) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                isDirty = true;
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                // can't insert into the output slot
                return stack;
            }
        };
    }

    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new DryingCupboardContainer(i, level, pos, playerInventory, playerEntity);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    public int getProcessingTime(int slotIndex) {
        return this.processingTimes[slotIndex];
    }

    public void setProcessingTime(int slotIndex, int value) {
        this.processingTimes[slotIndex] = value;
    }

    public int getRecipeTime(int slotIndex) {
        return this.recipeTimes[slotIndex] == 0 ? -1 : this.recipeTimes[slotIndex];
    }

    public void setRecipeTime(int slotIndex, int value) {
        this.recipeTimes[slotIndex] = value;
    }
}
