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

public class KudbebeddaChestTileEntity extends CuboidChestTileEntityBase {

    public KudbebeddaChestTileEntity() {
        this(BlockPos.ZERO, ModBlocks.KUDBEBEDDA_CHEST.get().defaultBlockState());
    }

    public KudbebeddaChestTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.KUDBEBEDDA_CHEST.get(),
                pos, state,
                CuboidChestTypes.KUDBEBEDDA,
                ModBlocks.KUDBEBEDDA_CHEST::get,
                Config.kudbebeddaChestRetainsInventoryWhenBroken.get(),
                Config.kudbebeddaChestCanOpenWhenObstructedAbove.get());
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInventory) {
        return CuboidChestContainer.createKudbebeddaContainer(id, playerInventory, this);
    }
}
