package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.CombinedInvWrapper;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public abstract class QuantumCollapserTileEntityBase extends TileEntity implements ITickableTileEntity {
    public static final int INPUT_SLOT = 0;
    public static final int OUTPUT_SLOT = 1;
    private static final int INPUT_SLOTS = 1;
    private static final int OUTPUT_SLOTS = 1;
    public static final int TOTAL_SLOTS = INPUT_SLOTS + OUTPUT_SLOTS;
    private ItemStackHandler inputItemHandler = createInputHandler();
    private ItemStackHandler outputItemHandler = createOutputHandler();
    private CombinedInvWrapper combinedItemHandler = new CombinedInvWrapper(inputItemHandler, outputItemHandler);

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> inputHandler = LazyOptional.of(() -> inputItemHandler);
    private LazyOptional<IItemHandler> outputHandler = LazyOptional.of(() -> outputItemHandler);
    private LazyOptional<IItemHandler> combinedHandler = LazyOptional.of(() -> combinedItemHandler);

    private final float speedFactor;

    private int processingTime = 0;
    private int recipeTime = -1;
    private int amountConsumed = 0;
    private int amountRequired = -1;
    private Ingredient currentIngredient = Ingredient.EMPTY;
    private ItemStack currentOutput = ItemStack.EMPTY;

    public QuantumCollapserTileEntityBase(TileEntityType<?> tileEntityType, float speedFactor) {
        super(tileEntityType);
        this.speedFactor = speedFactor;
    }

    public abstract ITextComponent getDisplayName();

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide)
            return;

        boolean didWorkThisTick = false;

        QuantumCollapsingRecipe recipe = getRecipe();

        if (recipe != null) {
            if (this.currentIngredient.isEmpty()) {
                this.currentIngredient = recipe.getIngredient();
                this.amountRequired = recipe.getRequiredInputAmount();
                this.recipeTime = (int) (recipe.getWorkTicks() / this.speedFactor);
                this.currentOutput = recipe.getResultItem();
                setChanged();
            } else if (!currentIngredient.test(this.inputItemHandler.getStackInSlot(0))) {
                // can't change recipe mid-load
                return;
            }
        }

        if (!currentIngredient.isEmpty()) {
            if (processingTime <= 0) {
                ItemStack currentInputStack = inputItemHandler.getStackInSlot(INPUT_SLOT);
                if (currentInputStack.isEmpty() || !currentIngredient.test(currentInputStack))
                    // nothing in the input slot or not the right item - do nothing
                    return;

                if (amountConsumed < amountRequired) {
                    // consume the  amount of input items
                    int amountNeeded = amountRequired - amountConsumed;

                    if (amountNeeded > 0) {
                        int amountToConsume = Math.min(amountNeeded, currentInputStack.getCount());

                        ItemStack newInputStack = ItemStack.EMPTY;
                        if (amountToConsume < currentInputStack.getCount()) {
                            newInputStack = currentInputStack.copy();
                            newInputStack.shrink(amountToConsume);
                        }
                        inputItemHandler.setStackInSlot(INPUT_SLOT, newInputStack);
                        amountConsumed += amountToConsume;

                        // force a block update to be sent to the client so that it updates the GUI
                        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
                        setChanged();
                    }
                }
            }

            if (processingTime <= 0 && amountConsumed >= amountRequired) {
                // we've consumed enough items but have not started working - start working!
                this.processingTime = this.recipeTime;
                setChanged();
            }

            if (processingTime > 0 && amountConsumed >= amountRequired) {
                didWorkThisTick = doWork();
                setChanged();
            }
        }

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        if (blockState.getValue(BlockStateProperties.LIT) != (processingTime > 0 || didWorkThisTick)) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LIT, processingTime > 0),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    private boolean doWork() {
        assert this.level != null;

        boolean hasRoomForOutputs = false;
        int outputSlotIndex = 0;
        while (!hasRoomForOutputs && outputSlotIndex < OUTPUT_SLOTS) {
            ItemStack outputSlot = this.outputItemHandler.getStackInSlot(outputSlotIndex);
            hasRoomForOutputs = outputSlot.isEmpty() || (!currentOutput.isEmpty() &&
                    currentOutput.sameItem(outputSlot) &&
                    currentOutput.getCount() + outputSlot.getCount() <= currentOutput.getMaxStackSize());

            outputSlotIndex++;
        }

        if (!hasRoomForOutputs) {
            stopWork();
            return false;
        }

        if (processingTime > 0)
            processingTime--;

        if (processingTime <= 0) {
            finishWork();

            // return true so that we don't get "flicker" for the core between recipes
            return true;
        }

        return false;
    }

    private void stopWork() {
        this.processingTime = 0;
        this.recipeTime = -1;
        setChanged();
    }

    private void finishWork() {
        moveStackToOutputs(currentOutput);

        this.amountConsumed = 0;
        this.processingTime = 0;
        this.recipeTime = -1;
        this.amountRequired = -1;
        this.currentOutput = ItemStack.EMPTY;
        this.currentIngredient = Ingredient.EMPTY;
        setChanged();
    }

    private void moveStackToOutputs(ItemStack stack) {
        int firstEmptyIndex = -1;
        for (int outputSlotIndex = 0; outputSlotIndex < OUTPUT_SLOTS; outputSlotIndex++) {
            ItemStack outputStack = outputItemHandler.getStackInSlot(outputSlotIndex).copy();
            if (outputStack.sameItem(stack) && outputStack.getCount() + stack.getCount() <= outputStack.getMaxStackSize()) {
                outputStack.grow(stack.getCount());
                outputItemHandler.setStackInSlot(outputSlotIndex, outputStack);
                return;
            }

            if (outputStack.isEmpty() && firstEmptyIndex < 0) {
                firstEmptyIndex = outputSlotIndex;
            }
        }

        if (firstEmptyIndex >= 0) {
            outputItemHandler.setStackInSlot(firstEmptyIndex, stack.copy());
        } else {
            throw new IllegalStateException("Attempted to move stack to output slots without checking if there is room first!");
        }
    }

    @Nullable
    public QuantumCollapsingRecipe getRecipe() {
        if (this.level == null
                || this.inputItemHandler.getStackInSlot(0).isEmpty())
            return null;

        // make an inventory
        IInventory inv = getInputsAsInventory();

        return this.level.getRecipeManager().getRecipeFor(ModRecipeTypes.COLLAPSING, inv, this.level).orElse(null);
    }

    private Inventory getInputsAsInventory() {
        return new Inventory(this.inputItemHandler.getStackInSlot(0).copy());
    }

    private ItemStack getWorkOutput(@Nullable QuantumCollapsingRecipe recipe) {
        if (recipe != null) {
            return recipe.getResultItem().copy();
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
            // BOTTOM = output, UP = base item input, sides = additional
            switch (side) {
                case UP:
                    return inputHandler.cast();

                default:
                    return outputHandler.cast();
            }
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        inputItemHandler.deserializeNBT(tag.getCompound("invIn"));
        outputItemHandler.deserializeNBT(tag.getCompound("invOut"));
        processingTime = tag.getInt("procTime");
        recipeTime = tag.getInt("recTime");
        amountConsumed = tag.getInt("amtConsumed");
        amountRequired = tag.getInt("amtRequired");

        try {
            String curIngId = tag.getString("curIng");
            currentIngredient = curIngId.isEmpty()
                    ? Ingredient.EMPTY
                    : Ingredient.of(new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(curIngId)), 1));
        } catch(Exception ex) {
            currentIngredient = Ingredient.EMPTY;
        }

        String currentOutputId = tag.getString("curOutId");
        currentOutput = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(currentOutputId)));

        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("invIn", inputItemHandler.serializeNBT());
        tag.put("invOut", outputItemHandler.serializeNBT());
        tag.putInt("procTime", processingTime);
        tag.putInt("recTime", recipeTime);
        tag.putInt("amtConsumed", amountConsumed);
        tag.putInt("amtRequired", amountRequired);

        if (currentIngredient.isEmpty())
            tag.putString("curOutId", "");
        else
            tag.putString("curIng", currentIngredient.getItems()[0].getItem().getRegistryName().toString());

        tag.putString("curOutId", currentOutput.getItem().getRegistryName().toString());

        return super.save(tag);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        inputHandler.invalidate();
        outputHandler.invalidate();
        combinedHandler.invalidate();
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

    private ItemStackHandler createInputHandler() {
        return new ItemStackHandler(INPUT_SLOTS) {

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

    public abstract Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity);

    public int getProcessingTime() {
        return this.processingTime;
    }

    public void setProcessingTime(int value) {
        this.processingTime = value;
    }

    public int getRecipeTime() {
        return this.recipeTime == 0 ? -1 : this.recipeTime;
    }

    public void setRecipeTime(int value) {
        this.recipeTime = value <= 0 ? -1 : value;
    }

    public int getAmountConsumed() {
        return this.amountConsumed;
    }

    public void setAmountConsumed(int value) {
        this.amountConsumed = value;
    }

    public int getAmountRequired() {
        return this.amountRequired == 0 ? -1 : this.amountRequired;
    }

    public void setAmountRequired(int value) {
        this.amountRequired = value;
    }

    public ItemStack getCollapsingItemStackForDisplay() {
        if (this.currentIngredient.isEmpty())
            return ItemStack.EMPTY;

        return this.currentIngredient.getItems()[0].copy();
    }

    public ItemStack getSingularityOutputForDisplay() {
        return this.currentOutput.copy();
    }
}
