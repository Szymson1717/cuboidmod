package com.cuboiddroid.cuboidmod.modules.resourcegen.block;

import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.ThatlduSingularityResourceGeneratorTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class ThatlduSingularityResourceGeneratorBlock extends SingularityResourceGeneratorBlockBase {

    public ThatlduSingularityResourceGeneratorBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 3;
    // }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ThatlduSingularityResourceGeneratorTileEntity(pos, state);
    }
}