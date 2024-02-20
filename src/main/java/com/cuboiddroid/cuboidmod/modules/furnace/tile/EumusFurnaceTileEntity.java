package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.EumusFurnaceContainer;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class EumusFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public EumusFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.EUMUS_FURNACE.get().defaultBlockState());
    }

    public EumusFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.EUMUS_FURNACE.get(), pos, state);
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
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new EumusFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}