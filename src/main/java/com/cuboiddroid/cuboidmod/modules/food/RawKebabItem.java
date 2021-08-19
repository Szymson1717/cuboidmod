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
 * Made from a rod, rotten flesh, spider eye and protein paste
 * 60% chance of poison for 5 seconds (spider eye - 40%)
 * 40% chance of hunger for 30 seconds (rotten flesh - 40%)
 */
public class RawKebabItem extends Item {
    public static final String ID_STRING = "kebab_raw";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public RawKebabItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(1)
                .food(new Food.Builder()
                        .nutrition(8)
                        .saturationMod(0.6F)
                        .effect(() -> new EffectInstance(Effects.POISON, 5 * 20, 0), 0.8F)
                        .effect(() -> new EffectInstance(Effects.HUNGER, 30 * 20, 0), 0.6F)
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
