package com.cuboiddroid.cuboidmod.modules.tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.*;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

import java.util.Set;

public class ToolUtils {
    public static void tryBreakAdjacent(World world, BlockPos pos, PlayerEntity player, Set<Material> effectiveMaterials) {
        RayTraceResult trace = calcRayTrace(world, player, RayTraceContext.FluidMode.ANY);

        if (trace.getType() == RayTraceResult.Type.BLOCK) {
            BlockRayTraceResult blockTrace = (BlockRayTraceResult) trace;
            Direction face = blockTrace.getDirection();

            for (int a = -1; a <= 1; a++) {
                for (int b = -1; b <= 1; b++) {
                    if (a == 0 && b == 0) continue;

                    BlockPos target = null;

                    if (face == Direction.UP || face == Direction.DOWN) target = pos.offset(a, 0, b);
                    if (face == Direction.NORTH || face == Direction.SOUTH) target = pos.offset(a, b, 0);
                    if (face == Direction.EAST || face == Direction.WEST) target = pos.offset(0, a, b);

                    tryBreak(world, target, player, effectiveMaterials);
                }
            }
        }
    }

    public static void tryBreak(World world, BlockPos pos, PlayerEntity player, Set<Material> effectiveMaterials) {
        BlockState state = world.getBlockState(pos);
        boolean isWithinHarvestLevel = player.getMainHandItem().isCorrectToolForDrops(state);
        boolean isEffective = effectiveMaterials.contains(state.getMaterial());

        boolean witherImmune = BlockTags.WITHER_IMMUNE.contains(state.getBlock());

        if (isEffective && !witherImmune && isWithinHarvestLevel) {
            if (!state.hasTileEntity()) {
                world.destroyBlock(pos, false);
                Block.dropResources(state, world, pos, null, player, player.getMainHandItem());
            }
        }
    }

    public static RayTraceResult calcRayTrace(World worldIn, PlayerEntity player, RayTraceContext.FluidMode fluidMode) {
        float f = player.xRot;
        float f1 = player.yRot;
        Vector3d vec3d = player.getEyePosition(1.0F);
        float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;
        double d0 = player.getAttribute(net.minecraftforge.common.ForgeMod.REACH_DISTANCE.get()).getValue() + 1;
        ;
        Vector3d vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
        return worldIn.clip(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.OUTLINE, fluidMode, player));
    }
}