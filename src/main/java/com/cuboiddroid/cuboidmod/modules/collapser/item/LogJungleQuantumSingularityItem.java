package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import net.minecraft.util.ResourceLocation;

public class LogJungleQuantumSingularityItem extends QuantumSingularityItemBase {
    public LogJungleQuantumSingularityItem() {
        super(new Properties().tab(CuboidMod.CUBOIDMOD_ITEM_GROUP),
                QuantumSingularityRegistry.getInstance()
                        .getSingularityById(new ResourceLocation(CuboidMod.MOD_ID, "jungle_log")));
    }
}
