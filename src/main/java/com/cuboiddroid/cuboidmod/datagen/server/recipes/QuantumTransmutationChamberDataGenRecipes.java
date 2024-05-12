package com.cuboiddroid.cuboidmod.datagen.server.recipes;

import com.cuboiddroid.cuboidmod.datagen.server.ModRecipeProvider;
import com.cuboiddroid.cuboidmod.modules.collapser.block.QuantumCollapserBlockBase;
import com.cuboiddroid.cuboidmod.modules.powergen.block.SingularityPowerGeneratorBlockBase;
import com.cuboiddroid.cuboidmod.modules.transmuter.block.QuantumTransmutationChamberBlock;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Items;

import java.util.function.Consumer;

public class QuantumTransmutationChamberDataGenRecipes extends DataGenRecipesBase {

    public static void build(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer) {
        qtcFromHopperCollapserSingularitiesPowerGenAndRedstone(provider, consumer,
                ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get(),
                ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get(),
                ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(),
                "wikidium");

        qtcFromHopperCollapserSingularitiesPowerGenAndRedstone(provider, consumer,
                ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get(),
                ModBlocks.THATLDU_QUANTUM_COLLAPSER.get(),
                ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get(),
                "thatldu");
    }

    private static void qtcFromHopperCollapserSingularitiesPowerGenAndRedstone(ModRecipeProvider provider, Consumer<FinishedRecipe> consumer, QuantumTransmutationChamberBlock output, QuantumCollapserBlockBase collapser, SingularityPowerGeneratorBlockBase spg, String materialName) {
        ShapedRecipeBuilder.shaped(output)
                .define('@', collapser)
                .define('R', Items.REDSTONE)
                .define('Q', ModTags.Items.QUANTUM_SINGULARITIES)
                .define('Y', Items.HOPPER)
                .define('$', spg)
                .pattern(" Y ")
                .pattern("R@R")
                .pattern("Q$Q")
                .unlockedBy("has_item", provider.hasItem(spg))
                .save(consumer, modId("qtc_from_" + materialName + "_collapser_spg_and_singularities"));
    }

}
