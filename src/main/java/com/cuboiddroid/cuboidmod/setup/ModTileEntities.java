package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.cdt.tile.CryogenicDimensionalTeleporterTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.*;
import com.cuboiddroid.cuboidmod.modules.collapser.tile.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
import com.cuboiddroid.cuboidmod.modules.furnace.tile.*;
import com.cuboiddroid.cuboidmod.modules.powergen.tile.*;
import com.cuboiddroid.cuboidmod.modules.recycler.tile.MolecularRecyclerTileEntity;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.tile.RefinedInscriberTileEntity;
import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
import com.cuboiddroid.cuboidmod.modules.xmas.tile.FiberOpticTreeTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityType.BlockEntitySupplier;
import net.minecraftforge.fmllegacy.RegistryObject;

public class ModTileEntities {
    public static void register() {
    }

    // Fiber Optic Tree

    public static final RegistryObject<BlockEntityType<FiberOpticTreeTileEntity>> FIBER_OPTIC_TREE = register(
            "fiber_optic_tree", FiberOpticTreeTileEntity::new, ModBlocks.FIBER_OPTIC_TREE);

    // Cryogenic Dimensional Teleporter

    public static final RegistryObject<BlockEntityType<CryogenicDimensionalTeleporterTileEntity>> CRYOGENIC_DIMENSIONAL_TELEPORTER = register(
            "cryogenic_dimensional_teleporter", CryogenicDimensionalTeleporterTileEntity::new, ModBlocks.CRYOGENIC_DIMENSIONAL_TELEPORTER);

    // Drying Cupboard

    public static final RegistryObject<BlockEntityType<DryingCupboardTileEntity>> DRYING_CUPBOARD = register(
            "drying_cupboard", DryingCupboardTileEntity::new, ModBlocks.DRYING_CUPBOARD);

    // Molecular Recycler

    public static final RegistryObject<BlockEntityType<MolecularRecyclerTileEntity>> MOLECULAR_RECYCLER = register(
            "molecular_recycler", MolecularRecyclerTileEntity::new, ModBlocks.MOLECULAR_RECYCLER);

    // Quantum transmutation chamber

    public static final RegistryObject<BlockEntityType<QuantumTransmutationChamberTileEntity>> QUANTUM_TRANSMUTATION_CHAMBER = register(
            "quantum_transmutation_chamber", QuantumTransmutationChamberTileEntity::new, ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER);

    // Refined Inscriber

    public static final RegistryObject<BlockEntityType<RefinedInscriberTileEntity>> REFINED_INSCRIBER = register(
            "refined_inscriber", RefinedInscriberTileEntity::new, ModBlocks.REFINED_INSCRIBER);

    // Quantum collapsers

    public static final RegistryObject<BlockEntityType<NotsogudiumQuantumCollapserTileEntity>> NOTSOGUDIUM_QUANTUM_COLLAPSER = register(
            "notsogudium_quantum_collapser", NotsogudiumQuantumCollapserTileEntity::new, ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<BlockEntityType<KudbebeddaQuantumCollapserTileEntity>> KUDBEBEDDA_QUANTUM_COLLAPSER = register(
            "kudbebedda_quantum_collapser", KudbebeddaQuantumCollapserTileEntity::new, ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER);

    public static final RegistryObject<BlockEntityType<NotarfbadiumQuantumCollapserTileEntity>> NOTARFBADIUM_QUANTUM_COLLAPSER = register(
            "notarfbadium_quantum_collapser", NotarfbadiumQuantumCollapserTileEntity::new, ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<BlockEntityType<WikidiumQuantumCollapserTileEntity>> WIKIDIUM_QUANTUM_COLLAPSER = register(
            "wikidium_quantum_collapser", WikidiumQuantumCollapserTileEntity::new, ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<BlockEntityType<ThatlduQuantumCollapserTileEntity>> THATLDU_QUANTUM_COLLAPSER = register(
            "thatldu_quantum_collapser", ThatlduQuantumCollapserTileEntity::new, ModBlocks.THATLDU_QUANTUM_COLLAPSER);

    // Singularity Resource Generators

    public static final RegistryObject<BlockEntityType<NotsogudiumSingularityResourceGeneratorTileEntity>> NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "notsogudium_singularity_resource_generator", NotsogudiumSingularityResourceGeneratorTileEntity::new, ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<BlockEntityType<KudbebeddaSingularityResourceGeneratorTileEntity>> KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR = register(
            "kudbebedda_singularity_resource_generator", KudbebeddaSingularityResourceGeneratorTileEntity::new, ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<BlockEntityType<NotarfbadiumSingularityResourceGeneratorTileEntity>> NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "notarfbadium_singularity_resource_generator", NotarfbadiumSingularityResourceGeneratorTileEntity::new, ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<BlockEntityType<WikidiumSingularityResourceGeneratorTileEntity>> WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "wikidium_singularity_resource_generator", WikidiumSingularityResourceGeneratorTileEntity::new, ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<BlockEntityType<ThatlduSingularityResourceGeneratorTileEntity>> THATLDU_SINGULARITY_RESOURCE_GENERATOR = register(
            "thatldu_singularity_resource_generator", ThatlduSingularityResourceGeneratorTileEntity::new, ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR);

    // Singularity Power Generators

    public static final RegistryObject<BlockEntityType<NotsogudiumSingularityPowerGeneratorTileEntity>> NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR = register(
            "notsogudium_singularity_power_generator", NotsogudiumSingularityPowerGeneratorTileEntity::new, ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<BlockEntityType<KudbebeddaSingularityPowerGeneratorTileEntity>> KUDBEBEDDA_SINGULARITY_POWER_GENERATOR = register(
            "kudbebedda_singularity_power_generator", KudbebeddaSingularityPowerGeneratorTileEntity::new, ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<BlockEntityType<NotarfbadiumSingularityPowerGeneratorTileEntity>> NOTARFBADIUM_SINGULARITY_POWER_GENERATOR = register(
            "notarfbadium_singularity_power_generator", NotarfbadiumSingularityPowerGeneratorTileEntity::new, ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<BlockEntityType<WikidiumSingularityPowerGeneratorTileEntity>> WIKIDIUM_SINGULARITY_POWER_GENERATOR = register(
            "wikidium_singularity_power_generator", WikidiumSingularityPowerGeneratorTileEntity::new, ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<BlockEntityType<ThatlduSingularityPowerGeneratorTileEntity>> THATLDU_SINGULARITY_POWER_GENERATOR = register(
            "thatldu_singularity_power_generator", ThatlduSingularityPowerGeneratorTileEntity::new, ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR);

    // chests
    public static final RegistryObject<BlockEntityType<NotsogudiumChestTileEntity>> NOTSOGUDIUM_CHEST = register(
            "notsogudium_chest", NotsogudiumChestTileEntity::new, ModBlocks.NOTSOGUDIUM_CHEST);

    public static final RegistryObject<BlockEntityType<KudbebeddaChestTileEntity>> KUDBEBEDDA_CHEST = register(
            "kudbebedda_chest", KudbebeddaChestTileEntity::new, ModBlocks.KUDBEBEDDA_CHEST);

    public static final RegistryObject<BlockEntityType<NotarfbadiumChestTileEntity>> NOTARFBADIUM_CHEST = register(
            "notarfbadium_chest", NotarfbadiumChestTileEntity::new, ModBlocks.NOTARFBADIUM_CHEST);

    public static final RegistryObject<BlockEntityType<WikidiumChestTileEntity>> WIKIDIUM_CHEST = register(
            "wikidium_chest", WikidiumChestTileEntity::new, ModBlocks.WIKIDIUM_CHEST);

    public static final RegistryObject<BlockEntityType<ThatlduChestTileEntity>> THATLDU_CHEST = register(
            "thatldu_chest", ThatlduChestTileEntity::new, ModBlocks.THATLDU_CHEST);

    // furnaces
    public static final RegistryObject<BlockEntityType<NotsogudiumFurnaceTileEntity>> NOTSOGUDIUM_FURNACE = register(
            "notsogudium_furnace", NotsogudiumFurnaceTileEntity::new, ModBlocks.NOTSOGUDIUM_FURNACE);

    public static final RegistryObject<BlockEntityType<KudbebeddaFurnaceTileEntity>> KUDBEBEDDA_FURNACE = register(
            "kudbebedda_furnace", KudbebeddaFurnaceTileEntity::new, ModBlocks.KUDBEBEDDA_FURNACE);

    public static final RegistryObject<BlockEntityType<NotarfbadiumFurnaceTileEntity>> NOTARFBADIUM_FURNACE = register(
            "notarfbadium_furnace", NotarfbadiumFurnaceTileEntity::new, ModBlocks.NOTARFBADIUM_FURNACE);

    public static final RegistryObject<BlockEntityType<WikidiumFurnaceTileEntity>> WIKIDIUM_FURNACE = register(
            "wikidium_furnace", WikidiumFurnaceTileEntity::new, ModBlocks.WIKIDIUM_FURNACE);

    public static final RegistryObject<BlockEntityType<ThatlduFurnaceTileEntity>> THATLDU_FURNACE = register(
            "thatldu_furnace", ThatlduFurnaceTileEntity::new, ModBlocks.THATLDU_FURNACE);

    public static final RegistryObject<BlockEntityType<EumusFurnaceTileEntity>> EUMUS_FURNACE = register(
            "eumus_furnace", EumusFurnaceTileEntity::new, ModBlocks.EUMUS_FURNACE);


    private static <T extends BlockEntity> RegistryObject<BlockEntityType<T>> register(String name, BlockEntitySupplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.BLOCK_ENTITIES.register(name, () -> {
            //noinspection ConstantConditions - null in build
            return BlockEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}