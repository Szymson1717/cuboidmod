package com.cuboiddroid.cuboidmod.datagen.client;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.BlockModelBuilder;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput gen, ExistingFileHelper exFileHelper) {
        super(gen, CuboidMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        registerSimpleBlocks();
        registerFurnaces();
        registerCraftingTables();
        registerDryingCupboard();
        //registerRefinedInscriber();
    }

    private void registerSimpleBlocks() {
        this.simpleBlock(ModBlocks.NOTSOGUDIUM_BLOCK.get());
        this.simpleBlock(ModBlocks.NOTSOGUDIUM_ORE.get());

        this.simpleBlock(ModBlocks.KUDBEBEDDA_BLOCK.get());
        this.simpleBlock(ModBlocks.KUDBEBEDDA_ORE.get());

        this.simpleBlock(ModBlocks.NOTARFBADIUM_BLOCK.get());
        this.simpleBlock(ModBlocks.NOTARFBADIUM_ORE.get());

        this.simpleBlock(ModBlocks.WIKIDIUM_BLOCK.get());
        this.simpleBlock(ModBlocks.WIKIDIUM_ORE.get());

        this.simpleBlock(ModBlocks.THATLDU_BLOCK.get());
        this.simpleBlock(ModBlocks.THATLDU_ORE.get());

        this.simpleBlock(ModBlocks.SILICA_DUST_BLOCK.get());
        this.simpleBlock(ModBlocks.CARBON_NANOTUBE_BLOCK.get());
        this.simpleBlock(ModBlocks.PROTEIN_PASTE_BLOCK.get());
        this.simpleBlock(ModBlocks.PROTEIN_FIBER_BLOCK.get());
        this.simpleBlock(ModBlocks.CELLULOSE_BLOCK.get());
        this.simpleBlock(ModBlocks.CELLULOSE_BRICKS.get());
        this.simpleBlock(ModBlocks.CELLULOSE_CHISELED_BRICKS.get());
        this.simpleBlock(ModBlocks.ORGANICALLY_ENRICHED_SAND.get());
        this.simpleBlock(ModBlocks.AGGREGATE.get());

        this.simpleBlock(ModBlocks.ENERGIZED_END_STONE_BRICKS.get());
        this.simpleBlock(ModBlocks.ENERGIZED_NETHER_BRICKS.get());
        this.simpleBlock(ModBlocks.ENERGIZED_STONE_BRICKS.get());
        this.simpleBlock(ModBlocks.ENERGIZED_THATLDUVIUM.get());
    }

    private void registerCraftingTables() {
        registerCraftingTableBlock("notsogudium", ModBlocks.NOTSOGUDIUM_CRAFTING_TABLE.get());
        registerCraftingTableBlock("kudbebedda", ModBlocks.KUDBEBEDDA_CRAFTING_TABLE.get());
        registerCraftingTableBlock("notarfbadium", ModBlocks.NOTARFBADIUM_CRAFTING_TABLE.get());
        registerCraftingTableBlock("wikidium", ModBlocks.WIKIDIUM_CRAFTING_TABLE.get());
        registerCraftingTableBlock("thatldu", ModBlocks.THATLDU_CRAFTING_TABLE.get());
    }

    private void registerCraftingTableBlock(String material, Block block) {
        // textures for faces
        ResourceLocation top = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_crafting_table_top");
        ResourceLocation bottom = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_crafting_table_bottom");
        ResourceLocation front = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_crafting_table_front");
        ResourceLocation side = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_crafting_table_side");

        // models for different states
        //  the textures are provided in order: down, up, north, south, east, west
        BlockModelBuilder model = models().cube("block/" + material + "_crafting_table", bottom, top, front, front, side, side).texture("particle", front);

        // add it, and vary model according to state
        this.simpleBlock(block, model);
    }

    private void registerFurnaces() {
        registerFurnaceBlock("notsogudium", ModBlocks.NOTSOGUDIUM_FURNACE.get());
        registerFurnaceBlock("kudbebedda", ModBlocks.KUDBEBEDDA_FURNACE.get());
        registerFurnaceBlock("notarfbadium", ModBlocks.NOTARFBADIUM_FURNACE.get());
        registerFurnaceBlock("wikidium", ModBlocks.WIKIDIUM_FURNACE.get());
        registerFurnaceBlock("thatldu", ModBlocks.THATLDU_FURNACE.get());

        registerFurnaceBlock("eumus", ModBlocks.EUMUS_FURNACE.get());
    }

    private void registerDryingCupboard() {
        // textures for faces
        ResourceLocation top = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_top");
        ResourceLocation front = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_front");
        ResourceLocation front_on = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_front_lit");
        ResourceLocation back = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_back");
        ResourceLocation back_on = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_back_lit");
        ResourceLocation left_side = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_left");
        ResourceLocation right_side = new ResourceLocation(CuboidMod.MOD_ID, "block/drying_cupboard_right");

        // models for different states
        //  the textures are provided in order: down, up, north, south, east, west
        BlockModelBuilder modelCupboard = models().cube("block/drying_cupboard", top, top, front, back, left_side, right_side).texture("particle", left_side);
        BlockModelBuilder modelCupboardLit = models().cube("block/drying_cupboard_on", top, top, front_on, back_on, left_side, right_side).texture("particle", left_side);

        // add it, and vary model according to state
        this.orientedHorizontalBlock(
                ModBlocks.DRYING_CUPBOARD.get(),
                state -> state.getValue(BlockStateProperties.LIT)
                        ? modelCupboardLit
                        : modelCupboard);
    }

    private void registerRefinedInscriber() {
        // textures for faces
        ResourceLocation top = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_top");
        ResourceLocation front = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_front");
        ResourceLocation front_on = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_front_lit");
        ResourceLocation back = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_back");
        ResourceLocation back_on = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_back_lit");
        ResourceLocation left_side = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_left");
        ResourceLocation right_side = new ResourceLocation(CuboidMod.MOD_ID, "block/refined_inscriber_right");

        // models for different states
        //  the textures are provided in order: down, up, north, south, east, west
        BlockModelBuilder modelInscriber = models().cube("block/refined_inscriber", top, top, front, back, left_side, right_side).texture("particle", left_side);
        BlockModelBuilder modelInscriberLit = models().cube("block/refined_inscriber_on", top, top, front_on, back_on, left_side, right_side).texture("particle", left_side);

        // add it, and vary model according to state
        this.orientedHorizontalBlock(
                ModBlocks.REFINED_INSCRIBER.get(),
                state -> state.getValue(BlockStateProperties.LIT)
                        ? modelInscriberLit
                        : modelInscriber);
    }

    private void registerFurnaceBlock(String material, Block block) {
        // textures for faces
        ResourceLocation top = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_furnace_top");
        ResourceLocation front = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_furnace_front");
        ResourceLocation front_on = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_furnace_front_on");
        ResourceLocation side = new ResourceLocation(CuboidMod.MOD_ID, "block/" + material + "_furnace_side");

        // models for different states
        //  the textures are provided in order: down, up, north, south, east, west
        BlockModelBuilder modelFurnace = models().cube("block/" + material + "_furnace", top, top, front, side, side, side).texture("particle", side);
        BlockModelBuilder modelFurnaceLit = models().cube("block/" + material + "_furnace_on", top, top, front_on, side, side, side).texture("particle", side);

        // add it, and vary model according to state
        this.orientedHorizontalBlock(
                block,
                state -> state.getValue(BlockStateProperties.LIT)
                        ? modelFurnaceLit
                        : modelFurnace);
    }

    // helper class for creating block states for blocks that are put
    // down facing a specific direction (any of the 6)
    private void orientedBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationX(dir.getAxis() == Direction.Axis.Y ?  dir.getAxisDirection().getStep() * -90 : 0)
                            .rotationY(dir.getAxis() != Direction.Axis.Y ? ((dir.get2DDataValue() + 2) % 4) * 90 : 0)
                            .build();
                });
    }

    // helper class for creating block states for blocks that are put
    // down facing a specific horizontal direction (i.e. not up or down)
    private void orientedHorizontalBlock(Block block, Function<BlockState, ModelFile> modelFunc) {
        getVariantBuilder(block)
                .forAllStates(state -> {
                    Direction dir = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                    return ConfiguredModel.builder()
                            .modelFile(modelFunc.apply(state))
                            .rotationY(((dir.get2DDataValue() + 2) % 4) * 90)
                            .build();
                });
    }
}
