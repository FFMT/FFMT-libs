package fr.minecraftforgefrance.ffmtlibs.client.gui;

public interface ISliderButton
{
	void handlerSliderAction(int sliderId, float sliderValue);

	String getSliderName(int sliderId, float sliderValue);
}