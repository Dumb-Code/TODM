package com.dumbcode.todm.server.blocks;

import com.google.common.collect.Lists;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.List;

public class BlockHandler
{

    public static final BasicBlock TEST = new BasicBlock(Material.ANVIL, "Test Block");


    private static List<Block> blocks = Lists.newArrayList();

    public static void init()
    {
        blocks.add(TEST);
    }

    private static void registerTileEntities()
    {
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
        event.getRegistry().registerAll(blocks.toArray(new Block[blocks.size()]));
        registerTileEntities();
    }

    @SubscribeEvent
    public static void registerItemBlocks(RegistryEvent.Register<Item> event)
    {
        for (Block block : blocks)
        {
            event.getRegistry().register(new ItemBlock(block).setRegistryName(block.getRegistryName()));
        }
    }

    public static List<Block> getBlocks()
    {
        return blocks;
    }
}
