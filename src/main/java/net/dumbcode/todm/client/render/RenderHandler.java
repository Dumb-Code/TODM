package net.dumbcode.todm.client.render;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.blocks.BlockHandler;
import net.dumbcode.todm.server.items.ItemHandler;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod.EventBusSubscriber
@SideOnly(Side.CLIENT)
public class RenderHandler
{

    private static void init()
    {
        registerItemRenderer(ItemHandler.CROSSBOW, "crossbow");
        registerItemRenderer(Item.getItemFromBlock(BlockHandler.DEAD_GRASS), "dead_grass");
    }

    private static void registerItemRenderer(Item item, String id)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(TODM.MODID + ":" + id, "inventory"));
    }

    @SubscribeEvent
    public static void onModelEvent(ModelRegistryEvent event)
    {
        init();
    }
}
