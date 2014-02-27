package fr.minecraftforgefrance.ffmtlibs.event;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class FFMTVersionCheckPlayerEventHandler
{
	public final String outdatedMod;
	public final String lastestVersion;
	public final String downloadURL;
	public boolean hassay = false;

	public FFMTVersionCheckPlayerEventHandler(String modid, String last, String download)
	{
		outdatedMod = modid;
		lastestVersion = last;
		downloadURL = download;
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(!hassay)
		{
			event.player.addChatMessage(new ChatComponentText(String.format(StatCollector.translateToLocal("update.available"), outdatedMod, lastestVersion)));
			event.player.addChatMessage(new ChatComponentText(StatCollector.translateToLocal("update.download") + " " + EnumChatFormatting.BLUE + downloadURL));
			hassay = true;
		}
	}
}