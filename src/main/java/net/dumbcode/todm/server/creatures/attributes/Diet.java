package net.dumbcode.todm.server.creatures.attributes;

import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.List;

public enum Diet
{
    CARNIVORE(FoodHelper.FoodType.MEAT),
    HERBIVORE(FoodHelper.FoodType.PLANT),
    PISCIVORE(FoodHelper.FoodType.FISH),
    CUSTOM();

    private List<Item> items;
    private List<Block> blocks;
    private FoodHelper helper = FoodHelper.INSTANCE;

    Diet()
    {

    }

    Diet(FoodHelper.FoodType foodType)
    {
        items = helper.getItemsForDiet(foodType);
        blocks = helper.getBlocksForDiet(foodType);
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
