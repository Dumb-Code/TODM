package net.dumbcode.todm.server.world.layers;

import net.minecraft.world.gen.layer.*;

/**
 * Derived a lot of what I learned from Betweenlands and Twilight Forest,
 * so special thanks to them.
 * Checkout this website, if you need help understanding what each genlayer does.
 * @URL http://jsfiddle.net/eddyb/NFa3P/
 */
public abstract class GenLayerTODM extends GenLayer
{

    public GenLayerTODM(long seed)
    {
        super(seed);
    }

    public static GenLayer[] initializeAllBiomeGenerators(long seed)
    {
        GenLayer genLayer = new GenLayerIsland(1L);
        genLayer = new GenLayerFuzzyZoom(2000L, genLayer);
        genLayer = new GenLayerBiomes(200L, genLayer);
        //Just zoom in a certain amount of times (In this case twice)
        genLayer = GenLayerZoom.magnify(1000L, genLayer, 1);
        genLayer = new GenLayerSmooth(1000L, genLayer);
        GenLayer indexLayer = new GenLayerRiver(1L, genLayer);
        indexLayer = new GenLayerSmooth(1000L, indexLayer);
        indexLayer = new GenLayerRiverMix(100L, genLayer, indexLayer);
        indexLayer = new GenLayerVoronoiZoom(10L, indexLayer);
        indexLayer.initWorldGenSeed(seed);
        genLayer.initWorldGenSeed(seed);

        return new GenLayer[]{genLayer, indexLayer, genLayer};
    }
}
