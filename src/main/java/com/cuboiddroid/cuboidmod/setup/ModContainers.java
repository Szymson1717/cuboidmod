package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.*;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.recycler.inventory.MolecularRecyclerContainer;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;

public class ModContainers {
    static void register() {
    }

    // drying cupboard
    public static final RegistryObject<ContainerType<DryingCupboardContainer>> DRYING_CUPBOARD =
            Registration.CONTAINERS.register("drying_cupboard", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new DryingCupboardContainer(windowId, world, pos, inv, inv.player);
            }));

    // molecular recycler
    public static final RegistryObject<ContainerType<MolecularRecyclerContainer>> MOLECULAR_RECYCLER =
            Registration.CONTAINERS.register("molecular_recycler", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new MolecularRecyclerContainer(windowId, world, pos, inv, inv.player);
            }));

    // quantum transmutation chamber
    public static final RegistryObject<ContainerType<QuantumTransmutationChamberContainer>> QUANTUM_TRANSMUTATION_CHAMBER =
            Registration.CONTAINERS.register("quantum_transmutation_chamber", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new QuantumTransmutationChamberContainer(windowId, world, pos, inv, inv.player);
            }));

    // quantum collapsers
    public static final RegistryObject<ContainerType<NotsogudiumQuantumCollapserContainer>> NOTSOGUDIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("notsogudium_quantum_collapser", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<KudbebeddaQuantumCollapserContainer>> KUDBEBEDDA_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("kudbebedda_quantum_collapser", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<NotarfbadiumQuantumCollapserContainer>> NOTARFBADIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("notarfbadium_quantum_collapser", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<WikidiumQuantumCollapserContainer>> WIKIDIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("wikidium_quantum_collapser", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<ThatlduQuantumCollapserContainer>> THATLDU_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("thatldu_quantum_collapser", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    // singularity resource generators

    public static final RegistryObject<ContainerType<NotsogudiumSingularityResourceGeneratorContainer>> NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("notsogudium_singularity_resource_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<KudbebeddaSingularityResourceGeneratorContainer>> KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("kudbebedda_singularity_resource_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<NotarfbadiumSingularityResourceGeneratorContainer>> NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("notarfbadium_singularity_resource_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<WikidiumSingularityResourceGeneratorContainer>> WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("wikidium_singularity_resource_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<ThatlduSingularityResourceGeneratorContainer>> THATLDU_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("thatldu_singularity_resource_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    // singularity power generators

    public static final RegistryObject<ContainerType<NotsogudiumSingularityPowerGeneratorContainer>> NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("notsogudium_singularity_power_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<KudbebeddaSingularityPowerGeneratorContainer>> KUDBEBEDDA_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("kudbebedda_singularity_power_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<NotarfbadiumSingularityPowerGeneratorContainer>> NOTARFBADIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("notarfbadium_singularity_power_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<WikidiumSingularityPowerGeneratorContainer>> WIKIDIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("wikidium_singularity_power_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<ThatlduSingularityPowerGeneratorContainer>> THATLDU_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("thatldu_singularity_power_generator", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    // chests
    public static final RegistryObject<ContainerType<CuboidChestContainer>> NOTSOGUDIUM_CHEST =
            Registration.CONTAINERS.register("notsogudium_chest", () -> new ContainerType<>(CuboidChestContainer::createNotsogudiumContainer));

    public static final RegistryObject<ContainerType<CuboidChestContainer>> KUDBEBEDDA_CHEST =
            Registration.CONTAINERS.register("kudbebedda_chest", () -> new ContainerType<>(CuboidChestContainer::createKudbebeddaContainer));

    public static final RegistryObject<ContainerType<CuboidChestContainer>> NOTARFBADIUM_CHEST =
            Registration.CONTAINERS.register("notarfbadium_chest", () -> new ContainerType<>(CuboidChestContainer::createNotarfbadiumContainer));

    public static final RegistryObject<ContainerType<CuboidChestContainer>> WIKIDIUM_CHEST =
            Registration.CONTAINERS.register("wikidium_chest", () -> new ContainerType<>(CuboidChestContainer::createWikidiumContainer));

    public static final RegistryObject<ContainerType<CuboidChestContainer>> THATLDU_CHEST =
            Registration.CONTAINERS.register("thatldu_chest", () -> new ContainerType<>(CuboidChestContainer::createThatlduContainer));

    // furnaces
    public static final RegistryObject<ContainerType<NotsogudiumFurnaceContainer>> NOTSOGUDIUM_FURNACE =
            Registration.CONTAINERS.register("notsogudium_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getCommandSenderWorld();  // possibly level instead?
        return new NotsogudiumFurnaceContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<ContainerType<KudbebeddaFurnaceContainer>> KUDBEBEDDA_FURNACE =
            Registration.CONTAINERS.register("kudbebedda_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<NotarfbadiumFurnaceContainer>> NOTARFBADIUM_FURNACE =
            Registration.CONTAINERS.register("notarfbadium_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<WikidiumFurnaceContainer>> WIKIDIUM_FURNACE =
            Registration.CONTAINERS.register("wikidium_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<ThatlduFurnaceContainer>> THATLDU_FURNACE =
            Registration.CONTAINERS.register("thatldu_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<ContainerType<EumusFurnaceContainer>> EUMUS_FURNACE =
            Registration.CONTAINERS.register("eumus_furnace", () -> IForgeContainerType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                World world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new EumusFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

}
