package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.ThatlduSingularityResourceGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThatlduSingularityResourceGeneratorScreen extends SingularityResourceGeneratorScreenBase<ThatlduSingularityResourceGeneratorContainer> {

    public ThatlduSingularityResourceGeneratorScreen(ThatlduSingularityResourceGeneratorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}