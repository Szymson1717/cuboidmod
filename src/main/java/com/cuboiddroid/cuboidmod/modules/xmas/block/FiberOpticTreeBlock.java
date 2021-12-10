package com.cuboiddroid.cuboidmod.modules.xmas.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.xmas.tile.FiberOpticTreeTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class FiberOpticTreeBlock extends Block {
    public static final String ID_STRING = "fiber_optic_tree";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    private static final int LIGHT_VALUE_WHEN_ON = 8;

    public FiberOpticTreeBlock() {
        super(Properties.of(Material.WOOD)
                .strength(3, 9)
                .harvestLevel(1).harvestTool(ToolType.AXE)
                .sound(SoundType.WOOD));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("block.cuboidmod.fiber_optic_tree.hover_text"));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new FiberOpticTreeTileEntity();
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return ActionResultType.SUCCESS;
        }

        TileEntity te = level.getBlockEntity(pos);
        if (te instanceof FiberOpticTreeTileEntity) {
            FiberOpticTreeTileEntity tree = (FiberOpticTreeTileEntity) te;

            ItemStack item = player.getItemInHand(hand);

            if (item.isEmpty()) {
                // player used empty hand - try change the mode

                tree.changeMode();

                return ActionResultType.SUCCESS;
            }
        }

        return ActionResultType.CONSUME;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LEVEL) > 0 ? LIGHT_VALUE_WHEN_ON : 0;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LEVEL, 0)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LEVEL);
    }

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16),
            Block.box(1, 1, 1, 15, 2, 15),
            Block.box(7, 2, 7, 9, 15, 9),
            Block.box(7.5, 15, 7.5, 8.5, 16, 8.5),
            Block.box(6, 12, 6, 10, 13, 10),
            Block.box(5, 9.5, 5, 11, 10.5, 11),
            Block.box(4, 7, 4, 12, 8, 12),
            Block.box(3, 4, 3, 13, 5, 13),
            Block.box(0, 1, 0, 16, 2, 1),
            Block.box(0, 1, 1, 1, 2, 15),
            Block.box(0, 1, 15, 16, 2, 16),
            Block.box(15, 1, 1, 16, 2, 15)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE;
    }
}
