package net.dumbcode.todm.server.world.dimension.biome;

import com.google.common.collect.Lists;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.storage.WorldInfo;

import java.util.List;

public class BiomeProviderTODM extends BiomeProvider {

    private GenLayer genBiomes;
    private GenLayer biomeIndexLayer;
    private final BiomeCache biomeCache;
    private final List<Biome> spawnBiomes;
    public static List<Biome> allowedBiomes = Lists.newArrayList(BiomeHandler.SCORCHED_BIOME);


    private BiomeProviderTODM(long seed, WorldType worldTypeIn)
    {
        biomeCache = new BiomeCache(this);
        spawnBiomes = Lists.newArrayList(allowedBiomes);
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
}
