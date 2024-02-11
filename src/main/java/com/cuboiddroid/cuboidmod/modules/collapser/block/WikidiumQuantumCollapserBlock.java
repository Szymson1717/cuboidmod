package com.cuboiddroid.cuboidmod.modules.collapser.block;

import com.cuboiddroid.cuboidmod.modules.collapser.tile.WikidiumQuantumCollapserTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class WikidiumQuantumCollapserBlock extends QuantumCollapserBlockBase {

    public WikidiumQuantumCollapserBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 3;
    // }
    
    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WikidiumQuantumCollapserTileEntity(pos, state);
    }
}