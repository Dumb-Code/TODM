package net.dumbcode.todm.server.json;

import com.google.gson.*;
import net.dumbcode.dumblibrary.server.json.JsonUtil;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.Animal;
import net.dumbcode.todm.server.event.RegisterAnimalEvent;
import net.dumbcode.todm.server.json.data.creature.JsonAnimal;
import net.dumbcode.todm.server.json.data.creature.attributes.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Type;

@Mod.EventBusSubscriber(modid = TODM.MODID)
public class TODMJsonHandler
{
    private static final GsonBuilder BUILDER = new GsonBuilder()
            .registerTypeAdapter(Animal.class, new JsonHandler())
            .registerTypeAdapter(JsonAnimalProperties.class, new JsonAnimalProperties.JsonHandler())
            .registerTypeAdapter(JsonSpawnEgg.class, new JsonSpawnEgg.JsonHandler())
            .registerTypeAdapter(JsonAnimalTraits.class, new JsonAnimalTraits.JsonHandler())
            .registerTypeAdapter(JsonAdultBabyValue.class, new JsonAdultBabyValue.JsonHandler())
            .registerTypeAdapter(JsonDiet.class, new JsonDiet.JsonHandler())
            .registerTypeAdapter(JsonGrowthProperties.class, new JsonGrowthProperties.JsonHandler());
    private static final Gson GSON = BUILDER.create();

    @SubscribeEvent
    public static void onAnimalRegistry(RegisterAnimalEvent event)
    {
        JsonUtil.registerModJsons(event.getRegistry(), GSON, TODM.MODID, "animals");
        JsonUtil.registerLocalJsons(event.getRegistry(), GSON, TODM.MODID, "animals");
    }

    public static class JsonHandler implements JsonDeserializer<Animal>
    {

        @Override
        public Animal deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            return new JsonAnimal(context.deserialize(json, JsonAnimalProperties.class));
        }
    }
}
