package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import com.cuboiddroid.cuboidmod.setup.ModTags;
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
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntityTicker ;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

@SuppressWarnings("rawtypes")
public abstract class SingularityPowerGeneratorTileEntityBase extends BlockEntity implements BlockEntityTicker  {

    private ItemStackHandler itemHandler = createHandler();
    private CuboidEnergyStorage energyStorage;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    public static final int SINGULARITY_INPUT = 0;

    private int counter;

    private int energyCapacity;
    private int ticksPerCycle;
    private int baseEnergyGenerated;
    private int maxEnergyOutputPerTick;
    private int energyProducedPerCycle = 80;
    private float singularityMultiplier = 0.0F;

    private PowerGeneratingRecipe cachedRecipe = null;

    public SingularityPowerGeneratorTileEntityBase(BlockEntityType<?> tileEntityType, int energyCapacity, int ticksPerCycle, int baseEnergyGenerated, int maxEnergyOutputPerTick) {
        this(tileEntityType, null, null, energyCapacity, ticksPerCycle, baseEnergyGenerated, maxEnergyOutputPerTick);
    }

    public SingularityPowerGeneratorTileEntityBase(BlockEntityType<?> tileEntityType, BlockPos pos, BlockState state, int energyCapacity, int ticksPerCycle, int baseEnergyGenerated, int maxEnergyOutputPerTick) {
        super(tileEntityType, pos, state);

        this.energyCapacity = energyCapacity;
        this.ticksPerCycle = ticksPerCycle;
        this.baseEnergyGenerated = baseEnergyGenerated;
        this.maxEnergyOutputPerTick = maxEnergyOutputPerTick;

        energyStorage = createEnergy();
    }

    /**
     * implementing classes should do something like this:
     *
     * return Component.translatable("cuboidmod.container.[identifier]");
     *
     * @return the display name
     */
    public abstract Component getDisplayName();

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    public static <T extends SingularityPowerGeneratorTileEntityBase> void gameTick(Level level, BlockPos worldPosition, BlockState blockState, T entity) {
        entity.tick(level, worldPosition, blockState, entity);
    }

    @Override
    public void tick(Level level, BlockPos worldPosition, BlockState blockState, BlockEntity entity) {
        if (level.isClientSide) {
            return;
        }

        if (this.energyStorage.getEnergyStored() >= this.energyCapacity) {
            // we're full - don't keep working!
            counter = 0;
        } else {
            PowerGeneratingRecipe recipe = getRecipe();
            if (recipe != null) {
                if (counter > 0) {
                    counter--;
                    if (counter <= 0) {
                        energyStorage.addEnergy(this.energyProducedPerCycle, this.energyCapacity);
                    }
                    setChanged();
                }

                if (counter <= 0) {
                    this.singularityMultiplier = recipe.getPowerMultiplier();

                    if (this.singularityMultiplier > 0.0F) {
                        // simulate only as we don't consume the singularity!
                        itemHandler.extractItem(SINGULARITY_INPUT, 1, true);
                        counter = ticksPerCycle;

                        // calculate energy produced per cycle by multiplying
                        // the base energy generated by the singularity's
                        // generation rate multiplier, and by the number
                        // of ticks per generation cycle
                        energyProducedPerCycle = (int) (baseEnergyGenerated * ticksPerCycle * singularityMultiplier);
                        setChanged();
                    }
                }
            }
        }

        Boolean shouldBeLit = (counter > 0 || this.energyStorage.getEnergyStored() > 0) && !itemHandler.getStackInSlot(0).isEmpty();
        if (blockState.getValue(BlockStateProperties.LIT) != shouldBeLit) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.LIT, shouldBeLit), Block.UPDATE_ALL);
        }

        sendOutPower();
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity te = this.level.getBlockEntity(this.worldPosition.relative(direction));
                Direction opposite = direction.getOpposite();
                if (te != null) {
                    boolean doContinue = te.getCapability(ForgeCapabilities.ENERGY, opposite).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), maxEnergyOutputPerTick), false);
                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    @Nullable
    public PowerGeneratingRecipe getRecipe() {
        if (this.level == null
                || this.itemHandler.getStackInSlot(0).isEmpty())
            return null;

        // make an inventory
        Container inv = getInputsAsInventory();

        if (cachedRecipe == null || !cachedRecipe.matches(inv, this.level)) {
            RecipeType<PowerGeneratingRecipe> recipeType = ModRecipeTypes.POWER_GENERATING.getRecipeType();
            cachedRecipe = this.level.getRecipeManager().getRecipeFor(recipeType, inv, this.level).orElse(null);
        }

        return cachedRecipe;
    }

    private SimpleContainer getInputsAsInventory() {
        return new SimpleContainer(this.itemHandler.getStackInSlot(0).copy());
    }

    public int getBaseEnergyGenerated() {
        return this.baseEnergyGenerated;
    }

    public int getTicksPerCycle() {
        return this.ticksPerCycle;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);

        itemHandler.deserializeNBT(tag.getCompound("inv"));
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        counter = tag.getInt("counter");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        tag.put("inv", itemHandler.serializeNBT());
        tag.put("energy", energyStorage.serializeNBT());
        tag.putInt("counter", counter);
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                // To make sure the TE persists when the chunk is saved later we need to
                // mark it dirty every time the item handler changes
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return stack.is(ModTags.Items.QUANTUM_SINGULARITIES);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!stack.is(ModTags.Items.QUANTUM_SINGULARITIES)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private CuboidEnergyStorage createEnergy() {
        return new CuboidEnergyStorage(this.energyCapacity, 0, this.maxEnergyOutputPerTick) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }

    public abstract AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity);

    public Container getContentDrops() {
        return getInputsAsInventory();
    }
}
