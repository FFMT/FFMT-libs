package fr.minecraftforgefrance.ffmtlibs.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;

public class ItemFFMTSword extends ItemSword
{
	private final Item repairItem;

	public ItemFFMTSword(ToolMaterial material, Item repair)
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