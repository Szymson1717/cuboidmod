package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotarfbadiumFurnaceContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class NotarfbadiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public NotarfbadiumFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTARFBADIUM_FURNACE.get().defaultBlockState());
    }

    public NotarfbadiumFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTARFBADIUM_FURNACE.get(), pos, state);
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
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new NotarfbadiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}