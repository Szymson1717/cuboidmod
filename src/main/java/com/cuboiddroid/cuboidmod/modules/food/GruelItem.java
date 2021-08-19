package com.cuboiddroid.cuboidmod.modules.food;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

/**
 * Made from bowl of broth and protein paste
 */
public class GruelItem extends Item {
    public static final String ID_STRING = "gruel";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public GruelItem() {
        super(new Properties()
                .tab(CuboidMod.CUBOIDMOD_ITEM_GROUP)
                .stacksTo(1)
                .food(new Food.Builder()
                        .nutrition(5)
                        .saturationMod(0.7F)
                        .build()));
    }

    /**
     * Return an empty Notsogudium Bowl when the gruel is consumed.
     *
     * @param itemStack the gruel being consumed
     * @param level     the world
     * @param entity    the entity consuming the broth
     * @return the empty notsogudium bowl
     */
    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, World level, LivingEntity entity) {
        ItemStack itemstack = super.finishUsingItem(itemStack, level, entity);
        return entity instanceof PlayerEntity && ((PlayerEntity) entity).abilities.instabuild ? itemstack : new ItemStack(ModItems.NOTSOGUDIUM_BOWL.get());
    }

}
