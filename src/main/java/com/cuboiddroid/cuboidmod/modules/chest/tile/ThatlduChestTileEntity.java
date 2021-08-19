package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class ThatlduChestTileEntity extends CuboidChestTileEntityBase {

    public ThatlduChestTileEntity() {
        super(ModTileEntities.THATLDU_CHEST.get(),
                CuboidChestTypes.THATLDU,
                ModBlocks.THATLDU_CHEST::get,
                Config.thatlduChestRetainsInventoryWhenBroken.get(),
                Config.thatlduChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return CuboidChestContainer.createThatlduContainer(id, playerInventory, this);
    }
}
