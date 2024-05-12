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

public class NotsogudiumChestTileEntity extends CuboidChestTileEntityBase {

    public NotsogudiumChestTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTSOGUDIUM_CHEST.get().defaultBlockState());
    }

    public NotsogudiumChestTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTSOGUDIUM_CHEST.get(),
                pos, state,
                CuboidChestTypes.NOTSOGUDIUM,
                ModBlocks.NOTSOGUDIUM_CHEST::get,
                Config.notsogudiumChestRetainsInventoryWhenBroken.get(),
                Config.notsogudiumChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return CuboidChestContainer.createNotsogudiumContainer(id, playerInventory, this);
    }
}
