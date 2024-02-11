package com.cuboiddroid.cuboidmod.events;

import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.server.MinecraftServer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LivingDeathEventHandler {

    // Added to generate the dragon egg on every dragon kill, rather than just the first one
    @SubscribeEvent
    public void LivingDeathEvent(LivingDeathEvent event) {
        if (event.getEntityLiving() == null) return;
        MinecraftServer server = event.getEntity().getServer();
        if (server == null) return;
        if (event.getEntity().level.equals(server.getLevel(Level.END))) {
            if (event.getEntity() instanceof EnderDragon) {
                EnderDragon d = (EnderDragon) event.getEntity();
                if (d.getDragonFight() != null && d.getDragonFight().hasPreviouslyKilledDragon()) {
                    Level world = event.getEntity().level;
                    BlockPos pos = world.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING, EndPodiumFeature.END_PODIUM_LOCATION);
                    while (world.getBlockState(pos).getBlock() != Blocks.AIR) pos.above();
                    world.setBlockAndUpdate(pos, Blocks.DRAGON_EGG.defaultBlockState());
                }
            }
        }
    }
}
