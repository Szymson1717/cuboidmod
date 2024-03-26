package com.cuboiddroid.cuboidmod.datagen.server;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTags;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(DataGenerator generatorIn, ExistingFileHelper existingFileHelper) {
        super(generatorIn, CuboidMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        // notsogudium
        tag(ModTags.Blocks.ORES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);
        tag(Tags.Blocks.NEEDS_WOOD_TOOL).addTag(ModTags.Blocks.ORES_NOTSOGUDIUM);

        tag(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTSOGUDIUM);

        tag(ModTags.Blocks.CHESTS_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_NOTSOGUDIUM);

        tag(ModTags.Blocks.CRAFTING_TABLES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get());

        tag(ModTags.Blocks.FURNACES_NOTSOGUDIUM).add(ModBlocks.NOTSOGUDIUM_FURNACE.get());

        // kudbebedda
        tag(ModTags.Blocks.ORES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);
        tag(ModTags.Blocks.NEEDS_NOTSOGUDIUM_TOOL).addTag(ModTags.Blocks.ORES_KUDBEBEDDA);

        tag(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_KUDBEBEDDA);

        tag(ModTags.Blocks.CHESTS_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_KUDBEBEDDA);

        tag(ModTags.Blocks.CRAFTING_TABLES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get());

        tag(ModTags.Blocks.FURNACES_KUDBEBEDDA).add(ModBlocks.KUDBEBEDDA_FURNACE.get());

        // notarfbadium
        tag(ModTags.Blocks.ORES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);
        tag(ModTags.Blocks.NEEDS_KUDBEBEDDA_TOOL).addTag(ModTags.Blocks.ORES_NOTARFBADIUM);

        tag(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_NOTARFBADIUM);

        tag(ModTags.Blocks.CHESTS_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_NOTARFBADIUM);

        tag(ModTags.Blocks.CRAFTING_TABLES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get());

        tag(ModTags.Blocks.FURNACES_NOTARFBADIUM).add(ModBlocks.NOTARFBADIUM_FURNACE.get());

        // wikidium
        tag(ModTags.Blocks.ORES_WIKIDIUM).add(ModBlocks.WIKIDIUM_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_WIKIDIUM);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_WIKIDIUM);
        tag(ModTags.Blocks.NEEDS_NOTARFBADIUM_TOOL).addTag(ModTags.Blocks.ORES_WIKIDIUM);

        tag(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM).add(ModBlocks.WIKIDIUM_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_WIKIDIUM);

        tag(ModTags.Blocks.CHESTS_WIKIDIUM).add(ModBlocks.WIKIDIUM_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_WIKIDIUM);

        tag(ModTags.Blocks.CRAFTING_TABLES_WIKIDIUM).add(ModBlocks.WIKIDIUM_CRAFTING_TABLE.get());

        tag(ModTags.Blocks.FURNACES_WIKIDIUM).add(ModBlocks.WIKIDIUM_FURNACE.get());

        // thatldu
        tag(ModTags.Blocks.ORES_THATLDU).add(ModBlocks.THATLDU_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.Blocks.ORES_THATLDU);
        tag(BlockTags.MINEABLE_WITH_PICKAXE).addTag(ModTags.Blocks.ORES_THATLDU);
        tag(ModTags.Blocks.NEEDS_WIKIDIUM_TOOL).addTag(ModTags.Blocks.ORES_THATLDU);

        tag(ModTags.Blocks.STORAGE_BLOCKS_THATLDU).add(ModBlocks.THATLDU_BLOCK.get());
        tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.Blocks.STORAGE_BLOCKS_THATLDU);

        tag(ModTags.Blocks.CHESTS_THATLDU).add(ModBlocks.THATLDU_CHEST.get());
        tag(Tags.Blocks.CHESTS).addTag(ModTags.Blocks.CHESTS_THATLDU);

        tag(ModTags.Blocks.CRAFTING_TABLES_THATLDU).add(ModBlocks.THATLDU_CRAFTING_TABLE.get());

        tag(ModTags.Blocks.FURNACES_THATLDU).add(ModBlocks.THATLDU_FURNACE.get());

        tag(ModTags.Blocks.FURNACES_EUMUS).add(ModBlocks.EUMUS_FURNACE.get());


        // storage blocks
        tag(ModTags.Blocks.STORAGE_BLOCKS_CARBON_NANOTUBE).add(ModBlocks.CARBON_NANOTUBE_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_CELLULOSE).add(ModBlocks.CELLULOSE_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_FIBER).add(ModBlocks.PROTEIN_FIBER_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_PROTEIN_PASTE).add(ModBlocks.PROTEIN_PASTE_BLOCK.get());
        tag(ModTags.Blocks.STORAGE_BLOCKS_SILICA).add(ModBlocks.SILICA_DUST_BLOCK.get());
    }
}
