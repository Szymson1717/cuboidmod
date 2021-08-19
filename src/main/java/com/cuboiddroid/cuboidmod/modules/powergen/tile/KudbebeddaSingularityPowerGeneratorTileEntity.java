package com.cuboiddroid.cuboidmod.modules.powergen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.KudbebeddaSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;

public class KudbebeddaSingularityPowerGeneratorTileEntity extends SingularityPowerGeneratorTileEntityBase {
    public KudbebeddaSingularityPowerGeneratorTileEntity() {
        super(ModTileEntities.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(),
                Config.kudbebeddaSingularityPowerGeneratorEnergyCapacity.get(),
                Config.kudbebeddaSingularityPowerGeneratorTicksPerCycle.get(),
                Config.kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated.get(),
                Config.kudbebeddaSingularityPowerGeneratorMaxEnergyOutputPerTick.get());
    }

    @Override
    public ITextComponent getDisplayName() {
        return null;
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new KudbebeddaSingularityPowerGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}
