package net.dumbcode.todm.server.event;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.Animal;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.registries.RegistryBuilder;

import static net.dumbcode.todm.server.entities.EntityHandler.ANIMAL_REGISTRY;

@Mod.EventBusSubscriber(modid = TODM.MODID)
public class ServerEventHandler
{

    @SubscribeEvent
    public static void createRegisteries(RegistryEvent.NewRegistry event)
    {
        ANIMAL_REGISTRY = new RegistryBuilder<Animal>()
                .setType(Animal.class)
                .setName(new ResourceLocation(TODM.MODID, "creature"))
                .setDefaultKey(new ResourceLocation(TODM.MODID, "test"))
                .set(((key, isNetwork) -> Animal.MISSING))
                .create();
        MinecraftForge.EVENT_BUS.post(new RegisterAnimalEvent(ANIMAL_REGISTRY));
    }
}
