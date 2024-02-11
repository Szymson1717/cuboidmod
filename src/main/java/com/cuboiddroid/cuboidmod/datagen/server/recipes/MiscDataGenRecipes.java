package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class MiscDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        buildHopperRecipe(provider, consumer);
        buildStringRecipe(provider, consumer);
        buildOrganicallyEnrichedSandRecipe(provider, consumer);
        buildBucketRecipe(provider, consumer);
        buildTorchAndLanternRecipes(provider, consumer);
    }

    private static void buildBucketRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        // unfortunately can't use tags it seems or the recycler doesn't pick these over the vanilla recipe
        Item[] ingots = new Item[]{
                ModItems.NOTSOGUDIUM_INGOT.get(),
                ModItems.KUDBEBEDDA_INGOT.get(),
                ModItems.NOTARFBADIUM_INGOT.get(),
                ModItems.WIKIDIUM_INGOT.get(),
                ModItems.THATLDU_INGOT.get()
        };

        String[] names = new String[]{"notsogudium", "kudbebedda", "notarfbadium", "wikidium", "thatldu"};

        for (int index = 0; index < ingots.length; index++) {
            // note - we use 5 ingots so if a bucket is recycled, you don't get back iron!
            ShapedRecipeBuilder.shaped(Items.BUCKET)
                    .define('#', ingots[index])
                    .pattern("# #")
                    .pattern("# #")
                    .pattern(" # ")
                    .unlockedBy("has_item", provider.hasItem(ingots[index]))
                    .save(consumer, modId("bucket_from_" + names[index] + "_ingots"));
        }
    }

    private static void buildStringRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Items.STRING, 4)
                .define('#', ModTags.Items.CARBON_NANOTUBE)
                .define('/', ModTags.Items.PROTEIN_FIBER)
                .pattern("#/")
                .pattern("/#")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.PROTEIN_FIBER))
                .save(consumer, modId("string_from_nanotubes_and_protein_fiber"));
    }

    private static void buildHopperRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Items.HOPPER)
                .define('#', ModTags.Items.INGOTS)
                .define('$', ModTags.Items.CHESTS)
                .pattern("# #")
                .pattern("#$#")
                .pattern(" # ")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.CHESTS))
                .save(consumer, modId("hopper_from_ingots_and_chest"));
    }

    private static void buildTorchAndLanternRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Items.TORCH, 8)
                .define('#', Items.COAL)
                .define('|', ModTags.Items.RODS)
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.INGOTS))
                .save(consumer, modId("torches_from_coal_and_rod"));

        ShapedRecipeBuilder.shaped(Items.TORCH, 8)
                .define('#', Items.CHARCOAL)
                .define('|', ModTags.Items.RODS)
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.INGOTS))
                .save(consumer, modId("torches_from_charcoal_and_rod"));

        ShapedRecipeBuilder.shaped(Items.LANTERN, 1)
                .define('-', ModTags.Items.NUGGETS)
                .define('|', Items.TORCH)
                .pattern("---")
                .pattern("-|-")
                .pattern("---")
                .unlockedBy("has_item", provider.hasItem(Items.TORCH))
                .save(consumer, modId("lantern_from_nuggets_and_torch"));
    }

    private static void buildOrganicallyEnrichedSandRecipe(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapelessRecipeBuilder.shapeless(ModBlocks.ORGANICALLY_ENRICHED_SAND.get())
                .requires(Items.SAND)
                .requires(Items.BONE_MEAL)
                .requires(ModItems.CELLULOSE.get(), 2)
                .requires(ModItems.CARBON_DEPOSIT.get())
                .requires(ModItems.PROTEIN_PASTE.get())
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.CELLULOSE))
                .save(consumer, modId("organically_enriched_sand_from_sand_and_stuff"));
    }
}
