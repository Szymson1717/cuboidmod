package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class NotarfbadiumChestTileEntity extends CuboidChestTileEntityBase {

    public NotarfbadiumChestTileEntity() {
        super(ModTileEntities.NOTARFBADIUM_CHEST.get(),
                CuboidChestTypes.NOTARFBADIUM,
                ModBlocks.NOTARFBADIUM_CHEST::get,
                Config.notarfbadiumChestRetainsInventoryWhenBroken.get(),
                Config.notarfbadiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return CuboidChestContainer.createNotarfbadiumContainer(id, playerInventory, this);
    }
}
