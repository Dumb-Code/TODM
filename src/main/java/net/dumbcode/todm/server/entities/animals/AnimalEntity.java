package net.dumbcode.todm.server.entities.animals;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.server.entity.EntityAnimatable;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.creatures.attributes.MetabolismContainer;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.ilexiconn.llibrary.server.animation.Animation;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

@Getter
@Setter
public class AnimalEntity extends EntityCreature implements IEntityAdditionalSpawnData, EntityAnimatable
{

    private Animal animal;
    private MetabolismContainer metabolism;
    private EntityAITasks tasks;
    private boolean isCarcass = false;
    private boolean isMale;

    public AnimalEntity(World world)
    {
        super(world);
        this.animal = EntityHandler.test;
        this.tasks = new EntityAITasks(world.profiler);
        this.metabolism = new MetabolismContainer(animal);
        this.initMetabolism();
    }

    @Override
    public void onUpdate()
    {
        super.onUpdate();
        if (!this.world.isRemote)
        {
            metabolism.update(this);
        }
    }

    public void initMetabolism()
    {
        metabolism.setDecreaseRate(2);
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
    public GrowthStage getGrowthStage()
    {
        return GrowthStage.ADULT;
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
