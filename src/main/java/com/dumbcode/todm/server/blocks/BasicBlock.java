package com.dumbcode.todm.server.blocks;

import com.dumbcode.todm.TODM;
import com.dumbcode.todm.server.tabs.TabHandler;
import com.dumbcode.todm.server.utils.Utils;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BasicBlock extends Block
{

    protected String name;
    protected String formattedName;

    public BasicBlock(Material material, String name)
    {
        super(material);
        this.name = name;
        this.setCreativeTab(TabHandler.BLOCKS);
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
