package net.dumbcode.todm.server;

import net.dumbcode.todm.server.blocks.BlockHandler;
import net.dumbcode.todm.server.creatures.attributes.FoodHelper;
import net.dumbcode.todm.server.items.ItemHandler;
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
        //GameRegistry.registerWorldGenerator(new TODMWorldGenerator(), 0);
    }

    public void init(FMLInitializationEvent event)
    {
    }

    public void postInit(FMLPostInitializationEvent event)
    {
    }
}
