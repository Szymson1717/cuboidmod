package com.cuboiddroid.cuboidmod.modules.collapser.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.NotsogudiumQuantumCollapserContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class NotsogudiumQuantumCollapserTileEntity extends QuantumCollapserTileEntityBase {
    public NotsogudiumQuantumCollapserTileEntity() {
        super(ModTileEntities.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(),
                Config.notsogudiumQuantumCollapserSpeed.get().floatValue());
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("cuboidmod.container.notsogudium_quantum_collapser");
    }

    @Override
    public Container createContainer(int i, World level, BlockPos pos, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NotsogudiumQuantumCollapserContainer(i, level, pos, playerInventory, playerEntity);
    }

}