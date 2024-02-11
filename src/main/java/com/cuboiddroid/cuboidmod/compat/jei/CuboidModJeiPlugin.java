package com.cuboiddroid.cuboidmod.compat.jei;

// import com.cuboiddroid.cuboidmod.Config;
// import com.cuboiddroid.cuboidmod.CuboidMod;
// import com.cuboiddroid.cuboidmod.modules.collapser.inventory.*;
// import com.cuboiddroid.cuboidmod.modules.collapser.screen.*;
// import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
// import com.cuboiddroid.cuboidmod.modules.dryingcupboard.screen.DryingCupboardScreen;
// import com.cuboiddroid.cuboidmod.modules.powergen.inventory.*;
// import com.cuboiddroid.cuboidmod.modules.powergen.screen.*;
// import com.cuboiddroid.cuboidmod.modules.recycler.inventory.MolecularRecyclerContainer;
// import com.cuboiddroid.cuboidmod.modules.recycler.screen.MolecularRecyclerScreen;
// import com.cuboiddroid.cuboidmod.modules.refinedinscriber.inventory.RefinedInscriberContainer;
// import com.cuboiddroid.cuboidmod.modules.refinedinscriber.screen.RefinedInscriberScreen;
// import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.*;
// import com.cuboiddroid.cuboidmod.modules.resourcegen.screen.*;
// import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
// import com.cuboiddroid.cuboidmod.modules.transmuter.screen.QuantumTransmutationChamberScreen;
// import com.cuboiddroid.cuboidmod.setup.ModBlocks;
// import com.cuboiddroid.cuboidmod.setup.ModRecipeTypes;
// import com.cuboiddroid.cuboidmod.util.Constants;
// import mezz.jei.api.IModPlugin;
// import mezz.jei.api.JeiPlugin;
// import mezz.jei.api.helpers.IGuiHelper;
// import mezz.jei.api.registration.*;
// import net.minecraft.client.Minecraft;
// import net.minecraft.world.item.ItemStack;
// import net.minecraft.item.crafting.IRecipe;
// import net.minecraft.item.crafting.IRecipeType;
// import net.minecraft.resources.ResourceLocation;

// import java.util.List;
// import java.util.stream.Collectors;

// @JeiPlugin
public class CuboidModJeiPlugin {//implements IModPlugin {
    // private static final ResourceLocation PLUGIN_UID = CuboidMod.getModId("plugin/main");

    // private static List<IRecipe<?>> getRecipesOfType(IRecipeType<?> recipeType) {
    //     assert Minecraft.getInstance().level != null;
    //     return Minecraft.getInstance().level.getRecipeManager().getRecipes().stream()
    //             .filter(r -> r.getType() == recipeType)
    //             .collect(Collectors.toList());
    // }

    // @Override
    // public ResourceLocation getPluginUid() {
    //     return PLUGIN_UID;
    // }

    // @Override
    // public void registerCategories(IRecipeCategoryRegistration registration) {
    //     if (Config.enableJeiPlugin.get()) {
    //         IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
    //         registration.addRecipeCategories(new RecyclingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new TransmutingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new InscribingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new CollapsingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new ResourceGeneratingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new PowerGeneratingRecipeCategoryJei(guiHelper));
    //         registration.addRecipeCategories(new DryingRecipeCategoryJei(guiHelper));
    //     }
    // }

    // @Override
    // public void registerRecipes(IRecipeRegistration registration) {
    //     if (Config.enableJeiPlugin.get()) {
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.RECYCLING), Constants.RECYCLING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.TRANSMUTING), Constants.TRANSMUTING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.INSCRIBING), Constants.INSCRIBING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.COLLAPSING), Constants.COLLAPSING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.RESOURCE_GENERATING), Constants.RESOURCE_GENERATING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.POWER_GENERATING), Constants.POWER_GENERATING);
    //         registration.addRecipes(getRecipesOfType(ModRecipeTypes.DRYING), Constants.DRYING);
    //     }
    // }

    // @Override
    // public void registerGuiHandlers(IGuiHandlerRegistration registration) {
    //     if (Config.enableJeiPlugin.get() && Config.enableJeiClickArea.get()) {
    //         registration.addRecipeClickArea(MolecularRecyclerScreen.class, 76, 41, 28, 21, Constants.RECYCLING);
    //         registration.addRecipeClickArea(QuantumTransmutationChamberScreen.class, 76, 41, 28, 21, Constants.TRANSMUTING);
    //         registration.addRecipeClickArea(RefinedInscriberScreen.class, 103, 56, 28, 21, Constants.INSCRIBING);
    //         registration.addRecipeClickArea(DryingCupboardScreen.class, 30, 38, 134, 15, Constants.DRYING);

    //         registration.addRecipeClickArea(NotsogudiumQuantumCollapserScreen.class, 76, 41, 28, 21, Constants.COLLAPSING);
    //         registration.addRecipeClickArea(KudbebeddaQuantumCollapserScreen.class, 76, 41, 28, 21, Constants.COLLAPSING);
    //         registration.addRecipeClickArea(NotarfbadiumQuantumCollapserScreen.class, 76, 41, 28, 21, Constants.COLLAPSING);
    //         registration.addRecipeClickArea(WikidiumQuantumCollapserScreen.class, 76, 41, 28, 21, Constants.COLLAPSING);
    //         registration.addRecipeClickArea(ThatlduQuantumCollapserScreen.class, 76, 41, 28, 21, Constants.COLLAPSING);

    //         registration.addRecipeClickArea(NotsogudiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, Constants.RESOURCE_GENERATING);
    //         registration.addRecipeClickArea(KudbebeddaSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, Constants.RESOURCE_GENERATING);
    //         registration.addRecipeClickArea(NotarfbadiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, Constants.RESOURCE_GENERATING);
    //         registration.addRecipeClickArea(WikidiumSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, Constants.RESOURCE_GENERATING);
    //         registration.addRecipeClickArea(ThatlduSingularityResourceGeneratorScreen.class, 52, 41, 10, 18, Constants.RESOURCE_GENERATING);

    //         registration.addRecipeClickArea(NotsogudiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, Constants.POWER_GENERATING);
    //         registration.addRecipeClickArea(KudbebeddaSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, Constants.POWER_GENERATING);
    //         registration.addRecipeClickArea(NotarfbadiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, Constants.POWER_GENERATING);
    //         registration.addRecipeClickArea(WikidiumSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, Constants.POWER_GENERATING);
    //         registration.addRecipeClickArea(ThatlduSingularityPowerGeneratorScreen.class, 44, 41, 10, 18, Constants.POWER_GENERATING);
    //     }
    // }

    // @Override
    // public void registerRecipeTransferHandlers(IRecipeTransferRegistration registration) {
    //     if (Config.enableJeiPlugin.get()) {
    //         registration.addRecipeTransferHandler(MolecularRecyclerContainer.class, Constants.RECYCLING, 0, 7, 7, 36);
    //         registration.addRecipeTransferHandler(QuantumTransmutationChamberContainer.class, Constants.TRANSMUTING, 0, 3, 3, 36);
    //         registration.addRecipeTransferHandler(RefinedInscriberContainer.class, Constants.INSCRIBING, 0, 4, 4, 36);
    //         registration.addRecipeTransferHandler(DryingCupboardContainer.class, Constants.DRYING, 0, 8, 16, 36);

    //         registration.addRecipeTransferHandler(NotsogudiumQuantumCollapserContainer.class, Constants.COLLAPSING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(KudbebeddaQuantumCollapserContainer.class, Constants.COLLAPSING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(NotarfbadiumQuantumCollapserContainer.class, Constants.COLLAPSING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(WikidiumQuantumCollapserContainer.class, Constants.COLLAPSING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(ThatlduQuantumCollapserContainer.class, Constants.COLLAPSING, 0, 2, 2, 36);

    //         registration.addRecipeTransferHandler(NotsogudiumSingularityResourceGeneratorContainer.class, Constants.RESOURCE_GENERATING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(KudbebeddaSingularityResourceGeneratorContainer.class, Constants.RESOURCE_GENERATING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(NotarfbadiumSingularityResourceGeneratorContainer.class, Constants.RESOURCE_GENERATING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(WikidiumSingularityResourceGeneratorContainer.class, Constants.RESOURCE_GENERATING, 0, 2, 2, 36);
    //         registration.addRecipeTransferHandler(ThatlduSingularityResourceGeneratorContainer.class, Constants.RESOURCE_GENERATING, 0, 2, 2, 36);

    //         registration.addRecipeTransferHandler(NotsogudiumSingularityPowerGeneratorContainer.class, Constants.POWER_GENERATING, 0, 1, 1, 36);
    //         registration.addRecipeTransferHandler(KudbebeddaSingularityPowerGeneratorContainer.class, Constants.POWER_GENERATING, 0, 1, 1, 36);
    //         registration.addRecipeTransferHandler(NotarfbadiumSingularityPowerGeneratorContainer.class, Constants.POWER_GENERATING, 0, 1, 1, 36);
    //         registration.addRecipeTransferHandler(WikidiumSingularityPowerGeneratorContainer.class, Constants.POWER_GENERATING, 0, 1, 1, 36);
    //         registration.addRecipeTransferHandler(ThatlduSingularityPowerGeneratorContainer.class, Constants.POWER_GENERATING, 0, 1, 1, 36);
    //     }
    // }

    // @Override
    // public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
    //     if (Config.enableJeiPlugin.get() && Config.enableJeiCatalysts.get()) {
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.MOLECULAR_RECYCLER.get()), Constants.RECYCLING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get()), Constants.TRANSMUTING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.REFINED_INSCRIBER.get()), Constants.INSCRIBING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.DRYING_CUPBOARD.get()), Constants.DRYING);

    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get()), Constants.COLLAPSING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get()), Constants.COLLAPSING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get()), Constants.COLLAPSING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get()), Constants.COLLAPSING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get()), Constants.COLLAPSING);

    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get()), Constants.RESOURCE_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get()), Constants.RESOURCE_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get()), Constants.RESOURCE_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get()), Constants.RESOURCE_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get()), Constants.RESOURCE_GENERATING);

    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get()), Constants.POWER_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get()), Constants.POWER_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get()), Constants.POWER_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get()), Constants.POWER_GENERATING);
    //         registration.addRecipeCatalyst(new ItemStack(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get()), Constants.POWER_GENERATING);
    //     }
    // }
}
