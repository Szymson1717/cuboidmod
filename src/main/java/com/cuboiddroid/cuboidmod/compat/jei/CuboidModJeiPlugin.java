package com.cuboiddroid.cuboidmod.compat.jei;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.*;
import com.cuboiddroid.cuboidmod.modules.collapser.recipe.QuantumCollapsingRecipe;
import com.cuboiddroid.cuboidmod.modules.collapser.screen.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.recipe.DryingRecipe;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.screen.DryingCupboardScreen;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.*;
import com.cuboiddroid.cuboidmod.modules.furnace.screen.*;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.powergen.recipe.PowerGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.powergen.screen.*;
import com.cuboiddroid.cuboidmod.modules.recycler.inventory.MolecularRecyclerContainer;
import com.cuboiddroid.cuboidmod.modules.recycler.recipe.RecyclingRecipe;
import com.cuboiddroid.cuboidmod.modules.recycler.screen.MolecularRecyclerScreen;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.inventory.RefinedInscriberContainer;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.recipe.InscribingRecipe;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.screen.RefinedInscriberScreen;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.resourcegen.recipe.ResourceGeneratingRecipe;
import com.cuboiddroid.cuboidmod.modules.resourcegen.screen.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
import com.cuboiddroid.cuboidmod.modules.transmuter.recipe.TransmutingRecipe;
import com.cuboiddroid.cuboidmod.modules.transmuter.screen.QuantumTransmutationChamberScreen;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.RecipeTypes;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.*;
import net.minecraft.client.Minecraft;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

@JeiPlugin
public class CuboidModJeiPlugin implements IModPlugin {
    private static final ResourceLocation PLUGIN_UID = CuboidMod.getModId("plugin/main");

    RecipeType<RecyclingRecipe> RECYCLING = createRecipeType(ModRecipeTypes.RECYCLING);
    RecipeType<TransmutingRecipe> TRANSMUTING = createRecipeType(ModRecipeTypes.TRANSMUTING);
    RecipeType<InscribingRecipe> INSCRIBING = createRecipeType(ModRecipeTypes.INSCRIBING);
    RecipeType<QuantumCollapsingRecipe> COLLAPSING = createRecipeType(ModRecipeTypes.COLLAPSING);
    RecipeType<ResourceGeneratingRecipe> RESOURCE_GENERATING = createRecipeType(ModRecipeTypes.RESOURCE_GENERATING);
    RecipeType<PowerGeneratingRecipe> POWER_GENERATING = createRecipeType(ModRecipeTypes.POWER_GENERATING);
    RecipeType<DryingRecipe> DRYING = createRecipeType(ModRecipeTypes.DRYING);

    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(ModRecipeTypes recipeType) {
        return RecipeType.create(CuboidMod.MOD_ID, recipeType.getName(), recipeType.getType());
    }

    private static <C extends Container, T extends Recipe<C>> List<T> getRecipesOfType(ModRecipeTypes recipeType) {
        assert Minecraft.getInstance().level != null;
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor((net.minecraft.world.item.crafting.RecipeType<T>) recipeType.getRecipeType());
    }

    @Override
    public ResourceLocation getPluginUid() {
        return PLUGIN_UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        if (Config.enableJeiPlugin.get()) {
            IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
            registration.addRecipeCategories(new RecyclingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new TransmutingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new InscribingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new CollapsingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new ResourceGeneratingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new PowerGeneratingRecipeCategoryJei(guiHelper));
            registration.addRecipeCategories(new DryingRecipeCategoryJei(guiHelper));
        }
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        if (Config.enableJeiPlugin.get()) {
            registration.addRecipes(RECYCLING, getRecipesOfType(ModRecipeTypes.RECYCLING));
            registration.addRecipes(TRANSMUTING, getRecipesOfType(ModRecipeTypes.TRANSMUTING));
            registration.addRecipes(INSCRIBING, getRecipesOfType(ModRecipeTypes.INSCRIBING));
            registration.addRecipes(COLLAPSING, getRecipesOfType(ModRecipeTypes.COLLAPSING));
            registration.addRecipes(RESOURCE_GENERATING, getRecipesOfType(ModRecipeTypes.RESOURCE_GENERATING));
            registration.addRecipes(POWER_GENERATING, getRecipesOfType(ModRecipeTypes.POWER_GENERATING));
            registration.addRecipes(DRYING, getRecipesOfType(ModRecipeTypes.DRYING));
        }
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        if (Config.enableJeiPlugin.get() && Config.enableJeiClickArea.get()) {
            registration.addRecipeClickArea(MolecularRecyclerScreen.class, 76, 41, 28, 21, RECYCLING);
            registration.addRecipeClickArea(QuantumTransmutationChamberScreen.class, 76, 41, 28, 21, TRANSMUTING);
            registration.addRecipeClickArea(RefinedInscriberScreen.class, 103, 56, 28, 21, INSCRIBING);
            registration.addRecipeClickArea(DryingCupboardScreen.class, 30, 38, 134, 15, DRYING);

            registration.addRecipeClickArea(NotsogudiumQuantumCollapserScreen.class, 76, 41, 28, 21, COLLAPSING);
            registration.addRecipeClickArea(KudbebeddaQuantumCollapserScreen.class, 76, 41, 28, 21, COLLAPSING);
            registration.addRecipeClickArea(NotarfbadiumQuantumCollapserScreen.class, 76, 41, 28, 21, COLLAPSING);
            registration.addRecipeClickArea(WikidiumQuantumCollapserScreen.class, 76, 41, 28, 21, COLLAPSING);
            registration.addRecipeClickArea(ThatlduQuantumCollapserScreen.class, 76, 41, 28, 21, COLLAPSING);

            registration.addRecipeClickArea(NotsogudiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, RESOURCE_GENERATING);
            registration.addRecipeClickArea(KudbebeddaSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, RESOURCE_GENERATING);
            registration.addRecipeClickArea(NotarfbadiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, RESOURCE_GENERATING);
            registration.addRecipeClickArea(WikidiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, RESOURCE_GENERATING);
            registration.addRecipeClickArea(ThatlduSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, RESOURCE_GENERATING);

            registration.addRecipeClickArea(NotsogudiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, POWER_GENERATING);
            registration.addRecipeClickArea(KudbebeddaSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, POWER_GENERATING);
            registration.addRecipeClickArea(NotarfbadiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, POWER_GENERATING);
            registration.addRecipeClickArea(WikidiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, POWER_GENERATING);
            registration.addRecipeClickArea(ThatlduSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, POWER_GENERATING);

            registration.addRecipeClickArea(NotsogudiumFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeClickArea(KudbebeddaFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeClickArea(NotarfbadiumFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeClickArea(WikidiumFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeClickArea(ThatlduFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
        }
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        if (Config.enableJeiPlugin.get()) {
            registration.addRecipeTransferHandler(MolecularRecyclerContainer.class, RECYCLING, 0, 7, 7, 36);
            registration.addRecipeTransferHandler(QuantumTransmutationChamberContainer.class, TRANSMUTING, 0, 3, 3, 36);
            registration.addRecipeTransferHandler(RefinedInscriberContainer.class, INSCRIBING, 0, 4, 4, 36);
            registration.addRecipeTransferHandler(DryingCupboardContainer.class, DRYING, 0, 8, 16, 36);

            registration.addRecipeTransferHandler(NotsogudiumQuantumCollapserContainer.class, COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(KudbebeddaQuantumCollapserContainer.class, COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(NotarfbadiumQuantumCollapserContainer.class, COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(WikidiumQuantumCollapserContainer.class, COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(ThatlduQuantumCollapserContainer.class, COLLAPSING, 0, 2, 2, 36);

            registration.addRecipeTransferHandler(NotsogudiumSingularityResourceGeneratorContainer.class, RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(KudbebeddaSingularityResourceGeneratorContainer.class, RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(NotarfbadiumSingularityResourceGeneratorContainer.class, RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(WikidiumSingularityResourceGeneratorContainer.class, RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(ThatlduSingularityResourceGeneratorContainer.class, RESOURCE_GENERATING, 0, 2, 2, 36);

            registration.addRecipeTransferHandler(NotsogudiumSingularityPowerGeneratorContainer.class, POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(KudbebeddaSingularityPowerGeneratorContainer.class, POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(NotarfbadiumSingularityPowerGeneratorContainer.class, POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(WikidiumSingularityPowerGeneratorContainer.class, POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(ThatlduSingularityPowerGeneratorContainer.class, POWER_GENERATING, 0, 1, 1, 36);

            registration.addRecipeTransferHandler(NotsogudiumFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(KudbebeddaFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(NotarfbadiumFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(WikidiumFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(ThatlduFurnaceContainer.class, RecipeTypes.SMELTING, 0, 1, 3, 36);

            registration.addRecipeTransferHandler(NotsogudiumFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(KudbebeddaFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(NotarfbadiumFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(WikidiumFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(ThatlduFurnaceContainer.class, RecipeTypes.FUELING, 1, 1, 3, 36);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        if (Config.enableJeiPlugin.get() && Config.enableJeiCatalysts.get()) {
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.MOLECULAR_RECYCLER.get()), RECYCLING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get()), TRANSMUTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.REFINED_INSCRIBER.get()), INSCRIBING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.DRYING_CUPBOARD.get()), DRYING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get()), COLLAPSING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get()), COLLAPSING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get()), COLLAPSING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get()), COLLAPSING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get()), COLLAPSING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get()), RESOURCE_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get()), RESOURCE_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get()), RESOURCE_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get()), RESOURCE_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get()), RESOURCE_GENERATING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get()), POWER_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get()), POWER_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get()), POWER_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get()), POWER_GENERATING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get()), POWER_GENERATING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
        }
    }
}
