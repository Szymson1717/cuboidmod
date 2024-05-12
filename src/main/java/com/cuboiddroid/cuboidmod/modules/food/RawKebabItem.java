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
 * Made from a rod, rotten flesh, spider eye and protein paste
 * 60% chance of poison for 5 seconds (spider eye - 40%)
 * 40% chance of hunger for 30 seconds (rotten flesh - 40%)
 */
public class RawKebabItem extends Item {
    public static final String ID_STRING = "kebab_raw";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public RawKebabItem() {
        super(new Properties()
                
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(8)
                        .saturationMod(0.6F)
                        .effect(() -> new MobEffectInstance(MobEffects.POISON, 5 * 20, 0), 0.8F)
                        .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 30 * 20, 0), 0.6F)
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
