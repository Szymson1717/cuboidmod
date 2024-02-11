package com.cuboiddroid.cuboidmod.setup;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.tags.ItemTags;
import net.minecraft.resources.ResourceLocation;

public class ModTags {
    public static final class Fluids {
        public static final Tag.Named<Fluid> MINECRAFT_FLUID_WATER = minecraft("water");
        public static final Tag.Named<Fluid> MINECRAFT_FLUID_LAVA = minecraft("lava");

        private static Tag.Named<Fluid> forge(String path) {
            return FluidTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Fluid> minecraft(String path) {
            return FluidTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Fluid> mod(String path) {
            return FluidTags.bind(new ResourceLocation(CuboidMod.MOD_ID, path).toString());
        }
    }

    public static final class Blocks {
        public static final Tag.Named<Block> ORES_NOTSOGUDIUM = forge("ores/notsogudium");
        public static final Tag.Named<Block> STORAGE_BLOCKS_NOTSOGUDIUM = forge("storage_blocks/notsogudium");
        public static final Tag.Named<Block> CHESTS_NOTSOGUDIUM = forge("chests/notsogudium");
        public static final Tag.Named<Block> CRAFTING_TABLES_NOTSOGUDIUM = forge("crafting_tables/notsogudium");
        public static final Tag.Named<Block> FURNACES_NOTSOGUDIUM = mod("furnaces/notsogudium");

        public static final Tag.Named<Block> ORES_KUDBEBEDDA = forge("ores/kudbebedda");
        public static final Tag.Named<Block> STORAGE_BLOCKS_KUDBEBEDDA = forge("storage_blocks/kudbebedda");
        public static final Tag.Named<Block> CHESTS_KUDBEBEDDA = forge("chests/kudbebedda");
        public static final Tag.Named<Block> CRAFTING_TABLES_KUDBEBEDDA = forge("crafting_tables/kudbebedda");
        public static final Tag.Named<Block> FURNACES_KUDBEBEDDA = mod("furnaces/kudbebedda");

        public static final Tag.Named<Block> ORES_NOTARFBADIUM = forge("ores/notarfbadium");
        public static final Tag.Named<Block> STORAGE_BLOCKS_NOTARFBADIUM = forge("storage_blocks/notarfbadium");
        public static final Tag.Named<Block> CHESTS_NOTARFBADIUM = forge("chests/notarfbadium");
        public static final Tag.Named<Block> CRAFTING_TABLES_NOTARFBADIUM = forge("crafting_tables/notarfbadium");
        public static final Tag.Named<Block> FURNACES_NOTARFBADIUM = mod("furnaces/notarfbadium");

        public static final Tag.Named<Block> ORES_WIKIDIUM = forge("ores/wikidium");
        public static final Tag.Named<Block> STORAGE_BLOCKS_WIKIDIUM = forge("storage_blocks/wikidium");
        public static final Tag.Named<Block> CHESTS_WIKIDIUM = forge("chests/wikidium");
        public static final Tag.Named<Block> CRAFTING_TABLES_WIKIDIUM = forge("crafting_tables/wikidium");
        public static final Tag.Named<Block> FURNACES_WIKIDIUM = mod("furnaces/wikidium");

        public static final Tag.Named<Block> ORES_THATLDU = forge("ores/thatldu");
        public static final Tag.Named<Block> STORAGE_BLOCKS_THATLDU = forge("storage_blocks/thatldu");
        public static final Tag.Named<Block> CHESTS_THATLDU = forge("chests/thatldu");
        public static final Tag.Named<Block> CRAFTING_TABLES_THATLDU = forge("crafting_tables/thatldu");
        public static final Tag.Named<Block> FURNACES_THATLDU = mod("furnaces/thatldu");
        public static final Tag.Named<Block> FURNACES_EUMUS = mod("furnaces/eumus");

        public static final Tag.Named<Block> STORAGE_BLOCKS_SILICA = forge("storage_blocks/silica");
        public static final Tag.Named<Block> STORAGE_BLOCKS_CARBON_NANOTUBE = forge("storage_blocks/carbon_nanotube");
        public static final Tag.Named<Block> STORAGE_BLOCKS_PROTEIN_PASTE = forge("storage_blocks/protein_paste");
        public static final Tag.Named<Block> STORAGE_BLOCKS_PROTEIN_FIBER = forge("storage_blocks/protein_fiber");
        public static final Tag.Named<Block> STORAGE_BLOCKS_CELLULOSE = forge("storage_blocks/cellulose");

        private static Tag.Named<Block> forge(String path) {
            return BlockTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Block> minecraft(String path) {
            return BlockTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Block> mod(String path) {
            return BlockTags.bind(new ResourceLocation(CuboidMod.MOD_ID, path).toString());
        }
    }

    public static final class Items {
        public static final Tag.Named<Item> ORES = mod("ores");
        public static final Tag.Named<Item> FORGE_ORES = forge("ores");
        public static final Tag.Named<Item> STORAGE_BLOCKS = mod("storage_blocks");
        public static final Tag.Named<Item> INGOTS = mod("ingots");
        public static final Tag.Named<Item> NUGGETS = mod("nuggets");
        public static final Tag.Named<Item> DUSTS = mod("dusts");
        public static final Tag.Named<Item> PIECES = mod("pieces");
        public static final Tag.Named<Item> RODS = mod("rods");
        public static final Tag.Named<Item> CHESTS = mod("chests");
        public static final Tag.Named<Item> FOOD = mod("food");
        public static final Tag.Named<Item> CRAFTING_TABLES = mod("crafting_tables");
        public static final Tag.Named<Item> FURNACES = mod("furnaces");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES = mod("quantum_singularities");

        // notsogudium
        public static final Tag.Named<Item> ORES_NOTSOGUDIUM = forge("ores/notsogudium");
        public static final Tag.Named<Item> STORAGE_BLOCKS_NOTSOGUDIUM = forge("storage_blocks/notsogudium");
        public static final Tag.Named<Item> CHESTS_NOTSOGUDIUM = forge("chests/notsogudium");
        public static final Tag.Named<Item> CRAFTING_TABLES_NOTSOGUDIUM = forge("crafting_tables/notsogudium");
        public static final Tag.Named<Item> FURNACES_NOTSOGUDIUM = forge("furnaces/notsogudium");

        public static final Tag.Named<Item> INGOTS_NOTSOGUDIUM = forge("ingots/notsogudium");
        public static final Tag.Named<Item> NUGGETS_NOTSOGUDIUM = forge("nuggets/notsogudium");
        public static final Tag.Named<Item> DUSTS_NOTSOGUDIUM = forge("dusts/notsogudium");
        public static final Tag.Named<Item> RODS_NOTSOGUDIUM = forge("rods/notsogudium");

        public static final Tag.Named<Item> PIECES_NOTSOGUDIUM = mod("pieces/notsogudium");

        // kudbebedda
        public static final Tag.Named<Item> ORES_KUDBEBEDDA = forge("ores/kudbebedda");
        public static final Tag.Named<Item> STORAGE_BLOCKS_KUDBEBEDDA = forge("storage_blocks/kudbebedda");
        public static final Tag.Named<Item> CHESTS_KUDBEBEDDA = forge("chests/kudbebedda");
        public static final Tag.Named<Item> CRAFTING_TABLES_KUDBEBEDDA = forge("crafting_tables/kudbebedda");
        public static final Tag.Named<Item> FURNACES_KUDBEBEDDA = forge("furnaces/kudbebedda");

        public static final Tag.Named<Item> INGOTS_KUDBEBEDDA = forge("ingots/kudbebedda");
        public static final Tag.Named<Item> NUGGETS_KUDBEBEDDA = forge("nuggets/kudbebedda");
        public static final Tag.Named<Item> DUSTS_KUDBEBEDDA = forge("dusts/kudbebedda");
        public static final Tag.Named<Item> RODS_KUDBEBEDDA = forge("rods/kudbebedda");

        public static final Tag.Named<Item> PIECES_KUDBEBEDDA = mod("pieces/kudbebedda");

        // notarfbadium
        public static final Tag.Named<Item> ORES_NOTARFBADIUM = forge("ores/notarfbadium");
        public static final Tag.Named<Item> STORAGE_BLOCKS_NOTARFBADIUM = forge("storage_blocks/notarfbadium");
        public static final Tag.Named<Item> CHESTS_NOTARFBADIUM = forge("chests/notarfbadium");
        public static final Tag.Named<Item> CRAFTING_TABLES_NOTARFBADIUM = forge("crafting_tables/notarfbadium");
        public static final Tag.Named<Item> FURNACES_NOTARFBADIUM = forge("furnaces/notarfbadium");

        public static final Tag.Named<Item> INGOTS_NOTARFBADIUM = forge("ingots/notarfbadium");
        public static final Tag.Named<Item> NUGGETS_NOTARFBADIUM = forge("nuggets/notarfbadium");
        public static final Tag.Named<Item> DUSTS_NOTARFBADIUM = forge("dusts/notarfbadium");
        public static final Tag.Named<Item> RODS_NOTARFBADIUM = forge("rods/notarfbadium");

        public static final Tag.Named<Item> PIECES_NOTARFBADIUM = mod("pieces/notarfbadium");

        // wikidium
        public static final Tag.Named<Item> ORES_WIKIDIUM = forge("ores/wikidium");
        public static final Tag.Named<Item> STORAGE_BLOCKS_WIKIDIUM = forge("storage_blocks/wikidium");
        public static final Tag.Named<Item> CHESTS_WIKIDIUM = forge("chests/wikidium");
        public static final Tag.Named<Item> CRAFTING_TABLES_WIKIDIUM = forge("crafting_tables/wikidium");
        public static final Tag.Named<Item> FURNACES_WIKIDIUM = forge("furnaces/wikidium");

        public static final Tag.Named<Item> INGOTS_WIKIDIUM = forge("ingots/wikidium");
        public static final Tag.Named<Item> NUGGETS_WIKIDIUM = forge("nuggets/wikidium");
        public static final Tag.Named<Item> DUSTS_WIKIDIUM = forge("dusts/wikidium");
        public static final Tag.Named<Item> RODS_WIKIDIUM = forge("rods/wikidium");

        public static final Tag.Named<Item> PIECES_WIKIDIUM = mod("pieces/wikidium");

        // thatldu
        public static final Tag.Named<Item> ORES_THATLDU = forge("ores/thatldu");
        public static final Tag.Named<Item> STORAGE_BLOCKS_THATLDU = forge("storage_blocks/thatldu");
        public static final Tag.Named<Item> CHESTS_THATLDU = forge("chests/thatldu");
        public static final Tag.Named<Item> CRAFTING_TABLES_THATLDU = forge("crafting_tables/thatldu");
        public static final Tag.Named<Item> FURNACES_THATLDU = forge("furnaces/thatldu");

        public static final Tag.Named<Item> INGOTS_THATLDU = forge("ingots/thatldu");
        public static final Tag.Named<Item> NUGGETS_THATLDU = forge("nuggets/thatldu");
        public static final Tag.Named<Item> DUSTS_THATLDU = forge("dusts/thatldu");
        public static final Tag.Named<Item> RODS_THATLDU = forge("rods/thatldu");

        public static final Tag.Named<Item> PIECES_THATLDU = mod("pieces/thatldu");

        // eumus
        public static final Tag.Named<Item> FURNACES_EUMUS = forge("furnaces/eumus");

        // silica dust
        public static final Tag.Named<Item> DUSTS_SILICA = forge("dusts/silica");
        public static final Tag.Named<Item> STORAGE_BLOCKS_SILICA = forge("storage_blocks/silica");

        // additional dusts
        public static final Tag.Named<Item> DUSTS_ZINC = forge("dusts/zinc");
        public static final Tag.Named<Item> DUSTS_COBALT = forge("dusts/cobalt");

        // salt
        public static final Tag.Named<Item> DUSTS_SALT = forge("dusts/salt");

        // carbon deposit
        public static final Tag.Named<Item> PIECES_CARBON_DEPOSIT = forge("pieces/carbon_deposit");

        // carbon nanotube
        public static final Tag.Named<Item> STORAGE_BLOCKS_CARBON_NANOTUBE = forge("storage_blocks/carbon_nanotube");
        public static final Tag.Named<Item> CARBON_NANOTUBE = mod("carbon_nanotube");

        // protein paste
        public static final Tag.Named<Item> STORAGE_BLOCKS_PROTEIN_PASTE = forge("storage_blocks/protein_paste");
        public static final Tag.Named<Item> PROTEIN_PASTE = mod("protein_paste");

        // protein fiber
        public static final Tag.Named<Item> STORAGE_BLOCKS_PROTEIN_FIBER = forge("storage_blocks/protein_fiber");
        public static final Tag.Named<Item> PROTEIN_FIBER = mod("protein_fiber");

        // cellulose
        public static final Tag.Named<Item> STORAGE_BLOCKS_CELLULOSE = forge("storage_blocks/cellulose");
        public static final Tag.Named<Item> CELLULOSE = mod("cellulose");

        // stick bundle
        public static final Tag.Named<Item> STICK_BUNDLE = mod("stick_bundle");

        // food
        public static final Tag.Named<Item> FOOD_APPLE_CIDER_VINEGAR = mod("food/apple_cider_vinegar");
        public static final Tag.Named<Item> FOOD_ARACHNUGGET = mod("food/arachnugget");
        public static final Tag.Named<Item> FOOD_BROTH = mod("food/broth");
        public static final Tag.Named<Item> FOOD_KEBAB_COOKED = mod("food/kebab_cooked");
        public static final Tag.Named<Item> FOOD_GRUEL = mod("food/gruel");
        public static final Tag.Named<Item> FOOD_PROTEIN_BAR = mod("food/protein_bar");
        public static final Tag.Named<Item> FOOD_PROTEIN_PASTE = mod("food/protein_paste");
        public static final Tag.Named<Item> FOOD_KEBAB_RAW = mod("food/kebab_raw");
        public static final Tag.Named<Item> FOOD_RATION_PACK = mod("food/ration_pack");
        public static final Tag.Named<Item> FOOD_ROTTEN_APPLE = mod("food/rotten_apple");

        public static final Tag.Named<Item> FOOD_CURED_BEEF = mod("food/cured_beef");
        public static final Tag.Named<Item> FOOD_CURED_FLESH = mod("food/cured_flesh");
        public static final Tag.Named<Item> FOOD_BEEF_BILTONG = mod("food/beef_biltong");
        public static final Tag.Named<Item> FOOD_ZOMBIE_BILTONG = mod("food/zombie_biltong");
        public static final Tag.Named<Item> FOOD_HARDFISKUR = mod("food/hardfiskur");

        // quantum singularities
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_ANDESITE = mod("quantum_singularities/andesite");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_BASALT = mod("quantum_singularities/basalt");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_BLACKSTONE = mod("quantum_singularities/blackstone");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_CARBON_NANOTUBE = mod("quantum_singularities/carbon_nanotube");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_CELLULOSE = mod("quantum_singularities/cellulose");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_CLAY = mod("quantum_singularities/clay");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_COAL = mod("quantum_singularities/coal");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_COBBLESTONE = mod("quantum_singularities/cobblestone");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_DIORITE = mod("quantum_singularities/diorite");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_DIRT = mod("quantum_singularities/dirt");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_DUST = mod("quantum_singularities/dust");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_GLOWSTONE = mod("quantum_singularities/glowstone");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_GRANITE = mod("quantum_singularities/granite");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_GRAVEL = mod("quantum_singularities/gravel");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_KUDBEBEDDA = mod("quantum_singularities/kudbebedda");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LAPIS = mod("quantum_singularities/lapis");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_ACACIA = mod("quantum_singularities/log_acacia");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_BIRCH = mod("quantum_singularities/log_birch");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_DARK_OAK = mod("quantum_singularities/log_dark_oak");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_JUNGLE = mod("quantum_singularities/log_jungle");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_OAK = mod("quantum_singularities/log_oak");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LOG_SPRUCE = mod("quantum_singularities/log_spruce");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_NOTARFBADIUM = mod("quantum_singularities/notarfbadium");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_NOTSOGUDIUM = mod("quantum_singularities/notsogudium");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_REDSTONE = mod("quantum_singularities/redstone");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_SAND = mod("quantum_singularities/sand");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_SILICA_DUST = mod("quantum_singularities/silica_dust");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_THATLDU = mod("quantum_singularities/thatldu");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_WIKIDIUM = mod("quantum_singularities/wikidium");

        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_ENDSTONE = mod("quantum_singularities/endstone");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_NETHERRACK = mod("quantum_singularities/netherrack");

        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_ZINC = mod("quantum_singularities/zinc");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_TIN = mod("quantum_singularities/tin");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_COPPER = mod("quantum_singularities/copper");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_NICKEL = mod("quantum_singularities/nickel");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_SILVER = mod("quantum_singularities/silver");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_ALUMINIUM = mod("quantum_singularities/aluminium");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_IRON = mod("quantum_singularities/iron");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_GOLD = mod("quantum_singularities/gold");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_LEAD = mod("quantum_singularities/lead");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_URANIUM = mod("quantum_singularities/uranium");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_URANINITE = mod("quantum_singularities/uraninite");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_OSMIUM = mod("quantum_singularities/osmium");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_COBALT = mod("quantum_singularities/cobalt");

        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_DIAMOND = mod("quantum_singularities/diamond");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_EMERALD = mod("quantum_singularities/emerald");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_STEEL = mod("quantum_singularities/steel");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_BRONZE = mod("quantum_singularities/bronze");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_ELECTRUM = mod("quantum_singularities/electrum");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_INVAR = mod("quantum_singularities/invar");
        public static final Tag.Named<Item> QUANTUM_SINGULARITIES_PLATINUM = mod("quantum_singularities/platinum");

        private static Tag.Named<Item> forge(String path) {
            return ItemTags.bind(new ResourceLocation("forge", path).toString());
        }

        private static Tag.Named<Item> minecraft(String path) {
            return ItemTags.bind(new ResourceLocation("minecraft", path).toString());
        }

        private static Tag.Named<Item> mod(String path) {
            return ItemTags.bind(new ResourceLocation(CuboidMod.MOD_ID, path).toString());
        }
    }
}
