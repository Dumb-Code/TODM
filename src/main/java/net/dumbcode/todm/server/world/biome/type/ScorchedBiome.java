package net.dumbcode.todm.server.world.biome.type;

import net.dumbcode.todm.server.world.biome.decorator.BiomeDecoratorTODM;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;

import java.util.Random;

//TODO:
public class ScorchedBiome extends Biome {

    public ScorchedBiome() {
        super(new BiomeProperties("Scorched"));
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        this.decorator.decorate(world, rand, this, pos);
    }

//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getGrassColorAtPos(BlockPos pos)
//    {
//        return 2000;
//    }
//
//    @Override
//    @SideOnly(Side.CLIENT)
//    public int getWaterColorMultiplier()
//    {
//        return 981;
//    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        BiomeDecorator biomeDecorator = new BiomeDecoratorTODM();

        biomeDecorator.waterlilyPerChunk = 0;
        biomeDecorator.treesPerChunk = 3;
        biomeDecorator.extraTreeChance = 0.1F;
        biomeDecorator.flowersPerChunk = 100;
        biomeDecorator.grassPerChunk = 2;
        biomeDecorator.deadBushPerChunk = 0;
        biomeDecorator.mushroomsPerChunk = 0;
        biomeDecorator.reedsPerChunk = 0;
        biomeDecorator.cactiPerChunk = 0;
        biomeDecorator.gravelPatchesPerChunk = 1;
        biomeDecorator.sandPatchesPerChunk = 3;
        biomeDecorator.clayPerChunk = 1;
        biomeDecorator.bigMushroomsPerChunk = 0;
        biomeDecorator.generateFalls = true;

        return getModdedBiomeDecorator(biomeDecorator);
    }
}
