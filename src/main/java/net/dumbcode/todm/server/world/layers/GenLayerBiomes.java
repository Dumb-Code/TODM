package net.dumbcode.todm.server.world.layers;

import net.dumbcode.todm.server.world.biome.BiomeHandler;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

import java.util.List;

/**
 * http://jsfiddle.net/eddyb/NFa3P/
 */
public class GenLayerBiomes extends GenLayer
{
    private static final int RARE_BIOME_CHANCE = 15;

    public GenLayerBiomes(long seed, GenLayer genLayer)
    {
        this(seed);
        parent = genLayer;
    }

    public GenLayerBiomes(long seed)
    {
        super(seed);
    }

    /**
     * Setting the biomes
     */
    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        int dest[] = IntCache.getIntCache(areaWidth * areaHeight);

        for (int z = 0; z < areaHeight; z++) {
            for (int x = 0; x < areaWidth; x++) {
                initChunkSeed(x + areaX, z + areaY);
                if (nextInt(RARE_BIOME_CHANCE) == 0) {
                    dest[x + z * areaWidth] = Biome.getIdForBiome(getRandomBiome(BiomeHandler.getCommon()));
                } else {
                    dest[x + z * areaWidth] = Biome.getIdForBiome(getRandomBiome(BiomeHandler.getCommon()));
                }
            }
        }

        return dest;
    }

    private Biome getRandomBiome(List<Biome> biomes) {
        return biomes.get(nextInt(biomes.size()));
    }
}