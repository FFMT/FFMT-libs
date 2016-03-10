package fr.minecraftforgefrance.ffmtlibs.config;

import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;

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