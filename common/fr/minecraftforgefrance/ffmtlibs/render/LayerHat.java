package fr.minecraftforgefrance.ffmtlibs.render;

import fr.minecraftforgefrance.ffmtlibs.FFMTCapabilityProvider;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.fml.common.Loader;

public class LayerHat implements LayerRenderer
{
    private RenderPlayer render;

    public LayerHat(RenderPlayer render)
    {
        this.render = render;
    }

    @Override
    public void doRenderLayer(EntityLivingBase entityliving, float f, float f1, float partialTicks, float f2, float f3, float f4, float scale)
    {
        if(entityliving instanceof EntityPlayer)
        {
            if(entityliving.hasCapability(FFMTLibs.TEST_CAP, null))
            {
                this.renderHat((EntityPlayer)entityliving, (FFMTCapabilityProvider)entityliving.getCapability(FFMTLibs.TEST_CAP, null), partialTicks, scale, f1, f1, f2, f3, f4);
            }
        }
    }

    public void renderHat(EntityPlayer player, FFMTCapabilityProvider cap, float partialTicks, float scale, float f, float f1, float f2, float f3, float f4)
    {
        if(cap.downloadImageHat == null)
        {
            cap.downloadImageHat = cap.getDownloadImageHat(cap.getLocationHat(player.getUniqueID().toString()), player.getUniqueID().toString());
        }
        if(cap.downloadParticle == null)
        {
            cap.downloadParticle = cap.getDownloadListHat(player.getUniqueID().toString());
        }
        if(cap.model == null)
        {
            cap.model = cap.getDownloadListModelHat(player.getUniqueID().toString());
        }
        if(!player.isInvisible() && cap.getLocationHat(player.getGameProfile().getId().toString()) != null && cap.downloadParticle.isTextDownloaded() && cap.model.isTextDownloaded())
        {
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(cap.getLocationHat(player.getGameProfile().getId().toString()));
            ModelHat hat = new ModelHat(this.render.getMainModel(), cap.model.getValue());

            if(player.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.3F, 0.0F);
            }

            hat.render(player, f, f1, f2, f3, f4, 0.03125F);
            if(cap.downloadParticle.getValue() != null && !cap.downloadParticle.getValue().isEmpty())
            {
                if(cap.particules == null)
                {
                    cap.getParticules();
                }
                if(!Loader.isModLoaded("nhg"))
                {
                    for(EnumParticleTypes particles : cap.particules)
                    {
                        if(particles != null && player.worldObj.rand.nextInt(10) == 0)
                        {
                            player.worldObj.spawnParticle(particles, player.posX - 0.5F + player.worldObj.rand.nextFloat(), player.posY + 1.5F + player.worldObj.rand.nextFloat(), player.posZ - 0.5F + player.worldObj.rand.nextFloat(), 0.0F, 0.2F, 0.0F);
                        }
                    }
                }
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures()
    {
        return false;
    }
}