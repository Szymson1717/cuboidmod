package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.DoubleBlockCombiner;
import net.minecraft.core.Direction;
import com.mojang.math.Vector3f;
import net.minecraft.world.level.Level;

public class CuboidChestTileEntityRenderer<T extends BlockEntity & LidBlockEntity> implements BlockEntityRenderer<T> {

    private final LayerDefinition chest;
    private final ModelPart chestLid;
    private final ModelPart chestBottom;
    private final ModelPart chestLock;

    public CuboidChestTileEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.chest = createBodyLayer();
        this.chestLid = this.chest.bakeRoot().getChild("base");
        this.chestBottom = this.chest.bakeRoot().getChild("lid");
        this.chestLock = this.chest.bakeRoot().getChild("lock");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        partdefinition.addOrReplaceChild("base", CubeListBuilder.create()
                .texOffs(0, 19)
                .addBox(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), PartPose.ZERO);
        partdefinition.addOrReplaceChild("lid", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(1.0F, 0.0F, 0.0F, 14.0F, 5.0F, 14.0F), PartPose.offset(0.0F, 9.0F, 1.0F));
        partdefinition.addOrReplaceChild("lock", CubeListBuilder.create()
                .texOffs(0, 0)
                .addBox(7.0F, -1.0F, 15.0F, 2.0F, 4.0F, 1.0F), PartPose.offset(0.0F, 8.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void render(T tileEntityIn, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        CuboidChestTileEntityBase tileEntity = (CuboidChestTileEntityBase) tileEntityIn;

        Level level = tileEntity.getLevel();
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

            DoubleBlockCombiner.NeighborCombineResult<? extends CuboidChestTileEntityBase> iCallbackWrapper;
            if (flag) {
                iCallbackWrapper = cuboidChestBlock.getWrapper(blockstate, level, tileEntity.getBlockPos(), true);
            }
            else {
                iCallbackWrapper = DoubleBlockCombiner.Combiner::acceptNone;
            }

            float f1 = iCallbackWrapper.apply(CuboidChestBlockBase.getLid((LidBlockEntity) tileEntity)).get(partialTicks);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = iCallbackWrapper.apply(new BrightnessCombiner<>()).applyAsInt(combinedLightIn);

            Material material = new Material(Sheets.CHEST_SHEET, CuboidChestModels.chooseChestTexture(chestType));
            VertexConsumer ivertexbuilder = material.buffer(bufferIn, RenderType::entityCutout);

            this.handleModelRender(matrixStackIn, ivertexbuilder, f1, i, combinedOverlayIn);

            matrixStackIn.popPose();
        }
    }

    private void handleModelRender(PoseStack matrixStackIn, VertexConsumer iVertexBuilder, float f1, int vi1, int vi2) {
        this.chestLid.xRot = -(f1 * ((float) Math.PI / 2F));
        this.chestLock.xRot = this.chestLid.xRot;
        this.chestLid.render(matrixStackIn, iVertexBuilder, vi1, vi2);
        this.chestLock.render(matrixStackIn, iVertexBuilder, vi1, vi2);
        this.chestBottom.render(matrixStackIn, iVertexBuilder, vi1, vi2);
    }
}