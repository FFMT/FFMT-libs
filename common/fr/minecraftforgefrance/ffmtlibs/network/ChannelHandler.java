package fr.minecraftforgefrance.ffmtlibs.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import net.minecraftforge.fml.common.network.FMLIndexedMessageToMessageCodec;

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