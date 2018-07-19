package net.dumbcode.todm.server.items;

import com.google.common.collect.Lists;
import net.dumbcode.todm.server.items.weapons.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemHandler
{

    public static final CrossbowItem CROSSBOW = new CrossbowItem("Crossbow");

    public static List<Item> items = Lists.newArrayList();

    public static void init()
    {
        items.add(CROSSBOW);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
        event.getRegistry().registerAll(items.toArray(new Item[items.size()]));
    }

    public static List<Item> getItems()
    {
        return items;
    }
}
