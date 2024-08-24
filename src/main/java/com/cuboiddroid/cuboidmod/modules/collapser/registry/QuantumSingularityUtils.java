package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.CollapsingRecipeData;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipeData;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipeData;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;

public class QuantumSingularityUtils {
    public static TempQuantumSingularity loadFromJson(ResourceLocation id, JsonObject json) {
        boolean disabled = GsonHelper.getAsBoolean(json, "disabled", false);
        JsonArray colors = GsonHelper.getAsJsonArray(json, "colors", new JsonArray(0));

        int[] colorArray = null;

        if (colors.size() == 2) {
            int overlayColor = Integer.parseInt(colors.get(0).getAsString(), 16);
            int underlayColor = Integer.parseInt(colors.get(1).getAsString(), 16);

            colorArray = new int[] { overlayColor, underlayColor };
        }

        TempQuantumSingularity tempSingularity = new TempQuantumSingularity(id, colorArray);

        if (json.has("recipe")) {
            try {
                JsonObject recipe = GsonHelper.getAsJsonObject(json, "recipe");
                String identifier = GsonHelper.getAsString(recipe, "identifier");
                int amount = GsonHelper.getAsInt(recipe, "amount");
                tempSingularity.setRecipe(identifier, amount);
            } catch (Exception e) {}
        }
        
        if (json.has("production")) {
            try {
                JsonObject production = GsonHelper.getAsJsonObject(json, "production");
                String output = GsonHelper.getAsString(production, "output");
                float outputMult = GsonHelper.getAsFloat(production, "amountMult");
                float workTimeMult = GsonHelper.getAsFloat(production, "workTime");
                tempSingularity.setProduction(output, outputMult, workTimeMult);
            } catch (Exception e) {}
        }

        if (json.has("power")) {
            try {
                float power = GsonHelper.getAsFloat(json, "power");
                tempSingularity.setPowerOutput(power);
            } catch (Exception e) {}
        }

        return tempSingularity.setDisabled(disabled);
    }

    public static JsonObject writeToJson(TempQuantumSingularity singularity) {
        JsonObject json = new JsonObject();

        if (singularity.isDisabled())
            json.addProperty("disabled", true);

        JsonArray colors = new JsonArray();
        colors.add(Integer.toString(singularity.getOverlayColor(), 16));
        colors.add(Integer.toString(singularity.getUnderlayColor(), 16));
        json.add("colors", colors);

        if (singularity.getRecipe() != null) {
            CollapsingRecipeData recipeData = singularity.getRecipe();
            JsonObject recipe = new JsonObject();
            String identifier;
            
            if (recipeData.usesTag) identifier = "#" + recipeData.recipeInputTag.toString();
            else identifier = recipeData.recipeInput.toString();
            recipe.addProperty("identifier", identifier);
            recipe.addProperty("amount", recipeData.recipeCount);

            json.add("recipe", recipe);
        }

        if (singularity.getProduction() != null) {
            ResourceGeneratingRecipeData productionData = singularity.getProduction();
            JsonObject production = new JsonObject();

            production.addProperty("output", productionData.output.toString());
            production.addProperty("amountMult", productionData.outputMult);
            production.addProperty("workTime", productionData.workTimeMult);

            json.add("production", production);
        }

        if (singularity.getPowerOutput() != null) {
            PowerGeneratingRecipeData powerData = singularity.getPowerOutput();
            json.addProperty("power", powerData.powerOutput);
        }

        return json;
    }
}