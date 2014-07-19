package fr.minecraftforgefrance.ffmtlibs.config;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

public class ConfigEventHandler
{
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equals("ffmtlibs"))
		{
			FFMTLibs.syncConfig();
		}
	}
}