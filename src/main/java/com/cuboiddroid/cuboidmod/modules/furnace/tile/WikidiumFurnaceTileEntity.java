package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.WikidiumFurnaceContainer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraftforge.common.ForgeConfigSpec;

public class WikidiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public WikidiumFurnaceTileEntity() {
        super(ModTileEntities.WIKIDIUM_FURNACE.get());
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.wikidiumFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return "cuboidmod.container.wikidium_furnace";
    }

    @Override
    public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
        return new WikidiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}