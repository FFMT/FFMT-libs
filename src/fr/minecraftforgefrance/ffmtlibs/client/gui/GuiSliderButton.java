package fr.minecraftforgefrance.ffmtlibs.client.gui;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Robot;

import org.lwjgl.opengl.Display;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class GuiSliderButton extends GuiButton
{
    public float sliderValue;
    private boolean dragging, shouldReset;
    private int sliderId, yTexBackGround = -1;
    private ISliderButton iSliderButton;
    private ResourceLocation texture = BUTTON_TEXTURES;

    public GuiSliderButton(ISliderButton containerSliderBase, int id, int x, int y, int width, int height, String name, float value)
    {
        super(id, x, y, width, height, name);
        this.sliderValue = value;
        this.sliderId = id;
        this.iSliderButton = containerSliderBase;
    }

    public void shouldResetOnEnd(boolean should)
    {
        this.shouldReset = should;
    }

    public void setCustomTexture(ResourceLocation customTexture, int yTexBackGround)
    {
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
    public int getHoverState(boolean mouseOver)
    {
        return 0;
    }

    @Override
    protected void mouseDragged(Minecraft mc, int mouseX, int mouseY)
    {
        if(this.visible)
        {
            if(this.dragging)
            {
                this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
                if(this.sliderValue >= 1.0F && this.shouldReset)
                {
                    if(mouseX > this.width + this.xPosition + 10)
                    {
                        this.sliderValue = 0.0F;
                        try
                        {
                            Robot robot = new Robot();
                            int x = Display.getX() + (mouseX - this.width) * (Display.getWidth() / mc.currentScreen.width);
                            robot.mouseMove(x, MouseInfo.getPointerInfo().getLocation().y);
                        }
                        catch(AWTException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                else if(this.sliderValue <= 0.0F && this.shouldReset)
                {
                    if(mouseX < this.xPosition - 10)
                    {
                        this.sliderValue = 1.0F;
                        try
                        {
                            Robot robot = new Robot();
                            int x = Display.getX() + (mouseX + this.width + 10) * (Display.getWidth() / mc.currentScreen.width);
                            robot.mouseMove(x, MouseInfo.getPointerInfo().getLocation().y);
                        }
                        catch(AWTException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
                this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);

                this.iSliderButton.handlerSliderAction(this.sliderId, this.sliderValue);
                this.displayString = this.iSliderButton.getSliderName(this.sliderId, this.sliderValue);
            }

            mc.getTextureManager().bindTexture(this.texture);
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, this.yTexBackGround == -1 ? 66 : this.yTexBackGround, 4, 20);
            this.drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, this.yTexBackGround == -1 ? 66 : this.yTexBackGround, 4, 20);
        }
    }

    @Override
    public boolean mousePressed(Minecraft mc, int mouseX, int mouseY)
    {
        if(super.mousePressed(mc, mouseX, mouseY))
        {
            this.sliderValue = (float)(mouseX - (this.xPosition + 4)) / (float)(this.width - 8);
            this.sliderValue = MathHelper.clamp(this.sliderValue, 0.0F, 1.0F);

            this.iSliderButton.handlerSliderAction(this.sliderId, this.sliderValue);
            this.displayString = this.iSliderButton.getSliderName(this.sliderId, this.sliderValue);
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY)
    {
        this.dragging = false;
    }
}