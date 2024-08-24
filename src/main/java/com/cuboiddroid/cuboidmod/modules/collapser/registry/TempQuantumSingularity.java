package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.CollapsingRecipeData;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipeData;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipeData;

import net.minecraft.resources.ResourceLocation;

public class TempQuantumSingularity {

    protected final ResourceLocation id;
    private int[] colors;
    private boolean disabled;

    private CollapsingRecipeData collapsingRecipeData;
    private ResourceGeneratingRecipeData resourceGeneratingRecipeData;
    private PowerGeneratingRecipeData powerGeneratingRecipeData;

    protected TempQuantumSingularity(ResourceLocation id, int[] colors) {
        this.id = id;

        this.colors = colors;
    }

    public TempQuantumSingularity setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    protected TempQuantumSingularity setColors(int[] colors) {
        if (colors != null) this.colors = colors;
        return this;
    }

    public TempQuantumSingularity setRecipe(String identifier, int count) {
        this.collapsingRecipeData = new CollapsingRecipeData(identifier, count);
        return this;
    }

    public TempQuantumSingularity setRecipe(CollapsingRecipeData collapsing) {
        this.collapsingRecipeData = collapsing;
        return this;
    }

    public TempQuantumSingularity setProduction(String identifier, float workTimeMult, float outputMult) {
        this.resourceGeneratingRecipeData = new ResourceGeneratingRecipeData(identifier, workTimeMult, outputMult);
        return this;
    }

    public TempQuantumSingularity setProduction(ResourceGeneratingRecipeData resourceGenerating) {
        this.resourceGeneratingRecipeData = resourceGenerating;
        return this;
    }

    public TempQuantumSingularity setPowerOutput(float powerOutput) {
        this.powerGeneratingRecipeData = new PowerGeneratingRecipeData(powerOutput);
        return this;
    }

    public TempQuantumSingularity setPowerOutput(PowerGeneratingRecipeData powerGenerating) {
        this.powerGeneratingRecipeData = powerGenerating;
        return this;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public int[] getColors() {
        return colors;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public int getOverlayColor() {
        return this.colors[0];
    }

    public int getUnderlayColor() {
        return this.colors[1];
    }

    public CollapsingRecipeData getRecipe() {
        return this.collapsingRecipeData;
    }

    public ResourceGeneratingRecipeData getProduction() {
        return this.resourceGeneratingRecipeData;
    }

    public PowerGeneratingRecipeData getPowerOutput() {
        return this.powerGeneratingRecipeData;
    }
}
