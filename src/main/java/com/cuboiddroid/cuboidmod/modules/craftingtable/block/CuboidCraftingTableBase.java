package com.cuboiddroid.cuboidmod.modules.craftingtable.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.CraftingTableBlock;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.Level;

public class CuboidCraftingTableBase extends CraftingTableBlock {

    private Component NAME;

    public CuboidCraftingTableBase(String cuboidMaterialName) {
        super(BlockBehaviour.Properties.copy(net.minecraft.world.level.block.Blocks.CRAFTING_TABLE));
        this.NAME = new TranslatableComponent(CuboidMod.MOD_ID + ".container." + cuboidMaterialName + "_crafting_table");
    }

    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        return new SimpleMenuProvider(
                (id, inventory, entity) ->
                        new CuboidCraftingContainer(
                                id,
                                inventory,
                                ContainerLevelAccess.create(worldIn, pos),
                                this
                        ),
                NAME);
    }
}
