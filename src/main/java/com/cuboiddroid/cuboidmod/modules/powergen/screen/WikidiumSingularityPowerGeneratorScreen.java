package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.WikidiumSingularityPowerGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WikidiumSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<WikidiumSingularityPowerGeneratorContainer> {

    public WikidiumSingularityPowerGeneratorScreen(WikidiumSingularityPowerGeneratorContainer container, Inventory inv, Component name) {
        super(container,
                inv,
                name != null
                        ? name
                        : Component.translatable("cuboidmod.container.wikidium_singularity_power_generator"));
    }
}
