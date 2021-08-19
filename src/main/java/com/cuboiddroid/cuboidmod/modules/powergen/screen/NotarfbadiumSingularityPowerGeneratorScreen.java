package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotarfbadiumSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotarfbadiumSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<NotarfbadiumSingularityPowerGeneratorContainer> {

    public NotarfbadiumSingularityPowerGeneratorScreen(NotarfbadiumSingularityPowerGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container,
                inv,
                name != null
                        ? name
                        : new TranslationTextComponent("cuboidmod.container.notarfbadium_singularity_power_generator"));
    }
}
