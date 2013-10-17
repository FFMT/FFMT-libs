package fr.minecraftforgefrance.ffmtlibs;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * @author robin4002, elias
 */
public class FFMTVersionChecker
{
	public static void Check(FMLPreInitializationEvent event, String versionUrl, String downloadurl, String modname, String actuallyversion)
	{
		try
		{
			String lastversion = lastVersion(new URL(versionUrl));
			String lastversiondebug = lastversion.substring(0, lastversion.length() - 1);
			if(!lastversiondebug.equals(actuallyversion))
			{
				event.getModLog().info("A new update for " + modname + " is available (" + lastversiondebug + ")");
				if(event.getSide().isClient())
				{
					GameRegistry.registerPlayerTracker(new FFMTPlayerTracker(modname, lastversiondebug, downloadurl));
				}
			}
		}
		catch(FileNotFoundException e)
		{
			event.getModLog().info("FAILED TO CHECK FOR UPDATE (url not found)");
		} 
		catch(MalformedURLException e)
		{
			event.getModLog().info("FAILED TO CHECK FOR UPDATE (url not found)");
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public static String lastVersion(URL url) throws IOException
	{
		HttpURLConnection httpurlconnection = (HttpURLConnection)url.openConnection();
		httpurlconnection.setRequestMethod("GET");
		BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(httpurlconnection.getInputStream()));
		StringBuilder stringbuilder = new StringBuilder();
		String s;

		while((s = bufferedreader.readLine()) != null)
		{
			stringbuilder.append(s);
			stringbuilder.append('\r');
		}

		bufferedreader.close();
		return stringbuilder.toString();
	}
}