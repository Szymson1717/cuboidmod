package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotarfbadiumSingularityPowerGeneratorContainer;
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

public class NotarfbadiumSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public NotarfbadiumSingularityPowerGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get().defaultBlockState());
    }

    public NotarfbadiumSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                ModGeneratorTiers.NOTARFBADIUM.getEnergyCapacity(),
                ModGeneratorTiers.NOTARFBADIUM.getTicksPerCycle(),
                ModGeneratorTiers.NOTARFBADIUM.getBaseEnergyGenerated(),
                ModGeneratorTiers.NOTARFBADIUM.getMaxEnergyOutputPerTick());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.notarfbadium_singularity_power_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new NotarfbadiumSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
