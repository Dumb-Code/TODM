package net.dumbcode.todm.server.creatures;

import com.google.common.collect.Maps;
import lombok.Data;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.dumbcode.todm.server.creatures.attributes.GrowthStage;
import net.dumbcode.todm.server.creatures.attributes.Overlay;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.registries.IForgeRegistryEntry;

import java.util.Map;

@Data
public abstract class Animal extends IForgeRegistryEntry.Impl<Animal>
{

    private Map<Overlay, Map<GrowthStage, ResourceLocation>> overlays = Maps.newHashMap();

    private Class<? extends AnimalEntity> entityClass;

    private String name;
    private boolean isMale;
    private boolean isMarineAnimal;
    private int primaryEggColorMale, primaryEggColorFemale;
    private int secondaryEggColorMale, secondaryEggColorFemale;
    private int maximumAge;
    private int spawnChance;
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

    private Biome[] spawnBiomes;
}
