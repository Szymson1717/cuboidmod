package com.cuboiddroid.cuboidmod.modules.tools;

import com.google.common.collect.ImmutableSet;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;

/*
  Based on the hammers from easy_steel by kwpugh
 */
public abstract class SmasherBase extends PickaxeItem {
    public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(
            Material.STONE, Material.METAL, Material.GLASS, Material.ICE,
            Material.ICE_SOLID, Material.HEAVY_METAL);

    public SmasherBase(IItemTier tier, int attackDamage, double attackSpeed, Properties properties) {
        super(
                tier,
                attackDamage - (int) (tier.getAttackDamageBonus()) - 1,
                (float) (-4.0F + attackSpeed),
                properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity entity) {
        stack.hurt(1, random, null);

        if (entity instanceof PlayerEntity) {
            ToolUtils.tryBreakAdjacent(world, pos, (PlayerEntity) entity, EFFECTIVE_MATERIALS);
        }
        return super.mineBlock(stack, world, state, pos, entity);
    }

    @Override
    public int getBurnTime(ItemStack itemStack) {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add((new TranslationTextComponent("hover_text.cuboidmod.smasher").withStyle(TextFormatting.GREEN)));
    }
}