package com.cuboiddroid.cuboidmod.modules.collapser.screen;

import com.cuboiddroid.cuboidmod.modules.collapser.inventory.NotarfbadiumQuantumCollapserContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotarfbadiumQuantumCollapserScreen extends QuantumCollapserScreenBase<NotarfbadiumQuantumCollapserContainer> {

    public NotarfbadiumQuantumCollapserScreen(NotarfbadiumQuantumCollapserContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}