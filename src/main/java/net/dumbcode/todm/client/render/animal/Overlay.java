package net.dumbcode.todm.client.render.animal;

import net.dumbcode.dumblibrary.client.animation.AnimatableRenderer;
import net.dumbcode.dumblibrary.server.entity.EntityAnimatable;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class Overlay<T extends EntityLiving & EntityAnimatable> implements LayerRenderer<T>
{

    private final Function<T, ResourceLocation> texture;
    private AnimatableRenderer<T> renderer;

    public Overlay(AnimatableRenderer<T> renderer, Function<T, ResourceLocation> texture)
    {
        this.texture = texture;
        this.renderer = renderer;
    }

    @Override
    public void doRenderLayer(T entity, float limbSwing, float limbSwingAmount, float partialTicks, float age, float yaw, float pitch, float scale)
    {
        if (!entity.isInvisible())
        {
            this.renderer.bindTexture(this.texture.apply(entity));
            this.renderer.getMainModel().render(entity, limbSwing, limbSwingAmount, age, yaw, pitch, scale);
            this.renderer.setLightmap(entity);
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return true;
    }
}
