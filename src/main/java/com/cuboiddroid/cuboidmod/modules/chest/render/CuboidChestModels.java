package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CuboidChestModels {
    public static final ResourceLocation NOTSOGUDIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "model/notsogudium_chest");
    public static final ResourceLocation KUDBEBEDDA_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "model/kudbebedda_chest");
    public static final ResourceLocation NOTARFBADIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "model/notarfbadium_chest");
    public static final ResourceLocation WIKIDIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "model/wikidium_chest");
    public static final ResourceLocation THATLDU_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "model/thatldu_chest");

    public static final ResourceLocation VANILLA_CHEST_LOCATION = new ResourceLocation("entity/chest/normal");

    public static ResourceLocation chooseChestTexture(CuboidChestTypes type) {
        switch (type) {
            case NOTSOGUDIUM:
                return NOTSOGUDIUM_CHEST_LOCATION;
            case KUDBEBEDDA:
                return KUDBEBEDDA_CHEST_LOCATION;
            case NOTARFBADIUM:
                return NOTARFBADIUM_CHEST_LOCATION;
            case WIKIDIUM:
                return WIKIDIUM_CHEST_LOCATION;
            case THATLDU:
                return THATLDU_CHEST_LOCATION;
            default:
                return VANILLA_CHEST_LOCATION;
        }
    }

    @SubscribeEvent
    public static void onStitch(TextureStitchEvent.Pre event) {
        if (!event.getMap().location().equals(Atlases.CHEST_SHEET)) {
            return;
        }

        event.addSprite(NOTSOGUDIUM_CHEST_LOCATION);
        event.addSprite(KUDBEBEDDA_CHEST_LOCATION);
        event.addSprite(NOTARFBADIUM_CHEST_LOCATION);
        event.addSprite(WIKIDIUM_CHEST_LOCATION);
        event.addSprite(THATLDU_CHEST_LOCATION);
    }
}
