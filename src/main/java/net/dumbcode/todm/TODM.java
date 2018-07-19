package net.dumbcode.todm;

import net.dumbcode.todm.server.ServerProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = TODM.MODID, name = TODM.NAME, version = TODM.VERSION, dependencies = "required-after:llibrary@[" + TODM.LLIB_VERSION + ",);required-after:forge@[14.23.4.2705,)")
public class TODM
{

    public static final String MODID = "todm";
    public static final String NAME = "That One Dragon Mod";
    public static final String VERSION = "0.0.1";

    public static final String LLIB_VERSION = "1.7.9";

    @Mod.Instance
    public static TODM instance;

    @SidedProxy(serverSide = "net.dumbcode.todm.server.ServerProxy", clientSide = "net.dumbcode.todm.client.ClientProxy")
    public static ServerProxy proxy;

    public static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        logger = event.getModLog();
        proxy.preInit(event);
        System.out.println(NAME + " is Loading!");
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
        proxy.postInit(event);
    }
}