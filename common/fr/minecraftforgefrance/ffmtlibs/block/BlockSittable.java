package fr.minecraftforgefrance.ffmtlibs.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

/**
 * To use, add this in your block: (change heightOfThePlayer with a float)
 *
 * <pre>
 * public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
 * {
 * 	return FFMTBlockSittable.sitPlayer(world, x, y, z, player, heightOfThePlayer);
 * }
 * </pre>
 **/
public class BlockSittable extends Block
{
	public BlockSittable(Material material)
	{
		super(material);
	}

	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		return sitPlayer(world, x, y, z, player, 1.0F);
	}

	public static boolean sitPlayer(World world, int x, int y, int z, EntityPlayer player, float entityY)
	{
		return sitPlayer(world, x, y, z, player, 0.5F, entityY, 0.5F);
	}

	public static boolean sitPlayer(World world, int x, int y, int z, EntityPlayer player, float entityX, float entityY, float entityZ)
	{
		if(!world.isRemote)
		{
			List<EntityBlockSittable> listEMB = world.getEntitiesWithinAABB(EntityBlockSittable.class, AxisAlignedBB.fromBounds(x, y, z, x + 1.0D, y + 1.0D, z + 1.0D).expand(1.0D, 1.0D, 1.0D));
			for(EntityBlockSittable entitytocheck : listEMB)
			{
				if((entitytocheck.blockPosX == x) && (entitytocheck.blockPosY == y) && (entitytocheck.blockPosZ == z))
				{
					entitytocheck.interact(player);
					return true;
				}
			}

			EntityBlockSittable entity = new EntityBlockSittable(world, player, x, y, z, x + entityX, y + entityY, z + entityZ);
			world.spawnEntityInWorld(entity);
			entity.interact(player);
		}
		return true;
	}
}