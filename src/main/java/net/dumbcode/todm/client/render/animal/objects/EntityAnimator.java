package net.dumbcode.todm.client.render.animal.objects;

import net.dumbcode.todm.server.creatures.attributes.GrowthStage;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.ilexiconn.llibrary.client.model.tabula.ITabulaModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.TabulaModel;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.EnumMap;
import java.util.Map;
import java.util.WeakHashMap;

@SideOnly(Side.CLIENT)
public class EntityAnimator implements ITabulaModelAnimator<AnimalEntity>
{
    protected EnumMap<GrowthStage, Map<AnimalEntity, AnimationPassesWrapper<AnimalEntity>>> animationHandlers = new EnumMap<>(GrowthStage.class);

    private AnimationPassesWrapper<AnimalEntity> getAnimationHelper(AnimalEntity entity, TabulaModel model, boolean useInertialTweens)
    {
        GrowthStage growth = GrowthStage.ADULT;//TODO: link to entity
        return this.animationHandlers.computeIfAbsent(growth, g -> new WeakHashMap<>()).computeIfAbsent(entity, e -> e.getAnimal().getPoseHandler().createPass(e, model, growth, useInertialTweens));
    }

    @Override
    public final void setRotationAngles(TabulaModel model, AnimalEntity entity, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
        this.getAnimationHelper(entity, model, true).performAnimations(entity, limbSwing, limbSwingAmount, ticks);
        this.performAnimations(model, entity, limbSwing, limbSwingAmount, ticks, rotationYaw, rotationPitch, scale);
    }

    protected void performAnimations(TabulaModel parModel, AnimalEntity entity, float limbSwing, float limbSwingAmount, float ticks, float rotationYaw, float rotationPitch, float scale)
    {
    }
}