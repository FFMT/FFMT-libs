package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class FFMTGuiHelper
{
	public static Minecraft mc = Minecraft.getMinecraft();
	public static TextureManager renderEngine = mc.renderEngine;
	
	/**
	 * Bind the given texture
	 * @param texture ResourceLocation
	 */
	public static void bindTexture(ResourceLocation texture)
	{
		renderEngine.bindTexture(texture);
	}
	
	/**
	 * Bind the given texture by name (path)
	 * @param texture String
	 */
	public static void bindTexture(String texture)
	{
		bindTexture(new ResourceLocation(texture));
	}
	
	/**
	 * Bind the given texture by name (path) and modid
	 * @param modid
	 * @param texture
	 */
	public static void bindTexture(String modid, String texture)
	{
		bindTexture(new ResourceLocation(modid, texture));
	}
	
	/**
	 * Draw a string aligned on left
	 * @param fontRenderer
	 * @param str
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void drawLeftAlignedString(FontRenderer font, String string, int x, int y, int color)
	{
		font.drawStringWithShadow(string, x, y, color);
	}

	/**
	 * Draw a string centered
	 * @param font
	 * @param string
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void drawCenteredString(FontRenderer font, String string, int x, int y, int color)
	{
		font.drawStringWithShadow(string, x - font.getStringWidth(string) / 2, y, color);
	}

	/**
	 * Draw a string aligned on right
	 * @param font
	 * @param string
	 * @param x
	 * @param y
	 * @param color
	 */
	public static void drawRightAlignedString(FontRenderer font, String string, int x, int y, int color)
	{
		font.drawStringWithShadow(string, x - font.getStringWidth(string), y, color);
	}
	
	/**
	 * Draw a localized string aligned on the left
	 * @param font
	 * @param string
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 * @param arrayObj
	 */
	public static void drawLeftAlignedLocalizedString(FontRenderer font, String string, int x, int y, int color, Object ... arrayObj)
	{
		drawLeftAlignedString(font, I18n.format(string, arrayObj), x, y, color);
	}
	
	/**
	 * Draw a centered localized string
	 * @param font
	 * @param string
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 * @param arrayObj
	 */
	public static void drawCenteredLocalizedString(FontRenderer font, String string, int x, int y, int color, Object ... arrayObj)
	{
		drawCenteredString(font, I18n.format(string, arrayObj), x, y, color);
	}
	
	/**
	 * Draw a localized string aligned on the right
	 * @param font
	 * @param string
	 * @param x
	 * @param y
	 * @param z
	 * @param color
	 * @param arrayObj
	 */
	public static void drawRightAlignedLocalizedString(FontRenderer font, String string, int x, int y, int color, Object ... arrayObj)
	{
		drawRightAlignedString(font, I18n.format(string, arrayObj), x, y, color);
	}
	
	/**
	 * Draw texture at given start and end cords
	 * 
	 * o───────────────────┐
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * └───────────────────e
	 * 
	 * @param xo 
	 * @param yo
	 * @param xe
	 * @param ye
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 */
	public static void drawTexture(int xo, int yo, int xe, int ye, float xto, float yto, float xte, float yte)
	{
		drawTexture(xo, xe, xe, xo, ye, ye, yo, yo, xto, yto, xte, yte);
	}
	
	/**
	 * Draw a texture with 4 cords:
	 * 
	 * 0───────────────────1
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * 3───────────────────2
	 * 
	 * @param x0
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param y0
	 * @param y1
	 * @param y2
	 * @param y3
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 */
	public static void drawTexture(int x0, int x1, int x2, int x3, int y0, int y1, int y2, int y3, float xto, float yto, float xte, float yte)
	{
		Tessellator tessellator = Tessellator.instance;
		float zLevel = -90.0F;
		tessellator.startDrawingQuads();
		tessellator.addVertexWithUV((double)x0, (double)y0, (double)zLevel, (double)xto, (double)yte);
		tessellator.addVertexWithUV((double)x1, (double)y1, (double)zLevel, (double)xte, (double)yte);
		tessellator.addVertexWithUV((double)x2, (double)y2, (double)zLevel, (double)xte, (double)yto);
		tessellator.addVertexWithUV((double)x3, (double)y3, (double)zLevel, (double)xto, (double)yto);
		tessellator.draw();
	}
	
	/**
	 * Draw a texture based on a circle, the for points will be on the circle with an angle of 45°
	 * @param x
	 * @param y
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 * @param angle
	 * @param radius
	 */
	public static void drawTextureWithRotation(int x, int y, float xto, float yto, float xte, float yte, double angle, double radius)
	{
		int x0 = (int)(Math.cos((Math.PI / 2) + (Math.PI / 4) + angle) * radius);
		int y0 = (int)(Math.sin((Math.PI / 2) + (Math.PI / 4) + angle) * radius);
		int x1 = (int)(Math.cos((Math.PI / 4) + angle) * radius);
		int y1 = (int)(Math.sin((Math.PI / 4) + angle) * radius);
		int x2 = (int)(Math.cos(-(Math.PI / 4) + angle) * radius);
		int y2 = (int)(Math.sin(-(Math.PI / 4) + angle) * radius);
		int x3 = (int)(Math.cos(-(Math.PI / 2) - (Math.PI / 4) + angle) * radius);
		int y3 = (int)(Math.sin(-(Math.PI / 2) - (Math.PI / 4) + angle) * radius);
		drawTexture(x + x0, x + x1, x + x2, x + x3, y + y0, y + y1, y + y2, y + y3, xto, yto, xte, yte);
	}
	
	/**
	 * Bind and draw texture at given start and end cords
	 * 
	 * o───────────────────┐
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * └───────────────────e
	 * 
	 * @param texture ResourceLocation
	 * @param xo 
	 * @param yo
	 * @param xe
	 * @param ye
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 */
	public static void bindAndDrawTexture(ResourceLocation texture, int xo, int yo, int xe, int ye, float xto, float yto, float xte, float yte)
	{
		bindTexture(texture);
		drawTexture(xo, yo, xe, ye, xto, yto, xte, yte);
	}
	
	/**
	 * Bind and draw a texture with 4 cords:
	 * 
	 * 0───────────────────1
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * │                   │
	 * 3───────────────────2
	 * 
	 * @param texture ResourceLocation
	 * @param x0
	 * @param x1
	 * @param x2
	 * @param x3
	 * @param y0
	 * @param y1
	 * @param y2
	 * @param y3
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 */
	public static void bindAndDrawTexture(ResourceLocation texture, int x0, int x1, int x2, int x3, int y0, int y1, int y2, int y3, float xto, float yto, float xte, float yte)
	{
		bindTexture(texture);
		drawTexture(x0, x1, x2, x3, y0, y1, y2, y3, xto, yto, xte, yte);
	}
	
	/**
	 * bind and draw a texture based on a circle, the for points will be on the circle with an angle of 45°
	 * @param x
	 * @param y
	 * @param xto x texture start (ratio)
	 * @param yto y texture start (ratio)
	 * @param xte x texture end (ratio)
	 * @param yte y texture end (ratio)
	 * @param angle
	 * @param radius
	 */
	public static void bindAndDrawTextureWithRotation(ResourceLocation texture, int x, int y, float xto, float yto, float xte, float yte, double angle, double radius)
	{
		bindTexture(texture);
		drawTextureWithRotation(x, y, xto, yto, xte, yte, angle, radius);
	}
}