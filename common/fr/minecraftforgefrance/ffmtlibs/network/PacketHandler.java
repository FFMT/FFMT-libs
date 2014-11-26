package fr.minecraftforgefrance.ffmtlibs.network;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Sharable
public class PacketHandler extends SimpleChannelInboundHandler<FFMTPacket>
{
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FFMTPacket packet) throws Exception
	{
		EntityPlayer player;
		switch(FMLCommonHandler.instance().getEffectiveSide())
		{
		case CLIENT:
			player = this.getClientPlayer();
			packet.handleClientSide(player);
			break;

		case SERVER:
			INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
			player = ((NetHandlerPlayServer)netHandler).playerEntity;
			packet.handleServerSide(player);
			break;
		}
	}
	
	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
}