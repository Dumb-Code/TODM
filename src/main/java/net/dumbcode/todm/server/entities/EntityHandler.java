package net.dumbcode.todm.server.entities;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber(modid = TODM.MODID)
@GameRegistry.ObjectHolder(TODM.MODID)
public class EntityHandler
{


    //Test Entity
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(AnimalEntity.class)
                .factory(AnimalEntity::new)
                .name(TODM.MODID + ".animal")
                .tracker(64, 1, true)
                .id(new ResourceLocation(TODM.MODID, "animal"), 0)
                .build());
    }
}
