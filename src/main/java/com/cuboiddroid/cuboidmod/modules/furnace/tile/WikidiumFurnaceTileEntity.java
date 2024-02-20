package com.cuboiddroid.cuboidmod.modules.furnace.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.WikidiumFurnaceContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeConfigSpec;

public class WikidiumFurnaceTileEntity extends CuboidFurnaceTileEntityBase {
    public WikidiumFurnaceTileEntity() {
        this(BlockPos.ZERO, ModBlocks.WIKIDIUM_FURNACE.get().defaultBlockState());
    }

    public WikidiumFurnaceTileEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.WIKIDIUM_FURNACE.get(), pos, state);
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
    public AbstractContainerMenu IcreateMenu(int i, Inventory playerInventory, Player playerEntity) {
        return new WikidiumFurnaceContainer(i, level, worldPosition, playerInventory, playerEntity, this.fields);
    }
}