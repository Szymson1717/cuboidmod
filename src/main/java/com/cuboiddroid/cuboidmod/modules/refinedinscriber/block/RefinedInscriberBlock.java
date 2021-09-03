package com.cuboiddroid.cuboidmod.modules.refinedinscriber.block;

import com.cuboiddroid.cuboidmod.modules.refinedinscriber.tile.RefinedInscriberTileEntity;
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

public class RefinedInscriberBlock extends Block {

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(0, 1, 0, 3, 15, 16),
            Block.box(13, 1, 0, 16, 15, 16),
            Block.box(3, 1, 14, 13, 15, 16),
            Block.box(1, 0, 1, 15, 1, 15),
            Block.box(1, 15, 1, 15, 16, 15),
            Block.box(3, 1, 1, 13, 4, 14),
            Block.box(3, 12, 1, 13, 15, 14),
            Block.box(3, 4, 2, 13, 12, 3)
    ).reduce((v1, v2) -> VoxelShapes.join(v1, v2, IBooleanFunction.OR)).get();;

    public RefinedInscriberBlock(Properties properties) {
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
        list.add(new TranslationTextComponent("cuboidmod.hover_text.refined_inscriber"));
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
        return new RefinedInscriberTileEntity();
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
        if (tileEntity instanceof RefinedInscriberTileEntity) {
            RefinedInscriberTileEntity refinedInscriberTileEntity = (RefinedInscriberTileEntity) tileEntity;
            INamedContainerProvider containerProvider = new INamedContainerProvider() {
                @Override
                public ITextComponent getDisplayName() {
                    return refinedInscriberTileEntity.getDisplayName();
                }

                @Override
                public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
                    return refinedInscriberTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openGui((ServerPlayerEntity) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

}
