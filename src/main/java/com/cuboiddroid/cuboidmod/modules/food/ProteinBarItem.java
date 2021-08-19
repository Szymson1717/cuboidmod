package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

/**
 * Made by smelting protein paste.
 */
public class ProteinBarItem extends Item {
    public static final String ID_STRING = "protein_bar";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public ProteinBarItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .food(new Food.Builder()
                        .nutrition(5)
                        .saturationMod(0.6F)
                        .meat()
                        .build()));
    }
}
