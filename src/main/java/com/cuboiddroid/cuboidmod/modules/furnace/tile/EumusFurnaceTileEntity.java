package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.EumusFurnaceContainer;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class EumusFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public EumusFurnaceTileEntity() {
        super(ModTileEntities.EUMUS_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.eumusFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.eumus_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new EumusFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}