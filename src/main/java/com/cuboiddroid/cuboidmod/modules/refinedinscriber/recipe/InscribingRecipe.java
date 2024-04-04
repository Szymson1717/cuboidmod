package com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.tile.RefinedInscriberTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipeSerializers;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

// based on SmithingRecipe
public class InscribingRecipe implements Recipe<Container> {
    private final String mode;
    private final Ingredient topLeft;
    private final Ingredient middle;
    private final Ingredient bottomRight;
    private final ItemStack result;
    private final ResourceLocation recipeId;
    private final int workTicks;
    private final int energyRequired;

    public InscribingRecipe(ResourceLocation recipeId, String mode, Ingredient topLeft, Ingredient middle, Ingredient bottomRight, ItemStack result, int workTicks, int energyRequired) {
        this.recipeId = recipeId;
        this.mode = mode;
        this.topLeft = topLeft;
        this.middle = middle;
        this.bottomRight = bottomRight;
        this.result = result;
        this.workTicks = workTicks;
        this.energyRequired = energyRequired;
    }

    /**
     * Get the mode
     *
     * @return The mode
     */
    public String getMode() {
        return this.mode;
    }

    /**
     * Get the top/left input ingredient
     *
     * @return The top/left input ingredient
     */
    public Ingredient getTopLeftIngredient() {
        return this.topLeft;
    }

    /**
     * Get the middle input ingredient
     *
     * @return The middle input ingredient
     */
    public Ingredient getMiddleIngredient() {
        return this.middle;
    }

    /**
     * Get the bottom/right input ingredient
     *
     * @return The bottom/right input ingredient
     */
    public Ingredient getBottomRightIngredient() {
        return this.bottomRight;
    }

    /**
     * Get this tick in ticks to inscribe the input ingredients
     *
     * @return The work time in ticks
     */
    public int getWorkTicks() {
        return this.workTicks;
    }

    /**
     * Get the energy in FE needed to inscribe the input ingredients
     *
     * @return The energy required in FE
     */
    public int getEnergyRequired() {
        return this.energyRequired;
    }

    /**
     * Get the Refined Inscriber image as the toast symbol
     *
     * @return
     */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.REFINED_INSCRIBER.get());
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
     * @return the IRecipeSerializer for the InscribingRecipe
     */
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeSerializers.INSCRIBING.get();
    }

    /**
     * Get the recipe type for the InscribingRecipe
     *
     * @return The IRecipeType for this recipe
     */
    public RecipeType<?> getType() {
        return ModRecipeTypes.INSCRIBING.get();
    }


    /**
     * Checks if there is a recipe match for the ingredients in the tile entities input slots
     *
     * @param inv   The Refined Inscriber tile entity
     * @param level The level / world
     * @return true if there is a match, otherwise false
     */
    @Override
    public boolean matches(Container inv, Level level) {
        return (this.topLeft.test(inv.getItem(RefinedInscriberTileEntity.SLOT_TOP_LEFT))
                && this.middle.test(inv.getItem(RefinedInscriberTileEntity.SLOT_MIDDLE))
                && this.bottomRight.test(inv.getItem(RefinedInscriberTileEntity.SLOT_BOTTOM_RIGHT)));
    }

    /**
     * Assembles the recipe output and returns it as a stack
     *
     * @param inventory the input inventory
     * @return the result
     */
    @Override
    public ItemStack assemble(Container inventory) {
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
    public ItemStack getResultItem() {
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
        NonNullList<Ingredient> ingredients = NonNullList.withSize(3, Ingredient.EMPTY);
        ingredients.set(0, this.topLeft);
        ingredients.set(1, this.middle);
        ingredients.set(2, this.bottomRight);
        return ingredients;
    }

    // ---- Serializer ----

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>>
            implements RecipeSerializer<InscribingRecipe> {

        /* - currently making this match AE2 recipe structure until I figure out
             how to just use them directly

          JSON structure:
            {
              "type": "cuboidmod:inscribing",
              "mode": "press",
              "result": {
                "item": "appliedenergistics2:engineering_processor"
              },
              "ingredients": {
                "top": {
                  "item": "appliedenergistics2:printed_engineering_processor"
                },
                "middle": {
                  "tag": "forge:dusts/redstone"
                },
                "bottom": {
                  "item": "appliedenergistics2:printed_silicon"
                }
              }
            }

            OR

            {
              "type": "cuboidmod:inscribing",
              "mode": "inscribe",
              "result": {
                "item": "appliedenergistics2:calculation_processor_press"
              },
              "ingredients": {
                "top": {
                  "item": "appliedenergistics2:calculation_processor_press"
                },
                "middle": {
                  "tag": "forge:storage_blocks/iron"
                }
              }
            }
         */
        public InscribingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            // TODO - decide whether I'm going to care about the mode...
            String mode = GsonHelper.getAsString(json, "mode");

            JsonObject ingredientsJson = GsonHelper.getAsJsonObject(json, "ingredients");

            Ingredient topLeft = (GsonHelper.isValidNode(ingredientsJson, "top"))
                    ? Ingredient.fromJson(GsonHelper.getAsJsonObject(ingredientsJson, "top"))
                    : Ingredient.EMPTY;
            Ingredient middle = Ingredient.fromJson(GsonHelper.getAsJsonObject(ingredientsJson, "middle"));
            Ingredient bottomRight = (GsonHelper.isValidNode(ingredientsJson, "bottom"))
                    ? Ingredient.fromJson(GsonHelper.getAsJsonObject(ingredientsJson, "bottom"))
                    : Ingredient.EMPTY;

            JsonObject resultJson = GsonHelper.getAsJsonObject(json, "result");
            ResourceLocation itemId = new ResourceLocation(GsonHelper.getAsString(resultJson, "item"));
            int count = GsonHelper.getAsInt(resultJson, "count", 1);

            ItemStack result = new ItemStack(ForgeRegistries.ITEMS.getValue(itemId), count);

            int workTicks = GsonHelper.getAsInt(json, "work_ticks", Config.refinedInscriberDefaultWorkTicks.get());
            int energyRequired = GsonHelper.getAsInt(json, "energy", Config.refinedInscriberDefaultEnergyRequired.get());

            return new InscribingRecipe(recipeId, mode, topLeft, middle, bottomRight, result, workTicks, energyRequired);
        }

        public InscribingRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            String mode = buffer.readUtf();
            Ingredient topLeft = Ingredient.fromNetwork(buffer);
            Ingredient middle = Ingredient.fromNetwork(buffer);
            Ingredient bottomRight = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int workTicks = buffer.readVarInt();
            int energyRequired = buffer.readVarInt();

            return new InscribingRecipe(recipeId, mode, topLeft, middle, bottomRight, result, workTicks, energyRequired);
        }

        public void toNetwork(FriendlyByteBuf buffer, InscribingRecipe recipe) {
            buffer.writeUtf(recipe.mode);
            recipe.topLeft.toNetwork(buffer);
            recipe.middle.toNetwork(buffer);
            recipe.bottomRight.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeVarInt(recipe.workTicks);
            buffer.writeVarInt(recipe.energyRequired);
        }
    }
}
