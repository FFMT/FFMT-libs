package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class VersionCheckerPlayerEventHandler
{
    public final String outdatedMod;
    public final String lastestVersion;
    public final String downloadURL;
    public boolean hasSay = false;

    public VersionCheckerPlayerEventHandler(String modid, String last, String download)
    {
        this.outdatedMod = modid;
        this.lastestVersion = last;
        this.downloadURL = download;
    }

    @SubscribeEvent
    public void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event)
    {
        if(!this.hasSay)
        {
            event.player.sendMessage(new TextComponentTranslation("update.available", this.outdatedMod, this.lastestVersion));
            event.player.sendMessage(new TextComponentTranslation("update.download", this.downloadURL));
            this.hasSay = true;
        }
    }
}