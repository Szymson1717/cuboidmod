package com.cuboiddroid.cuboidmod.modules.refinedinscriber.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.inventory.RefinedInscriberContainer;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntityTicker ;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("rawtypes")
public class RefinedInscriberTileEntity extends BlockEntity implements BlockEntityTicker  {
    public static final int SLOT_TOP_LEFT = 0;
    public static final int SLOT_MIDDLE = 1;
    public static final int SLOT_BOTTOM_RIGHT = 2;
    public static final int SLOT_OUTPUT = 3;
    private static final int INPUT_SLOTS = 3;
    private static final int OUTPUT_SLOTS = 1;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
    private ItemStackHandler topLeftItemHandler = createTopLeftInputHandler();
    private ItemStackHandler middleItemHandler = createMiddleInputHandler();
    private ItemStackHandler bottomRightItemHandler = createBottomRightInputHandler();
    private ItemStackHandler outputItemHandler = createOutputHandler();
    private CombinedInvWrapper combinedItemHandler = new CombinedInvWrapper(topLeftItemHandler, middleItemHandler, bottomRightItemHandler, outputItemHandler);
    private CuboidEnergyStorage energyStorage;
    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> topLeftHandler = LazyOptional.of(() -> topLeftItemHandler);
    private LazyOptional<IItemHandler> middleHandler = LazyOptional.of(() -> middleItemHandler);
    private LazyOptional<IItemHandler> bottomRightHandler = LazyOptional.of(() -> bottomRightItemHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<IItemHandler> combinedHandler = LazyOptional.of(() -> combinedItemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int energyCapacity = Config.refinedInscriberEnergyCapacity.get();
    private int maxEnergyReceivedPerTick = Config.refinedInscriberMaxEnergyInputPerTick.get();

    private int processingTime = 0;
    private int recipeTime = -1;
    private int energyConsumed = 0;

    private InscribingRecipe cachedRecipe = null;

    public RefinedInscriberTileEntity() {
        this(BlockPos.ZERO, ModBlocks.REFINED_INSCRIBER.get().defaultBlockState());
    }

    public RefinedInscriberTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.REFINED_INSCRIBER.get(), pos, state);
        energyStorage = createEnergy();
    }

    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.refined_inscriber");
    }

    public static void gameTick(Level level, BlockPos worldPosition, BlockState blockState, RefinedInscriberTileEntity entity) {
        entity.tick(level, worldPosition, blockState, entity);
    }

    @Override
    public void tick(Level level, BlockPos worldPosition, BlockState blockState, BlockEntity entity) {
        if (level == null || level.isClientSide)
            return;

        boolean didWorkThisTick = false;

        InscribingRecipe recipe = getRecipe();

        if (recipe == null) {
            if (processingTime > 0 || energyConsumed > 0) {
                stopWork();
                setChanged();
            }
        } else {
            if (processingTime <= 0) {
                if (energyConsumed + energyStorage.getEnergyStored() < recipe.getEnergyRequired())
                    // not enough energy to do anything - do nothing!
                    return;

                if (energyConsumed < recipe.getEnergyRequired()) {
                    // consume the required amount of energy
                    int energyNeeded = recipe.getEnergyRequired() - energyConsumed;

                    if (energyNeeded > 0) {
                        int energyToConsume = Math.min(energyNeeded, energyStorage.getEnergyStored());
                        energyStorage.consumeEnergy(energyToConsume);
                        energyConsumed += energyToConsume;
                        setChanged();
                    }
                }
            }

            if (processingTime <= 0 && energyConsumed >= recipe.getEnergyRequired()) {
                // we've consumed enough energy but have not started working - start working!
                this.processingTime = recipe.getWorkTicks();
                this.recipeTime = processingTime;
                setChanged();
            }

            if (processingTime > 0 && energyConsumed >= recipe.getEnergyRequired()) {
                didWorkThisTick = doWork(recipe);
                setChanged();
            }
        }

        if (blockState.getValue(BlockStateProperties.LIT) != (processingTime > 0 || didWorkThisTick)) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.LIT, processingTime > 0), Block.UPDATE_ALL);
        }
    }

    private boolean doWork(InscribingRecipe recipe) {
        assert this.level != null;

        ItemStack currentOutput = this.outputItemHandler.getStackInSlot(0).copy();
        ItemStack recipeOutput = getWorkOutput(recipe);

        if (!currentOutput.isEmpty()) {
            // output slot has something in it...

            // work out how many output items we'd have if we add them together...
            int newTotal = currentOutput.getCount() + recipeOutput.getCount();

            if (!currentOutput.sameItem(recipeOutput) || newTotal > recipeOutput.getMaxStackSize()) {
                // the recipe output is different to what's already in the output stack, or
                // the amount of items produced by the recipe plus the current number of items in
                // the output slot would exceed the maximum stack size for the item - stop working!
                stopWork();
                return false;
            }
        }

        if (processingTime > 0)
            processingTime--;

        if (processingTime <= 0) {
            finishWork(recipe, currentOutput, recipeOutput);

            // return true so that we don't get "flicker" for the core between recipes
            return true;
        }

        return false;
    }

    private void stopWork() {
        this.processingTime = 0;
        this.recipeTime = -1;
    }

    private void finishWork(InscribingRecipe recipe, ItemStack currentOutput, ItemStack recipeOutput) {
        if (!currentOutput.isEmpty()) {
            currentOutput.grow(recipeOutput.getCount());
            outputItemHandler.setStackInSlot(0, currentOutput);
        } else {
            outputItemHandler.setStackInSlot(0, recipeOutput.copy());
        }

        this.energyConsumed = 0;
        this.processingTime = 0;
        this.recipeTime = -1;

        ItemStack topLeftInput = topLeftItemHandler.getStackInSlot(0);
        ItemStack middleInput = middleItemHandler.getStackInSlot(0);
        ItemStack bottomRightInput = bottomRightItemHandler.getStackInSlot(0);

        if (recipe.getMode().equalsIgnoreCase("inscribe")) {
            // "inscribe" mode recipe - don't consume item in top/left slot
        } else {
            // "press" mode recipe - consume item in top/left slot
            topLeftInput.shrink(1);
            topLeftItemHandler.setStackInSlot(0, topLeftInput);
        }

        middleInput.shrink(1);
        middleItemHandler.setStackInSlot(0, middleInput);

        bottomRightInput.shrink(1);
        bottomRightItemHandler.setStackInSlot(0, bottomRightInput);
    }

    @Nullable
    public InscribingRecipe getRecipe() {
        if (this.level == null
                || this.middleItemHandler.getStackInSlot(0).isEmpty())
            return null;

        // make an inventory
        Container inv = getInputsAsInventory();

        if (cachedRecipe == null || !cachedRecipe.matches(inv, this.level)) {
            RecipeType<InscribingRecipe> recipeType = ModRecipeTypes.INSCRIBING.getRecipeType();
            cachedRecipe = this.level.getRecipeManager().getRecipeFor(recipeType, inv, this.level).orElse(null);
        }

        return cachedRecipe;
    }

    private SimpleContainer getInputsAsInventory() {
        return new SimpleContainer(
                this.topLeftItemHandler.getStackInSlot(0).copy(),
                this.middleItemHandler.getStackInSlot(0).copy(),
                this.bottomRightItemHandler.getStackInSlot(0).copy());
    }

    private ItemStack getWorkOutput(@Nullable InscribingRecipe recipe) {
        if (recipe != null) {
            return recipe.assemble(getInputsAsInventory());
        }

        return ItemStack.EMPTY;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            // if side is null, then it's not via automation, so provide access to everything
            if (side == null)
                return combinedHandler.cast();

            // if side is not null, then it's automation
            // BOTTOM = output, UP = base item input, sides = additional

            // TODO - figure out how to work out the relative facing by rotating so that
            //        front of machine is "NORTH"
            //
            // Top (UP) = input
            // Bottom (DOWN) = output
            // Left (EAST) + Front (NORTH) = top/left
            // Right (WEST) + Back (SOUTH) = bottom/right

            BlockState state = this.level.getBlockState(this.getBlockPos());
            Direction blockDirection = state.getValue(BlockStateProperties.HORIZONTAL_FACING);

            if (side == Direction.UP)
                return middleHandler.cast();
            if (side == Direction.DOWN)
                return outputHandler.cast();

            // rotate until we're aligned with known side directions
            Direction rotatedSide = side;
            while (blockDirection != Direction.NORTH)
            {
                blockDirection = blockDirection.getClockWise();
                rotatedSide = rotatedSide.getClockWise();
            }

            switch (rotatedSide) {
                case EAST:
                case NORTH:
                    return topLeftHandler.cast();

                default:
                    return bottomRightHandler.cast();
            }
        }

        if (cap == ForgeCapabilities.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        topLeftItemHandler.deserializeNBT(tag.getCompound("invTL"));
        middleItemHandler.deserializeNBT(tag.getCompound("invMid"));
        bottomRightItemHandler.deserializeNBT(tag.getCompound("invBR"));
        outputItemHandler.deserializeNBT(tag.getCompound("invOut"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        processingTime = tag.getInt("procTime");
        recipeTime = tag.getInt("recTime");
        energyConsumed = tag.getInt("feConsumed");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("invTL", topLeftItemHandler.serializeNBT());
        tag.put("invMid", middleItemHandler.serializeNBT());
        tag.put("invBR", bottomRightItemHandler.serializeNBT());
        tag.put("invOut", outputItemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("procTime", processingTime);
        tag.putInt("recTime", recipeTime);
        tag.putInt("feConsumed", energyConsumed);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        topLeftHandler.invalidate();
        middleHandler.invalidate();
        bottomRightHandler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
        energy.invalidate();
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() { 
        CompoundTag nbtTag = super.getUpdateTag();
        this.saveAdditional(nbtTag);
        this.setChanged();
        return nbtTag;
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();
        this.load(tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(energyCapacity, maxEnergyReceivedPerTick, 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    private ItemStackHandler createTopLeftInputHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
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

    private ItemStackHandler createMiddleInputHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
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

    private ItemStackHandler createBottomRightInputHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
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
                setChanged();
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

    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new RefinedInscriberContainer(i, level, pos, playerInventory, playerEntity);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    public int getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(int value) {
        this.processingTime = value;
    }

    public int getRecipeTime() {
        return this.recipeTime == 0 ? -1 : this.recipeTime;
    }
}
