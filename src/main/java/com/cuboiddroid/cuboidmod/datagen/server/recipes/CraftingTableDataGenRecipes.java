package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Consumer;

public class CraftingTableDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftingTableFromSilicaAndChunks(provider, consumer, ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE, ModItems.NOTSOGUDIUM_CHUNK, "notsogudium");
        craftingTableFromSilicaAndChunks(provider, consumer, ModBlocks.KUDBEBEDDA_CRAFTING_TABLE, ModItems.KUDBEBEDDA_CHUNK, "kudbebedda");
        craftingTableFromSilicaAndChunks(provider, consumer, ModBlocks.NOTARFBADIUM_CRAFTING_TABLE, ModItems.NOTARFBADIUM_CHUNK, "notarfbadium");
        craftingTableFromSilicaAndChunks(provider, consumer, ModBlocks.WIKIDIUM_CRAFTING_TABLE, ModItems.WIKIDIUM_CHUNK, "wikidium");
        craftingTableFromSilicaAndChunks(provider, consumer, ModBlocks.THATLDU_CRAFTING_TABLE, ModItems.THATLDU_CHUNK, "thatldu");

        smithingTableFromSilicaAndChunks(provider, consumer);
    }

    private static void craftingTableFromSilicaAndChunks(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Block> craftingTable, RegistryObject<Item> chunk, String materialName) {
        ShapedRecipeBuilder.shaped(craftingTable.get())
                .define('$', ModItems.SILICA_DUST.get())
                .define('#', chunk.get())
                .pattern("$$")
                .pattern("##")
                .unlockedBy("has_item", provider.hasItem(chunk.get()))
                .save(consumer, modId(materialName + "_crafting_table_from_silica_and_chunk"));
    }

    private static void smithingTableFromSilicaAndChunks(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Items.SMITHING_TABLE)
                .define('$', ModTags.Items.INGOTS)
                .define('#', ModTags.Items.ORES)
                .pattern("$$")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("smithing_table_when_thatldu_ingot"));
    }
}
