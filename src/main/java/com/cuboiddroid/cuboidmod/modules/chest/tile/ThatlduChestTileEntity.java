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

public class ThatlduChestTileEntity extends CuboidChestTileEntityBase {

    public ThatlduChestTileEntity() {
        this(BlockPos.ZERO, ModBlocks.THATLDU_CHEST.get().defaultBlockState());
    }

    public ThatlduChestTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.THATLDU_CHEST.get(),
                pos, state,
                CuboidChestTypes.THATLDU,
                ModBlocks.THATLDU_CHEST::get,
                Config.thatlduChestRetainsInventoryWhenBroken.get(),
                Config.thatlduChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return CuboidChestContainer.createThatlduContainer(id, playerInventory, this);
    }
}
