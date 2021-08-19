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
 * Key Item - Cuboid Overworld Access
 */
public class EnergizedThatlduviumBlock extends Block {
    public static final String ID_STRING = "energized_thatlduvium";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public EnergizedThatlduviumBlock() {
        super(Properties.of(Material.METAL)
                .strength(4, 10)
                .harvestLevel(3).harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable IBlockReader reader, List<ITextComponent> list, ITooltipFlag flag) {
        list.add(new TranslationTextComponent("block.cuboidmod.energized_thatlduvium.hover_text"));
    }
}
