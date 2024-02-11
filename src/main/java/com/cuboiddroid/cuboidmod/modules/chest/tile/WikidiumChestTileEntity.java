package com.cuboiddroid.cuboidmod.modules.chest.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;

public class WikidiumChestTileEntity extends CuboidChestTileEntityBase {

    public WikidiumChestTileEntity() {
        this(BlockPos.ZERO, ModBlocks.WIKIDIUM_CHEST.get().defaultBlockState());
    }

    public WikidiumChestTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.WIKIDIUM_CHEST.get(),
                pos, state,
                CuboidChestTypes.NOTARFBADIUM,
                ModBlocks.WIKIDIUM_CHEST::get,
                Config.wikidiumChestRetainsInventoryWhenBroken.get(),
                Config.wikidiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return CuboidChestContainer.createWikidiumContainer(id, playerInventory, this);
    }
}
