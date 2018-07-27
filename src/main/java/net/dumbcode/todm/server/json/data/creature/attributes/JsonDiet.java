package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.gson.*;
import lombok.Value;
import net.dumbcode.todm.server.creatures.attributes.Diet;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

@Value
public class JsonDiet
{

    Diet diet;
    int full, hungry, starving;
    int hydrated, thirsty, dehydrated;

    public static class JsonHandler implements JsonDeserializer<JsonDiet>
    {

        @Override
        public JsonDiet deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject json = element.getAsJsonObject();
            JsonArray foodAmount = json.getAsJsonArray("food_amount");
            JsonArray hydratedAmount = json.getAsJsonArray("hydrated_amount");
            if (foodAmount.size() > 3 || hydratedAmount.size() > 3)
            {
                throw new JsonParseException("Found an array larger than 3 items in the diet");
            }
            return new JsonDiet(
                    Diet.values()[JsonUtils.getInt(json, "diet")],
                    foodAmount.get(0).getAsInt(),
                    foodAmount.get(1).getAsInt(),
                    foodAmount.get(2).getAsInt(),
                    hydratedAmount.get(0).getAsInt(),
                    hydratedAmount.get(1).getAsInt(),
                    hydratedAmount.get(2).getAsInt()
            );
        }
    }
}
