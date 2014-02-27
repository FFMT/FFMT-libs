package fr.minecraftforgefrance.ffmtlibs;

import net.minecraftforge.common.MinecraftForge;

import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import fr.minecraftforgefrance.ffmtlibs.blockhelper.EntityFFMTBlockSittable;
import fr.minecraftforgefrance.ffmtlibs.event.FFMTCapePlayerEventHandler;
import fr.minecraftforgefrance.ffmtlibs.renderer.TESRInventoryRenderHandler;

@Mod(modid = "FFMTLIBS", name = "FFMT Library", version = "@VERSION@", useMetadata = true)
/**
 * @authors kevin_68, elias54, robin4002
 */
public class FFMTLibs
{
	public static Logger FFMTlog;
	public static FFMTColor getColor = new FFMTColor();

	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		FFMTlog = event.getModLog();
		FFMTRegistry.registerVersionCheck("http://dl.mcnanotech.fr/FFMT/libs/version.txt", "http://ci.mcnanotech.fr/job/FFMT-libs/", "FFMT Library", "@VERSION@");
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		EntityRegistry.registerModEntity(EntityFFMTBlockSittable.class, "EntityFFMTBlockSittable", 1, this, 500, 5, false);
		if(event.getSide().isClient())
		{
			FFMTClientRegistry.tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new TESRInventoryRenderHandler());

			MinecraftForge.EVENT_BUS.register(new FFMTCapePlayerEventHandler());
		}
	}
}