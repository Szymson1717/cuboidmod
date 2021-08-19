package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class QuantumSingularity {
    private static int nextIndex = 0;

    private final int index;
    private final ResourceLocation id;
    private final String name;
    private final int[] colors;

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

    public ITextComponent getDisplayName() {
        return new TranslationTextComponent(this.name);
    }
}
