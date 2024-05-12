package com.cuboiddroid.cuboidmod.modules.furnace.screen;

import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotsogudiumFurnaceContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NotsogudiumFurnaceScreen extends CuboidFurnaceScreenBase<NotsogudiumFurnaceContainer> {

    public NotsogudiumFurnaceScreen(NotsogudiumFurnaceContainer container, Inventory inv, Component name) {
        super(container, inv, name);
    }
}