package net.dumbcode.todm.server.creatures.attributes;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.creatures.Animal;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public enum FoodHelper
{
    INSTANCE;

    private final Map<Item, FoodNutrient> food = Maps.newHashMap();
    private final Map<Block, FoodNutrient> foodBlocks = Maps.newHashMap();

    public void registerItem(Item item, FoodType type, int calories)
    {
        food.put(item, new FoodNutrient(type, calories));
    }

    public void registerBlock(Block block, FoodType type, int calories)
    {
        foodBlocks.put(block, new FoodNutrient(type, calories));
    }

    @SuppressWarnings("unchecked")
    public List<Item> getItemsForDiet(FoodHelper.FoodType type)
    {
        return (List) FoodHelper.INSTANCE.getFood().entrySet().stream()
                .filter(s -> s.getKey().equals(type))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    public List<Block> getBlocksForDiet(FoodHelper.FoodType type)
    {
        return (List) FoodHelper.INSTANCE.getFoodBlocks().entrySet().stream()
                .filter(s -> s.getKey().equals(type))
                .collect(Collectors.toList());
    }

    public void registerFoodItems()
    {
        // Plants
        registerItem(Items.CARROT, FoodType.PLANT, 100);
        registerItem(Items.SUGAR, FoodType.PLANT, 10);
        registerItem(Items.MELON, FoodType.PLANT, 150);
        registerItem(Items.WHEAT, FoodType.PLANT, 120);
        registerItem(Items.APPLE, FoodType.PLANT, 160);
        registerItem(Items.GOLDEN_APPLE, FoodType.PLANT, 1200);
        registerItem(Items.POTATO, FoodType.PLANT, 160);
        registerItem(Items.BREAD, FoodType.PLANT, 130);
        // Meat
        registerItem(Items.CHICKEN, FoodType.MEAT, 250);
        registerItem(Items.COOKED_CHICKEN, FoodType.MEAT, 350);
        registerItem(Items.PORKCHOP, FoodType.MEAT, 300);
        registerItem(Items.COOKED_PORKCHOP, FoodType.MEAT, 500);
        registerItem(Items.BEEF, FoodType.MEAT, 300);
        registerItem(Items.COOKED_BEEF, FoodType.MEAT, 500);
        registerItem(Items.EGG, FoodType.MEAT, 200);
        registerItem(Items.MUTTON, FoodType.MEAT, 400);
        registerItem(Items.COOKED_MUTTON, FoodType.MEAT, 600);
        // Fish
        registerItem(Items.FISH, FoodType.FISH, 200);
        registerItem(Items.COOKED_FISH, FoodType.FISH, 300);
    }

    public void registerFoodBlocks()
    {
        registerBlock(Blocks.RED_FLOWER, FoodType.PLANT, 100);
    }

    public boolean isEdible(Item item, Animal animal)
    {
        return animal.getDiet().getItems().contains(item);
    }

    public boolean isEdible(Block block, Animal animal)
    {
        return animal.getDiet().getBlocks().contains(block);
    }

    public FoodNutrient getNutrientsFromFood(Item item)
    {
        return food.get(item);
    }

    public Map<Item, FoodNutrient> getFood()
    {
        return food;
    }

    public Map<Block, FoodNutrient> getFoodBlocks()
    {
        return foodBlocks;
    }

    public enum FoodType
    {
        MEAT, FISH, PLANT, INSECT
    }

    @Getter
    @Setter
    public static class FoodNutrient
    {

        private int calories;
        private FoodType type;

        public FoodNutrient(FoodType type, int calories)
        {
            this.calories = calories;
            this.type = type;
        }
    }
}
