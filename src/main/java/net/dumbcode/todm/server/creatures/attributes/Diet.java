package net.dumbcode.todm.server.creatures.attributes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.List;

public enum Diet
{
    CARNIVOR(FoodHelper.getItemsForDiet(FoodHelper.FoodType.MEAT), FoodHelper.getBlocksForDiet(FoodHelper.FoodType.MEAT)),
    HERBIVOR(FoodHelper.getItemsForDiet(FoodHelper.FoodType.PLANT), FoodHelper.getBlocksForDiet(FoodHelper.FoodType.PLANT)),
    PISCIVORE(FoodHelper.getItemsForDiet(FoodHelper.FoodType.FISH), FoodHelper.getBlocksForDiet(FoodHelper.FoodType.PLANT)),
    CUSTOM();

    private List<Item> items;
    private List<Block> blocks;

    Diet()
    {

    }

    Diet(List<Item> items, List<Block> blocks)
    {
        this.items = items;
        this.blocks = blocks;
    }

    public List<Item> getItems()
    {
        return items;
    }

    public List<Block> getBlocks()
    {
        return blocks;
    }
}
