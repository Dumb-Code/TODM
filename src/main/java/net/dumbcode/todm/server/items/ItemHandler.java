package net.dumbcode.todm.server.items;

import com.google.common.collect.Lists;
import net.dumbcode.todm.server.items.weapons.WeaponType;
import net.dumbcode.todm.server.items.weapons.bows.CrossbowItem;
import net.dumbcode.todm.server.items.weapons.swords.LongSwordItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

@Mod.EventBusSubscriber
public class ItemHandler
{

    public static final CrossbowItem CROSSBOW = new CrossbowItem("Crossbow");
    public static final LongSwordItem LONGSWORD_DIAMOND = new LongSwordItem("Longsword", Item.ToolMaterial.DIAMOND, WeaponType.DIAMOND);
    public static final LongSwordItem LONGSWORD_GOLD = new LongSwordItem("Longsword", Item.ToolMaterial.GOLD, WeaponType.GOLD);
    public static final LongSwordItem LONGSWORD_IRON = new LongSwordItem("Longsword", Item.ToolMaterial.IRON, WeaponType.IRON);
    public static final LongSwordItem LONGSWORD_SCORCHING = new LongSwordItem("Longsword", Item.ToolMaterial.IRON, WeaponType.SCORCHING);

    public static List<Item> items = Lists.newArrayList();

    public static void init()
    {
        items.add(CROSSBOW);
        items.add(LONGSWORD_DIAMOND);
        items.add(LONGSWORD_GOLD);
        items.add(LONGSWORD_IRON);
        items.add(LONGSWORD_SCORCHING);
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
