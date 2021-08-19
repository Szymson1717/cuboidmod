package com.cuboiddroid.cuboidmod.datagen.server;

import com.cuboiddroid.cuboidmod.datagen.server.recipes.*;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        MaterialsDataGenRecipes.build(this, consumer);

        FurnaceDataGenRecipes.build(this, consumer);
        ChestDataGenRecipes.build(this, consumer);
        CraftingTableDataGenRecipes.build(this, consumer);

        QuantumCollapserDataGenRecipes.build(this, consumer);
        SingularityResourceGeneratorDataGenRecipes.build(this, consumer);
        SingularityPowerGeneratorDataGenRecipes.build(this, consumer);
        QuantumTransmutationChamberDataGenRecipes.build(this, consumer);

        ToolsDataGenRecipes.build(this, consumer);
        ArmorDataGenRecipes.build(this, consumer);

        SmoosherDataGenRecipes.build(this, consumer);

        MiscDataGenRecipes.build(this, consumer);
        FoodItemDataGenRecipes.build(this, consumer);
    }

    public InventoryChangeTrigger.Instance hasItem(ITag<Item> itemITag) {
        return RecipeProvider.has(itemITag);
    }

    public InventoryChangeTrigger.Instance hasItem(IItemProvider itemProvider) {
        return RecipeProvider.has(itemProvider);
    }
}
