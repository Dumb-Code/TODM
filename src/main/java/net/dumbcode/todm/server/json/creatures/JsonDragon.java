package net.dumbcode.todm.server.json.creatures;

import com.google.gson.*;
import net.dumbcode.todm.server.creatures.dragon.Dragon;

import java.lang.reflect.Type;

public class JsonDragon extends Dragon
{

    public static class JsonHandler implements JsonSerializer<JsonDragon>, JsonDeserializer<JsonDragon>
    {

        @Override
        public JsonDragon deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            return null;
        }

        @Override
        public JsonElement serialize(JsonDragon dragon, Type typeOfSrc, JsonSerializationContext context)
        {
            return null;
        }
    }
}
