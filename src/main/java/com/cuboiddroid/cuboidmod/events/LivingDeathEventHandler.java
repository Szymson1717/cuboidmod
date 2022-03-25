package com.cuboiddroid.cuboidmod.events;

import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.EndPodiumFeature;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDeathEventHandler {

    // Added to generate the dragon egg on every dragon kill, rather than just the first one
    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntityLiving() == null) return;
        MinecraftServer server = event.getEntity().getServer();
        if (server == null) return;
        if (event.getEntity().level.equals(server.getLevel(World.END))) {
            if (event.getEntity() instanceof EnderDragonEntity) {
                EnderDragonEntity d = (EnderDragonEntity) event.getEntity();
                if (d.getDragonFight() != null && d.getDragonFight().hasPreviouslyKilledDragon()) {
                    World world = event.getEntity().level;
                    BlockPos pos = world.getHeightmapPos(Heightmap.Type.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
                    while (world.getBlockState(pos).getBlock() != Blocks.AIR) pos.above();
                    world.setBlockAndUpdate(pos, Blocks.DRAGON_EGG.defaultBlockState());
                }
            }
        }
    }
}
