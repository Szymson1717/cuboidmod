package com.cuboiddroid.cuboidmod.modules.furnace.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import static com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer.isWithinUsableDistance;

public class EumusFurnaceContainer extends CuboidFurnaceContainerBase {

    public EumusFurnaceContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.EUMUS_FURNACE.get(), windowId, level, pos, playerInventory, player);
    }

    public EumusFurnaceContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player, ContainerData fields) {
        super(ModContainers.EUMUS_FURNACE.get(), windowId, level, pos, playerInventory, player, fields);
    }

    @Override
    public boolean stillValid(Player player) {
        return isWithinUsableDistance(ContainerLevelAccess.create(te.getLevel(), te.getBlockPos()), playerEntity, ModBlocks.EUMUS_FURNACE.get());
    }
}