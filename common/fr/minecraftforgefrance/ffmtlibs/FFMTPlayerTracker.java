package fr.minecraftforgefrance.ffmtlibs;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StatCollector;
import cpw.mods.fml.common.IPlayerTracker;

public class FFMTPlayerTracker implements IPlayerTracker
{
	public final String outdatedmod;
	public final String lastversion;
	public final String downloadurl;
	public boolean hassay = false;

	public FFMTPlayerTracker(String modid, String last, String download)
	{
		outdatedmod = modid;
		lastversion = last;
		downloadurl = download;
	}

	@Override
	public void onPlayerLogin(EntityPlayer player)
	{
		if(!hassay)
		{
			player.addChatMessage(String.format(StatCollector.translateToLocal("update.available"), outdatedmod, lastversion));
			player.addChatMessage(StatCollector.translateToLocal("update.download") + " " + EnumChatFormatting.BLUE + downloadurl);
			hassay = true;
		}
	}

	@Override
	public void onPlayerLogout(EntityPlayer player)
	{}

	@Override
	public void onPlayerChangedDimension(EntityPlayer player)
	{}

	@Override
	public void onPlayerRespawn(EntityPlayer player)
	{}
}