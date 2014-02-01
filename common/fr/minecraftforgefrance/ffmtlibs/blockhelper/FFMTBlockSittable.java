package fr.minecraftforgefrance.ffmtlibs.blockhelper;

import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;


/**
 For use, add this in your block: (change heightOfThePlayer with a float) 
 Pour l'utiliser, ajoutez ceci dans votre bloc: (changez heightOfThePlayer avec un float)
 
 public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7, float par8, float par9)
 {
 	return FFMTBlockSittable.onBlockActivated(world, x, y, z, player, 0.5F, heightOfThePlayer, 0.5F, 0, 0, 0, 0);
 }
 **/
public class FFMTBlockSittable extends Block
{
	public FFMTBlockSittable(int x, Material material)
	{
		super(x, material);
	}

	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int par6, float par7, float par8, float par9)
	{
		return onBlockActivated(world, i, j, k, entityplayer, 0.5F, 1.0F, 0.5F, 0, 0, 0, 0);
	}

	public static boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float y)
	{
		return onBlockActivated(world, i, j, k, entityplayer, 0.5F, y, 0.5F, 0, 0, 0, 0);
	}

	public static boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, float x, float y, float z, int north, int south, int east, int west)
	{
		if(!world.isRemote)
		{
			List<EntityFFMTBlockSittable> listEMB = world.getEntitiesWithinAABB(EntityFFMTBlockSittable.class, AxisAlignedBB.getBoundingBox(i, j, k, i + 1.0D, j + 1.0D, k + 1.0D).expand(1.0D, 1.0D, 1.0D));
			for(EntityFFMTBlockSittable entitytocheck : listEMB)
			{
				if((entitytocheck.orgBlockPosX == i) && (entitytocheck.orgBlockPosY == j) && (entitytocheck.orgBlockPosZ == k))
				{
					entitytocheck.interact(entityplayer);
					return true;
				}
			}

			float mountingX = i + x;
			float mountingY = j + y;
			float mountingZ = k + z;

			if(north != south)
			{
				int md = world.getBlockMetadata(i, j, k);
				if(md == east)
				{
					mountingX = i + 1 - z;
					mountingZ = k + x;
				}
				else if(md == south)
				{
					mountingX = i + 1 - x;
					mountingZ = k + 1 - z;
				}
				else if(md == west)
				{
					mountingX = i + z;
					mountingZ = k + 1 - x;
				}
			}

			EntityFFMTBlockSittable nemb = new EntityFFMTBlockSittable(world, entityplayer, i, j, k, mountingX, mountingY, mountingZ);
			world.spawnEntityInWorld(nemb);
			nemb.interact(entityplayer);
			return true;
		}
		return true;
	}
}