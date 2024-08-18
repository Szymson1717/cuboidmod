package com.cuboiddroid.cuboidmod.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import com.cuboiddroid.cuboidmod.modules.collapser.item.QuantumSingularityItem;
import com.cuboiddroid.cuboidmod.modules.collapser.registry.QuantumSingularity;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.ItemModelShaper;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

@Mixin(ItemRenderer.class)
public abstract class ClientItemRendererMixin {

    @Shadow
    private ItemModelShaper itemModelShaper;

    @ModifyVariable(at = @At("HEAD"), method = "render", argsOnly = true)
    private BakedModel renderSingularityItem(BakedModel model, ItemStack stack, ItemDisplayContext context, boolean applyLeftHandTransform, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof QuantumSingularityItem singularityItem) {
            if (singularityItem.showingExtras()) {
                if (singularityItem.canProduceItems(stack)) {
                    QuantumSingularity singularity = singularityItem.getSingularity(stack);
                    Item productionItem = ForgeRegistries.ITEMS.getValue(singularity.getProduction().output);
                    ItemStack displayItem = new ItemStack(productionItem, 1);
                    BakedModel itemModel = this.itemModelShaper.getItemModel(displayItem);
    
                    return itemModel;
                }
            }
        }

        return model;
    }
}
