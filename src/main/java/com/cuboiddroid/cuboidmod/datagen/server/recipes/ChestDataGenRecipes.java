package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraftforge.fmllegacy.RegistryObject;

import java.util.function.Consumer;

public class ChestDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        // chest from ingots
        chestFromIngots(provider, consumer, ModBlocks.NOTSOGUDIUM_CHEST, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        chestFromIngots(provider, consumer, ModBlocks.KUDBEBEDDA_CHEST, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        chestFromIngots(provider, consumer, ModBlocks.NOTARFBADIUM_CHEST, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        chestFromIngots(provider, consumer, ModBlocks.WIKIDIUM_CHEST, ModItems.WIKIDIUM_INGOT, "wikidium");
        chestFromIngots(provider, consumer, ModBlocks.THATLDU_CHEST, ModItems.THATLDU_INGOT, "thatldu");

        // upgrade recipes
        chestFromIngotsAndPreviousChest(provider, consumer, ModBlocks.KUDBEBEDDA_CHEST, ModBlocks.NOTSOGUDIUM_CHEST, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        chestFromIngotsAndPreviousChest(provider, consumer, ModBlocks.NOTARFBADIUM_CHEST, ModBlocks.KUDBEBEDDA_CHEST, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        chestFromIngotsAndPreviousChest(provider, consumer, ModBlocks.WIKIDIUM_CHEST, ModBlocks.NOTARFBADIUM_CHEST, ModItems.WIKIDIUM_INGOT, "wikidium");
        chestFromIngotsAndPreviousChest(provider, consumer, ModBlocks.THATLDU_CHEST, ModBlocks.WIKIDIUM_CHEST, ModItems.THATLDU_INGOT, "thatldu");
    }

    private static void chestFromIngots(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<? extends CuboidChestBlockBase> chestBlock, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(chestBlock.get())
                .define('#', ingot.get())
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_chest_from_ingots"));
    }

    private static void chestFromIngotsAndPreviousChest(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<? extends CuboidChestBlockBase> chestBlock, RegistryObject<? extends CuboidChestBlockBase> prevChest, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(chestBlock.get())
                .define('#', ingot.get())
                .define('$', prevChest.get())
                .pattern(" # ")
                .pattern("#$#")
                .pattern(" # ")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_chest_from_prev"));
    }

}
