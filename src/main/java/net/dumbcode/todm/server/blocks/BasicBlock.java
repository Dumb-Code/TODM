package net.dumbcode.todm.server.blocks;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.dumbcode.todm.server.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block
{

    public BasicBlock(Material material, String name)
    {
        super(material);
        this.setCreativeTab(TabHandler.ITEMS);
        String formattedName = Utils.formatString(name);
        this.setUnlocalizedName(formattedName);
        this.setRegistryName(TODM.MODID, formattedName);
    }
}