package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderForContainer extends GuiButton
{
	public float sliderValue;
	public boolean dragging;
	private int sladerId;
	private GuiContainerSliderBase container;

	public GuiSliderForContainer(GuiContainerSliderBase containerSliderBase, int id, int x, int y, String name, float value)
	{
		super(id, x, y, 150, 20, name);
		this.sliderValue = value;
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
				this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

				if(this.sliderValue < 0.0F)
				{
					this.sliderValue = 0.0F;
				}

				if(this.sliderValue > 1.0F)
				{
					this.sliderValue = 1.0F;
				}

				container.handlerSliderAction(this.sladerId, this.sliderValue);
				this.displayString = container.getSladerName(this.sladerId, this.sliderValue);
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)), this.yPosition, 0, 66, 4, 20);
			this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (float)(this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
		}
	}

	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		if(super.mousePressed(mc, x, y))
		{
			this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

			if(this.sliderValue < 0.0F)
			{
				this.sliderValue = 0.0F;
			}

			if(this.sliderValue > 1.0F)
			{
				this.sliderValue = 1.0F;
			}

			container.handlerSliderAction(this.sladerId, this.sliderValue);
			this.displayString = container.getSladerName(this.sladerId, this.sliderValue);
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