package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers {
    public static final RegistryObject<RecipeSerializer<QuantumCollapsingRecipe>> COLLAPSING =
            Registration.RECIPE_SERIALIZERS.register(QuantumCollapsingRecipe.Serializer.ID, () -> QuantumCollapsingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<TransmutingRecipe>> TRANSMUTING =
            Registration.RECIPE_SERIALIZERS.register(TransmutingRecipe.Serializer.ID, () -> TransmutingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<InscribingRecipe>> INSCRIBING =
            Registration.RECIPE_SERIALIZERS.register(InscribingRecipe.Serializer.ID, () -> InscribingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<DryingRecipe>> DRYING =
            Registration.RECIPE_SERIALIZERS.register(DryingRecipe.Serializer.ID, () -> DryingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<RecyclingRecipe>> RECYCLING =
            Registration.RECIPE_SERIALIZERS.register(RecyclingRecipe.Serializer.ID, () -> RecyclingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<ResourceGeneratingRecipe>> RESOURCE_GENERATING =
            Registration.RECIPE_SERIALIZERS.register(ResourceGeneratingRecipe.Serializer.ID, () -> ResourceGeneratingRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PowerGeneratingRecipe>> POWER_GENERATING =
            Registration.RECIPE_SERIALIZERS.register(PowerGeneratingRecipe.Serializer.ID, () -> PowerGeneratingRecipe.Serializer.INSTANCE);

    static void register() {}
}
