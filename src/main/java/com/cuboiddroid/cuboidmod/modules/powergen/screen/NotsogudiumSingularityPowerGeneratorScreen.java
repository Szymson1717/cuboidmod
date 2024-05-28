package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<NotsogudiumSingularityPowerGeneratorContainer> {

    public NotsogudiumSingularityPowerGeneratorScreen(NotsogudiumSingularityPowerGeneratorContainer container, Inventory inv, Component name) {
        super(container,
                inv,
                name != null
                        ? name
                        : Component.translatable(CuboidMod.MOD_ID + ".container.notsogudium_singularity_power_generator"));
    }
}
