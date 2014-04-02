package fr.minecraftforgefrance.ffmtlibs.network;

import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@ChannelHandler.Sharable
public class FFMTPacketHandler extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
{

	private EnumMap<Side, FMLEmbeddedChannel> channels;
	private LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();
	private boolean isPostInitialised = false;

	public FFMTPacketHandler(String packetsPackage)
	{
		try
		{
			ImmutableSet<ClassInfo> set = ClassPath.from(ClassLoader.getSystemClassLoader()).getTopLevelClassesRecursive(packetsPackage);
			Iterator<ClassInfo> it = set.iterator();
			while(it.hasNext())
			{
				ClassInfo info = it.next();
				Class<?> c = Class.forName(info.getName());
				if(isInstanceof(c, AbstractPacket.class))
				{
					registerPacket((Class<? extends AbstractPacket>)c);
				}
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	private static boolean isInstanceof(Class<?> c1, Class<?> c)
	{
		Class<?>[] ints = c1.getInterfaces();
		if(ints != null)
			for(int i = 0; i < ints.length; i++)
			{
				if(c == ints[i])
					return true;
			}
		if(c1 == c)
		{
			return true;
		}
		while(!verifySuperclass(c1, c))
		{
			c1 = c1.getSuperclass();
			if(c1 == null)
				return false;
		}
		return true;
	}

	private static boolean verifySuperclass(Class<?> daughter, Class<?> mother)
	{
		if(daughter.getSuperclass() == mother)
		{
			return true;
		}
		else
			return false;
	}

	private boolean registerPacket(Class<? extends AbstractPacket> clazz)
	{
		if(this.packets.size() > 256)
		{
			FFMTLibs.FFMTlog.error("packets size > 256");
			return false;
		}

		if(this.packets.contains(clazz))
		{
			FFMTLibs.FFMTlog.error("packets contains clazz");
			return false;
		}

		if(this.isPostInitialised)
		{
			FFMTLibs.FFMTlog.error("packetshandler is postIntialised");
			return false;
		}

		this.packets.add(clazz);
		return true;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception
	{
		ByteBuf buffer = Unpooled.buffer();
		Class<? extends AbstractPacket> clazz = msg.getClass();
		if(!this.packets.contains(msg.getClass()))
		{
			throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
		}

		byte discriminator = (byte)this.packets.indexOf(clazz);
		buffer.writeByte(discriminator);
		msg.encodeInto(ctx, buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
	{
		ByteBuf payload = msg.payload();
		byte discriminator = payload.readByte();
		Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
		if(clazz == null)
		{
			throw new NullPointerException("No packet registered for discriminator: " + discriminator);
		}

		AbstractPacket pkt = clazz.newInstance();
		pkt.decodeInto(ctx, payload.slice());

		EntityPlayer player;
		switch(FMLCommonHandler.instance().getEffectiveSide())
		{
		case CLIENT:
			player = this.getClientPlayer();
			pkt.handleClientSide(player);
			break;

		case SERVER:
			INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
			player = ((NetHandlerPlayServer)netHandler).playerEntity;
			pkt.handleServerSide(player);
			break;

		default:
		}

		out.add(pkt);
	}

	public void initialise(String channel)
	{
		this.channels = NetworkRegistry.INSTANCE.newChannel(channel, this);
	}

	public void postInitialise()
	{
		if(this.isPostInitialised)
		{
			return;
		}

		this.isPostInitialised = true;
		Collections.sort(this.packets, new Comparator<Class<? extends AbstractPacket>>()
		{

			@Override
			public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
			{
				int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
				if(com == 0)
				{
					com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
				}

				return com;
			}
		});
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	public void sendToAll(AbstractPacket message)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendTo(AbstractPacket message, EntityPlayerMP player)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToDimension(AbstractPacket message, int dimensionId)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToServer(AbstractPacket message)
	{
		this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		this.channels.get(Side.CLIENT).writeAndFlush(message);
	}
}