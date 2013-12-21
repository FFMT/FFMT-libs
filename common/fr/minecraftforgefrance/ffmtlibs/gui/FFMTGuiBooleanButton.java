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
	public boolean active;
	private int buttonHeight;

	public FFMTGuiBooleanButton(int id, int x, int y, int width, int height, String s, boolean active)
	{
		super(id, x, y, width, height, s);
		this.active = active;
		this.buttonHeight = height;
	}

	public void toggle()
	{
		this.active = (!this.active);
	}

	protected int getHoverState(boolean par1)
	{
		byte b0 = 1;

		if(!this.enabled)
		{
			b0 = 0;
		}
		else if(this.active)
		{
			b0 = 0;
		}
		else if(par1)
		{
			b0 = 2;
		}

		return b0;
	}

	public void drawButton(Minecraft par1Minecraft, int par2, int par3)
	{
		if(this.drawButton)
		{
			FontRenderer fontrenderer = par1Minecraft.fontRenderer;
			par1Minecraft.getTextureManager().bindTexture(buttonTextures);
			GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
			this.field_82253_i = par2 >= this.xPosition && par3 >= this.yPosition && par2 < this.xPosition + this.width && par3 < this.yPosition + this.height;
			int k = this.getHoverState(this.field_82253_i);
			this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, 46 + k * 20, this.width / 2, this.height);
			this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, 46 + k * 20, this.width / 2, this.height);
			this.mouseDragged(par1Minecraft, par2, par3);
			int l = 14737632;

			if(!this.enabled)
			{
				l = -6250336;
			}
			else if(this.field_82253_i)
			{
				l = 16777120;
			}

			this.drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
		}
	}
}
