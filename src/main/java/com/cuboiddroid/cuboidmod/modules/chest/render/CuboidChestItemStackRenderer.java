package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class CuboidChestItemStackRenderer<T extends CuboidChestTileEntityBase> extends BlockEntityWithoutLevelRenderer {

    private final Supplier<T> te;

    public CuboidChestItemStackRenderer(Supplier<T> te) {
        super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.te = te;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType context, PoseStack PoseStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        Minecraft.getInstance().getBlockEntityRenderDispatcher().renderItem(this.te.get(), PoseStack, buffer, combinedLight, combinedOverlay);
    }
}
