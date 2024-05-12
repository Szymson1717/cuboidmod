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

public class NotarfbadiumChestTileEntity extends CuboidChestTileEntityBase {

    public NotarfbadiumChestTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTARFBADIUM_CHEST.get().defaultBlockState());
    }

    public NotarfbadiumChestTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTARFBADIUM_CHEST.get(),
                pos, state,
                CuboidChestTypes.NOTARFBADIUM,
                ModBlocks.NOTARFBADIUM_CHEST::get,
                Config.notarfbadiumChestRetainsInventoryWhenBroken.get(),
                Config.notarfbadiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return CuboidChestContainer.createNotarfbadiumContainer(id, playerInventory, this);
    }
}
