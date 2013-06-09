package fr.mcnanotech.FFMT.FFMTAPI;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.world.biome.BiomeGenBase;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class FFMTRegistry 
{
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static Minecraft getMinecraftInstance()
	{
		return mc;
	}
	
	/**
	 * Add another entity other than mob
	 * @param entityClass (The entity class)
	 * @param entityName (The entity name)
	 * @param id (The entity ID)
	 * @param mod (Mod instance)
	 * @param trackingRange (Number of tracking range)
	 * @param updateFrequency (Number update frequency)
	 * @param sendsVelocityUpdates (Send velocity updates or not)
	 * @author elias54
	 */
	public static void addOtherEntity(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates)
	{
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
	}
	
	/**
	 * Add a mob too easy without specific biomes
	 * @param entityClass (The entity class)
	 * @param entityName (The entity name)
	 * @param id (The entity ID)
	 * @param mod (Mod instance)
	 * @param trackingRange (Number of tracking range)
	 * @param updateFrequency (Number update frequency)
	 * @param sendsVelocityUpdates (Send velocity updates or not)
	 * @param backGroundEggColour (Background egg color)
	 * @param foreGroundEggColour (Foreground egg color)
	 * @param weightedProb (Chance to spawn)
	 * @param minSpawn (Minimum spawn per chunk)
	 * @param maxSpawn (Maximum spawn per chunk)
	 * @author elias54
	 */
	public static void addMobWithSpawn(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int backGroundEggColour, int foreGroundEggColour, int weightedProb, int minSpawn, int maxSpawn, EnumCreatureType creatureType)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, EntityRegistry.findGlobalUniqueEntityId(), backGroundEggColour, foreGroundEggColour);
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		EntityRegistry.addSpawn(entityName, weightedProb, minSpawn, maxSpawn, creatureType);
	}
	/**
	 * Add a mob too easy with specific biomes
	 * @param entityClass (The entity class)
	 * @param entityName (The entity name)
	 * @param id (The entity ID)
	 * @param mod (Mod instance)
	 * @param trackingRange (Number of tracking range)
	 * @param updateFrequency (Number update frequency)
	 * @param sendsVelocityUpdates (Send velocity updates or not)
	 * @param backGroundEggColour (Background egg color)
	 * @param foreGroundEggColour (Foreground egg color)
	 * @param weightedProb (Chance to spawn)
	 * @param minSpawn (Minimum spawn per chunk)
	 * @param maxSpawn (Maximum spawn per chunk)
	 * @param creatureType (The famous EnumCreatureType attribute)
	 * @param biome (Biome where you want to spawn the mob)
	 * @author elias54
	 */
	public static void addMobWithSpawnAndBiome(Class<? extends Entity> entityClass, String entityName, int id, Object mod, int trackingRange, int updateFrequency, boolean sendsVelocityUpdates, int backGroundEggColour, int foreGroundEggColour, int weightedProb, int minSpawn, int maxSpawn, EnumCreatureType creatureType, BiomeGenBase... biome)
	{
		EntityRegistry.registerGlobalEntityID(entityClass, entityName, EntityRegistry.findGlobalUniqueEntityId(), backGroundEggColour, foreGroundEggColour);
		EntityRegistry.registerModEntity(entityClass, entityName, id, mod, trackingRange, updateFrequency, sendsVelocityUpdates);
		EntityRegistry.addSpawn(entityName, weightedProb, minSpawn, maxSpawn, creatureType, biome);
	}
	/**
	 * Adding version checker (IS NOT COMPATIBLE IN SERVER VERSION)
	 * @param modName (the mod name)
	 * @param version (the version of your mod)
	 * @param versiondoc (the .htm version file (Args in the htm file (eg) : Version : 0.1))
	 * @param download (the download link)
	 * @author elias54
	 */
	@SideOnly(Side.CLIENT)
	public static void registerVersionCheck(String modName, double version, String versiondoc, String download)
	{
		FFMTVersionChecker.checkerSimpleSSP(modName, version, versiondoc, download, mc);
	}
	
	/**
	 * Adding version checker with metadata (coming soon)
	 * @param modName (the mod name)
	 * @param version (the version of your mod)
	 * @param versiondoc (the .xml version file for metadata version check)
	 * @param download (the download link)
	 * @author elias54
	 */
	public static void registerVersionCheckWithMetadata(String modName, double version, String versiondoc, String download)
	{
		//FFMTVersionChecker.checkerXMLSSP(modName, version, versiondoc, download, mc);
	}
	
	/**
	 * Add smelting for blocks/items with metadata
	 * @param input
	 * @param metadata
	 * @param output
	 * @param xp
	 */
	public static void addSmeltingWithMetadata(int input, int metadata, ItemStack output, float xp)
	{
		try
		{
			FurnaceRecipes.smelting().addSmelting(input, metadata, output, xp);
		}
		catch (Exception e)
		{
			FFMTAPI.FFMTlog.severe("Failed to register smelting whith metadata");
		}
	}
	
	/**
	 * Helper for crafting armors
	 * @param material
	 * @param type
	 * Helmet:0  
	 * Chestplate:1  
	 * Leggings:2  
	 * Boots:3 
	 * @param output
	 */
	public static void addArmorCrafting(ItemStack material, int type, ItemStack output)
	{
		try
		{
			if (type == 0)
			{
				GameRegistry.addRecipe(output, new Object[] {"XXX", "X X", 'X', material});
			}
			if (type == 1)
			{
				GameRegistry.addRecipe(output, new Object[] {"X X", "XXX", "XXX", 'X', material});
			}
			if (type == 2)
			{
				GameRegistry.addRecipe(output, new Object[] {"XXX", "X X", "X X", 'X', material});
			}
			if (type == 3)
			{
				GameRegistry.addRecipe(output, new Object[] {"X X", "X X", 'X', material});
			}
			if (type < 0 || type > 3)
			{
				FFMTAPI.FFMTlog.severe("Failed to register armor crafting, wrong 'type'");
			}
		}
		catch (Exception e)
		{
			FFMTAPI.FFMTlog.severe("Failed to register armor crafting");
		}
	}
	
	/**
	 * Helper for crafting all armors
	 * @param material
	 * @param outputHelmet
	 * @param outputChestPlate
	 * @param outputLeggings
	 * @param outputBoots
	 */
	public static void addAllArmorCrafting(ItemStack material, ItemStack outputHelmet, ItemStack outputChestPlate, ItemStack outputLeggings, ItemStack outputBoots)
	{
		try
		{
			GameRegistry.addRecipe(outputHelmet, new Object[] {"XXX", "X X", 'X', material});
			GameRegistry.addRecipe(outputChestPlate, new Object[] {"X X", "XXX", "XXX", 'X', material});
			GameRegistry.addRecipe(outputLeggings, new Object[] {"XXX", "X X", "X X", 'X', material});
			GameRegistry.addRecipe(outputBoots, new Object[] {"X X", "X X", 'X', material});
		}
		catch (Exception e)
		{
			FFMTAPI.FFMTlog.severe("Failed to register armor crafting");
		}
	}
	
	/**
	 * Helper for crafting tools
	 * @param material
	 * @param type
	 * Axe:0
	 * Shovel:1
	 * Hoe:2
	 * Pickaxe:3
	 * Sword:4
	 * @param output
	 */
	public static void addToolsCrafting(ItemStack material, int type, ItemStack output, ItemStack stick)
	{
		try
		{
			if (type == 0)
			{
				GameRegistry.addRecipe(output, new Object[] {"XX", "XS", " S", 'X', material, 'S', stick});
				GameRegistry.addRecipe(output, new Object[] {"XX", "SX", "S ", 'X', material, 'S', stick});
			}
			if (type == 1)
			{
				GameRegistry.addRecipe(output, new Object[] {"X", "S", "S", 'X', material, 'S', stick});
			}
			if (type == 2)
			{
				GameRegistry.addRecipe(output, new Object[] {"XX", " S", " S", 'X', material, 'S', stick});
				GameRegistry.addRecipe(output, new Object[] {"XX", "S ", "S ", 'X', material, 'S', stick});
			}
			if (type == 3)
			{
				GameRegistry.addRecipe(output, new Object[] {"XXX", " S ", " S ", 'X', material, 'S', stick});
			}
			if (type == 4)
			{
				GameRegistry.addRecipe(output, new Object[] {"X", "X", "S", 'X', material, 'S', stick});
			}
			if (type < 0 || type > 4)
			{
				FFMTAPI.FFMTlog.severe("Failed to register tools crafting, wrong 'type'");
			}
		}
		catch (Exception e)
		{
			FFMTAPI.FFMTlog.severe("Failed to register tools crafting");
		}
	}
	
}
