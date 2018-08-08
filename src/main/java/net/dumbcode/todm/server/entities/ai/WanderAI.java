package net.dumbcode.todm.server.entities.ai;

import net.dumbcode.todm.server.entities.ai.base.AIType;
import net.dumbcode.todm.server.entities.ai.base.AdvancedAIBase;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.math.Vec3d;

public class WanderAI extends AdvancedAIBase
{

    private AnimalEntity entity;
    private double x;
    private double y;
    private double z;
    private double speed;
    private PathNavigate navigator;


    public WanderAI(EntityLiving entity)
    {
        super(entity);
        this.entity = (AnimalEntity) entity;
        this.setType(AIType.MOVEMENT);
        this.setUpdatable(false);
        this.speed = this.entity.getMovementSpeed();
        this.navigator = this.entity.getNavigator();
    }

    @Override
    public boolean shouldExecute()
    {
        return this.entity.getTaskManager().getCurrentTasks().isEmpty() && this.navigator.noPath();
    }

    //TODO: Sudo random is generating same length targets too often.
    @Override
    public void execute()
    {
        Vec3d target = RandomPositionGenerator.findRandomTarget(this.entity, 10, 2);
        this.entity.getNavigator().tryMoveToXYZ(target.x, target.y, target.z, this.speed);
    }

    @Override
    public boolean shouldContinue()
    {
        if (this.navigator.noPath())
        {
            this.setFinished(true);
            return false;
        }
        return true;
    }

    @Override
    public void checkImportance()
    {
        this.setImportance(0);
    }
}
