package fr.mcnanotech.FFMT.FFMTAPI;

import java.util.logging.Logger;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;

@Mod(modid = "FFMTAPI", name = "FFMT API", version = "1.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class FFMTAPI
{
	public static Logger FFMTlog;
	
	@PreInit
	public void preload(FMLPreInitializationEvent event)
	{
		FFMTlog = event.getModLog();
	}

}
