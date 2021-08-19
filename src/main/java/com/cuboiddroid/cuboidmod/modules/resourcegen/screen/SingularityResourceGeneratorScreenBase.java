package com.cuboiddroid.cuboidmod.modules.resourcegen.screen;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.resourcegen.inventory.SingularityResourceGeneratorContainerBase;
import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.SingularityResourceGeneratorTileEntityBase;
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

@OnlyIn(Dist.CLIENT)
public class SingularityResourceGeneratorScreenBase<T extends SingularityResourceGeneratorContainerBase> extends ContainerScreen<T> {

    public static ResourceLocation GUI = CuboidMod.getModId("textures/gui/singularity_resource_generator.png");
    PlayerInventory playerInv;
    ITextComponent name;
    SingularityResourceGeneratorTileEntityBase tile;

    public SingularityResourceGeneratorScreenBase(T container, PlayerInventory inv, ITextComponent name) {
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
    }

    private SingularityResourceGeneratorTileEntityBase getTileEntity() {
        ClientWorld world = this.getMinecraft().level;

        if (world != null) {
            TileEntity tile = world.getBlockEntity(this.getMenu().getPos());

            if (tile instanceof SingularityResourceGeneratorTileEntityBase) {
                return (SingularityResourceGeneratorTileEntityBase) tile;
            }
        }

        return null;
    }

}