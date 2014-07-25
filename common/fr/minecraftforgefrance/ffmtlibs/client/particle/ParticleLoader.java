package fr.minecraftforgefrance.ffmtlibs.client.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ParticleLoader
{
	private static final Minecraft mcInstance = Minecraft.getMinecraft();
	
	/**
	 * Spawn custom or vanilla particle. If you select custom particle, set the texture location.
	 * [WIP] - (Work in progress)
	 * 
	 * Args:
	 * @param customParticle true for custom particle, false for vanilla particle
	 * @param particleNamrIfVanilla just vanilla particle name if you switched to particle vanilla mode, else set "null"
	 * @param world the world instance
	 * @param entity the entity instance (eg. player)
	 * @param x posX
	 * @param y posY
	 * @param z posZ
	 * @param motx motionX
	 * @param moty motionY
	 * @param motz motionZ
	 * @param gravity particle gravity (only for custom particle)
	 * @param scale particle scale (only for custom particle)
	 * @param textureLocation the particle texture location (eg. "modid:textures/folder/texture.png") (only for custom particle)
	 * 
	 * [WARNING] If you want to use it in a entity class, don't forget the worldObj.isRemote condition, because the game will crash when you try to spawn it ! 
	 * 
	 * @author elias54
	 */
	public static void spawnParticle(boolean customParticle, String particleNameIfVanilla, World world, Entity entity, double x, double y, double z, double motx, double moty, double motz, float gravity, float scale, String textureLocation)
	{
		EntityCustomFX customFX = null;
		if(customParticle)
		{
			customFX = new EntityCustomFX(world, x, y, z, motx, moty, motz, gravity, scale, textureLocation);
		}else{
			world.spawnParticle(particleNameIfVanilla, x, y, z, motx, moty, motz);
		}
		mcInstance.effectRenderer.addEffect(customFX);
	}
	// More stuff coming soon...
}
