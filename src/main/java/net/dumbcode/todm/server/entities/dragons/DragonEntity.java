package net.dumbcode.todm.server.entities.dragons;

import io.netty.buffer.ByteBuf;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.creatures.animal.dragon.Dragon;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;

@Getter
@Setter
public class DragonEntity extends AnimalEntity implements IEntityAdditionalSpawnData
{

    private Dragon dragon;

    public DragonEntity(World worldIn, Dragon dragon)
    {
        super(worldIn);
        this.dragon = dragon;
    }

    public DragonEntity(World worldIn)
    {
        //TODO: Set a default dragon
        this(worldIn, null);
        this.enablePersistence();
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
