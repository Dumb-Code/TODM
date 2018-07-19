package net.dumbcode.todm.server.items;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.dumbcode.todm.server.utils.Utils;
import net.minecraft.item.Item;

public class BasicItem extends Item
{

    public BasicItem(String name)
    {
        this.setCreativeTab(TabHandler.ITEMS);
        String formattedName = Utils.formatString(name);
        this.setUnlocalizedName(formattedName);
        this.setRegistryName(TODM.MODID, formattedName);
    }
}
