package com.cuboiddroid.cuboidmod.modules.tools;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.List;
import net.minecraft.world.item.crafting.RecipeType;

/*
  Based on the hammers from easy_steel by kwpugh
 */
public abstract class SmasherBase extends PickaxeItem {
    // public static final Set<Material> EFFECTIVE_MATERIALS = ImmutableSet.of(
    //         Material.STONE, Material.METAL, Material.GLASS, Material.ICE,
    //         Material.ICE_SOLID, Material.HEAVY_METAL);

    public SmasherBase(Tier tier, int attackDamage, double attackSpeed, Properties properties) {
        super(
                tier,
                attackDamage - (int) (tier.getAttackDamageBonus()) - 1,
                (float) (-4.0F + attackSpeed),
                properties);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity entity) {
        if (entity instanceof Player) {
            if (ToolUtils.isBreakableWithSmasher(world, pos, (Player) entity))
            {
                ToolUtils.tryBreakAdjacent(stack, world, pos, (Player) entity);
            }
        }
        return super.mineBlock(stack, world, state, pos, entity);
    }

    @Override
    public int getBurnTime(ItemStack itemStack, RecipeType<?> recipeType) {
        return 0;
    }

    @Override
    public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
        return true;
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        super.appendHoverText(stack, worldIn, tooltip, flagIn);
        tooltip.add((Component.translatable("hover_text.cuboidmod.smasher").withStyle(ChatFormatting.GREEN)));
    }
}