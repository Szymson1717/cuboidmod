package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.SingularityResourceGeneratorContainerBase;
import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.SingularityResourceGeneratorTileEntityBase;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SingularityResourceGeneratorScreenBase<T extends SingularityResourceGeneratorContainerBase> extends AbstractContainerScreen<T> {

    public static ResourceLocation GUI = CuboidMod.getModId("textures/gui/singularity_resource_generator.png");
    Inventory playerInv;
    Component name;
    SingularityResourceGeneratorTileEntityBase tile;

    public SingularityResourceGeneratorScreenBase(T container, Inventory inv, Component name) {
        super(container, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
    }

    @Override
    public void render(PoseStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(PoseStack matrix, int mouseX, int mouseY) {
        String[] words = this.name.getString().split("\\s+");
        String firstLine = words[0] + ((words.length > 1) ? " " + words[1] : "");
        String secondLine = "";
        for (int i = 2; i < words.length; i++)
            secondLine += words[i] + (i < words.length-1 ? " " : "");

        this.minecraft.font.draw(matrix, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752);

        Component first = Component.literal(firstLine);
        Component second = Component.literal(secondLine);

        this.minecraft.font.draw(matrix, first, this.imageWidth / 2 - this.minecraft.font.width(firstLine) / 2, 6, 4210752);
        this.minecraft.font.draw(matrix, second, this.imageWidth / 2 - this.minecraft.font.width(secondLine) / 2, 18, 4210752);
    }

    @Override
    protected void renderBg(PoseStack matrix, float partialTicks, int mouseX, int mouseY) {
        // render the main container background
        // RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrix, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }

    @SuppressWarnings("resource")
    private SingularityResourceGeneratorTileEntityBase getTileEntity() {
        ClientLevel world = this.getMinecraft().level;

        if (world != null) {
            BlockEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof SingularityResourceGeneratorTileEntityBase) {
                return (SingularityResourceGeneratorTileEntityBase) tile;
            }
        }

        return null;
    }

}