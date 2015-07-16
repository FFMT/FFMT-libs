package fr.minecraftforgefrance.ffmtlibs.event;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import net.minecraft.client.Minecraft;
import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

public class ThreadDowloadTextData
{
	private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
	private List<String> value;
	private Thread textThread;
	private String textUrl;

	public ThreadDowloadTextData(String url)
	{
		this.textUrl = url;
		if(this.textThread == null)
		{
			this.textThread = new Thread("Text Downloader #" + threadDownloadCounter.incrementAndGet())
			{
				@Override
                public void run()
				{
					HttpURLConnection httpurlconnection = null;

					try
					{
						httpurlconnection = (HttpURLConnection)(new URL(ThreadDowloadTextData.this.textUrl)).openConnection(Minecraft.getMinecraft().getProxy());
						httpurlconnection.setDoInput(true);
						httpurlconnection.setDoOutput(false);
						httpurlconnection.connect();

						if(httpurlconnection.getResponseCode() / 100 == 2)
						{
							List<String> list = new ArrayList();
							InputStreamReader in = new InputStreamReader(httpurlconnection.getInputStream());
							BufferedReader reader = new BufferedReader(in);

							String ligne;
							while((ligne = reader.readLine()) != null)
							{
								list.add(ligne);
							}
							reader.close();
							in.close();
							ThreadDowloadTextData.this.value = list;
						}
					}
					catch(Exception exception)
					{
						FFMTLibs.ffmtLog.error("Couldn\'t download http texture", exception);
						return;
					}
					finally
					{
						if(httpurlconnection != null)
						{
							httpurlconnection.disconnect();
						}
					}
				}
			};
			this.textThread.setDaemon(true);
			this.textThread.setName("Text downloader: " + this.textUrl);
			this.textThread.start();
		}
	}

	public boolean isTextDownloaded()
	{
		return this.value != null;
	}

	public List<String> getValue()
	{
		return this.value;
	}
}