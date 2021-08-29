package com.cuboiddroid.cuboidmod.modules.recycler.recipe;

import com.cuboiddroid.cuboidmod.modules.recycler.tile.MolecularRecyclerTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipes;
import com.cuboiddroid.cuboidmod.util.Pair;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
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

import java.util.*;
import java.util.stream.Collectors;

public class RecyclingRecipe implements IRecipe<IInventory> {
    private static final Random RANDOM = new Random();

    private final ResourceLocation recipeId;
    private final Map<ItemStack, Float> results = new LinkedHashMap<>();
    private Ingredient ingredient;
    private int workTicks;
    private int energyRequired;

    public RecyclingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "RecyclingRecipe [ingredient=" + this.ingredient + ", addition=" + this.addition +
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
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    /**
     * Get the molecular recycler image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.MOLECULAR_RECYCLER.get());
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
     * @return the IRecipeSerializer for the RecyclingRecipe
     */
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.Serializers.RECYCLING.get();
    }

    /**
     * Get the recipe type for the RecyclingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public IRecipeType<?> getType() {
        return ModRecipes.Types.RECYCLING;
    }

    /**
     * Get results of recycling. Some results may have a limited chance of being produced, and this
     * method takes that into account.
     *
     * @param inv The recycler tile entity
     * @return Results of recycling
     */
    public List<ItemStack> getResults(IInventory inv) {
        return results.entrySet().stream()
                .filter(e -> RANDOM.nextDouble() < e.getValue())
                .map(e -> e.getKey().copy())
                .collect(Collectors.toList());
    }

    /**
     * Get the possible results of recycling. Useful for making sure there is enough room in the
     * inventory.
     *
     * @param inv The recycler tile entity
     * @return All possible results of recycling
     */
    public Set<ItemStack> getPossibleResults(IInventory inv) {
        return results.keySet();
    }

    public List<Pair<ItemStack, Float>> getPossibleResultsWithChances() {
        return results.entrySet().stream()
                .map(e -> new Pair<>(e.getKey(), e.getValue()))
                .collect(Collectors.toList());
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The recycler tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(IInventory inv, World level) {
        return this.ingredient.test(inv.getItem(MolecularRecyclerTileEntity.SLOT_INPUT));
    }

    /**
     * DO NOT use this method - use the "getResults" method instead for this custom recipe.
     *
     * @param inventory
     * @return
     */
    @Deprecated
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
     * DO NOT use this method - use the "getResults" method instead for this custom recipe.
     *
     * @return
     */
    @Deprecated
    @Override
    public ItemStack getResultItem() {
        return !results.isEmpty() ? results.keySet().iterator().next() : ItemStack.EMPTY;
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

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>>
            implements IRecipeSerializer<RecyclingRecipe> {

        /*
          JSON structure:
            {
              "type": "cuboidmod:recycling",
              "ingredient": {
                "item": "cuboidmod:notarfbadium_helmet"
              },
              "results": [
                {
                  "item": "cuboidmod:notarfbadium_ingot",
                  "count": 3
                },
                {
                  "item": "cuboidmod:notarfbadium_ingot",
                  "count": 1,
                  "chance": 0.25
                }
              ],
              "work_ticks": 200,
              "energy": 500
            }
         */
        @Override
        public RecyclingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            RecyclingRecipe recipe = new RecyclingRecipe(recipeId);
            recipe.workTicks = JSONUtils.getAsInt(json, "work_ticks", 200);
            recipe.energyRequired = JSONUtils.getAsInt(json, "energy", 500);
            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));
            JsonArray resultsArray = json.getAsJsonArray("results");
            for (JsonElement element : resultsArray) {
                JsonObject obj = element.getAsJsonObject();
                String itemId = JSONUtils.getAsString(obj, "item");
                Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemId));
                int count = JSONUtils.getAsInt(obj, "count", 1);
                ItemStack stack = new ItemStack(item, count);
                float chance = JSONUtils.getAsFloat(obj, "chance", 1.0F);
                recipe.results.put(stack, chance);
            }
            return recipe;
        }

        @Override
        public RecyclingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            RecyclingRecipe recipe = new RecyclingRecipe(recipeId);
            recipe.workTicks = buffer.readVarInt();
            recipe.energyRequired = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);
            int resultCount = buffer.readByte();
            for (int i = 0; i < resultCount; ++i) {
                ResourceLocation itemId = buffer.readResourceLocation();
                int count = buffer.readVarInt();
                float chance = buffer.readFloat();
                Item item = ForgeRegistries.ITEMS.getValue(itemId);
                recipe.results.put(new ItemStack(item, count), chance);
            }

            return recipe;
        }

        public void toNetwork(PacketBuffer buffer, RecyclingRecipe recipe) {
            buffer.writeVarInt(recipe.workTicks);
            buffer.writeVarInt(recipe.energyRequired);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeByte(recipe.results.size());
            recipe.results.forEach((stack, chance) -> {
                buffer.writeResourceLocation(Objects.requireNonNull(stack.getItem().getRegistryName()));
                buffer.writeVarInt(stack.getCount());
                buffer.writeFloat(chance);
            });
        }
    }
}
