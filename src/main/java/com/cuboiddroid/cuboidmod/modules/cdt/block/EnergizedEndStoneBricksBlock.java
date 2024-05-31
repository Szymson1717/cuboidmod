package com.cuboiddroid.cuboidmod.modules.cdt.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.BlockGetter;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Key Item - End Access
 */
public class EnergizedEndStoneBricksBlock extends Block {
    public static final String ID_STRING = "energized_end_stone_bricks";
    public static final ResourceLocation ID = CuboidMod.getModId(ID_STRING);

    public EnergizedEndStoneBricksBlock() {
        super(Properties.of()
                .strength(3, 9)
                // .harvestLevel(2).harvestTool(ToolType.PICKAXE)
                .sound(SoundType.STONE));
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable BlockGetter reader, List<Component> list, TooltipFlag flag) {
        list.add(Component.translatable("block." + CuboidMod.MOD_ID + ".energized_end_stone_bricks.hover_text"));
    }
}
