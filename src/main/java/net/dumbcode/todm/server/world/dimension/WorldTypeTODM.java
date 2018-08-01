package net.dumbcode.todm.server.world.dimension;

import net.dumbcode.todm.server.world.dimension.biome.BiomeProviderTODM;
import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldTypeTODM extends WorldType {

    public WorldTypeTODM() {
        super("TODM");
    }

    @Override
    public IChunkGenerator getChunkGenerator(World world, String generatorOptions) {
        return new ChunkGeneratorTODM(world);
    }

    @Override
    public BiomeProvider getBiomeProvider(World world) {
        return new BiomeProviderTODM(world.getWorldInfo());
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
