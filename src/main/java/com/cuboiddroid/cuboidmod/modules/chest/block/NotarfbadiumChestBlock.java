package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.NotarfbadiumChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class NotarfbadiumChestBlock extends CuboidChestBlockBase {

    public NotarfbadiumChestBlock(Properties properties) {
        super(CuboidChestTypes.NOTARFBADIUM, ModTileEntities.NOTARFBADIUM_CHEST::get, properties, Config.notarfbadiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new NotarfbadiumChestTileEntity();
    }
}
