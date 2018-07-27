package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.gson.*;
import lombok.Value;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

@Value
public class JsonAdultBabyValue
{
    float baby;
    float adult;

    public static class JsonHandler implements JsonDeserializer<JsonAdultBabyValue>
    {

        @Override
        public JsonAdultBabyValue deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            if (!element.isJsonObject())
            {
                throw new JsonParseException("Expected Json Object, found " + JsonUtils.toString(element));
            }
            JsonObject json = element.getAsJsonObject();
            return new JsonAdultBabyValue(Math.round(JsonUtils.getFloat(json, "baby") * 100F) / 100F, Math.round(JsonUtils.getFloat(json, "adult") * 100F) / 100F);
        }
    }
}
