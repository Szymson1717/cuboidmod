package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.NotarfbadiumSingularityPowerGeneratorTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class NotarfbadiumSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public NotarfbadiumSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NotarfbadiumSingularityPowerGeneratorTileEntity(pos, state);
    }
}
