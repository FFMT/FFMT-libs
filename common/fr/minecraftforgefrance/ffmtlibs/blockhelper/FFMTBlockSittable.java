package fr.minecraftforgefrance.ffmtlibs.blockhelper;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * For use, add this in your block: (change heightOfThePlayer with a float)
 * Pour l'utiliser, ajoutez ceci dans votre bloc: (changez heightOfThePlayer avec un float)
 * 
 * <pre>
 * public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
 * {
 * 	return FFMTBlockSittable.onBlockActivated(world, x, y, z, player, 0.5F, heightOfThePlayer, 0.5F, 0, 0, 0, 0);
 * }
 * </pre>
 **/
public class FFMTBlockSittable extends Block
{
	public FFMTBlockSittable(Material material)
	{
		super(material);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
	{
		return onBlockActivated(world, x, y, z, player, 0.5F, 1.0F, 0.5F, 0, 0, 0, 0);
	}

	public static boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, float Yy)
	{
		return onBlockActivated(world, x, y, z, player, 0.5F, Yy, 0.5F, 0, 0, 0, 0);
	}

	public static boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, float Xx, float Yy, float Zz, int north, int south, int east, int west)
	{
		if(!world.isRemote)
		{
			List<EntityFFMTBlockSittable> listEMB = world.getEntitiesWithinAABB(EntityFFMTBlockSittable.class, AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1.0D, 1.0D, 1.0D));
			for(EntityFFMTBlockSittable entitytocheck : listEMB)
			{
				if((entitytocheck.blockPosX == x) && (entitytocheck.blockPosY == y) && (entitytocheck.blockPosZ == z))
				{
					entitytocheck.interact(player);
					return true;
				}
			}

			float mountingX = x + Xx;
			float mountingY = y + Yy;
			float mountingZ = z + Zz;

			if(north != south)
			{
				int md = world.getBlockMetadata(x, y, z);
				if(md == east)
				{
					mountingX = x + 1 - Zz;
					mountingZ = z + Xx;
				}
				else if(md == south)
				{
					mountingX = x + 1 - Xx;
					mountingZ = z + 1 - Zz;
				}
				else if(md == west)
				{
					mountingX = x + Zz;
					mountingZ = z + 1 - Xx;
				}
			}

			EntityFFMTBlockSittable entity = new EntityFFMTBlockSittable(world, player, x, y, z, mountingX, mountingY, mountingZ);
			world.spawnEntityInWorld(entity);
			entity.interact(player);
			return true;
		}
		return true;
	}
}