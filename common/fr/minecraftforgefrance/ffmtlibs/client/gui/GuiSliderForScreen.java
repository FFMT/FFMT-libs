package fr.minecraftforgefrance.ffmtlibs.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.opengl.GL11;

@SideOnly(Side.CLIENT)
public class GuiSliderForScreen extends GuiButton
{
    private ResourceLocation texture;
    public float sliderValue;
    public boolean dragging;
    private int sliderId, yTexBackGround;
    private GuiScreenSliderBase container;

    public GuiSliderForScreen(GuiScreenSliderBase containerSliderBase, int id, int x, int y, String name, float value)
    {
        this(containerSliderBase, id, x, y, 150, 20, name, value);
    }

    public GuiSliderForScreen(GuiScreenSliderBase containerSliderBase, int id, int x, int y, int xSize, int ySize, String name, float value)
    {
        this(containerSliderBase, id, x, y, xSize, ySize, name, value, new ResourceLocation("textures/gui/widgets.png"), -1);
    }

    public GuiSliderForScreen(GuiScreenSliderBase containerSliderBase, int id, int x, int y, int xSize, int ySize, String name, float value, ResourceLocation customTexture, int yTexBackGround)
    {
        super(id, x, y, xSize, ySize, name);
        this.sliderValue = value;
        this.sliderId = id;
        this.container = containerSliderBase;
        this.texture = customTexture;
        this.yTexBackGround = yTexBackGround;
    }

    public void disable()
    {
        this.enabled = false;
    }

    public void enable()
    {
        this.enabled = true;
    }

    @Override
    public int getHoverState(boolean b)
    {
        return 0;
    }

    @Override
    protected void mouseDragged(Minecraft mc, int x, int y)
    {
        if(this.visible)
        {
            if(this.dragging)
            {
                this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

                if(this.sliderValue < 0.0F)
                {
                    this.sliderValue = 0.0F;
                }

                if(this.sliderValue > 1.0F)
                {
                    this.sliderValue = 1.0F;
                }

                container.handlerSliderAction(this.sliderId, this.sliderValue);
                this.displayString = container.getSliderName(this.sliderId, this.sliderValue);
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            mc.getTextureManager().bindTexture(texture);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, yTexBackGround == -1 ? 66 : yTexBackGround, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, yTexBackGround == -1 ? 66 : yTexBackGround, 4, 20);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int x, int y)
    {
        if(super.mousePressed(mc, x, y))
        {
            this.sliderValue = (float)(x - (this.xPosition + 4)) / (float)(this.width - 8);

            if(this.sliderValue < 0.0F)
            {
                this.sliderValue = 0.0F;
            }

            if(this.sliderValue > 1.0F)
            {
                this.sliderValue = 1.0F;
            }

            container.handlerSliderAction(this.sliderId, this.sliderValue);
            this.displayString = container.getSliderName(this.sliderId, this.sliderValue);
            this.dragging = true;
            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public void mouseReleased(int x, int y)
    {
        this.dragging = false;
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY)
    {
        if(this.visible && yTexBackGround != -1)
        {
            FontRenderer fontrenderer = mc.fontRendererObj;
            mc.getTextureManager().bindTexture(texture);
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            int k = this.getHoverState(this.hovered);
            GL11.glEnable(GL11.GL_BLEND);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

            this.drawTexturedModalRect(this.xPosition, this.yPosition, 0, yTexBackGround, this.width / 2, yTexBackGround + this.height);
            this.drawTexturedModalRect(this.xPosition + this.width / 2, this.yPosition, 200 - this.width / 2, yTexBackGround, this.width / 2, yTexBackGround + this.height);

            int l = 14737632;

            if(packedFGColour != 0)
            {
                l = packedFGColour;
            }
            else if(!this.enabled)
            {
                l = 10526880;
            }
            else if(this.hovered)
            {
                l = 16777120;
            }
            this.drawString(fontrenderer, this.displayString, this.xPosition + 5, this.yPosition + (this.height - 8) / 2, l);
            this.mouseDragged(mc, mouseX, mouseY);
        }
        else
        {
            super.drawButton(mc, mouseX, mouseY);
        }
    }
}