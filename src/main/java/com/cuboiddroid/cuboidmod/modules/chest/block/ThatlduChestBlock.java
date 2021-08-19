package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.ThatlduChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class ThatlduChestBlock extends CuboidChestBlockBase {

    public ThatlduChestBlock(Properties properties) {
        super(CuboidChestTypes.THATLDU, ModTileEntities.THATLDU_CHEST::get, properties, Config.thatlduChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ThatlduChestTileEntity();
    }
}
