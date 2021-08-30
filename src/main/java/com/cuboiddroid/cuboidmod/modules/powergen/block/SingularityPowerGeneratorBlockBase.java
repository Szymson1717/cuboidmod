package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.SingularityPowerGeneratorTileEntityBase;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public abstract class SingularityPowerGeneratorBlockBase extends Block {

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
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();
            
    private static final int LIGHT_VALUE_WHEN_PRODUCING = 14;

    public SingularityPowerGeneratorBlockBase(AbstractBlock.Properties properties) {
        super(properties);
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader blockReader, BlockPos pos, ISelectionContext context) {
        return VOXEL_SHAPE;
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flags) {
        list.add(new TranslationTextComponent("cuboidmod.hover_text.singularity_power_generator"));
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        return state.getValue(BlockStateProperties.LIT) ? LIGHT_VALUE_WHEN_PRODUCING : 0;
    }

    /**
     * @param state - the block state
     * @param world - the current world
     * @return the new, appropriate tile entity instance
     */
    @Nullable
    @Override
    public abstract TileEntity createTileEntity(BlockState state, IBlockReader world);

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType use(BlockState state, World level, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult trace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return ActionResultType.SUCCESS;
        }

        this.interactWith(level, pos, player);
        return ActionResultType.CONSUME;
    }

    private void interactWith(World level, BlockPos pos, PlayerEntity player) {
        TileEntity tileEntity = level.getBlockEntity(pos);
        if (tileEntity instanceof SingularityPowerGeneratorTileEntityBase) {
            SingularityPowerGeneratorTileEntityBase spgTileEntity = (SingularityPowerGeneratorTileEntityBase) tileEntity;
            INamedContainerProvider containerProvider = new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return spgTileEntity.getDisplayName();
                }

                @Override
                public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                    return spgTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onRemove(BlockState state, World level, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = level.getBlockEntity(pos);

            if (tileentity instanceof SingularityPowerGeneratorTileEntityBase) {
                InventoryHelper.dropContents(level, pos, ((SingularityPowerGeneratorTileEntityBase) tileentity).getContentDrops());

                level.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, level, pos, newState, isMoving);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }
}
