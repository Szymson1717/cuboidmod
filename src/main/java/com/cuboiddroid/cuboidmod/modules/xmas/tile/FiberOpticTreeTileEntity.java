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
        this.mode = tag.getByte("mode");
        super.load(tag);
    }

    @Override
    public CompoundTag save(CompoundTag tag) {
        tag.putByte("mode", (byte)this.mode);
        return super.save(tag);
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        CompoundTag nbtTag = new CompoundTag();
        this.save(nbtTag);
        this.setChanged();
        return new ClientboundBlockEntityDataPacket(getBlockPos(), -1, nbtTag);
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
        CompoundTag nbtTagCompound = new CompoundTag();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(CompoundTag nbt) {
        this.load(nbt);
    }
}
