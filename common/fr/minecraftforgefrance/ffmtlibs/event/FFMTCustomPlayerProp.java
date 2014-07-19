package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class FFMTCustomPlayerProp implements IExtendedEntityProperties
{
	public static final String ENTITY_PROP_NAME = "FFMTCustomPlayerProp";
	private final EntityPlayer player;

	public ResourceLocation locationHat;
	public ThreadDownloadImageData downloadImageHat;
	public ThreadDowloadTextData particle;
	public ThreadDowloadTextData model;

	public FFMTCustomPlayerProp(EntityPlayer player)
	{
		this.player = player;
	}

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
	{

	}

	public static final FFMTCustomPlayerProp get(EntityPlayer player)
	{
		return (FFMTCustomPlayerProp)player.getExtendedProperties(ENTITY_PROP_NAME);
	}

	public ThreadDownloadImageData getTextureHat()
	{
		return this.downloadImageHat;
	}

	public ThreadDowloadTextData getDownloadListHat(String playerName)
	{
		return new ThreadDowloadTextData(this.getHatInfoUrl(playerName));
	}

	public ThreadDowloadTextData getDownloadListModelHat(String playerName)
	{
		return new ThreadDowloadTextData(this.getHatModelUrl(playerName));
	}

	public ThreadDownloadImageData getDownloadImageHat(ResourceLocation resourceLocation, String playerName)
	{
		return getDownloadImage(resourceLocation, getHatUrl(playerName), (ResourceLocation)null, (IImageBuffer)null);
	}

	private ThreadDownloadImageData getDownloadImage(ResourceLocation res, String link, ResourceLocation defRes, IImageBuffer image)
	{
		TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
		Object object = texturemanager.getTexture(res);

		if(object == null)
		{
			object = new ThreadDownloadImageData(link, defRes, image);
			texturemanager.loadTexture(res, (ITextureObject)object);
		}

		return (ThreadDownloadImageData)object;
	}

	public String getHatUrl(String playerName)
	{
		return String.format("http://files.minecraftforgefrance.fr/hats/%s.png", new Object[] {StringUtils.stripControlCodes(playerName)});
	}

	public String getHatInfoUrl(String playerName)
	{
		return String.format("http://files.minecraftforgefrance.fr/hats/%s.txt", new Object[] {StringUtils.stripControlCodes(playerName)});
	}

	public String getHatModelUrl(String playerName)
	{
		return String.format("http://files.minecraftforgefrance.fr/hats/%s_model.txt", new Object[] {StringUtils.stripControlCodes(playerName)});
	}

	public ResourceLocation getLocationHat(String playerName)
	{
		return new ResourceLocation("ffmtlibs" + "hats/" + StringUtils.stripControlCodes(playerName));
	}
}