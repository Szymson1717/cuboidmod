package com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe;

import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.google.gson.JsonObject;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

public class DryingRecipe implements Recipe<Container> {
    // private static final Random RANDOM = new Random();

    private final ResourceLocation recipeId;
    private Ingredient ingredient;
    private ItemStack resultItem = ItemStack.EMPTY;
    private int workTicks;

    public DryingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "DryingRecipe [ingredient=" + this.ingredient +
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
     * Get the time in ticks to dry the ingredient
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the drying cupboard image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.DRYING_CUPBOARD.get());
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
     * @return the IRecipeSerializer for the DryingRecipe
     */
    public RecipeSerializer<DryingRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Get the recipe type for the DryingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public RecipeType<?> getType() {
        return DryingRecipe.Type.INSTANCE;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The drying cupboard tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(Container inv, Level level) {
        for (int i = 0; i < DryingCupboardTileEntity.INPUT_SLOTS; i++) {
            if (this.ingredient.test(inv.getItem(i)))
                return true;
        }

        return false;
    }

    /**
     * Returns the result item
     *
     * @param inventory the input ingredient
     * @return the result item
     */
    @Deprecated
    @Override
    public ItemStack assemble(Container inventory, RegistryAccess access) {
        return this.getResultItem(access);
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
     * @return the result of the recipe
     */
    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return resultItem.copy();
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

    public static class Type implements RecipeType<DryingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "drying";
    }

    // ---- Serializer ----

    public static class Serializer implements RecipeSerializer<DryingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = Type.ID;

        /*
          JSON structure:
            {
              "type": "cuboidmod:drying",
              "ingredient": {
                "item": "cuboidmod:cured_rotten_flesh"
              },
              "result": {
                "item": "cuboidmod:zombie_biltong",
                "count": 3
              },
              "work_ticks": 600
            }
         */
        @Override
        public DryingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            DryingRecipe recipe = new DryingRecipe(recipeId);
            recipe.workTicks = GsonHelper.getAsInt(json, "work_ticks", 200);
            recipe.ingredient = Ingredient.fromJson(json.get("ingredient"));

            JsonObject resultJson = json.getAsJsonObject("result");
            String itemId = GsonHelper.getAsString(resultJson, "item");
            Item item = ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(itemId));
            int count = GsonHelper.getAsInt(resultJson, "count", 1);

            recipe.resultItem = new ItemStack(item, count);

            return recipe;
        }

        @Override
        public DryingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            DryingRecipe recipe = new DryingRecipe(recipeId);
            recipe.workTicks = buffer.readVarInt();
            recipe.ingredient = Ingredient.fromNetwork(buffer);

            ResourceLocation itemId = buffer.readResourceLocation();
            int count = buffer.readVarInt();
            Item item = ForgeRegistries.ITEMS.getValue(itemId);
            recipe.resultItem = new ItemStack(item, count);

            return recipe;
        }

        public void toNetwork(FriendlyByteBuf buffer, DryingRecipe recipe) {
            buffer.writeVarInt(recipe.workTicks);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeResourceLocation(Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(recipe.resultItem.getItem())));
            buffer.writeVarInt(recipe.resultItem.getCount());
        }
    }
}
