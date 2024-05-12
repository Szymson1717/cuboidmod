package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.modules.furnace.block.CuboidFurnaceBlockBase;
import com.cuboiddroid.cuboidmod.modules.powergen.block.SingularityPowerGeneratorBlockBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class SingularityPowerGeneratorDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        spgFromBlocksFurnacesAndRedstoneBlock(provider, consumer,
                ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.NOTSOGUDIUM_BLOCK.get(),
                ModBlocks.NOTSOGUDIUM_FURNACE.get(),
                "notsogudium");

        spgFromBlocksFurnacesAndRedstoneBlock(provider, consumer,
                ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                "kudbebedda");

        spgFromBlocksFurnacesAndRedstoneBlock(provider, consumer,
                ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                "notarfbadium");

        spgFromBlocksFurnacesAndRedstoneBlock(provider, consumer,
                ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                "wikidium");

        spgFromBlocksFurnacesAndRedstoneBlock(provider, consumer,
                ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                "thatldu");

        // upgrades
        spgUpgrade(provider, consumer,
                ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_BLOCK.get(),
                ModBlocks.KUDBEBEDDA_FURNACE.get(),
                "kudbebedda");

        spgUpgrade(provider, consumer,
                ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_BLOCK.get(),
                ModBlocks.NOTARFBADIUM_FURNACE.get(),
                "notarfbadium");

        spgUpgrade(provider, consumer,
                ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.WIKIDIUM_BLOCK.get(),
                ModBlocks.WIKIDIUM_FURNACE.get(),
                "wikidium");

        spgUpgrade(provider, consumer,
                ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(),
                ModBlocks.THATLDU_BLOCK.get(),
                ModBlocks.THATLDU_FURNACE.get(),
                "thatldu");
    }

    private static void spgFromBlocksFurnacesAndRedstoneBlock(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, SingularityPowerGeneratorBlockBase output, Block block, CuboidFurnaceBlockBase furnace, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .define('R', Items.REDSTONE_BLOCK)
                .pattern("#$#")
                .pattern("$R$")
                .pattern("#$#")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_spg_from_blocks_furnace_and_redstone_block"));
    }

    private static void spgUpgrade(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, SingularityPowerGeneratorBlockBase output, SingularityPowerGeneratorBlockBase prevTier, Block block, CuboidFurnaceBlockBase furnace, String materialName) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('#', block)
                .define('$', furnace)
                .define('@', prevTier)
                .pattern(" $ ")
                .pattern("#@#")
                .pattern(" $ ")
                .unlockedBy("has_item", provider.hasItem(furnace))
                .save(consumer, modId(materialName + "_spg_upgrade"));
    }

}
