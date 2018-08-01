package net.dumbcode.todm.server;

import net.dumbcode.todm.server.blocks.BlockHandler;
import net.dumbcode.todm.server.creatures.attributes.FoodHelper;
import net.dumbcode.todm.server.items.ItemHandler;
import net.dumbcode.todm.server.world.dimension.DimensionHandler;
import net.dumbcode.todm.server.world.dimension.biome.BiomeHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy
{

    public void preInit(FMLPreInitializationEvent event)
    {
        ItemHandler.init();
        BlockHandler.init();
        FoodHelper.INSTANCE.registerFoodBlocks();
        FoodHelper.INSTANCE.registerFoodItems();
        DimensionHandler.INSTANCE.init();
    }

    public void init(FMLInitializationEvent event)
    {
        BiomeHandler.initBiomeManagerAndDictionary();
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
