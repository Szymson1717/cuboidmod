package com.cuboiddroid.cuboidmod.modules.furnace.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer.isWithinUsableDistance;

public class NotsogudiumFurnaceContainer extends CuboidFurnaceContainerBase {

    public NotsogudiumFurnaceContainer(int windowId, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.NOTSOGUDIUM_FURNACE.get(), windowId, level, pos, playerInventory, player);
    }

    public NotsogudiumFurnaceContainer(int windowId, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(ModContainers.NOTSOGUDIUM_FURNACE.get(), windowId, level, pos, playerInventory, player, fields);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.create(te.getLevel(), te.getBlockPos()), playerEntity, ModBlocks.NOTSOGUDIUM_FURNACE.get());
    }
}