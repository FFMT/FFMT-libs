package fr.minecraftforgefrance.ffmtlibs.renderer;

import net.minecraft.block.Block;

public class InventoryTESRIndex
{
	public final Block block;
	public final int metadata;

	public InventoryTESRIndex(Block block, int metadata)
	{
		this.block = block;
		this.metadata = metadata;
	}
	
	@Override
	public int hashCode()
	{
		return block.hashCode() + this.metadata;
	}

	@Override
	public boolean equals(Object o)
	{
		if(!(o instanceof InventoryTESRIndex))
			return false;

		InventoryTESRIndex tesr = (InventoryTESRIndex)o;

		return tesr.block == this.block && tesr.metadata == this.metadata;
	}

	public Block getBlock()
	{
		return block;
	}

	public int getBlockMetadata()
	{
		return metadata;
	}
}