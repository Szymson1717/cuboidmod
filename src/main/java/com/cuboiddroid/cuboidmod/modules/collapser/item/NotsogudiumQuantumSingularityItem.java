package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;

public class NotsogudiumQuantumSingularityItem extends QuantumSingularityItemBase {
    public NotsogudiumQuantumSingularityItem() {
        super(new Item.Properties(),
                QuantumSingularityRegistry.getInstance()
                        .getSingularityById(new ResourceLocation(CuboidMod.MOD_ID, "notsogudium")));
    }
}
