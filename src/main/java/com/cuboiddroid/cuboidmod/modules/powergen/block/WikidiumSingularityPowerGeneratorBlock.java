package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.WikidiumSingularityPowerGeneratorTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class WikidiumSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public WikidiumSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WikidiumSingularityPowerGeneratorTileEntity(pos, state);
    }
}
