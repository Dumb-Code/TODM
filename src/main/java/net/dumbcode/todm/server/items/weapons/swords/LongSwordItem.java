package net.dumbcode.todm.server.items.weapons.swords;

import net.dumbcode.todm.server.items.weapons.WeaponType;
import net.dumbcode.todm.server.tabs.TabHandler;

public class LongSwordItem extends BasicSwordItem
{

    public LongSwordItem(String name, ToolMaterial material, WeaponType type)
    {
        super(name, material, 4f, type, -3);
        this.setCreativeTab(TabHandler.WEAPONS);
    }
}
