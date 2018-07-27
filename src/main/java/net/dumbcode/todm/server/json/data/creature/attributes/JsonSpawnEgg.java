package net.dumbcode.todm.server.json.data.creature.attributes;

import com.google.gson.*;
import lombok.Value;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

@Value
public class JsonSpawnEgg
{

    int primary;
    int secondary;

    public static class JsonHandler implements JsonDeserializer<JsonSpawnEgg>
    {

        @Override
        public JsonSpawnEgg deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            if (!json.isJsonArray())
            {
                throw new JsonSyntaxException("Expected an array, found " + JsonUtils.toString(json));
            }
            JsonArray array = json.getAsJsonArray();
            if (array.size() != 2)
            {
                throw new JsonSyntaxException("Expected array size to be 2, found " + array.size());
            }
            return new JsonSpawnEgg(getInt(array.get(0)), getInt(array.get(1)));
        }

        private int getInt(JsonElement json) throws JsonParseException
        {
            if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isString())
            {
                String num = json.getAsString();
                if (num.startsWith("0x"))
                {
                    num = num.substring(2, num.length());
                }
                if (num.length() != 6)
                {
                    throw new JsonParseException("Expected a string length of 6, found " + num.length());
                }
                return Integer.parseInt(num, 16);
            }
            throw new JsonSyntaxException("Expected a string, found " + JsonUtils.toString(json));
        }
    }
}
