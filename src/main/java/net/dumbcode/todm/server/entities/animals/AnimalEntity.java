package net.dumbcode.todm.server.entities.animals;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import net.dumbcode.todm.server.api.EntityAnimatable;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.creatures.attributes.MetabolismContainer;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

@Data
public class AnimalEntity extends EntityCreature implements IEntityAdditionalSpawnData, EntityAnimatable
{

    private Animal animal;
    private MetabolismContainer metabolism;
    private EntityAITasks tasks;
    private boolean isCarcass = false;

    public AnimalEntity(World world, Animal animal)
    {
        super(world);
        this.animal = animal;
        this.tasks = new EntityAITasks(world.profiler);
        this.metabolism = new MetabolismContainer();
    }

    public AnimalEntity(World world)
    {
        super(world);
        this.animal = animal;
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
    public boolean isRunning()
    {
        return false;
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
}
