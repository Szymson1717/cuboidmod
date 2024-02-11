package com.cuboiddroid.cuboidmod.modules.xmas.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.xmas.tile.FiberOpticTreeTileEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
// import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

import net.minecraft.world.level.block.state.BlockBehaviour.Properties;

public class FiberOpticTreeBlock extends Block {
    public static final String ID_STRING = "fiber_optic_tree";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    private static final int LIGHT_VALUE_WHEN_ON = 8;

    public FiberOpticTreeBlock() {
        super(Properties.of(Material.WOOD)
                .strength(3, 9)
                // .harvestLevel(1).harvestTool(ToolType.AXE)
                .sound(SoundType.WOOD));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flag) {
        list.add(new TranslatableComponent("block.cuboidmod.fiber_optic_tree.hover_text"));
    }

    // @Override
    // public boolean hasTileEntity(BlockState state) {
    //     return true;
    // }
    
    // @Nullable
    // @Override
    // public BlockEntity createTileEntity(BlockState state, BlockGetter world) {
    //     return new FiberOpticTreeTileEntity();
    // }

    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return InteractionResult.SUCCESS;
        }

        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof FiberOpticTreeTileEntity) {
            FiberOpticTreeTileEntity tree = (FiberOpticTreeTileEntity) te;

            ItemStack item = player.getItemInHand(hand);

            if (item.isEmpty()) {
                // player used empty hand - try change the mode

                tree.changeMode();

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.CONSUME;
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LEVEL) > 0 ? LIGHT_VALUE_WHEN_ON : 0;
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LEVEL, 0)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
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
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        return VOXEL_SHAPE;
    }
}
