package com.cuboiddroid.cuboidmod.modules.cdt.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;

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
                // .harvestLevel(3).harvestTool(ToolType.PICKAXE)
                .sound(SoundType.METAL));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block.cuboidmod.energized_thatlduvium.hover_text"));
    }
}
