package com.cuboiddroid.cuboidmod.modules.transmuter.block;

import com.cuboiddroid.cuboidmod.modules.transmuter.tile.QuantumTransmutationChamberTileEntity;
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

public class QuantumTransmutationChamberBlock extends BaseEntityBlock {

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
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public QuantumTransmutationChamberBlock(Properties properties) {
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
        list.add(new TranslatableComponent("cuboidmod.hover_text.quantum_transmutation_chamber"));
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

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new QuantumTransmutationChamberTileEntity(pos, state);
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
        if (tileEntity instanceof QuantumTransmutationChamberTileEntity qtcTileEntity) {
            // QuantumTransmutationChamberTileEntity qtcTileEntity = (QuantumTransmutationChamberTileEntity) tileEntity;
            MenuProvider containerProvider = new MenuProvider() {
                @Override
                public Component getDisplayName() {
                    return qtcTileEntity.getDisplayName();
                }

                @Override
                public AbstractContainerMenu createMenu(int i, Inventory playerInventory, Player playerEntity) {
                    return qtcTileEntity.createContainer(i, level, pos, playerInventory, playerEntity);
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
        return createTickerHelper(type, ModTileEntities.QUANTUM_TRANSMUTATION_CHAMBER.get(), QuantumTransmutationChamberTileEntity::gameTick);
    }

}
