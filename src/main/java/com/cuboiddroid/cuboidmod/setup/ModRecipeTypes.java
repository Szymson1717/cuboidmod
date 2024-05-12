package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipeTypes {
    public static final RecipeType<QuantumCollapsingRecipe> COLLAPSING =
            RecipeType.register(CuboidMod.MOD_ID + ":collapsing");

    public static final RecipeType<TransmutingRecipe> TRANSMUTING =
            RecipeType.register(CuboidMod.MOD_ID + ":transmuting");

    public static final RecipeType<InscribingRecipe> INSCRIBING =
            RecipeType.register(CuboidMod.MOD_ID + ":inscribing");

    public static final RecipeType<RecyclingRecipe> RECYCLING =
            RecipeType.register(CuboidMod.MOD_ID + ":recycling");

    public static final RecipeType<DryingRecipe> DRYING =
            RecipeType.register(CuboidMod.MOD_ID + ":drying");

    public static final RecipeType<ResourceGeneratingRecipe> RESOURCE_GENERATING =
            RecipeType.register(CuboidMod.MOD_ID + ":resource_generating");

    public static final RecipeType<PowerGeneratingRecipe> POWER_GENERATING =
            RecipeType.register(CuboidMod.MOD_ID + ":power_generating");

    static void register() {}
}
