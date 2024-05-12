package com.cuboiddroid.cuboidmod.datagen.server;

import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.modules.furnace.block.CuboidFurnaceBlockBase;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModItems;
import com.cuboiddroid.cuboidmod.setup.Registration;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.critereon.EnchantmentPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.MinMaxBounds;
import net.minecraft.world.level.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.CopyNameFunction;
import net.minecraft.world.level.storage.loot.functions.CopyNbtFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.ItemLike;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTables;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

public class ModLootTableProvider extends LootTableProvider {
    private static final LootItemCondition.Builder HAS_SILK_TOUCH = MatchTool.toolMatches(ItemPredicate.Builder.item().hasEnchantment(new EnchantmentPredicate(Enchantments.SILK_TOUCH, MinMaxBounds.Ints.atLeast(1))));
    private static final LootItemCondition.Builder HAS_NO_SILK_TOUCH = HAS_SILK_TOUCH.invert();

    public ModLootTableProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootContextParamSet>> getTables() {
        return ImmutableList.of(
                Pair.of(ModBlockLootTables::new, LootContextParamSets.BLOCK)
        );
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationContext validationTracker) {
        map.forEach((p1, p2) -> LootTables.validate(validationTracker, p1, p2));
    }

    public static class ModBlockLootTables extends BlockLoot {

        @Override
        protected void addTables() {
            dropSelf(ModBlocks.NOTSOGUDIUM_QUANTUM_COLLAPSER.get());
            dropSelf(ModBlocks.KUDBEBEDDA_QUANTUM_COLLAPSER.get());
            dropSelf(ModBlocks.NOTARFBADIUM_QUANTUM_COLLAPSER.get());
            dropSelf(ModBlocks.WIKIDIUM_QUANTUM_COLLAPSER.get());
            dropSelf(ModBlocks.THATLDU_QUANTUM_COLLAPSER.get());

            addDropMachine(ModBlocks.FIBER_OPTIC_TREE.get(), new String[]{"mode"}, false);

            addDropMachine(ModBlocks.MOLECULAR_RECYCLER.get(), new String[]{"invIn", "invAdd", "invOut"}, true);
            addDropMachine(ModBlocks.QUANTUM_TRANSMUTATION_CHAMBER.get(), new String[]{"invIn", "invAdd", "invOut"}, true);
            addDropMachine(ModBlocks.REFINED_INSCRIBER.get(), new String[]{"invTL", "invMid", "invBR", "invOut"}, true);

            addDropMachine(ModBlocks.NOTSOGUDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), new String[]{"invOut"}, false);
            addDropMachine(ModBlocks.KUDBEBEDDA_SINGULARITY_RESOURCE_GENERATOR.get(), new String[]{"invOut"}, false);
            addDropMachine(ModBlocks.NOTARFBADIUM_SINGULARITY_RESOURCE_GENERATOR.get(), new String[]{"invOut"}, false);
            addDropMachine(ModBlocks.WIKIDIUM_SINGULARITY_RESOURCE_GENERATOR.get(), new String[]{"invOut"}, false);
            addDropMachine(ModBlocks.THATLDU_SINGULARITY_RESOURCE_GENERATOR.get(), new String[]{"invOut"}, false);

            addDropMachine(ModBlocks.NOTSOGUDIUM_SINGULARITY_POWER_GENERATOR.get(), new String[]{}, true);
            addDropMachine(ModBlocks.KUDBEBEDDA_SINGULARITY_POWER_GENERATOR.get(), new String[]{}, true);
            addDropMachine(ModBlocks.NOTARFBADIUM_SINGULARITY_POWER_GENERATOR.get(), new String[]{}, true);
            addDropMachine(ModBlocks.WIKIDIUM_SINGULARITY_POWER_GENERATOR.get(), new String[]{}, true);
            addDropMachine(ModBlocks.THATLDU_SINGULARITY_POWER_GENERATOR.get(), new String[]{}, true);

            addDropMachine(ModBlocks.DRYING_CUPBOARD.get(), new String[]{"invIn", "invOut"}, true);

            dropSelf(ModBlocks.CRYOGENIC_DIMENSIONAL_TELEPORTER.get());

            dropSelf(ModBlocks.NOTSOGUDIUM_BLOCK.get());
            dropSelf(ModBlocks.KUDBEBEDDA_BLOCK.get());
            dropSelf(ModBlocks.NOTARFBADIUM_BLOCK.get());
            dropSelf(ModBlocks.WIKIDIUM_BLOCK.get());
            dropSelf(ModBlocks.THATLDU_BLOCK.get());

            dropSelf(ModBlocks.SILICA_DUST_BLOCK.get());
            dropSelf(ModBlocks.CARBON_NANOTUBE_BLOCK.get());
            dropSelf(ModBlocks.PROTEIN_PASTE_BLOCK.get());
            dropSelf(ModBlocks.PROTEIN_FIBER_BLOCK.get());
            dropSelf(ModBlocks.CELLULOSE_BLOCK.get());
            dropSelf(ModBlocks.CELLULOSE_BRICKS.get());
            dropSelf(ModBlocks.CELLULOSE_CHISELED_BRICKS.get());
            dropSelf(ModBlocks.ORGANICALLY_ENRICHED_SAND.get());
            dropSelf(ModBlocks.AGGREGATE.get());

            dropSelf(ModBlocks.ENERGIZED_END_STONE_BRICKS.get());
            dropSelf(ModBlocks.ENERGIZED_NETHER_BRICKS.get());
            dropSelf(ModBlocks.ENERGIZED_STONE_BRICKS.get());
            dropSelf(ModBlocks.ENERGIZED_THATLDUVIUM.get());

            addOreDrops(ModBlocks.NOTSOGUDIUM_ORE, 0.2F, 1.0F, 2.0F, 0.45F, 1.0F, 4.0F, ModItems.NOTSOGUDIUM_PIECE);
            addOreDrops(ModBlocks.KUDBEBEDDA_ORE, 0.3F, 1.0F, 2.0F, 0.55F, 2.0F, 4.0F, ModItems.KUDBEBEDDA_PIECE);
            addOreDrops(ModBlocks.NOTARFBADIUM_ORE, 0.3F, 1.0F, 3.0F, 0.45F, 1.0F, 5.0F, ModItems.NOTARFBADIUM_PIECE);
            addOreDrops(ModBlocks.WIKIDIUM_ORE, 0.35F, 2.0F, 3.0F, 0.40F, 1.0F, 3.0F, ModItems.WIKIDIUM_PIECE);
            addOreDrops(ModBlocks.THATLDU_ORE, 0.35F, 1.0F, 2.0F, 0.35F, 1.0F, 2.0F, ModItems.THATLDU_PIECE);

            addChestDrop(ModBlocks.NOTSOGUDIUM_CHEST);
            addChestDrop(ModBlocks.KUDBEBEDDA_CHEST);
            addChestDrop(ModBlocks.NOTARFBADIUM_CHEST);
            addChestDrop(ModBlocks.WIKIDIUM_CHEST);
            addChestDrop(ModBlocks.THATLDU_CHEST);

            addCraftingTableDrop(ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE);
            addCraftingTableDrop(ModBlocks.KUDBEBEDDA_CRAFTING_TABLE);
            addCraftingTableDrop(ModBlocks.NOTARFBADIUM_CRAFTING_TABLE);
            addCraftingTableDrop(ModBlocks.WIKIDIUM_CRAFTING_TABLE);
            addCraftingTableDrop(ModBlocks.THATLDU_CRAFTING_TABLE);

            addFurnaceDrop(ModBlocks.NOTSOGUDIUM_FURNACE);
            addFurnaceDrop(ModBlocks.KUDBEBEDDA_FURNACE);
            addFurnaceDrop(ModBlocks.NOTARFBADIUM_FURNACE);
            addFurnaceDrop(ModBlocks.WIKIDIUM_FURNACE);
            addFurnaceDrop(ModBlocks.THATLDU_FURNACE);
            addFurnaceDrop(ModBlocks.EUMUS_FURNACE);
        }

        private void addCraftingTableDrop(RegistryObject<Block> craftingTable) {
            dropSelf(craftingTable.get());
        }

        private void addChestDrop(RegistryObject<? extends CuboidChestBlockBase> chest) {
            add(chest.get(), (block) -> createSingleItemTableForChest(chest.get()));
        }

        private void addFurnaceDrop(RegistryObject<? extends CuboidFurnaceBlockBase> furnace) {
            add(furnace.get(), (block) -> createSingleItemTable(furnace.get())
                    .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                    .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)));
        }

        private void addOreDrops(RegistryObject<Block> oreBlock,
                                 float chanceSilica,
                                 float minSilica,
                                 float maxSilica,
                                 float chanceCarbon,
                                 float minCarbon,
                                 float maxCarbon,
                                 RegistryObject<Item> orePiece) {
            add(oreBlock.get(), (block) -> {
                return applyExplosionDecay(
                        block,
                        LootTable.lootTable()
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.SILICA_DUST.get())
                                                .when(LootItemRandomChanceCondition.randomChance(chanceSilica))
                                                .when(HAS_NO_SILK_TOUCH))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minSilica, maxSilica)))
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(ModItems.CARBON_DEPOSIT.get())
                                                .when(LootItemRandomChanceCondition.randomChance(chanceCarbon))
                                                .when(HAS_NO_SILK_TOUCH))
                                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minCarbon, maxCarbon)))
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(orePiece.get())
                                                .when(HAS_NO_SILK_TOUCH))
                                        .apply(ApplyBonusCount.addBonusBinomialDistributionCount(Enchantments.BLOCK_FORTUNE, 0.5714286F, 3))
                                )
                                .withPool(LootPool.lootPool()
                                        .add(LootItem.lootTableItem(oreBlock.get())
                                                .when(HAS_SILK_TOUCH))
                                        .apply(SetItemCountFunction.setCount(ConstantValue.exactly(1)))
                                )
                );
            });
        }

        private void addDropMachine(Block machine, String[] keepInventoryTags, boolean keepEnergy)
        {
            add(machine, (block) -> {
                CopyNbtFunction.Builder nbtBuilder = CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY);
                if (keepInventoryTags != null) {
                    for (String tag: keepInventoryTags) {
                        nbtBuilder = nbtBuilder.copy(tag, "BlockEntityTag." + tag, CopyNbtFunction.MergeStrategy.REPLACE);
                    }
                }
                if (keepEnergy)
                    nbtBuilder = nbtBuilder.copy("energy", "BlockEntityTag.energy", CopyNbtFunction.MergeStrategy.REPLACE);

                return createSingleItemTable(machine)
                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                        .apply(nbtBuilder);
            });
        }

        @Override
        protected Iterable<Block> getKnownBlocks() {
            return Registration.BLOCKS.getEntries().stream()
                    .map(RegistryObject::get)
                    .collect(Collectors.toList());
        }

        protected static LootTable.Builder createSingleItemTableForChest(ItemLike itemProvider) {
            return LootTable.lootTable()
                    .withPool(applyExplosionCondition(itemProvider,
                            LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                                    .add(LootItem.lootTableItem(itemProvider)
                                        .apply(CopyNameFunction.copyName(CopyNameFunction.NameSource.BLOCK_ENTITY))
                                        .apply(CopyNbtFunction.copyData(ContextNbtProvider.BLOCK_ENTITY).copy("Items", "BlockEntityTag.Items", CopyNbtFunction.MergeStrategy.REPLACE)))
                    ));
        }

    }
}
