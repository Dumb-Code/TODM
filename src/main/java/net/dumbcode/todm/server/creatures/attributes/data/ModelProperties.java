package net.dumbcode.todm.server.creatures.attributes.data;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.dumblibrary.client.animation.objects.EntityAnimator;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.client.render.animals.Overlay;

import java.util.List;
import java.util.Map;

/**
 * Made for future Json capabilities
 */
@Getter
@Setter
public class ModelProperties
{
    private List<GrowthStage> modelGrowthStages = Lists.newArrayList(GrowthStage.ADULT);
    private Map<GrowthStage, String> mainModelMap = Maps.newEnumMap(GrowthStage.class);
    private ModelContainer.AnimatorFactory entityAnimatorSupplier = EntityAnimator::new;
    private Map<Boolean, Overlay> overlays = Maps.newHashMap();
}
