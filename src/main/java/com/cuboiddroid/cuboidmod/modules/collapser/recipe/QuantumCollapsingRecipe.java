package com.cuboiddroid.cuboidmod.modules.collapser.recipe;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.tile.QuantumCollapserTileEntityBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipes;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

/*
  Recipes for collapsing items into singularities for the Quantum Singularity Collapser
 */
public class QuantumCollapsingRecipe implements IRecipe<IInventory> {
    private final ResourceLocation recipeId;
    private Ingredient ingredient;
    private ItemStack result;
    private int inputAmount;
    private int workTicks;

    public QuantumCollapsingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        //CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString() {

        // Overriding toString is not required, it's just useful for debugging.
        return "QuantumCollapsingRecipe [ingredient=" + this.ingredient +
                ", result=" + this.result + ", id=" + this.recipeId + "]";
    }
    */

    /**
     * Get the input ingredient
     *
     * @return The input ingredient
     */
    public Ingredient getIngredient() {
        return this.ingredient;
    }

    /**
     * Get the time in ticks to recycle the input ingredient
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the energy in FE needed to recycle the input ingredient
     *
     * @return The energy required in FE
     */
    public int getRequiredInputAmount() {
        return this.inputAmount;
    }

    /**
     * Get the Thatldu Quantum Collapser image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get());
    }

    /**
     * Get the recipe's resource location (recipe ID)
     *
     * @return the recipe ID as a ResourceLocation
     */
    @Override
    public ResourceLocation getId() {
        return this.recipeId;
    }

    /**
     * Get the recipe serializer
     *
     * @return the IRecipeSerializer for the CollapsingRecipe
     */
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.Serializers.COLLAPSING.get();
    }

    /**
     * Get the recipe type for the CollapsingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public IRecipeType<?> getType() {
        return ModRecipes.Types.COLLAPSING;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The collapser tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(IInventory inv, World level) {
        return this.ingredient.test(inv.getItem(QuantumCollapserTileEntityBase.INPUT_SLOT));
    }

    /**
     * Assemble the recipe and return the result item stack
     *
     * @param inventory
     * @return
     */
    @Override
    public ItemStack assemble(IInventory inventory) {
        return this.getResultItem();
    }

    /**
     * Checks if the recipe can fit in the machine. As this recipe is for single input, we'll just say yes!
     *
     * @param gridWidth
     * @param gridHeight
     * @return always returns true
     */
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return true;
    }

    /**
     * Gets the result item.
     *
     * @return
     */
    @Override
    public ItemStack getResultItem() {
        return result.copy();
    }

    /**
     * Indicates that this recipe has special processing to Forge/Minecraft
     *
     * @return
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    // ---- Serializer ----

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<QuantumCollapsingRecipe> {

        @Override
        public QuantumCollapsingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            QuantumCollapsingRecipe recipe = new QuantumCollapsingRecipe(recipeId);
            recipe.workTicks = JSONUtils.getAsInt(json, "work_ticks", 200);

            JsonObject inputJson = JSONUtils.getAsJsonObject(json, "input");
            if (inputJson.has("item")) {
                ResourceLocation inputItemId = new ResourceLocation(JSONUtils.getAsString(inputJson, "item"));

                recipe.ingredient = Ingredient.of(new ItemStack(ForgeRegistries.ITEMS.getValue(inputItemId), 1));
            } else {
                try {
                    recipe.ingredient = Ingredient.fromJson(inputJson);
                } catch (Exception ex) {
                    CuboidMod.LOGGER.warn("Could not load tag: '" + JSONUtils.getAsString(inputJson, "tag") + "' - recipe: '" + recipeId.getPath() + "'\n\n" + ex);
                    recipe.ingredient = Ingredient.EMPTY;
                }
            }

            recipe.inputAmount = JSONUtils.getAsInt(inputJson, "count", 1024);

            JsonObject resultJson = JSONUtils.getAsJsonObject(json, "result");
            ResourceLocation itemId = new ResourceLocation(JSONUtils.getAsString(resultJson, "item"));
            int resultCount = JSONUtils.getAsInt(resultJson, "count", 1);

            recipe.result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), resultCount);

            return recipe;
        }

        @Nullable
        @Override
        public QuantumCollapsingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            QuantumCollapsingRecipe recipe = new QuantumCollapsingRecipe(recipeId);
            recipe.workTicks = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            recipe.result = buffer.readItem();
            recipe.inputAmount = buffer.readVarInt();

            return recipe;
        }

        @Override
        public void toNetwork(PacketBuffer buffer, QuantumCollapsingRecipe recipe) {
            buffer.writeVarInt(recipe.workTicks);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.inputAmount);
        }
    }
}
