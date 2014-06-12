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
import fr.minecraftforgefrance.ffmtlibs.event.VersionCheckerPlayerEventHandler;

/**
 * @author robin4002
 */
public class FFMTVersionChecker
{
	public static void check(String versionUrl, String downloadUrl, String modName, String currentVersion)
	{
		List<String> versionList = getRemoteFile(versionUrl, false);
		if(versionList.isEmpty())
		{
			FFMTLibs.ffmtLog.error("Couldn't get remote file");
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
					if(!remoteVersion.equals(currentVersion))
					{
						FFMTLibs.ffmtLog.info("A new update for " + modName + " is available (" + remoteVersion + "). Current version is : " + currentVersion);
						FMLCommonHandler.instance().bus().register(new VersionCheckerPlayerEventHandler(modName, remoteVersion, downloadUrl));
					}
				}
			}
		}
	}

	public static List<String> getRemoteFile(String fileUrl, boolean silence)
	{
		try
		{
			URL url = new URL(fileUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			InputSupplier<InputStream> urlSupplier = new URLISSupplier(connection);
			return CharStreams.readLines(CharStreams.newReaderSupplier(urlSupplier, Charsets.UTF_8));
		}
		catch(Exception e)
		{
			if(!silence)
			{
				e.printStackTrace();
			}
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