package com.cuboiddroid.cuboidmod.modules.collapser.item;

import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularity;
import com.cuboiddroid.cuboidmod.util.IColored;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.Level;
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
    public Component getName(ItemStack stack) {
        return quantumSingularity.getDisplayName();
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flag) {
        if (flag.isAdvanced())
            tooltip.add(Component.literal("Quantum Singularity ID: " + quantumSingularity.getId()));
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