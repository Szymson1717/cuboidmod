package com.cuboiddroid.cuboidmod.modules.craftingtable.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.craftingtable.inventory.CuboidCraftingContainer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CuboidCraftingTableBase extends CraftingTableBlock {

    private ITextComponent NAME;

    public CuboidCraftingTableBase(String cuboidMaterialName) {
        super(AbstractBlock.Properties.copy(net.minecraft.block.Blocks.CRAFTING_TABLE));
        this.NAME = new TranslationTextComponent(CuboidMod.MOD_ID + ".container." + cuboidMaterialName + "_crafting_table");
    }

    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World worldIn, BlockPos pos) {
        return new SimpleNamedContainerProvider(
                (id, inventory, entity) ->
                        new CuboidCraftingContainer(
                                id,
                                inventory,
                                IWorldPosCallable.create(worldIn, pos),
                                this
                        ),
                NAME);
    }
}
