package net.dumbcode.todm.server.entities.ai;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.minecraft.entity.EntityLiving;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Getter
public class AdvancedAIManager
{

    private List<AdvancedAIBase> tasks = Lists.newLinkedList();
    // TODO: Maybe a better way that's not as performance taxing?
    private List<AdvancedAIBase> currentTasks = Lists.newCopyOnWriteArrayList();

    private final EntityLiving entity;

    public AdvancedAIManager(EntityLiving entity)
    {
        this.entity = entity;
    }

    /**
     * Updates and sorts the tasks every second to see if a new
     * current task can be added
     */
    public void update()
    {
        if (this.entity.ticksExisted % 20 == 0)
        {
            this.sortTasks();
            Iterator<AdvancedAIBase> taskIt = tasks.iterator();
            while (taskIt.hasNext())
            {
                AdvancedAIBase task = taskIt.next();
                if (task.shouldExecute() && currentTasks.isEmpty())
                {
                    currentTasks.add(task);
                    task.execute();
                } else if (task.shouldExecute() && !currentTasks.isEmpty())
                {
                    if (currentTasks.get(0).isConcurrent() && task.isConcurrent())
                    {
                        currentTasks.add(task);
                        task.execute();
                    }
                }
            }
            if (!currentTasks.isEmpty())
            {
                Iterator<AdvancedAIBase> it = currentTasks.iterator();
                while (it.hasNext())
                {
                    AdvancedAIBase task = it.next();
                    if (task.isFinished())
                    {
                        if (task.shouldContinue())
                        {
                            task.execute();
                        }
                        currentTasks.remove(task);
                    } else
                    {
                        if (task.isUpdatable())
                        {
                            task.update();
                        }
                    }
                }
            }
        }
    }

    /**
     * Sorts the tasks by importance
     */
    private void sortTasks()
    {
        tasks.sort(Comparator.comparing(AdvancedAIBase::getImportance).reversed());
    }
}
