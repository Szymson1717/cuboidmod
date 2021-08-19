package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.cdt.tile.CryogenicDimensionalTeleporterTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.*;
import com.cuboiddroid.cuboidmod.modules.collapser.tile.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
import com.cuboiddroid.cuboidmod.modules.furnace.tile.*;
import com.cuboiddroid.cuboidmod.modules.powergen.tile.*;
import com.cuboiddroid.cuboidmod.modules.recycler.tile.MolecularRecyclerTileEntity;
import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModTileEntities {
    public static void register() {
    }

    // Cryogenic Dimensional Teleporter

    public static final RegistryObject<TileEntityType<CryogenicDimensionalTeleporterTileEntity>> CRYOGENIC_DIMENSIONAL_TELEPORTER = register(
            "cryogenic_dimensional_teleporter", CryogenicDimensionalTeleporterTileEntity::new, ModBlocks.CRYOGENIC_DIMENSIONAL_TELEPORTER);

    // Drying Cupboard

    public static final RegistryObject<TileEntityType<DryingCupboardTileEntity>> DRYING_CUPBOARD = register(
            "drying_cupboard", DryingCupboardTileEntity::new, ModBlocks.DRYING_CUPBOARD);

    // Molecular Recycler

    public static final RegistryObject<TileEntityType<MolecularRecyclerTileEntity>> MOLECULAR_RECYCLER = register(
            "molecular_recycler", MolecularRecyclerTileEntity::new, ModBlocks.MOLECULAR_RECYCLER);

    // Quantum transmutation chamber

    public static final RegistryObject<TileEntityType<QuantumTransmutationChamberTileEntity>> QUANTUM_TRANSMUTATION_CHAMBER = register(
            "quantum_transmutation_chamber", QuantumTransmutationChamberTileEntity::new, ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER);

    // Quantum collapsers

    public static final RegistryObject<TileEntityType<NotsogudiumQuantumCollapserTileEntity>> NOTSOGUDIUM_QUANTUM_COLLAPSER = register(
            "notsogudium_quantum_collapser", NotsogudiumQuantumCollapserTileEntity::new, ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<TileEntityType<KudbebeddaQuantumCollapserTileEntity>> KUDBEBEDDA_QUANTUM_COLLAPSER = register(
            "kudbebedda_quantum_collapser", KudbebeddaQuantumCollapserTileEntity::new, ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER);

    public static final RegistryObject<TileEntityType<NotarfbadiumQuantumCollapserTileEntity>> NOTARFBADIUM_QUANTUM_COLLAPSER = register(
            "notarfbadium_quantum_collapser", NotarfbadiumQuantumCollapserTileEntity::new, ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<TileEntityType<WikidiumQuantumCollapserTileEntity>> WIKIDIUM_QUANTUM_COLLAPSER = register(
            "wikidium_quantum_collapser", WikidiumQuantumCollapserTileEntity::new, ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER);

    public static final RegistryObject<TileEntityType<ThatlduQuantumCollapserTileEntity>> THATLDU_QUANTUM_COLLAPSER = register(
            "thatldu_quantum_collapser", ThatlduQuantumCollapserTileEntity::new, ModBlocks.THATLDU_QUANTUM_COLLAPSER);

    // Singularity Resource Generators

    public static final RegistryObject<TileEntityType<NotsogudiumSingularityResourceGeneratorTileEntity>> NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "notsogudium_singularity_resource_generator", NotsogudiumSingularityResourceGeneratorTileEntity::new, ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<TileEntityType<KudbebeddaSingularityResourceGeneratorTileEntity>> KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR = register(
            "kudbebedda_singularity_resource_generator", KudbebeddaSingularityResourceGeneratorTileEntity::new, ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<TileEntityType<NotarfbadiumSingularityResourceGeneratorTileEntity>> NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "notarfbadium_singularity_resource_generator", NotarfbadiumSingularityResourceGeneratorTileEntity::new, ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<TileEntityType<WikidiumSingularityResourceGeneratorTileEntity>> WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
            "wikidium_singularity_resource_generator", WikidiumSingularityResourceGeneratorTileEntity::new, ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR);

    public static final RegistryObject<TileEntityType<ThatlduSingularityResourceGeneratorTileEntity>> THATLDU_SINGULARITY_RESOURCE_GENERATOR = register(
            "thatldu_singularity_resource_generator", ThatlduSingularityResourceGeneratorTileEntity::new, ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR);

    // Singularity Power Generators

    public static final RegistryObject<TileEntityType<NotsogudiumSingularityPowerGeneratorTileEntity>> NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR = register(
            "notsogudium_singularity_power_generator", NotsogudiumSingularityPowerGeneratorTileEntity::new, ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<TileEntityType<KudbebeddaSingularityPowerGeneratorTileEntity>> KUDBEBEDDA_SINGULARITY_POWER_GENERATOR = register(
            "kudbebedda_singularity_power_generator", KudbebeddaSingularityPowerGeneratorTileEntity::new, ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<TileEntityType<NotarfbadiumSingularityPowerGeneratorTileEntity>> NOTARFBADIUM_SINGULARITY_POWER_GENERATOR = register(
            "notarfbadium_singularity_power_generator", NotarfbadiumSingularityPowerGeneratorTileEntity::new, ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<TileEntityType<WikidiumSingularityPowerGeneratorTileEntity>> WIKIDIUM_SINGULARITY_POWER_GENERATOR = register(
            "wikidium_singularity_power_generator", WikidiumSingularityPowerGeneratorTileEntity::new, ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR);

    public static final RegistryObject<TileEntityType<ThatlduSingularityPowerGeneratorTileEntity>> THATLDU_SINGULARITY_POWER_GENERATOR = register(
            "thatldu_singularity_power_generator", ThatlduSingularityPowerGeneratorTileEntity::new, ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR);

    // chests
    public static final RegistryObject<TileEntityType<NotsogudiumChestTileEntity>> NOTSOGUDIUM_CHEST = register(
            "notsogudium_chest", NotsogudiumChestTileEntity::new, ModBlocks.NOTSOGUDIUM_CHEST);

    public static final RegistryObject<TileEntityType<KudbebeddaChestTileEntity>> KUDBEBEDDA_CHEST = register(
            "kudbebedda_chest", KudbebeddaChestTileEntity::new, ModBlocks.KUDBEBEDDA_CHEST);

    public static final RegistryObject<TileEntityType<NotarfbadiumChestTileEntity>> NOTARFBADIUM_CHEST = register(
            "notarfbadium_chest", NotarfbadiumChestTileEntity::new, ModBlocks.NOTARFBADIUM_CHEST);

    public static final RegistryObject<TileEntityType<WikidiumChestTileEntity>> WIKIDIUM_CHEST = register(
            "wikidium_chest", WikidiumChestTileEntity::new, ModBlocks.WIKIDIUM_CHEST);

    public static final RegistryObject<TileEntityType<ThatlduChestTileEntity>> THATLDU_CHEST = register(
            "thatldu_chest", ThatlduChestTileEntity::new, ModBlocks.THATLDU_CHEST);

    // furnaces
    public static final RegistryObject<TileEntityType<NotsogudiumFurnaceTileEntity>> NOTSOGUDIUM_FURNACE = register(
            "notsogudium_furnace", NotsogudiumFurnaceTileEntity::new, ModBlocks.NOTSOGUDIUM_FURNACE);

    public static final RegistryObject<TileEntityType<KudbebeddaFurnaceTileEntity>> KUDBEBEDDA_FURNACE = register(
            "kudbebedda_furnace", KudbebeddaFurnaceTileEntity::new, ModBlocks.KUDBEBEDDA_FURNACE);

    public static final RegistryObject<TileEntityType<NotarfbadiumFurnaceTileEntity>> NOTARFBADIUM_FURNACE = register(
            "notarfbadium_furnace", NotarfbadiumFurnaceTileEntity::new, ModBlocks.NOTARFBADIUM_FURNACE);

    public static final RegistryObject<TileEntityType<WikidiumFurnaceTileEntity>> WIKIDIUM_FURNACE = register(
            "wikidium_furnace", WikidiumFurnaceTileEntity::new, ModBlocks.WIKIDIUM_FURNACE);

    public static final RegistryObject<TileEntityType<ThatlduFurnaceTileEntity>> THATLDU_FURNACE = register(
            "thatldu_furnace", ThatlduFurnaceTileEntity::new, ModBlocks.THATLDU_FURNACE);

    public static final RegistryObject<TileEntityType<EumusFurnaceTileEntity>> EUMUS_FURNACE = register(
            "eumus_furnace", EumusFurnaceTileEntity::new, ModBlocks.EUMUS_FURNACE);


    private static <T extends TileEntity> RegistryObject<TileEntityType<T>> register(String name, Supplier<T> factory, RegistryObject<? extends Block> block) {
        return Registration.TILE_ENTITIES.register(name, () -> {
            //noinspection ConstantConditions - null in build
            return TileEntityType.Builder.of(factory, block.get()).build(null);
        });
    }
}