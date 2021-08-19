package com.cuboiddroid.cuboidmod.modules.furnace.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.furnace.inventory.CuboidFurnaceContainerBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CuboidFurnaceScreenBase<T extends CuboidFurnaceContainerBase> extends ContainerScreen<T> {

    public ResourceLocation GUI = CuboidMod.getModId("textures/gui/furnace.png");
    PlayerInventory playerInv;
    ITextComponent name;

    public CuboidFurnaceScreenBase(T t, PlayerInventory inv, ITextComponent name) {
        super(t, inv, name);
        playerInv = inv;
        this.name = name;
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    protected void renderLabels(MatrixStack matrix, int mouseX, int mouseY) {
        this.minecraft.font.draw(matrix, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752);
        this.minecraft.font.draw(matrix, name, this.imageWidth / 2 - this.minecraft.font.width(name.getString()) / 2, 6, 4210752);

        if (this.menu.showInventoryButtons() && this.menu.getRedstoneMode() == 4) {
            int comSub = this.menu.getComSub();
            int i = comSub > 9 ? 28 : 31;
            this.minecraft.font.draw(matrix, new StringTextComponent("" + comSub), i - 42, 90, 4210752);
        }
    }

    @Override
    protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrix, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
        int i;
        if (((CuboidFurnaceContainerBase) this.menu).isBurning()) {
            i = ((CuboidFurnaceContainerBase) this.menu).getBurnLeftScaled(13);
            this.blit(matrix, this.leftPos + 56, this.topPos + 36 + 12 - i, 176, 12 - i, 14, i + 1);
        }

        i = ((CuboidFurnaceContainerBase) this.menu).getCookScaled(24);
        this.blit(matrix, this.leftPos + 79, this.topPos + 34, 176, 14, i + 1, 16);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return super.mouseClicked(mouseX, mouseY, button);
    }

    public static boolean isKeyDown(int glfw) {
        InputMappings.Input key = InputMappings.Type.KEYSYM.getOrCreate(glfw);
        int keyCode = key.getValue();
        if (keyCode != InputMappings.UNKNOWN.getValue()) {
            long windowHandle = Minecraft.getInstance().getWindow().getWindow();
            try {
                if (key.getType() == InputMappings.Type.KEYSYM) {
                    return InputMappings.isKeyDown(windowHandle, keyCode);
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }
}