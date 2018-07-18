package com.dumbcode.todm.client;

import com.dumbcode.todm.client.render.RenderHandler;
import com.dumbcode.todm.server.ServerProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ClientProxy extends ServerProxy
{

    public void preInit(FMLPreInitializationEvent event)
    {
        super.preInit(event);
        MinecraftForge.EVENT_BUS.register(RenderHandler.INSTANCE);
    }

    public void init(FMLInitializationEvent event)
    {
        super.init(event);
    }

    public void postInit(FMLPostInitializationEvent event)
    {
        super.postInit(event);
    }
}
