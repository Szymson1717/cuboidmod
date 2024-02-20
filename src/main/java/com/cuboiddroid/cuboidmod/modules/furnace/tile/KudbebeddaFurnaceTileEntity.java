package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.KudbebeddaFurnaceContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class KudbebeddaFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public KudbebeddaFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.KUDBEBEDDA_FURNACE.get().defaultBlockState());
    }

    public KudbebeddaFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.KUDBEBEDDA_FURNACE.get(), pos, state);
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
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new KudbebeddaFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}