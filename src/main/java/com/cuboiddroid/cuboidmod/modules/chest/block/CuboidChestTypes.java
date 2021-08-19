package com.cuboiddroid.cuboidmod.modules.chest.block;

import com.cuboiddroid.cuboidmod.CuboidMod;
import com.cuboiddroid.cuboidmod.modules.chest.tile.*;
import com.cuboiddroid.cuboidmod.setup.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.Locale;

public enum CuboidChestTypes implements IStringSerializable {

    NOTSOGUDIUM(18, 9, 184, 150, new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/notsogudium_container.png"), 256, 256),
    KUDBEBEDDA(27, 9, 184, 168, new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/kudbebedda_container.png"), 256, 256),
    NOTARFBADIUM(36, 9, 184, 186, new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/notarfbadium_container.png"), 256, 256),
    WIKIDIUM(45, 9, 184, 204, new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/wikidium_container.png"), 256, 256),
    THATLDU(54, 9, 184, 222, new ResourceLocation(CuboidMod.MOD_ID, "textures/gui/thatldu_container.png"), 256, 256);

    private final String name;
    public final int size;
    public final int rowLength;
    public final int xSize;
    public final int ySize;
    public final ResourceLocation guiTexture;
    public final int textureXSize;
    public final int textureYSize;

    CuboidChestTypes(int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this(null, size, rowLength, xSize, ySize, guiTexture, textureXSize, textureYSize);
    }

    CuboidChestTypes(@Nullable String name, int size, int rowLength, int xSize, int ySize, ResourceLocation guiTexture, int textureXSize, int textureYSize) {
        this.name = name;
        this.size = size;
        this.rowLength = rowLength;
        this.xSize = xSize;
        this.ySize = ySize;
        this.guiTexture = guiTexture;
        this.textureXSize = textureXSize;
        this.textureYSize = textureYSize;
    }

    public String getId() {
        return this.name().toLowerCase(Locale.ROOT);
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    public int getRowCount() {
        return this.size / this.rowLength;
    }

    public static Block get(CuboidChestTypes type) {
        switch (type) {
            case NOTSOGUDIUM:
                return ModBlocks.NOTSOGUDIUM_CHEST.get();
            case KUDBEBEDDA:
                return ModBlocks.KUDBEBEDDA_CHEST.get();
            case NOTARFBADIUM:
                return ModBlocks.NOTARFBADIUM_CHEST.get();
            case WIKIDIUM:
                return ModBlocks.WIKIDIUM_CHEST.get();
            case THATLDU:
                return ModBlocks.THATLDU_CHEST.get();
            default:
                return Blocks.CHEST;
        }
    }

    public CuboidChestTileEntityBase makeEntity() {
        switch (this) {
            case NOTSOGUDIUM:
                return new NotsogudiumChestTileEntity();
            case KUDBEBEDDA:
                return new KudbebeddaChestTileEntity();
            case NOTARFBADIUM:
                return new NotarfbadiumChestTileEntity();
            case WIKIDIUM:
                return new WikidiumChestTileEntity();
            case THATLDU:
                return new ThatlduChestTileEntity();
            default:
                return null;
        }
    }

    @Override
    public String getSerializedName() {
        return this.name;
    }
}