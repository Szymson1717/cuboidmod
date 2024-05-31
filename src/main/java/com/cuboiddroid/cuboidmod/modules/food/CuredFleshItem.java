package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Made by crafting rotten flesh and apple cider vinegar
 */
public class CuredFleshItem extends Item {
    public static final String ID_STRING = "cured_flesh";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public CuredFleshItem() {
        super(new Properties()
                
                .food(new FoodProperties.Builder()
                        .nutrition(5)
                        .saturationMod(0.3F)
                        .meat()
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item." + CuboidMod.MOD_ID + ".cured_flesh.hover_text"));
    }
}
