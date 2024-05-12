package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;

public class QuantumSingularityUtils {
    public static QuantumSingularity loadFromJson(ResourceLocation id, JsonObject json) {
        String name = GsonHelper.getAsString(json, "name");
        JsonArray colors = GsonHelper.getAsJsonArray(json, "colors");

        int overlayColor = Integer.parseInt(colors.get(0).getAsString(), 16);
        int underlayColor = Integer.parseInt(colors.get(1).getAsString(), 16);

        return new QuantumSingularity(id, name, new int[] { overlayColor, underlayColor });
    }

    public static JsonObject writeToJson(QuantumSingularity singularity) {
        JsonObject json = new JsonObject();
        json.addProperty("name", singularity.getName());
        JsonArray colors = new JsonArray();
        colors.add(Integer.toString(singularity.getOverlayColor(), 16));
        colors.add(Integer.toString(singularity.getUnderlayColor(), 16));
        json.add("colors", colors);

        return json;
    }
}