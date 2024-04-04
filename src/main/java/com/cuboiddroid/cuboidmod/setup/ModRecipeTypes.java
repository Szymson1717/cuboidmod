package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeTypes {
    public static final RegistryObject<RecipeType<QuantumCollapsingRecipe>> COLLAPSING = register("collapsing");
    public static final RegistryObject<RecipeType<TransmutingRecipe>> TRANSMUTING = register("transmuting");
    public static final RegistryObject<RecipeType<InscribingRecipe>> INSCRIBING = register("inscribing");
    public static final RegistryObject<RecipeType<RecyclingRecipe>> RECYCLING = register("recycling");
    public static final RegistryObject<RecipeType<DryingRecipe>> DRYING = register("drying");
    public static final RegistryObject<RecipeType<ResourceGeneratingRecipe>> RESOURCE_GENERATING = register("resource_generating");
    public static final RegistryObject<RecipeType<PowerGeneratingRecipe>> POWER_GENERATING = register("power_generating");

    private static <T extends Recipe<?>> RegistryObject<RecipeType<T>> register(String name) {
        return Registration.RECIPE_TYPES.register(name, () -> new RecipeType<T>() {});
    }

    static void register() {}
}
