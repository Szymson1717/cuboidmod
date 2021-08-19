package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class KudbebeddaChestTileEntity extends CuboidChestTileEntityBase {

    public KudbebeddaChestTileEntity() {
        super(ModTileEntities.KUDBEBEDDA_CHEST.get(),
                CuboidChestTypes.KUDBEBEDDA,
                ModBlocks.KUDBEBEDDA_CHEST::get,
                Config.kudbebeddaChestRetainsInventoryWhenBroken.get(),
                Config.kudbebeddaChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return CuboidChestContainer.createKudbebeddaContainer(id, playerInventory, this);
    }
}
