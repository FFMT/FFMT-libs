package fr.minecraftforgefrance.ffmtlibs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.minecraftforgefrance.ffmtlibs.config.ConfigEventHandler;
import fr.minecraftforgefrance.ffmtlibs.entity.EntityBlockSittable;
import fr.minecraftforgefrance.ffmtlibs.event.PlayerEventHandler;
import fr.minecraftforgefrance.ffmtlibs.render.LayerHat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = "ffmtlibs", name = "FFMT Library", version = "@VERSION@", dependencies = "required-after:forge@[13.19.1,)", guiFactory = "fr.minecraftforgefrance.ffmtlibs.config.FFMTGuiConfigFactory", acceptableRemoteVersions = "*", updateJSON = "http://dl.mcnanotech.fr/FFMT/libs/version.json")
/**
 * @authors kevin_68, elias54, robin4002
 */
public class FFMTLibs
{
    @CapabilityInject(IFFMTCapability.class)
    public static final Capability<IFFMTCapability> TEST_CAP = null;

    public class Storage implements IStorage<IFFMTCapability>
    {
        @Override
        public NBTBase writeNBT(Capability<IFFMTCapability> capability, IFFMTCapability instance, EnumFacing side)
        {
            return null;
        }

        @Override
        public void readNBT(Capability<IFFMTCapability> capability, IFFMTCapability instance, EnumFacing side, NBTBase nbt)
        {

        }
    }

    public static Logger ffmtLog = LogManager.getLogger("FFMTLibs");
    public static Configuration cfg;
    public static boolean hideHat = false;

    @EventHandler
    public void preload(FMLPreInitializationEvent event)
    {
        cfg = new Configuration(event.getSuggestedConfigurationFile());
        syncConfig();
        CapabilityManager.INSTANCE.register(IFFMTCapability.class, new Storage(), FFMTCapabilityProvider.class);
    }

    @EventHandler
    public void load(FMLInitializationEvent event)
    {
        EntityRegistry.registerModEntity(new ResourceLocation("ffmtlibs", "EntityFFMTBlockSittable"), EntityBlockSittable.class, "EntityFFMTBlockSittable", 1, this, 500, 5, false);
        if(event.getSide().isClient())
        {
            MinecraftForge.EVENT_BUS.register(new PlayerEventHandler());
            MinecraftForge.EVENT_BUS.register(new ConfigEventHandler());
            this.addHatLayer();
        }
    }

    @SideOnly(Side.CLIENT)
    private void addHatLayer()
    {
        for(RenderPlayer renderPlayer : Minecraft.getMinecraft().getRenderManager().getSkinMap().values())
        {
            renderPlayer.addLayer(new LayerHat(renderPlayer));
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