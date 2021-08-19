package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.tile.NotsogudiumChestTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class NotsogudiumChestBlock extends CuboidChestBlockBase {

    public NotsogudiumChestBlock(Properties properties) {
        super(CuboidChestTypes.NOTSOGUDIUM, ModTileEntities.NOTSOGUDIUM_CHEST::get, properties, Config.notsogudiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new NotsogudiumChestTileEntity();
    }
}
