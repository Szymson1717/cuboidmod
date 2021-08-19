package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.KudbebeddaSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.KudbebeddaSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class KudbebeddaSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public KudbebeddaSingularityResourceGeneratorTileEntity() {
        super(ModTileEntities.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(),
                Config.kudbebeddaSingularityResourceGeneratorTicksPerOperation.get(),
                Config.kudbebeddaSingularityResourceGeneratorItemsPerOperation.get(),
                1024 * 4);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.kudbebedda_singularity_resource_generator");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new KudbebeddaSingularityResourceGeneratorContainer(i, level, pos, playerInventory, playerEntity);
    }
}