package com.cuboiddroid.cuboidmod.modules.chest.screen;

import com.cuboiddroid.cuboidmod.modules.chest.block.CuboidChestTypes;
import com.cuboiddroid.cuboidmod.modules.chest.inventory.CuboidChestContainer;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CuboidChestScreen extends AbstractContainerScreen<CuboidChestContainer> {

    private final CuboidChestTypes chestType;

    private final int textureXSize;

    private final int textureYSize;

    public CuboidChestScreen(CuboidChestContainer container, Inventory playerInventory, Component title) {
        super(container, playerInventory, title);

        this.chestType = container.getChestType();
        this.imageWidth = container.getChestType().xSize;
        this.imageHeight = container.getChestType().ySize;
        this.textureXSize = container.getChestType().textureXSize;
        this.textureYSize = container.getChestType().textureYSize;

        // this.passEvents = false;
    }

    @Override
    public void render(GuiGraphics matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        matrix.renderComponentHoverEffect(this.font, null, mouseX, mouseY);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics matrix, int mouseX, int mouseY) {
        matrix.drawString(this.font, this.title, 8, 6, 4210752);
        matrix.drawString(this.font, this.playerInventoryTitle, 8, (this.imageHeight - 96 + 2), 4210752);
    }

    @Override
    protected void renderBg(GuiGraphics matrix, float partialTicks, int mouseX, int mouseY) {
        // RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.chestType.guiTexture);

        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;

        matrix.blit(this.chestType.guiTexture, x, y, 0, 0, this.imageWidth, this.imageHeight, this.textureXSize, this.textureYSize);
    }
}
