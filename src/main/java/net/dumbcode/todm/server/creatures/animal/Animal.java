package net.dumbcode.todm.server.creatures.animal;

import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.dumbcode.todm.server.creatures.attributes.data.ModelProperties;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistryEntry;

import javax.annotation.Nonnull;

@Setter
@Getter
public abstract class Animal extends IForgeRegistryEntry.Impl<Animal>
{

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
    private float babySizeX, adultSizeX;
    private float babySizeY, adultSizeY;
    private float babyEyeHeight, adultEyeHeight;
    private float scaleInfant;
    private float scaleAdult;
    private Diet diet;
    private GrowthStage growthStage;
    private ModelContainer modelContainer;
    private Biome[] spawnBiomes;
    private ModelProperties modelProperties = new ModelProperties();
    public static Animal MISSING = null;

    @Nonnull
    public ResourceLocation getRegName()
    {
        if (this.getRegistryName() == null)
        {
            throw new RuntimeException("Null Registry Name Found");
        }
        return this.getRegistryName();
    }
}
