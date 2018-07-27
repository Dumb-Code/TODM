package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.gson.*;
import lombok.Value;
import net.dumbcode.todm.server.entities.animals.AnimalEntity;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

@Value
public class JsonAnimalProperties
{

    String name;
    boolean isMarineAnimal;
    Class<? extends AnimalEntity> entityClass;

    JsonSpawnEgg spawnEggMale;
    JsonSpawnEgg spawnEggFemale;
    JsonAnimalTraits traits;
    JsonDiet diet;
    JsonGrowthProperties growthProperties;

    public static class JsonHandler implements JsonDeserializer<JsonAnimalProperties>
    {

        @Override
        public JsonAnimalProperties deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject json = element.getAsJsonObject();
            JsonObject spawnEgg = JsonUtils.getJsonObject(json, "spawn_egg");

            Class entityClass = null;
            try
            {
                entityClass = Class.forName(JsonUtils.getString(json, "entity_class"));
                if (!AnimalEntity.class.isAssignableFrom(entityClass))
                {
                    throw new JsonParseException("Entity class, " + entityClass + " is not a child class of Entity");
                }
            } catch (ClassNotFoundException e)
            {
                throw new JsonParseException("Could not find the class " + entityClass);
            }
            return new JsonAnimalProperties(
                    JsonUtils.getString(json, "name"),
                    JsonUtils.getBoolean(json, "marine_animal"),
                    entityClass,
                    context.deserialize(JsonUtils.getJsonArray(spawnEgg, "male"), JsonSpawnEgg.class),
                    context.deserialize(JsonUtils.getJsonArray(spawnEgg, "female"), JsonSpawnEgg.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "traits"), JsonAnimalTraits.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "diet"), JsonDiet.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "properties"), JsonGrowthProperties.class)
            );
        }
    }
}
