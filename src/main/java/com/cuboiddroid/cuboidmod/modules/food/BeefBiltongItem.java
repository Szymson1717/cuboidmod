package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Made by drying cured beef in a drying cupboard
 */
public class BeefBiltongItem extends Item {
    public static final String ID_STRING = "biltong_beef";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public BeefBiltongItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .food(new Food.Builder()
                        .nutrition(12)
                        .saturationMod(2.0F)
                        .meat()
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World level, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("item.cuboidmod.biltong_beef.hover_text"));
    }
}
