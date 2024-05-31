package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.powergen.tile.SingularityPowerGeneratorTileEntityBase;
import net.minecraft.world.level.block.state.BlockBehaviour;
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

public abstract class SingularityPowerGeneratorBlockBase extends BaseEntityBlock {

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(15, 7, 6, 16, 9, 7), Block.box(0, 7, 6, 1, 9, 7), Block.box(15, 7, 9, 16, 9, 10),
            Block.box(0, 7, 9, 1, 9, 10), Block.box(15, 9, 7, 16, 10, 9), Block.box(7, 0, 6, 9, 1, 7),
            Block.box(7, 15, 6, 9, 16, 7), Block.box(7, 0, 9, 9, 1, 10), Block.box(6, 7, 0, 7, 9, 1),
            Block.box(6, 7, 15, 7, 9, 16), Block.box(9, 7, 0, 10, 9, 1), Block.box(9, 7, 15, 10, 9, 16),
            Block.box(7, 6, 0, 9, 7, 1), Block.box(7, 6, 15, 9, 7, 16), Block.box(7, 9, 0, 9, 10, 1),
            Block.box(7, 9, 15, 9, 10, 16), Block.box(7, 15, 9, 9, 16, 10), Block.box(9, 0, 7, 10, 1, 9),
            Block.box(9, 15, 7, 10, 16, 9), Block.box(6, 0, 7, 7, 1, 9), Block.box(6, 15, 7, 7, 16, 9),
            Block.box(0, 9, 7, 1, 10, 9), Block.box(15, 6, 7, 16, 7, 9), Block.box(0, 6, 7, 1, 7, 9),
            Block.box(4, 4, 4, 12, 12, 12), Block.box(3, 3, 4, 4, 4, 12), Block.box(3, 4, 12, 4, 12, 13),
            Block.box(7, 7, 12, 9, 9, 15), Block.box(4, 3, 12, 12, 4, 13), Block.box(4, 12, 12, 12, 13, 13),
            Block.box(7, 7, 1, 9, 9, 4), Block.box(12, 4, 12, 13, 12, 13), Block.box(12, 12, 4, 13, 13, 12),
            Block.box(12, 3, 4, 13, 4, 12), Block.box(4, 3, 3, 12, 4, 4), Block.box(12, 4, 3, 13, 12, 4),
            Block.box(4, 12, 3, 12, 13, 4), Block.box(3, 4, 3, 4, 12, 4), Block.box(1, 7, 7, 4, 9, 9),
            Block.box(7, 12, 7, 9, 15, 9), Block.box(12, 7, 7, 15, 9, 9), Block.box(7, 1, 7, 9, 4, 9),
            Block.box(3, 12, 4, 4, 13, 12), Block.box(1, 12, 12, 4, 15, 15), Block.box(1, 1, 12, 4, 4, 15),
            Block.box(12, 12, 12, 15, 15, 15), Block.box(12, 1, 12, 15, 4, 15), Block.box(12, 12, 1, 15, 15, 4),
            Block.box(12, 1, 1, 15, 4, 4), Block.box(1, 12, 1, 4, 15, 4), Block.box(1, 1, 1, 4, 4, 4),
            Block.box(0, 4, 15, 1, 12, 16), Block.box(15, 4, 15, 16, 12, 16), Block.box(15, 4, 0, 16, 12, 1),
            Block.box(0, 4, 0, 1, 12, 1), Block.box(15, 12, 1, 16, 14, 2), Block.box(15, 2, 1, 16, 4, 2),
            Block.box(14, 12, 0, 15, 14, 1), Block.box(14, 2, 0, 15, 4, 1), Block.box(15, 12, 14, 16, 14, 15),
            Block.box(15, 2, 14, 16, 4, 15), Block.box(14, 12, 15, 15, 14, 16), Block.box(14, 2, 15, 15, 4, 16),
            Block.box(1, 12, 15, 2, 14, 16), Block.box(1, 2, 15, 2, 4, 16), Block.box(0, 12, 14, 1, 14, 15),
            Block.box(0, 2, 14, 1, 4, 15), Block.box(1, 12, 0, 2, 14, 1), Block.box(1, 2, 0, 2, 4, 1),
            Block.box(0, 12, 1, 1, 14, 2), Block.box(0, 2, 1, 1, 4, 2), Block.box(15, 14, 12, 16, 15, 14),
            Block.box(15, 1, 12, 16, 2, 14), Block.box(12, 14, 15, 14, 15, 16), Block.box(12, 1, 15, 14, 2, 16),
            Block.box(2, 14, 15, 4, 15, 16), Block.box(2, 1, 15, 4, 2, 16), Block.box(15, 14, 2, 16, 15, 4),
            Block.box(15, 1, 2, 16, 2, 4), Block.box(12, 14, 0, 14, 15, 1), Block.box(12, 1, 0, 14, 2, 1),
            Block.box(0, 14, 12, 1, 15, 14), Block.box(0, 1, 12, 1, 2, 14), Block.box(0, 14, 2, 1, 15, 4),
            Block.box(0, 1, 2, 1, 2, 4), Block.box(2, 14, 0, 4, 15, 1), Block.box(2, 1, 0, 4, 2, 1),
            Block.box(12, 15, 14, 14, 16, 15), Block.box(12, 0, 14, 14, 1, 15), Block.box(14, 15, 12, 15, 16, 14),
            Block.box(14, 0, 12, 15, 1, 14), Block.box(2, 15, 14, 4, 16, 15), Block.box(2, 0, 14, 4, 1, 15),
            Block.box(1, 15, 12, 2, 16, 14), Block.box(1, 0, 12, 2, 1, 14), Block.box(14, 15, 2, 15, 16, 4),
            Block.box(14, 0, 2, 15, 1, 4), Block.box(12, 15, 1, 14, 16, 2), Block.box(12, 0, 1, 14, 1, 2),
            Block.box(1, 15, 2, 2, 16, 4), Block.box(1, 0, 2, 2, 1, 4), Block.box(2, 15, 1, 4, 16, 2),
            Block.box(2, 0, 1, 4, 1, 2), Block.box(4, 15, 15, 12, 16, 16), Block.box(4, 0, 15, 12, 1, 16),
            Block.box(15, 15, 4, 16, 16, 12), Block.box(15, 0, 4, 16, 1, 12), Block.box(4, 15, 0, 12, 16, 1),
            Block.box(4, 0, 0, 12, 1, 1), Block.box(0, 15, 4, 1, 16, 12), Block.box(0, 0, 4, 1, 1, 12)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();
            
    private static final int LIGHT_VALUE_WHEN_PRODUCING = 14;

    public SingularityPowerGeneratorBlockBase(BlockBehaviour.Properties properties) {
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
        list.add(Component.translatable(CuboidMod.MOD_ID + ".hover_text.singularity_power_generator"));
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return InteractionResult.SUCCESS;
        }

        this.interactWith(level, pos, player);
        return InteractionResult.CONSUME;
    }

    private void interactWith(Level level, BlockPos pos, Player player) {
        BlockEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof SingularityPowerGeneratorTileEntityBase) {
            SingularityPowerGeneratorTileEntityBase spgTileEntity = (SingularityPowerGeneratorTileEntityBase) tileEntity;
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return spgTileEntity.getDisplayName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return spgTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
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

            if (tileentity instanceof SingularityPowerGeneratorTileEntityBase) {
                Containers.dropContents(level, pos, ((SingularityPowerGeneratorTileEntityBase) tileentity).getContentDrops());

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
