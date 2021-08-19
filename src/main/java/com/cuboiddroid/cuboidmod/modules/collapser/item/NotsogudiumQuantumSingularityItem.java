package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class NotsogudiumQuantumSingularityItem extends QuantumSingularityItemBase {
    public NotsogudiumQuantumSingularityItem() {
        super(new Item.Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP),
                QuantumSingularityRegistry.getInstance()
                        .getSingularityById(new ResourceLocation(CuboidMod.MOD_ID, "notsogudium")));
    }
}
