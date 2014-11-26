package fr.minecraftforgefrance.ffmtlibs.client.gui;

import java.io.IOException;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class GuiScreenSliderBase extends GuiScreen
{
	private GuiButton selectedButton;
	@Override
	protected void mouseClicked(int x, int y, int action) throws IOException
	{
		super.mouseClicked(x, y, action);
		if(action == 0)
		{
			for(int l = 0; l < this.buttonList.size(); ++l)
			{
				GuiButton guibutton = (GuiButton)this.buttonList.get(l);

				if(guibutton.mousePressed(this.mc, x, y) && guibutton instanceof GuiSliderForContainer)
				{
					this.selectedButton = guibutton;
					this.mc.getSoundHandler().playSound(PositionedSoundRecord.createPositionedSoundRecord(new ResourceLocation("gui.button.press"), 1.0F));
					this.actionPerformed(guibutton);
				}
			}
		}
	}
	@Override
	protected void mouseClickMove(int mouseX, int mouseY, int clickedMouseButton, long timeSinceLastClick)
	{
		super.mouseClickMove(mouseX, mouseY, clickedMouseButton, timeSinceLastClick);
		if(this.selectedButton != null && clickedMouseButton == 0)
		{
			this.selectedButton.mouseReleased(mouseX, mouseY);
			this.selectedButton = null;
		}
	}

	public abstract void handlerSliderAction(int sliderId, float sliderValue);

	public abstract String getSliderName(int sliderId, float sliderValue);
}