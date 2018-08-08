package net.dumbcode.todm.server.entities.ai.group;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.entity.EntityLiving;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Herd
{

    private UUID leader;
    private List<UUID> members;

    private int herdSize;

    public Herd(int herdSize)
    {
        this.herdSize = herdSize;
    }

    public void addMember(EntityLiving entity)
    {
        if (members.size() < herdSize)
        {
            members.add(entity.getUniqueID());
        }
    }

    public void removeMember(EntityLiving entity)
    {
        members.remove(entity.getUniqueID());
    }
}
