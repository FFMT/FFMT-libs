package fr.minecraftforgefrance.ffmtlibs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.io.InputSupplier;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import fr.minecraftforgefrance.ffmtlibs.event.FFMTVersionCheckPlayerEventHandler;

/**
 * @author robin4002
 */
public class FFMTVersionChecker
{
	public static void check(String versionUrl, String downloadUrl, String modName, String actuallyVersion)
	{
		List<String> versionList = getRemoteFile(versionUrl);
		if(versionList.isEmpty())
		{
			FFMTLibs.FFMTlog.error("couldn't get remote file");
			return;
		}
		for(String version : versionList)
		{
			if(version.contains(Loader.instance().getMCVersionString()))
			{
				String[] line = version.split(":");
				if(line.length == 2)
				{
					String remoteVersion = line[1];
					if(!remoteVersion.equals(actuallyVersion))
					{
						FFMTLibs.FFMTlog.info("A new update for " + modName + " is available (" + remoteVersion + ")");
						FMLCommonHandler.instance().bus().register(new FFMTVersionCheckPlayerEventHandler(modName, remoteVersion, downloadUrl));
					}
				}
			}
		}
	}

	public static List<String> getRemoteFile(String fileURL)
	{
		try
		{
			URL url = new URL(fileURL);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			InputSupplier<InputStream> urlSupplier = new URLISSupplier(connection);
			return CharStreams.readLines(CharStreams.newReaderSupplier(urlSupplier, Charsets.UTF_8));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	static class URLISSupplier implements InputSupplier<InputStream>
	{
		private final URLConnection connection;

		private URLISSupplier(URLConnection connection)
		{
			this.connection = connection;
		}

		@Override
		public InputStream getInput() throws IOException
		{
			return connection.getInputStream();
		}
	}
}