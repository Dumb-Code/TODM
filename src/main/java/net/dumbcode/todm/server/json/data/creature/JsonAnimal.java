package net.dumbcode.todm.server.json.data.creature;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.dumbcode.dumblibrary.client.animation.objects.EntityAnimator;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.json.data.creature.attributes.*;

public class JsonAnimal extends Animal
{

    public JsonAnimal(JsonAnimalProperties properties)
    {
        JsonSpawnEgg eggFemale = properties.getSpawnEggFemale();
        JsonSpawnEgg eggMale = properties.getSpawnEggMale();
        JsonAnimalTraits traits = properties.getTraits();
        JsonDiet diet = properties.getDiet();
        JsonGrowthProperties growth = properties.getGrowthProperties();

        //Properties
        this.setName(properties.getName());
        this.setMarineAnimal(properties.isMarineAnimal());
        this.setEntityClass(properties.getEntityClass());
        //Egg
        this.setPrimaryEggColorFemale(eggFemale.getPrimary());
        this.setSecondaryEggColorFemale(eggFemale.getSecondary());
        this.setPrimaryEggColorMale(eggMale.getPrimary());
        this.setSecondaryEggColorMale(eggMale.getSecondary());
        //Traits
        this.setMaximumAge(traits.getMaxAge());
        this.setSpawnChance(traits.getSpawnChance());
        this.setSpawnBiomes(traits.getBiomes());
        this.setBabyHealth(traits.getHealth().getBaby());
        this.setAdultHealth(traits.getHealth().getAdult());
        //Forgot these, will do later.
        this.setAdultSpeed(0);
        this.setBabySpeed(0);
        //
        this.setAdultStrength(traits.getStrength().getAdult());
        this.setBabyStrength(traits.getStrength().getBaby());
        //Diet
        this.setDiet(diet.getDiet());
        this.setFullAmount(diet.getFull());
        this.setHungryAmount(diet.getHungry());
        this.setStarvingAmount(diet.getStarving());
        this.setHydratedAmount(diet.getHydrated());
        this.setThirstyAmount(diet.getThirsty());
        this.setDehydratedAmount(diet.getDehydrated());
        //Growth
        this.setAdultEyeHeight(growth.getEyeHeight().getAdult());
        this.setBabyEyeHeight(growth.getEyeHeight().getBaby());
        this.setAdultSizeX(growth.getSizeX().getAdult());
        this.setBabySizeX(growth.getSizeX().getBaby());
        this.setAdultSizeY(growth.getSizeY().getAdult());
        this.setBabySizeY(growth.getSizeY().getBaby());
        this.setAdultScale(growth.getScale().getAdult());
        this.setBabyScale(growth.getScale().getBaby());
        //TODO: Model Properties
        this.getModelProperties().setModelGrowthStages(growth.getGrowthStages());
        this.getModelProperties().setEntityAnimatorSupplier(EntityAnimator::new);
        this.getModelProperties().setMainModelMap(Maps.newEnumMap(GrowthStage.class));
        this.getModelProperties().setOverlays(Lists.newArrayList(growth.getOverlays()));
    }
}
