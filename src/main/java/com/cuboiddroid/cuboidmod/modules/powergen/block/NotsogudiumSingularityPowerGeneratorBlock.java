package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.NotsogudiumSingularityPowerGeneratorTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class NotsogudiumSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public NotsogudiumSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NotsogudiumSingularityPowerGeneratorTileEntity(pos, state);
    }
}
