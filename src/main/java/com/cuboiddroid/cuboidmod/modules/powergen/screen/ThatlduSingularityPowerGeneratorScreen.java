package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.ThatlduSingularityPowerGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThatlduSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<ThatlduSingularityPowerGeneratorContainer> {

    public ThatlduSingularityPowerGeneratorScreen(ThatlduSingularityPowerGeneratorContainer container, Inventory inv, Component name) {
        super(container,
                inv,
                name != null
                        ? name
                        : Component.translatable(CuboidMod.MOD_ID + ".container.thatldu_singularity_power_generator"));
    }
}
