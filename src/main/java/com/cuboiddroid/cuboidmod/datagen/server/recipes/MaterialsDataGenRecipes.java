package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Items;

import java.util.function.Consumer;

public class MaterialsDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        buildNotsogudiumRecipes(provider, consumer);
        buildKudbebeddaRecipes(provider, consumer);
        buildNotarfbadiumRecipes(provider, consumer);
        buildWikidiumRecipes(provider, consumer);
        buildThatlduRecipes(provider, consumer);

        buildCarbonDepositRecipes(provider, consumer);
        buildSilicaDustRecipes(provider, consumer);
        buildCarbonNanotubeRecipes(provider, consumer);
        buildStickBundleRecipes(provider, consumer);
        buildProteinPasteRecipes(provider, consumer);
        buildProteinFiberRecipes(provider, consumer);
        buildCelluloseRecipes(provider, consumer);
    }

    private static void buildNotsogudiumRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemsFromBlock(provider, consumer, ModItems.NOTSOGUDIUM_INGOT, 9, ModBlocks.NOTSOGUDIUM_BLOCK, "notsogudium_ingots_from_block");
        craftBlock3x3(provider, consumer, ModBlocks.NOTSOGUDIUM_BLOCK, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_block_from_ingots");

        craftItemsFromItem(provider, consumer, ModItems.NOTSOGUDIUM_NUGGET, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_nuggets_from_ingot", 9);
        craftItemFromItems3x3(provider, consumer, ModItems.NOTSOGUDIUM_INGOT, ModItems.NOTSOGUDIUM_NUGGET, "notsogudium_ingot_from_nuggets");

        craftItemFromItems2x2(provider, consumer, ModItems.NOTSOGUDIUM_CHUNK, ModItems.NOTSOGUDIUM_PIECE, "notsogudium_chunk_from_pieces");

        craftItemFromItems1x2(provider, consumer, ModItems.NOTSOGUDIUM_ROD, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_rod_from_ingots");

        smeltBlockToItem(provider, consumer, ModBlocks.NOTSOGUDIUM_ORE, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_ore_ingot");
        smeltItemToItem(provider, consumer, ModItems.NOTSOGUDIUM_CHUNK, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_chunk_ingot");
        smeltItemToItem(provider, consumer, ModItems.NOTSOGUDIUM_DUST, ModItems.NOTSOGUDIUM_INGOT, "notsogudium_dust_ingot");
    }

    private static void buildKudbebeddaRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemsFromBlock(provider, consumer, ModItems.KUDBEBEDDA_INGOT, 9, ModBlocks.KUDBEBEDDA_BLOCK, "kudbebedda_ingots_from_block");
        craftBlock3x3(provider, consumer, ModBlocks.KUDBEBEDDA_BLOCK, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_block_from_ingots");

        craftItemsFromItem(provider, consumer, ModItems.KUDBEBEDDA_NUGGET, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_nuggets_from_ingot", 9);
        craftItemFromItems3x3(provider, consumer, ModItems.KUDBEBEDDA_INGOT, ModItems.KUDBEBEDDA_NUGGET, "kudbebedda_ingot_from_nuggets");

        craftItemFromItems2x2(provider, consumer, ModItems.KUDBEBEDDA_CHUNK, ModItems.KUDBEBEDDA_PIECE, "kudbebedda_chunk_from_pieces");

        craftItemFromItems1x2(provider, consumer, ModItems.KUDBEBEDDA_ROD, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_rod_from_ingots");

        smeltBlockToItem(provider, consumer, ModBlocks.KUDBEBEDDA_ORE, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_ore_ingot");
        smeltItemToItem(provider, consumer, ModItems.KUDBEBEDDA_CHUNK, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_chunk_ingot");
        smeltItemToItem(provider, consumer, ModItems.KUDBEBEDDA_DUST, ModItems.KUDBEBEDDA_INGOT, "kudbebedda_dust_ingot");
    }

    private static void buildNotarfbadiumRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemsFromBlock(provider, consumer, ModItems.NOTARFBADIUM_INGOT, 9, ModBlocks.NOTARFBADIUM_BLOCK, "notarfbadium_ingots_from_block");
        craftBlock3x3(provider, consumer, ModBlocks.NOTARFBADIUM_BLOCK, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_block_from_ingots");

        craftItemsFromItem(provider, consumer, ModItems.NOTARFBADIUM_NUGGET, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_nuggets_from_ingot", 9);
        craftItemFromItems3x3(provider, consumer, ModItems.NOTARFBADIUM_INGOT, ModItems.NOTARFBADIUM_NUGGET, "notarfbadium_ingot_from_nuggets");

        craftItemFromItems2x2(provider, consumer, ModItems.NOTARFBADIUM_CHUNK, ModItems.NOTARFBADIUM_PIECE, "notarfbadium_chunk_from_pieces");

        craftItemFromItems1x2(provider, consumer, ModItems.NOTARFBADIUM_ROD, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_rod_from_ingots");

        smeltBlockToItem(provider, consumer, ModBlocks.NOTARFBADIUM_ORE, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_ore_ingot");
        smeltItemToItem(provider, consumer, ModItems.NOTARFBADIUM_CHUNK, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_chunk_ingot");
        smeltItemToItem(provider, consumer, ModItems.NOTARFBADIUM_DUST, ModItems.NOTARFBADIUM_INGOT, "notarfbadium_dust_ingot");
    }

    private static void buildWikidiumRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemsFromBlock(provider, consumer, ModItems.WIKIDIUM_INGOT, 9, ModBlocks.WIKIDIUM_BLOCK, "wikidium_ingots_from_block");
        craftBlock3x3(provider, consumer, ModBlocks.WIKIDIUM_BLOCK, ModItems.WIKIDIUM_INGOT, "wikidium_block_from_ingots");

        craftItemsFromItem(provider, consumer, ModItems.WIKIDIUM_NUGGET, ModItems.WIKIDIUM_INGOT, "wikidium_nuggets_from_ingot", 9);
        craftItemFromItems3x3(provider, consumer, ModItems.WIKIDIUM_INGOT, ModItems.WIKIDIUM_NUGGET, "wikidium_ingot_from_nuggets");

        craftItemFromItems2x2(provider, consumer, ModItems.WIKIDIUM_CHUNK, ModItems.WIKIDIUM_PIECE, "wikidium_chunk_from_pieces");

        craftItemFromItems1x2(provider, consumer, ModItems.WIKIDIUM_ROD, ModItems.WIKIDIUM_INGOT, "wikidium_rod_from_ingots");

        smeltBlockToItem(provider, consumer, ModBlocks.WIKIDIUM_ORE, ModItems.WIKIDIUM_INGOT, "wikidium_ore_ingot");
        smeltItemToItem(provider, consumer, ModItems.WIKIDIUM_CHUNK, ModItems.WIKIDIUM_INGOT, "wikidium_chunk_ingot");
        smeltItemToItem(provider, consumer, ModItems.WIKIDIUM_DUST, ModItems.WIKIDIUM_INGOT, "wikidium_dust_ingot");
    }

    private static void buildThatlduRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemsFromBlock(provider, consumer, ModItems.THATLDU_INGOT, 9, ModBlocks.THATLDU_BLOCK, "thatldu_ingots_from_block");
        craftBlock3x3(provider, consumer, ModBlocks.THATLDU_BLOCK, ModItems.THATLDU_INGOT, "thatldu_block_from_ingots");

        craftItemsFromItem(provider, consumer, ModItems.THATLDU_NUGGET, ModItems.THATLDU_INGOT, "thatldu_nuggets_from_ingot", 9);
        craftItemFromItems3x3(provider, consumer, ModItems.THATLDU_INGOT, ModItems.THATLDU_NUGGET, "thatldu_ingot_from_nuggets");

        craftItemFromItems2x2(provider, consumer, ModItems.THATLDU_CHUNK, ModItems.THATLDU_PIECE, "thatldu_chunk_from_pieces");

        craftItemFromItems1x2(provider, consumer, ModItems.THATLDU_ROD, ModItems.THATLDU_INGOT, "thatldu_rod_from_ingots");

        smeltBlockToItem(provider, consumer, ModBlocks.THATLDU_ORE, ModItems.THATLDU_INGOT, "thatldu_ore_ingot");
        smeltItemToItem(provider, consumer, ModItems.THATLDU_CHUNK, ModItems.THATLDU_INGOT, "thatldu_chunk_ingot");
        smeltItemToItem(provider, consumer, ModItems.THATLDU_DUST, ModItems.THATLDU_INGOT, "thatldu_dust_ingot");
    }

    private static void buildCarbonDepositRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemFromItems2x2(provider, consumer, Items.COAL, ModItems.CARBON_DEPOSIT.get(), "coal_from_carbon_deposits");
    }

    private static void buildSilicaDustRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftBlock3x3(provider, consumer, ModBlocks.SILICA_DUST_BLOCK, ModItems.SILICA_DUST, "silica_dust_block_from_silica_dust");
        craftItemsFromBlock(provider, consumer, ModItems.SILICA_DUST, 9, ModBlocks.SILICA_DUST_BLOCK, "silica_dust_from_silica_dust_block");
        smeltBlockToItem(provider, consumer, ModBlocks.SILICA_DUST_BLOCK.get(), Items.GLASS, "smelt_glass_from_silica_dust_block");
    }

    private static void buildCarbonNanotubeRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftBlock3x3(provider, consumer, ModBlocks.CARBON_NANOTUBE_BLOCK, ModItems.CARBON_NANOTUBE, "carbon_nanotube_block_from_carbon_nanotubes");
        craftItemsFromBlock(provider, consumer, ModItems.CARBON_NANOTUBE, 9, ModBlocks.CARBON_NANOTUBE_BLOCK, "carbon_nanotubes_from_carbon_nanotube_block");
    }

    private static void buildStickBundleRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftItemFromItems3x3(provider, consumer, ModItems.STICK_BUNDLE, Items.STICK, "stick_bundle_from_sticks");
        craftItemsFromItem(provider, consumer, Items.STICK, ModItems.STICK_BUNDLE, "sticks_from_stick_bundle", 9);
    }

    private static void buildProteinPasteRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftBlock3x3(provider, consumer, ModBlocks.PROTEIN_PASTE_BLOCK, ModItems.PROTEIN_PASTE, "protein_paste_block_from_protein_paste");
        craftItemsFromBlock(provider, consumer, ModItems.PROTEIN_PASTE, 9, ModBlocks.PROTEIN_PASTE_BLOCK, "protein_paste_from_protein_paste_block");
    }

    private static void buildProteinFiberRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftBlock3x3(provider, consumer, ModBlocks.PROTEIN_FIBER_BLOCK, ModItems.PROTEIN_FIBER, "protein_fiber_block_from_protein_fiber");
        craftItemsFromBlock(provider, consumer, ModItems.PROTEIN_FIBER, 9, ModBlocks.PROTEIN_FIBER_BLOCK, "protein_fiber_from_protein_fiber_block");
    }

    private static void buildCelluloseRecipes(ModRecipeProvider provider, Consumer<IFinishedRecipe> consumer) {
        craftBlock3x3(provider, consumer, ModBlocks.CELLULOSE_BLOCK, ModItems.CELLULOSE, "cellulose_block_from_cellulose");
        craftItemsFromBlock(provider, consumer, ModItems.CELLULOSE, 9, ModBlocks.CELLULOSE_BLOCK, "cellulose_from_cellulose_block");

        craftBlock2x2(provider, consumer, ModBlocks.CELLULOSE_BRICKS.get(), ModBlocks.CELLULOSE_BLOCK.get(), "cellulose_bricks_from_blocks", 4);
        craftBlock2x2(provider, consumer, ModBlocks.CELLULOSE_CHISELED_BRICKS.get(), ModBlocks.CELLULOSE_BRICKS.get(), "cellulose_chiseled_bricks_from_bricks", 4);

        stonecutterBlockFromBlock(provider, consumer, ModBlocks.CELLULOSE_BRICKS.get(), ModBlocks.CELLULOSE_BLOCK.get(), "cellulose_bricks_from_block_sc");
        stonecutterBlockFromBlock(provider, consumer, ModBlocks.CELLULOSE_BRICKS.get(), ModBlocks.CELLULOSE_CHISELED_BRICKS.get(), "cellulose_bricks_from_chiseled_bricks_sc");
        stonecutterBlockFromBlock(provider, consumer, ModBlocks.CELLULOSE_CHISELED_BRICKS.get(), ModBlocks.CELLULOSE_BLOCK.get(), "cellulose_chiseled_bricks_from_block_sc");
        stonecutterBlockFromBlock(provider, consumer, ModBlocks.CELLULOSE_CHISELED_BRICKS.get(), ModBlocks.CELLULOSE_BRICKS.get(), "cellulose_chiseled_bricks_from_bricks_sc");
    }

}
