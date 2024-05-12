package com.cuboiddroid.cuboidmod.util;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;

public class ContainerHelper {
    public static boolean isWithinUsableDistance(ContainerLevelAccess worldPos, Player player, Block targetBlock) {
        return worldPos.evaluate((level, pos) ->
                level.getBlockState(pos).getBlock() == targetBlock && player.distanceToSqr((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D) <= 64.0D, true);
    }

}
