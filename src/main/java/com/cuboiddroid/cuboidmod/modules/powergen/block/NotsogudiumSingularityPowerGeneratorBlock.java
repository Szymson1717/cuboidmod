package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.NotsogudiumSingularityPowerGeneratorTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class NotsogudiumSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public NotsogudiumSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new NotsogudiumSingularityPowerGeneratorTileEntity();
    }
}
