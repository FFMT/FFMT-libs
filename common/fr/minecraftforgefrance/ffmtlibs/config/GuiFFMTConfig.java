package fr.minecraftforgefrance.ffmtlibs.config;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

public class GuiFFMTConfig extends GuiConfig
{
	public GuiFFMTConfig(GuiScreen parent)
	{
		super(parent, new ConfigElement(FFMTLibs.cfg.getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), "ffmtlibs", false, false, GuiConfig.getAbridgedConfigPath(FFMTLibs.cfg.toString()));
	}
}