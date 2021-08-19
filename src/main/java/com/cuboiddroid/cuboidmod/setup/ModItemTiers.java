package com.cuboiddroid.cuboidmod.setup;

import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum ModItemTiers implements IItemTier {

    NOTSOGUDIUM  (2, 65,    2.0F, 0.0F, 8,  () -> Ingredient.of(ModItems.NOTSOGUDIUM_INGOT.get())),
    KUDBEBEDDA   (2, 95,    4.0F, 1.0F, 10, () -> Ingredient.of(ModItems.KUDBEBEDDA_INGOT.get())),
    NOTARFBADIUM (2, 180,   6.0F, 2.0F, 12, () -> Ingredient.of(ModItems.NOTARFBADIUM_INGOT.get())),
    WIKIDIUM     (3, 360,   8.0F, 3.5F, 14, () -> Ingredient.of(ModItems.WIKIDIUM_INGOT.get())),
    THATLDU      (4, 1080, 10.0F, 5.0F, 15, () -> Ingredient.of(ModItems.THATLDU_INGOT.get()));

    private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamageBonus;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModItemTiers(int harvestLevel, int maxUses, float efficiency, float attackDamageBonus, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamageBonus = attackDamageBonus;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return this.maxUses;
    }

    @Override
    public float getSpeed() {
        return this.efficiency;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.attackDamageBonus;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }
}
