package fr.minecraftforgefrance.ffmtlibs;

import fr.minecraftforgefrance.ffmtlibs.event.ThreadDowloadTextData;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ResourceLocation;

public interface IFFMTCapability
{
    ThreadDownloadImageData getTextureHat();

    ThreadDowloadTextData getDownloadListHat(String uuid);

    ThreadDowloadTextData getDownloadListModelHat(String uuid);

    ThreadDownloadImageData getDownloadImageHat(ResourceLocation resourceLocation, String uuid);

    String getHatInfoUrl(String uuid);

    String getHatModelUrl(String uuid);

    ResourceLocation getLocationHat(String uuid);

    EnumParticleTypes[] getParticules();
}