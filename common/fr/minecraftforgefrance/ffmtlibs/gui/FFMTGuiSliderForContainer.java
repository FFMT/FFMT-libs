package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFMTGuiSliderForContainer extends GuiButton
{
	public float sliderValue;
	public boolean dragging;
	private int sliderId;
	private FFMTGuiContainerSliderBase container;

	public FFMTGuiSliderForContainer(FFMTGuiContainerSliderBase containerSliderBase, int id, int x, int y, String name, float value)
	{
		this(containerSliderBase, id, x, y, 150, 20, name, value);
	}

	public FFMTGuiSliderForContainer(FFMTGuiContainerSliderBase containerSliderBase, int id, int x, int y, int xSize, int ySize, String name, float value)
	{
		super(id, x, y, xSize, ySize, name);
		this.sliderValue = value;
		this.sliderId = id;
		this.container = containerSliderBase;
	}

	public void disable()
	{
		this.field_146124_l = false;
	}

	public void enable()
	{
		this.field_146124_l = true;
	}

	protected int getHoverState(boolean b)
	{
		return 0;
	}

	protected void mouseDragged(Minecraft mc, int x, int y)
	{
		if(this.field_146125_m)
		{
			if(this.dragging)
			{
				this.sliderValue = (float)(x - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);

				if(this.sliderValue < 0.0F)
				{
					this.sliderValue = 0.0F;
				}

				if(this.sliderValue > 1.0F)
				{
					this.sliderValue = 1.0F;
				}

				container.handlerSliderAction(this.sliderId, this.sliderValue);
				this.field_146126_j = container.getSliderName(this.sliderId, this.sliderValue);
			}

			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)), this.field_146129_i, 0, 66, 4, 20);
			this.drawTexturedModalRect(this.field_146128_h + (int)(this.sliderValue * (float)(this.field_146120_f - 8)) + 4, this.field_146129_i, 196, 66, 4, 20);
		}
	}

	public boolean mousePressed(Minecraft mc, int x, int y)
	{
		if(super.func_146116_c(mc, x, y))
		{
			this.sliderValue = (float)(x - (this.field_146128_h + 4)) / (float)(this.field_146120_f - 8);

			if(this.sliderValue < 0.0F)
			{
				this.sliderValue = 0.0F;
			}

			if(this.sliderValue > 1.0F)
			{
				this.sliderValue = 1.0F;
			}

			container.handlerSliderAction(this.sliderId, this.sliderValue);
			this.field_146126_j = container.getSliderName(this.sliderId, this.sliderValue);
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