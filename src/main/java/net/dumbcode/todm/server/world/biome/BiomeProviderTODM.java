package net.dumbcode.todm.server.world.biome;

import com.google.common.collect.Lists;
import net.dumbcode.todm.server.world.layers.GenLayerTODM;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.layer.GenLayer;

import java.util.List;

/*
 * 99% of this is just minecraft's default
 */
public class BiomeProviderTODM extends BiomeProvider {

    private World world;
    private final BiomeCache biomeCache;
    private final List<Biome> spawnBiomes;
    public static List<Biome> allowedBiomes = Lists.newArrayList(BiomeHandler.SCORCHED_BIOME);


    public BiomeProviderTODM(World world)
    {
        super(world.getWorldInfo());
        this.biomeCache = new BiomeCache(this);
        this.spawnBiomes = Lists.newArrayList(allowedBiomes);
        this.world = world;
    }

    @Override
    public GenLayer[] getModdedBiomeGenerators(WorldType worldType, long seed, GenLayer[] original)
    {
        original = GenLayerTODM.initializeAllBiomeGenerators(seed);
        return super.getModdedBiomeGenerators(worldType, seed, original);
    }

    @Override
    public void cleanupCache() {
        this.biomeCache.cleanupCache();
        super.cleanupCache();
    }

    @Override
    public List<Biome> getBiomesToSpawnIn() {
        return this.spawnBiomes;
    }
}
