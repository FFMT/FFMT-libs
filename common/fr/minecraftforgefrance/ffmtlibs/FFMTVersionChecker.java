package fr.minecraftforgefrance.ffmtlibs;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.List;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Loader;

import com.google.common.base.Charsets;
import com.google.common.io.ByteSource;

import fr.minecraftforgefrance.ffmtlibs.event.VersionCheckerPlayerEventHandler;

/**
 * @author robin4002
 */
public class FFMTVersionChecker
{
    public static final String DEV_VERSION = "@VERSION@";

	public static void check(String versionUrl, String downloadUrl, String modName, String currentVersion)
	{
	    if(currentVersion.equals(DEV_VERSION))
	    {
	        return;
	    }
		List<String> versionList = getRemoteFile(versionUrl);
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

	public static List<String> getRemoteFile(String fileUrl)
	{
		try
		{
			URL url = new URL(fileUrl);
			URLConnection connection = url.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			ByteSource byteSource = new URLISSupplier(connection);
			return byteSource.asCharSource(Charsets.UTF_8).readLines();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return Collections.emptyList();
		}
	}

	static class URLISSupplier extends ByteSource
	{
		private final URLConnection connection;

		private URLISSupplier(URLConnection connection)
		{
			this.connection = connection;
		}

        @Override
        public InputStream openStream() throws IOException
        {
            return this.connection.getInputStream();
        }
	}
}