package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.KudbebeddaSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModGeneratorTiers;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class KudbebeddaSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public KudbebeddaSingularityResourceGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get().defaultBlockState());
    }

    public KudbebeddaSingularityResourceGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(),
                pos, state,
                ModGeneratorTiers.KUDBEBEDDA.getTicksPerOperation(),
                ModGeneratorTiers.KUDBEBEDDA.getItemsPerOperation(),
                1024 * 4);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(CuboidMod.MOD_ID + ".container.kudbebedda_singularity_resource_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new KudbebeddaSingularityResourceGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}