package com.cuboiddroid.cuboidmod.modules.resourcegen.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotsogudiumSingularityResourceGeneratorContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class NotsogudiumSingularityResourceGeneratorTileEntity extends SingularityResourceGeneratorTileEntityBase {
    public NotsogudiumSingularityResourceGeneratorTileEntity() {
        this(null, null);
    }

    public NotsogudiumSingularityResourceGeneratorTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                pos, state,
                Config.notsogudiumSingularityResourceGeneratorTicksPerOperation.get(),
                Config.notsogudiumSingularityResourceGeneratorItemsPerOperation.get(),
                1024 * 4);
    }

    @Override
    public Component getDisplayName() {
        return new TranslatableComponent("cuboidmod.container.notsogudium_singularity_resource_generator");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new NotsogudiumSingularityResourceGeneratorContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}