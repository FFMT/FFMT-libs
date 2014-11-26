package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class GuiMultipleOptionButton extends GuiButton
{
    protected ResourceLocation buttonTex;
    private final String[] changTxt;
    private int currentState, yTex;
    private boolean useHoverState;

    public GuiMultipleOptionButton(int id, int x, int y, String text, String[] changingTxt, int currentState)
    {
        this(id, x, y, 150, 20, text, changingTxt, currentState);
    }

    public GuiMultipleOptionButton(int id, int x, int y, int width, int height, String s, String[] changingTxt, int currentState)
    {
        this(id, x, y, width, height, s, changingTxt, currentState, new ResourceLocation("textures/gui/widgets.png"), 46, true);
    }

    public GuiMultipleOptionButton(int id, int x, int y, int width, int height, String s, String[] changingTxt, int currentState, ResourceLocation custom, int yTex, boolean useHoverState)
    {
        super(id, x, y, width, height, s);
        this.changTxt = changingTxt;
        this.currentState = currentState;
        this.buttonTex = custom;
        this.yTex = yTex;
        this.useHoverState = useHoverState;
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
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(buttonTex);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, yTex + (useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, yTex + (useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.mouseDragged(mc, x, y);
            int l = 14737632;
            String str = this.displayString + this.changTxt[currentState];
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