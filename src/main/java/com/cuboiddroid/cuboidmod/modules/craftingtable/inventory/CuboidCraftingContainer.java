package com.cuboiddroid.cuboidmod.modules.craftingtable.inventory;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.WorkbenchContainer;
import net.minecraft.util.IWorldPosCallable;

public class CuboidCraftingContainer extends WorkbenchContainer {
    private final Block workbench;
    private final IWorldPosCallable worldPos;

    public CuboidCraftingContainer(int id, PlayerInventory playerInv, IWorldPosCallable worldPos, Block workbench) {
        super(id, playerInv, worldPos);
        this.workbench = workbench;
        this.worldPos = worldPos;
    }

    public static boolean isWithinUsableDistance(IWorldPosCallable worldPos, PlayerEntity player, Block targetBlock) {
        return worldPos.evaluate((level, pos) ->
                level.getBlockState(pos).getBlock() == targetBlock && player.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D, true);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return isWithinUsableDistance(this.worldPos, player, this.workbench);
    }
}