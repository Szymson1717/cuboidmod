package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;

public class ModRecipes {

    private ModRecipes() {
    }

    public static class Types {
        public static final IRecipeType<QuantumCollapsingRecipe> COLLAPSING =
                IRecipeType.register(CuboidMod.MOD_ID + ":collapsing");

        public static final IRecipeType<TransmutingRecipe> TRANSMUTING =
                IRecipeType.register(CuboidMod.MOD_ID + ":transmuting");

        public static final IRecipeType<RecyclingRecipe> RECYCLING =
                IRecipeType.register(CuboidMod.MOD_ID + ":recycling");

        public static final IRecipeType<DryingRecipe> DRYING =
                IRecipeType.register(CuboidMod.MOD_ID + ":drying");

        public static final IRecipeType<ResourceGeneratingRecipe> RESOURCE_GENERATING =
                IRecipeType.register(CuboidMod.MOD_ID + ":resource_generating");

        public static final IRecipeType<PowerGeneratingRecipe> POWER_GENERATING =
                IRecipeType.register(CuboidMod.MOD_ID + ":power_generating");

        private Types() {
        }
    }

    public static class Serializers {
        public static final RegistryObject<IRecipeSerializer<?>> COLLAPSING =
                Registration.RECIPE_SERIALIZERS.register("collapsing",
                        QuantumCollapsingRecipe.Serializer::new);

        public static final RegistryObject<IRecipeSerializer<?>> TRANSMUTING =
                Registration.RECIPE_SERIALIZERS.register("transmuting",
                        TransmutingRecipe.Serializer::new);

        public static final RegistryObject<IRecipeSerializer<?>> DRYING =
                Registration.RECIPE_SERIALIZERS.register("drying",
                        DryingRecipe.Serializer::new);

        public static final RegistryObject<IRecipeSerializer<?>> RECYCLING =
                Registration.RECIPE_SERIALIZERS.register("recycling",
                        RecyclingRecipe.Serializer::new);

        public static final RegistryObject<IRecipeSerializer<?>> RESOURCE_GENERATING =
                Registration.RECIPE_SERIALIZERS.register("resource_generating",
                        ResourceGeneratingRecipe.Serializer::new);

        public static final RegistryObject<IRecipeSerializer<?>> POWER_GENERATING =
                Registration.RECIPE_SERIALIZERS.register("power_generating",
                        PowerGeneratingRecipe.Serializer::new);

        private Serializers() {
        }
    }

    static void register() {}
}
