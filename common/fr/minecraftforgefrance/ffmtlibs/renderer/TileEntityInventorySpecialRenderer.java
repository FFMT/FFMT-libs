package fr.minecraftforgefrance.ffmtlibs.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityInventorySpecialRenderer extends TileEntitySpecialRenderer implements ITESRInventoryRenderer
{
	public TileEntityInventorySpecialRenderer()
	{
		this.setTileEntityRenderer(TileEntityRenderer.instance);
	}

	@Override
	public abstract void renderInventory(double x, double y, double z);

	@Override
	public abstract void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f);
}