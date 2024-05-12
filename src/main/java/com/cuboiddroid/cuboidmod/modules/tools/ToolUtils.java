package com.cuboiddroid.cuboidmod.modules.tools;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

public class ToolUtils {
    private static RandomSource random = RandomSource.create();

    public static void tryBreakAdjacent(ItemStack stack, Level world, BlockPos pos, Player player) {
        HitResult trace = calcRayTrace(world, player, ClipContext.Fluid.ANY);

        if (trace.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockTrace = (BlockHitResult) trace;
            Direction face = blockTrace.getDirection();

            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    // check that we have enough durability left (leaving 1 for main parent mineBlock routine!)
                    if (stack.getDamageValue()+1 >= stack.getMaxDamage())
                        continue;

                    // middle block handled by main mineBlock parent routine
                    if (a == 0 && b == 0) continue;

                    BlockPos target = null;

                    if (face == Direction.UP || face == Direction.DOWN) target = pos.offset(a, 0, b);
                    if (face == Direction.NORTH || face == Direction.SOUTH) target = pos.offset(a, b, 0);
                    if (face == Direction.EAST || face == Direction.WEST) target = pos.offset(0, a, b);

                    tryBreak(stack, world, target, player);
                }
            }
        }
    }

    public static void tryBreak(ItemStack stack, Level world, BlockPos pos, Player player) {
        if (isBreakableWithSmasher(world, pos, player)) {
            BlockState state = world.getBlockState(pos);

            if (!state.hasBlockEntity()) {
                stack.hurt(1, random, null);
                world.destroyBlock(pos, false);
                Block.dropResources(state, world, pos, null, player, player.getMainHandItem());
            }
        }
    }

    public static Boolean isBreakableWithSmasher(Level world, BlockPos pos, Player player) {
        BlockState state = world.getBlockState(pos);
        boolean isWithinHarvestLevel = player.getMainHandItem().isCorrectToolForDrops(state);

        boolean witherImmune = state.is(BlockTags.WITHER_IMMUNE);

        return (!witherImmune && isWithinHarvestLevel);
    }

    public static HitResult calcRayTrace(Level worldIn, Player player, ClipContext.Fluid fluidMode) {
        float f = player.getRotationVector().x;
        float f1 = player.getRotationVector().x;
        Vec3 vec3d = player.getEyePosition(1.0F);
        float f2 = Mth.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = Mth.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -Mth.cos(-f * ((float) Math.PI / 180F));
        float f5 = Mth.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.BLOCK_REACH.get()).getValue() + 1;

        Vec3 vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
        return worldIn.clip(new ClipContext(vec3d, vec3d1, ClipContext.Block.OUTLINE, fluidMode, player));
    }
}