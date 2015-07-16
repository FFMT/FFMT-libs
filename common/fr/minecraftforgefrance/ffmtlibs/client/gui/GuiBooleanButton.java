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
    protected ResourceLocation buttonTex;
    private boolean active;
    private String disabled, enabledS;
    private int yTex;
    private boolean useHoverState;

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
        this(id, x, y, width, height, sEn, sDi, active, new ResourceLocation("textures/gui/widgets.png"), 46, true);
    }

    public GuiBooleanButton(int id, int x, int y, int width, int height, String sEn, String sDi, boolean active, ResourceLocation tex, int yTex, boolean useHoverState)
    {
        super(id, x, y, width, height, sEn);
        this.active = active;
        this.disabled = sDi;
        this.enabledS = sEn;
        this.buttonTex = tex;
        this.yTex = yTex;
        this.useHoverState = useHoverState;
    }

    public void toggle()
    {
        this.active = !this.getIsActive();
    }

    public boolean getIsActive()
    {
        return this.active;
    }

    @Override
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

    @Override
    public void drawButton(Minecraft mc, int x, int y)
    {
        if(this.visible)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(this.buttonTex);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = x >= this.xPosition && y >= this.yPosition && x < this.xPosition + this.width && y < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
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

            if(!getIsActive())
            {
                l = 6316128;
                str = this.disabled;
            }
            else
            {
                str = this.enabledS;
            }

            this.drawCenteredString(fontrenderer, str, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }
}