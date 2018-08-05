package net.dumbcode.todm.server.entities.ai.base;

import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.server.utils.MathUtils;
import net.minecraft.entity.EntityLiving;

@Getter
@Setter
public abstract class AdvancedAIBase
{

    private EntityLiving entity;
    /**
     * Type of AI (Animation, Movement, etc...)
     */
    private AIType type;
    /**
     * Default weight assigned to the type of AI (Can be changed)
     */
    private double weight;
    /**
     * The threshold before the AI is activated
     */
    private double threshold = .5f;
    /**
     * The overall importance of the task, decided by the programmer
     */
    private double importance;
    /**
     * Should this task be updated, currently once a second
     */
    private boolean isUpdatable = false;
    /**
     * Is the tasks finished (Removes the task from the currentTask list in the AIManager
     */
    private boolean isFinished = false;
    /**
     * Can this task be ran with other tasks? For example: Animations
     */
    private boolean isConcurrent = false;

    public AdvancedAIBase(EntityLiving entity)
    {
        this.entity = entity;
        this.type = AIType.MOVEMENT;
        this.weight = type.getWeight();
    }

    public AdvancedAIBase(EntityLiving entity, AIType type)
    {
        this(entity);
        this.type = type;
    }

    /**
     * Should the task be executed? By default it just sees if the importance
     * is greater than the threshold. It uses a sigmoid function to clamp and map the
     * value between 0 and 1 on an s curve. For example, an importance of 0 would equal a 0.5 using sigmoid.
     * @return true if the task should execute
     */
    public boolean shouldExecute()
    {
        return MathUtils.sigmoid(importance) > threshold;
    }

    /**
     * If the task is updatable, it will be updated every second (currently)
     */
    public void update()
    {
    }

    /**
     * Executes the task
     * Ex: Move the entity
     */
    public abstract void execute();

    /**
     * Should the task continue executing?
     * @return true if the task should continue
     */
    public abstract boolean shouldContinue();
}