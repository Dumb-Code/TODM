package net.dumbcode.todm.server.world.biome.type;

import net.dumbcode.todm.server.world.biome.decorator.BiomeDecoratorTODM;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Random;

//TODO:
public class ScorchedBiome extends Biome {

    public ScorchedBiome()
    {
        super(new BiomeProperties("Scorched").setWaterColor(0xd2a679));
        this.topBlock = Blocks.GRASS.getDefaultState();
        this.fillerBlock = Blocks.DIRT.getDefaultState();
    }

    @Override
    public void decorate(World world, Random rand, BlockPos pos) {
        this.decorator.decorate(world, rand, this, pos);
    }

    @Override
    public BiomeDecorator createBiomeDecorator()
    {
        BiomeDecorator biomeDecorator = new BiomeDecoratorTODM();

        biomeDecorator.waterlilyPerChunk = 0;
        biomeDecorator.treesPerChunk = 0;
        biomeDecorator.extraTreeChance = 0F;
        biomeDecorator.flowersPerChunk = 0;
        biomeDecorator.grassPerChunk = 0;
        biomeDecorator.deadBushPerChunk = 40;
        biomeDecorator.mushroomsPerChunk = 0;
        biomeDecorator.reedsPerChunk = 0;
        biomeDecorator.cactiPerChunk = 0;
        biomeDecorator.gravelPatchesPerChunk = 1;
        biomeDecorator.sandPatchesPerChunk = 0;
        biomeDecorator.clayPerChunk = 1;
        biomeDecorator.bigMushroomsPerChunk = 0;
        biomeDecorator.generateFalls = true;

        return biomeDecorator;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xffcc66;
    }
}
