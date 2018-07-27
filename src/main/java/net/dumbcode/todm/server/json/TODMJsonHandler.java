package net.dumbcode.todm.server.json;

import com.google.gson.*;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.creatures.animal.Animal;
import net.dumbcode.todm.server.entities.EntityHandler;
import net.dumbcode.todm.server.json.data.creature.JsonAnimal;
import net.dumbcode.todm.server.json.data.creature.attributes.*;
import net.minecraftforge.event.RegistryEvent;
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
    public static void onAnimalRegistry(RegistryEvent.Register<Animal> event)
    {
        event.getRegistry().register(EntityHandler.test);
        JsonUtil.getAllRegister(event.getRegistry(), GSON, "animals");
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
