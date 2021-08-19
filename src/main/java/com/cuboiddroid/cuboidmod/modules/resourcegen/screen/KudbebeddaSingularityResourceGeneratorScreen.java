package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.KudbebeddaSingularityResourceGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KudbebeddaSingularityResourceGeneratorScreen extends SingularityResourceGeneratorScreenBase<KudbebeddaSingularityResourceGeneratorContainer> {

    public KudbebeddaSingularityResourceGeneratorScreen(KudbebeddaSingularityResourceGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}