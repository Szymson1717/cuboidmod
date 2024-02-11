package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotarfbadiumSingularityPowerGeneratorContainer;
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
        this(null, null);
    }

    public NotarfbadiumSingularityPowerGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(),
                pos, state,
                Config.notarfbadiumSingularityPowerGeneratorEnergyCapacity.get(),
                Config.notarfbadiumSingularityPowerGeneratorTicksPerCycle.get(),
                Config.notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated.get(),
                Config.notarfbadiumSingularityPowerGeneratorMaxEnergyOutputPerTick.get());
    }

    @Override
    public Component getDisplayName() {
        return null;
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new NotarfbadiumSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
