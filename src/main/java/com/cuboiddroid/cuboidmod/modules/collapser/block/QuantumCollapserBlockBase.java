package com.cuboiddroid.cuboidmod.modules.collapser.block;

import com.cuboiddroid.cuboidmod.modules.collapser.tile.QuantumCollapserTileEntityBase;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.stream.Stream;

public abstract class QuantumCollapserBlockBase extends BaseEntityBlock {

    private static final VoxelShape VOXEL_SHAPE_N = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16), Block.box(2, 1, 2, 14, 15, 14), Block.box(1, 15, 1, 15, 16, 15),
            Block.box(0, 1, 0, 3, 2, 3), Block.box(0, 1, 13, 3, 2, 16), Block.box(13, 1, 0, 16, 2, 3),
            Block.box(13, 1, 13, 16, 2, 16), Block.box(0, 3, 0, 1, 13, 1), Block.box(0, 14, 0, 3, 15, 3),
            Block.box(0, 2, 0, 2, 3, 2), Block.box(0, 13, 0, 2, 14, 2), Block.box(0, 2, 14, 2, 3, 16),
            Block.box(14, 2, 14, 16, 3, 16), Block.box(14, 2, 0, 16, 3, 2), Block.box(13, 14, 0, 16, 15, 3),
            Block.box(14, 13, 0, 16, 14, 2), Block.box(15, 3, 0, 16, 13, 1), Block.box(13, 14, 13, 16, 15, 16),
            Block.box(14, 13, 14, 16, 14, 16), Block.box(0, 14, 13, 3, 15, 16), Block.box(0, 13, 14, 2, 14, 16),
            Block.box(0, 3, 15, 1, 13, 16), Block.box(15, 3, 15, 16, 13, 16), Block.box(3, 3, 1, 4, 13, 2),
            Block.box(12, 3, 1, 13, 13, 2), Block.box(14, 1, 3, 15, 15, 13), Block.box(1, 1, 3, 2, 15, 13),
            Block.box(4, 3, 1, 12, 4, 2), Block.box(4, 12, 1, 12, 13, 2), Block.box(3, 1, 14, 13, 15, 15),
            Block.box(2, 15, 0, 14, 16, 1), Block.box(2, 15, 15, 14, 16, 16), Block.box(0, 15, 2, 1, 16, 14),
            Block.box(15, 15, 2, 16, 16, 14), Block.box(4, 4, 1, 12, 12, 2)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape VOXEL_SHAPE_E = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16), Block.box(2, 1, 2, 14, 15, 14), Block.box(1, 15, 1, 15, 16, 15),
            Block.box(13, 1, 0, 16, 2, 3), Block.box(0, 1, 0, 3, 2, 3), Block.box(13, 1, 13, 16, 2, 16),
            Block.box(0, 1, 13, 3, 2, 16), Block.box(15, 3, 0, 16, 13, 1), Block.box(13, 14, 0, 16, 15, 3),
            Block.box(14, 2, 0, 16, 3, 2), Block.box(14, 13, 0, 16, 14, 2), Block.box(0, 2, 0, 2, 3, 2),
            Block.box(0, 2, 14, 2, 3, 16), Block.box(14, 2, 14, 16, 3, 16), Block.box(13, 14, 13, 16, 15, 16),
            Block.box(14, 13, 14, 16, 14, 16), Block.box(15, 3, 15, 16, 13, 16), Block.box(0, 14, 13, 3, 15, 16),
            Block.box(0, 13, 14, 2, 14, 16), Block.box(0, 14, 0, 3, 15, 3), Block.box(0, 13, 0, 2, 14, 2),
            Block.box(0, 3, 0, 1, 13, 1), Block.box(0, 3, 15, 1, 13, 16), Block.box(14, 3, 3, 15, 13, 4),
            Block.box(14, 3, 12, 15, 13, 13), Block.box(3, 1, 14, 13, 15, 15), Block.box(3, 1, 1, 13, 15, 2),
            Block.box(14, 3, 4, 15, 4, 12), Block.box(14, 12, 4, 15, 13, 12), Block.box(1, 1, 3, 2, 15, 13),
            Block.box(15, 15, 2, 16, 16, 14), Block.box(0, 15, 2, 1, 16, 14), Block.box(2, 15, 0, 14, 16, 1),
            Block.box(2, 15, 15, 14, 16, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape VOXEL_SHAPE_S = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16), Block.box(2, 1, 2, 14, 15, 14), Block.box(1, 15, 1, 15, 16, 15),
            Block.box(13, 1, 13, 16, 2, 16), Block.box(13, 1, 0, 16, 2, 3), Block.box(0, 1, 13, 3, 2, 16),
            Block.box(0, 1, 0, 3, 2, 3), Block.box(15, 3, 15, 16, 13, 16), Block.box(13, 14, 13, 16, 15, 16),
            Block.box(14, 2, 14, 16, 3, 16), Block.box(14, 13, 14, 16, 14, 16), Block.box(14, 2, 0, 16, 3, 2),
            Block.box(0, 2, 0, 2, 3, 2), Block.box(0, 2, 14, 2, 3, 16), Block.box(0, 14, 13, 3, 15, 16),
            Block.box(0, 13, 14, 2, 14, 16), Block.box(0, 3, 15, 1, 13, 16), Block.box(0, 14, 0, 3, 15, 3),
            Block.box(0, 13, 0, 2, 14, 2), Block.box(13, 14, 0, 16, 15, 3), Block.box(14, 13, 0, 16, 14, 2),
            Block.box(15, 3, 0, 16, 13, 1), Block.box(0, 3, 0, 1, 13, 1), Block.box(12, 3, 14, 13, 13, 15),
            Block.box(3, 3, 14, 4, 13, 15), Block.box(1, 1, 3, 2, 15, 13), Block.box(14, 1, 3, 15, 15, 13),
            Block.box(4, 3, 14, 12, 4, 15), Block.box(4, 12, 14, 12, 13, 15), Block.box(3, 1, 1, 13, 15, 2),
            Block.box(2, 15, 15, 14, 16, 16), Block.box(2, 15, 0, 14, 16, 1), Block.box(15, 15, 2, 16, 16, 14),
            Block.box(0, 15, 2, 1, 16, 14)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final VoxelShape VOXEL_SHAPE_W = Stream.of(
            Block.box(0, 0, 0, 16, 1, 16), Block.box(2, 1, 2, 14, 15, 14), Block.box(1, 15, 1, 15, 16, 15),
            Block.box(0, 1, 13, 3, 2, 16), Block.box(13, 1, 13, 16, 2, 16), Block.box(0, 1, 0, 3, 2, 3),
            Block.box(13, 1, 0, 16, 2, 3), Block.box(0, 3, 15, 1, 13, 16), Block.box(0, 14, 13, 3, 15, 16),
            Block.box(0, 2, 14, 2, 3, 16), Block.box(0, 13, 14, 2, 14, 16), Block.box(14, 2, 14, 16, 3, 16),
            Block.box(14, 2, 0, 16, 3, 2), Block.box(0, 2, 0, 2, 3, 2), Block.box(0, 14, 0, 3, 15, 3),
            Block.box(0, 13, 0, 2, 14, 2), Block.box(0, 3, 0, 1, 13, 1), Block.box(13, 14, 0, 16, 15, 3),
            Block.box(14, 13, 0, 16, 14, 2), Block.box(13, 14, 13, 16, 15, 16), Block.box(14, 13, 14, 16, 14, 16),
            Block.box(15, 3, 15, 16, 13, 16), Block.box(15, 3, 0, 16, 13, 1), Block.box(1, 3, 12, 2, 13, 13),
            Block.box(1, 3, 3, 2, 13, 4), Block.box(3, 1, 1, 13, 15, 2), Block.box(3, 1, 14, 13, 15, 15),
            Block.box(1, 3, 4, 2, 4, 12), Block.box(1, 12, 4, 2, 13, 12), Block.box(14, 1, 3, 15, 15, 13),
            Block.box(0, 15, 2, 1, 16, 14), Block.box(15, 15, 2, 16, 16, 14), Block.box(2, 15, 15, 14, 16, 16),
            Block.box(2, 15, 0, 14, 16, 1)
    ).reduce((v1, v2) -> {return Shapes.join(v1, v2, BooleanOp.OR);}).get();

    public QuantumCollapserBlockBase(Properties properties) {
        super(properties);
        this.registerDefaultState(this.getStateDefinition().any().setValue(BlockStateProperties.LIT, false));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        switch (state.getValue(BlockStateProperties.HORIZONTAL_FACING)) {
            case EAST:
                return VOXEL_SHAPE_E;
            case SOUTH:
                return VOXEL_SHAPE_S;
            case WEST:
                return VOXEL_SHAPE_W;
            default:
                return VOXEL_SHAPE_N;
        }
    }

    @Override
    public RenderShape getRenderShape(BlockState p_49232_) {
        return RenderShape.MODEL;
    }

    // @Override
    // public boolean hasTileEntity(BlockState state) {
    //     return true;
    // }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rayTrace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return InteractionResult.SUCCESS;
        }

        this.interactWith(level, pos, player);
        return InteractionResult.CONSUME;
    }

    private void interactWith(Level level, BlockPos pos, Player player) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof QuantumCollapserTileEntityBase) {
            QuantumCollapserTileEntityBase collapserTileEntity = (QuantumCollapserTileEntityBase) tileEntity;
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return collapserTileEntity.getDisplayName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return collapserTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openGui((ServerPlayer) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        // check we're removing the right block
        if (!state.is(newState.getBlock())) {
            BlockEntity tileEntity = level.getBlockEntity(pos);
            if (tileEntity instanceof Container) {
                Containers.dropContents(level, pos, (Container) tileEntity);
                level.updateNeighbourForOutputSignal(pos, this);
            }
            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(BlockStateProperties.HORIZONTAL_FACING, rotation.rotate(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @SuppressWarnings("deprecation")
    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(BlockStateProperties.HORIZONTAL_FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING);
        builder.add(BlockStateProperties.LIT);
    }
}
