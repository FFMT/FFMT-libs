package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFMTCapePlayerEventHandler
{
	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Specials.Pre event)
	{
		if(event.entityPlayer.getCommandSenderName() != null && !event.entityPlayer.getCommandSenderName().isEmpty())
		{
			FFMTCustomPlayerProp player = FFMTCustomPlayerProp.get(event.entityPlayer);
			if(player.downloadImageCape == null)
			{
				player.downloadImageCape = player.getDownloadImageCape(player.getLocationCape(event.entityPlayer.getCommandSenderName()), event.entityPlayer.getCommandSenderName());
			}
			else
			{
				boolean flag = player.getTextureCape().isTextureUploaded();
				boolean playerVisible = !event.entityPlayer.isInvisible();
				boolean capeVisible = !event.entityPlayer.getHideCape();
				flag = event.renderCape && flag;
				float f6;

				if(flag && playerVisible && capeVisible)
				{
					Minecraft.getMinecraft().renderEngine.bindTexture(player.getLocationCape(event.entityPlayer.getCommandSenderName()));
					GL11.glPushMatrix();
					GL11.glTranslatef(0.0F, 0.0F, 0.125F);
					double d0 = event.entityPlayer.field_71091_bM + (event.entityPlayer.field_71094_bP - event.entityPlayer.field_71091_bM) * (double)event.partialRenderTick - (event.entityPlayer.prevPosX + (event.entityPlayer.posX - event.entityPlayer.prevPosX) * (double)event.partialRenderTick);
					double d1 = event.entityPlayer.field_71096_bN + (event.entityPlayer.field_71095_bQ - event.entityPlayer.field_71096_bN) * (double)event.partialRenderTick - (event.entityPlayer.prevPosY + (event.entityPlayer.posY - event.entityPlayer.prevPosY) * (double)event.partialRenderTick);
					double d2 = event.entityPlayer.field_71097_bO + (event.entityPlayer.field_71085_bR - event.entityPlayer.field_71097_bO) * (double)event.partialRenderTick - (event.entityPlayer.prevPosZ + (event.entityPlayer.posZ - event.entityPlayer.prevPosZ) * (double)event.partialRenderTick);
					f6 = event.entityPlayer.prevRenderYawOffset + (event.entityPlayer.renderYawOffset - event.entityPlayer.prevRenderYawOffset) * event.partialRenderTick;
					double d3 = (double)MathHelper.sin(f6 * (float)Math.PI / 180.0F);
					double d4 = (double)(-MathHelper.cos(f6 * (float)Math.PI / 180.0F));
					float f7 = (float)d1 * 10.0F;

					if(f7 < -6.0F)
					{
						f7 = -6.0F;
					}

					if(f7 > 32.0F)
					{
						f7 = 32.0F;
					}

					float f8 = (float)(d0 * d3 + d2 * d4) * 100.0F;
					float f9 = (float)(d0 * d4 - d2 * d3) * 100.0F;

					if(f8 < 0.0F)
					{
						f8 = 0.0F;
					}

					float f10 = event.entityPlayer.prevCameraYaw + (event.entityPlayer.cameraYaw - event.entityPlayer.prevCameraYaw) * event.partialRenderTick;
					f7 += MathHelper.sin((event.entityPlayer.prevDistanceWalkedModified + (event.entityPlayer.distanceWalkedModified - event.entityPlayer.prevDistanceWalkedModified) * event.partialRenderTick) * 6.0F) * 32.0F * f10;

					if(event.entityPlayer.isSneaking())
					{
						f7 += 25.0F;
					}

					GL11.glRotatef(6.0F + f8 / 2.0F + f7, 1.0F, 0.0F, 0.0F);
					GL11.glRotatef(f9 / 2.0F, 0.0F, 0.0F, 1.0F);
					GL11.glRotatef(-f9 / 2.0F, 0.0F, 1.0F, 0.0F);
					GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
					ModelBiped modelBiped = new ModelBiped();
					modelBiped.renderCloak(0.0625F);
					GL11.glPopMatrix();
				}
			}
		}
	}

	@SubscribeEvent
	public void onEntityConstructing(EntityEvent.EntityConstructing event)
	{
		if(event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			player.registerExtendedProperties(FFMTCustomPlayerProp.ENTITY_PROP_NAME, new FFMTCustomPlayerProp(player));
		}
	}
}
