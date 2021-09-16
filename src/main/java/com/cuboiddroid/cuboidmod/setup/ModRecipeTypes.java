package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import net.minecraft.item.crafting.IRecipeType;

public class ModRecipeTypes {
    public static final IRecipeType<QuantumCollapsingRecipe> COLLAPSING =
            IRecipeType.register(CuboidMod.MOD_ID + ":collapsing");

    public static final IRecipeType<TransmutingRecipe> TRANSMUTING =
            IRecipeType.register(CuboidMod.MOD_ID + ":transmuting");

    public static final IRecipeType<InscribingRecipe> INSCRIBING =
            IRecipeType.register(CuboidMod.MOD_ID + ":inscribing");

    public static final IRecipeType<RecyclingRecipe> RECYCLING =
            IRecipeType.register(CuboidMod.MOD_ID + ":recycling");

    public static final IRecipeType<DryingRecipe> DRYING =
            IRecipeType.register(CuboidMod.MOD_ID + ":drying");

    public static final IRecipeType<ResourceGeneratingRecipe> RESOURCE_GENERATING =
            IRecipeType.register(CuboidMod.MOD_ID + ":resource_generating");

    public static final IRecipeType<PowerGeneratingRecipe> POWER_GENERATING =
            IRecipeType.register(CuboidMod.MOD_ID + ":power_generating");

    static void register() {}
}
