package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Made by combining an apple and water bottle in a QTC
 * 50% chance of nausea when eaten
 */
public class AppleCiderVinegarItem extends Item {
    public static final String ID_STRING = "apple_cider_vinegar";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public AppleCiderVinegarItem() {
        super(new Properties()
                
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.2F)
                        .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 10 * 20, 0), 0.25F)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.cuboidmod.apple_cider_vinegar.hover_text"));
    }
}
