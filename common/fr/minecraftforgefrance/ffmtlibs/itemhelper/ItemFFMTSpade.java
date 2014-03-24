package fr.minecraftforgefrance.ffmtlibs.itemhelper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;

public class ItemFFMTSpade extends ItemSpade
{
	private final Item repairItem;

	public ItemFFMTSpade(ToolMaterial  material, Item repair)
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