package fr.minecraftforgefrance.ffmtlibs.block;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockHelper
{
	/**
	 * Spawn particles
	 *
	 * @param quantity high value = more particle
	 * @param particles particle type
	 * @param world instance of world
	 * @param posX x coord
	 * @param posY y coord
	 * @param posZ z coord
	 * @param velX Velocity on X-axis
	 * @param velY Velocity on Y-axis
	 * @param velZ Velocity on Z-axis
	 * @param blockId id of blocks (for texture) (use Block.getIdFromBlock)
	 */
	@SideOnly(Side.CLIENT)
	public static void spawnParticles(int quantity, EnumParticleTypes particles, World world, int posX, int posY, int posZ, double velX, double velY, double velZ, int ... blockId)
	{
		float x = posX + world.rand.nextFloat();
		float y = posY + world.rand.nextFloat() * 0.1F;
		float z = posZ + world.rand.nextFloat();

		for(int i = 0; i < quantity; i++)
		{
			world.spawnParticle(particles, x, y, z, velX, velY, velZ, blockId);
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
	@SideOnly(Side.CLIENT)
	public static BlockPos getBlockInSight(int distance, EntityLivingBase living)
	{
		RayTraceResult objectMouseOver = living.rayTrace(distance, 1);

		if(objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK)
		{
		    return objectMouseOver.getBlockPos();
		}
		return null;
	}
}