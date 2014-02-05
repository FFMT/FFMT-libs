package fr.minecraftforgefrance.ffmtlibs.blockhelper;

import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFFMTLeavesBase extends BlockLeaves
{
	protected IIcon fastIcon;

	protected BlockFFMTLeavesBase()
	{
		super();
		this.setLightOpacity(1);
	}

	public boolean isOpaqueCube()
	{
		return Blocks.leaves.isOpaqueCube();
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockaccess, int x, int y, int z, int side)
	{
		return !this.isOpaqueCube() ? true : super.shouldSideBeRendered(blockaccess, x, y, z, side);
	}
	
	@Override
	public IIcon getIcon(int side, int metadata)
	{
		return(isOpaqueCube() ? fastIcon : blockIcon);
	}

	/*@Override
	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list)
	{
		list.add(new ItemStack(par1, 1, 0));
	}*/
	
    @Override
    public boolean isLeaves(IBlockAccess world, int x, int y, int z)
    {
        return true;
    }

	@Override
	public String[] func_150125_e()
	{
		return null;
	}
}
