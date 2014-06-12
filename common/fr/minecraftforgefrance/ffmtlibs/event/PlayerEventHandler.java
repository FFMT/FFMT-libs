package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.event.entity.EntityEvent;
import cpw.mods.fml.common.ObfuscationReflectionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.minecraftforgefrance.ffmtlibs.FFMTVersionChecker;

@SideOnly(Side.CLIENT)
public class PlayerEventHandler
{
	@SubscribeEvent
	public void onPlayerRender(RenderPlayerEvent.Specials.Pre event)
	{
		if(event.entityPlayer.getCommandSenderName() != null && !event.entityPlayer.getCommandSenderName().isEmpty())
		{
			FFMTCustomPlayerProp player = FFMTCustomPlayerProp.get(event.entityPlayer);
			if(player.particle == null)
			{
				player.particle = player.getDownloadListHat(event.entityPlayer.getCommandSenderName());
			}
			if(player.downloadImageHat == null)
			{
				player.downloadImageHat = player.getDownloadImageHat(player.getLocationHat(event.entityPlayer.getCommandSenderName()), event.entityPlayer.getCommandSenderName());
			}
			else if(player.getTextureHat().isTextureUploaded())
			{
				Minecraft.getMinecraft().renderEngine.bindTexture(player.getLocationHat(event.entityPlayer.getCommandSenderName()));
				ModelBiped biped = ObfuscationReflectionHelper.getPrivateValue(RenderPlayer.class, event.renderer, 1);
				ModelHat hat = new ModelHat(biped);

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
				if(player.particle != null && !player.particle.isEmpty())
				{
					for(String particles : player.particle)
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
		ModelRenderer hatBase;
		ModelRenderer hatSide1;
		ModelRenderer hatSide2;
		ModelRenderer hatSide3;
		ModelRenderer hatSide4;
		ModelRenderer chemine;
		ModelRenderer text;
		ModelBiped biped;

		public ModelHat(ModelBiped biped)
		{
			this.biped = biped;
			textureWidth = 256;
			textureHeight = 128;

			hatBase = new ModelRenderer(this, 0, 0);
			hatBase.addBox(-11F, -18F, -11F, 22, 2, 22);
			hatBase.setRotationPoint(0F, 0F, 0F);
			hatBase.setTextureSize(256, 128);
			hatBase.mirror = true;
			hatSide1 = new ModelRenderer(this, 0, 24);
			hatSide1.addBox(-9F, -38F, -9F, 1, 20, 18);
			hatSide1.setRotationPoint(0F, 0F, 0F);
			hatSide1.setTextureSize(256, 128);
			hatSide1.mirror = true;
			hatSide2 = new ModelRenderer(this, 0, 62);
			hatSide2.addBox(8F, -38F, -9F, 1, 20, 18);
			hatSide2.setRotationPoint(0F, 0F, 0F);
			hatSide2.setTextureSize(256, 128);
			hatSide2.mirror = true;
			hatSide3 = new ModelRenderer(this, 38, 24);
			hatSide3.addBox(-8F, -38F, -9F, 16, 20, 1);
			hatSide3.setRotationPoint(0F, 0F, 0F);
			hatSide3.setTextureSize(256, 128);
			hatSide3.mirror = true;
			hatSide4 = new ModelRenderer(this, 38, 45);
			hatSide4.addBox(-8F, -38F, 8F, 16, 20, 1);
			hatSide4.setRotationPoint(0F, 0F, 0F);
			hatSide4.setTextureSize(256, 128);
			hatSide4.mirror = true;
			chemine = new ModelRenderer(this, 71, 0);
			chemine.addBox(-3F, -31F, -3F, 6, 13, 6);
			chemine.setRotationPoint(0F, 0F, 0F);
			chemine.setTextureSize(256, 128);
			chemine.mirror = true;
			text = new ModelRenderer(this, 96, 82);
			text.addBox(-20F, -26F, -20F, 40, 6, 40);
			text.setRotationPoint(0F, 0F, 0F);
			text.setTextureSize(256, 128);
			text.mirror = true;
		}

		public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
		{
			super.render(entity, f, f1, f2, f3, f4, f5);
			setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			hatBase.render(f5);
			hatSide1.render(f5);
			hatSide2.render(f5);
			hatSide3.render(f5);
			hatSide4.render(f5);
			chemine.render(f5);
			text.render(f5);
		}

		public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
		{
			super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
			this.hatBase.rotateAngleX = this.hatSide1.rotateAngleX = this.hatSide2.rotateAngleX = this.hatSide3.rotateAngleX = this.hatSide4.rotateAngleX = this.chemine.rotateAngleX = this.biped.bipedHead.rotateAngleX;
			this.hatBase.rotateAngleY = this.hatSide1.rotateAngleY = this.hatSide2.rotateAngleY = this.hatSide3.rotateAngleY = this.hatSide4.rotateAngleY = this.chemine.rotateAngleY = this.biped.bipedHead.rotateAngleY;

			this.text.rotateAngleY += f2 / 16;

			this.hatBase.rotationPointY = this.hatSide1.rotationPointY = this.hatSide2.rotationPointY = this.hatSide3.rotationPointY = this.hatSide4.rotationPointY = this.chemine.rotationPointY = this.biped.bipedHead.rotationPointY;

		}
	}
}
