package net.dumbcode.todm.server.items;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.dumbcode.todm.server.utils.Utils;
import net.minecraft.item.Item;

public class BasicItem extends Item
{

    protected String name;
    protected String formattedName;

    public BasicItem(String name)
    {
        this.name = name;
        this.setCreativeTab(TabHandler.ITEMS);
        formattedName = Utils.formatString(name);
        this.setUnlocalizedName(formattedName);
        this.setRegistryName(TODM.MODID, formattedName);
    }

    public String getName()
    {
        return name;
    }

    public String getFormattedName()
    {
        return formattedName;
    }
}
