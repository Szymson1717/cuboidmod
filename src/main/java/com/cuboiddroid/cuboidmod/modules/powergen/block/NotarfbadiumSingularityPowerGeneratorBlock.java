package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.NotarfbadiumSingularityPowerGeneratorTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), NotarfbadiumSingularityPowerGeneratorTileEntity::gameTick);
    }
}
