package fr.minecraftforgefrance.ffmtlibs.event;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;

import javax.imageio.ImageIO;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IImageBuffer;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.minecraftforgefrance.ffmtlibs.FFMTLibs;

@SideOnly(Side.CLIENT)
public class ThreadDownloadImageData extends SimpleTexture
{
    private static final AtomicInteger threadDownloadCounter = new AtomicInteger(0);
    private final String imageUrl;
    private final IImageBuffer imageBuffer;
    private BufferedImage bufferedImage;
    private Thread imageThread;
    private boolean textureUploaded;

    public ThreadDownloadImageData(String url, ResourceLocation location, IImageBuffer image)
    {
        super(location);
        this.imageUrl = url;
        this.imageBuffer = image;
    }

    private void checkTextureUploaded()
    {
        if (!this.textureUploaded)
        {
            if (this.bufferedImage != null)
            {
                if (this.textureLocation != null)
                {
                    this.deleteGlTexture();
                }

                TextureUtil.uploadTextureImage(super.getGlTextureId(), this.bufferedImage);
                this.textureUploaded = true;
            }
        }
    }

    public int getGlTextureId()
    {
        this.checkTextureUploaded();
        return super.getGlTextureId();
    }

    public void setBufferedImage(BufferedImage image)
    {
        this.bufferedImage = image;
    }

    public void loadTexture(IResourceManager resource)
    {
        if (this.bufferedImage == null && this.textureLocation != null)
        {
            try
			{
				super.loadTexture(resource);
			}
			catch(IOException e)
			{
				e.printStackTrace();
			}
        }

        if (this.imageThread == null)
        {
            this.imageThread = new Thread("Texture Downloader #" + threadDownloadCounter.incrementAndGet())
            {
                public void run()
                {
                    HttpURLConnection httpurlconnection = null;

                    try
                    {
                        httpurlconnection = (HttpURLConnection)(new URL(ThreadDownloadImageData.this.imageUrl)).openConnection(Minecraft.getMinecraft().getProxy());
                        httpurlconnection.setDoInput(true);
                        httpurlconnection.setDoOutput(false);
                        httpurlconnection.connect();

                        if (httpurlconnection.getResponseCode() / 100 == 2)
                        {
                            BufferedImage bufferedimage = ImageIO.read(httpurlconnection.getInputStream());

                            if (ThreadDownloadImageData.this.imageBuffer != null)
                            {
                                bufferedimage = ThreadDownloadImageData.this.imageBuffer.parseUserSkin(bufferedimage);
                            }

                            ThreadDownloadImageData.this.setBufferedImage(bufferedimage);
                            return;
                        }
                    }
                    catch (Exception exception)
                    {
                        FFMTLibs.ffmtLog.error("Couldn\'t download http texture", exception);
                        return;
                    }
                    finally
                    {
                        if (httpurlconnection != null)
                        {
                            httpurlconnection.disconnect();
                        }
                    }
                }
            };
            this.imageThread.setDaemon(true);
            this.imageThread.setName("Hat downloader: " + this.imageUrl);
            this.imageThread.start();
        }
    }

    public boolean isTextureUploaded()
    {
        this.checkTextureUploaded();
        return this.textureUploaded;
    }
}