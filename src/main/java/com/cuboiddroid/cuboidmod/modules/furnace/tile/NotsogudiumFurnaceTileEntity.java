package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotsogudiumFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class NotsogudiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public NotsogudiumFurnaceTileEntity() {
        super(ModTileEntities.NOTSOGUDIUM_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.notsogudiumFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.notsogudium_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new NotsogudiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}