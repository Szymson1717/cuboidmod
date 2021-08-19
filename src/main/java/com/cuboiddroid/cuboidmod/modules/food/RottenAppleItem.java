package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;

/**
 * Made by combining an apple and rotten flesh in a QTC
 * 15% chance of poison when eaten
 * 50% chance of nausea when eaten
 */
public class RottenAppleItem extends Item {
    public static final String ID_STRING = "rotten_apple";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public RottenAppleItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .food(new Food.Builder()
                        .nutrition(4)
                        .saturationMod(0.3F)
                        .effect(() -> new EffectInstance(Effects.CONFUSION, 10 * 20, 0), 0.5F)
                        .effect(() -> new EffectInstance(Effects.POISON, 5 * 20, 0), 0.15F)
                        .build()));
    }
}
