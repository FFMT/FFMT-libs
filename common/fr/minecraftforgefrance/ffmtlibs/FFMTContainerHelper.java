package fr.minecraftforgefrance.ffmtlibs;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;

public class FFMTContainerHelper
{
	/**
	 * Consume a item with medatata
	 * 
	 * @param player
	 * @param item
	 * @param metadata
	 * @return
	 */
	public static boolean consumeItemWithMetadataInInventory(EntityPlayer player, Item item, int metadata)
	{
		for(int j = 0; j < player.inventory.mainInventory.length; ++j)
		{
			if(player.inventory.mainInventory[j] != null && player.inventory.mainInventory[j].getItem().equals(item) && player.inventory.mainInventory[j].getItemDamage() == metadata)
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
	
	/**
	 * Consume a block with medatata
	 * 
	 * @param player
	 * @param block
	 * @param metadata
	 * @return
	 */
	public static boolean consumeItemWithMetadataInInventory(EntityPlayer player, Block block, int metadata)
	{
		return consumeItemWithMetadataInInventory(player, Item.getItemFromBlock(block), metadata);
	}
}
