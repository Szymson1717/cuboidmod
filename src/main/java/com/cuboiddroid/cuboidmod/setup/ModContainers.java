package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import com.cuboiddroid.cuboidmod.modules.collapser.inventory.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.*;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.recycler.inventory.MolecularRecyclerContainer;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.inventory.RefinedInscriberContainer;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.inventory.QuantumTransmutationChamberContainer;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.RegistryObject;

public class ModContainers {
    static void register() {
    }

    // drying cupboard
    public static final RegistryObject<MenuType<DryingCupboardContainer>> DRYING_CUPBOARD =
            Registration.CONTAINERS.register("drying_cupboard", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new DryingCupboardContainer(windowId, world, pos, inv, inv.player);
            }));

    // molecular recycler
    public static final RegistryObject<MenuType<MolecularRecyclerContainer>> MOLECULAR_RECYCLER =
            Registration.CONTAINERS.register("molecular_recycler", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new MolecularRecyclerContainer(windowId, world, pos, inv, inv.player);
            }));

    // quantum transmutation chamber
    public static final RegistryObject<MenuType<QuantumTransmutationChamberContainer>> QUANTUM_TRANSMUTATION_CHAMBER =
            Registration.CONTAINERS.register("quantum_transmutation_chamber", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new QuantumTransmutationChamberContainer(windowId, world, pos, inv, inv.player);
            }));

    // refined inscriber
    public static final RegistryObject<MenuType<RefinedInscriberContainer>> REFINED_INSCRIBER =
            Registration.CONTAINERS.register("refined_inscriber", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new RefinedInscriberContainer(windowId, world, pos, inv, inv.player);
            }));

    // quantum collapsers
    public static final RegistryObject<MenuType<NotsogudiumQuantumCollapserContainer>> NOTSOGUDIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("notsogudium_quantum_collapser", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<KudbebeddaQuantumCollapserContainer>> KUDBEBEDDA_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("kudbebedda_quantum_collapser", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<NotarfbadiumQuantumCollapserContainer>> NOTARFBADIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("notarfbadium_quantum_collapser", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<WikidiumQuantumCollapserContainer>> WIKIDIUM_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("wikidium_quantum_collapser", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<ThatlduQuantumCollapserContainer>> THATLDU_QUANTUM_COLLAPSER =
            Registration.CONTAINERS.register("thatldu_quantum_collapser", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduQuantumCollapserContainer(windowId, world, pos, inv, inv.player);
            }));

    // singularity resource generators

    public static final RegistryObject<MenuType<NotsogudiumSingularityResourceGeneratorContainer>> NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("notsogudium_singularity_resource_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<KudbebeddaSingularityResourceGeneratorContainer>> KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("kudbebedda_singularity_resource_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<NotarfbadiumSingularityResourceGeneratorContainer>> NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("notarfbadium_singularity_resource_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<WikidiumSingularityResourceGeneratorContainer>> WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("wikidium_singularity_resource_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<ThatlduSingularityResourceGeneratorContainer>> THATLDU_SINGULARITY_RESOURCE_GENERATOR =
            Registration.CONTAINERS.register("thatldu_singularity_resource_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduSingularityResourceGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    // singularity power generators

    public static final RegistryObject<MenuType<NotsogudiumSingularityPowerGeneratorContainer>> NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("notsogudium_singularity_power_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotsogudiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<KudbebeddaSingularityPowerGeneratorContainer>> KUDBEBEDDA_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("kudbebedda_singularity_power_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<NotarfbadiumSingularityPowerGeneratorContainer>> NOTARFBADIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("notarfbadium_singularity_power_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<WikidiumSingularityPowerGeneratorContainer>> WIKIDIUM_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("wikidium_singularity_power_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<ThatlduSingularityPowerGeneratorContainer>> THATLDU_SINGULARITY_POWER_GENERATOR =
            Registration.CONTAINERS.register("thatldu_singularity_power_generator", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduSingularityPowerGeneratorContainer(windowId, world, pos, inv, inv.player);
            }));

    // chests
    public static final RegistryObject<MenuType<CuboidChestContainer>> NOTSOGUDIUM_CHEST =
            Registration.CONTAINERS.register("notsogudium_chest", () -> new MenuType<>(CuboidChestContainer::createNotsogudiumContainer));

    public static final RegistryObject<MenuType<CuboidChestContainer>> KUDBEBEDDA_CHEST =
            Registration.CONTAINERS.register("kudbebedda_chest", () -> new MenuType<>(CuboidChestContainer::createKudbebeddaContainer));

    public static final RegistryObject<MenuType<CuboidChestContainer>> NOTARFBADIUM_CHEST =
            Registration.CONTAINERS.register("notarfbadium_chest", () -> new MenuType<>(CuboidChestContainer::createNotarfbadiumContainer));

    public static final RegistryObject<MenuType<CuboidChestContainer>> WIKIDIUM_CHEST =
            Registration.CONTAINERS.register("wikidium_chest", () -> new MenuType<>(CuboidChestContainer::createWikidiumContainer));

    public static final RegistryObject<MenuType<CuboidChestContainer>> THATLDU_CHEST =
            Registration.CONTAINERS.register("thatldu_chest", () -> new MenuType<>(CuboidChestContainer::createThatlduContainer));

    // furnaces
    public static final RegistryObject<MenuType<NotsogudiumFurnaceContainer>> NOTSOGUDIUM_FURNACE =
            Registration.CONTAINERS.register("notsogudium_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
        return new NotsogudiumFurnaceContainer(windowId, world, pos, inv, inv.player);
    }));

    public static final RegistryObject<MenuType<KudbebeddaFurnaceContainer>> KUDBEBEDDA_FURNACE =
            Registration.CONTAINERS.register("kudbebedda_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new KudbebeddaFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<NotarfbadiumFurnaceContainer>> NOTARFBADIUM_FURNACE =
            Registration.CONTAINERS.register("notarfbadium_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new NotarfbadiumFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<WikidiumFurnaceContainer>> WIKIDIUM_FURNACE =
            Registration.CONTAINERS.register("wikidium_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new WikidiumFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<ThatlduFurnaceContainer>> THATLDU_FURNACE =
            Registration.CONTAINERS.register("thatldu_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new ThatlduFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

    public static final RegistryObject<MenuType<EumusFurnaceContainer>> EUMUS_FURNACE =
            Registration.CONTAINERS.register("eumus_furnace", () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();  // possibly level instead?
                return new EumusFurnaceContainer(windowId, world, pos, inv, inv.player);
            }));

}
