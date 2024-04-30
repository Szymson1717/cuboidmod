package com.cuboiddroid.cuboidmod.modules.collapser.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;

public class QuantumSingularity {
    private static int nextIndex = 0;

    private final int index;
    private final ResourceLocation id;
    protected String name;
    protected int[] colors;

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

    protected void setName(String name) {
        this.name = name;
    }

    protected void setColors(int[] colors) {
        this.colors = colors;
    }
}
