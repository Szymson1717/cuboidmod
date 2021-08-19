package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class NotsogudiumChestTileEntity extends CuboidChestTileEntityBase {

    public NotsogudiumChestTileEntity() {
        super(ModTileEntities.NOTSOGUDIUM_CHEST.get(),
                CuboidChestTypes.NOTSOGUDIUM,
                ModBlocks.NOTSOGUDIUM_CHEST::get,
                Config.notsogudiumChestRetainsInventoryWhenBroken.get(),
                Config.notsogudiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInventory) {
        return CuboidChestContainer.createNotsogudiumContainer(id, playerInventory, this);
    }
}
