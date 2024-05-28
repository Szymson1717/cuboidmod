package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.NotsogudiumFurnaceContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class NotsogudiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public NotsogudiumFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.NOTSOGUDIUM_FURNACE.get().defaultBlockState());
    }

    public NotsogudiumFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.NOTSOGUDIUM_FURNACE.get(), pos, state);
    }

    @Override
    protected ForgeConfigSpec.IntValue getCookTimeConfig() {
        return Config.notsogudiumFurnaceSpeed;
    }

    @Override
    public String IgetName() {
        return CuboidMod.MOD_ID + ".container.notsogudium_furnace";
    }

    @Override
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new NotsogudiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity);
    }
}