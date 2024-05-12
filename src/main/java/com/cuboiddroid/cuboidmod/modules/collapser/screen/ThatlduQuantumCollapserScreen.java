package com.cuboiddroid.cuboidmod.modules.collapser.screen;

import com.cuboiddroid.cuboidmod.modules.collapser.inventory.ThatlduQuantumCollapserContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThatlduQuantumCollapserScreen extends QuantumCollapserScreenBase<ThatlduQuantumCollapserContainer> {

    public ThatlduQuantumCollapserScreen(ThatlduQuantumCollapserContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}