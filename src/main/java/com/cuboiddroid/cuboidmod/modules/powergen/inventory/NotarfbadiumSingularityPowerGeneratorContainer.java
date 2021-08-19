package com.cuboiddroid.cuboidmod.modules.powergen.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import com.cuboiddroid.cuboidmod.util.ContainerHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class NotarfbadiumSingularityPowerGeneratorContainer extends SingularityPowerGeneratorContainerBase {
    public NotarfbadiumSingularityPowerGeneratorContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), windowId, world, pos, playerInventory, player);
    }

    @Override
    public boolean stillValid(PlayerEntity playerIn) {
        return ContainerHelper.isWithinUsableDistance(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get());
    }
}
