package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
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

public class NotsogudiumSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public NotsogudiumSingularityPowerGeneratorTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get().defaultBlockState());
    }

    public NotsogudiumSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                ModGeneratorTiers.NOTSOGUDIUM.getEnergyCapacity(),
                ModGeneratorTiers.NOTSOGUDIUM.getTicksPerCycle(),
                ModGeneratorTiers.NOTSOGUDIUM.getBaseEnergyGenerated(),
                ModGeneratorTiers.NOTSOGUDIUM.getMaxEnergyOutputPerTick());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.notsogudium_singularity_power_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new NotsogudiumSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
