package com.cuboiddroid.cuboidmod.modules.xmas.tile;

import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class FiberOpticTreeTileEntity  extends TileEntity implements ITickableTileEntity {
    public FiberOpticTreeTileEntity() {
        super(ModTileEntities.FIBER_OPTIC_TREE.get());
        mode = 0;
    }

    private int mode;

    @Override
    public void tick() {
        if (this.level == null || this.level.isClientSide)
            return;

        BlockState blockState = this.level.getBlockState(this.worldPosition);
        if (blockState.getValue(BlockStateProperties.LEVEL) != mode) {
            this.level.setBlock(this.worldPosition, blockState.setValue(BlockStateProperties.LEVEL, mode),
                    Constants.BlockFlags.NOTIFY_NEIGHBORS + Constants.BlockFlags.BLOCK_UPDATE);
        }
    }

    public void changeMode()
    {
        mode++;
        if (mode > 15) mode = 0;
    }

    @Override
    public void load(BlockState state, CompoundNBT tag) {
        this.mode = tag.getByte("mode");
        super.load(state, tag);
    }

    @Override
    public CompoundNBT save(CompoundNBT tag) {
        tag.putByte("mode", (byte)this.mode);
        return super.save(tag);
    }

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

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        save(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound) {
        this.load(blockState, parentNBTTagCompound);
    }
}
