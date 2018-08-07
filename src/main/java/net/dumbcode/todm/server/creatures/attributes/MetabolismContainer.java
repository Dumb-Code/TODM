package net.dumbcode.todm.server.creatures.attributes;

import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.nbt.NBTTagCompound;

@Getter
@Setter
public class MetabolismContainer
{

    private int currentFood, currentWater;
    private int maxFood, maxWater;
    private int decreaseFoodRate, decreaseWaterRate;
    private boolean starving, dehydrated;
    private boolean hungry, thirsty;
    private boolean full, hydrated;
    private Animal animal;

    public MetabolismContainer(Animal animal)
    {
        this.animal = animal;
        this.maxFood = animal.getFullAmount();
        this.maxWater = animal.getHydratedAmount();
        this.currentFood = maxFood;
        this.currentWater = maxWater;
        this.full = true;
        this.hydrated = true;
    }

    public void update(AnimalEntity entity)
    {
        if (entity.ticksExisted % 20 == 0)
        {
            if ((currentFood -= decreaseFoodRate) > 0)
            {
                currentFood -= decreaseFoodRate;
            } else
            {
                currentFood = 0;
            }
            if ((currentWater -= decreaseWaterRate) > 0)
            {
                currentWater -= decreaseWaterRate;
            } else
            {
                currentWater = 0;
            }
            getAnimalFoodLevel();
            getAnimalThirstLevel();
        }
    }

    public void getAnimalFoodLevel()
    {
        if (currentFood == animal.getFullAmount())
        {
            setFull(true);
        } else if (currentFood <= animal.getHungryAmount())
        {
            setHungry(true);
        } else if (currentFood <= animal.getStarvingAmount())
        {
            setStarving(true);
        }
    }

    public void getAnimalThirstLevel()
    {
        if (currentWater == animal.getHydratedAmount())
        {
            setHydrated(true);
            setThirsty(false);
            setHydrated(false);
        } else if (currentWater <= animal.getThirstyAmount() && !isThirsty())
        {
            setThirsty(true);
            setHydrated(false);
            setDehydrated(false);
        } else if (currentWater <= animal.getDehydratedAmount() && !isDehydrated())
        {
            setDehydrated(true);
            setThirsty(false);
            setHydrated(false);
        }
    }

    public boolean doesAnimalNeedFood()
    {
        return isStarving() || isHungry();
    }

    public boolean doesAnimalNeedWater()
    {
        return isThirsty() || isDehydrated();
    }

    public void eat(int calories)
    {
        if ((currentFood += calories) >= animal.getFullAmount())
        {
            currentFood = animal.getFullAmount();
            return;
        }
        currentFood += calories;
    }

    public void drink(int water)
    {
        if ((currentWater += water) >= animal.getHydratedAmount())
        {
            currentWater = animal.getHydratedAmount();
            return;
        }
        currentFood += water;
    }

    public void readFromNBT(NBTTagCompound nbt)
    {
        this.currentWater = nbt.getInteger("currentWater");
        this.currentFood = nbt.getInteger("currentFood");
    }

    public void writeToNBT(NBTTagCompound nbt)
    {
        nbt.setInteger("currentWater", this.currentWater);
        nbt.setInteger("currentFood", this.currentFood);
    }
}
