package com.cuboiddroid.cuboidmod.modules.chest.block;

import javax.annotation.Nullable;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.ThatlduChestTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

public class ThatlduChestBlock extends CuboidChestBlockBase {

    public ThatlduChestBlock() {
        super(CuboidChestTypes.THATLDU, ModTileEntities.THATLDU_CHEST::get, null, Config.thatlduChestCanOpenWhenObstructedAbove.get());
    }

    public ThatlduChestBlock(Properties properties) {
        super(CuboidChestTypes.THATLDU, ModTileEntities.THATLDU_CHEST::get, properties, Config.thatlduChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ThatlduChestTileEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.THATLDU_CHEST.get(), ThatlduChestTileEntity::gameTick);
    }
}
