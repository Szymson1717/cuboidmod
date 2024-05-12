package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotsogudiumSingularityResourceGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumSingularityResourceGeneratorScreen
        extends SingularityResourceGeneratorScreenBase<NotsogudiumSingularityResourceGeneratorContainer> {

    public NotsogudiumSingularityResourceGeneratorScreen(NotsogudiumSingularityResourceGeneratorContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}