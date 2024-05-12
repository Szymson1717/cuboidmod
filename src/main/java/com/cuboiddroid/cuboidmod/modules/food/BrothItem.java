package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;

/**
 * Made from Notsogudium Bowls, Bucket of Water and Bone Meal
 */
public class BrothItem extends Item {
    public static final String ID_STRING = "broth";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public BrothItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(1)
                .food(new FoodProperties.Builder()
                        .nutrition(3)
                        .saturationMod(0.3F)
                        .build()));
    }

    /**
     * Return an empty Notsogudium Bowl when the broth is consumed.
     *
     * @param itemStack the broth being consumed
     * @param level     the world
     * @param entity    the entity consuming the broth
     * @return the empty notsogudium bowl
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(itemStack, level, entity);

        return entity instanceof Player && ((Player) entity).getAbilities().instabuild ? itemstack : new ItemStack(ModItems.NOTSOGUDIUM_BOWL.get());
    }
}
