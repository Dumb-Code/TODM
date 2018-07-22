package net.dumbcode.todm.server.config;

import net.dumbcode.todm.TODM;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Config(modid = TODM.MODID, category = "")
@Mod.EventBusSubscriber
public class TODMConfig
{

    @Config.Name("Structure Generation")
    public static final StructureGeneration STRUCTURE_GENERATION = new StructureGeneration();

    public static class StructureGeneration {
        @Config.Name("Settlement Generation")
        public boolean settlementGeneration = true;
    }

    @SubscribeEvent
    public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if(TODM.MODID.equals(event.getModID()))
        {
            ConfigManager.sync(TODM.MODID, Config.Type.INSTANCE);
        }
    }
}
