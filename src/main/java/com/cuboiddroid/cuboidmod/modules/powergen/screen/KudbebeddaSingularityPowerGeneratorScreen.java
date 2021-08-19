package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.KudbebeddaSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KudbebeddaSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<KudbebeddaSingularityPowerGeneratorContainer> {

    public KudbebeddaSingularityPowerGeneratorScreen(KudbebeddaSingularityPowerGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container,
                inv,
                name != null
                        ? name
                        : new TranslationTextComponent("cuboidmod.container.kudbebedda_singularity_power_generator"));
    }
}
