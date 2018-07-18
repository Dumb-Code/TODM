package com.dumbcode.todm.server;

import com.dumbcode.todm.server.blocks.BlockHandler;
import com.dumbcode.todm.server.entities.EntityHandler;
import com.dumbcode.todm.server.items.ItemHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy
{

    public void preInit(FMLPreInitializationEvent event)
    {
        ItemHandler.init();
        BlockHandler.init();
        EntityHandler.init();
    }

    public void init(FMLInitializationEvent event)
    {

    }

    public void postInit(FMLPostInitializationEvent event)
    {

    }
}
