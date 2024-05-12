package com.cuboiddroid.cuboidmod.modules.powergen.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import com.cuboiddroid.cuboidmod.util.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class NotsogudiumSingularityPowerGeneratorContainer extends SingularityPowerGeneratorContainerBase {
    public NotsogudiumSingularityPowerGeneratorContainer(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(ModContainers.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(), windowId, world, pos, playerInventory, player);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return ContainerHelper.isWithinUsableDistance(ContainerLevelAccess.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get());
    }
}
