package net.dumbcode.todm.server.entities.ai.group;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.EntityLiving;

import java.util.List;

@Getter
@Setter
public class Herd
{

    private EntityLiving leader;
    private List<EntityLiving> members;

    private int herdSize;

    public Herd(int herdSize)
    {
        this.herdSize = herdSize;
    }

    public void addMember(EntityLiving entity)
    {
        if (members.size() >= herdSize)
        {
            return;
        }
        members.add(entity);
    }
}
