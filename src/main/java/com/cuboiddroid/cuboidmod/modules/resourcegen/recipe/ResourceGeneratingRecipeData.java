package com.cuboiddroid.cuboidmod.modules.resourcegen.recipe;

import net.minecraft.resources.ResourceLocation;

public class ResourceGeneratingRecipeData {
    public final ResourceLocation output;
    public final float workTimeMult;
    public final float outputMult;

    public ResourceGeneratingRecipeData(String identifier, float workTimeMult, float outputMult) {
        this.output = ResourceLocation.tryParse(identifier);
        // this.output = ForgeRegistries.ITEMS.getValue(itemIdentifier);
        this.workTimeMult = workTimeMult;
        this.outputMult = outputMult;
    }
}