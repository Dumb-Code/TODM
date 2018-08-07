package net.dumbcode.todm.server.entities.ai.metabolism;

import net.dumbcode.todm.server.creatures.attributes.MetabolismContainer;
import net.dumbcode.todm.server.entities.ai.base.AIType;
import net.dumbcode.todm.server.entities.ai.base.AdvancedAIBase;
import net.dumbcode.todm.server.entities.ai.utils.AIUtils;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public class DrinkingAI extends AdvancedAIBase
{

    private AnimalEntity entity;
    private BlockPos pos;

    public DrinkingAI(EntityLiving entity, int cooldown)
    {
        super(entity, AIType.METABOLISM, cooldown);
        this.entity = (AnimalEntity) entity;
        this.setThreshold(.6);
    }

    @Override
    public boolean shouldExecute()
    {
        if (super.shouldExecute())
        {
            List<BlockPos> pos = AIUtils.traverXZ((int) entity.posX, (int) entity.posY - 1, (int) entity.posZ, 10);
            for (BlockPos bPos : pos)
            {
                if (entity.world.getBlockState(bPos).getMaterial() == Material.WATER)
                {
                    this.pos = bPos;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public void execute()
    {
        super.execute();
        entity.getNavigator().tryMoveToXYZ(pos.getX() + 1, pos.getY(), pos.getZ(), entity.getMovementSpeed());
        entity.getMetabolism().drink(3000);
    }

    @Override
    public boolean shouldContinue()
    {
        if (entity.getNavigator().noPath())
        {
            this.setFinished(true);
            return false;
        }
        return true;
    }

    @Override
    public void checkImportance()
    {
        MetabolismContainer meta = entity.getMetabolism();
        if (meta.isDehydrated())
        {
            this.setImportance(.6 * this.getWeight());
        } else if (meta.isThirsty())
        {
            this.setImportance(.4 * this.getWeight());
        } else
        {
            this.setImportance(0);
        }
    }

}
