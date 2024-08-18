package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class QuantumSingularity {
    private static int nextIndex = 0;

    private final int index;
    private final ResourceLocation id;
    protected boolean disabled = false;
    protected String name;
    protected int[] colors;

    private CollapsingRecipeData collapsingRecipeData;
    private ResourceGeneratingRecipeData resourceGeneratingRecipeData;
    private PowerGeneratingRecipeData powerGeneratingRecipeData;

    public QuantumSingularity(TempQuantumSingularity singularity) {
        this.index = nextIndex++;
        this.id = singularity.id;
        this.name = singularity.name;
        this.colors = singularity.colors;
    }

    public QuantumSingularity(ResourceLocation id, String name, int[] colors) {
        this.index = nextIndex++;
        this.id = id;
        this.name = name;
        this.colors = colors;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public int getOverlayColor() {
        return this.colors[0];
    }

    public int getUnderlayColor() {
        return this.colors[1];
    }

    public int getIndex() { return this.index; }

    public Component getDisplayName() {
        return Component.translatable(this.name);
    }

    protected QuantumSingularity setName(String name) {
        this.name = name;
        return this;
    }

    protected QuantumSingularity setColors(int[] colors) {
        this.colors = colors;
        return this;
    }

    public QuantumSingularity setDisabled(boolean disabled) {
        this.disabled = disabled;
        return this;
    }

    public QuantumSingularity setRecipe(String identifier, int count) {
        this.collapsingRecipeData = new CollapsingRecipeData(identifier, count);
        return this;
    }

    public QuantumSingularity setProduction(String identifier, float workTimeMult, float outputMult) {
        this.resourceGeneratingRecipeData = new ResourceGeneratingRecipeData(identifier, workTimeMult, outputMult);
        return this;
    }

    public QuantumSingularity setPowerOutput(float powerOutput) {
        this.powerGeneratingRecipeData = new PowerGeneratingRecipeData(powerOutput);
        return this;
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


    public class CollapsingRecipeData {
        public final ResourceLocation recipeInput;
        public final ResourceLocation recipeInputTag;
        public final int recipeCount;

        public final boolean usesTag;
        
        private CollapsingRecipeData(String identifier, int recipeCount) {
            this.recipeInput = ResourceLocation.tryParse(identifier); // Returns null if it starts with #
            this.recipeInputTag = ResourceLocation.tryParse(identifier.replaceFirst("^#", ""));

            // this.recipeInput = ForgeRegistries.ITEMS.getValue(itemIdentifier);
            // this.recipeInputTag = TagKey.create(Registries.ITEM, tagIdentifier);
            this.recipeCount = recipeCount;

            this.usesTag = (this.recipeInput == this.recipeInputTag);
        }
    }

    public class ResourceGeneratingRecipeData {
        public final ResourceLocation output;
        public final float workTimeMult;
        public final float outputMult;

        private ResourceGeneratingRecipeData(String identifier, float workTimeMult, float outputMult) {
            this.output = ResourceLocation.tryParse(identifier);
            // this.output = ForgeRegistries.ITEMS.getValue(itemIdentifier);
            this.workTimeMult = workTimeMult;
            this.outputMult = outputMult;
        }
    }

    public class PowerGeneratingRecipeData {
        public final float powerOutput;

        private PowerGeneratingRecipeData(float powerOutput) {
            this.powerOutput = powerOutput;
        }
    }

}
