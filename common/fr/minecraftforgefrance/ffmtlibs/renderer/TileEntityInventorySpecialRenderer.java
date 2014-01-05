package fr.minecraftforgefrance.ffmtlibs.renderer;

import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityInventorySpecialRenderer extends TileEntitySpecialRenderer implements ITESRInventoryRenderer
{
	public TileEntityInventorySpecialRenderer()
	{
		this.func_147497_a(TileEntityRendererDispatcher.field_147556_a);
	}

	@Override
	public abstract void renderInventory(double x, double y, double z);

	@Override
	public abstract void func_147500_a(TileEntity tileentity, double d0, double d1, double d2, float f);
}