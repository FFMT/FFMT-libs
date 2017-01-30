package fr.minecraftforgefrance.ffmtlibs.client.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

public class GuiMultipleOptionButton extends GuiButton
{
    protected ResourceLocation buttonTex =  new ResourceLocation("textures/gui/widgets.png");
    private String[] changTxt;
    private int currentState, yTex = 46, stateCount;
    private boolean useHoverState = true;

    public GuiMultipleOptionButton(int id, int x, int y, int width, int height, String s, int currentState, int stateCount)
    {
        super(id, x, y, width, height, s);
        this.currentState = currentState;
        this.stateCount = stateCount;
    }
    
    public void shouldUseHoverState(boolean should)
    {
        this.useHoverState = should;
    }
    
    public void setTexts(String[] texts)
    {
        this.changTxt = texts;
    }
    
    public void setCutomTexture(ResourceLocation loc, int textureY)
    {
        this.buttonTex = loc;
        this.yTex = textureY;
    }

    public void next()
    {
        this.currentState++;
        if(this.currentState >= this.stateCount)
        {
            this.currentState = 0;
        }
    }

    public int getState()
    {
        return this.currentState;
    }

    @Override
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

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {
        if(this.visible)
        {
            FontRenderer fontrenderer = mc.fontRenderer;
            mc.getTextureManager().bindTexture(this.buttonTex);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, this.yTex + (this.useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, this.yTex + (this.useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.mouseDragged(mc, x, y);
            int l = 14737632;
            String str = this.displayString;
            if(this.currentState < this.changTxt.length)
            {
                str = this.changTxt[this.currentState];
            }
            if(!this.enabled)
            {
                l = -6250336;
            }
            else if(this.hovered)
            {
                l = 16777120;
            }

            this.drawCenteredString(fontrenderer, str, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }
}