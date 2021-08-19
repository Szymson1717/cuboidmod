package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class WikidiumChestTileEntity extends CuboidChestTileEntityBase {

    public WikidiumChestTileEntity() {
        super(ModTileEntities.WIKIDIUM_CHEST.get(),
                CuboidChestTypes.WIKIDIUM,
                ModBlocks.WIKIDIUM_CHEST::get,
                Config.wikidiumChestRetainsInventoryWhenBroken.get(),
                Config.wikidiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return CuboidChestContainer.createWikidiumContainer(id, playerInventory, this);
    }
}
