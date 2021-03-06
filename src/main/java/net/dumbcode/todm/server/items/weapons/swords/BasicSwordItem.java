package net.dumbcode.todm.server.items.weapons.swords;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import lombok.Getter;
import lombok.Setter;
import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.items.weapons.WeaponType;
import net.dumbcode.todm.server.utils.StringUtils;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemSword;

@Setter
@Getter
public class BasicSwordItem extends ItemSword
{

    private float attackModifier;
    /* Base Minecraft value*/
    private double weaponSpeed = -2.4000000953674316D;
    private WeaponType type;

    public BasicSwordItem(String name, ToolMaterial material, float attackModifier)
    {
        super(material);
        String formattedName = StringUtils.formatString(name);
        this.setRegistryName(TODM.MODID, formattedName);
        this.setUnlocalizedName(formattedName);
        this.attackModifier = attackModifier + material.getAttackDamage();
    }

    public BasicSwordItem(String name, ToolMaterial material, float attackModifier, WeaponType type)
    {
        super(material);
        name += "_" + type.name();
        String formattedName = StringUtils.formatString(name);
        this.setRegistryName(TODM.MODID, formattedName);
        this.setUnlocalizedName(formattedName);
        this.attackModifier = attackModifier + material.getAttackDamage();
        this.setType(type);
    }

    public BasicSwordItem(String name, ToolMaterial material, float attackModifier, WeaponType type, double weaponSpeed)
    {
        this(name, material, attackModifier, type);
        this.setWeaponSpeed(weaponSpeed);
    }

    @Override
    public Multimap<String, AttributeModifier> getItemAttributeModifiers(EntityEquipmentSlot equipmentSlot)
    {
        Multimap<String, AttributeModifier> multimap = HashMultimap.create();

        if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
        {
            multimap.put(SharedMonsterAttributes.ATTACK_DAMAGE.getName(), new AttributeModifier(ATTACK_DAMAGE_MODIFIER, "Weapon modifier", (double) this.attackModifier, 0));
            multimap.put(SharedMonsterAttributes.ATTACK_SPEED.getName(), new AttributeModifier(ATTACK_SPEED_MODIFIER, "Weapon modifier", this.weaponSpeed, 0));
        }

        return multimap;
    }
}
