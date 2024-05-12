package com.cuboiddroid.cuboidmod.modules.furnace.block;

import com.cuboiddroid.cuboidmod.modules.furnace.tile.NotarfbadiumFurnaceTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class NotarfbadiumFurnaceBlock extends CuboidFurnaceBlockBase {

    public NotarfbadiumFurnaceBlock(Properties properties) {
        super(properties);
    }

    // @Override
    // public int getHarvestLevel(BlockState state) {
    //     return 2;
    // }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NotarfbadiumFurnaceTileEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.NOTARFBADIUM_FURNACE.get(), NotarfbadiumFurnaceTileEntity::gameTick);
    }
}