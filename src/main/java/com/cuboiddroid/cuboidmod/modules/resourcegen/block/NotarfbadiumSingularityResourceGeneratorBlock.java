package com.cuboiddroid.cuboidmod.modules.resourcegen.block;

import com.cuboiddroid.cuboidmod.modules.resourcegen.tile.NotarfbadiumSingularityResourceGeneratorTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class NotarfbadiumSingularityResourceGeneratorBlock extends SingularityResourceGeneratorBlockBase {

    public NotarfbadiumSingularityResourceGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 3;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new NotarfbadiumSingularityResourceGeneratorTileEntity();
    }
}