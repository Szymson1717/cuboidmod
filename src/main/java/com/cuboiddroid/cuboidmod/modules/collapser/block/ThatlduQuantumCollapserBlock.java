package com.cuboiddroid.cuboidmod.modules.collapser.block;

import com.cuboiddroid.cuboidmod.modules.collapser.tile.ThatlduQuantumCollapserTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class ThatlduQuantumCollapserBlock extends QuantumCollapserBlockBase {

    public ThatlduQuantumCollapserBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 3;
    // }


    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ThatlduQuantumCollapserTileEntity(pos, state);
    }
}