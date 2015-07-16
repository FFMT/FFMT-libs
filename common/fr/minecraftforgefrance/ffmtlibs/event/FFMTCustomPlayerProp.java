package fr.minecraftforgefrance.ffmtlibs.event;

import java.awt.image.BufferedImage;
import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class FFMTCustomPlayerProp implements IExtendedEntityProperties
{
    public static final String ENTITY_PROP_NAME = "FFMTCustomPlayerProp";

    public ResourceLocation locationHat;
    public ThreadDownloadImageData downloadImageHat;
    public ThreadDowloadTextData downloadParticle;
    public ThreadDowloadTextData model;
    public EnumParticleTypes[] particules;

    @Override
    public void saveNBTData(NBTTagCompound compound)
    {

    }

    @Override
    public void loadNBTData(NBTTagCompound compound)
    {

    }

    @Override
    public void init(Entity entity, World world)
    {}

    public static final FFMTCustomPlayerProp get(EntityPlayer player)
    {
        return (FFMTCustomPlayerProp)player.getExtendedProperties(ENTITY_PROP_NAME);
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
                System.out.println(this.downloadParticle.getValue().get(i));
                this.particules[i] = this.getParticuleByName(this.downloadParticle.getValue().get(i));
            }
        }
        return this.particules;
    }

    private EnumParticleTypes getParticuleByName(String name)
    {
        for(int i = 0; i < EnumParticleTypes.getParticleNames().length; i++)
        {
            if(EnumParticleTypes.getParticleNames()[i].equals(name))
            {
                return EnumParticleTypes.getParticleFromId(i);
            }
        }
        return null;
    }
}