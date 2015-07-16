package fr.minecraftforgefrance.ffmtlibs.item;

import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;

public class ItemFFMTArmor extends ItemArmor
{
	private final String modid, armorName;
	private final Item repairItem;

	public ItemFFMTArmor(ArmorMaterial material, int type, String mod, String name, Item repair)
	{
		super(material, 0, type);
		this.modid = mod;
		this.armorName = name;
		this.repairItem = repair;
	}

	@Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type)
	{
		if(slot == 2)
		{
			return this.modid + ":textures/models/armor/" + this.armorName + "_layer_2.png";
		}
		return this.modid + ":textures/models/armor/" + this.armorName + "_layer_1.png";
	}

	@Override
    public boolean getIsRepairable(ItemStack input, ItemStack repair)
	{
		if(this.repairItem != null && repair.getItem() == this.repairItem)
		{
			return true;
		}
		return false;
	}
}