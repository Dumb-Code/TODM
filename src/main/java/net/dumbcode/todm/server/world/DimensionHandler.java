package net.dumbcode.todm.server.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldType;
import net.minecraftforge.common.DimensionManager;

import javax.annotation.Nullable;

public enum DimensionHandler {
    INSTANCE;

    public final String DIMENSION_NAME = "todm";
    public final int DIMENSION_ID = getID();
    public final DimensionType DIMENSION_TYPE = DimensionType.register(DIMENSION_NAME, "_" + DIMENSION_NAME, DIMENSION_ID, WorldProviderTODM.class, true);
    public final WorldType WORLD_TYPE = new WorldTypeTODM();

    public void init()
    {
        DimensionManager.registerDimension(DIMENSION_ID, DIMENSION_TYPE);
    }

    @Nullable
    private Integer getID()
    {
        for (int i=2; i<Integer.MAX_VALUE; i++)
        {
            if (!DimensionManager.isDimensionRegistered(i))
            {
                return i;
            }
        }
        return null;
    }
}
