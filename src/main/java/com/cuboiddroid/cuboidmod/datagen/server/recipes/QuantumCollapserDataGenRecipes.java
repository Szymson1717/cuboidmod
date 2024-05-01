package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.modules.collapser.block.QuantumCollapserBlockBase;
import com.cuboiddroid.cuboidmod.modules.furnace.block.CuboidFurnaceBlockBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

public class QuantumCollapserDataGenRecipes extends DataGenRecipesBase {
    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        quantumCollapserFromBlocksAndFurnace(provider, consumer,
                ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.NOTSOGUDIUM_BLOCK.get(),
                ModBlocks.NOTSOGUDIUM_FURNACE.get(),
                "notsogudium");

        quantumCollapserFromBlocksAndFurnace(provider, consumer,
                ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                "kudbebedda");

        quantumCollapserFromBlocksAndFurnace(provider, consumer,
                ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                "notarfbadium");

        quantumCollapserFromBlocksAndFurnace(provider, consumer,
                ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                "wikidium");

        quantumCollapserFromBlocksAndFurnace(provider, consumer,
                ModBlocks.THATLDU_QUANTUM_COLLAPSER.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                "thatldu");

        // upgrades
        quantumCollapserUpgrade(provider, consumer,
                ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get(),
                ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                "kudbebedda");

        quantumCollapserUpgrade(provider, consumer,
                ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                "notarfbadium");

        quantumCollapserUpgrade(provider, consumer,
                ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                "wikidium");

        quantumCollapserUpgrade(provider, consumer,
                ModBlocks.THATLDU_QUANTUM_COLLAPSER.get(),
                ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                "thatldu");
    }

    private static void quantumCollapserFromBlocksAndFurnace(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, QuantumCollapserBlockBase output, Block block, CuboidFurnaceBlockBase furnace, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .pattern("###")
                .pattern("#$#")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_quantum_collapser_from_blocks_and_furnace"));
    }

    private static void quantumCollapserUpgrade(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, QuantumCollapserBlockBase output, QuantumCollapserBlockBase prevTier, Block block, CuboidFurnaceBlockBase furnace, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .define('@', prevTier)
                .pattern(" $ ")
                .pattern("#@#")
                .pattern(" # ")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_quantum_collapser_upgrade"));
    }

}
