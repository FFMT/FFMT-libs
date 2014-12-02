package fr.minecraftforgefrance.ffmtlibs.client.gui;

import java.util.ArrayList;
import java.util.Iterator;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

@SideOnly(Side.CLIENT)
public class GuiHelper
{
    public static Minecraft mc = Minecraft.getMinecraft();
    public static TextureManager renderEngine = mc.renderEngine;

    /**
     * Bind the given texture
     *
     * @param texture
     *            ResourceLocation
     */
    public static void bindTexture(ResourceLocation texture)
    {
        renderEngine.bindTexture(texture);
    }

    /**
     * Bind the given texture by name (path)
     *
     * @param texture
     *            String
     */
    public static void bindTexture(String texture)
    {
        bindTexture(new ResourceLocation(texture));
    }

    /**
     * Bind the given texture by name (path) and modid
     *
     * @param modid
     * @param texture
     */
    public static void bindTexture(String modid, String texture)
    {
        bindTexture(new ResourceLocation(modid, texture));
    }

    /**
     * Draw a string aligned on left
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     */
    public static void drawLeftAlignedString(FontRenderer font, String string, int x, int y, int color)
    {
        font.drawString(string, x, y, color);
    }

    /**
     * Draw a string centered
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     */
    public static void drawCenteredString(FontRenderer font, String string, int x, int y, int color)
    {
        font.drawString(string, x - font.getStringWidth(string) / 2, y, color);
    }

    /**
     * Draw a string aligned on right
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     */
    public static void drawRightAlignedString(FontRenderer font, String string, int x, int y, int color)
    {
        font.drawString(string, x - font.getStringWidth(string), y, color);
    }

    /**
     * Draw a localized string aligned on the left
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     * @param arrayObj
     */
    public static void drawLeftAlignedLocalizedString(FontRenderer font, String string, int x, int y, int color, Object... arrayObj)
    {
        drawLeftAlignedString(font, I18n.format(string, arrayObj), x, y, color);
    }

    /**
     * Draw a centered localized string
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     * @param arrayObj
     */
    public static void drawCenteredLocalizedString(FontRenderer font, String string, int x, int y, int color, Object... arrayObj)
    {
        drawCenteredString(font, I18n.format(string, arrayObj), x, y, color);
    }

    /**
     * Draw a localized string aligned on the right
     *
     * @param font
     * @param string
     * @param x
     * @param y
     * @param color
     * @param arrayObj
     */
    public static void drawRightAlignedLocalizedString(FontRenderer font, String string, int x, int y, int color, Object... arrayObj)
    {
        drawRightAlignedString(font, I18n.format(string, arrayObj), x, y, color);
    }

    /**
     * Draw texture at given start and end cords
     *
     * @param xo
     * @param yo
     * @param xe
     * @param ye
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
     */
    public static void drawTexture(int xo, int yo, int xe, int ye, float xto, float yto, float xte, float yte)
    {
        drawTexture(xo, xe, xe, xo, ye, ye, yo, yo, xto, yto, xte, yte);
    }

    public static void drawTexture(int xo, int yo, int xe, int ye, float xto, float yto, float xte, float yte, float zLevel)
    {
        drawTexture(xo, xe, xe, xo, ye, ye, yo, yo, xto, yto, xte, yte, zLevel);
    }

    public static void drawTexture(int x0, int x1, int x2, int x3, int y0, int y1, int y2, int y3, float xto, float yto, float xte, float yte)
    {
        drawTexture(x0, x1, x2, x3, y0, y1, y2, y3, xto, yto, xte, yte, -90.0F);
    }

    /**
     * Draw a texture with 4 cords:
     *
     * @param x0
     * @param x1
     * @param x2
     * @param x3
     * @param y0
     * @param y1
     * @param y2
     * @param y3
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
     */
    public static void drawTexture(int x0, int x1, int x2, int x3, int y0, int y1, int y2, int y3, float xto, float yto, float xte, float yte, float zLevel)
    {
    	Tessellator tess = Tessellator.getInstance();
    	WorldRenderer worldrenderer = tess.getWorldRenderer();
    	worldrenderer.startDrawingQuads();
    	worldrenderer.addVertexWithUV(x0, y0, zLevel, xto, yte);
    	worldrenderer.addVertexWithUV(x1, y1, zLevel, xte, yte);
    	worldrenderer.addVertexWithUV(x2, y2, zLevel, xte, yto);
    	worldrenderer.addVertexWithUV(x3, y3, zLevel, xto, yto);
    	tess.draw();
    }

    /**
     * Draw a texture based on a circle, the for points will be on the circle with an angle of 45°
     *
     * @param x
     * @param y
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
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
     * Bind and draw texture at given start and end cords :
     *
     * @param texture
     *            ResourceLocation
     * @param xo
     * @param yo
     * @param xe
     * @param ye
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
     */
    public static void bindAndDrawTexture(ResourceLocation texture, int xo, int yo, int xe, int ye, float xto, float yto, float xte, float yte)
    {
        bindTexture(texture);
        drawTexture(xo, yo, xe, ye, xto, yto, xte, yte);
    }

    /**
     * Bind and draw a texture with 4 cords:
     *
     * @param texture
     *            ResourceLocation
     * @param x0
     * @param x1
     * @param x2
     * @param x3
     * @param y0
     * @param y1
     * @param y2
     * @param y3
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
     */
    public static void bindAndDrawTexture(ResourceLocation texture, int x0, int x1, int x2, int x3, int y0, int y1, int y2, int y3, float xto, float yto, float xte, float yte)
    {
        bindTexture(texture);
        drawTexture(x0, x1, x2, x3, y0, y1, y2, y3, xto, yto, xte, yte);
    }

    /**
     * bind and draw a texture based on a circle, the for points will be on the circle with an angle of 45°
     *
     * @param x
     * @param y
     * @param xto
     *            x texture start (ratio)
     * @param yto
     *            y texture start (ratio)
     * @param xte
     *            x texture end (ratio)
     * @param yte
     *            y texture end (ratio)
     * @param angle
     * @param radius
     */
    public static void bindAndDrawTextureWithRotation(ResourceLocation texture, int x, int y, float xto, float yto, float xte, float yte, double angle, double radius)
    {
        bindTexture(texture);
        drawTextureWithRotation(x, y, xto, yto, xte, yte, angle, radius);
    }

    /**
     * Draw string with little backgroud, like when you hover an item
     *
     * @param list
     *            contain text, one string by line
     * @param mouseX
     * @param mouseY
     * @param font
     *            fontrenderer
     * @param width
     *            width of the gui
     * @param height
     *            height of the gui
     * @param color
     *            color of the text
     */
    public static void drawHoveringText(ArrayList<String> list, int mouseX, int mouseY, FontRenderer font, int width, int height, int color)
    {
        if(!list.isEmpty())
        {
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            RenderHelper.disableStandardItemLighting();
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            int k = 0;
            Iterator iterator = list.iterator();

            while(iterator.hasNext())
            {
                String s = (String)iterator.next();
                int l = font.getStringWidth(s);

                if(l > k)
                {
                    k = l;
                }
            }

            int j2 = mouseX + 12;
            int k2 = mouseY - 12;
            int i1 = 8;

            if(list.size() > 1)
            {
                i1 += 2 + (list.size() - 1) * 10;
            }

            if(j2 + k > width)
            {
                j2 -= 28 + k;
            }

            if(k2 + i1 + 6 > height)
            {
                k2 = height - i1 - 6;
            }

            float zLevel = 300.0F;
            int j1 = -267386864;
            drawGradientRect(j2 - 3, k2 - 4, j2 + k + 3, k2 - 3, j1, j1, zLevel);
            drawGradientRect(j2 - 3, k2 + i1 + 3, j2 + k + 3, k2 + i1 + 4, j1, j1, zLevel);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 + i1 + 3, j1, j1, zLevel);
            drawGradientRect(j2 - 4, k2 - 3, j2 - 3, k2 + i1 + 3, j1, j1, zLevel);
            drawGradientRect(j2 + k + 3, k2 - 3, j2 + k + 4, k2 + i1 + 3, j1, j1, zLevel);
            int k1 = 1347420415;
            int l1 = (k1 & 16711422) >> 1 | k1 & -16777216;
            drawGradientRect(j2 - 3, k2 - 3 + 1, j2 - 3 + 1, k2 + i1 + 3 - 1, k1, l1, zLevel);
            drawGradientRect(j2 + k + 2, k2 - 3 + 1, j2 + k + 3, k2 + i1 + 3 - 1, k1, l1, zLevel);
            drawGradientRect(j2 - 3, k2 - 3, j2 + k + 3, k2 - 3 + 1, k1, k1, zLevel);
            drawGradientRect(j2 - 3, k2 + i1 + 2, j2 + k + 3, k2 + i1 + 3, l1, l1, zLevel);

            for(int i2 = 0; i2 < list.size(); ++i2)
            {
                String s1 = list.get(i2);
                font.drawString(s1, j2, k2, color);

                if(i2 == 0)
                {
                    k2 += 2;
                }

                k2 += 10;
            }
            GL11.glEnable(GL11.GL_LIGHTING);
            GL11.glEnable(GL11.GL_DEPTH_TEST);
            RenderHelper.enableStandardItemLighting();
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }

    /**
     * Function for drawHoveringText.
     */
    private static void drawGradientRect(int par1, int par2, int par3, int par4, int par5, int par6, float zLevel)
    {
        float f = (par5 >> 24 & 255) / 255.0F;
        float f1 = (par5 >> 16 & 255) / 255.0F;
        float f2 = (par5 >> 8 & 255) / 255.0F;
        float f3 = (par5 & 255) / 255.0F;
        float f4 = (par6 >> 24 & 255) / 255.0F;
        float f5 = (par6 >> 16 & 255) / 255.0F;
        float f6 = (par6 >> 8 & 255) / 255.0F;
        float f7 = (par6 & 255) / 255.0F;
        GL11.glDisable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        OpenGlHelper.glBlendFunc(770, 771, 1, 0);
        GL11.glShadeModel(GL11.GL_SMOOTH);
    	Tessellator tess = Tessellator.getInstance();
    	WorldRenderer worldrenderer = tess.getWorldRenderer();
    	worldrenderer.startDrawingQuads();
    	worldrenderer.setColorRGBA_F(f1, f2, f3, f);
    	worldrenderer.addVertex(par3, par2, zLevel);
    	worldrenderer.addVertex(par1, par2, zLevel);
    	worldrenderer.setColorRGBA_F(f5, f6, f7, f4);
    	worldrenderer.addVertex(par1, par4, zLevel);
    	worldrenderer.addVertex(par3, par4, zLevel);
    	tess.draw();
        GL11.glShadeModel(GL11.GL_FLAT);
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glEnable(GL11.GL_TEXTURE_2D);
    }
}