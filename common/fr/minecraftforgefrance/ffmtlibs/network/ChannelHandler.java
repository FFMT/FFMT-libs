package fr.minecraftforgefrance.ffmtlibs.network;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandler.Sharable;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

@Sharable
public class ChannelHandler extends FMLIndexedMessageToMessageCodec<FFMTPacket>
{
	@Override
	public void encodeInto(ChannelHandlerContext ctx, FFMTPacket packet, ByteBuf target) throws Exception
	{
		packet.writeData(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, FFMTPacket packet)
	{
		packet.readData(source);
	}
}