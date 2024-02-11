package com.cuboiddroid.cuboidmod.modules.chest.render;

import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestBlockBase;
import com.cuboiddroid.cuboidmod.modules.chest.tile.CuboidChestTileEntityBase;
import com.cuboiddroid.cuboidmod.modules.chest.tile.KudbebeddaChestTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.NotarfbadiumChestTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.NotsogudiumChestTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.ThatlduChestTileEntity;
import com.cuboiddroid.cuboidmod.modules.chest.tile.WikidiumChestTileEntity;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public class CuboidChestItemStackRenderer<T extends BlockEntity> extends BlockEntityWithoutLevelRenderer {

    private BlockEntityRenderDispatcher blockRenderDispatcher = Minecraft.getInstance().getBlockEntityRenderDispatcher();
    private Supplier<CuboidChestTileEntityBase> block;

    public CuboidChestItemStackRenderer(BlockEntityRenderDispatcher blockRenderDispatcher, EntityModelSet context, Supplier<CuboidChestTileEntityBase> block) {
        super(blockRenderDispatcher, context);

        this.blockRenderDispatcher = blockRenderDispatcher;
        this.block = block;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemTransforms.TransformType transformType, PoseStack matrixStack, MultiBufferSource renderType, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) stack.getItem()).getBlock();
            BlockState blockstate = block.defaultBlockState();
            if (block instanceof CuboidChestBlockBase) {

                BlockEntity entity = null;

                if (blockstate.is(ModBlocks.THATLDU_CHEST.get()))
                    entity = new ThatlduChestTileEntity();
                if (blockstate.is(ModBlocks.WIKIDIUM_CHEST.get()))
                    entity = new WikidiumChestTileEntity();
                if (blockstate.is(ModBlocks.KUDBEBEDDA_CHEST.get()))
                    entity = new KudbebeddaChestTileEntity();
                if (blockstate.is(ModBlocks.NOTSOGUDIUM_CHEST.get()))
                    entity = new NotsogudiumChestTileEntity();
                if (blockstate.is(ModBlocks.NOTARFBADIUM_CHEST.get()))
                    entity = new NotarfbadiumChestTileEntity();

                if (entity != null) {
                    // CuboidChestBlockBase chest = (CuboidChestBlockBase) block;
                    // CuboidChestTileEntityBase blockDisplay = chest.getType().makeEntity();
    
                    // ModelPart model = CuboidChestTileEntityRenderer.createBodyLayer().bakeRoot();
                    matrixStack.pushPose();
                    // matrixStack.translate(0.5D, 0.5D, 0.5D);
                    // matrixStack.translate(-0.5D, -0.5D, -0.5D);
                    // matrixStack.translate(0.5, 0.8, 0.5);
                    // matrixStack.mulPose(Axis.YP.rotationDegrees(-90));
                    // matrixStack.mulPose(Axis.XP.rotationDegrees(180));
                    // matrixStack.scale(0.6F, 0.6F, 0.6F);
                    // if (stack.is(BlockRegistry.kitraStatue.get().asItem())) {
                    //     matrixStack.scale(0.25F, 0.25F, 0.25F);
                    //     matrixStack.translate(0F, 3F, 0F);
                    // }
                    this.blockRenderDispatcher.renderItem(entity, matrixStack, renderType, combinedLight, combinedOverlay);
    
                    // if (CuboidChestModels.chooseChestTexture(chest.getType()) != null) {
                    //     VertexConsumer builder = renderType.getBuffer(RenderType.entityCutout(CuboidChestModels.chooseChestTexture(chest.getType())));
                    //     model.render(matrixStack, builder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
                    // }
                    matrixStack.popPose();
                }
            }
        }
    }

    // public CuboidChestItemStackRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
    //     super(p_172550_, p_172551_);
    //     //TODO Auto-generated constructor stub
    // }

    // private final Supplier<T> te;
    // private BlockEntityRenderDispatcher dispatcher;

    // // public CuboidChestItemStackRenderer(Supplier<T> te) {
    // //     this(null, null, te);
    // // }

    // // public CuboidChestItemStackRenderer(BlockEntityRenderDispatcher dispatcher, EntityModelSet model, Supplier<T> te) {
    // //     super(dispatcher, model);
    // //     this.dispatcher = dispatcher;
    // //     this.te = te;
    // // }

    // @Override
    // public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack matrixStackIn, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
    //     this.dispatcher.renderItem(this.te.get(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
    // }
}
