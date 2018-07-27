package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.common.collect.Lists;
import com.google.gson.*;
import lombok.Value;
import net.minecraft.util.JsonUtils;
import net.minecraftforge.common.BiomeDictionary;

import java.lang.reflect.Type;
import java.util.List;

@Value
public class JsonAnimalTraits
{

    int maxAge;
    int spawnChance;
    BiomeDictionary.Type[] biomes;

    JsonAdultBabyValue strength;
    JsonAdultBabyValue health;

    public static class JsonHandler implements JsonDeserializer<JsonAnimalTraits>
    {

        @Override
        public JsonAnimalTraits deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject json = element.getAsJsonObject();
            JsonArray biomeArray = JsonUtils.getJsonArray(json, "biomes");
            List<BiomeDictionary.Type> biomes = Lists.newArrayList();
            for (JsonElement jsonElement : biomeArray)
            {
                for (BiomeDictionary.Type type : BiomeDictionary.Type.getAll())
                {
                    if (type.getName().equalsIgnoreCase(JsonUtils.getString(jsonElement, "biomes")))
                    {
                        biomes.add(type);
                        break;
                    }
                }
            }
            return new JsonAnimalTraits(
                    JsonUtils.getInt(json, "max_age"),
                    JsonUtils.getInt(json, "spawn_chance"),
                    biomes.toArray(new BiomeDictionary.Type[0]),
                    context.deserialize(JsonUtils.getJsonObject(json, "strength"), JsonAdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "health"), JsonAdultBabyValue.class)
            );
        }
    }
}
