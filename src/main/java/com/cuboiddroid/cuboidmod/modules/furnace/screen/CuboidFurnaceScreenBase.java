package com.cuboiddroid.cuboidmod.modules.furnace.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.CuboidFurnaceContainerBase;
import net.minecraft.client.gui.GuiGraphics;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CuboidFurnaceScreenBase<T extends CuboidFurnaceContainerBase> extends AbstractContainerScreen<T> {

    public ResourceLocation GUI = CuboidMod.getModId("textures/gui/furnace.png");
    Inventory playerInv;
    Component name;

    public CuboidFurnaceScreenBase(T t, Inventory inv, Component name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    public void render(GuiGraphics matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderLabels(GuiGraphics matrix, int mouseX, int mouseY) {
        matrix.drawString(this.font, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752, false);
        matrix.drawString(this.font, name, this.imageWidth / 2 - this.minecraft.font.width(name.getString()) / 2, 6, 4210752, false);

        if (this.menu.showInventoryButtons() && this.menu.getRedstoneMode() == 4) {
            int comSub = this.menu.getComSub();
            int i = comSub > 9 ? 28 : 31;
            matrix.drawString(this.font, Component.literal("" + comSub), i - 42, 90, 4210752, false);
        }
    }

    @Override
    protected void renderBg(GuiGraphics matrix, float partialTicks, int mouseX, int mouseY) {
        // RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        matrix.blit(GUI, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        int i;
        if (((CuboidFurnaceContainerBase) this.menu).isBurning()) {
            i = ((CuboidFurnaceContainerBase) this.menu).getBurnLeftScaled(13);
            matrix.blit(GUI, this.leftPos + 56, this.topPos + 36 + 12 - i, 176, 12 - i, 14, i + 1);
        }

        i = ((CuboidFurnaceContainerBase) this.menu).getCookScaled(24);
        matrix.blit(GUI, this.leftPos + 79, this.topPos + 34, 176, 14, i + 1, 16);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static boolean isKeyDown(int glfw) {
        InputConstants.Key key = InputConstants.Type.KEYSYM.getOrCreate(glfw);
        int keyCode = key.getValue();
        if (keyCode != InputConstants.UNKNOWN.getValue()) {
            long windowHandle = Minecraft.getInstance().getWindow().getWindow();
            try {
                if (key.getType() == InputConstants.Type.KEYSYM) {
                    return InputConstants.isKeyDown(windowHandle, keyCode);
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }
}