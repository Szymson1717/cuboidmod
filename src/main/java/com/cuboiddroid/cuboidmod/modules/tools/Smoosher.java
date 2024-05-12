package com.cuboiddroid.cuboidmod.modules.tools;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;

// basic ore-doubler with configurable durability

public class Smoosher extends Item {
    public Smoosher() {
        super(new Properties()
                .durability(Config.smoosherDurability.get())
                .setNoRepair()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP));
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
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
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(stack, level, tooltip, flag);
        tooltip.add(Component.translatable("hover_text." + CuboidMod.MOD_ID + ".smoosher").withStyle(ChatFormatting.GREEN));
    }
}
