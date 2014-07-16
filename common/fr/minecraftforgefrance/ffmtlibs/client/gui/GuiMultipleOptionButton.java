package fr.minecraftforgefrance.ffmtlibs.client.gui;

import java.util.ArrayList;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

public class GuiMultipleOptionButton extends GuiButton
{
	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
	private int numberStates;
	private ArrayList<String> changTxt;
	private int currentState;
	
	public GuiMultipleOptionButton(int id, int x, int y, String text, ArrayList<String> changingTxt, int numberOfStates, int currentState)
	{
		this(id, x, y, 150, 20, text, changingTxt, numberOfStates, currentState);
	}

	public GuiMultipleOptionButton(int id, int x, int y, int width, int height, String s, ArrayList<String> changingTxt, int numberOfStates, int currentState)
	{
		super(id, x, y, width, height, s);
		this.numberStates = numberOfStates;
		this.changTxt = changingTxt;
		this.currentState = currentState;
	}
	
	public void next()
	{
		if(currentState == numberStates)
		{
			currentState = 0;
		}
		else
		{
			currentState++;
		}
	}
	
	public int getState()
	{
		return currentState;
	}
	
	public int getHoverState(boolean mouseIsInButton)
	{
		byte b0 = 1;

		if(!this.enabled)
		{
			b0 = 0;
		}
		else if(mouseIsInButton)
		{
			b0 = 2;
		}

		return b0;
	}
	
	public void drawButton(Minecraft mc, int x, int y)
	{
		if(this.visible)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
			int k = this.getHoverState(this.field_146123_n);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
			this.mouseDragged(mc, x, y);
			int l = 14737632;
			String str = this.displayString + this.changTxt.get(this.currentState);
			if(!this.enabled)
			{
				l = -6250336;
			}
			else if(this.field_146123_n)
			{
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, str, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
}