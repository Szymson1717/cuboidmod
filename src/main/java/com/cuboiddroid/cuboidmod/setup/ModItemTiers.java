package com.cuboiddroid.cuboidmod.setup;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;

import java.util.List;

import com.cuboiddroid.cuboidmod.CuboidMod;

public class ModItemTiers {

    public static final Tier NOTSOGUDIUM = TierSortingRegistry.registerTier(
        new ForgeTier(1, 65, 2.0F, 0.0F, 8, ModTags.Blocks.NEEDS_NOTSOGUDIUM_TOOL, () -> Ingredient.of(ModItems.NOTSOGUDIUM_INGOT.get())),
        new ResourceLocation(CuboidMod.MOD_ID, "notsogudium_tier"), List.of(Tiers.WOOD), List.of(Tiers.IRON)
    );

    public static final Tier KUDBEBEDDA = TierSortingRegistry.registerTier(
        new ForgeTier(2, 95, 4.0F, 1.0F, 10, ModTags.Blocks.NEEDS_KUDBEBEDDA_TOOL, () -> Ingredient.of(ModItems.KUDBEBEDDA_INGOT.get())),
        new ResourceLocation(CuboidMod.MOD_ID, "kudbebedda_tier"), List.of(Tiers.STONE), List.of(Tiers.DIAMOND)
    );

    public static final Tier NOTARFBADIUM = TierSortingRegistry.registerTier(
        new ForgeTier(3, 180, 6.0F, 2.0F, 12, ModTags.Blocks.NEEDS_NOTARFBADIUM_TOOL, () -> Ingredient.of(ModItems.NOTARFBADIUM_INGOT.get())),
        new ResourceLocation(CuboidMod.MOD_ID, "notarfbadium_tier"), List.of(Tiers.IRON), List.of(Tiers.NETHERITE)
    );

    public static final Tier WIKIDIUM = TierSortingRegistry.registerTier(
        new ForgeTier(4, 360, 8.0F, 3.5F, 14, ModTags.Blocks.NEEDS_WIKIDIUM_TOOL, () -> Ingredient.of(ModItems.WIKIDIUM_INGOT.get())),
        new ResourceLocation(CuboidMod.MOD_ID, "wikidium_tier"), List.of(Tiers.DIAMOND), List.of()
    );

    public static final Tier THATLDU = TierSortingRegistry.registerTier(
        new ForgeTier(5, 1080, 10.0F, 5.0F, 15, ModTags.Blocks.NEEDS_THATLDU_TOOL, () -> Ingredient.of(ModItems.THATLDU_INGOT.get())),
        new ResourceLocation(CuboidMod.MOD_ID, "thatldu_tier"), List.of(WIKIDIUM), List.of()
    );

    static void register() {}

}
