package net.dumbcode.todm.server.tabs;

import net.dumbcode.todm.server.items.ItemHandler;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class TabHandler {

    public static final CreativeTabs ITEMS = new CreativeTabs("todm.items") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.ARROW);
        }
    };

    public static final CreativeTabs BLOCKS = new CreativeTabs("todm.blocks") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(Items.ARROW);
        }
    };

    public static final CreativeTabs WEAPONS = new CreativeTabs("todm.weapons")
    {
        @Override
        public ItemStack getTabIconItem()
        {
            return new ItemStack(ItemHandler.CROSSBOW);
        }
    };
}