package com.cuboiddroid.cuboidmod.modules.powergen.block;

import com.cuboiddroid.cuboidmod.modules.powergen.tile.KudbebeddaSingularityPowerGeneratorTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class KudbebeddaSingularityPowerGeneratorBlock extends SingularityPowerGeneratorBlockBase {

    public KudbebeddaSingularityPowerGeneratorBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new KudbebeddaSingularityPowerGeneratorTileEntity();
    }
}
