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

public enum ModRecipeTypes {
    COLLAPSING("collapsing", QuantumCollapsingRecipe.class),
    TRANSMUTING("transmuting", TransmutingRecipe.class),
    INSCRIBING("inscribing", InscribingRecipe.class),
    RECYCLING("recycling", RecyclingRecipe.class),
    DRYING("drying", DryingRecipe.class),
    RESOURCE_GENERATING("resource_generating", ResourceGeneratingRecipe.class),
    POWER_GENERATING("power_generating", PowerGeneratingRecipe.class);

    private final String name;
    private final Class<?> type;
    private final RegistryObject<RecipeType<Recipe<?>>> registry;

    <T extends Recipe<?>> ModRecipeTypes(String name, Class<T> recipeType) {
        this.name = name;
        this.type = recipeType;

        this.registry = Registration.RECIPE_TYPES.register(this.getName(), () -> new RecipeType<Recipe<?>>() {});
    }

    public String getName() {
        return name;
    }

    public <T extends RecipeType<Recipe<?>>> RegistryObject<T> getRegistry() {
        return (RegistryObject<T>) registry;
    }

    public <T extends Recipe<?>> RecipeType<T> getRecipeType() {
        return (RecipeType<T>) registry.get();
    }

    public <T extends Recipe<?>> Class<T> getType() {
        return (Class<T>) type;
    }

    static void register() {}
}
