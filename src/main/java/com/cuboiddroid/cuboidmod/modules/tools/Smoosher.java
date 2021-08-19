package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

// basic ore-doubler with configurable durability
public class Smoosher extends Item {
    public Smoosher() {
        super(new Properties()
                .durability(Config.smoosherDurability.get())
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP));
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack stack = itemStack.copy();
        stack.setDamageValue(stack.getDamageValue() + 1);

        if (stack.getDamageValue() >= stack.getMaxDamage()) {
            stack.shrink(1);
        }

        return stack;
    }

    @Override
    public boolean isEnchantable(ItemStack itemStack) {
        return false;
    }

    @Override
    public boolean isValidRepairItem(ItemStack itemToRepair, ItemStack repairItem) {
        return repairItem.sameItem(ModBlocks.NOTSOGUDIUM_BLOCK.get().asItem().getDefaultInstance())
                || repairItem.sameItem(ModBlocks.KUDBEBEDDA_BLOCK.get().asItem().getDefaultInstance())
                || repairItem.sameItem(ModBlocks.NOTARFBADIUM_BLOCK.get().asItem().getDefaultInstance())
                || repairItem.sameItem(ModBlocks.WIKIDIUM_BLOCK.get().asItem().getDefaultInstance())
                || repairItem.sameItem(ModBlocks.THATLDU_BLOCK.get().asItem().getDefaultInstance());
    }

    @OnlyIn(Dist.CLIENT)

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World level, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(new TranslationTextComponent("hover_text." + CuboidMod.MOD_ID + ".smoosher").withStyle(TextFormatting.GREEN));
    }
}
