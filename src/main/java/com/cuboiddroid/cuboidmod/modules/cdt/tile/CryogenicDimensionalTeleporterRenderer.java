package com.cuboiddroid.cuboidmod.modules.cdt.tile;

import com.cuboiddroid.cuboidmod.Config;
import com.cuboiddroid.cuboidmod.setup.ModTileEntities;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Quaternion;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.registry.ClientRegistry;

// Ideas merged from:
//   https://github.com/McJty/YouTubeModding14/blob/master/src/main/java/com/mcjty/mytutorial/blocks/MagicRenderer.java
// and
//   https://github.com/Cy4Shot/ModdingTutorial1.16/blob/main/9.0-RotatableBlockModels/java/com/cy4/tutorialmod/client/ter/DisplayCaseTileEntityRenderer.java

public class CryogenicDimensionalTeleporterRenderer extends TileEntityRenderer<CryogenicDimensionalTeleporterTileEntity> {

    public CryogenicDimensionalTeleporterRenderer(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(CryogenicDimensionalTeleporterTileEntity tileEntity,
                       float partialTicks,
                       MatrixStack matrixStack,
                       IRenderTypeBuffer buffer,
                       int combinedLight,
                       int combinedOverlay) {
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

    public void renderPending(CryogenicDimensionalTeleporterTileEntity tileEntity,
                               float partialTicks,
                               MatrixStack matrixStack,
                               IRenderTypeBuffer buffer,
                               int combinedLight,
                               int combinedOverlay) {
        // do nothing at the moment - just the block should be rendered
    }

    public void renderCharging(CryogenicDimensionalTeleporterTileEntity tileEntity,
                       float partialTicks,
                       MatrixStack matrixStack,
                       IRenderTypeBuffer buffer,
                       int combinedLight,
                       int combinedOverlay) {
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
        IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntity.getLevel(), null);
        itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

        matrixStack.popPose();
    }

    public void renderReady(CryogenicDimensionalTeleporterTileEntity tileEntity,
                               float partialTicks,
                               MatrixStack matrixStack,
                               IRenderTypeBuffer buffer,
                               int combinedLight,
                               int combinedOverlay) {
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
        IBakedModel ibakedmodel = itemRenderer.getModel(stack, tileEntity.getLevel(), null);
        itemRenderer.render(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer, combinedLight, combinedOverlay, ibakedmodel);

        matrixStack.popPose();
    }

    private int getLightLevel(World level, BlockPos pos) {
        // not used atm, but will do some testing to see if this method
        // of calculating light level makes more sense...

        int bLight = level.getBrightness(LightType.BLOCK, pos);
        int sLight = level.getBrightness(LightType.SKY, pos);
        return LightTexture.pack(bLight, sLight);
    }

    public static void register() {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.CRYOGENIC_DIMENSIONAL_TELEPORTER.get(), CryogenicDimensionalTeleporterRenderer::new);
    }

}
