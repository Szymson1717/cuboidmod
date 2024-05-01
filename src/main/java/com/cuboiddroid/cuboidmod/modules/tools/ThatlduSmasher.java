package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItemTiers;

public class ThatlduSmasher extends SmasherBase {
    public ThatlduSmasher() {
        super(ModItemTiers.THATLDU,
                Config.thatlduSmasherAttackDamage.get(),
                Config.thatlduSmasherAttackSpeed.get(),
                new Properties()
                        .defaultDurability(Config.thatlduSmasherDurability.get())
                        );
    }
}
