package com.cuboiddroid.cuboidmod.modules.furnace.block;

import com.cuboiddroid.cuboidmod.modules.furnace.tile.EumusFurnaceTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class EumusFurnaceBlock extends CuboidFurnaceBlockBase {

    public EumusFurnaceBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 1;
    // }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new EumusFurnaceTileEntity(pos, state);
    }
}