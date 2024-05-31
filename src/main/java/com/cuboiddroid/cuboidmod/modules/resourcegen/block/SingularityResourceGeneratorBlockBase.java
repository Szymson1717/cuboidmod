package com.cuboiddroid.cuboidmod.modules.resourcegen.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.SingularityResourceGeneratorTileEntityBase;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
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
import java.util.List;
import java.util.stream.Stream;

public abstract class SingularityResourceGeneratorBlockBase extends BaseEntityBlock {

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(3, 2, 6, 13, 3, 10), Block.box(3, 13, 6, 13, 14, 10),Block.box(4, 3, 6, 12, 5, 10),
            Block.box(4, 11, 6, 12, 13, 10), Block.box(4.4, 10, 6.2, 11.6, 11.9, 9.8),
            Block.box(4.76, 9, 6.38, 11.24, 10, 9.620000000000001), Block.box(4.76, 6, 6.38, 11.24, 7, 9.620000000000001),
            Block.box(4.922000000000001, 7, 6.461, 11.078, 9, 9.539000000000001), Block.box(4.4, 4, 6.2, 11.6, 5.9, 9.8),
            Block.box(2, 1, 6, 14, 2, 10), Block.box(2, 14, 6, 14, 15, 10), Block.box(6, 2, 3, 10, 3, 6),
            Block.box(6, 13, 3, 10, 14, 6), Block.box(6, 3, 4, 10, 5, 6), Block.box(6, 11, 4, 10, 13, 6),
            Block.box(6.2, 10, 4.4, 9.8, 11.9, 6.2), Block.box(6.38, 9, 4.76, 9.620000000000001, 10, 6.38),
            Block.box(6.38, 6, 4.76, 9.620000000000001, 7, 6.38), Block.box(6.461, 7, 4.922000000000001, 9.539000000000001, 9, 6.461),
            Block.box(6.2, 4, 4.4, 9.8, 5.9, 6.2), Block.box(6, 1, 2, 10, 2, 6), Block.box(6, 14, 2, 10, 15, 6),
            Block.box(6, 2, 10, 10, 3, 13), Block.box(6, 13, 10, 10, 14, 13), Block.box(6, 3, 10, 10, 5, 12),
            Block.box(6, 11, 10, 10, 13, 12), Block.box(6.2, 10, 9.8, 9.8, 11.9, 11.6),
            Block.box(6.38, 9, 9.620000000000001, 9.620000000000001, 10, 11.24),
            Block.box(6.38, 6, 9.620000000000001, 9.620000000000001, 7, 11.24),
            Block.box(6.461, 7, 9.539000000000001, 9.539000000000001, 9, 11.078),
            Block.box(6.2, 4, 9.8, 9.8, 5.9, 11.6), Block.box(6, 1, 10, 10, 2, 14), Block.box(6, 14, 10, 10, 15, 14),
            Block.box(10, 2, 10, 12, 3, 12), Block.box(10, 13, 10, 12, 14, 12), Block.box(10, 3, 10, 11, 5, 11),
            Block.box(10, 11, 10, 11, 13, 11), Block.box(9.8, 10, 9.8, 10.7, 11.9, 10.7),
            Block.box(9.620000000000001, 9, 9.620000000000001, 10.43, 10, 10.43),
            Block.box(9.620000000000001, 6, 9.620000000000001, 10.43, 7, 10.43),
            Block.box(9.539000000000001, 7, 9.539000000000001, 10.308499999999999, 9, 10.308499999999999),
            Block.box(9.8, 4, 9.8, 10.7, 5.9, 10.7), Block.box(10, 1, 10, 13, 2, 13), Block.box(10, 14, 10, 13, 15, 13),
            Block.box(4, 2, 10, 6, 3, 12), Block.box(4, 13, 10, 6, 14, 12), Block.box(5, 3, 10, 6, 5, 11),
            Block.box(5, 11, 10, 6, 13, 11), Block.box(5.3, 10, 9.8, 6.2, 11.9, 10.7),
            Block.box(5.57, 9, 9.620000000000001, 6.38, 10, 10.43),
            Block.box(5.57, 6, 9.620000000000001, 6.38, 7, 10.43),
            Block.box(5.6915000000000004, 7, 9.539000000000001, 6.461, 9, 10.308499999999999),
            Block.box(5.3, 4, 9.8, 6.2, 5.9, 10.7), Block.box(3, 1, 10, 6, 2, 13), Block.box(3, 14, 10, 6, 15, 13),
            Block.box(4, 2, 4, 6, 3, 6), Block.box(4, 13, 4, 6, 14, 6), Block.box(5, 3, 5, 6, 5, 6),
            Block.box(5, 11, 5, 6, 13, 6), Block.box(5.3, 10, 5.3, 6.2, 11.9, 6.2),
            Block.box(5.57, 9, 5.57, 6.38, 10, 6.38), Block.box(5.57, 6, 5.57, 6.38, 7, 6.38),
            Block.box(5.6915000000000004, 7, 5.6915000000000004, 6.461, 9, 6.461),
            Block.box(5.3, 4, 5.3, 6.2, 5.9, 6.2), Block.box(3, 1, 3, 6, 2, 6), Block.box(3, 14, 3, 6, 15, 6),
            Block.box(10, 2, 4, 12, 3, 6), Block.box(10, 13, 4, 12, 14, 6), Block.box(10, 3, 5, 11, 5, 6),
            Block.box(10, 11, 5, 11, 13, 6), Block.box(9.8, 10, 5.3, 10.7, 11.9, 6.2), Block.box(9.620000000000001, 9, 5.57, 10.43, 10, 6.38),
            Block.box(9.620000000000001, 6, 5.57, 10.43, 7, 6.38),
            Block.box(9.539000000000001, 7, 5.6915000000000004, 10.308499999999999, 9, 6.461),
            Block.box(9.8, 4, 5.3, 10.7, 5.9, 6.2), Block.box(10, 1, 3, 13, 2, 6), Block.box(10, 14, 3, 13, 15, 6),
            Block.box(0, 1, 14, 2, 15, 16), Block.box(14, 1, 14, 16, 15, 16), Block.box(0, 1, 0, 2, 15, 2),
            Block.box(14, 1, 0, 16, 15, 2), Block.box(0, 15, 0, 16, 16, 16), Block.box(0, 0, 0, 16, 1, 16)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    private static final int LIGHT_VALUE_WHEN_PRODUCING = 14;

    public SingularityResourceGeneratorBlockBase(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        return VOXEL_SHAPE;
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
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(Component.translatable(CuboidMod.MOD_ID + ".hover_text.singularity_resource_generator"));
    }

    @Override
    public int getLightEmission(BlockState state, BlockGetter world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LIT) ? LIGHT_VALUE_WHEN_PRODUCING : 0;
    }

    // /**
    //  * @param state - the block state
    //  * @param world - the current world
    //  * @return the new, appropriate tile entity instance
    //  */
    // @Nullable
    // @Override
    // public abstract BlockEntity createTileEntity(BlockState state, BlockGetter world);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

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
        if (tileEntity instanceof SingularityResourceGeneratorTileEntityBase) {
            SingularityResourceGeneratorTileEntityBase srgTileEntity = (SingularityResourceGeneratorTileEntityBase) tileEntity;
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return srgTileEntity.getDisplayName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return srgTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openScreen((ServerPlayer) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = level.getBlockEntity(pos);

            if (tileentity instanceof SingularityResourceGeneratorTileEntityBase) {
                Containers.dropContents(level, pos, ((SingularityResourceGeneratorTileEntityBase) tileentity).getContentDrops());

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }
}
