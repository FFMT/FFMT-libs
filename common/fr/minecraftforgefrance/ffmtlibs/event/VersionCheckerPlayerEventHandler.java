package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.util.ChatComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class VersionCheckerPlayerEventHandler
{
	public final String outdatedMod;
	public final String lastestVersion;
	public final String downloadURL;
	public boolean hasSay = false;

	public VersionCheckerPlayerEventHandler(String modid, String last, String download)
	{
		outdatedMod = modid;
		lastestVersion = last;
		downloadURL = download;
	}

	@SubscribeEvent
	public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
	{
		if(!hasSay)
		{
			event.player.addChatMessage(new ChatComponentTranslation("update.available", outdatedMod, lastestVersion));
			event.player.addChatMessage(new ChatComponentTranslation("update.download", downloadURL));
			hasSay = true;
		}
	}
}