package fr.minecraftforgefrance.ffmtlibs.entityhelper;

import net.minecraft.block.BlockLeavesBase;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

/**
 * This class is *same* of EntityWaterMob, but this is an entity can spawn on leaves block !
 * @author elias54
 *
 */

public class EntityLeafCreature extends EntityCreature {

	public EntityLeafCreature(World world)
	{
		super(world);
	}
	
	public boolean getCanSpawnHere()
	{
		int i = MathHelper.floor_double(posX);
		int j = MathHelper.floor_double(posY);
		int k = MathHelper.floor_double(posZ);
		int j2 = MathHelper.floor_double(boundingBox.maxY);
		return super.getCanSpawnHere() && worldObj.getBlock(i, j - 1, k) instanceof BlockLeavesBase && this.getBlockPathWeight(j, j2, k) >= 0.0F;
	}
}