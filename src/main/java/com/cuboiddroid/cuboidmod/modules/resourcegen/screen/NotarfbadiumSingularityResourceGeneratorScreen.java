package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotarfbadiumSingularityResourceGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotarfbadiumSingularityResourceGeneratorScreen extends SingularityResourceGeneratorScreenBase<NotarfbadiumSingularityResourceGeneratorContainer> {

    public NotarfbadiumSingularityResourceGeneratorScreen(NotarfbadiumSingularityResourceGeneratorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}