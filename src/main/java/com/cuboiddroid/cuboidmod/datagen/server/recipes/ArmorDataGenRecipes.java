package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

public class ArmorDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        buildNotsogudiumRecipes(provider, consumer);
        buildKudbebeddaRecipes(provider, consumer);
        buildNotarfbadiumRecipes(provider, consumer);
        buildWikidiumRecipes(provider, consumer);
        buildThatlduRecipes(provider, consumer);
    }

    private static void buildNotsogudiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidHelmetFromIngots(provider, consumer, ModItems.NOTSOGUDIUM_HELMET, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidChestplateFromIngots(provider, consumer, ModItems.NOTSOGUDIUM_CHESTPLATE, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidLeggingsFromIngots(provider, consumer, ModItems.NOTSOGUDIUM_LEGGINGS, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidBootsFromIngots(provider, consumer, ModItems.NOTSOGUDIUM_BOOTS, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
    }

    private static void buildKudbebeddaRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidHelmetFromIngots(provider, consumer, ModItems.KUDBEBEDDA_HELMET, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidChestplateFromIngots(provider, consumer, ModItems.KUDBEBEDDA_CHESTPLATE, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidLeggingsFromIngots(provider, consumer, ModItems.KUDBEBEDDA_LEGGINGS, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidBootsFromIngots(provider, consumer, ModItems.KUDBEBEDDA_BOOTS, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
    }

    private static void buildNotarfbadiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidHelmetFromIngots(provider, consumer, ModItems.NOTARFBADIUM_HELMET, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidChestplateFromIngots(provider, consumer, ModItems.NOTARFBADIUM_CHESTPLATE, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidLeggingsFromIngots(provider, consumer, ModItems.NOTARFBADIUM_LEGGINGS, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidBootsFromIngots(provider, consumer, ModItems.NOTARFBADIUM_BOOTS, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
    }

    private static void buildWikidiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidHelmetFromIngots(provider, consumer, ModItems.WIKIDIUM_HELMET, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidChestplateFromIngots(provider, consumer, ModItems.WIKIDIUM_CHESTPLATE, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidLeggingsFromIngots(provider, consumer, ModItems.WIKIDIUM_LEGGINGS, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidBootsFromIngots(provider, consumer, ModItems.WIKIDIUM_BOOTS, ModItems.WIKIDIUM_INGOT, "wikidium");
    }

    private static void buildThatlduRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.THATLDU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ModItems.WIKIDIUM_HELMET.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), RecipeCategory.MISC, ModItems.THATLDU_HELMET.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_helmet_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.THATLDU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ModItems.WIKIDIUM_CHESTPLATE.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), RecipeCategory.MISC, ModItems.THATLDU_CHESTPLATE.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_chestplate_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.THATLDU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ModItems.WIKIDIUM_LEGGINGS.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), RecipeCategory.MISC, ModItems.THATLDU_LEGGINGS.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_leggings_smithing"));

        SmithingTransformRecipeBuilder.smithing(Ingredient.of(ModItems.THATLDU_UPGRADE_SMITHING_TEMPLATE.get()), Ingredient.of(ModItems.WIKIDIUM_BOOTS.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), RecipeCategory.MISC, ModItems.THATLDU_BOOTS.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_boots_smithing"));
    }

    // -------------------------------------------------------------------

    private static void craftCuboidHelmetFromIngots(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<ArmorItem> output, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output.get())
                .define('#', ingot.get())
                .pattern("###")
                .pattern("# #")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_helmet_from_ingots"));
    }

    private static void craftCuboidChestplateFromIngots(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<ArmorItem> output, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output.get())
                .define('#', ingot.get())
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_chestplate_from_ingots"));
    }

    private static void craftCuboidLeggingsFromIngots(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<ArmorItem> output, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output.get())
                .define('#', ingot.get())
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_leggings_from_ingots"));
    }

    private static void craftCuboidBootsFromIngots(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<ArmorItem> output, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output.get())
                .define('#', ingot.get())
                .pattern("# #")
                .pattern("# #")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_boots_from_ingots"));
    }

}
