package fr.minecraftforgefrance.ffmtlibs.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;

public class ItemFFMTHoe extends ItemHoe
{
	private final Item repairItem;

	public ItemFFMTHoe(ToolMaterial material, Item repair)
	{
		super(material);
		this.repairItem = repair;
	}

	public boolean getIsRepairable(ItemStack input, ItemStack repair)
	{
		if(this.repairItem != null && repair.getItem() == this.repairItem)
		{
			return true;
		}
		return false;
	}
}