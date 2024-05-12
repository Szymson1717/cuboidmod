package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;

import java.util.function.Consumer;

public class SmoosherDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.SMOOSHER.get())
                .define('#', ModTags.Items.STORAGE_BLOCKS)
                .define('-', ModTags.Items.INGOTS)
                .define('/', ModTags.Items.RODS)
                .pattern(" #-")
                .pattern(" /#")
                .pattern("/  ")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.INGOTS))
                .save(consumer, modId("smoosher_from_blocks_ingot_and_rods"));

        addSmoosherRecipe(provider, consumer, ModItems.NOTSOGUDIUM_DUST.get(), ModItems.NOTSOGUDIUM_CHUNK.get(), "notsogudium_dust_with_smoosher_and_chunk");
        addSmoosherRecipe(provider, consumer, ModItems.NOTSOGUDIUM_DUST.get(), ModBlocks.NOTSOGUDIUM_ORE.get().asItem(), "notsogudium_dust_with_smoosher_and_ore");
        addSmoosherRecipe(provider, consumer, ModItems.NOTSOGUDIUM_DUST.get(), ModItems.NOTSOGUDIUM_INGOT.get().asItem(), "notsogudium_dust_with_smoosher_and_ingot", 1);

        addSmoosherRecipe(provider, consumer, ModItems.KUDBEBEDDA_DUST.get(), ModItems.KUDBEBEDDA_CHUNK.get(), "kudbebedda_dust_with_smoosher_and_chunk");
        addSmoosherRecipe(provider, consumer, ModItems.KUDBEBEDDA_DUST.get(), ModBlocks.KUDBEBEDDA_ORE.get().asItem(), "kudbebedda_dust_with_smoosher_and_ore");
        addSmoosherRecipe(provider, consumer, ModItems.KUDBEBEDDA_DUST.get(), ModItems.KUDBEBEDDA_INGOT.get().asItem(), "kudbebedda_dust_with_smoosher_and_ingot", 1);

        addSmoosherRecipe(provider, consumer, ModItems.NOTARFBADIUM_DUST.get(), ModItems.NOTARFBADIUM_CHUNK.get(), "notarfbadium_dust_with_smoosher_and_chunk");
        addSmoosherRecipe(provider, consumer, ModItems.NOTARFBADIUM_DUST.get(), ModBlocks.NOTARFBADIUM_ORE.get().asItem(), "notarfbadium_dust_with_smoosher_and_ore");
        addSmoosherRecipe(provider, consumer, ModItems.NOTARFBADIUM_DUST.get(), ModItems.NOTARFBADIUM_INGOT.get().asItem(), "notarfbadium_dust_with_smoosher_and_ingot", 1);

        addSmoosherRecipe(provider, consumer, ModItems.WIKIDIUM_DUST.get(), ModItems.WIKIDIUM_CHUNK.get(), "wikidium_dust_with_smoosher_and_chunk");
        addSmoosherRecipe(provider, consumer, ModItems.WIKIDIUM_DUST.get(), ModBlocks.WIKIDIUM_ORE.get().asItem(), "wikidium_dust_with_smoosher_and_ore");
        addSmoosherRecipe(provider, consumer, ModItems.WIKIDIUM_DUST.get(), ModItems.WIKIDIUM_INGOT.get().asItem(), "wikidium_dust_with_smoosher_and_ingot", 1);

        addSmoosherRecipe(provider, consumer, ModItems.THATLDU_DUST.get(), ModItems.THATLDU_CHUNK.get(), "thatldu_dust_with_smoosher_and_chunk");
        addSmoosherRecipe(provider, consumer, ModItems.THATLDU_DUST.get(), ModBlocks.THATLDU_ORE.get().asItem(), "thatldu_dust_with_smoosher_and_ore");
        addSmoosherRecipe(provider, consumer, ModItems.THATLDU_DUST.get(), ModItems.THATLDU_INGOT.get().asItem(), "thatldu_dust_with_smoosher_and_ingot", 1);
    }

    private static void addSmoosherRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item outputItem, Item inputItem, String recipeId) {
        addSmoosherRecipe(provider, consumer, outputItem, inputItem, recipeId, 2);
    }

    private static void addSmoosherRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, Item outputItem, Item inputItem, String recipeId, int amount) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, outputItem, amount)
                .requires(ModItems.SMOOSHER.get())
                .requires(inputItem)
                .unlockedBy("has_item", provider.hasItem(ModItems.SMOOSHER.get()))
                .save(consumer, modId(recipeId));
    }


}
