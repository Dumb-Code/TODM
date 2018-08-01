package net.dumbcode.todm.server.world.dimension.biome.type;

import net.dumbcode.todm.server.world.dimension.biome.decorator.BiomeDecoratorTODM;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

public class ScorchedBiome extends Biome {

    public ScorchedBiome() {
        super(new BiomeProperties("Scorched"));
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();
    }

    @Override
    public BiomeDecorator createBiomeDecorator() {
        return new BiomeDecoratorTODM();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        super.decorate(worldIn, rand, pos);
    }
}
