package net.dumbcode.todm.server.entities.ai;

import net.dumbcode.todm.server.entities.ai.base.AIType;
import net.dumbcode.todm.server.entities.ai.base.AdvancedAIBase;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.math.Vec3d;

/**
 * Just a test AI
 */
public class WanderAI extends AdvancedAIBase
{

    private AnimalEntity entity;
    private double x;
    private double y;
    private double z;
    private double speed;


    public WanderAI(EntityLiving entity, double speed)
    {
        super(entity);
        this.entity = (AnimalEntity) entity;
        this.setType(AIType.MOVEMENT);
        this.setUpdatable(false);
        this.speed = speed;
    }

    @Override
    public boolean shouldExecute()
    {
        if (this.entity.getTaskManager().getCurrentTasks().isEmpty())
        {
            Vec3d target = RandomPositionGenerator.findRandomTarget(this.entity, 6, 2);
            x = target.x;
            y = target.y;
            z = target.z;
            return true;
        }
        return super.shouldExecute();
    }

    @Override
    public void execute()
    {
        this.entity.getNavigator().tryMoveToXYZ(this.x, this.y, this.z, this.speed);
        this.setFinished(true);
    }

    @Override
    public boolean shouldContinue()
    {
        return false;
    }
}
