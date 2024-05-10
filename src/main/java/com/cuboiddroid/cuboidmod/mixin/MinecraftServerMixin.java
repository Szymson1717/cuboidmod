package com.cuboiddroid.cuboidmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureStart;
import net.minecraft.world.level.storage.ServerLevelData;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {

    @Shadow
    protected static void setInitialSpawn(ServerLevel serverLevel, ServerLevelData serverLevelData, boolean bonusChest, boolean flag) {};

    @Redirect(method = "createLevels",
        at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;setInitialSpawn(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/level/storage/ServerLevelData;ZZ)V")
    )
    private void spawnCuboidHome(ServerLevel serverLevel, ServerLevelData serverLevelData, boolean bonusChest, boolean flag) {
        setInitialSpawn(serverLevel, serverLevelData, bonusChest, flag);

        ServerChunkCache serverChunkCache = serverLevel.getChunkSource();
        ResourceKey<Structure> cuboidHouse = ResourceKey.create(Registries.STRUCTURE, new ResourceLocation(CuboidMod.MOD_ID, "cuboidhouse"));
        BlockPos spawnPoint = new BlockPos(serverLevelData.getXSpawn(), serverLevelData.getYSpawn(), serverLevelData.getZSpawn());

        serverLevel.registryAccess().registry(Registries.STRUCTURE).flatMap((registry) -> {
            return registry.getHolder(cuboidHouse);
        }).ifPresent((structureReference) -> {
            Structure structure = structureReference.value();
            ChunkGenerator chunkgenerator = serverChunkCache.getGenerator();
            StructureStart structureStartChecker = structure.generate(serverLevel.registryAccess(), chunkgenerator, chunkgenerator.getBiomeSource(),
                    serverChunkCache.randomState(), serverLevel.getStructureManager(), serverLevel.getSeed(), new ChunkPos(spawnPoint),
                    0, serverLevel, (predicate) -> true
            );

            // Get structure size
            if (!structureStartChecker.isValid()) return;
            BoundingBox boundingbox = structureStartChecker.getBoundingBox();

            // Generate structure
            StructureStart structureStart = structure.generate(serverLevel.registryAccess(), chunkgenerator, chunkgenerator.getBiomeSource(),
                    serverChunkCache.randomState(), serverLevel.getStructureManager(), serverLevel.getSeed(), new ChunkPos(spawnPoint.offset(boundingbox.getXSpan() / 2, -1, boundingbox.getZSpan() / 2)),
                    0, serverLevel, (predicate) -> true
            );

            if (!structureStart.isValid()) return;
            boundingbox = structureStart.getBoundingBox();
            ChunkPos chunkPosStart = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.minX()), SectionPos.blockToSectionCoord(boundingbox.minZ()));
            ChunkPos chunkPosEnd = new ChunkPos(SectionPos.blockToSectionCoord(boundingbox.maxX()), SectionPos.blockToSectionCoord(boundingbox.maxZ()));
            
            ChunkPos.rangeClosed(chunkPosStart, chunkPosEnd).forEach((chunk) -> {
                structureStart.placeInChunk(serverLevel, serverLevel.structureManager(), chunkgenerator, serverLevel.getRandom(), new BoundingBox(chunk.getMinBlockX(), serverLevel.getMinBuildHeight(), chunk.getMinBlockZ(), chunk.getMaxBlockX(), serverLevel.getMaxBuildHeight(), chunk.getMaxBlockZ()), chunk);
            });
            
        });
    }
}
