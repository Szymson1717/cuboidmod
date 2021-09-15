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
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class Registration {
    public static final DeferredRegister<Block> BLOCKS = create(ForgeRegistries.BLOCKS);
    public static final DeferredRegister<Item> ITEMS = create(ForgeRegistries.ITEMS);
    public static final DeferredRegister<ContainerType<?>> CONTAINERS = create(ForgeRegistries.CONTAINERS);
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITIES = create(ForgeRegistries.TILE_ENTITIES);
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = create(ForgeRegistries.RECIPE_SERIALIZERS);
    public static final DeferredRegister<Fluid> FLUIDS = create(ForgeRegistries.FLUIDS);

    public static void register() {
        QuantumSingularityRegistry.getInstance().loadSingularities();
        BlacklistConfig.getInstance().loadConfig();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        BLOCKS.register(modEventBus);
        CONTAINERS.register(modEventBus);
        ITEMS.register(modEventBus);
        TILE_ENTITIES.register(modEventBus);
        RECIPE_SERIALIZERS.register(modEventBus);
        FLUIDS.register(modEventBus);

        // class-load the registry object holder classes.
        ModBlocks.register();
        ModContainers.register();
        ModItems.register();
        ModRecipes.register();
        ModTileEntities.register();
        ModDimensions.register();

        DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
            // Client setup
            modEventBus.register(new ColorHandler());
        });
    }

    private static <T extends IForgeRegistryEntry<T>> DeferredRegister<T> create(IForgeRegistry<T> registry) {
        return DeferredRegister.create(registry, CuboidMod.MOD_ID);
    }

    @Mod.EventBusSubscriber(value=Dist.CLIENT, modid = CuboidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class Client {
        private Client() {}

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            ScreenManager.register(ModContainers.NOTSOGUDIUM_CHEST.get(), CuboidChestScreen::new);
            ScreenManager.register(ModContainers.KUDBEBEDDA_CHEST.get(), CuboidChestScreen::new);
            ScreenManager.register(ModContainers.NOTARFBADIUM_CHEST.get(), CuboidChestScreen::new);
            ScreenManager.register(ModContainers.WIKIDIUM_CHEST.get(), CuboidChestScreen::new);
            ScreenManager.register(ModContainers.THATLDU_CHEST.get(), CuboidChestScreen::new);

            ClientRegistry.bindTileEntityRenderer(ModTileEntities.NOTSOGUDIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.KUDBEBEDDA_CHEST.get(), CuboidChestTileEntityRenderer::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.NOTARFBADIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.WIKIDIUM_CHEST.get(), CuboidChestTileEntityRenderer::new);
            ClientRegistry.bindTileEntityRenderer(ModTileEntities.THATLDU_CHEST.get(), CuboidChestTileEntityRenderer::new);

            ScreenManager.register(ModContainers.NOTSOGUDIUM_FURNACE.get(), NotsogudiumFurnaceScreen::new);
            ScreenManager.register(ModContainers.KUDBEBEDDA_FURNACE.get(), KudbebeddaFurnaceScreen::new);
            ScreenManager.register(ModContainers.NOTARFBADIUM_FURNACE.get(), NotarfbadiumFurnaceScreen::new);
            ScreenManager.register(ModContainers.WIKIDIUM_FURNACE.get(), WikidiumFurnaceScreen::new);
            ScreenManager.register(ModContainers.THATLDU_FURNACE.get(), ThatlduFurnaceScreen::new);
            ScreenManager.register(ModContainers.EUMUS_FURNACE.get(), EumusFurnaceScreen::new);

            ScreenManager.register(ModContainers.NOTSOGUDIUM_QUANTUM_COLLAPSER.get(), NotsogudiumQuantumCollapserScreen::new);
            ScreenManager.register(ModContainers.KUDBEBEDDA_QUANTUM_COLLAPSER.get(), KudbebeddaQuantumCollapserScreen::new);
            ScreenManager.register(ModContainers.NOTARFBADIUM_QUANTUM_COLLAPSER.get(), NotarfbadiumQuantumCollapserScreen::new);
            ScreenManager.register(ModContainers.WIKIDIUM_QUANTUM_COLLAPSER.get(), WikidiumQuantumCollapserScreen::new);
            ScreenManager.register(ModContainers.THATLDU_QUANTUM_COLLAPSER.get(), ThatlduQuantumCollapserScreen::new);

            ScreenManager.register(ModContainers.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), NotsogudiumSingularityResourceGeneratorScreen::new);
            ScreenManager.register(ModContainers.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(), KudbebeddaSingularityResourceGeneratorScreen::new);
            ScreenManager.register(ModContainers.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(), NotarfbadiumSingularityResourceGeneratorScreen::new);
            ScreenManager.register(ModContainers.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), WikidiumSingularityResourceGeneratorScreen::new);
            ScreenManager.register(ModContainers.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(), ThatlduSingularityResourceGeneratorScreen::new);

            ScreenManager.register(ModContainers.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(), NotsogudiumSingularityPowerGeneratorScreen::new);
            ScreenManager.register(ModContainers.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(), KudbebeddaSingularityPowerGeneratorScreen::new);
            ScreenManager.register(ModContainers.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), NotarfbadiumSingularityPowerGeneratorScreen::new);
            ScreenManager.register(ModContainers.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(), WikidiumSingularityPowerGeneratorScreen::new);
            ScreenManager.register(ModContainers.THATLDU_SINGULARITY_POWER_GENERATOR.get(), ThatlduSingularityPowerGeneratorScreen::new);

            ScreenManager.register(ModContainers.QUANTUM_TRANSMUTATION_CHAMBER.get(), QuantumTransmutationChamberScreen::new);
            ScreenManager.register(ModContainers.REFINED_INSCRIBER.get(), RefinedInscriberScreen::new);
            ScreenManager.register(ModContainers.MOLECULAR_RECYCLER.get(), MolecularRecyclerScreen::new);
            ScreenManager.register(ModContainers.DRYING_CUPBOARD.get(), DryingCupboardScreen::new);

            CryogenicDimensionalTeleporterRenderer.register();
        }
    }
}
