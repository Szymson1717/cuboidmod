package com.cuboiddroid.cuboidmod.modules.transmuter.recipe;

import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.google.gson.JsonObject;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.GsonHelper;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

// based on SmithingRecipe
public class TransmutingRecipe implements Recipe<Container> {
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final ResourceLocation recipeId;
    private final int workTicks;
    private final int energyRequired;

    public TransmutingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result, int workTicks, int energyRequired) {
        this.recipeId = recipeId;
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.workTicks = workTicks;
        this.energyRequired = energyRequired;

        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "TransmutingRecipe [base=" + this.base + ", addition=" + this.addition +
                ", result=" + this.result + ", id=" + this.recipeId + "]";
    }
    */

    /**
     * Get the input ingredient
     *
     * @return The input ingredient
     */
    public Ingredient getIngredient() {
        return this.base;
    }

    /**
     * Get the additional input ingredient
     *
     * @return The additional input ingredient
     */
    public Ingredient getAdditionalIngredient() {
        return this.addition;
    }

    /**
     * Get this tick in ticks to transmute the input ingredients
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the energy in FE needed to transmute the input ingredients
     *
     * @return The energy required in FE
     */
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    /**
     * Get the Quantum Transmutation Chamber image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get());
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
     * @return the IRecipeSerializer for the TransmutingRecipe
     */
    public RecipeSerializer<TransmutingRecipe> getSerializer() {
        return Serializer.INSTANCE;
    }

    /**
     * Get the recipe type for the TransmutingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public RecipeType<?> getType() {
        return TransmutingRecipe.Type.INSTANCE;
    }


    /**
     * Checks if there is a recipe match for the ingredients in the tile entities input slots
     *
     * @param inv   The QTC tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(Container inv, Level level) {
        return (this.base.test(inv.getItem(QuantumTransmutationChamberTileEntity.SLOT_INPUT))
                && this.addition.test(inv.getItem(QuantumTransmutationChamberTileEntity.SLOT_ADDITIONAL)))
                || (this.base.test(inv.getItem(QuantumTransmutationChamberTileEntity.SLOT_ADDITIONAL))
                && this.addition.test(inv.getItem(QuantumTransmutationChamberTileEntity.SLOT_INPUT)));
    }

    /**
     * Assembles the recipe output and returns it as a stack
     *
     * @param inventory the input inventory
     * @return the result
     */
    @Override
    public ItemStack assemble(Container inventory, RegistryAccess access) {
        ItemStack itemstack = this.result.copy();
        CompoundTag compoundnbt = inventory.getItem(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        return itemstack;
    }

    /**
     * Checks if the recipe can fit in the machine.
     *
     * @param gridWidth
     * @param gridHeight
     * @return true if it fits, otherwise false
     */
    @Override
    public boolean canCraftInDimensions(int gridWidth, int gridHeight) {
        return true;
    }

    /**
     * Returns the result item
     *
     * @return the recipe output
     */
    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return this.result;
    }

    /**
     * Indicates that this recipe has special processing to Forge/Minecraft
     *
     * @return always true right now
     */
    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(2, Ingredient.EMPTY);
        ingredients.set(0, this.base);
        ingredients.set(1, this.addition);
        return ingredients;
    }

    public static class Type implements RecipeType<TransmutingRecipe> {
        private Type() { }
        public static final Type INSTANCE = new Type();
        public static final String ID = "transmuting";
    }

    // ---- Serializer ----

    public static class Serializer implements RecipeSerializer<TransmutingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = Type.ID;
        /*
          JSON structure:
            {
              "type": "cuboidmod:transmuting",
              "base": {
                "item": "cuboidmod:notarfbadium_chunk"
              },
              "addition": {
                "item": "minecraft:coal"
              },
              "result": {
                "item": "cuboidmod:wikidium_chunk",
                "count": 1
              }
              "work_ticks": 100,
              "energy": 500
            }
         */
        public TransmutingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient base = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "base"));
            Ingredient addition = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "addition"));

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
            ResourceLocation itemId = new ResourceLocation(GsonHelper.getAsString(resultJson, "item"));
            int count = GsonHelper.getAsInt(resultJson, "count", 1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            int workTicks = GsonHelper.getAsInt(json, "work_ticks", 100);
            int energyRequired = GsonHelper.getAsInt(json, "energy", 1000);

            return new TransmutingRecipe(recipeId, base, addition, result, workTicks, energyRequired);
        }

        public TransmutingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient base = Ingredient.fromNetwork(buffer);
            Ingredient addition = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int workTicks = buffer.readInt();
            int energyRequired = buffer.readInt();

            return new TransmutingRecipe(recipeId, base, addition, result, workTicks, energyRequired);
        }

        public void toNetwork(FriendlyByteBuf buffer, TransmutingRecipe recipe) {
            recipe.base.toNetwork(buffer);
            recipe.addition.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.workTicks);
            buffer.writeInt(recipe.energyRequired);
        }
    }
}
