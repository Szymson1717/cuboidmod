package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotsogudiumSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeConfigSpec;

public class NotsogudiumSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public NotsogudiumSingularityResourceGeneratorTileEntity() {
        super(ModTileEntities.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                Config.notsogudiumSingularityResourceGeneratorTicksPerOperation.get(),
                Config.notsogudiumSingularityResourceGeneratorItemsPerOperation.get(),
                1024 * 4);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.notsogudium_singularity_resource_generator");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NotsogudiumSingularityResourceGeneratorContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}