package net.dumbcode.todm.client.render;

import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.dumblibrary.client.animation.objects.AnimationPass;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.client.render.animal.AnimalAnimations;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.dumbcode.todm.server.items.ItemHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.Locale;

@Mod.EventBusSubscriber(modid = TODM.MODID)
@SideOnly(Side.CLIENT)
public class RenderHandler
{

    private static void init()
    {
        registerItemRenderer(ItemHandler.CROSSBOW, "crossbow");
        registerItemRenderer(ItemHandler.CRYSTALLITE_SHARD, "crystallite_shard");
        registerItemRenderer(ItemHandler.LONGSWORD_DIAMOND, "longsword_diamond.ttm");
        registerItemRenderer(ItemHandler.LONGSWORD_GOLD, "longsword_gold.ttm");
        registerItemRenderer(ItemHandler.LONGSWORD_IRON, "longsword_iron.ttm");
        registerItemRenderer(ItemHandler.LONGSWORD_SCORCHING, "longsword_scorching.ttm");
    }

    private static void registerItemRenderer(Item item, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(TODM.MODID + ":" + id, "gui"));
    }

    @SubscribeEvent
    public static void onModelEvent(ModelRegistryEvent event)
    {
        for (Animal animal : EntityHandler.ANIMAL_REGISTRY.getValuesCollection())
        {
            animal.setModelContainer(new ModelContainer(animal.getRegName(),
                    animal.getModelProperties().getModelGrowthStages(), animal.getModelProperties().getMainModelMap(),
                    AnimalAnimations.getNames(), animal.getModelProperties().getEntityAnimatorSupplier(),
                    AnimalAnimations::fromName, AnimalAnimations.IDLE.get(),
                    AnimalAnimations::getAnimation, AnimationPass::new));
        }
        init();
        registerEntityHandler();
    }

    private static void registerEntityHandler()
    {
        for (Animal animal : EntityHandler.ANIMAL_REGISTRY.getValuesCollection())
        {
            RenderingRegistry.registerEntityRenderingHandler(animal.getEntityClass(), manager -> new AnimalRenderer(manager, animal, entity -> animal.getModelContainer(),
                    entity ->
                    {
                        ResourceLocation regname = animal.getRegName();
                        return new ResourceLocation(regname.getResourceDomain(), "textures/entities/" + regname.getResourcePath() + "/" + (entity.isMale() ? "male" : "female") + "_" + entity.getGrowthStage().name().toLowerCase(Locale.ROOT) + ".png");
                    }));
        }
    }
}
