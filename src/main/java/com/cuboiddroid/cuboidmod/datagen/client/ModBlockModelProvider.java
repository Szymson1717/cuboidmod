package com.cuboiddroid.cuboidmod.datagen.client;

import com.cuboiddroid.cuboidmod.CuboidMod;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockModelProvider extends BlockModelProvider {
    

    public ModBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, CuboidMod.MOD_ID, existingFileHelper);
    }

    private static final String[] CUBOID_MATERIALS = {
            "notsogudium", "kudbebedda", "notarfbadium", "wikidium", "thatldu"
    };

    @Override
    public void registerModels() {
        registerTrees();
        registerMachines();
        registerGenerators();
    }

    private void registerTrees() {
        for (int i = 1; i < 16; i++)
            withExistingParent("fiber_optic_tree_" + i, modLoc("block/templates/fiber_optic_tree"))
                .texture("leaves", "block/model/xmas/leaves_" + i);

        withExistingParent("fiber_optic_tree", modLoc("block/templates/fiber_optic_tree"))
            .texture("leaves", "block/model/xmas/leaves");
    }
    
    private void registerMachines() {
        withExistingParent("quantum_transmutation_chamber", modLoc("block/templates/quantum_transmutation_chamber"))
            .texture("core", "block/model/quantum_transmutation_chamber_core");
        withExistingParent("quantum_transmutation_chamber_lit", modLoc("block/templates/quantum_transmutation_chamber"))
            .texture("core", "block/model/quantum_transmutation_chamber_core");

        withExistingParent("molecular_recycler", modLoc("block/templates/molecular_recycler"))
            .texture("core", "block/model/molecular_recycler_core");
        withExistingParent("molecular_recycler_lit", modLoc("block/templates/molecular_recycler"))
            .texture("core", "block/model/molecular_recycler_core_lit");

        withExistingParent("refined_inscriber", modLoc("block/templates/refined_inscriber"))
            .texture("window", "block/model/inscriber_window");
        withExistingParent("refined_inscriber_lit", modLoc("block/templates/refined_inscriber"))
            .texture("window", "block/model/inscriber_window_lit");

        withExistingParent("cryogenic_dimensional_teleporter", modLoc("block/templates/cryogenic_dimensional_teleporter"))
            .texture("pillar", "block/model/cryogenic_dimensional_teleporter_pillar");
        withExistingParent("cryogenic_dimensional_teleporter_lit", modLoc("block/templates/cryogenic_dimensional_teleporter"))
            .texture("pillar", "block/model/cryogenic_dimensional_teleporter_pillar_lit");
    }

    private void registerGenerators() {
        for (String material: CUBOID_MATERIALS) {
            withExistingParent(material + "_singularity_resource_generator", modLoc("block/templates/singularity_resource_generator"))
                .texture("frame", "block/model/" +  material+ "_singularity_resource_generator_casing")
                .texture("core", "block/model/singularity_resource_generator_pillar");
            withExistingParent(material + "_singularity_resource_generator_lit", modLoc("block/templates/singularity_resource_generator"))
                .texture("frame", "block/model/" +  material+ "_singularity_resource_generator_casing")
                .texture("core", "block/model/singularity_resource_generator_pillar_lit");
            
            withExistingParent(material + "_singularity_power_generator", modLoc("block/templates/singularity_power_generator"))
                .texture("frame", "cuboidmod:block/model/" + material + "_singularity_power_gen_frame")
                .texture("core", "block/model/singularity_power_gen_core");
            withExistingParent(material + "_singularity_power_generator_lit", modLoc("block/templates/singularity_power_generator"))
                .texture("frame", "cuboidmod:block/model/" + material + "_singularity_power_gen_frame")
                .texture("core", "block/model/singularity_power_gen_core_lit");

            withExistingParent(material + "_quantum_collapser", modLoc("block/templates/quantum_collapser"))
                .texture("frame", "block/model/" + material + "_quantum_collapser")
                .texture("panel", "block/model/quantum_collapser_panel")
                .texture("particle", "block/" + material + "_break");
            withExistingParent(material + "_quantum_collapser_lit", modLoc("block/templates/quantum_collapser"))
                .texture("frame", "block/model/" + material + "_quantum_collapser")
                .texture("panel", "block/model/quantum_collapser_panel_lit")
                .texture("particle", "block/" + material + "_break");

            withExistingParent(material + "_chest", modLoc("block/templates/chest"))
                .texture("chest", "block/model/" + material + "_chest")
                .texture("break", "block/" + material + "_break");
        }
    }
}
