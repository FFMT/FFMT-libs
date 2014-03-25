package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.util.ChatComponentTranslation;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;

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
			event.player.addChatMessage(new ChatComponentTranslation("update.available", outdatedMod, lastestVersion));
			event.player.addChatMessage(new ChatComponentTranslation("update.download", downloadURL));
			hassay = true;
		}
	}
}