package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.ThatlduQuantumCollapserContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class ThatlduQuantumCollapserTileEntity extends QuantumCollapserTileEntityBase {
    public ThatlduQuantumCollapserTileEntity() {
        super(ModTileEntities.THATLDU_QUANTUM_COLLAPSER.get(),
                Config.thatlduQuantumCollapserSpeed.get().floatValue());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.thatldu_quantum_collapser");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ThatlduQuantumCollapserContainer(i, level, pos, playerInventory, playerEntity);
    }

}