package fr.minecraftforgefrance.ffmtlibs.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlayerEventHandler
{
    @SubscribeEvent
    public void onEntityConstructing(EntityEvent.EntityConstructing event)
    {
        if(event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entity;
            player.registerExtendedProperties(FFMTCustomPlayerProp.ENTITY_PROP_NAME, new FFMTCustomPlayerProp());
        }
    }
}