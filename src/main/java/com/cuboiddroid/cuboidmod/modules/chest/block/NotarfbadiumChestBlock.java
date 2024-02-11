package com.cuboiddroid.cuboidmod.modules.chest.block;

import javax.annotation.Nullable;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.NotarfbadiumChestTileEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.core.BlockPos;

public class NotarfbadiumChestBlock extends CuboidChestBlockBase {

    public NotarfbadiumChestBlock() {
        super(CuboidChestTypes.NOTARFBADIUM, ModTileEntities.NOTARFBADIUM_CHEST::get, null, Config.notarfbadiumChestCanOpenWhenObstructedAbove.get());
    }

    public NotarfbadiumChestBlock(Properties properties) {
        super(CuboidChestTypes.NOTARFBADIUM, ModTileEntities.NOTARFBADIUM_CHEST::get, properties, Config.notarfbadiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NotarfbadiumChestTileEntity(pos, state);
    }
}
