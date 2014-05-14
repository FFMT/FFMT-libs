package fr.minecraftforgefrance.ffmtlibs.network;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;

public abstract class AbstractPacket
{
	public abstract void encodeInto(ChannelHandlerContext ctx, PacketBuffer buffer) throws IOException;

	public abstract void decodeInto(ChannelHandlerContext ctx, PacketBuffer buffer) throws IOException;

	public abstract void handleClientSide(EntityPlayer player);

	public abstract void handleServerSide(EntityPlayer player);
}