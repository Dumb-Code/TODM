package net.dumbcode.todm.server.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtils
{

    public double sigmoid(double x)
    {
        return 1 / (1 + Math.exp(-x));
    }
}
