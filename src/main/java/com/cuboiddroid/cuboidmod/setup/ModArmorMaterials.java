package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

public enum ModArmorMaterials implements ArmorMaterial {
    NOTSOGUDIUM (CuboidMod.MOD_ID +":notsogudium_armor",
            5, new int[] { 1, 2, 3, 1 },
            8, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F,
            () -> Ingredient.of(ModItems.NOTSOGUDIUM_INGOT.get())),

    KUDBEBEDDA (CuboidMod.MOD_ID +":kudbebedda_armor",
            8, new int[] { 1, 3, 4, 2 },
            12, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F,
            () -> Ingredient.of(ModItems.KUDBEBEDDA_INGOT.get())),

    NOTARFBADIUM (CuboidMod.MOD_ID +":notarfbadium_armor",
            14, new int[] { 2, 5, 6, 2 },
            16, SoundEvents.ARMOR_EQUIP_GENERIC,
            0.0F, 0.0F,
            () -> Ingredient.of(ModItems.NOTARFBADIUM_INGOT.get())),

    WIKIDIUM (CuboidMod.MOD_ID +":wikidium_armor",
            24, new int[] { 3, 6, 7, 3 },
            20, SoundEvents.ARMOR_EQUIP_GENERIC,
            1.5F, 0.0F,
            () -> Ingredient.of(ModItems.WIKIDIUM_INGOT.get())),

    THATLDU (CuboidMod.MOD_ID +":thatldu_armor",
            39, new int[] { 4, 7, 8, 4 },
            34, SoundEvents.ARMOR_EQUIP_GENERIC,
            2.5F, 0.1F,
            () -> Ingredient.of(ModItems.THATLDU_INGOT.get()));

    private static final int[] HEALTH_PER_SLOT = new int[] { 13, 15, 16, 11 };
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final Supplier<Ingredient> repairIngredient;

    ModArmorMaterials(String name,
                      int durabilityMultiplier,
                      int[] slotProtections,
                      int enchantmentValue,
                      SoundEvent sound,
                      float toughness,
                      float knockbackResistance,
                      Supplier<Ingredient> repairIngredient) {
        this.name = name;
        this.durabilityMultiplier = durabilityMultiplier;
        this.slotProtections = slotProtections;
        this.enchantmentValue = enchantmentValue;
        this.sound = sound;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlot slotType) {
        return HEALTH_PER_SLOT[slotType.getIndex()] * this.durabilityMultiplier;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlot slotType) {
        return this.slotProtections[slotType.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
