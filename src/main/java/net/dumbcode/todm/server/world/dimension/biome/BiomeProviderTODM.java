package net.dumbcode.todm.server.world.dimension.biome;

import com.google.common.collect.Lists;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.init.Biomes;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;
import net.minecraft.world.storage.WorldInfo;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/*
 * TODO: Go through and rename variables (if I can) and play around with values
 * 99% of this is just minecraft's default
 */
public class BiomeProviderTODM extends BiomeProvider {

    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private final BiomeCache biomeCache;
    private final List<Biome> spawnBiomes;
    public static List<Biome> allowedBiomes = Lists.newArrayList(BiomeHandler.SCORCHED_BIOME);


    private BiomeProviderTODM(long seed, WorldType worldType)
    {
        biomeCache = new BiomeCache(this);
        spawnBiomes = Lists.newArrayList(allowedBiomes);
        GenLayer[] agenlayer = GenLayer.initializeAllBiomeGenerators(seed, worldType, null);
        agenlayer = getModdedBiomeGenerators(worldType, seed, agenlayer);
        this.genBiomes = agenlayer[0];
        this.biomeIndexLayer = agenlayer[1];
    }

    public BiomeProviderTODM(WorldInfo info)
    {
        this(info.getSeed(), info.getTerrainType());
    }

    public BiomeCache getBiomeCache() {
        return biomeCache;
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return this.spawnBiomes;
    }

    @Override
    @Nullable
    public BlockPos findBiomePosition(int x, int z, int range, List<Biome> biomes, Random random)
    {
        IntCache.resetIntCache();
        int i = x - range >> 2;
        int j = z - range >> 2;
        int k = x + range >> 2;
        int l = z + range >> 2;
        int i1 = k - i + 1;
        int j1 = l - j + 1;
        int[] aint = this.genBiomes.getInts(i, j, i1, j1);
        BlockPos blockpos = null;
        int k1 = 0;

        for (int l1 = 0; l1 < i1 * j1; ++l1)
        {
            int i2 = i + l1 % i1 << 2;
            int j2 = j + l1 / i1 << 2;
            Biome biome = Biome.getBiome(aint[l1]);

            if (biomes.contains(biome) && (blockpos == null || random.nextInt(k1 + 1) == 0))
            {
                blockpos = new BlockPos(i2, 0, j2);
                ++k1;
            }
        }

        return blockpos;
    }

    @Override
    public Biome[] getBiomes(@Nullable Biome[] listToReuse, int x, int z, int width, int length, boolean cacheFlag)
    {
        IntCache.resetIntCache();

        if (listToReuse == null || listToReuse.length < width * length)
        {
            listToReuse = new Biome[width * length];
        }

        if (cacheFlag && width == 16 && length == 16 && (x & 15) == 0 && (z & 15) == 0)
        {
            Biome[] abiome = this.biomeCache.getCachedBiomes(x, z);
            System.arraycopy(abiome, 0, listToReuse, 0, width * length);
            return listToReuse;
        }
        else
        {
            int[] aint = this.biomeIndexLayer.getInts(x, z, width, length);

            for (int i = 0; i < width * length; ++i)
            {
                listToReuse[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
            }

            return listToReuse;
        }
    }

    @Override
    public Biome[] getBiomesForGeneration(Biome[] biomes, int x, int z, int width, int height)
    {
        IntCache.resetIntCache();

        if (biomes == null || biomes.length < width * height)
        {
            biomes = new Biome[width * height];
        }

        int[] aint = this.genBiomes.getInts(x, z, width, height);

        for (int i = 0; i < width * height; ++i)
        {
            biomes[i] = Biome.getBiome(aint[i], Biomes.DEFAULT);
        }
        return biomes;
    }
}
