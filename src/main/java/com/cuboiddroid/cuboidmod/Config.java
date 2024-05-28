package com.cuboiddroid.cuboidmod;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.event.level.LevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

@Mod.EventBusSubscriber
public class Config {
    public static ForgeConfigSpec COMMON_CONFIG;
    public static ForgeConfigSpec CLIENT_CONFIG;

    // --- GENERAL CATEGORY ---
    public static final String CATEGORY_GENERAL = "general";
    public static ForgeConfigSpec.IntValue smoosherDurability;
    public static ForgeConfigSpec.IntValue notsogudiumSmasherDurability;
    public static ForgeConfigSpec.IntValue notsogudiumSmasherAttackDamage;
    public static ForgeConfigSpec.DoubleValue notsogudiumSmasherAttackSpeed;
    public static ForgeConfigSpec.IntValue kudbebeddaSmasherDurability;
    public static ForgeConfigSpec.IntValue kudbebeddaSmasherAttackDamage;
    public static ForgeConfigSpec.DoubleValue kudbebeddaSmasherAttackSpeed;
    public static ForgeConfigSpec.IntValue notarfbadiumSmasherDurability;
    public static ForgeConfigSpec.IntValue notarfbadiumSmasherAttackDamage;
    public static ForgeConfigSpec.DoubleValue notarfbadiumSmasherAttackSpeed;
    public static ForgeConfigSpec.IntValue wikidiumSmasherDurability;
    public static ForgeConfigSpec.IntValue wikidiumSmasherAttackDamage;
    public static ForgeConfigSpec.DoubleValue wikidiumSmasherAttackSpeed;
    public static ForgeConfigSpec.IntValue thatlduSmasherDurability;
    public static ForgeConfigSpec.DoubleValue thatlduSmasherAttackSpeed;
    public static ForgeConfigSpec.IntValue thatlduSmasherAttackDamage;

    // --- FURNACES CATEGORY ---
    public static final String CATEGORY_FURNACE = "furnaces";
    public static ForgeConfigSpec.BooleanValue showErrors;
    public static ForgeConfigSpec.IntValue furnaceXPDropValue;
    public static ForgeConfigSpec.IntValue furnaceXPDropValue2;
    public static ForgeConfigSpec.IntValue notsogudiumFurnaceSpeed;
    public static ForgeConfigSpec.IntValue kudbebeddaFurnaceSpeed;
    public static ForgeConfigSpec.IntValue notarfbadiumFurnaceSpeed;
    public static ForgeConfigSpec.IntValue wikidiumFurnaceSpeed;
    public static ForgeConfigSpec.IntValue thatlduFurnaceSpeed;
    public static ForgeConfigSpec.IntValue eumusFurnaceSpeed;

    // --- CHESTS CATEGORY ---
    public static final String CATEGORY_CHESTS = "chests";
    public static ForgeConfigSpec.BooleanValue notsogudiumChestCanOpenWhenObstructedAbove;
    public static ForgeConfigSpec.BooleanValue notsogudiumChestRetainsInventoryWhenBroken;
    public static ForgeConfigSpec.BooleanValue kudbebeddaChestCanOpenWhenObstructedAbove;
    public static ForgeConfigSpec.BooleanValue kudbebeddaChestRetainsInventoryWhenBroken;
    public static ForgeConfigSpec.BooleanValue notarfbadiumChestCanOpenWhenObstructedAbove;
    public static ForgeConfigSpec.BooleanValue notarfbadiumChestRetainsInventoryWhenBroken;
    public static ForgeConfigSpec.BooleanValue wikidiumChestCanOpenWhenObstructedAbove;
    public static ForgeConfigSpec.BooleanValue wikidiumChestRetainsInventoryWhenBroken;
    public static ForgeConfigSpec.BooleanValue thatlduChestCanOpenWhenObstructedAbove;
    public static ForgeConfigSpec.BooleanValue thatlduChestRetainsInventoryWhenBroken;

    // --- QUANTUM COLLAPSERS CATEGORY ---
    public static final String CATEGORY_QUANTUM_COLLAPSERS = "quantum_collapsers";
    public static ForgeConfigSpec.DoubleValue notsogudiumQuantumCollapserSpeed;
    public static ForgeConfigSpec.DoubleValue kudbebeddaQuantumCollapserSpeed;
    public static ForgeConfigSpec.DoubleValue notarfbadiumQuantumCollapserSpeed;
    public static ForgeConfigSpec.DoubleValue wikidiumQuantumCollapserSpeed;
    public static ForgeConfigSpec.DoubleValue thatlduQuantumCollapserSpeed;

    // --- SINGULARITY RESOURCE GENERATORS CATEGORY ---
    public static final String CATEGORY_SINGULARITY_RESOURCE_GENERATORS = "singularity_resource_generators";
    public static ForgeConfigSpec.IntValue notsogudiumSingularityResourceGeneratorTicksPerOperation;
    public static ForgeConfigSpec.IntValue notsogudiumSingularityResourceGeneratorItemsPerOperation;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityResourceGeneratorTicksPerOperation;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityResourceGeneratorItemsPerOperation;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityResourceGeneratorTicksPerOperation;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityResourceGeneratorItemsPerOperation;
    public static ForgeConfigSpec.IntValue wikidiumSingularityResourceGeneratorTicksPerOperation;
    public static ForgeConfigSpec.IntValue wikidiumSingularityResourceGeneratorItemsPerOperation;
    public static ForgeConfigSpec.IntValue thatlduSingularityResourceGeneratorTicksPerOperation;
    public static ForgeConfigSpec.IntValue thatlduSingularityResourceGeneratorItemsPerOperation;

    // --- SINGULARITY POWER GENERATORS CATEGORY ---
    public static final String CATEGORY_SINGULARITY_POWER_GENERATORS = "singularity_power_generators";
    public static ForgeConfigSpec.IntValue notsogudiumSingularityPowerGeneratorEnergyCapacity;
    public static ForgeConfigSpec.IntValue notsogudiumSingularityPowerGeneratorTicksPerCycle;
    public static ForgeConfigSpec.IntValue notsogudiumSingularityPowerGeneratorBaseEnergyGenerated;
    public static ForgeConfigSpec.IntValue notsogudiumSingularityPowerGeneratorMaxEnergyOutputPerTick;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityPowerGeneratorEnergyCapacity;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityPowerGeneratorTicksPerCycle;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated;
    public static ForgeConfigSpec.IntValue kudbebeddaSingularityPowerGeneratorMaxEnergyOutputPerTick;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityPowerGeneratorEnergyCapacity;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityPowerGeneratorTicksPerCycle;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated;
    public static ForgeConfigSpec.IntValue notarfbadiumSingularityPowerGeneratorMaxEnergyOutputPerTick;
    public static ForgeConfigSpec.IntValue wikidiumSingularityPowerGeneratorEnergyCapacity;
    public static ForgeConfigSpec.IntValue wikidiumSingularityPowerGeneratorTicksPerCycle;
    public static ForgeConfigSpec.IntValue wikidiumSingularityPowerGeneratorBaseEnergyGenerated;
    public static ForgeConfigSpec.IntValue wikidiumSingularityPowerGeneratorMaxEnergyOutputPerTick;
    public static ForgeConfigSpec.IntValue thatlduSingularityPowerGeneratorEnergyCapacity;
    public static ForgeConfigSpec.IntValue thatlduSingularityPowerGeneratorTicksPerCycle;
    public static ForgeConfigSpec.IntValue thatlduSingularityPowerGeneratorBaseEnergyGenerated;
    public static ForgeConfigSpec.IntValue thatlduSingularityPowerGeneratorMaxEnergyOutputPerTick;

    // --- QUANTUM TRANSMUTATION CHAMBER CATEGORY ---
    public static final String CATEGORY_QUANTUM_TRANSMUTATION_CHAMBER = "quantum_transmutation_chamber";
    public static ForgeConfigSpec.IntValue quantumTransmutationChamberEnergyCapacity;
    public static ForgeConfigSpec.IntValue quantumTransmutationChamberMaxEnergyInputPerTick;

    // --- REFINED INSCRIBER CATEGORY ---
    public static final String CATEGORY_REFINED_INSCRIBER = "refined_inscriber";
    public static ForgeConfigSpec.IntValue refinedInscriberEnergyCapacity;
    public static ForgeConfigSpec.IntValue refinedInscriberMaxEnergyInputPerTick;
    public static ForgeConfigSpec.IntValue refinedInscriberDefaultWorkTicks;
    public static ForgeConfigSpec.IntValue refinedInscriberDefaultEnergyRequired;

    // --- MOLECULAR RECYCLER CATEGORY ---
    public static final String CATEGORY_MOLECULAR_RECYCLER = "molecular_recycler";
    public static ForgeConfigSpec.IntValue molecularRecyclerEnergyCapacity;
    public static ForgeConfigSpec.IntValue molecularRecyclerMaxEnergyInputPerTick;
    public static ForgeConfigSpec.IntValue molecularRecyclerMaxRecyclingSteps;
    public static ForgeConfigSpec.IntValue molecularRecyclerGuaranteedAmount;
    public static ForgeConfigSpec.IntValue molecularRecyclerGuaranteedCount;
    public static ForgeConfigSpec.IntValue molecularRecyclerBaseEnergyRequired;
    public static ForgeConfigSpec.IntValue molecularRecyclerStepEnergyRequired;
    public static ForgeConfigSpec.IntValue molecularRecyclerBaseWorkTicksRequired;
    public static ForgeConfigSpec.IntValue molecularRecyclerStepWorkTicksRequired;

    // --- DRYING CUPBOARD CATEGORY ---
    public static final String CATEGORY_DRYING_CUPBOARD = "drying_cupboard";
    public static ForgeConfigSpec.IntValue dryingCupboardEnergyCapacity;
    public static ForgeConfigSpec.IntValue dryingCupboardMaxEnergyInputPerTick;
    public static ForgeConfigSpec.IntValue dryingCupboardEnergyRequiredPerTick;

    // --- CRYOGENIC DIMENSIONAL TELEPORTER CATEGORY ---
    public static final String CRYOGENIC_DIMENSIONAL_TELEPORTER = "cryogenic_dimensional_teleporter";
    public static ForgeConfigSpec.IntValue cryoDimTeleporterEnergyCapacity;
    public static ForgeConfigSpec.IntValue cryoDimTeleporterMaxEnergyInputPerTick;
    public static ForgeConfigSpec.IntValue cryoDimTeleporterReadyRotationSpeed;
    public static ForgeConfigSpec.BooleanValue cryoDimTeleporterOverworldUsesPlayerSpawn;
    public static ForgeConfigSpec.BooleanValue cryoDimTeleporterClearsTargetDimensionForInvalidKey;

    // --- JEI CATEGORY ---
    public static final String CATEGORY_JEI = "jei";
    public static ForgeConfigSpec.BooleanValue enableJeiPlugin;
    public static ForgeConfigSpec.BooleanValue enableJeiCatalysts;
    public static ForgeConfigSpec.BooleanValue enableJeiClickArea;

    // --- MISC CATEGORY ---
    public static final String CATEGORY_MISC = "misc";
    public static ForgeConfigSpec.BooleanValue verboseLogging;
    public static ForgeConfigSpec.BooleanValue forcedCuboidFlat;
    public static ForgeConfigSpec.BooleanValue spawnCuboidHouse;

    static {
        ForgeConfigSpec.Builder COMMON_BUILDER = new ForgeConfigSpec.Builder();
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();

        COMMON_BUILDER.comment("Settings").push(CATEGORY_GENERAL);
        setupGeneralConfig(COMMON_BUILDER, CLIENT_BUILDER);
        setupSmasherConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Furnace Settings").push(CATEGORY_FURNACE);
        setupFurnacesConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Chest Settings").push(CATEGORY_CHESTS);
        setupChestsConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Quantum Collapser Settings").push(CATEGORY_QUANTUM_COLLAPSERS);
        setupQuantumCollapsersConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Singularity Resource Generator Settings").push(CATEGORY_SINGULARITY_RESOURCE_GENERATORS);
        setupSingularityResourceGeneratorsConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Singularity Power Generator Settings").push(CATEGORY_SINGULARITY_POWER_GENERATORS);
        setupSingularityPowerGeneratorsConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Quantum Transmutation Chamber Settings").push(CATEGORY_QUANTUM_TRANSMUTATION_CHAMBER);
        setupQuantumTransmutationChamberConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Refined Inscriber Settings").push(CATEGORY_REFINED_INSCRIBER);
        setupRefinedInscriberConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Molecular Recycler Settings\n" + getMolecularRecyclerDescription()).push(CATEGORY_MOLECULAR_RECYCLER);
        setupMolecularRecyclerConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Drying Cupboard Settings").push(CATEGORY_DRYING_CUPBOARD);
        setupDryingCupboardConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Cryogenic Dimensional Teleporter Settings").push(CRYOGENIC_DIMENSIONAL_TELEPORTER);
        setupCryogenicDimensionalTeleporterConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("JEI Settings").push(CATEGORY_JEI);
        setupJEIConfig(COMMON_BUILDER, CLIENT_BUILDER);
        COMMON_BUILDER.pop();

        COMMON_BUILDER.comment("Misc").push(CATEGORY_MISC);
        verboseLogging = COMMON_BUILDER
                .comment(" Logs additional information when loading Quantum Singularity and Molecular Recycler Blacklist config files.")
                .define("misc.verbose_logging", false);
        forcedCuboidFlat = COMMON_BUILDER
                .comment(" Forces the classic CuboidOutpost flat lands.")
                .define("misc.forced_cuboid_flat", false);
        spawnCuboidHouse = COMMON_BUILDER
                .comment(" Spawns the classic CuboidOutpost structure.")
                .comment(" NOTE: Some mods are required for this feature to work:")
                .comment(" - Applied Energistics 2 (ae2)")
                .comment(" - Framed Compacting Drawers (framedcompactdrawers)")
                .comment(" - Gauges and Switches (rsgauges)")
                .comment(" - Glassential Renewed (glassential)")
                .comment(" - Mekanism (mekanism)")
                .comment(" - Mekanism: Generators (mekanismgenerators)")
                .comment(" - MrCrayfish's Furniture Mod (cfm)")
                .comment(" - Simply Light (simplylight)")
                .comment(" - Thermal Expansion (thermal)")
                .define("misc.spawn_cuboid_house", false);
        COMMON_BUILDER.pop();

        COMMON_CONFIG = COMMON_BUILDER.build();
        CLIENT_CONFIG = CLIENT_BUILDER.build();
    }

    private static void setupGeneralConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        smoosherDurability = COMMON_BUILDER
                .comment(" Durability for the Smoosher.\n Default: 481")
                .defineInRange("smoosher.durability", 481, 1, 32767);
    }

    private static void setupSmasherConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        notsogudiumSmasherDurability = COMMON_BUILDER
                .comment(" Durability for the Notsogudium Smasher.\n Default: 261")
                .defineInRange("notsogudium_smasher.durability", 261, 1, 32767);

        notsogudiumSmasherAttackDamage = COMMON_BUILDER
                .comment(" Attack Damage for the Notsogudium Smasher.\n Default: 2")
                .defineInRange("notsogudium_smasher.attackDamage", 2, 0, 20);

        notsogudiumSmasherAttackSpeed = COMMON_BUILDER
                .comment(" Attack speed for the Notsogudium Smasher.\n Default: 1.4")
                .defineInRange("notsogudium_smasher.attackSpeed", 1.4, 0.0, 4.0);

        kudbebeddaSmasherDurability = COMMON_BUILDER
                .comment(" Durability for the Kudbebedda Smasher.\n Default: 383")
                .defineInRange("kudbebedda_smasher.durability", 383, 1, 32767);

        kudbebeddaSmasherAttackDamage = COMMON_BUILDER
                .comment(" Attack Damage for the Kudbebedda Smasher.\n Default: 3")
                .defineInRange("kudbebedda_smasher.attackDamage", 3, 0, 20);

        kudbebeddaSmasherAttackSpeed = COMMON_BUILDER
                .comment(" Attack speed for the Kudbebedda Smasher.\n Default: 1.4")
                .defineInRange("kudbebedda_smasher.attackSpeed", 1.4, 0.0, 4.0);

        notarfbadiumSmasherDurability = COMMON_BUILDER
                .comment(" Durability for the Notarfbadium Smasher.\n Default: 727")
                .defineInRange("notarfbadium_smasher.durability", 727, 1, 32767);

        notarfbadiumSmasherAttackDamage = COMMON_BUILDER
                .comment(" Attack Damage for the Notarfbadium Smasher.\n Default: 4")
                .defineInRange("notarfbadium_smasher.attackDamage", 4, 0, 20);

        notarfbadiumSmasherAttackSpeed = COMMON_BUILDER
                .comment(" Attack speed for the Notarfbadium Smasher.\n Default: 1.4")
                .defineInRange("notarfbadium_smasher.attackSpeed", 1.4, 0.0, 4.0);

        wikidiumSmasherDurability = COMMON_BUILDER
                .comment(" Durability for the Wikidium Smasher.\n Default: 1337")
                .defineInRange("wikidium_smasher.durability", 1337, 1, 32767);

        wikidiumSmasherAttackDamage = COMMON_BUILDER
                .comment(" Attack Damage for the Wikidium Smasher.\n Default: 2")
                .defineInRange("wikidium_smasher.attackDamage", 5, 0, 20);

        wikidiumSmasherAttackSpeed = COMMON_BUILDER
                .comment(" Attack speed for the Wikidium Smasher.\n Default: 1.4")
                .defineInRange("wikidium_smasher.attackSpeed", 1.4, 0.0, 4.0);

        thatlduSmasherDurability = COMMON_BUILDER
                .comment(" Durability for the Thatldu Smasher.\n Default: 2577")
                .defineInRange("thatldu_smasher.durability", 2577, 1, 32767);

        thatlduSmasherAttackDamage = COMMON_BUILDER
                .comment(" Attack Damage for the Thatldu Smasher.\n Default: 2")
                .defineInRange("thatldu_smasher.attackDamage", 6, 0, 20);

        thatlduSmasherAttackSpeed = COMMON_BUILDER
                .comment(" Attack speed for the Thatldu Smasher.\n Default: 1.5")
                .defineInRange("thatldu_smasher.attackSpeed", 1.5, 0.0, 4.0);
    }

    private static void setupFurnacesConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        showErrors = COMMON_BUILDER
                .comment(" Show furnace settings errors in chat, used for debugging")
                .define("furnace.errors", false);

        notsogudiumFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 180")
                .defineInRange("notsogudium_furnace.speed", 180, 2, 72000);

        kudbebeddaFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 160")
                .defineInRange("kudbebedda_furnace.speed", 160, 2, 72000);

        notarfbadiumFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 140")
                .defineInRange("notarfbadium_furnace.speed", 140, 2, 72000);

        wikidiumFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 110")
                .defineInRange("wikidium_furnace.speed", 110, 2, 72000);

        thatlduFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 70")
                .defineInRange("thatldu_furnace.speed", 70, 2, 72000);

        eumusFurnaceSpeed = COMMON_BUILDER
                .comment(" Number of ticks to complete one smelting operation.\n 200 ticks is what a regular furnace takes.\n Default: 200")
                .defineInRange("eumus_furnace.speed", 200, 2, 72000);

        furnaceXPDropValue = COMMON_BUILDER
                .comment(" This value indicates when the furnace should 'overload' and spit out the xp stored. \n Default: 10, Recipes")
                .defineInRange("furnace_xp_drop.value", 10, 1, 500);

        furnaceXPDropValue2 = COMMON_BUILDER
                .comment(" This value indicates when the furnace should 'overload' and spit out the xp stored. \n Default: 100000, Single recipe uses")
                .defineInRange("furnace_xp_drop.value_two", 100000, 1, 1000000);

    }

    private static void setupChestsConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        notsogudiumChestCanOpenWhenObstructedAbove = COMMON_BUILDER
                .comment(" Whether the chest can open if there's a full block above it.\n If true then can always open.\n Default: false")
                .define("notsogudium_chest.can_open_obstructed", false);

        notsogudiumChestRetainsInventoryWhenBroken = COMMON_BUILDER
                .comment(" Whether the chest keeps its inventory when broken.\n If true then chest retains inventory. If false, inventory is dumped on the ground when broken.\n Default: false")
                .define("notsogudium_chest.retains_inventory", false);

        kudbebeddaChestCanOpenWhenObstructedAbove = COMMON_BUILDER
                .comment(" Whether the chest can open if there's a full block above it.\n If true then can always open.\n Default: false")
                .define("kudbebedda_chest.can_open_obstructed", false);

        kudbebeddaChestRetainsInventoryWhenBroken = COMMON_BUILDER
                .comment(" Whether the chest keeps its inventory when broken.\n If true then chest retains inventory. If false, inventory is dumped on the ground when broken.\n Default: false")
                .define("kudbebedda_chest.retains_inventory", false);

        notarfbadiumChestCanOpenWhenObstructedAbove = COMMON_BUILDER
                .comment(" Whether the chest can open if there's a full block above it.\n If true then can always open.\n Default: false")
                .define("notarfbadium_chest.can_open_obstructed", true);

        notarfbadiumChestRetainsInventoryWhenBroken = COMMON_BUILDER
                .comment(" Whether the chest keeps its inventory when broken.\n If true then chest retains inventory. If false, inventory is dumped on the ground when broken.\n Default: false")
                .define("notarfbadium_chest.retains_inventory", false);

        wikidiumChestCanOpenWhenObstructedAbove = COMMON_BUILDER
                .comment(" Whether the chest can open if there's a full block above it.\n If true then can always open.\n Default: false")
                .define("wikidium_chest.can_open_obstructed", true);

        wikidiumChestRetainsInventoryWhenBroken = COMMON_BUILDER
                .comment(" Whether the chest keeps its inventory when broken.\n If true then chest retains inventory. If false, inventory is dumped on the ground when broken.\n Default: false")
                .define("wikidium_chest.retains_inventory", true);

        thatlduChestCanOpenWhenObstructedAbove = COMMON_BUILDER
                .comment(" Whether the chest can open if there's a full block above it.\n If true then can always open.\n Default: false")
                .define("thatldu_chest.can_open_obstructed", true);

        thatlduChestRetainsInventoryWhenBroken = COMMON_BUILDER
                .comment(" Whether the chest keeps its inventory when broken.\n If true then chest retains inventory. If false, inventory is dumped on the ground when broken.\n Default: false")
                .define("thatldu_chest.retains_inventory", true);
    }

    private static void setupQuantumCollapsersConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        notsogudiumQuantumCollapserSpeed = COMMON_BUILDER
                .comment(" Speed at which the collapser works.\n 1.0 means the collapser runs at normal speed according to the recipe being used.\n Default: 1.0")
                .defineInRange("quantum_collapser.notsogudium.speed", 1.0F, 1.0F, 20.0F);

        kudbebeddaQuantumCollapserSpeed = COMMON_BUILDER
                .comment(" Speed at which the collapser works.\n 1.2 means the collapser runs 20% faster than the recipe being used specifies (e.g. 200 / 1.2 = 167 ticks).\n Default: 1.2")
                .defineInRange("quantum_collapser.kudbebedda.speed", 1.2F, 1.0F, 20.0F);

        notarfbadiumQuantumCollapserSpeed = COMMON_BUILDER
                .comment(" Speed at which the collapser works.\n 1.5 means the collapser runs 50% faster than the recipe being used specifies (e.g. 200 / 1.5 = 133 ticks).\n Default: 1.5")
                .defineInRange("quantum_collapser.notarfbadium.speed", 1.5F, 1.0F, 20.0F);

        wikidiumQuantumCollapserSpeed = COMMON_BUILDER
                .comment(" Speed at which the collapser works.\n 2.5 means the collapser runs 150% faster than the recipe being used specifies (e.g. 200 / 2.5 = 80 ticks).\n Default: 2.5")
                .defineInRange("quantum_collapser.wikidium.speed", 2.5F, 1.0F, 20.0F);

        thatlduQuantumCollapserSpeed = COMMON_BUILDER
                .comment(" Speed at which the collapser works.\n 5.0 means the collapser runs 300% faster than the recipe being used specifies (e.g. 200 / 5.0 = 40 ticks).\n Default: 5.0")
                .defineInRange("quantum_collapser.thatldu.speed", 5.0F, 1.0F, 20.0F);
    }

    private static void setupSingularityResourceGeneratorsConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        notsogudiumSingularityResourceGeneratorTicksPerOperation = COMMON_BUILDER
                .comment(" Number of ticks between operations where items are produced.\n 20 would mean the amount of items gets produced every second.\n Default: 20")
                .defineInRange("srg.notsogudium.ticks_per_op", 20, 1, 1200);

        notsogudiumSingularityResourceGeneratorItemsPerOperation = COMMON_BUILDER
                .comment(" Number of items being produced per operation.\n 1 would mean that every specified number of ticks, 1 item is produced.\n Default: 1")
                .defineInRange("srg.notsogudium.items_per_op", 1, 1, 64);

        kudbebeddaSingularityResourceGeneratorTicksPerOperation = COMMON_BUILDER
                .comment(" Number of ticks between operations where items are produced.\n 10 would mean the amount of items gets produced every half second.\n Default: 10")
                .defineInRange("srg.kudbebedda.ticks_per_op", 10, 1, 1200);

        kudbebeddaSingularityResourceGeneratorItemsPerOperation = COMMON_BUILDER
                .comment(" Number of items being produced per operation.\n 4 would mean that every specified number of ticks, 2 items are produced.\n Default: 2")
                .defineInRange("srg.kudbebedda.items_per_op", 2, 1, 64);

        notarfbadiumSingularityResourceGeneratorTicksPerOperation = COMMON_BUILDER
                .comment(" Number of ticks between operations where items are produced.\n 5 would mean the amount of items gets produced 4 times per second.\n Default: 5")
                .defineInRange("srg.notarfbadium.ticks_per_op", 5, 1, 1200);

        notarfbadiumSingularityResourceGeneratorItemsPerOperation = COMMON_BUILDER
                .comment(" Number of items being produced per operation.\n 4 would mean that every specified number of ticks, 4 items are produced.\n Default: 4")
                .defineInRange("srg.notarfbadium.items_per_op", 4, 1, 64);

        wikidiumSingularityResourceGeneratorTicksPerOperation = COMMON_BUILDER
                .comment(" Number of ticks between operations where items are produced.\n 2 would mean the amount of items gets produced 10 times per second.\n Default: 2")
                .defineInRange("srg.wikidium.ticks_per_op", 5, 1, 1200);

        wikidiumSingularityResourceGeneratorItemsPerOperation = COMMON_BUILDER
                .comment(" Number of items being produced per operation.\n 16 would mean that every specified number of ticks, 16 items are produced.\n Default: 16")
                .defineInRange("srg.wikidium.items_per_op", 16, 1, 64);

        thatlduSingularityResourceGeneratorTicksPerOperation = COMMON_BUILDER
                .comment(" Number of ticks between operations where items are produced.\n 2 would mean the amount of items gets produced 10 times per second.\n Default: 2")
                .defineInRange("srg.thatldu.ticks_per_op", 5, 1, 1200);

        thatlduSingularityResourceGeneratorItemsPerOperation = COMMON_BUILDER
                .comment(" Number of items being produced per operation.\n 64 would mean that every specified number of ticks, 64 items are produced.\n Default: 64")
                .defineInRange("srg.thatldu.items_per_op", 64, 1, 64);

    }

    private static void setupSingularityPowerGeneratorsConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        notsogudiumSingularityPowerGeneratorTicksPerCycle = COMMON_BUILDER
                .comment(" Number of ticks between power generation cycles.\n 10 would mean that energy is produced twice each second.\n Default: 10")
                .defineInRange("spg.notsogudium.ticks_per_cycle", 10, 1, 20);

        notsogudiumSingularityPowerGeneratorBaseEnergyGenerated = COMMON_BUILDER
                .comment(" The base FE per tick this generator produces.\n 4 means that 4 FE/t is produced. If ticks per cycle is 10, then twice per second, 40 FE is produced. \n Default: 4")
                .defineInRange("spg.notsogudium.base_fe_generated", 4, 1, 50000);

        notsogudiumSingularityPowerGeneratorEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 20000")
                .defineInRange("spg.notsogudium.energy_capacity", 20000, 1, 10000000);

        notsogudiumSingularityPowerGeneratorMaxEnergyOutputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that this generator can provide to consumers.\n Usually you'd set this at around 4 to 8 times the figure used for base energy generated. Default: 16")
                .defineInRange("spg.notsogudium.max_output_per_tick", 16, 1, 10000000);

        kudbebeddaSingularityPowerGeneratorTicksPerCycle = COMMON_BUILDER
                .comment(" Number of ticks between power generation cycles.\n 10 would mean that energy is produced twice each second.\n Default: 10")
                .defineInRange("spg.kudbebedda.ticks_per_cycle", 10, 1, 20);

        kudbebeddaSingularityPowerGeneratorBaseEnergyGenerated = COMMON_BUILDER
                .comment(" The base FE per tick this generator produces.\n 8 means that 8 FE/t is produced. If ticks per cycle is 10, then twice per second, 80 FE is produced. \n Default: 8")
                .defineInRange("spg.kudbebedda.base_fe_generated", 8, 1, 50000);

        kudbebeddaSingularityPowerGeneratorEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 60000")
                .defineInRange("spg.kudbebedda.energy_capacity", 60000, 1, 10000000);

        kudbebeddaSingularityPowerGeneratorMaxEnergyOutputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that this generator can provide to consumers.\n Usually you'd set this at around 4 to 8 times the figure used for base energy generated. Default: 60")
                .defineInRange("spg.kudbebedda.max_output_per_tick", 60, 1, 10000000);

        notarfbadiumSingularityPowerGeneratorTicksPerCycle = COMMON_BUILDER
                .comment(" Number of ticks between power generation cycles.\n 10 would mean that energy is produced twice each second.\n Default: 10")
                .defineInRange("spg.notarfbadium.ticks_per_cycle", 10, 1, 20);

        notarfbadiumSingularityPowerGeneratorBaseEnergyGenerated = COMMON_BUILDER
                .comment(" The base FE per tick this generator produces.\n 16 means that 16 FE/t is produced. If ticks per cycle is 10, then twice per second, 160 FE is produced. \n Default: 16")
                .defineInRange("spg.notarfbadium.base_fe_generated", 16, 1, 50000);

        notarfbadiumSingularityPowerGeneratorEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 150000")
                .defineInRange("spg.notarfbadium.energy_capacity", 150000, 1, 10000000);

        notarfbadiumSingularityPowerGeneratorMaxEnergyOutputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that this generator can provide to consumers.\n Usually you'd set this at around 4 to 8 times the figure used for base energy generated. Default: 120")
                .defineInRange("spg.notarfbadium.max_output_per_tick", 120, 1, 10000000);

        wikidiumSingularityPowerGeneratorTicksPerCycle = COMMON_BUILDER
                .comment(" Number of ticks between power generation cycles.\n 10 would mean that energy is produced twice each second.\n Default: 10")
                .defineInRange("spg.wikidium.ticks_per_cycle", 10, 1, 20);

        wikidiumSingularityPowerGeneratorBaseEnergyGenerated = COMMON_BUILDER
                .comment(" The base FE per tick this generator produces.\n 40 means that 40 FE/t is produced. If ticks per cycle is 10, then twice per second, 400 FE is produced. \n Default: 40")
                .defineInRange("spg.wikidium.base_fe_generated", 40, 1, 50000);

        wikidiumSingularityPowerGeneratorEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 500000")
                .defineInRange("spg.wikidium.energy_capacity", 500000, 1, 10000000);

        wikidiumSingularityPowerGeneratorMaxEnergyOutputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that this generator can provide to consumers.\n Usually you'd set this at around 4 to 8 times the figure used for base energy generated. Default: 300")
                .defineInRange("spg.wikidium.max_output_per_tick", 300, 1, 10000000);

        thatlduSingularityPowerGeneratorTicksPerCycle = COMMON_BUILDER
                .comment(" Number of ticks between power generation cycles.\n 10 would mean that energy is produced twice each second.\n Default: 10")
                .defineInRange("spg.thatldu.ticks_per_cycle", 10, 1, 20);

        thatlduSingularityPowerGeneratorBaseEnergyGenerated = COMMON_BUILDER
                .comment(" The base FE per tick this generator produces.\n 100 means that 100 FE/t is produced. If ticks per cycle is 10, then twice per second, 1000 FE is produced. \n Default: 100")
                .defineInRange("spg.thatldu.base_fe_generated", 100, 1, 50000);

        thatlduSingularityPowerGeneratorEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 1000000")
                .defineInRange("spg.thatldu.energy_capacity", 1000000, 1, 10000000);

        thatlduSingularityPowerGeneratorMaxEnergyOutputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that this generator can provide to consumers.\n Usually you'd set this at around 4 to 8 times the figure used for base energy generated. Default: 800")
                .defineInRange("spg.thatldu.max_output_per_tick", 800, 1, 10000000);
    }

    private static void setupQuantumTransmutationChamberConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        quantumTransmutationChamberEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 50000")
                .defineInRange("qtc.energy_capacity", 50000, 1, 10000000);

        quantumTransmutationChamberMaxEnergyInputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that the QTC can receive.\n Default: 250")
                .defineInRange("qtc.max_energy_in_per_tick", 250, 1, 10000000);
    }

    private static void setupRefinedInscriberConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        refinedInscriberEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 50000")
                .defineInRange("ri.energy_capacity", 50000, 1, 10000000);

        refinedInscriberMaxEnergyInputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that the Refined Inscriber can receive.\n Default: 250")
                .defineInRange("ri.max_energy_in_per_tick", 250, 1, 10000000);

        refinedInscriberDefaultWorkTicks = COMMON_BUILDER
                .comment(" The default number of ticks for recipes if not specified in the recipe itself.\n Default: 100")
                .defineInRange("ri.recipe_work_ticks", 100, 1, 10000);

        refinedInscriberDefaultEnergyRequired = COMMON_BUILDER
                .comment(" The default amount of FE required for recipes if not specified in the recipe itself.\n Default: 5000")
                .defineInRange("ri.recipe_energy_required", 5000, 1, 10000000);
    }

    private static String getMolecularRecyclerDescription() {
        return "\n" +
                "  The Molecular Recycler is essentially similar to the concept of uncrafters,\n" +
                "  but is intentionally designed to be imperfect. What it attempts to do, with the\n" +
                "  default configuration, is recursively break down ingredients based on crafting\n" +
                "  recipes that result in the item being recycled. The number of steps it will take\n" +
                "  is determined by [qtc.max_recycling_steps] and defaults to 3. Increasing this will\n" +
                "  increase the likelihood that the outputs are going to be really basic! e.g. more\n" +
                "  likely to be nuggets than ingots, or any familiar ingredients given the input.\n" +
                "\n" +
                "  For example, an oak fence gate requires 4 sticks and 2 oak planks. If you put in\n" +
                "  an oak fence gate, you won't get back all 4 sticks and 2 oak planks - instead you\n" +
                "  will most likely get back less than that.\n" +
                "\n" +
                "  For each final ingredient type, the Molecular Recycler uses the following formula\n" +
                "  to determine your chances of getting back each of the input materials:\n" +
                "\n" +
                "    - For the first [qtc.guaranteed_amount] items of a particular type, you will get\n" +
                "      back [qtc.guaranteed_count] of that item with 100% certainty. By default, these\n" +
                "      are 4 and 3 respectively, which means that for any item where there were 4 or\n" +
                "      more ingredients identified by the recycler, you will get back 3 of the first 4.\n" +
                "    - For the next 2 items of a particular type, there is a 80% chance of getting (up to) 2 back\n" +
                "    - For the next 2 items of a particular type, there is a 60% chance of getting (up to) 2 back\n" +
                "    - For the next 2 items of a particular type, there is a 50% chance of getting (up to) 2 back\n" +
                "    - For the remainder of items, for each of the next 2 items of a particular type, " +
                "      there is a 25% chance of getting (up to) 2 back\n" +
                "\n" +
                "Note that we use (up to) 2 because if at any stage there is only 1 left, then the logic will apply\n" +
                "to just that one remaining item.\n" +
                "\n" +
                "Yes - it's confusing, and possibly a bit harsh, but this is not meant to be a lossless uncrafter!\n" +
                "\n" +
                "Going back to the oak fence gate example, 4 sticks and two oak planks - assuming default settings -\n" +
                "this would result in 3 sticks, and an 80% chance of getting back 2 oak planks.\n" +
                "\n" +
                "Another example is an Iron Chest from the Iron Chests mod. To make it you need a vanilla chest \n" +
                "(8x planks) and 8 iron ingots. The planks would not break down further, so would result in 3 of \n" +
                "the first 4 being guaranteed, then an 80% chance of 2 and a 60% chance of 2. The iron ingots, however\n" +
                "would be broken down again into iron nuggets - 72 of them. The first 4 would give back a guaranteed 3\n" +
                "then 2 at 80% chance, 2 at 60% chance, 2 at 50% chance, and the remaining 62 would be as 31 rolls of\n" +
                "2 at 25% chance... if you're really, really unlucky, you might only get back 3 planks and 3 nuggets!\n" +
                "It should be fairly clear that this machine is a mixed blessing, and should be used with caution!\n" +
                "\n" +
                "NOTE: When it comes to recycling items with multiple recipes (or their results when recursively\n" +
                "      recycling), the recipe with the most ingredients is chosen as a preference. This is not\n" +
                "      currently configurable.\n";
    }

    private static void setupMolecularRecyclerConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        molecularRecyclerEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 50000")
                .defineInRange("qtc.energy_capacity", 50000, 1, 10000000);

        molecularRecyclerMaxEnergyInputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that the recycler can receive.\n Default: 250")
                .defineInRange("qtc.max_energy_in_per_tick", 250, 1, 10000000);

        molecularRecyclerMaxRecyclingSteps = COMMON_BUILDER
                .comment(" The maximum number of recycling steps to perform.\n Default: 3, Max: 10")
                .defineInRange("qtc.max_recycling_steps", 3, 1, 10);

        molecularRecyclerGuaranteedAmount = COMMON_BUILDER
                .comment(" If the net result of recycling exceeds this number (N), then the first N results will have a guaranteed output count as specified.\n Default: 4, Max: 64")
                .defineInRange("qtc.guaranteed_amount", 4, 0, 64);

        molecularRecyclerGuaranteedCount = COMMON_BUILDER
                .comment(" If the net result of recycling exceeds the guaranteed amount, then this number of resulting items is guaranteed.\n This should always be less than or equal to the guaranteed amount. e.g. if Guaranteed Amount is 4, and this is 3, then if the recycler determines that there are 4 or more of a potential output, the first 4 will result in a 100% chance of getting 3 back (guaranteed 75% output).\nDefault: 3, Max: 64")
                .defineInRange("qtc.guaranteed_count", 3, 0, 64);

        molecularRecyclerBaseEnergyRequired = COMMON_BUILDER
                .comment(" The base energy requirement for any recycling.\nDefault: 2000, Max: 50000")
                .defineInRange("qtc.base_energy", 2000, 0, 50000);

        molecularRecyclerStepEnergyRequired = COMMON_BUILDER
                .comment(" The additional energy required for each additional recycling step, based on the maximum depth used on any 'branch'.\nDefault: 500, Max: 50000")
                .defineInRange("qtc.step_energy", 500, 0, 50000);

        molecularRecyclerBaseWorkTicksRequired = COMMON_BUILDER
                .comment(" The base time taken for any recycling in ticks.\nDefault: 100, Max: 6000")
                .defineInRange("qtc.base_work_ticks", 100, 0, 6000);

        molecularRecyclerStepWorkTicksRequired = COMMON_BUILDER
                .comment(" The additional time taken in ticks for each additional recycling step, based on the maximum depth used on any 'branch'.\nDefault: 40, Max: 6000")
                .defineInRange("qtc.step_work_ticks", 40, 0, 6000);
    }

    private static void setupDryingCupboardConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        dryingCupboardEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE.\n Default: 10000")
                .defineInRange("drying_cupboard.energy_capacity", 10000, 1, 10000000);

        dryingCupboardMaxEnergyInputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that the drying cupboard can receive.\n Default: 64")
                .defineInRange("drying_cupboard.max_energy_in_per_tick", 64, 1, 10000);

        dryingCupboardEnergyRequiredPerTick = COMMON_BUILDER
                .comment(" The amount of FE per tick that the drying cupboard uses when active.\n Default: 8")
                .defineInRange("drying_cupboard.max_energy_in_per_tick", 8, 1, 10000);
    }

    private static void setupCryogenicDimensionalTeleporterConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        cryoDimTeleporterEnergyCapacity = COMMON_BUILDER
                .comment(" The internal energy capacity in FE - i.e. the amount of FE needed per teleport\n Default: 300000")
                .defineInRange("cryogenic_dimensional_teleporter.energy_capacity", 300000, 1, 10000000);

        cryoDimTeleporterMaxEnergyInputPerTick = COMMON_BUILDER
                .comment(" The maximum amount of FE per tick that the CDT can receive.\n Default: 5000")
                .defineInRange("cryogenic_dimensional_teleporter.max_energy_in_per_tick", 5000, 1, 50000);

        cryoDimTeleporterReadyRotationSpeed = COMMON_BUILDER
                .comment(" The rotation speed for a ready / fully charged teleporter - lower = faster rotation.\n Default: 50")
                .defineInRange("cryogenic_dimensional_teleporter.ready_rotation_speed", 50, 1, 300);

        cryoDimTeleporterOverworldUsesPlayerSpawn = COMMON_BUILDER
                .comment(" Indicates whether teleporting to the overworld takes the player to their own respawn point.\n If false, player is just teleported to location relative to position in the originating dimension.\n Default: true")
                .define("cryogenic_dimensional_teleporter.overworld_uses_player_spawn", true);

        cryoDimTeleporterClearsTargetDimensionForInvalidKey = COMMON_BUILDER
                .comment(" Indicates whether using an invalid key on the CDT will reset the target dimension.")
                .define("cryogenic_dimensional_teleporter.clears_for_invalid_key", false);
    }

    private static void setupJEIConfig(ForgeConfigSpec.Builder COMMON_BUILDER, ForgeConfigSpec.Builder CLIENT_BUILDER) {
        enableJeiPlugin = COMMON_BUILDER
                .comment(" Enable or disable the JeiPlugin of Cuboid machines.").define("jei.enable_jei", true);

        enableJeiCatalysts = COMMON_BUILDER
                .comment(" Enable or disable the Catalysts in Jei for Cuboid machines.").define("jei.enable_jei_catalysts", true);

        enableJeiClickArea = COMMON_BUILDER
                .comment(" Enable or disable the Click Area inside the GUI in all Cuboid machines.").define("jei.enable_jei_click_area", true);
    }

    public static void loadConfig(ForgeConfigSpec spec, Path path) {
        CuboidMod.LOGGER.debug("Loading config file {}", path);

        final CommentedFileConfig configData = CommentedFileConfig.builder(path)
                .sync()
                .autosave()
                .writingMode(WritingMode.REPLACE)
                .build();

        CuboidMod.LOGGER.debug("Built TOML config for {}", path.toString());
        configData.load();
        CuboidMod.LOGGER.debug("Loaded TOML config file {}", path.toString());
        spec.setConfig(configData);
    }

    @SubscribeEvent
    public static void onLoad(final ModConfigEvent.Loading configEvent) {

    }

    @SubscribeEvent
    public static void onReload(final ModConfigEvent.Reloading configEvent) {

    }

    @SubscribeEvent
    public static void onWorldLoad(final LevelEvent.Load event) {
        Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "-client.toml"));
        Config.loadConfig(Config.COMMON_CONFIG, FMLPaths.CONFIGDIR.get().resolve(CuboidMod.MOD_ID + "-common.toml"));
    }
}
