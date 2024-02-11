package com.cuboiddroid.cuboidmod.modules.resourcegen.block;

import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.NotarfbadiumSingularityResourceGeneratorTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class NotarfbadiumSingularityResourceGeneratorBlock extends SingularityResourceGeneratorBlockBase {

    public NotarfbadiumSingularityResourceGeneratorBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 3;
    // }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NotarfbadiumSingularityResourceGeneratorTileEntity(pos, state);
    }
}