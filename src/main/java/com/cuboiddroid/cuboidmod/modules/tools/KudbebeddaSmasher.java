package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItemTiers;

public class KudbebeddaSmasher extends SmasherBase {
    public KudbebeddaSmasher() {
        super(ModItemTiers.KUDBEBEDDA,
                Config.kudbebeddaSmasherAttackDamage.get(),
                Config.kudbebeddaSmasherAttackSpeed.get(),
                new Properties()
                        .defaultDurability(Config.kudbebeddaSmasherDurability.get())
                        );
    }
}
