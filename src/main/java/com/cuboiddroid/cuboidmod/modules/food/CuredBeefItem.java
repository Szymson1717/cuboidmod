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
 * Made by crafting raw beef and apple cider vinegar
 */
public class CuredBeefItem extends Item {
    public static final String ID_STRING = "cured_beef";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public CuredBeefItem() {
        super(new Properties()
                
                .food(new FoodProperties.Builder()
                        .nutrition(4)
                        .saturationMod(0.8F)
                        .meat()
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.cuboidmod.cured_beef.hover_text"));
    }
}
