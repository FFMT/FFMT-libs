package fr.minecraftforgefrance.ffmtlibs.block;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHelper
{
	/**
	 * Spawn particles
	 *
	 * @param speed
	 * @param particles
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param random
	 * @param velX Velocity on X-axis
	 * @param velY Velocity on Y-axis
	 * @param velZ Velocity on Z-axis
	 * @param ids of blocks (for texture)
	 */
	@SideOnly(Side.CLIENT)
	public static void spawnParticles(int speed, EnumParticleTypes particles, World world, int posX, int posY, int posZ, Random random, double velX, double velY, double velZ, int ... blockId)
	{
		float x = (float)posX + random.nextFloat();
		float y = (float)posY + random.nextFloat() * 0.1F;
		float z = (float)posZ + random.nextFloat();

		for(int i = 0; i < speed; i++)
		{
			world.spawnParticle(particles, (double)x, (double)y, (double)z, velX, velY, velZ, blockId);
		}
	}

	/**
	 * Get the position of the block in the cursor of the player. Warning, client side only.
	 *
	 * @author letherman255, robin4002
	 * @param distance - the distance
	 * @param living - the living's object
	 * @return BlockCoords with the position of the block. Warning, if the block isn't found, the value will be null
	 */
	public static BlockCoords getBlockInSight(int distance, EntityLivingBase living)
	{
		//TODO find new
//		MovingObjectPosition objectMouseOver = living.rayTrace(distance, 1);
//
//		if(objectMouseOver != null && objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
//		{
//			return new BlockCoords(objectMouseOver.blockX, objectMouseOver.blockY, objectMouseOver.blockZ);
//		}
		return null;
	}
}