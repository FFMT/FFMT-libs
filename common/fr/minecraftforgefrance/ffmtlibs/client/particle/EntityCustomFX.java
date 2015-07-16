package fr.minecraftforgefrance.ffmtlibs.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class EntityCustomFX extends EntityFX
{
    public String textureLocation;

    public EntityCustomFX(World world, double lasttickX, double lasttickY, double lasttickZ, double motX, double motY, double motZ, float gravity, float scale, String texLocation)
    {
        super(world, lasttickX, lasttickY, lasttickZ, motX, motY, motZ);
        this.textureLocation = texLocation;
        this.particleGravity = gravity;
        this.particleScale = scale;
    }

    @Override
    public int getFXLayer()
    {
        return 3;
    }

    public void renderParticle(Tessellator tessellator, float f, float f1, float f2, float f3, float f4, float f5)
    {
        Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(this.textureLocation));
        GL11.glDepthMask(false);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.startDrawingQuads();
        // worldrenderer.setBrightness(getBrightnessForRender(f));//Apparently do not exist anymore
        float scale = 0.1F * this.particleScale;
        float x = (float)(this.prevPosX + (this.prevPosX - this.posX) * f - interpPosX), y = (float)(this.prevPosY + (this.prevPosY - this.posY) * f - interpPosY), z = (float)(this.prevPosZ + (this.prevPosZ - this.posZ) * f - interpPosZ);
        float f14 = getBrightness(f);
        worldrenderer.setTranslation(0F, 0F, 0F);
        worldrenderer.setColorOpaque_F(f14 * this.particleRed, f14 * this.particleGreen, f14 * this.particleBlue);
        worldrenderer.addVertexWithUV(x - f1 * scale - f4 * scale, y - f2 * scale, z - f3 * scale - f5 * scale, 1, 1);
        worldrenderer.addVertexWithUV(x - f1 * scale + f4 * scale, y + f2 * scale, z - f3 * scale + f5 * scale, 1, 0);
        worldrenderer.addVertexWithUV(x + f1 * scale + f4 * scale, y + f2 * scale, z + f3 * scale + f5 * scale, 0, 0);
        worldrenderer.addVertexWithUV(x + f1 * scale - f4 * scale, y - f2 * scale, z + f3 * scale - f5 * scale, 0, 1);
        tessellator.draw();
        GL11.glDisable(GL11.GL_BLEND);
        GL11.glDepthMask(true);
        GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
    }

    @Override
    public void onUpdate()
    {
        this.prevPosX = this.posX;
        this.prevPosY = this.posY;
        this.prevPosZ = this.posZ;

        if(this.particleAge++ >= this.particleMaxAge)
        {
            this.setDead();
        }

        this.motionY -= 0.04D * this.particleGravity;
        this.moveEntity(this.motionX, this.motionY, this.motionZ);
        this.motionX *= 0.9800000190734863D;
        this.motionY *= 0.9800000190734863D;
        this.motionZ *= 0.9800000190734863D;

        if(this.onGround)
        {
            this.motionX *= 0.699999988079071D;
            this.motionZ *= 0.699999988079071D;
        }
        super.onUpdate();
    }
}
