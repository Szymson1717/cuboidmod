package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

import java.util.function.Consumer;

public class FoodItemDataGenRecipes extends DataGenRecipesBase {
    public static void build(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        buildSmeltingRecipe(provider, consumer, "arachnugget_from_spider_eye", Items.SPIDER_EYE, ModItems.ARACHNUGGET.get());
        buildSmeltingRecipe(provider, consumer, "cooked_kebab_from_raw_kebab", ModItems.KEBAB_RAW.get(), ModItems.KEBAB_COOKED.get());
        buildSmeltingRecipe(provider, consumer, "protein_bar_from_protein_paste", ModItems.PROTEIN_PASTE.get(), ModItems.PROTEIN_BAR.get());

        buildRawKebabRecipe(provider, consumer);
        buildNotsogudiumBowlRecipe(provider, consumer);
        buildBrothRecipe(provider, consumer);
        buildGruelRecipe(provider, consumer);
        buildCuringRecipe(provider, consumer, "cured_flesh_from_acv_and_rotten_flesh", Items.ROTTEN_FLESH, ModItems.CURED_FLESH.get());
        buildCuringRecipe(provider, consumer, "cured_beef_from_acv_and_raw_beef", Items.BEEF, ModItems.CURED_BEEF.get());
    }

    private static void buildCuringRecipe(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer, String recipeName, Item uncuredItem, Item curedItem) {
        ShapelessRecipeBuilder.shapeless(curedItem, 1)
                .requires(uncuredItem)
                .requires(ModItems.APPLE_CIDER_VINEGAR.get())
                .requires(ModTags.Items.DUSTS_SALT)
                .unlockedBy("has_item", provider.hasItem(ModItems.APPLE_CIDER_VINEGAR.get()))
                .save(consumer, modId(recipeName));
    }

    private static void buildRawKebabRecipe(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.KEBAB_RAW.get(), 1)
                .requires(ModTags.Items.RODS)
                .requires(Items.ROTTEN_FLESH)
                .requires(ModItems.PROTEIN_PASTE.get())
                .requires(Items.SPIDER_EYE)
                .unlockedBy("has_item", provider.hasItem(ModItems.PROTEIN_PASTE.get()))
                .save(consumer, modId("raw_kebab"));
    }

    private static void buildNotsogudiumBowlRecipe(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ModItems.NOTSOGUDIUM_BOWL.get(), 3)
                .pattern("# #")
                .pattern(" # ")
                .define('#', ModItems.NOTSOGUDIUM_INGOT.get())
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_INGOT.get()))
                .save(consumer, modId("notsogudium_bowls_from_ingots"));
    }

    private static void buildBrothRecipe(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.BROTH.get(), 1)
                .requires(Ingredient.of(ModItems.NOTSOGUDIUM_BOWL.get()), 1)
                .requires(Ingredient.of(Items.BONE_MEAL), 1)
                .requires(Ingredient.of(Items.WATER_BUCKET), 1)
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_BOWL.get()))
                .save(consumer, modId("broth_from_water_bucket_and_bone_meal"));

        ShapelessRecipeBuilder.shapeless(ModItems.BROTH.get(), 1)
                .requires(Ingredient.of(ModItems.NOTSOGUDIUM_BOWL.get()), 1)
                .requires(Ingredient.of(Items.BONE_MEAL), 1)
                .requires(Ingredient.of(Items.POTION), 1)
                .unlockedBy("has_item", provider.hasItem(ModItems.NOTSOGUDIUM_BOWL.get()))
                .save(consumer, modId("broth_from_water_bottle_and_bone_meal"));
    }

    private static void buildGruelRecipe(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModItems.GRUEL.get(), 1)
                .requires(Ingredient.of(ModItems.BROTH.get()))
                .requires(Ingredient.of(ModItems.PROTEIN_PASTE.get()))
                .unlockedBy("has_item", provider.hasItem(ModItems.BROTH.get()))
                .save(consumer, modId("gruel_from_broth_and_protein_paste"));
    }

    private static void buildSmeltingRecipe(
            ModRecipeProvider provider,
            Consumer<IFinishedRecipe> consumer,
            String name,
            Item ingredient,
            Item output) {
        CookingRecipeBuilder
                .smelting(Ingredient.of(ingredient), output, 0.0F, 200)
                .unlockedBy("has_item", provider.hasItem(ingredient))
                .save(consumer, modId(name + "_smelting"));
    }
}
