package com.cuboiddroid.cuboidmod.modules.powergen.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.powergen.inventory.SingularityPowerGeneratorContainerBase;
import com.cuboiddroid.cuboidmod.modules.powergen.tile.SingularityPowerGeneratorTileEntityBase;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class SingularityPowerGeneratorScreenBase<T extends SingularityPowerGeneratorContainerBase> extends ContainerScreen<T> {

    // coords for inside the visible bar
    private static final int BAR_TOP_LEFT_X = 34;
    private PlayerInventory playerInv;
    private ITextComponent name;
    private SingularityPowerGeneratorTileEntityBase tile;

    // coords for inside the hidden bar
    private static final int HIDDEN_BAR_TOP_LEFT_X = 177;
    private static final int HIDDEN_BAR_TOP_LEFT_Y = 1;
    private static final int BAR_WIDTH = 6;
    private static final int BAR_HEIGHT = 34;
    public static ResourceLocation GUI = new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/singularity_power_generator.png");
    private static final int BAR_TOP_LEFT_Y = 35;

    public SingularityPowerGeneratorScreenBase(T container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
        this.playerInv = inv;
        this.name = name;
    }

    @Override
    protected void init() {
        super.init();

        this.tile = this.getTileEntity();
    }

    @Override
    public void render(MatrixStack matrix, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrix);
        super.render(matrix, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrix, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrix, int mouseX, int mouseY) {
        String[] words = this.name.getString().split("\\s+");
        String firstLine = words[0] + ((words.length > 1) ? " " + words[1] : "");
        String secondLine = "";
        for (int i = 2; i < words.length; i++)
            secondLine += words[i] + (i < words.length-1 ? " " : "");

        this.minecraft.font.draw(matrix, this.playerInv.getDisplayName(), 7, this.imageHeight - 93, 4210752);

        ITextComponent first = new StringTextComponent(firstLine);
        ITextComponent second = new StringTextComponent(secondLine);

        this.minecraft.font.draw(matrix, first, this.imageWidth / 2 - this.minecraft.font.width(firstLine) / 2, 6, 4210752);
        this.minecraft.font.draw(matrix, second, this.imageWidth / 2 - this.minecraft.font.width(secondLine) / 2, 18, 4210752);
    }

    @Override
    protected void renderBg(MatrixStack matrix, float partialTicks, int mouseX, int mouseY) {
        // render the main container background
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.minecraft.getTextureManager().bind(GUI);
        int relX = (this.width - this.imageWidth) / 2;
        int relY = (this.height - this.imageHeight) / 2;
        this.blit(matrix, relX, relY, 0, 0, this.imageWidth, this.imageHeight);

        // render the energy bar
        int energy = this.menu.getEnergy();
        if (energy > 0) {
            int capacity = this.menu.getEnergyCapacity();

            if (capacity > 0) {
                int powerHeight = BAR_HEIGHT * energy / this.menu.getEnergyCapacity();

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
    protected void renderTooltip(MatrixStack matrix, int mouseX, int mouseY) {
        super.renderTooltip(matrix, mouseX, mouseY);

        // tooltip to show energy stored & capacity
        if (mouseX >= this.leftPos + BAR_TOP_LEFT_X && mouseX <= this.leftPos + BAR_TOP_LEFT_X + BAR_WIDTH && mouseY >= this.topPos + BAR_TOP_LEFT_Y && mouseY <= this.topPos + BAR_TOP_LEFT_Y + BAR_HEIGHT) {
            List<ITextComponent> tooltip = new ArrayList<>();
            if (this.menu.getEnergy() <= 0) {
                tooltip.add(new StringTextComponent("Empty"));
            } else {
                StringTextComponent text = new StringTextComponent(this.menu.getEnergy() + " / " + this.menu.getEnergyCapacity());
                tooltip.add(text);
            }

            this.renderComponentTooltip(matrix, tooltip, mouseX, mouseY);
        }
    }

    private SingularityPowerGeneratorTileEntityBase getTileEntity() {
        ClientWorld world = this.getMinecraft().level;

        if (world != null) {
            TileEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof SingularityPowerGeneratorTileEntityBase) {
                return (SingularityPowerGeneratorTileEntityBase) tile;
            }
        }

        return null;
    }
}