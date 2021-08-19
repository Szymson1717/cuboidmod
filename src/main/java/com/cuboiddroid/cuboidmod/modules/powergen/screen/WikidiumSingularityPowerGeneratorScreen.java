package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotarfbadiumSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.WikidiumSingularityPowerGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WikidiumSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<WikidiumSingularityPowerGeneratorContainer> {

    public WikidiumSingularityPowerGeneratorScreen(WikidiumSingularityPowerGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container,
                inv,
                name != null
                        ? name
                        : new TranslationTextComponent("cuboidmod.container.wikidium_singularity_power_generator"));
    }
}
