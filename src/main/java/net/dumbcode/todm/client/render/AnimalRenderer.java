package net.dumbcode.todm.client.render;

import net.dumbcode.dumblibrary.client.animation.AnimatableRenderer;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class AnimalRenderer extends AnimatableRenderer<AnimalEntity>
{

    public AnimalRenderer(RenderManager renderManagerIn, Function<AnimalEntity, ModelContainer> modelContainerGetter, Function<AnimalEntity, ResourceLocation> textureGetter)
    {
        super(renderManagerIn, modelContainerGetter, textureGetter);
//        this.addLayer(new Overlay<>(this, entity ->
//                new ResourceLocation(entity.getAnimal().getRegName().getResourceDomain(), "textures/entities/" + entity.getAnimal().getRegName().getResourcePath() + entity.getGrowthStage().name().toLowerCase(Locale.ROOT) + ".png")));
    }
}
