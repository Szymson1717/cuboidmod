package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.KudbebeddaFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class KudbebeddaFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public KudbebeddaFurnaceTileEntity() {
        super(ModTileEntities.KUDBEBEDDA_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.kudbebeddaFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.kudbebedda_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new KudbebeddaFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}