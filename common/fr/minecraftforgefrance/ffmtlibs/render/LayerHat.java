package fr.minecraftforgefrance.ffmtlibs.render;

import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumParticleTypes;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.minecraftforge.fml.common.Loader;
import fr.minecraftforgefrance.ffmtlibs.event.FFMTCustomPlayerProp;

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
            this.renderHat((EntityPlayer)entityliving, FFMTCustomPlayerProp.get((EntityPlayer)entityliving), partialTicks, scale, f1, f1, f2, f3, f4);
        }
    }

    public void renderHat(EntityPlayer player, FFMTCustomPlayerProp prop, float partialTicks, float scale, float f, float f1, float f2, float f3, float f4)
    {
        if(Loader.isModLoaded("nhg"))
        {
            IExtendedEntityProperties nhgprop = player.getExtendedProperties("nhg_prop");
            try
            {
                Method m = nhgprop.getClass().getMethod("renderHat");
                boolean shouldRender = (Boolean)m.invoke(nhgprop);
                if(!shouldRender)
                {
                    return;
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        if(prop.downloadImageHat == null)
        {
            prop.downloadImageHat = prop.getDownloadImageHat(prop.getLocationHat(player.getUniqueID().toString()), player.getUniqueID().toString());
        }
        if(prop.downloadParticle == null)
        {
            prop.downloadParticle = prop.getDownloadListHat(player.getUniqueID().toString());
        }
        if(prop.model == null)
        {
            prop.model = prop.getDownloadListModelHat(player.getUniqueID().toString());
        }
        if(!player.isInvisible() && prop.getLocationHat(player.getGameProfile().getId().toString()) != null && prop.downloadParticle.isTextDownloaded() && prop.model.isTextDownloaded())
        {
            GlStateManager.pushMatrix();
            Minecraft.getMinecraft().renderEngine.bindTexture(prop.getLocationHat(player.getGameProfile().getId().toString()));
            ModelHat hat = new ModelHat(this.render.getPlayerModel(), prop.model.getValue());
            
            if(player.isSneaking())
            {
                GlStateManager.translate(0.0F, 0.3F, 0.0F);
            }

            hat.render(player, f, f1, f2, f3, f4, 0.03125F);
            if(prop.downloadParticle.getValue() != null && !prop.downloadParticle.getValue().isEmpty())
            {
                if(prop.particules == null)
                {
                    prop.getParticules();
                }
                if(!Loader.isModLoaded("nhg"))
                {
                    for(EnumParticleTypes particles : prop.particules)
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