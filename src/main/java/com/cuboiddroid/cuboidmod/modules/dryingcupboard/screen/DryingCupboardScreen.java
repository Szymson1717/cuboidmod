package com.cuboiddroid.cuboidmod.modules.dryingcupboard.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.inventory.DryingCupboardContainer;
import com.cuboiddroid.cuboidmod.modules.dryingcupboard.tile.DryingCupboardTileEntity;
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

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public class DryingCupboardScreen extends AbstractContainerScreen<DryingCupboardContainer> {

    // coords for inside the hidden bar
    private static final int HIDDEN_BAR_TOP_LEFT_X = 177;
    private static final int HIDDEN_BAR_TOP_LEFT_Y = 1;
    private static final int BAR_WIDTH = 6;
    private static final int BAR_HEIGHT = 51;
    // coords for inside the visible bar
    private static final int BAR_TOP_LEFT_X = 13;
    private static final int BAR_TOP_LEFT_Y = 20;
    // coords for the hidden progress arrow
    private static final int HIDDEN_MINI_BAR_TOP_LEFT_X = 185;
    private static final int HIDDEN_MINI_BAR_TOP_LEFT_Y = 1;
    private static final int MINI_BAR_WIDTH = 4;
    private static final int MINI_BAR_HEIGHT = 11;
    // coords for the visible arrow
    private static final int MINI_BAR_TOP_LEFT_X = 32;
    private static final int MINI_BAR_TOP_LEFT_Y = 40;
    public static ResourceLocation GUI = CuboidMod.getModId("textures/gui/drying_cupboard.png");
    Inventory playerInv;
    Component name;
    DryingCupboardTileEntity tile;

    public DryingCupboardScreen(DryingCupboardContainer container, Inventory inv, Component name) {
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
        this.minecraft.font.draw(matrix, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752);

        String name = this.name.getString();
        Component nameText = Component.literal(name);

        this.minecraft.font.draw(matrix, nameText, this.imageWidth / 2 - this.minecraft.font.width(name) / 2, 6, 4210752);
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

        // render progress mini bars
        for (int slotIndex = 0; slotIndex < DryingCupboardTileEntity.INPUT_SLOTS; slotIndex++) {
            float progress = ((this.tile.getProcessingTime(slotIndex) * 1.0F) / this.tile.getRecipeTime(slotIndex));

            if (progress > 0) {
                // draw it -
                //   matrix,
                //   top left X to place it at,
                //   top left Y to place it at
                //   top left X to copy from,
                //   top left Y to copy from,
                //   width to copy,
                //   height to copy
                this.blit(matrix,
                        this.leftPos + MINI_BAR_TOP_LEFT_X + 18 * slotIndex,
                        this.topPos + MINI_BAR_TOP_LEFT_Y,
                        HIDDEN_MINI_BAR_TOP_LEFT_X,
                        HIDDEN_MINI_BAR_TOP_LEFT_Y,
                        MINI_BAR_WIDTH,
                        (int) ((1 - progress) * (MINI_BAR_HEIGHT + 1)));
            }
        }

        // render the energy bar
        int energy = this.menu.getEnergy();
        if (energy > 0) {
            int capacity = this.menu.getEnergyCapacity();

            if (capacity > 0) {
                int powerHeight = BAR_HEIGHT * energy / capacity;

                // draw it -
                //   matrix,
                //   top left X to place it at,
                //   top left Y to place it at
                //   top left X to copy from,
                //   top left Y to copy from,
                //   width to copy,
                //   height to copy
                this.blit(matrix,
                        this.leftPos + BAR_TOP_LEFT_X,
                        this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        HIDDEN_BAR_TOP_LEFT_X,
                        HIDDEN_BAR_TOP_LEFT_Y + BAR_HEIGHT - powerHeight,
                        BAR_WIDTH,
                        powerHeight + 1);
            }
        }
    }

    @Override
    protected void renderTooltip(PoseStack matrix, int mouseX, int mouseY) {
        super.renderTooltip(matrix, mouseX, mouseY);

        // tooltip to show energy stored & capacity
        if (mouseX >= this.leftPos + BAR_TOP_LEFT_X && mouseX <= this.leftPos + BAR_TOP_LEFT_X + BAR_WIDTH && mouseY >= this.topPos + BAR_TOP_LEFT_Y && mouseY <= this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT) {
            List<Component> tooltip = new ArrayList<>();
            if (this.menu.getEnergy() <= 0) {
                tooltip.add(Component.literal("Empty"));
            } else {
                Component text = Component.literal(this.menu.getEnergy() + " / " + this.menu.getEnergyCapacity());
                tooltip.add(text);
            }

            this.renderComponentTooltip(matrix, tooltip, mouseX, mouseY);
        }
    }

    @SuppressWarnings("resource")
    private DryingCupboardTileEntity getTileEntity() {
        ClientLevel world = this.getMinecraft().level;

        if (world != null) {
            BlockEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof DryingCupboardTileEntity) {
                return (DryingCupboardTileEntity) tile;
            }
        }

        return null;
    }
}

