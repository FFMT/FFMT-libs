package fr.mcnanotech.FFMT.FFMTAPITestMod.Entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;

public class FFMTAPIMobTest extends EntityMob
{

	public FFMTAPIMobTest(World par1World) 
	{
		super(par1World);
	}

	@Override
	public int getMaxHealth() 
	{
		return Integer.MAX_VALUE;
	}

}
