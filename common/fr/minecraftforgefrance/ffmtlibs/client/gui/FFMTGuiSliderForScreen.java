package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFMTGuiSliderForScreen extends GuiButton
{
	public float sliderValue;
	public boolean dragging;
	private int sliderId;
	private FFMTGuiScreenSliderBase container;

	public FFMTGuiSliderForScreen(FFMTGuiScreenSliderBase containerSliderBase, int id, int x, int y, String name, float value)
	{
		this(containerSliderBase, id, x, y, 150, 20, name, value);
	}

	public FFMTGuiSliderForScreen(FFMTGuiScreenSliderBase containerSliderBase, int id, int x, int y, int xSize, int ySize, String name, float value)
	{
		super(id, x, y, xSize, ySize, name);
		this.sliderValue = value;
		this.sliderId = id;
		this.container = containerSliderBase;
	}

	public void disable()
	{
		this.enabled = false;
	}

	public void enable()
	{
		this.enabled = true;
	}

	protected int getHoverState(boolean b)
	{
		return 0;
	}

	protected void mouseDragged(Minecraft mc, int x, int y)
	{
		if(this.visible)
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

				container.handlerSliderAction(this.sliderId, this.sliderValue);
				this.displayString = container.getSliderName(this.sliderId, this.sliderValue);
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

			container.handlerSliderAction(this.sliderId, this.sliderValue);
			this.displayString = container.getSliderName(this.sliderId, this.sliderValue);
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