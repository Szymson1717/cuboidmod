package com.cuboiddroid.cuboidmod.modules.powergen.recipe;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.SingularityPowerGeneratorTileEntityBase;
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

public class PowerGeneratingRecipe implements IRecipe<IInventory> {
    private final ResourceLocation recipeId;
    private Ingredient singularity;
    private float powerMultiplier;

    public PowerGeneratingRecipe(ResourceLocation recipeId) {
        this.recipeId = recipeId;
        // This output is not required, but it can be used to detect when a recipe has been
        // loaded into the game.
        // CuboidMod.LOGGER.info("---> Loaded " + this.toString());
    }

    /*
    @Override
    public String toString () {

        // Overriding toString is not required, it's just useful for debugging.
        return "ResourceGeneratingRecipe [ingredient=" + this.ingredient + ", addition=" + this.addition +
                ", result=" + this.result + ", id=" + this.recipeId + "]";
    }
    */

    /**
     * Get the input ingredient (singularity)
     *
     * @return The input ingredient (singularity)
     */
    public Ingredient getSingularity() {
        return this.singularity;
    }

    /**
     * Get the Thatldu SPG image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get());
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
     * @return the IRecipeSerializer for the ResourceGeneratingRecipe
     */
    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.Serializers.POWER_GENERATING.get();
    }

    /**
     * Get the recipe type for the PowerGeneratingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public IRecipeType<?> getType() {
        return ModRecipes.Types.POWER_GENERATING;
    }

    /**
     * Checks if there is a recipe match for the ingredient in the tile entities input slot
     *
     * @param inv   The SRG entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(IInventory inv, World level) {
        return this.singularity.test(inv.getItem(SingularityPowerGeneratorTileEntityBase.SINGULARITY_INPUT));
    }

    /**
     * DO NOT use this method - use the "getPowerMultiplier" method instead for this custom recipe.
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
     * DO NOT use this method - use the "getPowerMultiplier" method instead for this custom recipe.
     *
     * @return
     */
    @Deprecated
    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY;
    }

    public float getPowerMultiplier() {
        return this.powerMultiplier;
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
            implements IRecipeSerializer<PowerGeneratingRecipe> {

        /*
          JSON structure:
            {
              "type": "cuboidmod:power_generating",
              "singularity": {
                "item": "cuboidmod:dirt_quantum_singularity",
                "multiplier": 1.0
              }
            }
         */
        @Override
        public PowerGeneratingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            PowerGeneratingRecipe recipe = new PowerGeneratingRecipe(recipeId);

            JsonObject singularityJson = JSONUtils.getAsJsonObject(json, "singularity");
            ResourceLocation singularityItemId = new ResourceLocation(JSONUtils.getAsString(singularityJson, "item"));

            recipe.singularity = Ingredient.of(new ItemStack(ForgeRegistries.ITEMS.getValue(singularityItemId), 1));
            recipe.powerMultiplier = JSONUtils.getAsFloat(singularityJson, "multiplier");

            return recipe;
        }

        @Override
        public PowerGeneratingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            PowerGeneratingRecipe recipe = new PowerGeneratingRecipe(recipeId);
            recipe.singularity = Ingredient.fromNetwork(buffer);
            recipe.powerMultiplier = buffer.readFloat();

            return recipe;
        }

        public void toNetwork(PacketBuffer buffer, PowerGeneratingRecipe recipe) {
            recipe.singularity.toNetwork(buffer);
            buffer.writeFloat(recipe.powerMultiplier);
        }
    }
}

