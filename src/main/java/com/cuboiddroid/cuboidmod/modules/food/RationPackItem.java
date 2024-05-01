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

public class RationPackItem extends Item {
    public static final String ID_STRING = "ration_pack";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public RationPackItem() {
        super(new Properties()
                
                .stacksTo(8)
                .food(new FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.4F)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("item.cuboidmod.ration_pack.hover_text"));
    }
}
