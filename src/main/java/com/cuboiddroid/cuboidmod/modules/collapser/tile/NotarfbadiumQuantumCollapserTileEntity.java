package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.NotarfbadiumQuantumCollapserContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class NotarfbadiumQuantumCollapserTileEntity extends QuantumCollapserTileEntityBase {
    public NotarfbadiumQuantumCollapserTileEntity() {
        super(ModTileEntities.NOTARFBADIUM_QUANTUM_COLLAPSER.get(),
                Config.notarfbadiumQuantumCollapserSpeed.get().floatValue());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.notarfbadium_quantum_collapser");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NotarfbadiumQuantumCollapserContainer(i, level, pos, playerInventory, playerEntity);
    }

}