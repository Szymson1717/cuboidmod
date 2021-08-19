package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularity;
import com.cuboiddroid.cuboidmod.util.IColored;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

public abstract class QuantumSingularityItemBase extends Item implements IColored {
    private final QuantumSingularity quantumSingularity;

    public QuantumSingularityItemBase(Properties properties, QuantumSingularity quantumSingularity) {
        super(properties);
        this.quantumSingularity = quantumSingularity;
    }

    public QuantumSingularity getQuantumSingularity()
    {
        return this.quantumSingularity;
    }

    @Override
    public ITextComponent getName(ItemStack stack) {
        return quantumSingularity.getDisplayName();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        if (flag.isAdvanced())
            tooltip.add(new StringTextComponent("Quantum Singularity ID: " + quantumSingularity.getId()));
    }

    @Override
    public int getColor(int tintIndex, ItemStack stack) {
        return tintIndex == 0
                ? quantumSingularity.getUnderlayColor()
                : tintIndex == 1
                    ? quantumSingularity.getOverlayColor()
                    : -1;
    }
}