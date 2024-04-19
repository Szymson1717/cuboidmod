package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

@Mod(CuboidMod.MOD_ID)
@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistration {
    @SubscribeEvent
    public static void registerRecipes(RegisterEvent event) {
        event.register(ForgeRegistries.Keys.RECIPE_SERIALIZERS, helper -> {
            helper.register(CuboidMod.getModId("transmuting"), new TransmutingRecipe.Serializer());
            helper.register(CuboidMod.getModId("inscribing"), new InscribingRecipe.Serializer());
            helper.register(CuboidMod.getModId("recycling"), new RecyclingRecipe.Serializer());
            helper.register(CuboidMod.getModId("collapsing"), new QuantumCollapsingRecipe.Serializer());
            helper.register(CuboidMod.getModId("resource_generating"), new ResourceGeneratingRecipe.Serializer());
            helper.register(CuboidMod.getModId("power_generating"), new PowerGeneratingRecipe.Serializer());
            helper.register(CuboidMod.getModId("drying"), new DryingRecipe.Serializer());
        });
    }
}
