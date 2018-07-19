package net.dumbcode.todm.client.render.animal.objects;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.*;
import lombok.Value;
import net.dumbcode.todm.client.render.animal.AnimalAnimations;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

@Value
public class AnimalAnimationInfomation
{
    Map<AnimalAnimations, List<PoseObject>> poses;
    int version;

    public enum Deserializer implements JsonDeserializer<AnimalAnimationInfomation>
    {
        INSTANCE;

        @Override
        public AnimalAnimationInfomation deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject json = element.getAsJsonObject();
            JsonObject poses = JsonUtils.getJsonObject(json, "poses");
            Map<AnimalAnimations, List<PoseObject>> map = Maps.newHashMap();
            for (AnimalAnimations animation : AnimalAnimations.values())
            {
                String animationName = animation.name();//.toLowerCase(Locale.ROOT);
                if (JsonUtils.hasField(poses, animationName))
                {
                    for (JsonElement pose : JsonUtils.getJsonArray(poses, animationName))
                    {
                        map.computeIfAbsent(animation, a -> Lists.newArrayList()).add(context.deserialize(pose, PoseObject.class));
                    }
                }
            }
            return new AnimalAnimationInfomation(
                    map,
                    JsonUtils.isNumber(json.get("version")) ? JsonUtils.getInt(json, "version") : 0
            );
        }
    }
}