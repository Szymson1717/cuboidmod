package com.cuboiddroid.cuboidmod.modules.cdt.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Key Item - Overworld Access
 */
public class EnergizedStoneBricksBlock extends Block {
    public static final String ID_STRING = "energized_stone_bricks";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public EnergizedStoneBricksBlock() {
        super(Properties.of(Material.STONE)
                .strength(3, 9)
                .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("block.cuboidmod.energized_stone_bricks.hover_text"));
    }
}
