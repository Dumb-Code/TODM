package net.dumbcode.todm.client.render.animals;

import net.dumbcode.dumblibrary.client.animation.AnimationInfo;

public enum AnimalAnimations implements AnimationInfo
{
    WALK;


    @Override
    public boolean shouldHold()
    {
        return false;
    }

    @Override
    public boolean useInertia()
    {
        return false;
    }
}
