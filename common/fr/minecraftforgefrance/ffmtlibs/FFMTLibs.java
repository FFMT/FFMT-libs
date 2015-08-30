package fr.minecraftforgefrance.ffmtlibs;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.minecraftforgefrance.ffmtlibs.config.ConfigEventHandler;
import fr.minecraftforgefrance.ffmtlibs.entity.EntityBlockSittable;
import fr.minecraftforgefrance.ffmtlibs.event.PlayerEventHandler;
import fr.minecraftforgefrance.ffmtlibs.render.LayerHat;

@Mod(modid = "ffmtlibs", name = "FFMT Library", version = "@VERSION@", guiFactory = "fr.minecraftforgefrance.ffmtlibs.config.FFMTGuiConfigFactory", acceptableRemoteVersions = "*")
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
            MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
            FMLCommonHandler.instance().bus().register(new ConfigEventHandler());
            this.addHatLayer();
        }
    }

    @SideOnly(Side.CLIENT)
    private void addHatLayer()
    {
        for(RenderPlayer renderPlayer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values())
        {
            LayerHat hat = new LayerHat(renderPlayer);
            renderPlayer.addLayer(hat);
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