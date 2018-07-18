package com.dumbcode.todm.server.items;

import com.dumbcode.todm.TODM;
import com.dumbcode.todm.server.tabs.TabHandler;
import com.dumbcode.todm.server.utils.Utils;
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
