package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.KudbebeddaSingularityPowerGeneratorTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class KudbebeddaSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public KudbebeddaSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new KudbebeddaSingularityPowerGeneratorTileEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(), KudbebeddaSingularityPowerGeneratorTileEntity::gameTick);
    }
}
