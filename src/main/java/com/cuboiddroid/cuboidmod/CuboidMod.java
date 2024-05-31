package com.cuboiddroid.cuboidmod;

import com.cuboiddroid.cuboidmod.events.LivingDeathEventHandler;
import com.cuboiddroid.cuboidmod.setup.Registration;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(CuboidMod.MOD_ID)
public class CuboidMod
{
    public static final String MOD_ID = "cuboidmod";

    public static final Logger LOGGER = LogManager.getLogger();

    public CuboidMod() {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_CONFIG);

        Registration.register();

        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "-common.toml"));

        Registration.registerConfigs();

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(new LivingDeathEventHandler());
    }

    public static ResourceLocation getModId(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
