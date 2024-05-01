package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItemTiers;

public class NotarfbadiumSmasher extends SmasherBase {
    public NotarfbadiumSmasher() {
        super(ModItemTiers.NOTARFBADIUM,
                Config.notarfbadiumSmasherAttackDamage.get(),
                Config.notarfbadiumSmasherAttackSpeed.get(),
                new Properties()
                        .defaultDurability(Config.notarfbadiumSmasherDurability.get())
                        );
    }
}
