package com.cuboiddroid.cuboidmod.modules.craftingtable.inventory;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class CuboidCraftingContainer extends CraftingMenu {
    private final Block workbench;
    private final ContainerLevelAccess worldPos;

    public CuboidCraftingContainer(int id, Inventory playerInv, ContainerLevelAccess worldPos, Block workbench) {
        super(id, playerInv, worldPos);
        this.workbench = workbench;
        this.worldPos = worldPos;
    }

    public static boolean isWithinUsableDistance(ContainerLevelAccess worldPos, Player player, Block targetBlock) {
        return worldPos.evaluate((level, pos) ->
                level.getBlockState(pos).getBlock() == targetBlock && player.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D, true);
    }

    @Override
    public boolean stillValid(Player player) {
        return isWithinUsableDistance(this.worldPos, player, this.workbench);
    }
}