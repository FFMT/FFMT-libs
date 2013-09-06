package fr.minecraftforgefrance.ffmtapi.blockhelper;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFFMTLeavesBase extends BlockLeaves
{
	protected Icon fastIcon;

	protected BlockFFMTLeavesBase(int id)
	{
		super(id);
		this.setLightOpacity(1);
	}

	public boolean isOpaqueCube()
	{
		return Block.leaves.isOpaqueCube();
	}

	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess blockaccess, int x, int y, int z, int side)
	{
		return !this.isOpaqueCube() ? true : super.shouldSideBeRendered(blockaccess, x, y, z, side);
	}

	public Icon getIcon(int side, int metadata)
	{
		return(isOpaqueCube() ? fastIcon : blockIcon);
	}

	public void getSubBlocks(int par1, CreativeTabs creativeTabs, List list)
	{
		list.add(new ItemStack(par1, 1, 0));
	}
	
	@Override
	public boolean isLeaves(World world, int x, int y, int z)
	{
		return true;
	}
}
