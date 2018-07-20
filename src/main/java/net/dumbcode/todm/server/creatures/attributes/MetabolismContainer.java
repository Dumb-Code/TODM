package net.dumbcode.todm.server.creatures.attributes;

import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;

@Getter
@Setter
public class MetabolismContainer
{

    private int currentMetabolism;
    private int maxMetabolism;
    private int decreaseRate;
    private boolean starving;
    private boolean hungry;
    private boolean full;
    private Animal animal;

    public MetabolismContainer(Animal animal)
    {
        this.animal = animal;
        this.maxMetabolism = animal.getFullAmount();
        this.currentMetabolism = maxMetabolism;
        this.full = true;
    }

    public void update(AnimalEntity entity)
    {
        if (entity.ticksExisted % 20 == 0)
        {
            getAnimalFoodLevel();
            if ((currentMetabolism = -decreaseRate) > 0)
            {
                currentMetabolism = -decreaseRate;
            } else
            {
                currentMetabolism = 0;
            }
        }
    }

    public void getAnimalFoodLevel()
    {
        if (currentMetabolism == animal.getFullAmount())
        {
            setFull(true);
        } else if (currentMetabolism <= animal.getHungryAmount())
        {
            setHungry(true);
        } else if (currentMetabolism <= animal.getStarvingAmount())
        {
            setStarving(true);
        } else
        {
            setStarving(false);
            setHungry(false);
            setFull(false);
        }
    }

    public boolean doesAnimalNeedFood()
    {
        return !isStarving() && !isHungry();
    }

    public void eat(int calories)
    {
        if ((currentMetabolism += calories) >= animal.getFullAmount())
        {
            currentMetabolism = animal.getFullAmount();
            return;
        }
        currentMetabolism += calories;
    }
}
