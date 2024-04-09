package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.KudbebeddaSingularityPowerGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KudbebeddaSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<KudbebeddaSingularityPowerGeneratorContainer> {

    public KudbebeddaSingularityPowerGeneratorScreen(KudbebeddaSingularityPowerGeneratorContainer container, Inventory inv, Component name) {
        super(container,
                inv,
                name != null
                        ? name
                        : Component.translatable("cuboidmod.container.kudbebedda_singularity_power_generator"));
    }
}
