package net.dumbcode.todm.server.world;

import net.dumbcode.todm.server.config.TODMConfig;
import net.dumbcode.todm.server.world.structures.SettlementStructure;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class TODMWorldGenerator implements IWorldGenerator
{

    private final SettlementStructure settlementStructure = new SettlementStructure();

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {
        if (random.nextFloat() < .05F && TODMConfig.STRUCTURE_GENERATION.settlementGeneration)
        {
            settlementStructure.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider);
        }
    }
}
