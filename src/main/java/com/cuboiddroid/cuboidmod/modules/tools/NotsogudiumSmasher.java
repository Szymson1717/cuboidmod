package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItemTiers;

public class NotsogudiumSmasher extends SmasherBase {
    public NotsogudiumSmasher() {
        super(ModItemTiers.NOTSOGUDIUM,
                Config.notsogudiumSmasherAttackDamage.get(),
                Config.notsogudiumSmasherAttackSpeed.get(),
                new Properties()
                        .defaultDurability(Config.notsogudiumSmasherDurability.get())
                        .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP));
    }
}
