package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Made from smelting a raw kebab item
 * 5% chance of poison for 2 seconds
 * 5% chance of hunger for 10 seconds
 * 5% chance of saturation for 5 seconds
 * 2.5% chance of absorption for 10 seconds
 */
public class CookedKebabItem extends Item {
    public static final String ID_STRING = "kebab_cooked";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public CookedKebabItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(1)
                .food(new Food.Builder()
                        .nutrition(10)
                        .saturationMod(2.0F)
                        .effect(() -> new EffectInstance(Effects.POISON, 2 * 20, 0), 0.05F)
                        .effect(() -> new EffectInstance(Effects.HUNGER, 10 * 20, 0), 0.05F)
                        .effect(() -> new EffectInstance(Effects.SATURATION, 5 * 20, 0), 0.05F)
                        .effect(() -> new EffectInstance(Effects.ABSORPTION, 10 * 20, 0), 0.025F)
                        .meat()
                        .build()));
    }

    /**
     * Return a Notsogudium Rod when the kebab is consumed.
     *
     * @param itemStack the kebab being consumed
     * @param level     the world
     * @param entity    the entity consuming the broth
     * @return the empty notsogudium bowl
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(itemStack, level, entity);
        return entity instanceof PlayerEntity && ((PlayerEntity) entity).abilities.instabuild ? itemstack : new ItemStack(ModItems.NOTSOGUDIUM_ROD.get());
    }
}
