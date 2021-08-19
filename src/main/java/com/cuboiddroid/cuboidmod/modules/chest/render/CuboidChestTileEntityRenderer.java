package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.client.renderer.model.RenderMaterial;
import net.minecraft.client.renderer.tileentity.DualBrightnessCallback;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.tileentity.IChestLid;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMerger;
import net.minecraft.util.Direction;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;

public class CuboidChestTileEntityRenderer<T extends TileEntity & IChestLid> extends TileEntityRenderer<T> {

    private final ModelRenderer chestLid;
    private final ModelRenderer chestBottom;
    private final ModelRenderer chestLock;

    public CuboidChestTileEntityRenderer(TileEntityRendererDispatcher tileEntityRendererDispatcher) {
        super(tileEntityRendererDispatcher);

        this.chestBottom = new ModelRenderer(64, 64, 0, 19);
        this.chestBottom.addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F, 0.0F);
        this.chestLid = new ModelRenderer(64, 64, 0, 0);
        this.chestLid.addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F, 0.0F);
        this.chestLid.y = 9.0F;
        this.chestLid.z = 1.0F;
        this.chestLock = new ModelRenderer(64, 64, 0, 0);
        this.chestLock.addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F, 0.0F);
        this.chestLock.y = 8.0F;
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        CuboidChestTileEntityBase tileEntity = (CuboidChestTileEntityBase) tileEntityIn;

        World level = tileEntity.getLevel();
        boolean flag = level != null;

        BlockState blockstate = flag ? tileEntity.getBlockState() : (BlockState) tileEntity.getBlockToUse().defaultBlockState().setValue(CuboidChestBlockBase.FACING, Direction.SOUTH);
        Block block = blockstate.getBlock();
        CuboidChestTypes chestType = CuboidChestTypes.NOTSOGUDIUM;
        CuboidChestTypes actualType = CuboidChestBlockBase.getTypeFromBlock(block);

        if (actualType != null) {
            chestType = actualType;
        }

        if (block instanceof CuboidChestBlockBase) {
            CuboidChestBlockBase cuboidChestBlock = (CuboidChestBlockBase) block;

            matrixStackIn.pushPose();
            float f = blockstate.getValue(CuboidChestBlockBase.FACING).toYRot();
            matrixStackIn.translate(0.5D, 0.5D, 0.5D);
            matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(-f));
            matrixStackIn.translate(-0.5D, -0.5D, -0.5D);

            TileEntityMerger.ICallbackWrapper<? extends CuboidChestTileEntityBase> iCallbackWrapper;
            if (flag) {
                iCallbackWrapper = cuboidChestBlock.getWrapper(blockstate, level, tileEntity.getBlockPos(), true);
            }
            else {
                iCallbackWrapper = TileEntityMerger.ICallback::acceptNone;
            }

            float f1 = iCallbackWrapper.apply(CuboidChestBlockBase.getLid((IChestLid) tileEntity)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = iCallbackWrapper.apply(new DualBrightnessCallback<>()).applyAsInt(combinedLightIn);

            RenderMaterial material = new RenderMaterial(Atlases.CHEST_SHEET, CuboidChestModels.chooseChestTexture(chestType));
            IVertexBuilder ivertexbuilder = material.buffer(bufferIn, RenderType::entityCutout);

            this.handleModelRender(matrixStackIn, ivertexbuilder, this.chestLid, this.chestLock, this.chestBottom, f1, i, combinedOverlayIn);

            matrixStackIn.popPose();
        }
    }

    private void handleModelRender(MatrixStack matrixStackIn, IVertexBuilder iVertexBuilder, ModelRenderer firstModel, ModelRenderer secondModel, ModelRenderer thirdModel, float f1, int vi1, int vi2) {
        firstModel.xRot = -(f1 * ((float) Math.PI / 2F));
        secondModel.xRot = firstModel.xRot;
        firstModel.render(matrixStackIn, iVertexBuilder, vi1, vi2);
        secondModel.render(matrixStackIn, iVertexBuilder, vi1, vi2);
        thirdModel.render(matrixStackIn, iVertexBuilder, vi1, vi2);
    }
}