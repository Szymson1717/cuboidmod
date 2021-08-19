package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class FurnaceDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        // vanilla furnace from chunks and silica dust
        buildFurnaceRecipes(provider, consumer);

        // upgrade furnace recipes to next tier with 4 ingots
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.NOTSOGUDIUM_FURNACE.get(), Blocks.FURNACE, ModItems.NOTSOGUDIUM_INGOT.get(), "notsogudium");
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.NOTSOGUDIUM_FURNACE.get(), ModBlocks.EUMUS_FURNACE.get(), ModItems.NOTSOGUDIUM_INGOT.get(), "notsogudium_eumus");
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.KUDBEBEDDA_FURNACE.get(), ModBlocks.NOTSOGUDIUM_FURNACE.get(), ModItems.KUDBEBEDDA_INGOT.get(), "kudbebedda");
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.NOTARFBADIUM_FURNACE.get(), ModBlocks.KUDBEBEDDA_FURNACE.get(), ModItems.NOTARFBADIUM_INGOT.get(), "notarfbadium");
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.WIKIDIUM_FURNACE.get(), ModBlocks.NOTARFBADIUM_FURNACE.get(), ModItems.WIKIDIUM_INGOT.get(), "wikidium");
        cuboidFurnaceFromPreviousAndIngots(provider, consumer, ModBlocks.THATLDU_FURNACE.get(), ModBlocks.WIKIDIUM_FURNACE.get(), ModItems.THATLDU_INGOT.get(), "thatldu");

        // upgrade from earlier furnace(s) with 8 ingots
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.KUDBEBEDDA_FURNACE.get(), Blocks.FURNACE, ModItems.KUDBEBEDDA_INGOT.get(), "kudbebedda", "vanilla");

        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.NOTARFBADIUM_FURNACE.get(), Blocks.FURNACE, ModItems.NOTARFBADIUM_INGOT.get(), "notarfbadium", "vanilla");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.NOTARFBADIUM_FURNACE.get(), ModBlocks.NOTSOGUDIUM_FURNACE.get(), ModItems.NOTARFBADIUM_INGOT.get(), "notarfbadium", "notsogudium");

        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.WIKIDIUM_FURNACE.get(), Blocks.FURNACE, ModItems.WIKIDIUM_INGOT.get(), "wikidium", "vanilla");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.WIKIDIUM_FURNACE.get(), ModBlocks.NOTSOGUDIUM_FURNACE.get(), ModItems.WIKIDIUM_INGOT.get(), "wikidium", "notsogudium");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.WIKIDIUM_FURNACE.get(), ModBlocks.KUDBEBEDDA_FURNACE.get(), ModItems.WIKIDIUM_INGOT.get(), "wikidium", "kudbebedda");

        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.THATLDU_FURNACE.get(), Blocks.FURNACE, ModItems.THATLDU_INGOT.get(), "thatldu", "vanilla");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.THATLDU_FURNACE.get(), ModBlocks.NOTSOGUDIUM_FURNACE.get(), ModItems.THATLDU_INGOT.get(), "thatldu", "notsogudium");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.THATLDU_FURNACE.get(), ModBlocks.KUDBEBEDDA_FURNACE.get(), ModItems.THATLDU_INGOT.get(), "thatldu", "kudbebedda");
        cuboidFurnaceFromEarlierAndIngots(provider, consumer, ModBlocks.THATLDU_FURNACE.get(), ModBlocks.NOTARFBADIUM_FURNACE.get(), ModItems.THATLDU_INGOT.get(), "thatldu", "notarfbadium");
    }

    private static void buildFurnaceRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Blocks.FURNACE)
                .define('#', ModTags.Items.ORES)
                .define('$', ModTags.Items.DUSTS_SILICA)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.ORES))
                .save(consumer, modId("furnace_from_chunks"));

        ShapedRecipeBuilder.shaped(Blocks.BLAST_FURNACE)
                .define('#', ModTags.Items.INGOTS)
                .define('$', Items.FURNACE)
                .define('@', ModTags.Items.STORAGE_BLOCKS)
                .pattern("###")
                .pattern("#$#")
                .pattern("@@@")
                .unlockedBy("has_item", provider.hasItem(ModTags.Items.INGOTS))
                .save(consumer, modId("blast_furnace_from_blocks"));
    }


    private static void cuboidFurnaceFromPreviousAndIngots(
            ModRecipeProvider provider,
            Consumer<IFinishedRecipe> consumer,
            Block furnaceBlock,
            Block prevFurnace,
            Item ingot,
            String materialName) {
        ShapedRecipeBuilder.shaped(furnaceBlock)
                .define('#', ingot)
                .define('$', prevFurnace)
                .pattern(" # ")
                .pattern("#$#")
                .pattern(" # ")
                .unlockedBy("has_item", provider.hasItem(prevFurnace))
                .save(consumer, modId(materialName + "_furnace_upgrade_with_four_ingots"));
    }

    private static void cuboidFurnaceFromEarlierAndIngots(
            ModRecipeProvider provider,
            Consumer<IFinishedRecipe> consumer,
            Block furnaceBlock,
            Block prevFurnace,
            Item ingot,
            String materialName,
            String prevName) {
        ShapedRecipeBuilder.shaped(furnaceBlock)
                .define('#', ingot)
                .define('$', prevFurnace)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(prevFurnace))
                .save(consumer, modId(materialName + "_furnace_upgrade_from_" + prevName + "_furnace"));
    }

}
