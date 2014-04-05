package fr.minecraftforgefrance.ffmtlibs.block;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityFFMTBlockSittable extends Entity
{
	protected int blockPosX;
	protected int blockPosY;
	protected int blockPosZ;
	protected Block block;

	public EntityFFMTBlockSittable(World world)
	{
		super(world);
		this.noClip = true;
		this.preventEntitySpawning = true;
		this.width = 0.0F;
		this.height = 0.0F;
	}

	public EntityFFMTBlockSittable(World world, double x, double y, double z)
	{
		super(world);
		this.noClip = true;
		this.preventEntitySpawning = true;
		this.width = 0.0F;
		this.height = 0.0F;
		setPosition(x, y, z);
	}

	public EntityFFMTBlockSittable(World world, EntityPlayer entityplayer, int x, int y, int z, float Xx, float Yy, float Zz)
	{
		super(world);
		this.noClip = true;
		this.preventEntitySpawning = true;
		this.width = 0.0F;
		this.height = 0.0F;

		this.blockPosX = x;
		this.blockPosY = y;
		this.blockPosZ = z;
		this.block = world.getBlock(x, y, z);
		setPosition(Xx, Yy, Zz);
	}

	public boolean interact(EntityPlayer entityplayer)
	{
		if((this.riddenByEntity != null) && ((this.riddenByEntity instanceof EntityPlayer)) && (this.riddenByEntity != entityplayer))
		{
			return true;
		}

		if(!this.worldObj.isRemote)
		{
			entityplayer.mountEntity(this);
		}
		return true;
	}

	public void onEntityUpdate()
	{
		this.worldObj.theProfiler.startSection("entityBaseTick");
		if((this.riddenByEntity == null) || (this.riddenByEntity.isDead))
		{
			setDead();
		}
		else if(this.worldObj.getBlock(this.blockPosX, this.blockPosY, this.blockPosZ).equals(block))
		{
			interact((EntityPlayer)this.riddenByEntity);
		}
		this.ticksExisted += 1;
		this.worldObj.theProfiler.endSection();
	}

	public void entityInit()
	{}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound)
	{}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
	{}
}