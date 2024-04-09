package com.cuboiddroid.cuboidmod.datagen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.datagen.client.ModBlockStateProvider;
import com.cuboiddroid.cuboidmod.datagen.client.ModItemModelProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModBlockTagsProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModItemTagsProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModLootTableProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    private DataGenerators() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(true, new ModBlockStateProvider(gen, existingFileHelper));
            gen.addProvider(true, new ModItemModelProvider(gen, existingFileHelper));
        }

        if (event.includeServer()) {
            ModBlockTagsProvider blockTags = new ModBlockTagsProvider(gen, existingFileHelper);
            gen.addProvider(true, blockTags);
            gen.addProvider(true, new ModItemTagsProvider(gen, blockTags, existingFileHelper));

            gen.addProvider(true, new ModLootTableProvider(gen));
            gen.addProvider(true, new ModRecipeProvider(gen));
        }
    }
}
