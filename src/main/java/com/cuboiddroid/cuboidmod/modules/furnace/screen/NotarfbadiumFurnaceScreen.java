package com.cuboiddroid.cuboidmod.modules.furnace.screen;

import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotarfbadiumFurnaceContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotarfbadiumFurnaceScreen extends CuboidFurnaceScreenBase<NotarfbadiumFurnaceContainer> {

    public NotarfbadiumFurnaceScreen(NotarfbadiumFurnaceContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}