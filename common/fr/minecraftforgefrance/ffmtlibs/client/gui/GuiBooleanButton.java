package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiBooleanButton extends GuiButton
{
    private ResourceLocation buttonTex = new ResourceLocation("textures/gui/widgets.png");
    private boolean active;
    private String textNotActive, textActive;
    private int yTex = 46;
    private boolean useHoverState = true, otherTextureWhenActive = true, doNotChangeTextColor = false;
    
    public GuiBooleanButton(int id , int x, int y,int width, int height, String text, boolean active)
    {
        super(id, x, y, width, height, text);
        this.active = active;
        this.textActive = this.textNotActive = text;
    }
    
    public void setTexts(String active, String notActive)
    {
        this.textActive = active;
        this.textNotActive = notActive;
    }
    
    /**
     * Follow the default texture model!
     * @param loc the resource location of the texture
     * @param textureY y coord of the texture in the file
     */
    public void setCustomTexture(ResourceLocation loc, int textureY)
    {
        this.yTex = textureY;
        this.buttonTex = loc;
    }

    public void shouldUseHoverState(boolean should)
    {
        this.useHoverState = should;
    }
    
    public void shouldChangeTextureOnToggle(boolean should)
    {
        this.otherTextureWhenActive = should;
    }
    
    public void changeTextColorWhenNotActive(boolean change)
    {
        this.doNotChangeTextColor = change;
    }

    public void toggle()
    {
        this.active = !this.isActive();
    }

    public boolean isActive()
    {
        return this.active;
    }

    @Override
    public int getHoverState(boolean mouseIsInButton)
    {
        return getHoverState(mouseIsInButton, true);
    }

    public int getHoverState(boolean mouseIsInButton, boolean otherActive)
    {
        byte b0 = 1;
        if((isActive() || this.active) && otherActive)
        {
            b0 = 1;
        }
        if(mouseIsInButton)
        {
            b0 = 2;
        }

        return b0;
    }

    public void shouldNotChangeTextColor(boolean b)
    {
        this.doNotChangeTextColor = b;
    }

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {
        if(this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(this.buttonTex);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered, this.otherTextureWhenActive);
            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, this.yTex + (this.useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, this.yTex + (this.useHoverState ? (k * 20) : 0), this.width / 2, this.height);
            this.mouseDragged(mc, x, y);
            int l = 14737632;
            String str;
            if(!this.enabled)
            {
                l = -6250336;
            }
            else if(this.hovered)
            {
                l = 16777120;
            }

            if(!isActive())
            {
                l = this.doNotChangeTextColor ? 14737632 : 6316128;
                str = this.textNotActive;
            }
            else
            {
                str = this.textActive;
            }

            this.drawCenteredString(fontrenderer, str, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }
}