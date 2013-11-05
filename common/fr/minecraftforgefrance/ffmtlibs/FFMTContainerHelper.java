package fr.minecraftforgefrance.ffmtlibs;

import net.minecraft.entity.player.EntityPlayer;

public class FFMTContainerHelper
{
	/**
	 * Consume a item with medatata
	 * 
	 * @param player
	 * @param itemID
	 * @param metadata
	 * @return
	 */
	public static boolean consumeItemWithMetadataInInventory(EntityPlayer player, int itemID, int metadata)
	{
		for(int j = 0; j < player.inventory.mainInventory.length; ++j)
		{
			if(player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].itemID == itemID && player.inventory.mainInventory[j].getItemDamage() == metadata)
			{
				if(--player.inventory.mainInventory[j].stackSize <= 0)
				{
					player.inventory.mainInventory[j] = null;
				}
				return true;
			}
		}
		return false;
	}
}
