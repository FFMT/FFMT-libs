package fr.minecraftforgefrance.ffmtlibs;

import java.util.logging.Logger;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "FFMTLIBS", name = "FFMT LIBS", version = "@VERSION@", useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

/**
 * @authors kevin_68, elias54, robin4002
 */
public class FFMTLIBS 
{
	public static Logger FFMTlog;
	public static FFMTColor getColor = new FFMTColor();
	
	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		FFMTlog = FMLLog.getLogger();
		event.getModMetadata().version = "@VERSION@";
		
		FFMTRegistry.registerVersionCheck(event, "http://dl.mcnanotech.fr/FFMT/API/download/version.txt", "http://dl.mcnanotech.fr/FFMT/API/download/", "FFMT API", "@VERSION@");
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event)
	{

	}
	
}
