package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiBooleanButton extends GuiButton
{
	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
	private boolean active;
	private int buttonHeight;
	private String disabled, enabledS;

	public GuiBooleanButton(int id, int x, int y, String text, boolean active)
	{
		this(id, x, y, 150, 20, text, active);
	}

	public GuiBooleanButton(int id, int x, int y, int width, int height, String s, boolean active)
	{
		this(id, x, y, width, height, s, s, active);
	}
	
	public GuiBooleanButton(int id, int x, int y, int width, int height, String sEn, String sDi, boolean active)
	{
		super(id, x, y, width, height, sEn);
		this.active = active;
		this.buttonHeight = height;
		this.disabled = sDi;
		this.enabledS = sEn;
	}

	public void toggle()
	{
		this.active = !this.getIsActive();
	}

	public boolean getIsActive()
	{
		return active;
	}

	public int getHoverState(boolean mouseIsInButton)
	{
		byte b0 = 1;

		if(!this.enabled || !getIsActive())
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
			String str;
			if(!this.enabled)
			{
				l = -6250336;
			}
			else if(this.field_146123_n)
			{
				l = 16777120;
			}

			if(!getIsActive())
			{
				l = 6316128;
				str = disabled;
			}
			else
			{
				str = enabledS;
			}

			this.drawCenteredString(fontrenderer, str, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
}