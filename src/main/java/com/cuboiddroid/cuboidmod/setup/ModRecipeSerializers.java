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

    public static final RegistryObject<RecipeSerializer<?>> COLLAPSING =
            Registration.RECIPE_SERIALIZERS.register("collapsing",
                    QuantumCollapsingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> TRANSMUTING =
            Registration.RECIPE_SERIALIZERS.register("transmuting",
                    TransmutingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> INSCRIBING =
            Registration.RECIPE_SERIALIZERS.register("inscribing",
                    InscribingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> DRYING =
            Registration.RECIPE_SERIALIZERS.register("drying",
                    DryingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> RECYCLING =
            Registration.RECIPE_SERIALIZERS.register("recycling",
                    RecyclingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> RESOURCE_GENERATING =
            Registration.RECIPE_SERIALIZERS.register("resource_generating",
                    ResourceGeneratingRecipe.Serializer::new);

    public static final RegistryObject<RecipeSerializer<?>> POWER_GENERATING =
            Registration.RECIPE_SERIALIZERS.register("power_generating",
                    PowerGeneratingRecipe.Serializer::new);

    static void register() {}
}
