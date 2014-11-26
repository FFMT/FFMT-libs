package fr.minecraftforgefrance.ffmtlibs.client.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;

public abstract class TileEntityInventorySpecialRenderer extends TileEntitySpecialRenderer implements ITESRInventoryRenderer
{
	public TileEntityInventorySpecialRenderer()
	{
		this.setRendererDispatcher(TileEntityRendererDispatcher.instance);
	}
}