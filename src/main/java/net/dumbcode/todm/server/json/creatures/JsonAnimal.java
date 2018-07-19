package net.dumbcode.todm.server.json.creatures;

import com.google.gson.*;
import net.dumbcode.todm.server.creatures.Animal;

import java.lang.reflect.Type;

public class JsonAnimal extends Animal
{

    public static class JsonHandler implements JsonSerializer<JsonAnimal>, JsonDeserializer<JsonAnimal>
    {

        @Override
        public JsonAnimal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            return null;
        }

        @Override
        public JsonElement serialize(JsonAnimal animal, Type typeOfSrc, JsonSerializationContext context)
        {
            return null;
        }
    }
}
