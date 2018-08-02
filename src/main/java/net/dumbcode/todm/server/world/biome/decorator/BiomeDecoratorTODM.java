package net.dumbcode.todm.server.world.biome.decorator;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

//TODO:
public class BiomeDecoratorTODM extends BiomeDecorator {


    public BiomeDecoratorTODM()
    {
    }

    @Override
    public void decorate(World worldIn, Random random, Biome biome, BlockPos pos)
    {
        super.decorate(worldIn, random, biome, pos);
    }
}
