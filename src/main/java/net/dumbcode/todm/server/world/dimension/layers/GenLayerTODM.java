package net.dumbcode.todm.server.world.dimension.layers;

import net.minecraft.world.gen.layer.GenLayer;

/**
 * http://jsfiddle.net/eddyb/NFa3P/
 * TODO:
 */
public class GenLayerTODM extends GenLayer
{

    public GenLayerTODM(long seed)
    {
        super(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        return new int[0];
    }
}
