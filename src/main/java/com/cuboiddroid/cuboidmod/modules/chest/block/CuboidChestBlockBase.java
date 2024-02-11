package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.Container;
import net.minecraft.world.Containers;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.util.*;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;

public abstract class CuboidChestBlockBase extends BaseEntityBlock {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    protected static final VoxelShape CUBOID_CHEST_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    private final CuboidChestTypes type;
    private final Supplier<BlockEntityType<? extends CuboidChestTileEntityBase>> tileEntityTypeSupplier;
    private final boolean canOpenWhenObstructed;

    public CuboidChestBlockBase(CuboidChestTypes type, Supplier<BlockEntityType<? extends CuboidChestTileEntityBase>> tileEntityTypeSupplier, Properties properties, boolean canOpenWhenObstructed) {
        super(properties);

        this.type = type;
        this.tileEntityTypeSupplier = tileEntityTypeSupplier;
        this.canOpenWhenObstructed = canOpenWhenObstructed;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        return CUBOID_CHEST_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();

        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public void setPlacedBy(Level level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockEntity tileentity = level.getBlockEntity(pos);

        if (tileentity instanceof CuboidChestTileEntityBase) {
            ((CuboidChestTileEntityBase) tileentity).wasPlaced(placer, stack);

            if (stack.hasCustomHoverName()) {
                ((CuboidChestTileEntityBase) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }


    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity tileentity = level.getBlockEntity(pos);

            if (tileentity instanceof CuboidChestTileEntityBase) {
                ((CuboidChestTileEntityBase) tileentity).removeAdornments();

                if (!((CuboidChestTileEntityBase) tileentity).retainsInventory)
                    Containers.dropContents(level, pos, (CuboidChestTileEntityBase) tileentity);

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!level.isClientSide) {
            if (this.canOpenWhenObstructed || !isBlocked(level, pos)) {
                MenuProvider inamedcontainerprovider = this.getMenuProvider(state, level, pos);
                if (inamedcontainerprovider != null) {
                    player.openMenu(inamedcontainerprovider);
                    player.awardStat(this.getOpenStat());
                }
            }
        }
        return InteractionResult.SUCCESS;
    }

    protected Stat<ResourceLocation> getOpenStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    @Override
    @Nullable
    public MenuProvider getMenuProvider(BlockState state, Level level, BlockPos pos) {
        BlockEntity tileentity = level.getBlockEntity(pos);
        return tileentity instanceof MenuProvider ? (MenuProvider) tileentity : null;
    }

    // @Override
    // public boolean hasTileEntity(BlockState state) {
    //     return true;
    // }

    @Override
    public boolean triggerEvent(BlockState state, Level level, BlockPos pos, int id, int param) {
        super.triggerEvent(state, level, pos, id, param);
        BlockEntity tileentity = level.getBlockEntity(pos);
        return tileentity == null ? false : tileentity.triggerEvent(id, param);
    }

    private static boolean isBlocked(LevelAccessor iWorld, BlockPos blockPos) {
        return isBelowSolidBlock(iWorld, blockPos);
    }

    private static boolean isBelowSolidBlock(BlockGetter iBlockReader, BlockPos blockPos) {
        BlockPos blockAbove = blockPos.above();
        return iBlockReader.getBlockState(blockAbove).isSolidRender(iBlockReader, blockAbove);
    }


    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, Level level, BlockPos pos) {
        return AbstractContainerMenu.getRedstoneSignalFromContainer((Container) level.getBlockEntity(pos));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter blockReader, BlockPos pos, PathComputationType path) {
        return false;
    }

    public static CuboidChestTypes getTypeFromItem(Item item) {
        return getTypeFromBlock(Block.byItem(item));
    }

    public static CuboidChestTypes getTypeFromBlock(Block block) {
        return block instanceof CuboidChestBlockBase ? ((CuboidChestBlockBase) block).getType() : null;
    }

    public CuboidChestTypes getType() {
        return this.type;
    }

    @OnlyIn(Dist.CLIENT)
    public static DoubleBlockCombiner.Combiner<CuboidChestTileEntityBase, Float2FloatFunction> getLid(final LidBlockEntity chestLid) {
        return new DoubleBlockCombiner.Combiner<CuboidChestTileEntityBase, Float2FloatFunction>() {
            @Override
            public Float2FloatFunction acceptDouble(CuboidChestTileEntityBase te1, CuboidChestTileEntityBase te2) {
                return (partialTicks) -> {
                    return Math.max(te1.getOpenNess(partialTicks), te2.getOpenNess(partialTicks));
                };
            }

            @Override
            public Float2FloatFunction acceptSingle(CuboidChestTileEntityBase chestTileEntity) {
                return chestTileEntity::getOpenNess;
            }

            @Override
            public Float2FloatFunction acceptNone() {
                return chestLid::getOpenNess;
            }
        };
    }

    public DoubleBlockCombiner.NeighborCombineResult<? extends CuboidChestTileEntityBase> getWrapper(BlockState blockState, Level world, BlockPos blockPos, boolean ignored) {
        BiPredicate<LevelAccessor, BlockPos> biPredicate;
        if (ignored) {
            biPredicate = (w, pos) -> false;
        }
        else {
            biPredicate = CuboidChestBlockBase::isBlocked;
        }

        return DoubleBlockCombiner.combineWithNeigbour(this.tileEntityTypeSupplier.get(), CuboidChestBlockBase::getMergerType, CuboidChestBlockBase::getDirectionToAttached, FACING, blockState, world, blockPos, biPredicate);
    }

    public static DoubleBlockCombiner.BlockType getMergerType(BlockState blockState) {
        return DoubleBlockCombiner.BlockType.SINGLE;
    }

    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.getValue(FACING);
        return direction.getCounterClockWise();
    }

    public boolean isObstructed(LevelAccessor level, BlockPos pos) {
        return isBlocked(level, pos);
    }
}