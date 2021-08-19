package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotarfbadiumFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class NotarfbadiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public NotarfbadiumFurnaceTileEntity() {
        super(ModTileEntities.NOTARFBADIUM_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.notarfbadiumFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.notarfbadium_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NotarfbadiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}