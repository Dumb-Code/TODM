package net.dumbcode.todm.server.creatures.animal;

import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;

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
        this.setMaximumAge(5);
        this.setSpawnChance(4);
        this.setSpawnBiomes(new Biome[]{Biomes.DESERT});
        this.setScaleAdult(.4f);
        this.setScaleInfant(.1f);
        this.setGrowthStage(GrowthStage.ADULT);
        this.setDiet(Diet.HERBIVOR);
        this.setRegistryName(TODM.MODID, "test");
    }
}
