package net.dumbcode.todm.server.creatures.attributes.data;

import com.google.common.collect.Maps;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.dumblibrary.client.animation.ModelContainer;
import net.dumbcode.dumblibrary.client.animation.objects.EntityAnimator;
import net.dumbcode.dumblibrary.server.entity.GrowthStage;
import net.dumbcode.todm.server.creatures.attributes.OverlayType;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ModelProperties
{
    private List<GrowthStage> modelGrowthStages;
    private Map<GrowthStage, String> mainModelMap = Maps.newEnumMap(GrowthStage.class);
    private ModelContainer.AnimatorFactory entityAnimatorSupplier = EntityAnimator::new;
    private List<OverlayType> overlays;
}
