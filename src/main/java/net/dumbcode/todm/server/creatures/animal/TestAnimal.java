package net.dumbcode.todm.server.creatures.animal;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;

public class TestAnimal extends Animal
{

    public TestAnimal(String name)
    {
        this.setName(name);
        this.setMarineAnimal(false);
        this.setEntityClass(AnimalEntity.class);
        this.setFullAmount(2000);
        this.setHungryAmount(1300);
        this.setStarvingAmount(500);
        this.setMaximumAge(100);
        this.setSpawnChance(4);
        this.setDiet(Diet.HERBIVOR);
        this.setRegistryName(TODM.MODID, "test");
    }
}
