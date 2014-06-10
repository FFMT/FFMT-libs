package fr.minecraftforgefrance.ffmtlibs.network;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.Enumeration;
import java.util.LinkedList;
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
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;

public class PacketManager
{
	private EnumMap<Side, FMLEmbeddedChannel> channels;
	private LinkedList<Class<? extends FFMTPacket>> packets = new LinkedList<Class<? extends FFMTPacket>>();
	private boolean isPostInitialised = false;
	private ChannelHandler channelHandler;
	private PacketHandler packetHandler;
	
	/**
	 * Register a packet handler
	 * 
	 * @param packetsPackage The package where all your packet are located
	 * @param modid The modid of your mod
	 */
	public PacketManager(String packetsPackage, String modid, String channel)
	{
		this.channelHandler = new ChannelHandler();
		this.packetHandler = new PacketHandler();
		this.channels = NetworkRegistry.INSTANCE.newChannel(channel, channelHandler, packetHandler);
		
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
					if(isInstanceof(c, FFMTPacket.class))
					{
						registerPacket((Class<? extends FFMTPacket>)c);
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
						if(isInstanceof(c, FFMTPacket.class))
						{
							registerPacket((Class<? extends FFMTPacket>)c);
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
	
	private boolean registerPacket(Class<? extends FFMTPacket> clazz)
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

		channelHandler.addDiscriminator(this.packets.size(), clazz);
		this.packets.add(clazz);
		return true;
	}
	
	public void sendToAll(FFMTPacket message)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendTo(FFMTPacket message, EntityPlayerMP player)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToAllAround(FFMTPacket message, NetworkRegistry.TargetPoint point)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToDimension(FFMTPacket message, int dimensionId)
	{
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
		this.channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
		this.channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToServer(FFMTPacket message)
	{
		this.channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		this.channels.get(Side.CLIENT).writeAndFlush(message);
	}
}