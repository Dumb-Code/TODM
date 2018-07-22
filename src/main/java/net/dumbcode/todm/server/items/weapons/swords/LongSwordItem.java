package net.dumbcode.todm.server.items.weapons.swords;

import net.dumbcode.todm.server.items.weapons.WeaponType;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class LongSwordItem extends BasicSwordItem
{

    public LongSwordItem(String name, ToolMaterial material, WeaponType type)
    {
        super(name, material, 4f, type, -3);
        this.setCreativeTab(TabHandler.WEAPONS);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
        if(stack.getItem() instanceof LongSwordItem && !player.world.isRemote)
        {
            if(((LongSwordItem) stack.getItem()).getType().equals(WeaponType.SCORCHING))
            {
                if(player.world.rand.nextFloat() <= .1F)
                {
                    entity.setFire(5);
                }
            }
        }
        return super.onLeftClickEntity(stack, player, entity);
    }
}
