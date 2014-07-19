package fr.minecraftforgefrance.ffmtlibs;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import fr.minecraftforgefrance.ffmtlibs.block.EntityBlockSittable;
import fr.minecraftforgefrance.ffmtlibs.client.renderer.TESRInventoryRenderHandler;
import fr.minecraftforgefrance.ffmtlibs.config.ConfigEventHandler;
import fr.minecraftforgefrance.ffmtlibs.event.PlayerEventHandler;

@Mod(modid = "ffmtlibs", name = "FFMT Library", version = "@VERSION@", guiFactory = "fr.minecraftforgefrance.ffmtlibs.config.FFMTGuiConfigFactory")
/**
 * @authors kevin_68, elias54, robin4002
 */
public class FFMTLibs
{
	public static Logger ffmtLog = LogManager.getLogger("FFMTLibs");
	public static Configuration cfg;
	public static boolean hideHat = false;

	@EventHandler
	public void preload(FMLPreInitializationEvent event)
	{
		FFMTRegistry.registerVersionCheck("http://dl.mcnanotech.fr/FFMT/libs/version.txt", "http://ci.mcnanotech.fr/job/FFMT-libs/", "ffmtlibs");
		cfg = new Configuration(event.getSuggestedConfigurationFile());
		syncConfig();
	}

	@EventHandler
	public void load(FMLInitializationEvent event)
	{
		EntityRegistry.registerModEntity(EntityBlockSittable.class, "EntityFFMTBlockSittable", 1, this, 500, 5, false);
		if(event.getSide().isClient())
		{
			FFMTClientRegistry.tesrRenderId = RenderingRegistry.getNextAvailableRenderId();
			RenderingRegistry.registerBlockHandler(new TESRInventoryRenderHandler());

			MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
			FMLCommonHandler.instance().bus().register(new ConfigEventHandler());
		}
	}

	public static void syncConfig()
	{
		hideHat = cfg.getBoolean("Hide hat", Configuration.CATEGORY_GENERAL, hideHat, "Hide hat");
		if(cfg.hasChanged())
		{
			cfg.save();
		}
	}
}