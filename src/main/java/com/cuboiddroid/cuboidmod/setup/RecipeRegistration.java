package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@ObjectHolder(CuboidMod.MOD_ID)
@Mod.EventBusSubscriber(modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RecipeRegistration {
    @SubscribeEvent
    public static void registerRecipes(RegistryEvent.Register<IRecipeSerializer<?>> event) {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();

        registry.register(new TransmutingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "transmuting"));
        registry.register(new InscribingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "inscribing"));
        registry.register(new RecyclingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "recycling"));
        registry.register(new QuantumCollapsingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "collapsing"));
        registry.register(new ResourceGeneratingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "resource_generating"));
        registry.register(new PowerGeneratingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "power_generating"));
        registry.register(new DryingRecipe.Serializer().setRegistryName(CuboidMod.MOD_ID, "drying"));
    }
}
