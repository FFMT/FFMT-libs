package fr.minecraftforgefrance.ffmtlibs;

import java.awt.image.BufferedImage;
import java.io.File;

import fr.minecraftforgefrance.ffmtlibs.event.ThreadDowloadTextData;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

public class FFMTCapabilityProvider implements ICapabilityProvider, IFFMTCapability
{
    public ResourceLocation locationHat;
    public ThreadDownloadImageData downloadImageHat;
    public ThreadDowloadTextData downloadParticle;
    public ThreadDowloadTextData model;
    public EnumParticleTypes[] particules;

    @Override
    public boolean hasCapability(Capability<?> capability, EnumFacing facing)
    {
        return FFMTLibs.TEST_CAP != null && capability == FFMTLibs.TEST_CAP;
    }

    @Override
    public <T> T getCapability(Capability<T> capability, EnumFacing facing)
    {
        if(FFMTLibs.TEST_CAP != null && capability == FFMTLibs.TEST_CAP)
        {
            return (T)this;
        }
        return null;
    }

    public ThreadDownloadImageData getTextureHat()
    {
        return this.downloadImageHat;
    }

    public ThreadDowloadTextData getDownloadListHat(String uuid)
    {
        return new ThreadDowloadTextData(this.getHatInfoUrl(uuid));
    }

    public ThreadDowloadTextData getDownloadListModelHat(String uuid)
    {
        return new ThreadDowloadTextData(this.getHatModelUrl(uuid));
    }

    public ThreadDownloadImageData getDownloadImageHat(ResourceLocation resourceLocation, String uuid)
    {
        TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
        Object object = texturemanager.getTexture(resourceLocation);

        if(object == null)
        {
            object = new ThreadDownloadImageData((File)null, String.format("http://files.minecraftforgefrance.fr/hats/%s.png", StringUtils.stripControlCodes(uuid)), null, new ImageBufferDownload()
            {
                @Override
                public BufferedImage parseUserSkin(BufferedImage image)
                {
                    return image;
                }
            });
            texturemanager.loadTexture(resourceLocation, (ITextureObject)object);
        }

        return (ThreadDownloadImageData)object;
    }

    public String getHatInfoUrl(String uuid)
    {
        return String.format("http://files.minecraftforgefrance.fr/hats/%s.txt", new Object[] {StringUtils.stripControlCodes(uuid)});
    }

    public String getHatModelUrl(String uuid)
    {
        return String.format("http://files.minecraftforgefrance.fr/hats/%s_model.txt", new Object[] {StringUtils.stripControlCodes(uuid)});
    }

    public ResourceLocation getLocationHat(String uuid)
    {
        return new ResourceLocation("ffmtlibs", "hats/" + StringUtils.stripControlCodes(uuid));
    }

    public EnumParticleTypes[] getParticules()
    {
        if(this.particules == null)
        {
            this.particules = new EnumParticleTypes[this.downloadParticle.getValue().size()];
            for(int i = 0; i < this.downloadParticle.getValue().size(); i++)
            {
                this.particules[i] = this.getParticuleByName(this.downloadParticle.getValue().get(i));
            }
        }
        return this.particules;
    }

    private EnumParticleTypes getParticuleByName(String name)
    {
        for(int i = 0; i < EnumParticleTypes.values().length; i++)
        {
            if(EnumParticleTypes.values()[i].getParticleName().equals(name))
            {
                return EnumParticleTypes.getParticleFromId(i);
            }
        }
        return null;
    }
}
