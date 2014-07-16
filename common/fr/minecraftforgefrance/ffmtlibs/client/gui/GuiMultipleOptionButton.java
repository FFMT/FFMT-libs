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
	private final String[] changTxt;
	private int currentState;
	
	public GuiMultipleOptionButton(int id, int x, int y, String text, String[] changingTxt, int currentState)
	{
		this(id, x, y, 150, 20, text, changingTxt, currentState);
	}

	public GuiMultipleOptionButton(int id, int x, int y, int width, int height, String s, String[] changingTxt, int currentState)
	{
		super(id, x, y, width, height, s);
		this.changTxt = changingTxt;
		this.currentState = currentState;
	}
	
	public void next()
	{
		currentState++;
		if(currentState >= changTxt.length)
		{
			currentState = 0;
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
			String str = this.displayString + this.changTxt[currentState];
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