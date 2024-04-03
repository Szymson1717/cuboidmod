package com.cuboiddroid.cuboidmod.modules.cdt.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.util.CuboidEnergyStorage;
// import net.darkhax.gamestages.GameStageHelper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SuppressWarnings("rawtypes")
public class CryogenicDimensionalTeleporterTileEntity extends BlockEntity implements BlockEntityTicker {

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
        super(ModTileEntities.CRYOGENIC_DIMENSIONAL_TELEPORTER.get(), null, null);
        energyStorage = createEnergy();
    }

    public CryogenicDimensionalTeleporterTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.CRYOGENIC_DIMENSIONAL_TELEPORTER.get(), pos, state);
        energyStorage = createEnergy();
    }

    public CdtStates getState() {
        return state;
    }

    public ItemStack getKeyItem() {
        return keyItem;
    }

    public boolean setTargetDimensionWithKeyItem(ServerPlayer serverPlayer, ItemStack stack) {
        String currentTarget = targetDimension;
        if (stack.getItem() == ModBlocks.ENERGIZED_END_STONE_BRICKS.get().asItem())
        {
            keyItem = stack.copy();
            targetDimension = "minecraft:the_end";
            // GameStageHelper.addStage(serverPlayer, "end_access");
        } else if (stack.getItem() == ModBlocks.ENERGIZED_STONE_BRICKS.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "minecraft:overworld";
        } else if (stack.getItem() == ModBlocks.ENERGIZED_THATLDUVIUM.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "cuboidmod:cuboid_overworld";
            // GameStageHelper.addStage(serverPlayer, "cuboid_overworld_access");
        } else if (stack.getItem() == ModBlocks.ENERGIZED_NETHER_BRICKS.get().asItem()) {
            keyItem = stack.copy();
            targetDimension = "minecraft:the_nether";
            // GameStageHelper.addStage(serverPlayer, "nether_access");
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

    @SuppressWarnings("resource")
    public DimensionType GetTargetDimensionIfCharged(ServerPlayer serverPlayer, Level level) {
        // if state != Ready or no target dimension, return null
        if (targetDimension == "" || state != CdtStates.READY)
            return null;

        // try get the actual Dimension identified as the target
        for (ResourceLocation resLoc : level.getServer().registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).keySet())
        {
            if (resLoc.toString().equalsIgnoreCase(targetDimension)) {
                // this is unfortunately, but due to FTB Quests not working as I expected it to,
                // this is to allow anyone on a server to get to target dimensions using a
                // charged CDT
                // if (isTargetCuboidOverworld())
                //     GameStageHelper.addStage(serverPlayer, "cuboid_overworld_access");
                // else if (isTargetTheEnd())
                //     GameStageHelper.addStage(serverPlayer, "end_access");
                // else if (isTargetTheNether())
                //     GameStageHelper.addStage(serverPlayer, "nether_access");

                // found it! return the target dimension type
                return level.getServer().registryAccess().registryOrThrow(Registry.DIMENSION_TYPE_REGISTRY).get(resLoc);
            }
        }

        // could not find the target dimension type
        return null;
    }

    public void onTeleport() {
        energyStorage.setEnergy(0);
        setChanged();
    }

    public MutableComponent getDisplayName() {
        return new TranslatableComponent("container.cuboidmod.cryogenic_dimensional_teleporter");
    }

    public static void gameTick(Level level, BlockPos pos, BlockState blockState, CryogenicDimensionalTeleporterTileEntity blockEntity) {
        blockEntity.tick(level, pos, blockState, blockEntity);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState blockState, BlockEntity blockEntity) {
        if (level == null || level.isClientSide)
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

        if (isDirty) setChanged();

        boolean shouldBeLit = state == CdtStates.CHARGING;
        boolean shouldBeOpen = state == CdtStates.READY;
        if (blockState.getValue(BlockStateProperties.LIT) != shouldBeLit || blockState.getValue(BlockStateProperties.OPEN) != shouldBeOpen) {
            level.setBlock(pos, blockState.setValue(BlockStateProperties.LIT, shouldBeLit).setValue(BlockStateProperties.OPEN, shouldBeOpen), Block.UPDATE_ALL);
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
    public void load(CompoundTag tag) {
        energyStorage.deserializeNBT(tag.getCompound("energy"));
        this.state = CdtStates.values()[tag.getByte("state")];
        this.targetDimension = tag.getString("tgtDim");
        this.keyItem = ItemStack.of(tag.getCompound("keyItem"));

        super.load(tag);
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        tag.put("energy", energyStorage.serializeNBT());
        tag.putByte("state", (byte)this.state.ordinal());
        tag.putString("tgtDim", this.targetDimension);
        CompoundTag keyTag = new CompoundTag();
        this.keyItem.save(keyTag);
        tag.put("keyItem", keyTag);

        super.saveAdditional(tag);
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
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        if (level == null)
            return;

        CompoundTag tag = pkt.getTag();
        this.load(tag);
        this.setChanged();
        level.sendBlockUpdated(worldPosition, level.getBlockState(worldPosition).getBlock().defaultBlockState(), level.getBlockState(worldPosition), 2);
    }

    /* Creates a tag containing all of the TileEntity information, used by vanilla to transmit from server to client
     */
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbtTagCompound = new CompoundTag();
        saveAdditional(nbtTagCompound);
        return nbtTagCompound;
    }

    /* Populates this TileEntity with information from the tag, used by vanilla to transmit from server to client
     */
    @Override
    public void handleUpdateTag(CompoundTag tag) {
        this.load(tag);
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
    public AABB getRenderBoundingBox() {
        return new AABB(getBlockPos(), getBlockPos().offset(1, 3, 1));
    }

    public boolean isTargetTheEnd() {
        return targetDimension.equalsIgnoreCase("minecraft:the_end");
    }

    public boolean isTargetTheNether() {
        return targetDimension.equalsIgnoreCase("minecraft:the_nether");
    }

    public boolean isTargetTheOverworld() {
        return targetDimension.equalsIgnoreCase("minecraft:overworld");
    }

    public boolean isTargetCuboidOverworld() {
        return targetDimension.equalsIgnoreCase("cuboidmod:cuboid_overworld");
    }
}
