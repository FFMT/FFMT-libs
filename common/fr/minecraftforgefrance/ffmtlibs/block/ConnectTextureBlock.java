package fr.minecraftforgefrance.ffmtlibs.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.world.IBlockAccess;

public abstract class ConnectTextureBlock extends Block
{
	/**
	 * Array for return value
	 */
	protected String[] iconName = new String[] {"all", "bottom_right", "top_right", "top_left", "bottom_left", "top_left_top_right", "top_left_bottom_left", "top_left_bottom_right", "top_right_bottom_left", "top_right_bottom_right", "bottom_left_bottom_right", "top_left_top_right_bottom_left", "top_left_top_right_bottom_right", "top_left_bottom_left_bottom_right", "top_right_bottom_left_bottom_right", "top", "bottom", "left", "right", "top_bottom_left", "top_bottom_right", "bottom_top_left", "bottom_top_right", "left_top_right", "left_bottom_right", "right_top_left", "left_bottom_left", "top_right_left_bottom", "corner_bottom_left", "corner_bottom_right", "corner_top_right", "corner_top_left", "bottom_top_left_top_right", "top_bottom_left_bottom_right", "left_top_right_bottom_right", "right_top_left_bottom_left", "top_bottom", "left_right", "corner_top_left_bottom_right", "corner_top_right_bottom_left", "corner_bottom_left_top_right", "corner_bottom_right_top_left", "d_top_bottom_right", "d_top_bottom_left", "d_bottom_left_right", "d_top_left_right", "nothing"};

	protected ConnectTextureBlock(Material material)
	{
		super(material);
	}

	/**
	 * Determine the id for texture array
	 *
	 * @author Phenix246
	 * @param world instance of the world
	 * @param x coord x
	 * @param y coord y
	 * @param z coord z
	 * @param metadata metadata if the block
	 * @param side side of the block
	 * @param block instance of the block
	 * @return a integer for you icon array. See {@link ConnectTextureBlock#getIconArray()}
	 */
	public int getTexturefromSide(IBlockAccess world, int x, int y, int z, int metadata, int side, Block block)
	{
		int[] cU, cUR, cR, cDR, cD, cDL, cL, cUL; // coordinates of test blocks

		if(side == 0 || side == 1 || side == 3 || side == 4)
		{
			if(side == 3)
			{
				cU = new int[] {x, y + 1, z};
				cUR = new int[] {x + 1, y + 1, z};
				cR = new int[] {x + 1, y, z};
				cDR = new int[] {x + 1, y - 1, z};
				cD = new int[] {x, y - 1, z};
				cDL = new int[] {x - 1, y - 1, z};
				cL = new int[] {x - 1, y, z};
				cUL = new int[] {x - 1, y + 1, z};
			}
			else if(side == 4)
			{
				cU = new int[] {x, y + 1, z};
				cUR = new int[] {x, y + 1, z + 1};
				cR = new int[] {x, y, z + 1};
				cDR = new int[] {x, y - 1, z + 1};
				cD = new int[] {x, y - 1, z};
				cDL = new int[] {x, y - 1, z - 1};
				cL = new int[] {x, y, z - 1};
				cUL = new int[] {x, y + 1, z - 1};
			}
			else
			{
				cU = new int[] {x, y, z - 1};
				cUR = new int[] {x + 1, y, z - 1};
				cR = new int[] {x + 1, y, z};
				cDR = new int[] {x + 1, y, z + 1};
				cD = new int[] {x, y, z + 1};
				cDL = new int[] {x - 1, y, z + 1};
				cL = new int[] {x - 1, y, z};
				cUL = new int[] {x - 1, y, z - 1};
			}

			// 8
			if(canConnect(world, cU, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 0;
			// 7
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 1;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 2;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 3;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 4;
			// 6
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 5;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 6;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 7;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 8;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 9;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 10;
			// 5
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 11;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 12;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 13;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 14;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 15;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 16;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 17;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 18;
			// 4
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 19;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 20;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 21;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata))
				return 22;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 23;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata))
				return 24;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 25;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata))
				return 26;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata))
				return 27;
			// 3
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 28;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 29;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 30;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 31;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 32;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 33;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 34;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 35;
			// 2
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 36;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata))
				return 37;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 38;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 39;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cU, block, metadata))
				return 40;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cU, block, metadata))
				return 41;
			// 1
			if(canConnect(world, cL, block, metadata))
				return 42;
			if(canConnect(world, cR, block, metadata))
				return 43;
			if(canConnect(world, cU, block, metadata))
				return 44;
			if(canConnect(world, cD, block, metadata))
				return 45;
			// 0
			return 46;
		}
		if(side == 2 || side == 5)
		{
			if(side == 2)
			{
				cU = new int[] {x, y + 1, z};
				cUR = new int[] {x - 1, y + 1, z};
				cR = new int[] {x - 1, y, z};
				cDR = new int[] {x - 1, y - 1, z};
				cD = new int[] {x, y - 1, z};
				cDL = new int[] {x + 1, y - 1, z};
				cL = new int[] {x + 1, y, z};
				cUL = new int[] {x + 1, y + 1, z};
			}
			else
			{
				cU = new int[] {x, y + 1, z};
				cUR = new int[] {x, y + 1, z - 1};
				cR = new int[] {x, y, z - 1};
				cDR = new int[] {x, y - 1, z - 1};
				cD = new int[] {x, y - 1, z};
				cDL = new int[] {x, y - 1, z + 1};
				cL = new int[] {x, y, z + 1};
				cUL = new int[] {x, y + 1, z + 1};
			}

			// 8
			if(canConnect(world, cU, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 0;
			// 7
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 1;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 2;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 3;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 4;
			// 6
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 5;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 6;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 7;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 8;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 9;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 10;
			// 5
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 11;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 12;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 13;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 14;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 15;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 16;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 17;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 18;
			// 4
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 19;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 20;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata))
				return 21;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 22;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 23;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata))
				return 24;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 25;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata))
				return 26;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata))
				return 27;
			// 3
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 28;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 29;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 30;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 31;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 32;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 33;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 34;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 35;
			// 2
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 36;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata))
				return 37;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 39;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 38;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cU, block, metadata))
				return 41;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cU, block, metadata))
				return 40;
			// 1
			if(canConnect(world, cR, block, metadata))
				return 42;
			if(canConnect(world, cL, block, metadata))
				return 43;
			if(canConnect(world, cU, block, metadata))
				return 44;
			if(canConnect(world, cD, block, metadata))
				return 45;
			// 0
			return 46;
		}
		if(side == 4)
		{
			cU = new int[] {x, y + 1, z};
			cUR = new int[] {x, y + 1, z + 1};
			cR = new int[] {x, y, z + 1};
			cDR = new int[] {x, y - 1, z + 1};
			cD = new int[] {x, y - 1, z};
			cDL = new int[] {x, y - 1, z - 1};
			cL = new int[] {x, y, z - 1};
			cUL = new int[] {x, y + 1, z - 1};
			// 8
			if(canConnect(world, cU, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 0;
			// 7
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 1;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 2;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 3;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 4;
			// 6
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 5;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 6;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 7;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 8;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 9;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 10;
			// 5
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 11;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 12;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 13;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 14;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata) && canConnect(world, cDR, block, metadata))
				return 15;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cUR, block, metadata))
				return 16;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 17;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata) && canConnect(world, cDL, block, metadata))
				return 18;
			// 4
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDR, block, metadata))
				return 19;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cDL, block, metadata))
				return 20;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 21;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUL, block, metadata))
				return 22;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 23;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUR, block, metadata))
				return 24;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 25;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cUL, block, metadata))
				return 26;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cL, block, metadata))
				return 27;
			// 3
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cUR, block, metadata))
				return 28;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cUL, block, metadata))
				return 29;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDL, block, metadata))
				return 30;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata) && canConnect(world, cDR, block, metadata))
				return 31;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 32;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 33;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 34;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 35;
			// 2
			if(canConnect(world, cL, block, metadata) && canConnect(world, cR, block, metadata))
				return 36;
			if(canConnect(world, cU, block, metadata) && canConnect(world, cD, block, metadata))
				return 37;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cD, block, metadata))
				return 38;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cD, block, metadata))
				return 39;
			if(canConnect(world, cR, block, metadata) && canConnect(world, cU, block, metadata))
				return 40;
			if(canConnect(world, cL, block, metadata) && canConnect(world, cU, block, metadata))
				return 41;
			// 1
			if(canConnect(world, cL, block, metadata))
				return 42;
			if(canConnect(world, cR, block, metadata))
				return 43;
			if(canConnect(world, cU, block, metadata))
				return 44;
			if(canConnect(world, cD, block, metadata))
				return 45;
			// 0
			return 46;
		}
		return 0;
	}

	public boolean canConnect(IBlockAccess world, int[] coord, Block block, int damage)
	{
		if(world.getBlockState(new BlockPos(coord[0], coord[1], coord[2])).getBlock() == block /*&& world.getBlockMetadata(coord[0], coord[1], coord[2]) == damage*/)//TODO
		{
			return true;
		}
		return false;
	}

//	@Override //TODO find new
//	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
//	{
//		return this.getIconArray()[this.getTexturefromSide(world, x, y, z, world.getBlockMetadata(x, y, z), side, this)];
//	}

	/**
	 * @return your array with all icon. To register yours icons, use {@link ConnectTextureBlock#iconName}
	 */
//	public abstract IIcon[] getIconArray();
}