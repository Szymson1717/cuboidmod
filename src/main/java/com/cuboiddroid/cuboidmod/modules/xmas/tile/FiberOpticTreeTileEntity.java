package com.cuboiddroid.cuboidmod.modules.xmas.tile;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntityTicker ;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

@SuppressWarnings("rawtypes")
public class FiberOpticTreeTileEntity extends BlockEntity implements BlockEntityTicker  {
    public FiberOpticTreeTileEntity() {
        this(BlockPos.ZERO, ModBlocks.FIBER_OPTIC_TREE.get().defaultBlockState());
    }

    public FiberOpticTreeTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.FIBER_OPTIC_TREE.get(), pos, state);
        mode = 0;
    }

    private int mode;

    public static void gameTick(Level level, BlockPos worldPosition, BlockState blockState, FiberOpticTreeTileEntity entity) {
        entity.tick(level, worldPosition, blockState, entity);
    }

    @Override
    public void tick(Level level, BlockPos worldPosition, BlockState blockState, BlockEntity entity) {
        if (level == null || level.isClientSide)
            return;

        if (blockState.getValue(BlockStateProperties.LEVEL) != mode) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.LEVEL, mode), Block.UPDATE_ALL);
        }
    }

    public void changeMode()
    {
        mode++;
        if (mode > 15) mode = 0;
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        this.mode = tag.getByte("mode");
    }

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putByte("mode", (byte)this.mode);
    }

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

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag nbtTagCompound = super.getUpdateTag();
        saveAdditional(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        this.load(nbt);
    }
}
