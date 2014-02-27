package fr.minecraftforgefrance.ffmtlibs.renderer;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.world.IBlockAccess;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import fr.minecraftforgefrance.ffmtlibs.FFMTClientRegistry;

public class TESRInventoryRenderHandler implements ISimpleBlockRenderingHandler
{
	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID, RenderBlocks renderer)
	{
		InventoryTESRIndex index = new InventoryTESRIndex(block, metadata);
		if(FFMTClientRegistry.blockByTESR.containsKey(index))
		{
			FFMTClientRegistry.blockByTESR.get(index).renderInventory(-0.5, -0.5, -0.5);
		}
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z, Block block, int modelId, RenderBlocks renderer)
	{
		return true;
	}

	@Override
	public boolean shouldRender3DInInventory(int modelId)
	{
		return true;
	}

	@Override
	public int getRenderId()
	{
		return FFMTClientRegistry.tesrRenderId;
	}
}