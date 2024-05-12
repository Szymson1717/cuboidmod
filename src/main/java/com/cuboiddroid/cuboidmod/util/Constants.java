package com.cuboiddroid.cuboidmod.util;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.resources.ResourceLocation;

public class Constants {
    // recipes
    public static final ResourceLocation RECYCLING = CuboidMod.getModId("recycling");
    public static final ResourceLocation TRANSMUTING = CuboidMod.getModId("transmuting");
    public static final ResourceLocation INSCRIBING = CuboidMod.getModId("inscribing");
    public static final ResourceLocation COLLAPSING = CuboidMod.getModId("collapsing");
    public static final ResourceLocation RESOURCE_GENERATING = CuboidMod.getModId("resource_generating");
    public static final ResourceLocation POWER_GENERATING = CuboidMod.getModId("power_generating");
    public static final ResourceLocation DRYING = CuboidMod.getModId("drying");

    // ----------------------------------------------------------------------------
    // utility methods

    private Constants() {
        throw new IllegalAccessError("Utility class only.");
    }
}
