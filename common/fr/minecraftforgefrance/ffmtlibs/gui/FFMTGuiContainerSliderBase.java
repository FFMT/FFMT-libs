package fr.minecraftforgefrance.ffmtlibs.gui;

import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public abstract class FFMTGuiContainerSliderBase extends GuiContainer
{
	private GuiButton selectedButton;

	public FFMTGuiContainerSliderBase(Container container)
	{
		super(container);
	}

	protected void mouseClicked(int x, int y, int id)
	{
		super.mouseClicked(x, y, id);
		if(id == 0)
		{
			for(int l = 0; l < this.field_146292_n.size(); ++l)
			{
				GuiButton guibutton = (GuiButton)this.field_146292_n.get(l);

				if(guibutton.func_146116_c(this.field_146297_k, x, y) && guibutton instanceof FFMTGuiSliderForContainer)
				{
					this.selectedButton = guibutton;
					this.field_146297_k.func_147118_V().func_147682_a(PositionedSoundRecord.func_147674_a(new ResourceLocation("gui.button.press"), 1.0F));
					this.func_146284_a(guibutton);
				}
			}
		}
	}

	protected void mouseMovedOrUp(int x, int y, int id)
	{
		super.func_146286_b(x, y, id);
		if(this.selectedButton != null && id == 0)
		{
			this.selectedButton.func_146118_a(x, y);
			this.selectedButton = null;
		}
	}

	public abstract void handlerSliderAction(int sliderId, float sliderValue);

	public abstract String getSliderName(int sliderId, float sliderValue);
}