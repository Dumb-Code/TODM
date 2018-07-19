package net.dumbcode.todm.server.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.json.creatures.JsonAnimal;
import net.dumbcode.todm.server.json.creatures.JsonDragon;
import net.minecraftforge.fml.common.Mod;

/**
 * Decided to wait for @Wyn's rewrite
 */
@Mod.EventBusSubscriber(modid = TODM.MODID)
public class TODMJsonHandler
{
    private static final GsonBuilder BUILDER = new GsonBuilder()
            .registerTypeAdapter(JsonAnimal.class, new JsonAnimal.JsonHandler())
            .registerTypeAdapter(JsonDragon.class, new JsonDragon.JsonHandler());
    private static final Gson GSON = BUILDER.create();

//    @SubscribeEvent
//    public static void onAnimalRegistry(RegistryEvent.Register<Animal> event)
//    {
//        JsonUtil.getAllRegister(event.getRegistry(), GSON, "animals");
//    }
//
//    @SubscribeEvent
//    public static void onDragonRegistry(RegistryEvent.Register<Dragon> event)
//    {
//        JsonUtil.getAllRegister(event.getRegistry(), GSON, "animals/dragons");
//    }
}
