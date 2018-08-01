package net.dumbcode.todm.server.world.dimension.biome;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.world.dimension.biome.type.ScorchedBiome;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class BiomeHandler {

    public static final Biome SCORCHED_BIOME = new ScorchedBiome();

    @SubscribeEvent
    public static void onEvent(final RegistryEvent.Register<Biome> event)
    {
        event.getRegistry().register(SCORCHED_BIOME.setRegistryName(TODM.MODID, SCORCHED_BIOME.getBiomeName()));
    }

    public static void initBiomeManagerAndDictionary()
    {
        BiomeManager.addBiome(BiomeManager.BiomeType.DESERT, new BiomeManager.BiomeEntry(SCORCHED_BIOME, 10));
        BiomeManager.addSpawnBiome(SCORCHED_BIOME);
        BiomeManager.addStrongholdBiome(SCORCHED_BIOME);
        BiomeManager.addVillageBiome(SCORCHED_BIOME, true);
        BiomeDictionary.addTypes(SCORCHED_BIOME,
                BiomeDictionary.Type.DRY,
                BiomeDictionary.Type.DEAD
        );
    }

}
