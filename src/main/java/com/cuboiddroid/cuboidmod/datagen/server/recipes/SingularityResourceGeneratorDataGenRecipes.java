package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.modules.furnace.block.CuboidFurnaceBlockBase;
import com.cuboiddroid.cuboidmod.modules.resourcegen.block.SingularityResourceGeneratorBlockBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;

import java.util.function.Consumer;

public class SingularityResourceGeneratorDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        srgFromBlocksFurnacesAndCraftingTable(provider, consumer,
                ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.NOTSOGUDIUM_BLOCK.get(),
                ModBlocks.NOTSOGUDIUM_FURNACE.get(),
                ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get(),
                "notsogudium");

        srgFromBlocksFurnacesAndCraftingTable(provider, consumer,
                ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get(),
                "kudbebedda");

        srgFromBlocksFurnacesAndCraftingTable(provider, consumer,
                ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get(),
                "notarfbadium");

        srgFromBlocksFurnacesAndCraftingTable(provider, consumer,
                ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                ModBlocks.WIKIDIUM_CRAFTING_TABLE.get(),
                "wikidium");

        srgFromBlocksFurnacesAndCraftingTable(provider, consumer,
                ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                ModBlocks.THATLDU_CRAFTING_TABLE.get(),
                "thatldu");

        // upgrades
        srgUpgrade(provider, consumer,
                ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get(),
                "kudbebedda");

        srgUpgrade(provider, consumer,
                ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get(),
                "notarfbadium");

        srgUpgrade(provider, consumer,
                ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                ModBlocks.WIKIDIUM_CRAFTING_TABLE.get(),
                "wikidium");

        srgUpgrade(provider, consumer,
                ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                ModBlocks.THATLDU_CRAFTING_TABLE.get(),
                "thatldu");
    }

    private static void srgFromBlocksFurnacesAndCraftingTable(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, SingularityResourceGeneratorBlockBase output, Block block, CuboidFurnaceBlockBase furnace, Block craftingTable, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .define('!', craftingTable)
                .pattern("###")
                .pattern("$!$")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_srg_from_blocks_furnace_and_crafting_table"));
    }

    private static void srgUpgrade(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, SingularityResourceGeneratorBlockBase output, SingularityResourceGeneratorBlockBase prevTier, Block block, CuboidFurnaceBlockBase furnace, Block craftingTable, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .define('!', craftingTable)
                .define('@', prevTier)
                .pattern(" $ ")
                .pattern("#@#")
                .pattern(" ! ")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_srg_upgrade"));
    }

}
