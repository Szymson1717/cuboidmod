package com.cuboiddroid.cuboidmod.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.datagen.client.ModBlockStateProvider;
import com.cuboiddroid.cuboidmod.datagen.client.ModItemModelProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModBlockTagsProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModItemTagsProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModLootTableProvider;
import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider.SubProviderEntry;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.data.event.GatherDataEvent;

@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PackOutputs {
    private PackOutputs() {}

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput output = gen.getPackOutput();
        CompletableFuture<Provider> lookup = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        if (event.includeClient()) {
            gen.addProvider(true, new ModBlockStateProvider(output, existingFileHelper));
            gen.addProvider(true, new ModItemModelProvider(output, existingFileHelper));
        }

        if (event.includeServer()) {
            ModBlockTagsProvider blockTags = new ModBlockTagsProvider(output, lookup, existingFileHelper);
            gen.addProvider(true, blockTags);
            gen.addProvider(true, new ModItemTagsProvider(output, lookup, blockTags.contentsGetter(), existingFileHelper));

            gen.addProvider(true, new ModLootTableProvider(output, Collections.emptySet(), List.of(
                    new SubProviderEntry(ModLootTableProvider.ModBlockLootTables::new, LootContextParamSets.BLOCK))));
            gen.addProvider(true, new ModRecipeProvider(output));
        }
    }
}
