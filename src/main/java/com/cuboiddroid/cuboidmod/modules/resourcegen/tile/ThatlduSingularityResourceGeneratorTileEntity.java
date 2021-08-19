package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.ThatlduSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class ThatlduSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public ThatlduSingularityResourceGeneratorTileEntity() {
        super(ModTileEntities.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(),
                Config.thatlduSingularityResourceGeneratorTicksPerOperation.get(),
                Config.thatlduSingularityResourceGeneratorItemsPerOperation.get(),
                1024 * 4);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.thatldu_singularity_resource_generator");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ThatlduSingularityResourceGeneratorContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}