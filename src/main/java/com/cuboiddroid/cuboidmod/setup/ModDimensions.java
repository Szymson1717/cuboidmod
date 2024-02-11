package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.level.Level;

public class ModDimensions {
    static void register() {}

    public static final ResourceKey<Level> CUBOID_OVERWORLD_DIMENSION_WORLD = ResourceKey.create(Registry.DIMENSION_REGISTRY,
            CuboidMod.getModId("cuboid_overworld"));
}
