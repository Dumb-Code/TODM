package net.dumbcode.todm.server.world;

import net.dumbcode.todm.server.world.biome.BiomeProviderTODM;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeTODM extends WorldType {

    public WorldTypeTODM() {
        super("todm");
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new ChunkGeneratorTODM(world);
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderTODM(world);
    }

    @Override
    public boolean hasInfoNotice() {
        return true;
    }

    @Override
    public boolean isCustomizable() {
        return false;
    }
}
