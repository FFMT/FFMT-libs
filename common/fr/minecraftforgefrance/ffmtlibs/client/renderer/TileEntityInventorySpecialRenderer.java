package fr.minecraftforgefrance.ffmtlibs.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityInventorySpecialRenderer extends TileEntitySpecialRenderer implements ITESRInventoryRenderer
{
	public TileEntityInventorySpecialRenderer()
	{
		this.func_147497_a(TileEntityRendererDispatcher.instance);
	}

	@Override
	public abstract void renderInventory(double x, double y, double z);

	@Override
	public abstract void renderTileEntityAt(TileEntity tile, double x, double y, double z, float tick);
}