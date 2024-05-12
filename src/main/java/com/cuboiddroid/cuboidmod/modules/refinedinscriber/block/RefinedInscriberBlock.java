package com.cuboiddroid.cuboidmod.modules.refinedinscriber.block;

import com.cuboiddroid.cuboidmod.modules.refinedinscriber.tile.RefinedInscriberTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
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
import net.minecraftforge.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.List;
import java.util.stream.Stream;

public class RefinedInscriberBlock extends BaseEntityBlock {

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(0, 1, 0, 3, 15, 16),
            Block.box(13, 1, 0, 16, 15, 16),
            Block.box(3, 1, 14, 13, 15, 16),
            Block.box(1, 0, 1, 15, 1, 15),
            Block.box(1, 15, 1, 15, 16, 15),
            Block.box(3, 1, 1, 13, 4, 14),
            Block.box(3, 12, 1, 13, 15, 14),
            Block.box(3, 4, 2, 13, 12, 3)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();;

    public RefinedInscriberBlock(Properties properties) {
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
        list.add(new TranslatableComponent("cuboidmod.hover_text.refined_inscriber"));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT);
    }
    
    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new RefinedInscriberTileEntity(pos, state);
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
        if (tileEntity instanceof RefinedInscriberTileEntity) {
            RefinedInscriberTileEntity refinedInscriberTileEntity = (RefinedInscriberTileEntity) tileEntity;
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return refinedInscriberTileEntity.getDisplayName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return refinedInscriberTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
                }
            };

            NetworkHooks.openGui((ServerPlayer) player, containerProvider, tileEntity.getBlockPos());
        } else {
            throw new IllegalStateException("Our named container provider is missing!");
        }
    }

    @Override
    @Nullable
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ModTileEntities.REFINED_INSCRIBER.get(), RefinedInscriberTileEntity::gameTick);
    }

}
