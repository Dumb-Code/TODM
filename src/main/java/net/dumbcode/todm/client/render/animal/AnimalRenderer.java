package net.dumbcode.todm.client.render.animal;

import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class AnimalRenderer extends RenderLiving<AnimalEntity>
{

    public AnimalRenderer(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn)
    {
        super(rendermanagerIn, modelbaseIn, shadowsizeIn);
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(AnimalEntity entity)
    {
        return null;
    }
}
