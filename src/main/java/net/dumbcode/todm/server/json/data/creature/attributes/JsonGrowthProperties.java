package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.common.collect.Lists;
import com.google.gson.*;
import lombok.Value;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.List;

@Value
public class JsonGrowthProperties
{

    List<GrowthStage> growthStages;
    JsonAdultBabyValue scale;
    JsonAdultBabyValue sizeX;
    JsonAdultBabyValue sizeY;
    JsonAdultBabyValue eyeHeight;

    public static class JsonHandler implements JsonDeserializer<JsonGrowthProperties>
    {

        @Override
        public JsonGrowthProperties deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject json = element.getAsJsonObject();
            JsonObject sizeJson = json.getAsJsonObject("size");
            JsonArray growthArray = json.getAsJsonArray("growth_stages");
            List<GrowthStage> growthStages = Lists.newArrayList();
            for (JsonElement jsonElement : growthArray)
            {
                for (GrowthStage stage : GrowthStage.values())
                {
                    if (stage.name().equalsIgnoreCase(JsonUtils.getString(jsonElement, "growth_stages")))
                    {
                        growthStages.add(stage);
                        break;
                    }
                }
            }
            return new JsonGrowthProperties(
                    growthStages,
                    context.deserialize(JsonUtils.getJsonObject(sizeJson, "scale"), JsonAdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(sizeJson, "size_x"), JsonAdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(sizeJson, "size_y"), JsonAdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(sizeJson, "eye_height"), JsonAdultBabyValue.class)
            );
        }
    }
}
