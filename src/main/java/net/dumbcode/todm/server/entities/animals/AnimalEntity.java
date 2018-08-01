package net.dumbcode.todm.server.entities.animals;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.server.entity.EntityAnimatable;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.creatures.attributes.MetabolismContainer;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

@Getter
@Setter
public class AnimalEntity extends EntityCreature implements IEntityAdditionalSpawnData, EntityAnimatable
{

    private Animal animal = Animal.MISSING;
    private MetabolismContainer metabolism;
    private EntityAITasks tasks;
    private boolean isCarcass = false;
    private boolean isMale;
    private int age;
    private GrowthStage growthStage;

    public AnimalEntity(World world)
    {
        super(world);
        this.setAnimal();
        this.tasks = new EntityAITasks(world.profiler);
        this.metabolism = new MetabolismContainer(animal);
        this.initMetabolism();
        this.age = 0;
        this.growthStage = GrowthStage.INFANT;
        this.isMale = this.rand.nextFloat() > .5F;
    }

    public AnimalEntity(World world, Animal animal)
    {
        this(world);
        this.animal = animal;
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (!this.world.isRemote)
        {
            if (this.ticksExisted % 24000 == 0)
            {
                age++;
                growthStage = getAppropriateStage();
                if (age == animal.getMaximumAge())
                {
                    this.setDead();
                }
            }
            metabolism.update(this);
        }
    }

    public void initMetabolism()
    {
        metabolism.setDecreaseFoodRate(2);
        metabolism.setDecreaseWaterRate(3);
    }

    public GrowthStage getAppropriateStage()
    {
        if (animal.getMaximumAge() / age <= animal.getMaximumAge() / 4)
        {
            return GrowthStage.INFANT;
        } else if (animal.getMaximumAge() / age <= animal.getMaximumAge() / 3)
        {
            return GrowthStage.JUVENILE;
        } else if (animal.getMaximumAge() / age <= animal.getMaximumAge() / 2)
        {
            return GrowthStage.ADOLESCENT;
        } else
        {
            return GrowthStage.ADULT;
        }
    }

    private void setAnimal()
    {
        if (animal == Animal.MISSING)
        {
            this.animal = EntityHandler.ANIMAL_REGISTRY.getValue(new ResourceLocation(TODM.MODID, "missing"));
        }
    }

    @Override
    public GrowthStage getGrowthStage()
    {
        return growthStage;
    }

    @Override
    public int getAnimationTick()
    {
        return 0;
    }

    @Override
    public void setAnimationTick(int tick)
    {

    }

    @Override
    public Animation getAnimation()
    {
        return null;
    }

    @Override
    public void setAnimation(Animation animation)
    {

    }

    @Override
    public Animation[] getAnimations()
    {
        return new Animation[0];
    }

    @Override
    public void writeSpawnData(ByteBuf buffer)
    {
    }

    @Override
    public void readSpawnData(ByteBuf additionalData)
    {

    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        compound = super.writeToNBT(compound);
        compound.setInteger("age", this.age);
        compound.setBoolean("isCarcass", this.isCarcass);
        compound.setBoolean("isMale", this.isMale);
        compound.setString("growthStage", this.growthStage.name());
        this.getMetabolism().writeToNBT(compound);
        return compound;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.setAge(compound.getInteger("age"));
        this.setCarcass(compound.getBoolean("isCarcass"));
        this.setMale(compound.getBoolean("isMale"));
        this.setGrowthStage(GrowthStage.valueOf(compound.getString("growthStage")));
        this.getMetabolism().readFromNBT(compound);
    }
}
