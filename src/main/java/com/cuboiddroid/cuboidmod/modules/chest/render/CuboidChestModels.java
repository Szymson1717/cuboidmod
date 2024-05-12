package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CuboidChestModels {
    public static final ResourceLocation NOTSOGUDIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "entity/chest/notsogudium");
    public static final ResourceLocation KUDBEBEDDA_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "entity/chest/kudbebedda");
    public static final ResourceLocation NOTARFBADIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "entity/chest/notarfbadium");
    public static final ResourceLocation WIKIDIUM_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "entity/chest/wikidium");
    public static final ResourceLocation THATLDU_CHEST_LOCATION = new ResourceLocation(CuboidMod.MOD_ID, "entity/chest/thatldu");

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

    // @SubscribeEvent
    // public static void onStitch(TextureStitchEvent.Post event) {
    //     if (!event.getAtlas().location().equals(Sheets.CHEST_SHEET)) {
    //         return;
    //     }

    //     event.addSprite(NOTSOGUDIUM_CHEST_LOCATION);
    //     event.addSprite(KUDBEBEDDA_CHEST_LOCATION);
    //     event.addSprite(NOTARFBADIUM_CHEST_LOCATION);
    //     event.addSprite(WIKIDIUM_CHEST_LOCATION);
    //     event.addSprite(THATLDU_CHEST_LOCATION);
    // }
}
