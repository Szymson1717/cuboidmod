package com.cuboiddroid.cuboidmod.modules.resourcegen.inventory;

import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModContainers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer.isWithinUsableDistance;

public class KudbebeddaSingularityResourceGeneratorContainer extends SingularityResourceGeneratorContainerBase {

    public KudbebeddaSingularityResourceGeneratorContainer(int windowId, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(ModContainers.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(), windowId, level, pos, playerInventory, player);
    }

    @Override
    public boolean stillValid(PlayerEntity player) {
        return isWithinUsableDistance(IWorldPosCallable.create(tileEntity.getLevel(), tileEntity.getBlockPos()), playerEntity, ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get());
    }
}