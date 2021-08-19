package com.cuboiddroid.cuboidmod.modules.transmuter.block;

import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
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

public class QuantumTransmutationChamberBlock extends Block {

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(2, 2, 2, 14, 14, 14), Block.box(0, 0, 0, 2, 1, 16), Block.box(14, 0, 0, 16, 1, 16),
            Block.box(0, 15, 0, 2, 16, 16), Block.box(14, 15, 0, 16, 16, 16), Block.box(1, 12, 1, 4, 15, 4),
            Block.box(1, 1, 1, 4, 4, 4), Block.box(1, 12, 12, 4, 15, 15), Block.box(1, 1, 12, 4, 4, 15),
            Block.box(12, 12, 1, 15, 15, 4), Block.box(12, 1, 1, 15, 4, 4), Block.box(12, 12, 12, 15, 15, 15),
            Block.box(12, 1, 12, 15, 4, 15), Block.box(14, 1, 15, 16, 15, 16), Block.box(14, 1, 0, 16, 15, 1),
            Block.box(0, 1, 15, 2, 15, 16), Block.box(0, 1, 0, 2, 15, 1), Block.box(0, 1, 1, 1, 15, 2),
            Block.box(0, 1, 14, 1, 15, 15), Block.box(15, 1, 1, 16, 15, 2), Block.box(15, 1, 14, 16, 15, 15),
            Block.box(2, 15, 0, 14, 16, 2), Block.box(2, 15, 14, 14, 16, 16), Block.box(2, 0, 0, 14, 1, 2),
            Block.box(2, 0, 14, 14, 1, 16), Block.box(0, 1, 2, 1, 2, 14), Block.box(0, 14, 2, 1, 15, 14),
            Block.box(15, 1, 2, 16, 2, 14), Block.box(15, 14, 2, 16, 15, 14), Block.box(2, 14, 0, 14, 15, 1),
            Block.box(2, 14, 15, 14, 15, 16), Block.box(2, 1, 0, 14, 2, 1), Block.box(2, 1, 15, 14, 2, 16),
            Block.box(2, 2, 15, 3, 3, 15.5), Block.box(2, 2, 0.5, 3, 3, 1), Block.box(2, 13, 15, 3, 14, 15.5),
            Block.box(2, 13, 0.5, 3, 14, 1), Block.box(13, 2, 15, 14, 3, 15.5), Block.box(13, 2, 0.5, 14, 3, 1),
            Block.box(13, 13, 15, 14, 14, 15.5), Block.box(13, 13, 0.5, 14, 14, 1), Block.box(13, 15, 2, 14, 15.5, 3),
            Block.box(13, 0.5, 2, 14, 1, 3), Block.box(2, 15, 2, 3, 15.5, 3), Block.box(2, 0.5, 2, 3, 1, 3),
            Block.box(13, 15, 13, 14, 15.5, 14), Block.box(13, 0.5, 13, 14, 1, 14), Block.box(2, 15, 13, 3, 15.5, 14),
            Block.box(2, 0.5, 13, 3, 1, 14), Block.box(0.5, 2, 13, 1, 3, 14), Block.box(15, 2, 13, 15.5, 3, 14),
            Block.box(0.5, 13, 13, 1, 14, 14), Block.box(15, 13, 13, 15.5, 14, 14), Block.box(0.5, 2, 2, 1, 3, 3),
            Block.box(15, 2, 2, 15.5, 3, 3), Block.box(0.5, 13, 2, 1, 14, 3), Block.box(15, 13, 2, 15.5, 14, 3)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();

    public QuantumTransmutationChamberBlock(Properties properties) {
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
        list.add(new TranslationTextComponent("cuboidmod.hover_text.quantum_transmutation_chamber"));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new QuantumTransmutationChamberTileEntity();
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
        if (tileEntity instanceof QuantumTransmutationChamberTileEntity) {
            QuantumTransmutationChamberTileEntity qtcTileEntity = (QuantumTransmutationChamberTileEntity) tileEntity;
            INamedContainerProvider containerProvider = new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return qtcTileEntity.getDisplayName();
                }

                @Override
                public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                    return qtcTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

}
