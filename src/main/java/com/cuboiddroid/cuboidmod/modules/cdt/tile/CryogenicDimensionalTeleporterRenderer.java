package com.cuboiddroid.cuboidmod.modules.cdt.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.Level;

// Ideas merged from:
//   https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/MagicRenderer.java
// and
//   https://github.com/Cy4Shot/ModdingTutorial1.16/blob/main/9.0-RotatableBlockModels/java/com/cy4/tutorialmod/client/ter/DisplayCaseTileEntityRenderer.java

public class CryogenicDimensionalTeleporterRenderer implements BlockEntityRenderer<CryogenicDimensionalTeleporterTileEntity> {

    private final BlockEntityRendererProvider.Context context;

    public CryogenicDimensionalTeleporterRenderer(BlockEntityRendererProvider.Context context) {
        this.context = context;
    }

    @Override
    public void render(CryogenicDimensionalTeleporterTileEntity tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        int lightLevel = getLightLevel(tileEntity.getLevel(), tileEntity.getBlockPos().above());

        switch (tileEntity.getState()) {
            case CHARGING:
                renderCharging(tileEntity, partialTicks, matrixStack, buffer, lightLevel, combinedOverlay);
                break;

            case READY:
                renderReady(tileEntity, partialTicks, matrixStack, buffer, lightLevel, combinedOverlay);
                break;

            default:
                renderPending(tileEntity, partialTicks, matrixStack, buffer, combinedLight, combinedOverlay);
                break;
        }
    }

    public void renderPending(CryogenicDimensionalTeleporterTileEntity tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // do nothing at the moment - just the block should be rendered
    }

    public void renderCharging(CryogenicDimensionalTeleporterTileEntity tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // charging - just want to show the key item in static pose above the Cryogenic Dimensional Teleporter
        matrixStack.pushPose();

        // move "up" to middle/middle/middle of block above
        matrixStack.translate(0.5, 1.5, 0.5);

        // rotate so block is on a point
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(45));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(45));

        // apply scaling
        float scale = 0.8F;
        matrixStack.scale(scale, scale, scale);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack stack = tileEntity.getKeyItem();
        BakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntity.getLevel(), null, 0);
        itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

        matrixStack.popPose();
    }

    public void renderReady(CryogenicDimensionalTeleporterTileEntity tileEntity, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        // ready - show the key item in rotating pose above the Cryogenic Dimensional Teleporter
        long time = System.currentTimeMillis();
        double speed = Config.cryoDimTeleporterReadyRotationSpeed.get();
        float angle = (time / (int)speed) % 360;

        Quaternion rotation = Vector3f.YP.rotationDegrees(angle);

        matrixStack.pushPose();

        // move "up" to middle/middle/middle of block above
        matrixStack.translate(0.5, 1.5, 0.5);

        // rotate so block is on a point
        matrixStack.mulPose(Vector3f.XP.rotationDegrees(45));
        matrixStack.mulPose(Vector3f.ZP.rotationDegrees(45));

        // rotate on horizontal according to calculated rotation angle
        matrixStack.mulPose(rotation);

        // apply scaling
        float scale = 0.8F;
        matrixStack.scale(scale, scale, scale);

        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();

        ItemStack stack = tileEntity.getKeyItem();
        BakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntity.getLevel(), null, 0);
        itemRenderer.render(stack, ItemTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

        matrixStack.popPose();
    }

    private int getLightLevel(Level level, BlockPos pos) {
        // not used atm, but will do some testing to see if this method
        // of calculating light level makes more sense...

        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
        int sLight = level.getBrightness(LightLayer.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }
}
