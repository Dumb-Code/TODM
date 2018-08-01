package net.dumbcode.todm.server.world.dimension;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class ChunkGeneratorTODM implements IChunkGenerator {

    private List<Biome.SpawnListEntry> creatures = Lists.newArrayList();
    private Random random = new Random();
    private World world;

    public ChunkGeneratorTODM(World world)
    {
        this.world = world;
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        this.random.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer primer = new ChunkPrimer();
        Biome[] biomes = this.world.getBiomeProvider().getBiomes(null, x * 16, z * 16, 16, 16);
        Chunk chunk = new Chunk(this.world, primer, x, z);
        for(int i = 0; i < chunk.getBiomeArray().length; i++)
        {
            chunk.getBiomeArray()[i] = (byte) Biome.getIdForBiome(biomes[i]);
        }
        chunk.generateSkylightMap();
        return chunk;
    }

    @Override
    public void populate(int chunkX, int chunkZ) {
        BlockFalling.fallInstantly = true;
        int x = chunkX * 16;
        int z = chunkZ * 16;

        BlockPos bottom = new BlockPos(x, 0, z);
        Biome biome = this.world.getBiome(bottom.add(16, 0, 16));
        this.random.setSeed(this.world.getSeed());
        long l0 = this.random.nextLong() / 2L * 2L + 1L;
        long l1 = this.random.nextLong() / 2L * 2L + 1L;
        this.random.setSeed((long) chunkX * l0 + (long) chunkZ * l1 ^ this.world.getSeed());
        ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
        MinecraftForge.EVENT_BUS.post(new PopulateChunkEvent.Pre(this, world, random, chunkX, chunkZ, false));
        biome.decorate(this.world, this.random, new BlockPos(x, 0, z));
        BlockFalling.fallInstantly = false;
    }

    @Override
    public boolean generateStructures(Chunk chunkIn, int x, int z) {
        return false;
    }

    @Override
    public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
        return creatures;
    }

    @Nullable
    @Override
    public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position, boolean findUnexplored) {
        return null;
    }

    @Override
    public void recreateStructures(Chunk chunkIn, int x, int z) {

    }

    @Override
    public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
        return false;
    }
}
