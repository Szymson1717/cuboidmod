package com.cuboiddroid.cuboidmod.mixin;

import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.server.packs.repository.RepositorySource;

import org.apache.commons.lang3.ArrayUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.cuboiddroid.cuboidmod.util.RepositorySourceUtils;

@Mixin(CreateWorldScreen.class)
public abstract class ClientPackSelectorMixin {

    @ModifyArg(method = "openFresh", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/packs/repository/PackRepository;<init>([Lnet/minecraft/server/packs/repository/RepositorySource;)V"))
    private static RepositorySource[] globalpacks_addServerPackFinder(RepositorySource[] arg) {
        return ArrayUtils.add(arg, RepositorySourceUtils.getCuboidDatapacks());
    }

}