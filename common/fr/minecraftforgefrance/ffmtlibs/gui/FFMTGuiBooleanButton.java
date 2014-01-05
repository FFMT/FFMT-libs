package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFMTGuiBooleanButton extends GuiButton
{
	protected static final ResourceLocation buttonTextures = new ResourceLocation("textures/gui/widgets.png");
	private boolean active;

	public FFMTGuiBooleanButton(int id, int x, int y, String s, boolean active)
	{
		this(id, x, y, 150, 20, s, active);
	}

	public FFMTGuiBooleanButton(int id, int x, int y, int width, int height, String s, boolean active)
	{
		super(id, x, y, width, height, s);
		this.active = active;
	}

	public void toggle()
	{
		this.active = (!this.getIsActive());
	}

	public boolean getIsActive()
	{
		return active;
	}

	protected int getHoverState(boolean mouseIsInButton)
	{
		byte b0 = 1;

		if(!this.field_146124_l || !getIsActive())
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
		if(this.field_146125_m)
		{
			FontRenderer fontrenderer = mc.fontRenderer;
			mc.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_146123_n = x >= this.field_146128_h && y >= this.field_146129_i && x < this.field_146128_h + this.field_146120_f && y < this.field_146129_i + this.field_146121_g;
			int k = this.getHoverState(this.field_146123_n);
			this.drawTexturedModalRect(this.field_146128_h, this.field_146129_i, 0, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
			this.drawTexturedModalRect(this.field_146128_h + this.field_146120_f / 2, this.field_146129_i, 200 - this.field_146120_f / 2, 46 + k * 20, this.field_146120_f / 2, this.field_146121_g);
			this.func_146119_b(mc, x, y);
			int l = 14737632;

			if(!this.field_146124_l)
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
			}

			this.drawCenteredString(fontrenderer, this.field_146126_j, this.field_146128_h + this.field_146120_f / 2, this.field_146129_i + (this.field_146121_g - 8) / 2, l);
		}
	}
}