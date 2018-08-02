package net.dumbcode.todm.server.world.dimension.structures;

import net.dumbcode.todm.TODM;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class SettlementStructure implements IWorldGenerator
{

    private static final ResourceLocation SETTLEMENT = new ResourceLocation(TODM.MODID, "/structures/settlement");

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider)
    {

        //Random in the air for now
        BlockPos basePos = new BlockPos(chunkX * 16 + random.nextInt(16), 100, chunkZ * 16 + random.nextInt(16));
        PlacementSettings settings = new PlacementSettings().setRotation(Rotation.NONE);
        Template template = world.getSaveHandler().getStructureTemplateManager().getTemplate(world.getMinecraftServer(), SETTLEMENT);
        template.addBlocksToWorld(world, basePos, settings);
    }
}
