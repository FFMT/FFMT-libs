package fr.mcnanotech.FFMT.FFMTAPITestMod.Entity;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import fr.mcnanotech.FFMT.FFMTAPI.FFMTColor;

public class FFMTAPIMobTest extends EntityMob
{

	public FFMTAPIMobTest(World par1World) 
	{
		super(par1World);
	}

	@Override
	public int getMaxHealth() 
	{
		return Integer.MAX_VALUE;
	}
	
    public boolean interact(EntityPlayer par1EntityPlayer)
    {
    	if (this.worldObj.isRemote)
    	{
    		message(par1EntityPlayer);
    		return true;
    	}
    	
    	return false;
    }
    
    @SideOnly(Side.CLIENT)
    public void message(EntityPlayer player)
    {
    		player.addChatMessage(FFMTColor.test());
    		player.addChatMessage(FFMTColor.obfuscated + "Obfuscated");
    		player.addChatMessage(FFMTColor.bold + "Bold");
    		player.addChatMessage(FFMTColor.strike + "Strike");
    		player.addChatMessage(FFMTColor.underline + "Underline");
    		player.addChatMessage(FFMTColor.italic + "Italic");
    }

}
