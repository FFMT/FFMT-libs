package fr.minecraftforgefrance.ffmtlibs.network;

import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
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

	/**
	 * Register a packet handler
	 * 
	 * @param packetsPackage The package where all your packet are located
	 * @param modid The modid of your mod
	 */
	public FFMTPacketHandler(String packetsPackage, String modid)
	{
		ModContainer mod = Loader.instance().getIndexedModList().get(modid);
		try
		{
			if(mod.getSource().isDirectory())
			{
				File packetsDir = new File(mod.getSource(), packetsPackage.replace(".", "/"));
				for(String packet : packetsDir.list())
				{
					packet = packet.replace(".class", "");
					Class<?> c = Class.forName(packetsPackage + "." + packet);
					if(isInstanceof(c, AbstractPacket.class))
					{
						registerPacket((Class<? extends AbstractPacket>)c);
						FFMTLibs.ffmtLog.info("Successful register packet : " + packetsPackage + "." + packet + " from " + mod.getModId());
					}
				}
			}
			else
			{
				ZipFile zipFile = new ZipFile(mod.getSource());
				Enumeration<? extends ZipEntry> entries = zipFile.entries();

				while(entries.hasMoreElements())
				{
					ZipEntry entry = entries.nextElement();
					if(entry.getName().startsWith(packetsPackage.replace(".", "/")) && entry.getName().endsWith(".class"))
					{
						Class<?> c = Class.forName(entry.getName().replace(".class", "").replace("/", "."));
						if(isInstanceof(c, AbstractPacket.class))
						{
							registerPacket((Class<? extends AbstractPacket>)c);
							FFMTLibs.ffmtLog.info("Successful register packet : " + entry.getName().replace("/", ".") + " from " + mod.getModId());
						}
					}
				}
				zipFile.close();
			}
		}
		catch(IOException e)
		{
			System.err.println("Failed to register packet for the mod : " + mod.getModId());
			e.printStackTrace();
		}
		catch(ClassNotFoundException e)
		{
			System.err.println("Failed to register packet for the mod : " + mod.getModId());
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
			FFMTLibs.ffmtLog.error("packets size > 256");
			return false;
		}

		if(this.packets.contains(clazz))
		{
			FFMTLibs.ffmtLog.error("Packet " + clazz.getName() + " is already registered");
			return false;
		}

		if(this.isPostInitialised)
		{
			FFMTLibs.ffmtLog.error("You can't register a packet if your packet handler was post initialised");
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

		PacketBuffer packetbuffer = new PacketBuffer(buffer);
		packetbuffer.writeByte(discriminator);
		msg.encodeInto(ctx, packetbuffer);

		FMLProxyPacket proxyPacket = new FMLProxyPacket(packetbuffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
	{
		ByteBuf payload = msg.payload();

		PacketBuffer packetbuffer = new PacketBuffer(payload);
		byte discriminator = packetbuffer.readByte();

		Class<? extends AbstractPacket> clazz = this.packets.get(discriminator);
		if(clazz == null)
		{
			throw new NullPointerException("No packet registered for discriminator: " + discriminator);
		}

		AbstractPacket pkt = clazz.newInstance();

		pkt.decodeInto(ctx, packetbuffer);

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