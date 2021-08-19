package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stat;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.function.BiPredicate;
import java.util.function.Supplier;

public abstract class CuboidChestBlockBase extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    protected static final VoxelShape CUBOID_CHEST_SHAPE = Block.box(1.0D, 0.0D, 1.0D, 15.0D, 14.0D, 15.0D);

    private final CuboidChestTypes type;
    private final Supplier<TileEntityType<? extends CuboidChestTileEntityBase>> tileEntityTypeSupplier;
    private final boolean canOpenWhenObstructed;

    public CuboidChestBlockBase(CuboidChestTypes type, Supplier<TileEntityType<? extends CuboidChestTileEntityBase>> tileEntityTypeSupplier, Properties properties, boolean canOpenWhenObstructed) {
        super(properties);

        this.type = type;
        this.tileEntityTypeSupplier = tileEntityTypeSupplier;
        this.canOpenWhenObstructed = canOpenWhenObstructed;

        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockRenderType getRenderShape(BlockState state) {
        return BlockRenderType.ENTITYBLOCK_ANIMATED;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return CUBOID_CHEST_SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        Direction direction = context.getHorizontalDirection().getOpposite();

        return this.defaultBlockState().setValue(FACING, direction);
    }

    @Override
    public void setPlacedBy(World level, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        TileEntity tileentity = level.getBlockEntity(pos);

        if (tileentity instanceof CuboidChestTileEntityBase) {
            ((CuboidChestTileEntityBase) tileentity).wasPlaced(placer, stack);

            if (stack.hasCustomHoverName()) {
                ((CuboidChestTileEntityBase) tileentity).setCustomName(stack.getDisplayName());
            }
        }
    }


    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = level.getBlockEntity(pos);

            if (tileentity instanceof CuboidChestTileEntityBase) {
                ((CuboidChestTileEntityBase) tileentity).removeAdornments();

                if (!((CuboidChestTileEntityBase) tileentity).retainsInventory)
                    InventoryHelper.dropContents(level, pos, (CuboidChestTileEntityBase) tileentity);

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if (!level.isClientSide) {
            if (this.canOpenWhenObstructed || !isBlocked(level, pos)) {
                INamedContainerProvider inamedcontainerprovider = this.getMenuProvider(state, level, pos);
                if (inamedcontainerprovider != null) {
                    player.openMenu(inamedcontainerprovider);
                    player.awardStat(this.getOpenStat());
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    protected Stat<ResourceLocation> getOpenStat() {
        return Stats.CUSTOM.get(Stats.OPEN_CHEST);
    }

    @Override
    @Nullable
    public INamedContainerProvider getMenuProvider(BlockState state, World level, BlockPos pos) {
        TileEntity tileentity = level.getBlockEntity(pos);
        return tileentity instanceof INamedContainerProvider ? (INamedContainerProvider) tileentity : null;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public boolean triggerEvent(BlockState state, World level, BlockPos pos, int id, int param) {
        super.triggerEvent(state, level, pos, id, param);
        TileEntity tileentity = level.getBlockEntity(pos);
        return tileentity == null ? false : tileentity.triggerEvent(id, param);
    }

    private static boolean isBlocked(IWorld iWorld, BlockPos blockPos) {
        return isBelowSolidBlock(iWorld, blockPos);
    }

    private static boolean isBelowSolidBlock(IBlockReader iBlockReader, BlockPos blockPos) {
        BlockPos blockAbove = blockPos.above();
        return iBlockReader.getBlockState(blockAbove).isSolidRender(iBlockReader, blockAbove);
    }


    @Override
    public boolean hasAnalogOutputSignal(BlockState state) {
        return true;
    }

    @Override
    public int getAnalogOutputSignal(BlockState blockState, World level, BlockPos pos) {
        return Container.getRedstoneSignalFromContainer((IInventory) level.getBlockEntity(pos));
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
    public boolean isPathfindable(BlockState state, IBlockReader blockReader, BlockPos pos, PathType path) {
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
    public static TileEntityMerger.ICallback<CuboidChestTileEntityBase, Float2FloatFunction> getLid(final IChestLid chestLid) {
        return new TileEntityMerger.ICallback<CuboidChestTileEntityBase, Float2FloatFunction>() {
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

    public TileEntityMerger.ICallbackWrapper<? extends CuboidChestTileEntityBase> getWrapper(BlockState blockState, World world, BlockPos blockPos, boolean ignored) {
        BiPredicate<IWorld, BlockPos> biPredicate;
        if (ignored) {
            biPredicate = (w, pos) -> false;
        }
        else {
            biPredicate = CuboidChestBlockBase::isBlocked;
        }

        return TileEntityMerger.combineWithNeigbour(this.tileEntityTypeSupplier.get(), CuboidChestBlockBase::getMergerType, CuboidChestBlockBase::getDirectionToAttached, FACING, blockState, world, blockPos, biPredicate);
    }

    public static TileEntityMerger.Type getMergerType(BlockState blockState) {
        return TileEntityMerger.Type.SINGLE;
    }

    public static Direction getDirectionToAttached(BlockState state) {
        Direction direction = state.getValue(FACING);
        return direction.getCounterClockWise();
    }

    public boolean isObstructed(IWorld level, BlockPos pos) {
        return isBlocked(level, pos);
    }
}