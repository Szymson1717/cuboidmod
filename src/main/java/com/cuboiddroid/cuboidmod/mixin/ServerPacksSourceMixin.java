package com.cuboiddroid.cuboidmod.mixin;

import net.minecraft.server.packs.repository.RepositorySource;
import net.minecraft.server.packs.repository.ServerPacksSource;
import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.util.RepositorySourceUtils;

@Mixin(ServerPacksSource.class)
public class ServerPacksSourceMixin {

    @ModifyArg(method = "createPackRepository", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] globalpacks_createPackRepository(RepositorySource[] arg) {
        if (!Config.forcedCuboidFlat.get())return arg;
        return ArrayUtils.addAll(arg, RepositorySourceUtils.getCuboidFlat());
    }
}