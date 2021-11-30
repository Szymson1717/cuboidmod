package com.cuboiddroid.cuboidmod.modules.cdt.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.DimensionType;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CryogenicDimensionalTeleporterTileEntity extends TileEntity implements ITickableTileEntity {
    private CuboidEnergyStorage energyStorage;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    // TODO - create & config
    private int energyCapacity = Config.cryoDimTeleporterEnergyCapacity.get();
    private int maxEnergyReceivedPerTick = Config.cryoDimTeleporterMaxEnergyInputPerTick.get();

    private CdtStates state = CdtStates.PENDING;
    private String targetDimension = "";
    private ItemStack keyItem = ItemStack.EMPTY;

    private boolean isDirty = false;

    public CryogenicDimensionalTeleporterTileEntity() {
        super(ModTileEntities.CRYOGENIC_DIMENSIONAL_TELEPORTER.get());
        energyStorage = createEnergy();
    }

    public CdtStates getState() {
        return state;
    }

    public ItemStack getKeyItem() {
        return keyItem;
    }

    public boolean setTargetDimensionWithKeyItem(ServerPlayerEntity serverPlayer, ItemStack stack) {
        String currentTarget = targetDimension;
        if (stack.getItem() == ModBlocks.ENERGIZED_END_STONE_BRICKS.get().asItem())
        {
            keyItem = stack.copy();
            targetDimension = "minecraft:the_end";
            GameStageHelper.addStage(serverPlayer, "end_access");
        } else if (stack.getItem() == ModBlocks.ENERGIZED_STONE_BRICKS.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "minecraft:overworld";
        } else if (stack.getItem() == ModBlocks.ENERGIZED_THATLDUVIUM.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "cuboidmod:cuboid_overworld";
            GameStageHelper.addStage(serverPlayer, "cuboid_overworld_access");
        } else if (stack.getItem() == ModBlocks.ENERGIZED_NETHER_BRICKS.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "minecraft:the_nether";
            GameStageHelper.addStage(serverPlayer, "nether_access");
        } else {
            if (Config.cryoDimTeleporterClearsTargetDimensionForInvalidKey.get())
            {
                keyItem = ItemStack.EMPTY;
                targetDimension = "";
                state = CdtStates.PENDING;
                energyStorage.setEnergy(0);
            }
            return false;
        }

        if (targetDimension != "" && currentTarget != targetDimension)
        {
            // new dimensions being targeted - set state to charging and start from zero
            state = CdtStates.CHARGING;
            energyStorage.setEnergy(0);

            return true;
        }

        return false;
    }

    public DimensionType GetTargetDimensionIfCharged(World level) {
        // if state != Ready or no target dimension, return null
        if (targetDimension == "" || state != CdtStates.READY)
            return null;

        // try get the actual Dimension identified as the target
        for (ResourceLocation resLoc : level.getServer().registryAccess().dimensionTypes().keySet())
        {
            if (resLoc.toString().equalsIgnoreCase(targetDimension))
                // found it! return the target dimension type
                return level.getServer().registryAccess().dimensionTypes().get(resLoc);
        }

        // could not find the target dimension type
        return null;
    }

    public void onTeleport() {
        energyStorage.setEnergy(0);
        setChanged();
    }

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("container.cuboidmod.cryogenic_dimensional_teleporter");
    }

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide)
            return;

        if (state == CdtStates.CHARGING) {
            // wait until energy bank is full
            if (energyStorage.getEnergyStored() >= energyCapacity) {
                // energy is full, change state to Charged
                if (this.state != CdtStates.READY) {
                    this.state = CdtStates.READY;
                    isDirty = true;
                }
            }
        }

        if (isDirty) {
            setChanged();
        }

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        boolean shouldBeLit = state == CdtStates.CHARGING;
        boolean shouldBeOpen = state == CdtStates.READY;
        if (blockState.getValue(BlockStateProperties.LIT) != shouldBeLit || blockState.getValue(BlockStateProperties.OPEN) != shouldBeOpen) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LIT, shouldBeLit).setValue(BlockStateProperties.OPEN, shouldBeOpen),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityEnergy.ENERGY) {
            return energy.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        this.state = CdtStates.values()[tag.getByte("state")];
        this.targetDimension = tag.getString("tgtDim");
        this.keyItem = ItemStack.of(tag.getCompound("keyItem"));

        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.put("energy", energyStorage.serializeNBT());
        tag.putByte("state", (byte)this.state.ordinal());
        tag.putString("tgtDim", this.targetDimension);
        CompoundNBT keyTag = new CompoundNBT();
        this.keyItem.save(keyTag);
        tag.put("keyItem", keyTag);

        return super.save(tag);
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
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
        if (level == null)
            return;

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
            public boolean canReceive() {
                return super.canReceive() && state == CdtStates.CHARGING;
            }

            @Override
            protected void onEnergyChanged() {
                isDirty = true;
            }
        };
    }

    public int getEnergyCapacity() {
        return this.energyCapacity;
    }

    @Override
    public AxisAlignedBB getRenderBoundingBox() {
        return new AxisAlignedBB(getBlockPos(), getBlockPos().offset(1, 3, 1));
    }

    public boolean isTargetTheEnd() {
        return targetDimension.equalsIgnoreCase("minecraft:the_end");
    }

    public boolean isTargetTheOverworld() {
        return targetDimension.equalsIgnoreCase("minecraft:overworld");
    }

    public boolean isTargetCuboidOverworld() {
        return targetDimension.equalsIgnoreCase("cuboidmod:cuboid_overworld");
    }
}
