package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.modules.powergen.inventory.NotsogudiumSingularityPowerGeneratorContainer;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.NotsogudiumSingularityResourceGeneratorContainer;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumSingularityPowerGeneratorScreen
        extends SingularityPowerGeneratorScreenBase<NotsogudiumSingularityPowerGeneratorContainer> {

    public NotsogudiumSingularityPowerGeneratorScreen(NotsogudiumSingularityPowerGeneratorContainer container, PlayerInventory inv, ITextComponent name) {
        super(container,
                inv,
                name != null
                        ? name
                        : new TranslationTextComponent("cuboidmod.container.notsogudium_singularity_power_generator"));
    }
}
