package fr.mcnanotech.FFMT.FFMTAPI;

import java.util.Arrays;
import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "FFMTAPI", name = "FFMT API", version = "1.0.0", useMetadata = true)
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class FFMTAPI 
{
	public static Logger FFMTlog;
	
	@PreInit
	public void preload(FMLPreInitializationEvent event)
	{
		FFMTlog = event.getModLog();
		ModMetadata meta = event.getModMetadata();
		meta.modId       = "FFMTAPI";
		meta.name        = "FFMT API";
		meta.version     = "1.0.0";
		meta.authorList  = Arrays.asList("kevin_68", "robin4002", "elias54");
		meta.description = "simplify your coder life";
		meta.url         = "http://forge.mcnanotech.fr/";
		meta.screenshots = new String[0];
		meta.logoFile    = "/ffmt_logo.png";
		
	}
	
}
