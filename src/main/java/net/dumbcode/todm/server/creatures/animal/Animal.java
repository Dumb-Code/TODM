package net.dumbcode.todm.server.creatures.animal;

import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.dumbcode.todm.server.creatures.attributes.data.ModelProperties;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

@Setter
@Getter
public class Animal extends IForgeRegistryEntry.Impl<Animal>
{

    public static Animal MISSING = null;
    private Class<? extends AnimalEntity> entityClass;
    private String name;
    private boolean isMarineAnimal;
    private int primaryEggColorMale, primaryEggColorFemale;
    private int secondaryEggColorMale, secondaryEggColorFemale;
    private int maximumAge;
    private int spawnChance;
    private int fullAmount, hydratedAmount;
    private int hungryAmount, thirstyAmount;
    private int starvingAmount, dehydratedAmount;
    private double babyHealth, adultHealth;
    private double babyStrength, adultStrength;
    private double babySpeed, adultSpeed;
    private double babySizeX, adultSizeX;
    private double babySizeY, adultSizeY;
    private double babyEyeHeight, adultEyeHeight;
    private double babyScale, adultScale;
    private Diet diet;
    private ModelContainer modelContainer;
    private BiomeDictionary.Type[] spawnBiomes;
    private ModelProperties modelProperties = new ModelProperties();

    @Nonnull
    public ResourceLocation getRegName()
    {
        if (this.getRegistryName() == null)
        {
            throw new RuntimeException("Null Registry Name Found");
        }
        return this.getRegistryName();
    }

    public AnimalEntity createEntity(World world)
    {
        return new AnimalEntity(world, this);
    }
}
