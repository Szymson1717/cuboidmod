package com.cuboiddroid.cuboidmod.modules.collapser.block;

import com.cuboiddroid.cuboidmod.modules.collapser.tile.ThatlduQuantumCollapserTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.THATLDU_QUANTUM_COLLAPSER.get(), ThatlduQuantumCollapserTileEntity::gameTick);
    }
}