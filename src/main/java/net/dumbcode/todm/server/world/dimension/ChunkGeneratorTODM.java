package net.dumbcode.todm.server.world.dimension;

import static net.minecraftforge.event.ForgeEventFactory.onReplaceBiomeBlocks;
import static net.minecraftforge.event.terraingen.InitMapGenEvent.EventType.CAVE;
import com.google.common.collect.Lists;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/*
 * TODO: Go through and rename variables (if I can) and play around with values
 * 99% of this is just minecraft's default
 */
public class ChunkGeneratorTODM implements IChunkGenerator {

    private List<Biome.SpawnListEntry> creatures = Lists.newArrayList();
    private Random random;
    private World world;
    private MapGenBase caveGenerator;
    private static final IBlockState STONE = Blocks.STONE.getDefaultState();
    private static final IBlockState OCEAN_BLOCK = Blocks.WATER.getDefaultState();
    private NoiseGeneratorOctaves minLimitPerlinNoise;
    private NoiseGeneratorOctaves maxLimitPerlinNoise;
    private NoiseGeneratorOctaves mainPerlinNoise;
    private NoiseGeneratorPerlin surfaceNoise;
    private NoiseGeneratorOctaves scaleNoise;
    private NoiseGeneratorOctaves depthNoise;
    private double[] depthBuffer = new double[256];
    private final double[] heightMap = new double[825];
    private double[] mainNoiseRegion;
    private double[] minLimitRegion;
    private double[] maxLimitRegion;
    private double[] depthRegion;
    private final float[] biomeWeights;
    private Biome[] biomes;

    //Settings
    private float coordinateScale = 684.412F;
    private int seaLevel = 63;
    private float heightScale = 684.412F;
    private float upperLimitScale = 512.0F;
    private float lowerLimitScale = 512.0F;
    private float depthNoiseScaleX = 200.0F;
    private float depthNoiseScaleZ = 200.0F;
    private float depthNoiseScaleExponent = 0.5F;
    private float mainNoiseScaleX = 80.0F;
    private float mainNoiseScaleY = 160.0F;
    private float mainNoiseScaleZ = 80.0F;
    private float baseSize = 8.5F;
    private float stretchY = 12.0F;
    private float biomeDepthWeight = 1.0F;
    private float biomeScaleWeight = 1.0F;
    public float biomeDepthOffSet = 0;
    public float biomeScaleOffset = 0;

    public ChunkGeneratorTODM(World world)
    {
        this.world = world;
        this.random = new Random(world.getSeed());
        this.caveGenerator = TerrainGen.getModdedMapGen(new MapGenCaves(), CAVE);
        this.minLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.maxLimitPerlinNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.mainPerlinNoise = new NoiseGeneratorOctaves(this.random, 8);
        this.surfaceNoise = new NoiseGeneratorPerlin(this.random, 4);
        this.scaleNoise = new NoiseGeneratorOctaves(this.random, 10);
        this.depthNoise = new NoiseGeneratorOctaves(this.random, 16);
        this.biomeWeights = new float[25];

        for (int i = -2; i <= 2; ++i)
        {
            for (int j = -2; j <= 2; ++j)
            {
                float f = 10.0F / MathHelper.sqrt((float)(i * i + j * j) + 0.2F);
                this.biomeWeights[i + 2 + (j + 2) * 5] = f;
            }
        }
    }

    @Override
    public Chunk generateChunk(int x, int z) {
        this.random.setSeed((long) x * 341873128712L + (long) z * 132897987541L);
        ChunkPrimer primer = new ChunkPrimer();
        this.setBlocksInChunk(x, z, primer);
        Biome[] biomes = this.world.getBiomeProvider().getBiomes(this.biomes, x * 16, z * 16, 16, 16);
        this.replaceBiomeBlocks(x, z, primer, biomes);
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
        //ChunkPos chunkPos = new ChunkPos(chunkX, chunkZ);
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

    private void generateHeightmap(int x, int y, int z)
    {
        this.depthRegion = this.depthNoise.generateNoiseOctaves(this.depthRegion, x, z, 5, 5, (double)this.depthNoiseScaleX, (double)this.depthNoiseScaleZ, (double)this.depthNoiseScaleExponent);
        float f = this.coordinateScale;
        float f1 = this.heightScale;
        this.mainNoiseRegion = this.mainPerlinNoise.generateNoiseOctaves(this.mainNoiseRegion, x, y, z, 5, 33, 5, (double)(f / this.mainNoiseScaleX), (double)(f1 / this.mainNoiseScaleY), (double)(f / this.mainNoiseScaleZ));
        this.minLimitRegion = this.minLimitPerlinNoise.generateNoiseOctaves(this.minLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        this.maxLimitRegion = this.maxLimitPerlinNoise.generateNoiseOctaves(this.maxLimitRegion, x, y, z, 5, 33, 5, (double)f, (double)f1, (double)f);
        int i = 0;
        int j = 0;

        for (int k = 0; k < 5; ++k)
        {
            for (int l = 0; l < 5; ++l)
            {
                float f2 = 0.0F;
                float f3 = 0.0F;
                float f4 = 0.0F;
                Biome biome = this.biomes[k + 2 + (l + 2) * 10];

                for (int j1 = -2; j1 <= 2; ++j1)
                {
                    for (int k1 = -2; k1 <= 2; ++k1)
                    {
                        Biome biome1 = this.biomes[k + j1 + 2 + (l + k1 + 2) * 10];
                        float f5 = this.biomeDepthOffSet + biome1.getBaseHeight() * this.biomeDepthWeight;
                        float f6 = this.biomeScaleOffset + biome1.getHeightVariation() * this.biomeScaleWeight;

                        float f7 = this.biomeWeights[j1 + 2 + (k1 + 2) * 5] / (f5 + 2.0F);

                        if (biome1.getBaseHeight() > biome.getBaseHeight())
                        {
                            f7 /= 2.0F;
                        }

                        f2 += f6 * f7;
                        f3 += f5 * f7;
                        f4 += f7;
                    }
                }

                f2 = f2 / f4;
                f3 = f3 / f4;
                f2 = f2 * 0.9F + 0.1F;
                f3 = (f3 * 4.0F - 1.0F) / 8.0F;
                double d7 = this.depthRegion[j] / 8000.0D;

                if (d7 < 0.0D)
                {
                    d7 = -d7 * 0.3D;
                }

                d7 = d7 * 3.0D - 2.0D;

                if (d7 < 0.0D)
                {
                    d7 = d7 / 2.0D;

                    if (d7 < -1.0D)
                    {
                        d7 = -1.0D;
                    }

                    d7 = d7 / 1.4D;
                    d7 = d7 / 2.0D;
                }
                else
                {
                    if (d7 > 1.0D)
                    {
                        d7 = 1.0D;
                    }

                    d7 = d7 / 8.0D;
                }

                ++j;
                double d8 = (double)f3;
                double d9 = (double)f2;
                d8 = d8 + d7 * 0.2D;
                d8 = d8 * (double)this.baseSize / 8.0D;
                double d0 = (double)this.baseSize + d8 * 4.0D;

                for (int l1 = 0; l1 < 33; ++l1)
                {
                    double d1 = ((double)l1 - d0) * (double)this.stretchY * 128.0D / 256.0D / d9;

                    if (d1 < 0.0D)
                    {
                        d1 *= 4.0D;
                    }

                    double d2 = this.minLimitRegion[i] / (double)this.lowerLimitScale;
                    double d3 = this.maxLimitRegion[i] / (double)this.upperLimitScale;
                    double d4 = (this.mainNoiseRegion[i] / 10.0D + 1.0D) / 2.0D;
                    double d5 = MathHelper.clampedLerp(d2, d3, d4) - d1;

                    if (l1 > 29)
                    {
                        double d6 = (double)((float)(l1 - 29) / 3.0F);
                        d5 = d5 * (1.0D - d6) + -10.0D * d6;
                    }

                    this.heightMap[i] = d5;
                    ++i;
                }
            }
        }
    }

    public void setBlocksInChunk(int x, int z, ChunkPrimer primer)
    {
        this.biomes = this.world.getBiomeProvider().getBiomesForGeneration(this.biomes, x * 4 - 2, z * 4 - 2, 10, 10);
        this.generateHeightmap(x * 4, 0, z * 4);

        for (int i = 0; i < 4; ++i)
        {
            int j = i * 5;
            int k = (i + 1) * 5;

            for (int l = 0; l < 4; ++l)
            {
                int i1 = (j + l) * 33;
                int j1 = (j + l + 1) * 33;
                int k1 = (k + l) * 33;
                int l1 = (k + l + 1) * 33;

                for (int i2 = 0; i2 < 32; ++i2)
                {
                    double d0 = 0.125D;
                    double d1 = this.heightMap[i1 + i2];
                    double d2 = this.heightMap[j1 + i2];
                    double d3 = this.heightMap[k1 + i2];
                    double d4 = this.heightMap[l1 + i2];
                    double d5 = (this.heightMap[i1 + i2 + 1] - d1) * 0.125D;
                    double d6 = (this.heightMap[j1 + i2 + 1] - d2) * 0.125D;
                    double d7 = (this.heightMap[k1 + i2 + 1] - d3) * 0.125D;
                    double d8 = (this.heightMap[l1 + i2 + 1] - d4) * 0.125D;

                    for (int j2 = 0; j2 < 8; ++j2)
                    {
                        double d9 = 0.25D;
                        double d10 = d1;
                        double d11 = d2;
                        double d12 = (d3 - d1) * 0.25D;
                        double d13 = (d4 - d2) * 0.25D;

                        for (int k2 = 0; k2 < 4; ++k2)
                        {
                            double d14 = 0.25D;
                            double d16 = (d11 - d10) * 0.25D;
                            double lvt_45_1_ = d10 - d16;

                            for (int l2 = 0; l2 < 4; ++l2)
                            {
                                if ((lvt_45_1_ += d16) > 0.0D)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, STONE);
                                }
                                else if (i2 * 8 + j2 < this.seaLevel)
                                {
                                    primer.setBlockState(i * 4 + k2, i2 * 8 + j2, l * 4 + l2, this.OCEAN_BLOCK);
                                }
                            }

                            d10 += d12;
                            d11 += d13;
                        }

                        d1 += d5;
                        d2 += d6;
                        d3 += d7;
                        d4 += d8;
                    }
                }
            }
        }
    }

    public void replaceBiomeBlocks(int x, int z, ChunkPrimer primer, Biome[] biomesIn)
    {
        if (!onReplaceBiomeBlocks(this, x, z, primer, this.world))
        {
            return;
        }
        this.depthBuffer = this.surfaceNoise.getRegion(this.depthBuffer, (double)(x * 16), (double)(z * 16), 16, 16, 0.0625D, 0.0625D, 1.0D);

        // Goes through each block on the surface and replaces it will the specific terrain blocks for that biome.
        for (int i = 0; i < 16; ++i)
        {
            for (int j = 0; j < 16; ++j)
            {
                Biome biome = biomesIn[j + i * 16];
                biome.genTerrainBlocks(this.world, this.random, primer, x * 16 + i, z * 16 + j, this.depthBuffer[j + i * 16]);
            }
        }
    }
}
