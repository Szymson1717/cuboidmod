package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.cdt.tile.CryogenicDimensionalTeleporterRenderer;
import com.cuboiddroid.cuboidmod.modules.chest.render.CuboidChestTileEntityRenderer;
import com.cuboiddroid.cuboidmod.modules.chest.screen.CuboidChestScreen;
import com.cuboiddroid.cuboidmod.modules.collapser.handler.ColorHandler;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularityRegistry;
import com.cuboiddroid.cuboidmod.modules.collapser.screen.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.screen.DryingCupboardScreen;
import com.cuboiddroid.cuboidmod.modules.furnace.screen.*;
import com.cuboiddroid.cuboidmod.modules.powergen.screen.*;
import com.cuboiddroid.cuboidmod.modules.recycler.config.BlacklistConfig;
import com.cuboiddroid.cuboidmod.modules.recycler.screen.MolecularRecyclerScreen;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.screen.RefinedInscriberScreen;
import com.cuboiddroid.cuboidmod.modules.resourcegen.screen.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.screen.QuantumTransmutationChamberScreen;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<MenuType<?>> CONTAINERS = create(ForgeRegistries.MENU_TYPES);
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = create(ForgeRegistries.BLOCK_ENTITY_TYPES);
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = create(ForgeRegistries.RECIPE_SERIALIZERS);
    public static final DeferredRegister<Fluid> FLUIDS = create(ForgeRegistries.FLUIDS);

    public static void register() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        ITEMS.register(modEventBus);
        BLOCK_ENTITIES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        FLUIDS.register(modEventBus);

        // class-load the registry object holder classes.
        ModBlocks.register();
        ModContainers.register();
        // ModItemTiers.register();
        ModItems.register();
        ModRecipeSerializers.register();
        ModTileEntities.register();
        ModDimensions.register();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            // Client setup
            modEventBus.register(new ColorHandler());
        });
    }

    public static void registerConfigs() {
        QuantumSingularityRegistry.getInstance().loadSingularities();
        BlacklistConfig.getInstance().loadConfig();
    }

    private static <T> DeferredRegister<T> create(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, CuboidMod.MOD_ID);
    }

    private static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> registry) {
        return DeferredRegister.create(registry, CuboidMod.MOD_ID);
    }

    @Mod.EventBusSubscriber(value=Dist.CLIENT, modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Client {
        private Client() {}

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            MenuScreens.register(ModContainers.NOTSOGUDIUM_CHEST.get(), CuboidChestScreen::new);
            MenuScreens.register(ModContainers.KUDBEBEDDA_CHEST.get(), CuboidChestScreen::new);
            MenuScreens.register(ModContainers.NOTARFBADIUM_CHEST.get(), CuboidChestScreen::new);
            MenuScreens.register(ModContainers.WIKIDIUM_CHEST.get(), CuboidChestScreen::new);
            MenuScreens.register(ModContainers.THATLDU_CHEST.get(), CuboidChestScreen::new);

            MenuScreens.register(ModContainers.NOTSOGUDIUM_FURNACE.get(), NotsogudiumFurnaceScreen::new);
            MenuScreens.register(ModContainers.KUDBEBEDDA_FURNACE.get(), KudbebeddaFurnaceScreen::new);
            MenuScreens.register(ModContainers.NOTARFBADIUM_FURNACE.get(), NotarfbadiumFurnaceScreen::new);
            MenuScreens.register(ModContainers.WIKIDIUM_FURNACE.get(), WikidiumFurnaceScreen::new);
            MenuScreens.register(ModContainers.THATLDU_FURNACE.get(), ThatlduFurnaceScreen::new);
            MenuScreens.register(ModContainers.EUMUS_FURNACE.get(), EumusFurnaceScreen::new);

            MenuScreens.register(ModContainers.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(), NotsogudiumQuantumCollapserScreen::new);
            MenuScreens.register(ModContainers.KUDBEBEDDA_QUANTUM_COLLAPSER.get(), KudbebeddaQuantumCollapserScreen::new);
            MenuScreens.register(ModContainers.NOTARFBADIUM_QUANTUM_COLLAPSER.get(), NotarfbadiumQuantumCollapserScreen::new);
            MenuScreens.register(ModContainers.WIKIDIUM_QUANTUM_COLLAPSER.get(), WikidiumQuantumCollapserScreen::new);
            MenuScreens.register(ModContainers.THATLDU_QUANTUM_COLLAPSER.get(), ThatlduQuantumCollapserScreen::new);

            MenuScreens.register(ModContainers.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), NotsogudiumSingularityResourceGeneratorScreen::new);
            MenuScreens.register(ModContainers.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(), KudbebeddaSingularityResourceGeneratorScreen::new);
            MenuScreens.register(ModContainers.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(), NotarfbadiumSingularityResourceGeneratorScreen::new);
            MenuScreens.register(ModContainers.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), WikidiumSingularityResourceGeneratorScreen::new);
            MenuScreens.register(ModContainers.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(), ThatlduSingularityResourceGeneratorScreen::new);

            MenuScreens.register(ModContainers.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(), NotsogudiumSingularityPowerGeneratorScreen::new);
            MenuScreens.register(ModContainers.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(), KudbebeddaSingularityPowerGeneratorScreen::new);
            MenuScreens.register(ModContainers.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), NotarfbadiumSingularityPowerGeneratorScreen::new);
            MenuScreens.register(ModContainers.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(), WikidiumSingularityPowerGeneratorScreen::new);
            MenuScreens.register(ModContainers.THATLDU_SINGULARITY_POWER_GENERATOR.get(), ThatlduSingularityPowerGeneratorScreen::new);

            MenuScreens.register(ModContainers.QUANTUM_TRANSMUTATION_CHAMBER.get(), QuantumTransmutationChamberScreen::new);
            MenuScreens.register(ModContainers.REFINED_INSCRIBER.get(), RefinedInscriberScreen::new);
            MenuScreens.register(ModContainers.MOLECULAR_RECYCLER.get(), MolecularRecyclerScreen::new);
            MenuScreens.register(ModContainers.DRYING_CUPBOARD.get(), DryingCupboardScreen::new);
        }

        @SubscribeEvent
        public static void onBlockEntityRender(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(ModTileEntities.NOTSOGUDIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            event.registerBlockEntityRenderer(ModTileEntities.KUDBEBEDDA_CHEST.get(), CuboidChestTileEntityRenderer::new);
            event.registerBlockEntityRenderer(ModTileEntities.NOTARFBADIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            event.registerBlockEntityRenderer(ModTileEntities.WIKIDIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            event.registerBlockEntityRenderer(ModTileEntities.THATLDU_CHEST.get(), CuboidChestTileEntityRenderer::new);
            event.registerBlockEntityRenderer(ModTileEntities.CRYOGENIC_DIMENSIONAL_TELEPORTER.get(), CryogenicDimensionalTeleporterRenderer::new);
        } 
    }
}
