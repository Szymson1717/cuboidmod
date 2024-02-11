package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.world.item.Item.Properties;

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
                .food(new FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.3F)
                        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 10 * 20, 0), 0.5F)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 5 * 20, 0), 0.15F)
                        .build()));
    }
}
