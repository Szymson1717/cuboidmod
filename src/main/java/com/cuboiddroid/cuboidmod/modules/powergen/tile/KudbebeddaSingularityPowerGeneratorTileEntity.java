package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.KudbebeddaSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class KudbebeddaSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public KudbebeddaSingularityPowerGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get().defaultBlockState());
    }

    public KudbebeddaSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                Config.kudbebeddaSingularityPowerGeneratorEnergyCapacity.get(),
                Config.kudbebeddaSingularityPowerGeneratorTicksPerCycle.get(),
                Config.kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated.get(),
                Config.kudbebeddaSingularityPowerGeneratorMaxEnergyOutputPerTick.get());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.kudbebedda_singularity_power_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new KudbebeddaSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
