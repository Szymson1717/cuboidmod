package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

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
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(1)
                .food(new Food.Builder()
                        .nutrition(3)
                        .saturationMod(0.2F)
                        .effect(() -> new EffectInstance(Effects.CONFUSION, 10 * 20, 0), 0.25F)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World level, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("item.cuboidmod.apple_cider_vinegar.hover_text"));
    }
}
