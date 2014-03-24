package fr.minecraftforgefrance.ffmtlibs.itemhelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;

public class ItemFFMTAxe extends ItemAxe
{
	private final Item repairItem;

	public ItemFFMTAxe(ToolMaterial material, Item repair)
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