package fr.minecraftforgefrance.ffmtlibs.event;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

@SideOnly(Side.CLIENT)
public class PlayerEventHandler
{
	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Specials.Pre event)
	{
		if(event.entityPlayer.getCommandSenderName() != null && !event.entityPlayer.getCommandSenderName().isEmpty() && !event.entityPlayer.isInvisible() && !FFMTLibs.hideHat)
		{
			FFMTCustomPlayerProp player = FFMTCustomPlayerProp.get(event.entityPlayer);
			if(player.downloadImageHat == null)
			{
				player.downloadImageHat = player.getDownloadImageHat(player.getLocationHat(event.entityPlayer.getCommandSenderName()), event.entityPlayer.getCommandSenderName());
			}
			if(player.particle == null)
			{
				player.particle = player.getDownloadListHat(event.entityPlayer.getCommandSenderName());
			}
			if(player.model == null)
			{
				player.model = player.getDownloadListModelHat(event.entityPlayer.getCommandSenderName());
			}
			if(player.getTextureHat().isTextureUploaded() && player.particle.isTextDownloaded() && player.model.isTextDownloaded())
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(player.getLocationHat(event.entityPlayer.getCommandSenderName()));
				ModelBiped biped = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1);
				ModelHat hat = new ModelHat(biped, player.model.getValue());

				float f = event.entityPlayer.limbSwing - event.entityPlayer.limbSwingAmount * (1.0F - event.partialRenderTick);
				float f1 = event.entityPlayer.prevLimbSwingAmount + (event.entityPlayer.limbSwingAmount - event.entityPlayer.prevLimbSwingAmount) * event.partialRenderTick;
				float f2 = (float)event.entityPlayer.ticksExisted + event.partialRenderTick;

				float ff2 = this.interpolateRotation(event.entityPlayer.prevRenderYawOffset, event.entityPlayer.renderYawOffset, event.partialRenderTick);
				float ff3 = this.interpolateRotation(event.entityPlayer.prevRotationYawHead, event.entityPlayer.rotationYawHead, event.partialRenderTick);
				float ff4;

				if(event.entityPlayer.isRiding() && event.entityPlayer.ridingEntity instanceof EntityLivingBase)
				{
					EntityLivingBase entitylivingbase1 = (EntityLivingBase)event.entityPlayer.ridingEntity;
					ff2 = this.interpolateRotation(entitylivingbase1.prevRenderYawOffset, entitylivingbase1.renderYawOffset, event.partialRenderTick);
					ff4 = MathHelper.wrapAngleTo180_float(ff3 - ff2);

					if(ff4 < -85.0F)
					{
						ff4 = -85.0F;
					}

					if(ff4 >= 85.0F)
					{
						ff4 = 85.0F;
					}

					ff2 = ff3 - ff4;

					if(ff4 * ff4 > 2500.0F)
					{
						ff2 += ff4 * 0.2F;
					}
				}

				float f3 = ff3 - ff2;
				float f4 = event.entityPlayer.prevRotationPitch + (event.entityPlayer.rotationPitch - event.entityPlayer.prevRotationPitch) * event.partialRenderTick;

				if(f1 > 1.0F)
				{
					f1 = 1.0F;
				}

				hat.render(event.entityPlayer, f, f1, f2, f3, f4, 0.03125F);
				if(player.particle != null && !player.particle.getValue().isEmpty() && !Loader.isModLoaded("nhg"))
				{
					for(String particles : player.particle.getValue())
					{
						event.entityPlayer.worldObj.spawnParticle(particles, event.entityPlayer.posX - 0.5F + event.entityPlayer.worldObj.rand.nextFloat(), event.entityPlayer.posY + 1.0F + event.entityPlayer.worldObj.rand.nextFloat(), event.entityPlayer.posZ - 0.5F + event.entityPlayer.worldObj.rand.nextFloat(), 0.0F, 0.2F, 0.0F);
					}
				}
			}
		}
	}

	private float interpolateRotation(float par1, float par2, float par3)
	{
		float f3;

		for(f3 = par2 - par1; f3 < -180.0F; f3 += 360.0F)
		{
			;
		}

		while(f3 >= 180.0F)
		{
			f3 -= 360.0F;
		}

		return par1 + par3 * f3;
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

	public class ModelHat extends ModelBase
	{
		List<String> str;
		ModelRenderer[] model;
		ModelBiped biped;

		public ModelHat(ModelBiped biped, List<String> str)
		{
			this.str = str;
			this.biped = biped;
			model = new ModelRenderer[str.size()];
			for(int i = 0; i < str.size(); i++)
			{

				String[] str2 = str.get(i).split(":");
				ModelRenderEntry entry = new ModelRenderEntry(Integer.valueOf(str2[0]), Integer.valueOf(str2[1]), Float.valueOf(str2[2]), Float.valueOf(str2[3]), Float.valueOf(str2[4]), Integer.valueOf(str2[5]), Integer.valueOf(str2[6]), Integer.valueOf(str2[7]), Float.valueOf(str2[8]), Float.valueOf(str2[9]), Float.valueOf(str2[10]), Integer.valueOf(str2[11]), Integer.valueOf(str2[12]), Boolean.valueOf(str2[13]), Boolean.valueOf(str2[14]), Integer.valueOf(str2[15]), Integer.valueOf(str2[16]), Float.valueOf(str2[17]), Integer.valueOf(str2[18]));

				textureWidth = entry.texture()[2];
				textureHeight = entry.texture()[3];
				model[i] = new ModelRenderer(this, entry.texture()[0], entry.texture()[1]);
				model[i].addBox(entry.getBox().posX(), entry.getBox().posY(), entry.getBox().posZ(), entry.getBox().dimX(), entry.getBox().dimY(), entry.getBox().dimZ());
				model[i].setRotationPoint(entry.rotation()[0], entry.rotation()[1], entry.rotation()[2]);
				model[i].setTextureSize(entry.texture()[2], entry.texture()[3]);
				model[i].mirror = entry.mirror();
			}
		}

		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
		{
			super.render(entity, f, f1, f2, f3, f4, f5);
			setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			for(int i = 0; i < model.length; i++)
			{
				model[i].render(f5);
			}
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
		{
			super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);

			for(int i = 0; i < str.size(); i++)
			{

				String[] str2 = str.get(i).split(":");
				ModelRenderEntry entry = new ModelRenderEntry(Integer.valueOf(str2[0]), Integer.valueOf(str2[1]), Float.valueOf(str2[2]), Float.valueOf(str2[3]), Float.valueOf(str2[4]), Integer.valueOf(str2[5]), Integer.valueOf(str2[6]), Integer.valueOf(str2[7]), Float.valueOf(str2[8]), Float.valueOf(str2[9]), Float.valueOf(str2[10]), Integer.valueOf(str2[11]), Integer.valueOf(str2[12]), Boolean.valueOf(str2[13]), Boolean.valueOf(str2[14]), Integer.valueOf(str2[15]), Integer.valueOf(str2[16]), Float.valueOf(str2[17]), Integer.valueOf(str2[18]));

				if(entry.getModelRota().followHead())
				{
					model[i].rotateAngleX = this.biped.bipedHead.rotateAngleX;
					model[i].rotateAngleY = this.biped.bipedHead.rotateAngleY;
				}
				else
				{
					switch(entry.getModelRota().var())
					{
					case 1:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					case 2:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f1 + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f1 + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f1 + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f1 * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f1 * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f1 * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					case 3:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f2 + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f2 + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f2 + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f2 * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f2 * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f2 * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					case 4:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f3 + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f3 + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f3 + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f3 * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f3 * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f3 * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					case 5:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f4 + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f4 + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f4 + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f4 * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f4 * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f4 * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					case 6:
					{
						switch(entry.getModelRota().operation())
						{
						case 1:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f5 + entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f5 + entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f5 + entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						case 2:
						{
							switch(entry.getModelRota().axe())
							{
							case 1:
							{
								model[i].rotateAngleX = f5 * entry.getModelRota().value();
								break;
							}
							case 2:
							{
								model[i].rotateAngleY = f5 * entry.getModelRota().value();
								break;
							}
							case 3:
							{
								model[i].rotateAngleZ = f5 * entry.getModelRota().value();
								break;
							}
							}
							break;
						}
						}
						break;
					}
					}
				}
			}
		}
	}
}