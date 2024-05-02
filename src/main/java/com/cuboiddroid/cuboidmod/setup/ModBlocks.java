package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.modules.cdt.block.*;
import com.cuboiddroid.cuboidmod.modules.chest.block.*;
import com.cuboiddroid.cuboidmod.modules.chest.render.CuboidChestItemStackRenderer;
import com.cuboiddroid.cuboidmod.modules.chest.tile.*;
import com.cuboiddroid.cuboidmod.modules.collapser.block.*;
import com.cuboiddroid.cuboidmod.modules.craftingtable.block.*;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.block.DryingCupboardBlock;
import com.cuboiddroid.cuboidmod.modules.furnace.block.*;
import com.cuboiddroid.cuboidmod.modules.powergen.block.*;
import com.cuboiddroid.cuboidmod.modules.recycler.block.MolecularRecyclerBlock;
import com.cuboiddroid.cuboidmod.modules.refinedinscriber.block.RefinedInscriberBlock;
import com.cuboiddroid.cuboidmod.modules.resourcegen.block.*;
import com.cuboiddroid.cuboidmod.modules.transmuter.block.QuantumTransmutationChamberBlock;
import com.cuboiddroid.cuboidmod.modules.xmas.block.FiberOpticTreeBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ModBlocks {
  // Fiber Optic Tree

  public static final RegistryObject<FiberOpticTreeBlock> FIBER_OPTIC_TREE = register(
          "fiber_optic_tree", () ->
                  new FiberOpticTreeBlock());

  // Quantum Collapsers

  public static final RegistryObject<NotsogudiumQuantumCollapserBlock> NOTSOGUDIUM_QUANTUM_COLLAPSER = register(
          "notsogudium_quantum_collapser", () ->
                  new NotsogudiumQuantumCollapserBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<KudbebeddaQuantumCollapserBlock> KUDBEBEDDA_QUANTUM_COLLAPSER = register(
          "kudbebedda_quantum_collapser", () ->
                  new KudbebeddaQuantumCollapserBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<NotarfbadiumQuantumCollapserBlock> NOTARFBADIUM_QUANTUM_COLLAPSER = register(
          "notarfbadium_quantum_collapser", () ->
                  new NotarfbadiumQuantumCollapserBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<WikidiumQuantumCollapserBlock> WIKIDIUM_QUANTUM_COLLAPSER = register(
          "wikidium_quantum_collapser", () ->
                  new WikidiumQuantumCollapserBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<ThatlduQuantumCollapserBlock> THATLDU_QUANTUM_COLLAPSER = register(
          "thatldu_quantum_collapser", () ->
                  new ThatlduQuantumCollapserBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Singularity Resource Generators

  public static final RegistryObject<NotsogudiumSingularityResourceGeneratorBlock> NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
          "notsogudium_singularity_resource_generator", () ->
                  new NotsogudiumSingularityResourceGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<KudbebeddaSingularityResourceGeneratorBlock> KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR = register(
          "kudbebedda_singularity_resource_generator", () ->
                  new KudbebeddaSingularityResourceGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<NotarfbadiumSingularityResourceGeneratorBlock> NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR = register(
          "notarfbadium_singularity_resource_generator", () ->
                  new NotarfbadiumSingularityResourceGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<WikidiumSingularityResourceGeneratorBlock> WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR = register(
          "wikidium_singularity_resource_generator", () ->
                  new WikidiumSingularityResourceGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<ThatlduSingularityResourceGeneratorBlock> THATLDU_SINGULARITY_RESOURCE_GENERATOR = register(
          "thatldu_singularity_resource_generator", () ->
                  new ThatlduSingularityResourceGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Singularity Power Generators

  public static final RegistryObject<NotsogudiumSingularityPowerGeneratorBlock> NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR = register(
          "notsogudium_singularity_power_generator", () ->
                  new NotsogudiumSingularityPowerGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<KudbebeddaSingularityPowerGeneratorBlock> KUDBEBEDDA_SINGULARITY_POWER_GENERATOR = register(
          "kudbebedda_singularity_power_generator", () ->
                  new KudbebeddaSingularityPowerGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<NotarfbadiumSingularityPowerGeneratorBlock> NOTARFBADIUM_SINGULARITY_POWER_GENERATOR = register(
          "notarfbadium_singularity_power_generator", () ->
                  new NotarfbadiumSingularityPowerGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<WikidiumSingularityPowerGeneratorBlock> WIKIDIUM_SINGULARITY_POWER_GENERATOR = register(
          "wikidium_singularity_power_generator", () ->
                  new WikidiumSingularityPowerGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  public static final RegistryObject<ThatlduSingularityPowerGeneratorBlock> THATLDU_SINGULARITY_POWER_GENERATOR = register(
          "thatldu_singularity_power_generator", () ->
                  new ThatlduSingularityPowerGeneratorBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Molecular Recycler

  public static final RegistryObject<MolecularRecyclerBlock> MOLECULAR_RECYCLER = register(
          "molecular_recycler", () ->
                  new MolecularRecyclerBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Cryogenic Dimensional Teleporter

  public static final RegistryObject<CryogenicDimensionalTeleporterBlock> CRYOGENIC_DIMENSIONAL_TELEPORTER = register(
          "cryogenic_dimensional_teleporter", () -> new CryogenicDimensionalTeleporterBlock());

  // Drying Cupboard

  public static final RegistryObject<DryingCupboardBlock> DRYING_CUPBOARD = register(
          "drying_cupboard", () ->
                  new DryingCupboardBlock(BlockBehaviour.Properties.of()
                          .strength(3, 15)
                          .sound(SoundType.GLASS)));

  // Quantum Transmutation Chamber

  public static final RegistryObject<QuantumTransmutationChamberBlock> QUANTUM_TRANSMUTATION_CHAMBER = register(
          "quantum_transmutation_chamber", () ->
                  new QuantumTransmutationChamberBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Refined Inscriber

  public static final RegistryObject<RefinedInscriberBlock> REFINED_INSCRIBER = register(
          "refined_inscriber", () ->
                  new RefinedInscriberBlock(BlockBehaviour.Properties.of()
                          .strength(4, 20)
                          .sound(SoundType.METAL)));

  // Notsogudium
  public static final RegistryObject<Block> NOTSOGUDIUM_ORE = register("notsogudium_ore", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                //   .harvestLevel(0).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> NOTSOGUDIUM_BLOCK = register("notsogudium_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                  .sound(SoundType.METAL)));

  public static final RegistryObject<NotsogudiumChestBlock> NOTSOGUDIUM_CHEST = register(
          "notsogudium_chest", () -> new NotsogudiumChestBlock(BlockBehaviour.Properties.of().strength(3)),
          () -> notsogudiumChestRenderer());

  public static final RegistryObject<Block> NOTSOGUDIUM_CRAFTING_TABLE = register(
          "notsogudium_crafting_table", () -> new NotsogudiumCraftingTable());

  public static final RegistryObject<NotsogudiumFurnaceBlock> NOTSOGUDIUM_FURNACE = register(
          "notsogudium_furnace", () -> new NotsogudiumFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));

  // Kudbebedda
  public static final RegistryObject<Block> KUDBEBEDDA_ORE = register("kudbebedda_ore", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                //   .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> KUDBEBEDDA_BLOCK = register("kudbebedda_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                  .sound(SoundType.METAL)));

  public static final RegistryObject<KudbebeddaChestBlock> KUDBEBEDDA_CHEST = register(
          "kudbebedda_chest", () -> new KudbebeddaChestBlock(BlockBehaviour.Properties.of().strength(3)),
          () -> kudbebeddaChestRenderer());

  public static final RegistryObject<Block> KUDBEBEDDA_CRAFTING_TABLE = register(
          "kudbebedda_crafting_table", () -> new KudbebeddaCraftingTable());

  public static final RegistryObject<KudbebeddaFurnaceBlock> KUDBEBEDDA_FURNACE = register(
          "kudbebedda_furnace", () -> new KudbebeddaFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));

  // Notarfbadium
  public static final RegistryObject<Block> NOTARFBADIUM_ORE = register("notarfbadium_ore", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                //   .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> NOTARFBADIUM_BLOCK = register("notarfbadium_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                  .sound(SoundType.METAL)));

  public static final RegistryObject<NotarfbadiumChestBlock> NOTARFBADIUM_CHEST = register(
          "notarfbadium_chest", () -> new NotarfbadiumChestBlock(BlockBehaviour.Properties.of().strength(3)),
          () -> notarfbadiumChestRenderer());

  public static final RegistryObject<Block> NOTARFBADIUM_CRAFTING_TABLE = register(
          "notarfbadium_crafting_table", () -> new NotarfbadiumCraftingTable());

  public static final RegistryObject<NotarfbadiumFurnaceBlock> NOTARFBADIUM_FURNACE = register(
          "notarfbadium_furnace", () -> new NotarfbadiumFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));

  // Wikidium
  public static final RegistryObject<Block> WIKIDIUM_ORE = register("wikidium_ore", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                //   .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> WIKIDIUM_BLOCK = register("wikidium_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                  .sound(SoundType.METAL)));

  public static final RegistryObject<WikidiumChestBlock> WIKIDIUM_CHEST = register(
          "wikidium_chest", () -> new WikidiumChestBlock(BlockBehaviour.Properties.of().strength(3)),
          () -> wikidiumChestRenderer());

  public static final RegistryObject<Block> WIKIDIUM_CRAFTING_TABLE = register(
          "wikidium_crafting_table", () -> new WikidiumCraftingTable());

  public static final RegistryObject<WikidiumFurnaceBlock> WIKIDIUM_FURNACE = register(
          "wikidium_furnace", () -> new WikidiumFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));

  // Thatldu
  public static final RegistryObject<Block> THATLDU_ORE = register("thatldu_ore", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                //   .harvestLevel(3).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> THATLDU_BLOCK = register("thatldu_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(3, 10)
                  .sound(SoundType.METAL)));

  public static final RegistryObject<ThatlduChestBlock> THATLDU_CHEST = register(
          "thatldu_chest", () -> new ThatlduChestBlock(BlockBehaviour.Properties.of().strength(3)),
          () -> thatlduChestRenderer());

  public static final RegistryObject<Block> THATLDU_CRAFTING_TABLE = register(
          "thatldu_crafting_table", () -> new ThatlduCraftingTable());

  public static final RegistryObject<ThatlduFurnaceBlock> THATLDU_FURNACE = register(
          "thatldu_furnace", () -> new ThatlduFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));

  // eumus
  public static final RegistryObject<EumusFurnaceBlock> EUMUS_FURNACE = register(
          "eumus_furnace", () -> new EumusFurnaceBlock(BlockBehaviour.Properties.of().strength(3)));


  // silica dust block
  public static final RegistryObject<Block> SILICA_DUST_BLOCK = register("silica_dust_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(1, 10)
                //   .harvestLevel(0).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.SAND)));

  // Block of Carbon Nanotubes
  public static final RegistryObject<Block> CARBON_NANOTUBE_BLOCK = register("carbon_nanotube_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(1, 10)
                //   .harvestLevel(0).harvestTool(ToolType.PICKAXE)
                  .requiresCorrectToolForDrops()
                  .sound(SoundType.STONE)));

  // Block of Protein Paste
  public static final RegistryObject<Block> PROTEIN_PASTE_BLOCK = register("protein_paste_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(0, 10)
                //   .harvestLevel(0).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.SLIME_BLOCK)));

  // Block of Protein Fiber
  public static final RegistryObject<Block> PROTEIN_FIBER_BLOCK = register("protein_fiber_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(0, 10)
                //   .harvestLevel(0).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.SLIME_BLOCK)));

  // Blocks of Cellulose
  public static final RegistryObject<Block> CELLULOSE_BLOCK = register("cellulose_block", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(0, 10)
                //   .harvestLevel(0).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.SAND)));

  public static final RegistryObject<Block> CELLULOSE_BRICKS = register("cellulose_bricks", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(2, 12)
                //   .harvestLevel(1).harvestTool(ToolType.PICKAXE)
                  .sound(SoundType.STONE)));

  public static final RegistryObject<Block> CELLULOSE_CHISELED_BRICKS = register("cellulose_chiseled_bricks", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(2, 12)
                //   .harvestLevel(1).harvestTool(ToolType.PICKAXE)
                  .sound(SoundType.STONE)));

  // Organically Enriched Sand
  public static final RegistryObject<Block> ORGANICALLY_ENRICHED_SAND = register("organically_enriched_sand", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(1, 10)
                //   .harvestLevel(0).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.SAND)));

  // Aggregate
  public static final RegistryObject<Block> AGGREGATE = register("aggregate", () ->
          new Block(BlockBehaviour.Properties.of()
                  .strength(2, 12)
                //   .harvestLevel(1).harvestTool(ToolType.SHOVEL)
                  .sound(SoundType.GRAVEL)));

  // Cryogenic Dimensional Teleporter Keys
  public static final RegistryObject<Block> ENERGIZED_END_STONE_BRICKS = register(
          EnergizedEndStoneBricksBlock.ID_STRING, () ->
                  new EnergizedEndStoneBricksBlock());
  public static final RegistryObject<Block> ENERGIZED_NETHER_BRICKS = register(
          EnergizedNetherBricksBlock.ID_STRING, () ->
                  new EnergizedNetherBricksBlock());
  public static final RegistryObject<Block> ENERGIZED_STONE_BRICKS = register(
          EnergizedStoneBricksBlock.ID_STRING, () ->
                  new EnergizedStoneBricksBlock());
  public static final RegistryObject<Block> ENERGIZED_THATLDUVIUM = register(
          EnergizedThatlduviumBlock.ID_STRING, () ->
                  new EnergizedThatlduviumBlock());


  // - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

  static void register() {
  }

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> blockSupplier) {
    RegistryObject<T> ret = registerNoItem(name, blockSupplier);
    Registration.ITEMS.register(name, () -> new BlockItem(ret.get(), new Item.Properties()));
    return ret;
  }

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Supplier<Callable<BlockEntityWithoutLevelRenderer>> renderMethod) {
    return register(name, sup, block -> item(block, renderMethod));
  }

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<? extends T> sup, Function<RegistryObject<T>, Supplier<? extends Item>> itemCreator) {
    RegistryObject<T> ret = registerNoItem(name, sup);
    Registration.ITEMS.register(name, itemCreator.apply(ret));
    return ret;
  }

  private static <T extends Block> RegistryObject<T> registerNoItem(String name, Supplier<? extends T> blockSupplier) {
    return Registration.BLOCKS.register(name, blockSupplier);
  }

  private static Supplier<BlockItem> item(final RegistryObject<? extends Block> block, final Supplier<Callable<BlockEntityWithoutLevelRenderer>> renderMethod) {
    return () -> new BlockItem(block.get(), new Item.Properties()) {
        @Override
        public void initializeClient(Consumer<IClientItemExtensions> consumer) {
            consumer.accept(new IClientItemExtensions() {
                @Override
                public BlockEntityWithoutLevelRenderer getCustomRenderer() {
                    try {
                        return renderMethod.get().call();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    }
                };
            });
        };
    };
  }


  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> notsogudiumChestRenderer() {
    return () -> new CuboidChestItemStackRenderer<>(NotsogudiumChestTileEntity::new);
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> kudbebeddaChestRenderer() {
    return () -> new CuboidChestItemStackRenderer<>(KudbebeddaChestTileEntity::new);
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> notarfbadiumChestRenderer() {
    return () -> new CuboidChestItemStackRenderer<>(NotarfbadiumChestTileEntity::new);
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> wikidiumChestRenderer() {
    return () -> new CuboidChestItemStackRenderer<>(WikidiumChestTileEntity::new);
  }

  @OnlyIn(Dist.CLIENT)
  private static Callable<BlockEntityWithoutLevelRenderer> thatlduChestRenderer() {
    return () -> new CuboidChestItemStackRenderer<>(ThatlduChestTileEntity::new);
  }
}
