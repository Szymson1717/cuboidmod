package com.cuboiddroid.cuboidmod.modules.chest.block;

import javax.annotation.Nullable;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.WikidiumChestTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.core.BlockPos;

public class WikidiumChestBlock extends CuboidChestBlockBase {

    public WikidiumChestBlock() {
        super(CuboidChestTypes.WIKIDIUM, ModTileEntities.WIKIDIUM_CHEST::get, null, Config.wikidiumChestCanOpenWhenObstructedAbove.get());
    }

    public WikidiumChestBlock(Properties properties) {
        super(CuboidChestTypes.WIKIDIUM, ModTileEntities.WIKIDIUM_CHEST::get, properties, Config.wikidiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new WikidiumChestTileEntity(pos, state);
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.WIKIDIUM_CHEST.get(), WikidiumChestTileEntity::gameTick);
    }
}
