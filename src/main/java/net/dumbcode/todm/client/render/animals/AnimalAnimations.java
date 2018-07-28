package net.dumbcode.todm.client.render.animals;

import com.google.common.collect.Lists;
import net.dumbcode.dumblibrary.client.animation.AnimationInfo;
import net.ilexiconn.llibrary.server.animation.Animation;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

public enum AnimalAnimations implements AnimationInfo
{

    IDLE(false, false, false),
    ATTACKING(false, false, true),
    SLEEPING(true, false, true);

    private Animation animation;
    private boolean hold;
    private boolean doesBlockMovement;
    private boolean useInertia;

    AnimalAnimations(boolean hold, boolean blockMovement, boolean useInertia)
    {
        this.hold = hold;
        this.doesBlockMovement = blockMovement;
        this.useInertia = useInertia;
    }

    public static Collection<String> getNames()
    {
        List<String> list = Lists.newArrayList();
        for (AnimalAnimations animation : values())
        {
            list.add(animation.name().toLowerCase(Locale.ROOT));
        }
        return list;
    }

    public static AnimalAnimations getAnimation(Animation animation)
    {
        for (AnimalAnimations animations : values())
        {
            if (animation.equals(animations.animation))
            {
                return animations;
            }
        }

        return AnimalAnimations.IDLE;
    }

    public static Animation fromName(String name)
    {
        AnimalAnimations animation = IDLE;
        for (AnimalAnimations animations : values())
        {
            if (animations.name().equalsIgnoreCase(name))
            {
                animation = animations;
            }
        }
        return animation.get();
    }

    public boolean shouldHold()
    {
        return this.hold;
    }

    public boolean doesBlockMovement()
    {
        return this.doesBlockMovement;
    }

    public boolean useInertia()
    {
        return this.useInertia;
    }

    public Animation get()
    {
        if (this.animation == null)
        {
            this.animation = Animation.create(-1);
        }
        return this.animation;
    }
}