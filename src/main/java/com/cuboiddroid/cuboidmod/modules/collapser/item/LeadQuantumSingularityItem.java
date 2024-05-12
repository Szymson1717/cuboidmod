package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import net.minecraft.resources.ResourceLocation;

public class LeadQuantumSingularityItem extends QuantumSingularityItemBase {
    public LeadQuantumSingularityItem() {
        super(new Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP),
                QuantumSingularityRegistry.getInstance()
                        .getSingularityById(new ResourceLocation(CuboidMod.MOD_ID, "lead")));
    }
}
