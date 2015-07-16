package fr.minecraftforgefrance.ffmtlibs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityBlockSittable extends Entity
{
	public int blockPosX;
	public int blockPosY;
	public int blockPosZ;

	public EntityBlockSittable(World world)
	{
		super(world);
		this.noClip = true;
		this.preventEntitySpawning = true;
		this.setSize(0.0F, 0.0F);
	}

	public EntityBlockSittable(World world, EntityPlayer entityplayer, int x, int y, int z, float entityX, float entityY, float entityZ)
	{
		this(world);
		this.blockPosX = x;
		this.blockPosY = y;
		this.blockPosZ = z;
		this.setPosition(entityX, entityY, entityZ);
	}

	public boolean interact(EntityPlayer entityplayer)
	{
		if(this.riddenByEntity != null)
		{
			return true;
		}

		if(!this.worldObj.isRemote)
		{
			entityplayer.mountEntity(this);
		}
		return true;
	}

	@Override
    public void onEntityUpdate()
	{
		if(this.riddenByEntity == null || this.riddenByEntity.isDead)
		{
			this.setDead();
		}
		super.onEntityUpdate();
	}

	@Override
	public void readEntityFromNBT(NBTTagCompound compound)
	{
		this.blockPosX = compound.getInteger("blockPosX");
		this.blockPosY = compound.getInteger("blockPosY");
		this.blockPosZ = compound.getInteger("blockPosZ");
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound compound)
	{
		compound.setInteger("blockPosX", this.blockPosX);
		compound.setInteger("blockPosY", this.blockPosY);
		compound.setInteger("blockPosZ", this.blockPosZ);
	}

	@Override
	protected void entityInit()
	{

	}
}