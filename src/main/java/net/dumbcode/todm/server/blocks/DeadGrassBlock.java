package net.dumbcode.todm.server.blocks;

import net.dumbcode.todm.TODM;
import net.dumbcode.todm.server.tabs.TabHandler;
import net.dumbcode.todm.server.utils.Utils;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

/**
 * Doesn't really work, but I'll fix it tomorrow
 */
public class DeadGrassBlock extends BlockGrass
{

    public DeadGrassBlock(String name)
    {
        this.setCreativeTab(TabHandler.BLOCKS);
        String formattedName = Utils.formatString(name);
        this.setUnlocalizedName(formattedName);
        this.setRegistryName(TODM.MODID, formattedName);
        this.setTickRandomly(false);
    }

    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient)
    {
        return false;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
    {
        return false;
    }
}
