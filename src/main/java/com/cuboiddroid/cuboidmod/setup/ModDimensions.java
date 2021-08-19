package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

public class ModDimensions {
    static void register() {}

    public static final RegistryKey<World> CUBOID_OVERWORLD_DIMENSION_WORLD = RegistryKey.create(Registry.DIMENSION_REGISTRY,
            CuboidMod.getModId("cuboid_overworld"));
}
