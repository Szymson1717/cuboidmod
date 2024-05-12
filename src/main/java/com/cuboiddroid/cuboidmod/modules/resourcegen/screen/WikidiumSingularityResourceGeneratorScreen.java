package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.WikidiumSingularityResourceGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WikidiumSingularityResourceGeneratorScreen extends SingularityResourceGeneratorScreenBase<WikidiumSingularityResourceGeneratorContainer> {

    public WikidiumSingularityResourceGeneratorScreen(WikidiumSingularityResourceGeneratorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}