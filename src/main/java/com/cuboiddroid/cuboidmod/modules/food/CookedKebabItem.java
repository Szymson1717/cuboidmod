package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

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
                
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(10)
                        .saturationMod(2.0F)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 2 * 20, 0), 0.05F)
                        .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 10 * 20, 0), 0.05F)
                        .effect(() -> new MobEffectInstance(MobEffects.SATURATION, 5 * 20, 0), 0.05F)
                        .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 10 * 20, 0), 0.025F)
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
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(itemStack, level, entity);
        return entity instanceof Player && ((Player) entity).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.NOTSOGUDIUM_ROD.get());
    }
}
