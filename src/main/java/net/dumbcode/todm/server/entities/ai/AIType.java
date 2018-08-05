package net.dumbcode.todm.server.entities.ai;

import lombok.Getter;

@Getter
public enum AIType
{
    METABOLISM(.3), MOVEMENT(.1), ANIMATION(0);

    private final double weight;

    AIType(double weight)
    {
        this.weight = weight;
    }
}
