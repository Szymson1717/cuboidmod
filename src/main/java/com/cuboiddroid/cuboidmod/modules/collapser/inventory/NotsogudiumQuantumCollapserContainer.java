package com.cuboiddroid.cuboidmod.modules.collapser.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

import static com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer.isWithinUsableDistance;

public class NotsogudiumQuantumCollapserContainer extends QuantumCollapserContainerBase {

    public NotsogudiumQuantumCollapserContainer(int windowId, Level level, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(), windowId, level, pos, playerInventory, player);
    }

    @Override
    public boolean stillValid(Player player) {
        return isWithinUsableDistance(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get());
    }
}