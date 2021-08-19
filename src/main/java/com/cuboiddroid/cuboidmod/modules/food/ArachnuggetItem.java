package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

/**
 * Made by smelting a spider eye
 * 10% chance of poison for 2 seconds
 * 2.5% chance of saturation for 5 seconds
 */
public class ArachnuggetItem extends Item {
    public static final String ID_STRING = "arachnugget";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public ArachnuggetItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .food(new Food.Builder()
                        .nutrition(3)
                        .saturationMod(0.4F)
                        .meat()
                        .effect(() -> new EffectInstance(Effects.POISON, 2 * 20, 0), 0.1F)
                        .effect(() -> new EffectInstance(Effects.SATURATION, 5 * 20, 0), 0.025F)
                        .build()));
    }
}
