package net.dumbcode.todm.server.world.dimension;

import net.dumbcode.todm.server.world.dimension.biome.BiomeProviderTODM;
import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.BiomeProvider;

public class WorldProviderTODM extends WorldProvider {

    @Override
    public DimensionType getDimensionType() {
        return DimensionHandler.INSTANCE.DIMENSION_TYPE;
    }

    @Override
    public BiomeProvider getBiomeProvider() {
        return new BiomeProviderTODM(this.world.getWorldInfo());
    }
}
