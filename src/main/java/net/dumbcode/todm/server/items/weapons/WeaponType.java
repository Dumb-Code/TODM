package net.dumbcode.todm.server.items.weapons;

import lombok.Getter;

@Getter
public enum WeaponType
{
    GOLD("Gold"), DIAMOND("Diamond"), MAGIC("Magic");

    private String name;

    WeaponType(String name)
    {
        this.name = name;
    }
}
