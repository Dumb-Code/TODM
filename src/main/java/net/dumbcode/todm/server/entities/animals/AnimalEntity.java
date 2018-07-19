package net.dumbcode.todm.server.entities.animals;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityCreature;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

public class AnimalEntity extends EntityCreature implements IEntityAdditionalSpawnData
{
    public AnimalEntity(World worldIn)
    {
        super(worldIn);
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
