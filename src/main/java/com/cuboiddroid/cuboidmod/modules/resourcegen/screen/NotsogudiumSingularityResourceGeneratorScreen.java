package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotsogudiumSingularityResourceGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumSingularityResourceGeneratorScreen
        extends SingularityResourceGeneratorScreenBase<NotsogudiumSingularityResourceGeneratorContainer> {

    public NotsogudiumSingularityResourceGeneratorScreen(NotsogudiumSingularityResourceGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}