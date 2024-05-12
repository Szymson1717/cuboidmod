package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.KudbebeddaQuantumCollapserContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class KudbebeddaQuantumCollapserTileEntity extends QuantumCollapserTileEntityBase {
    public KudbebeddaQuantumCollapserTileEntity() {
        super(ModTileEntities.KUDBEBEDDA_QUANTUM_COLLAPSER.get(),
                Config.kudbebeddaQuantumCollapserSpeed.get().floatValue());
    }

    public KudbebeddaQuantumCollapserTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.KUDBEBEDDA_QUANTUM_COLLAPSER.get(),
                pos, state, Config.kudbebeddaQuantumCollapserSpeed.get().floatValue());
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("cuboidmod.container.kudbebedda_quantum_collapser");
    }

    @Override
    public AbstractContainerMenu createContainer(int i, Level level, BlockPos pos, Inventory playerInventory, Player playerEntity) {
        return new KudbebeddaQuantumCollapserContainer(i, level, pos, playerInventory, playerEntity);
    }

}