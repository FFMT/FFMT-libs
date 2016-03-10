package fr.minecraftforgefrance.ffmtlibs.event;

import fr.minecraftforgefrance.ffmtlibs.FFMTCapabilityProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerEventHandler
{
    @SubscribeEvent
    public void onCapabilityAttachPlayer(AttachCapabilitiesEvent.Entity event)
    {
        if(event.getEntity() instanceof EntityPlayer)
        {
            event.addCapability(new ResourceLocation("ffmtlibs:ffmtcaps"), new FFMTCapabilityProvider());
        }
    }
}