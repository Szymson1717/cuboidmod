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
import com.cuboiddroid.cuboidmod.setup.ModContainers;
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

    static RecipeType<RecyclingRecipe> RECYCLING = createRecipeType(RecyclingRecipe.Type.ID, RecyclingRecipe.class);
    static RecipeType<TransmutingRecipe> TRANSMUTING = createRecipeType(TransmutingRecipe.Type.ID, TransmutingRecipe.class);
    static RecipeType<InscribingRecipe> INSCRIBING = createRecipeType(InscribingRecipe.Type.ID, InscribingRecipe.class);
    static RecipeType<QuantumCollapsingRecipe> COLLAPSING = createRecipeType(QuantumCollapsingRecipe.Type.ID, QuantumCollapsingRecipe.class);
    static RecipeType<ResourceGeneratingRecipe> RESOURCE_GENERATING = createRecipeType(ResourceGeneratingRecipe.Type.ID, ResourceGeneratingRecipe.class);
    static RecipeType<PowerGeneratingRecipe> POWER_GENERATING = createRecipeType(PowerGeneratingRecipe.Type.ID, PowerGeneratingRecipe.class);
    static RecipeType<DryingRecipe> DRYING = createRecipeType(DryingRecipe.Type.ID, DryingRecipe.class);

    private static <T extends Recipe<?>> RecipeType<T> createRecipeType(String name, Class<T> recipeType) {
        return RecipeType.create(CuboidMod.MOD_ID, name, recipeType);
    }

    private static <C extends Container, T extends Recipe<C>> List<T> getRecipesOfType(net.minecraft.world.item.crafting.RecipeType<T> recipeType) {
        assert Minecraft.getInstance().level != null;
        return Minecraft.getInstance().level.getRecipeManager().getAllRecipesFor(recipeType);
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
            registration.addRecipes(RECYCLING, getRecipesOfType(RecyclingRecipe.Type.INSTANCE));
            registration.addRecipes(TRANSMUTING, getRecipesOfType(TransmutingRecipe.Type.INSTANCE));
            registration.addRecipes(INSCRIBING, getRecipesOfType(InscribingRecipe.Type.INSTANCE));
            registration.addRecipes(COLLAPSING, getRecipesOfType(QuantumCollapsingRecipe.Type.INSTANCE));
            registration.addRecipes(RESOURCE_GENERATING, getRecipesOfType(ResourceGeneratingRecipe.Type.INSTANCE));
            registration.addRecipes(POWER_GENERATING, getRecipesOfType(PowerGeneratingRecipe.Type.INSTANCE));
            registration.addRecipes(DRYING, getRecipesOfType(DryingRecipe.Type.INSTANCE));
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
            registration.addRecipeClickArea(EumusFurnaceScreen.class, 78, 32, 28, 23, RecipeTypes.SMELTING, RecipeTypes.FUELING);
        }
    }

    @Override
    public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
        if (Config.enableJeiPlugin.get()) {
            registration.addRecipeTransferHandler(MolecularRecyclerContainer.class, ModContainers.MOLECULAR_RECYCLER.get(), RECYCLING, 0, 7, 7, 36);
            registration.addRecipeTransferHandler(QuantumTransmutationChamberContainer.class, ModContainers.QUANTUM_TRANSMUTATION_CHAMBER.get(), TRANSMUTING, 0, 3, 3, 36);
            registration.addRecipeTransferHandler(RefinedInscriberContainer.class, ModContainers.REFINED_INSCRIBER.get(), INSCRIBING, 0, 4, 4, 36);
            registration.addRecipeTransferHandler(DryingCupboardContainer.class, ModContainers.DRYING_CUPBOARD.get(), DRYING, 0, 8, 16, 36);

            registration.addRecipeTransferHandler(NotsogudiumQuantumCollapserContainer.class, ModContainers.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(), COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(KudbebeddaQuantumCollapserContainer.class, ModContainers.KUDBEBEDDA_QUANTUM_COLLAPSER.get(), COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(NotarfbadiumQuantumCollapserContainer.class, ModContainers.NOTARFBADIUM_QUANTUM_COLLAPSER.get(), COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(WikidiumQuantumCollapserContainer.class, ModContainers.WIKIDIUM_QUANTUM_COLLAPSER.get(), COLLAPSING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(ThatlduQuantumCollapserContainer.class, ModContainers.THATLDU_QUANTUM_COLLAPSER.get(), COLLAPSING, 0, 2, 2, 36);

            registration.addRecipeTransferHandler(NotsogudiumSingularityResourceGeneratorContainer.class, ModContainers.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(KudbebeddaSingularityResourceGeneratorContainer.class, ModContainers.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(), RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(NotarfbadiumSingularityResourceGeneratorContainer.class, ModContainers.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(), RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(WikidiumSingularityResourceGeneratorContainer.class, ModContainers.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), RESOURCE_GENERATING, 0, 2, 2, 36);
            registration.addRecipeTransferHandler(ThatlduSingularityResourceGeneratorContainer.class, ModContainers.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(), RESOURCE_GENERATING, 0, 2, 2, 36);

            registration.addRecipeTransferHandler(NotsogudiumSingularityPowerGeneratorContainer.class, ModContainers.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(), POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(KudbebeddaSingularityPowerGeneratorContainer.class, ModContainers.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(), POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(NotarfbadiumSingularityPowerGeneratorContainer.class, ModContainers.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(WikidiumSingularityPowerGeneratorContainer.class, ModContainers.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(), POWER_GENERATING, 0, 1, 1, 36);
            registration.addRecipeTransferHandler(ThatlduSingularityPowerGeneratorContainer.class, ModContainers.THATLDU_SINGULARITY_POWER_GENERATOR.get(), POWER_GENERATING, 0, 1, 1, 36);

            registration.addRecipeTransferHandler(NotsogudiumFurnaceContainer.class, ModContainers.NOTSOGUDIUM_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(KudbebeddaFurnaceContainer.class, ModContainers.KUDBEBEDDA_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(NotarfbadiumFurnaceContainer.class, ModContainers.NOTARFBADIUM_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(WikidiumFurnaceContainer.class, ModContainers.WIKIDIUM_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(ThatlduFurnaceContainer.class, ModContainers.THATLDU_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);
            registration.addRecipeTransferHandler(EumusFurnaceContainer.class, ModContainers.EUMUS_FURNACE.get(), RecipeTypes.SMELTING, 0, 1, 3, 36);

            registration.addRecipeTransferHandler(NotsogudiumFurnaceContainer.class, ModContainers.NOTSOGUDIUM_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(KudbebeddaFurnaceContainer.class, ModContainers.KUDBEBEDDA_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(NotarfbadiumFurnaceContainer.class, ModContainers.NOTARFBADIUM_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(WikidiumFurnaceContainer.class, ModContainers.WIKIDIUM_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(ThatlduFurnaceContainer.class, ModContainers.THATLDU_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
            registration.addRecipeTransferHandler(EumusFurnaceContainer.class, ModContainers.EUMUS_FURNACE.get(), RecipeTypes.FUELING, 1, 1, 3, 36);
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
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.EUMUS_FURNACE.get()), RecipeTypes.SMELTING, RecipeTypes.FUELING);

            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
            registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_CRAFTING_TABLE.get()), RecipeTypes.CRAFTING);
        }
    }
}
