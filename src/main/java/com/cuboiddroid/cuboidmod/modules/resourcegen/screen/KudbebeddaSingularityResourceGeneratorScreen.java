package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.KudbebeddaSingularityResourceGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KudbebeddaSingularityResourceGeneratorScreen extends SingularityResourceGeneratorScreenBase<KudbebeddaSingularityResourceGeneratorContainer> {

    public KudbebeddaSingularityResourceGeneratorScreen(KudbebeddaSingularityResourceGeneratorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}