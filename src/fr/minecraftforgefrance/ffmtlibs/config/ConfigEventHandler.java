package fr.minecraftforgefrance.ffmtlibs.config;

import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

public class ConfigEventHandler
{
	public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.getModID().equals("ffmtlibs"))
		{
			FFMTLibs.syncConfig();
		}
	}
}