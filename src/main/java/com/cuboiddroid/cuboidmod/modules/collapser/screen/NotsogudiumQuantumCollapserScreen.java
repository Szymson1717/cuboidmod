package com.cuboiddroid.cuboidmod.modules.collapser.screen;

import com.cuboiddroid.cuboidmod.modules.collapser.inventory.NotsogudiumQuantumCollapserContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumQuantumCollapserScreen extends QuantumCollapserScreenBase<NotsogudiumQuantumCollapserContainer> {

    public NotsogudiumQuantumCollapserScreen(NotsogudiumQuantumCollapserContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}