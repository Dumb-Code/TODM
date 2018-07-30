package net.dumbcode.todm.server.items.weapons.bows;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.dumbcode.todm.server.utils.StringUtils;
import net.minecraft.item.ItemBow;

public class CrossbowItem extends ItemBow
{

    public CrossbowItem(String name)
    {
        super();
        this.setCreativeTab(TabHandler.WEAPONS);
        String formattedName = StringUtils.formatString(name);
        this.setUnlocalizedName(formattedName);
        this.setRegistryName(TODM.MODID, formattedName);
    }
}
