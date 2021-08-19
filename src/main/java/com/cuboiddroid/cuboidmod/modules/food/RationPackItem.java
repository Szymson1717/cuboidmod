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

public class RationPackItem extends Item {
    public static final String ID_STRING = "ration_pack";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public RationPackItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(8)
                .food(new Food.Builder()
                        .nutrition(3)
                        .saturationMod(0.4F)
                        .build()));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable World level, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("item.cuboidmod.ration_pack.hover_text"));
    }
}
