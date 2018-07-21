package net.dumbcode.todm.server.event;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

public class ServerEventHandler
{

    @SubscribeEvent
    public static void createRegisteries(RegistryEvent.NewRegistry event)
    {
        EntityHandler.ANIMAL_REGISTRY = new RegistryBuilder<Animal>()
                .setType(Animal.class)
                .setName(new ResourceLocation(TODM.MODID, "animal"))
                .setDefaultKey(new ResourceLocation(TODM.MODID, ""))
                .set(((key, isNetwork) -> Animal.MISSING))
                .add((owner, stage, id, obj, oldObj) -> System.out.println(obj))
                .create();
    }
}
