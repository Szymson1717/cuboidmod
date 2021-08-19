package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.KudbebeddaChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class KudbebeddaChestBlock extends CuboidChestBlockBase {

    public KudbebeddaChestBlock(Properties properties) {
        super(CuboidChestTypes.KUDBEBEDDA, ModTileEntities.KUDBEBEDDA_CHEST::get, properties, Config.kudbebeddaChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new KudbebeddaChestTileEntity();
    }
}
