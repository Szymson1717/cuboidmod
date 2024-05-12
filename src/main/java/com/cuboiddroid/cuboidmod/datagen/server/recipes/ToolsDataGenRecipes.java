package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.UpgradeRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Consumer;

import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

public class ToolsDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        buildNotsogudiumRecipes(provider, consumer);
        buildKudbebeddaRecipes(provider, consumer);
        buildNotarfbadiumRecipes(provider, consumer);
        buildWikidiumRecipes(provider, consumer);
        buildThatlduRecipes(provider, consumer);
    }

    private static void buildNotsogudiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidSwordFromIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_SWORD, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidPickaxeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_PICKAXE, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidAxeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_AXE, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidHoeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_HOE, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidShovelFromIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_SHOVEL, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
        craftCuboidSmasherFromBlocksIngotsAndAnyRod(provider, consumer, ModItems.NOTSOGUDIUM_SMASHER, ModBlocks.NOTSOGUDIUM_BLOCK, ModItems.NOTSOGUDIUM_INGOT, "notsogudium");
    }

    private static void buildKudbebeddaRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidSwordFromIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_SWORD, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidPickaxeFromIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_PICKAXE, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidAxeFromIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_AXE, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidHoeFromIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_HOE, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidShovelFromIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_SHOVEL, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
        craftCuboidSmasherFromBlocksIngotsAndAnyRod(provider, consumer, ModItems.KUDBEBEDDA_SMASHER, ModBlocks.KUDBEBEDDA_BLOCK, ModItems.KUDBEBEDDA_INGOT, "kudbebedda");
    }

    private static void buildNotarfbadiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidSwordFromIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_SWORD, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidPickaxeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_PICKAXE, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidAxeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_AXE, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidHoeFromIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_HOE, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidShovelFromIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_SHOVEL, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
        craftCuboidSmasherFromBlocksIngotsAndAnyRod(provider, consumer, ModItems.NOTARFBADIUM_SMASHER, ModBlocks.NOTARFBADIUM_BLOCK, ModItems.NOTARFBADIUM_INGOT, "notarfbadium");
    }

    private static void buildWikidiumRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        craftCuboidSwordFromIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_SWORD, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidPickaxeFromIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_PICKAXE, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidAxeFromIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_AXE, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidHoeFromIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_HOE, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidShovelFromIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_SHOVEL, ModItems.WIKIDIUM_INGOT, "wikidium");
        craftCuboidSmasherFromBlocksIngotsAndAnyRod(provider, consumer, ModItems.WIKIDIUM_SMASHER, ModBlocks.WIKIDIUM_BLOCK, ModItems.WIKIDIUM_INGOT, "wikidium");
    }

    private static void buildThatlduRecipes(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_SWORD.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_SWORD.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_sword_smithing"));

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_PICKAXE.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_PICKAXE.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_pickaxe_smithing"));

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_AXE.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_AXE.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_axe_smithing"));

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_HOE.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_HOE.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_hoe_smithing"));

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_SHOVEL.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_SHOVEL.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_shovel_smithing"));

        UpgradeRecipeBuilder.smithing(Ingredient.of(ModItems.WIKIDIUM_SMASHER.get()), Ingredient.of(ModItems.THATLDU_INGOT.get()), ModItems.THATLDU_SMASHER.get())
                .unlocks("has_item", provider.hasItem(ModItems.THATLDU_INGOT.get()))
                .save(consumer, modId("thatldu_smasher_smithing"));
    }

    // ------------------------------------------------------------------------------------

    private static void craftCuboidSmasherFromBlocksIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<Item> smasher, RegistryObject<Block> materialBlock, RegistryObject<Item> materialIngot, String materialName) {
        ShapedRecipeBuilder.shaped(smasher.get())
                .define('-', materialIngot.get())
                .define('#', materialBlock.get())
                .define('|', Tags.Items.RODS)
                .pattern("#-#")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", provider.hasItem(materialIngot.get()))
                .save(consumer, modId(materialName + "_smasher_from_blocks_ingot_and_rods"));
    }


    private static void craftCuboidShovelFromIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<ShovelItem> shovel, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(shovel.get())
                .define('#', ingot.get())
                .define('|', Tags.Items.RODS)
                .pattern("#")
                .pattern("|")
                .pattern("|")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_shovel_from_ingots_and_rods"));
    }

    private static void craftCuboidHoeFromIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<HoeItem> hoe, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(hoe.get())
                .define('#', ingot.get())
                .define('|', Tags.Items.RODS)
                .pattern("##")
                .pattern(" |")
                .pattern(" |")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_hoe_from_ingots_and_rods"));
    }

    private static void craftCuboidAxeFromIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<AxeItem> axe, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(axe.get())
                .define('#', ingot.get())
                .define('|', Tags.Items.RODS)
                .pattern("##")
                .pattern("#|")
                .pattern(" |")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_axe_from_ingots_and_rods"));
    }

    private static void craftCuboidPickaxeFromIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<PickaxeItem> pickaxe, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(pickaxe.get())
                .define('#', ingot.get())
                .define('|', Tags.Items.RODS)
                .pattern("###")
                .pattern(" | ")
                .pattern(" | ")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_pickaxe_from_ingots_and_rods"));
    }

    private static void craftCuboidSwordFromIngotsAndAnyRod(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, RegistryObject<SwordItem> sword, RegistryObject<Item> ingot, String materialName) {
        ShapedRecipeBuilder.shaped(sword.get())
                .define('#', ingot.get())
                .define('|', Tags.Items.RODS)
                .pattern("#")
                .pattern("#")
                .pattern("|")
                .unlockedBy("has_item", provider.hasItem(ingot.get()))
                .save(consumer, modId(materialName + "_sword_from_ingots_and_rod"));
    }

}
