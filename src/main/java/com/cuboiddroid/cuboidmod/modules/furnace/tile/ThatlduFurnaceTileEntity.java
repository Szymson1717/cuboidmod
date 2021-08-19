package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.ThatlduFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class ThatlduFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public ThatlduFurnaceTileEntity() {
        super(ModTileEntities.THATLDU_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.thatlduFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.thatldu_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new ThatlduFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}