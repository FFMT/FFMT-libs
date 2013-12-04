package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderForContainer extends GuiButton
{
	public float sladerValue;
	public boolean dragging;
	private int sladerId;
	private GuiContainerSliderBase container;

	public GuiSliderForContainer(GuiContainerSliderBase containerSliderBase, int id, int x, int y, String name, float value)
	{
		this(containerSliderBase, id, x, y, 150, 20, name, value);
	}
	
	public GuiSliderForContainer(GuiContainerSliderBase containerSliderBase, int id, int x, int y, int xSize, int ySize, String name, float value)
	{
		super(id, x, y, xSize, ySize, name);
		this.sladerValue = value;
		this.sladerId = id;
		this.container = containerSliderBase;
	}

	protected int getHoverState(boolean b)
	{
		return 0;
	}

	protected void mouseDragged(Minecraft mc, int x, int y)
	{
		if(this.drawButton)
		{
			if(this.dragging)
			{
				this.sladerValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

				if(this.sladerValue < 0.0F)
				{
					this.sladerValue = 0.0F;
				}

				if(this.sladerValue > 1.0F)
				{
					this.sladerValue = 1.0F;
				}

				container.handlerSliderAction(this.sladerId, this.sladerValue);
				this.displayString = container.getSladerName(this.sladerId, this.sladerValue);
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.xPosition + (int)(this.sladerValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
			this.drawTexturedModalRect(this.xPosition + (int)(this.sladerValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
		}
	}

	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		if(super.mousePressed(mc, x, y))
		{
			this.sladerValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

			if(this.sladerValue < 0.0F)
			{
				this.sladerValue = 0.0F;
			}

			if(this.sladerValue > 1.0F)
			{
				this.sladerValue = 1.0F;
			}

			container.handlerSliderAction(this.sladerId, this.sladerValue);
			this.displayString = container.getSladerName(this.sladerId, this.sladerValue);
			this.dragging = true;
			return true;
		}
		else
		{
			return false;
		}
	}

	public void mouseReleased(int x, int y)
	{
		this.dragging = false;
	}
}