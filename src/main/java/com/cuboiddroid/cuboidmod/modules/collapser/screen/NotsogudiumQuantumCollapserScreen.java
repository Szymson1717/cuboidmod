package com.cuboiddroid.cuboidmod.modules.collapser.screen;

import com.cuboiddroid.cuboidmod.modules.collapser.inventory.NotsogudiumQuantumCollapserContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumQuantumCollapserScreen extends QuantumCollapserScreenBase<NotsogudiumQuantumCollapserContainer> {

    public NotsogudiumQuantumCollapserScreen(NotsogudiumQuantumCollapserContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}