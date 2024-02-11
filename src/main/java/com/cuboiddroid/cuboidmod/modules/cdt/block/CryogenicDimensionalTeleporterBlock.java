package com.cuboiddroid.cuboidmod.modules.cdt.block;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.cdt.tile.CryogenicDimensionalTeleporterTileEntity;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
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
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.common.util.ITeleporter;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

public class CryogenicDimensionalTeleporterBlock extends BaseEntityBlock {
    private final Random random = new Random();

    private static final VoxelShape VOXEL_SHAPE = Stream.of(
            Block.box(2, 2, 6.806525795722052, 14, 12, 9.193474204277948),
            Block.box(2, 2, 6.806525795722052, 14, 12, 9.193474204277948),
            Block.box(2, 2, 6.806525795722052, 14, 12, 9.193474204277948),
            Block.box(6.806525795722052, 2, 2, 9.193474204277948, 12, 14),
            Block.box(6.806525795722052, 2, 2, 9.193474204277948, 12, 14),
            Block.box(6.806525795722052, 2, 2, 9.193474204277948, 12, 14),
            Block.box(6.806525795722052, 2, 2, 9.193474204277948, 12, 14),
            Block.box(6.806525795722052, 2, 2, 9.193474204277948, 12, 14),
            Block.box(0, 0, 6.408701060962736, 16, 2, 9.591298939037264),
            Block.box(0, 0, 6.408701060962736, 16, 2, 9.591298939037264),
            Block.box(0, 0, 6.408701060962736, 16, 2, 9.591298939037264),
            Block.box(6.408701060962736, 0, 0, 9.591298939037264, 2, 16),
            Block.box(6.408701060962736, 0, 0, 9.591298939037264, 2, 16),
            Block.box(6.408701060962736, 0, 0, 9.591298939037264, 2, 16),
            Block.box(6.408701060962736, 0, 0, 9.591298939037264, 2, 16),
            Block.box(6.408701060962736, 0, 0, 9.591298939037264, 2, 16),
            Block.box(1, 11.5, 6.607613428342394, 15, 12.5, 9.392386571657607),
            Block.box(1, 11.5, 6.607613428342394, 15, 12.5, 9.392386571657607),
            Block.box(1, 11.5, 6.607613428342394, 15, 12.5, 9.392386571657607),
            Block.box(6.607613428342393, 11.5, 1, 9.392386571657607, 12.5, 15),
            Block.box(6.607613428342393, 11.5, 1, 9.392386571657607, 12.5, 15),
            Block.box(6.607613428342393, 11.5, 1, 9.392386571657607, 12.5, 15),
            Block.box(6.607613428342393, 11.5, 1, 9.392386571657607, 12.5, 15),
            Block.box(6.607613428342393, 11.5, 1, 9.392386571657607, 12.5, 15)
    ).reduce((v1, v2) -> Shapes.join(v1, v2, BooleanOp.OR)).get();

    public CryogenicDimensionalTeleporterBlock() {
        super(BlockBehaviour.Properties.of(Material.METAL)
                .strength(45, 1000)
                // .harvestLevel(3).harvestTool(ToolType.PICKAXE)
                .requiresCorrectToolForDrops()
                .sound(SoundType.METAL));
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter blockReader, BlockPos pos, CollisionContext context) {
        return VOXEL_SHAPE;
    }

    // @Override
    // public boolean hasTileEntity(BlockState state) {
    //     return true;
    // }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flags) {
        list.add(new TranslatableComponent("item.cuboidmod.cryogenic_dimensional_teleporter.hover_text"));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return defaultBlockState()
                .setValue(BlockStateProperties.LIT, false)
                .setValue(BlockStateProperties.OPEN, false)
                .setValue(BlockStateProperties.HORIZONTAL_FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.LIT, BlockStateProperties.OPEN);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CryogenicDimensionalTeleporterTileEntity(pos, state);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult trace) {
        if (level.isClientSide) {
            // return success on client so player swings their hand
            return InteractionResult.SUCCESS;
        }

        BlockEntity te = level.getBlockEntity(pos);
        if (te instanceof CryogenicDimensionalTeleporterTileEntity) {
            CryogenicDimensionalTeleporterTileEntity cdt = (CryogenicDimensionalTeleporterTileEntity) te;

            ItemStack item = player.getItemInHand(hand);

            if (!item.isEmpty() && item.getItem() instanceof Item) {
                // player used an item on the block - try set the dimension
                boolean validTargetKey = cdt.setTargetDimensionWithKeyItem((ServerPlayer) player, item);
                if (validTargetKey) {
                    // was a valid item to set target dimension - consume the item
                    player.setItemInHand(hand, ItemStack.EMPTY);
                }

                return InteractionResult.SUCCESS;
            } else if (item.isEmpty()) {
                // player used empty hand - try and teleport to the target dimension

                // teleport the player to the dimension...
                if (player instanceof ServerPlayer) {
                    DimensionType dim = cdt.GetTargetDimensionIfCharged((ServerPlayer) player, level);
                    if (dim == null) {
                        // play a sound? show failure message in chat?
                        return InteractionResult.SUCCESS;
                    }

                    // teleport the player to the dimension...
                    ServerPlayer serverPlayer = (ServerPlayer) player;
                    for (ServerLevel world : serverPlayer.getServer().getAllLevels())
                    {
                        if (world.dimensionType() == dim)
                        {
                            cdt.onTeleport();
                            serverPlayer.changeDimension(world, new ITeleporter() {
                                @Override
                                public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                                    int radialDivisor = 32;

                                    entity = repositionEntity.apply(false);

                                    BlockPos newPos = pos;
                                    int attempts = 0;

                                    // Start at the "top" of the world
                                    int yPos = dim.logicalHeight() - 8;

                                    // Adjust X and Z coordinates based on dimension coordinate scales (e.g. Nether)
                                    double xPos = newPos.getX() * DimensionType.getTeleportationScale(currentWorld.dimensionType(), dim);
                                    double zPos = newPos.getZ() * DimensionType.getTeleportationScale(currentWorld.dimensionType(), dim);

                                    newPos = new BlockPos(xPos, yPos, zPos);

                                    // if the end, then should "shift" to central island area instead of
                                    // current coords - use an exclusion zone around center of
                                    // main island so player gets placed on outer edges
                                    if (cdt.isTargetTheEnd()) {
                                        radialDivisor = 64; // use a smaller target area to look for a safe spot
                                        if (newPos.distSqr(0.0, 100, 0.0, true) > 96.0) {
                                            // further away than 96 blocks - adjust to be 80 blocks away!
                                            if (xPos == 0.0) {
                                                zPos = 80;
                                            } else {
                                                double g = zPos / xPos;

                                                xPos = Math.sqrt((80.0 * 80.0) / (g * g + 1));
                                                zPos = g * xPos;
                                            }
                                        } else if (newPos.distSqr(0.0, 100, 0.0, true) < 64.0) {
                                            // closer than 64 blocks - adjust to be 64 blocks away from 0,0
                                            if (xPos == 0.0) {
                                                zPos = 80;
                                            } else {
                                                double g = zPos / xPos;

                                                xPos = Math.sqrt((80.0 * 80.0) / (g * g + 1));
                                                zPos = g * xPos;
                                            }
                                        }

                                        newPos = new BlockPos(xPos, yPos, zPos);
                                    } else if (cdt.isTargetTheOverworld() && Config.cryoDimTeleporterOverworldUsesPlayerSpawn.get()) {
                                        // target is the overworld - instead of player's current position,
                                        // we're going to take them back to their spawn point
                                        if (serverPlayer.getRespawnDimension().location().getPath().equalsIgnoreCase("overworld")) {
                                            BlockPos respawnPos = serverPlayer.getRespawnPosition();
                                            newPos = new BlockPos(respawnPos.getX(), yPos, respawnPos.getZ());
                                        }
                                    } else if (cdt.isTargetCuboidOverworld() && Config.cryoDimTeleporterOverworldUsesPlayerSpawn.get()) {
                                        // target is the overworld - instead of player's current position,
                                        // we're going to take them back to their spawn point
                                        if (serverPlayer.getRespawnDimension().location().getPath().equalsIgnoreCase("cuboid_overworld")) {
                                            BlockPos respawnPos = serverPlayer.getRespawnPosition();
                                            newPos = new BlockPos(respawnPos.getX(), yPos, respawnPos.getZ());
                                        }
                                    }

                                    // target pos
                                    BlockPos targetPos = newPos;

                                    while (attempts < 1024) {
                                        while (yPos > 40 && (!destWorld.getBlockState(newPos).getMaterial().isSolid() ||
                                                (destWorld.getBlockState(newPos).getMaterial().isSolid() &&
                                                    (destWorld.getBlockState(newPos.above()).getMaterial().isSolid() ||
                                                    destWorld.getBlockState(newPos.above().above()).getMaterial().isSolid()))))
                                        {
                                            newPos = newPos.below();
                                            yPos = newPos.getY();
                                        }

                                        if (yPos > 40)
                                        {
                                            entity.teleportTo(newPos.getX() + 0.5F, yPos+2, newPos.getZ() + 0.5F);
                                            return entity;
                                        }

                                        // progressively larger radius for random positioning
                                        int radius = attempts / radialDivisor + 16;

                                        xPos = targetPos.getX() + ((2.0d * random.nextDouble() - 1.0d) * radius);
                                        zPos = targetPos.getY() + ((2.0d * random.nextDouble() - 1.0d) * radius);

                                        //xPos = player.getRandomX(radius);
                                        //zPos = player.getRandomZ(radius);

                                         // Start at the "top" of the world
                                        yPos = dim.logicalHeight() - 8;

                                        newPos = new BlockPos(xPos, yPos, zPos);
                                        attempts++;
                                    }

                                    if (attempts >= 1024) {
                                        // couldn't find a spot in 256 tries - cheat and just put an oak plank under the player

                                        destWorld.setBlock(targetPos.below().below(), Blocks.OAK_PLANKS.defaultBlockState(), Block.UPDATE_ALL);
                                        destWorld.setBlock(targetPos.below(), Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                                        destWorld.setBlock(targetPos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
                                        entity.teleportTo(targetPos.getX() + 0.5F, targetPos.getY()+1, targetPos.getZ() + 0.5F);
                                    }

                                    // apply potion effects - blindness, slowness & mining fatigue
                                    player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 120));
                                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 160));
                                    player.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 240));

                                    return entity;
                                }
                            });

                            break;
                        }
                    }
                }
            }
        }

        return InteractionResult.CONSUME;
    }
}