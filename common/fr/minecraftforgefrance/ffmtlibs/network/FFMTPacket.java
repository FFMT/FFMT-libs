package fr.minecraftforgefrance.ffmtlibs.network;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;

public abstract class FFMTPacket
{
	public abstract void writeData(ByteBuf buffer) throws IOException;

	public abstract void readData(ByteBuf buffer);

	public abstract void handleClientSide(EntityPlayer player);

	public abstract void handleServerSide(EntityPlayer player);
	
	/**
	 * 
	 * @return discriminator of the packet,
	 */
	public abstract int getDiscriminator();
}
