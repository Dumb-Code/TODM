package net.dumbcode.todm.server.event;

import lombok.Getter;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.registries.IForgeRegistry;

public class RegisterAnimalEvent extends Event
{

    @Getter
    private final IForgeRegistry<Animal> registry;

    public RegisterAnimalEvent(IForgeRegistry<Animal> registry)
    {
        this.registry = registry;
    }
}
