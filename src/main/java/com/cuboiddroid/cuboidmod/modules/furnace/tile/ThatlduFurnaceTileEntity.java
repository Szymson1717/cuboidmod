package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.ThatlduFurnaceContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class ThatlduFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public ThatlduFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.THATLDU_FURNACE.get().defaultBlockState());
    }

    public ThatlduFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.THATLDU_FURNACE.get(), pos, state);
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.thatlduFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return CuboidMod.MOD_ID + ".container.thatldu_furnace";
    }

    @Override
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new ThatlduFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}