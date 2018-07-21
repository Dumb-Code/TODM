package net.dumbcode.todm.server.entities.animals;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.creatures.attributes.MetabolismContainer;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

@Getter
@Setter
public class AnimalEntity extends EntityCreature implements IEntityAdditionalSpawnData
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
        this.metabolism = new MetabolismContainer(animal);
        this.initMetabolism();
    }

    public AnimalEntity(World world)
    {
        super(world);
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
}
