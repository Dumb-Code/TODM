package net.dumbcode.todm.client;

import net.dumbcode.todm.server.ServerProxy;
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
