package net.dumbcode.todm.client.render.animal.objects;

import net.dumbcode.todm.client.render.animal.AnimalAnimations;
import net.dumbcode.todm.client.render.animal.PoseHandler;
import net.dumbcode.todm.server.api.EntityAnimatable;
import net.ilexiconn.llibrary.server.animation.Animation;

import java.util.List;
import java.util.Map;

public class MovementAnimationPass<T extends EntityAnimatable> extends AnimationPass<T>
{

    public MovementAnimationPass(Map<Animation, List<PoseHandler.ModelData>> animations, Map<String, Map<String, CubeReference>> poses, boolean useInertia)
    {
        super(animations, poses, useInertia);
    }

    @Override
    protected boolean isEntityAnimationDependent()
    {
        return false;
    }

    @Override
    protected float getAnimationSpeed(T entity)
    {
        return entity.isMoving() ? this.getAnimationDegree(entity) : 3.0F;
    }

    @Override
    protected float getAnimationDegree(T entity)
    {
        float degree;
        if (this.animation == AnimalAnimations.WALKING.get() || this.animation == AnimalAnimations.RUNNING.get() || this.animation == AnimalAnimations.SWIMMING.get() || this.animation == AnimalAnimations.CLIMBING.get())
        {
            if (entity.inWater() || entity.inLava())
            {
                degree = this.limbSwingAmount * 4.0F;
            } else
            {
                degree = this.limbSwingAmount;
            }
        } else
        {
            return super.getAnimationDegree(entity);
        }

        return Math.max(entity.isMoving() ? 0.5F : 0.0F, Math.min(1.0F, degree));
    }

    @Override
    protected Animation getRequestedAnimation(T entity)
    {
        if (entity.isClimbing())
        {
            return AnimalAnimations.CLIMBING.get();
        } else if (entity.isMoving())
        {
            if (entity.isSwimming())
            {
                return this.animations.containsKey(AnimalAnimations.SWIMMING.get()) ? AnimalAnimations.SWIMMING.get() : AnimalAnimations.WALKING.get();
            } else
            {
                if (entity.isRunning())
                {
                    return AnimalAnimations.RUNNING.get();
                } else
                {
                    return AnimalAnimations.WALKING.get();
                }
            }
        } else
        {
            return AnimalAnimations.IDLE.get();
        }
    }

    @Override
    public boolean isLooping()
    {
        return true;
    }
}