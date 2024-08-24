package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import net.minecraft.resources.ResourceLocation;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.CollapsingRecipeData;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipeData;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipeData;

public class QuantumSingularity extends TempQuantumSingularity {

    private static int nextIndex = 0;
    private final int index;

    protected QuantumSingularity(TempQuantumSingularity singularity) {
        super(singularity.id, singularity.getColors());
        this.index = nextIndex++;
    }

    protected QuantumSingularity(ResourceLocation id, int[] colors) {
        super(id, colors);
        this.index = nextIndex++;
    }

    public int getIndex() { return this.index; }

    public QuantumSingularity setDisabled(boolean disabled) {
        super.setDisabled(disabled);
        return this;
    }

    protected QuantumSingularity setColors(int[] colors) {
        super.setColors(colors);
        return this;
    }

    public QuantumSingularity setRecipe(String identifier, int count) {
        super.setRecipe(identifier, count);
        return this;
    }

    public QuantumSingularity setRecipe(CollapsingRecipeData collapsing) {
        super.setRecipe(collapsing);
        return this;
    }

    public QuantumSingularity setProduction(String identifier, float workTimeMult, float outputMult) {
        super.setProduction(identifier, workTimeMult, outputMult);
        return this;
    }

    public QuantumSingularity setProduction(ResourceGeneratingRecipeData resourceGenerating) {
        super.setProduction(resourceGenerating);
        return this;
    }

    public QuantumSingularity setPowerOutput(float powerOutput) {
        super.setPowerOutput(powerOutput);
        return this;
    }

    public QuantumSingularity setPowerOutput(PowerGeneratingRecipeData powerGenerating) {
        super.setPowerOutput(powerGenerating);
        return this;
    }
}
