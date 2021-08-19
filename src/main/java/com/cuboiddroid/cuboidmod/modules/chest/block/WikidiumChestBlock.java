package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.WikidiumChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class WikidiumChestBlock extends CuboidChestBlockBase {

    public WikidiumChestBlock(Properties properties) {
        super(CuboidChestTypes.WIKIDIUM, ModTileEntities.WIKIDIUM_CHEST::get, properties, Config.wikidiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new WikidiumChestTileEntity();
    }
}
