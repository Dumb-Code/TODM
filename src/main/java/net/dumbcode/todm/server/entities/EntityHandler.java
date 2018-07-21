package net.dumbcode.todm.server.entities;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.creatures.animal.TestAnimal;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = TODM.MODID)
@GameRegistry.ObjectHolder(TODM.MODID)
public class EntityHandler
{


    public static IForgeRegistry<Animal> ANIMAL_REGISTRY;

    //Test Entity
    @SubscribeEvent
    public static void registerEntities(RegistryEvent.Register<EntityEntry> event)
    {
        event.getRegistry().register(EntityEntryBuilder.create()
                .entity(AnimalEntity.class)
                .factory(s -> new AnimalEntity(s.getMinecraftServer().getEntityWorld(), new TestAnimal("Test")))
                .name(TODM.MODID + ".animal")
                .tracker(64, 1, true)
                .id(new ResourceLocation(TODM.MODID, "animal"), 0)
                .build());
    }
}
