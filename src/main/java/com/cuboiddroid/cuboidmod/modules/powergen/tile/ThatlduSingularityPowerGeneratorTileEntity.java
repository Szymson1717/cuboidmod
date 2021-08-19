package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.ThatlduSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class ThatlduSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public ThatlduSingularityPowerGeneratorTileEntity() {
        super(ModTileEntities.THATLDU_SINGULARITY_POWER_GENERATOR.get(),
                Config.thatlduSingularityPowerGeneratorEnergyCapacity.get(),
                Config.thatlduSingularityPowerGeneratorTicksPerCycle.get(),
                Config.thatlduSingularityPowerGeneratorBaseEnergyGenerated.get(),
                Config.thatlduSingularityPowerGeneratorMaxEnergyOutputPerTick.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ThatlduSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
