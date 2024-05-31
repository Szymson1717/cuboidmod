package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.ThatlduSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ThatlduSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public ThatlduSingularityResourceGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get().defaultBlockState());
    }

    public ThatlduSingularityResourceGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(),
                pos, state,
                Config.thatlduSingularityResourceGeneratorTicksPerOperation.get(),
                Config.thatlduSingularityResourceGeneratorItemsPerOperation.get(),
                1024 * 4);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(CuboidMod.MOD_ID + ".container.thatldu_singularity_resource_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new ThatlduSingularityResourceGeneratorContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}