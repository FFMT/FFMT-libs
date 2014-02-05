package fr.minecraftforgefrance.ffmtlibs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class FFMTPlayerTracker //implements IPlayerTracker
{
	public final String outdatedMod;
	public final String lastestVersion;
	public final String downloadURL;
	public boolean hassay = false;

	public FFMTPlayerTracker(String modid, String last, String download)
	{
		outdatedMod = modid;
		lastestVersion = last;
		downloadURL = download;
	}

	//@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if(!hassay)
		{
			player.addChatMessage(new ChatComponentText(String.format(StatCollector.translateToLocal("update.available"), outdatedMod, lastestVersion)));
			player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("update.download") + " " + EnumChatFormatting.BLUE + downloadURL));
			hassay = true;
		}
	}

	//@Override
	public void onPlayerLogout(EntityPlayer player)
	{}

	//@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{}

	//@Override
	public void onPlayerRespawn(EntityPlayer player)
	{}
}
