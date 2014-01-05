package fr.minecraftforgefrance.ffmtlibs;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;

public class FFMTPlayerTracker 
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

	@SubscribeEvent
	public void PlayerLoggedInEvent(EntityPlayer player)
	{
		if(!hassay)
		{
			player.func_145747_a(new ChatComponentText((String.format(StatCollector.translateToLocal("update.available"), outdatedMod, lastestVersion))));
			player.func_145747_a(new ChatComponentText(StatCollector.translateToLocal("update.download") + " " + EnumChatFormatting.BLUE + downloadURL));
			hassay = true;
		}
	}

}
