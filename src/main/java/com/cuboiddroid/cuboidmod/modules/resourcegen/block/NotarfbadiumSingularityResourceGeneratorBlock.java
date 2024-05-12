package com.cuboiddroid.cuboidmod.modules.resourcegen.block;

import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.NotarfbadiumSingularityResourceGeneratorTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
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

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(), NotarfbadiumSingularityResourceGeneratorTileEntity::gameTick);
    }
}