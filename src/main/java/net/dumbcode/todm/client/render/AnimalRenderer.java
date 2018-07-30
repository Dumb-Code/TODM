package net.dumbcode.todm.client.render;

import net.dumbcode.dumblibrary.client.animation.AnimatableRenderer;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.todm.client.render.animals.Overlay;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

import java.util.Locale;
import java.util.function.Function;

public class AnimalRenderer extends AnimatableRenderer<AnimalEntity>
{

    private Animal animal;

    public AnimalRenderer(RenderManager renderManagerIn, Animal animal, Function<AnimalEntity, ModelContainer> modelContainerGetter, Function<AnimalEntity, ResourceLocation> textureGetter)
    {
        super(renderManagerIn, modelContainerGetter, textureGetter);
        this.animal = animal;
        this.addOverlays(animal);
    }

    private void addOverlays(Animal animal)
    {
        animal.getModelProperties().getOverlays().stream().forEach(overlayType ->
                this.addLayer(new Overlay<>(this, entity ->
                {
                    ResourceLocation regname = animal.getRegName();
                    return new ResourceLocation(regname.getResourceDomain(), "textures/entities/" + regname.getResourcePath()
                            + "/" + (entity.isMale() ? "male" : "female") + "_" + entity.getGrowthStage().name().toLowerCase(Locale.ROOT)
                            + "_" + overlayType.name().toLowerCase(Locale.ROOT) + ".png");
                })));
    }
}
