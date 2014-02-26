package fr.minecraftforgefrance.ffmtlibs;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.ShapedRecipes;
import net.minecraft.item.crafting.ShapelessRecipes;
import net.minecraft.world.World;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * @authors kevin_68, elias54
 */
public class FFMTRegistry
{
	public static final int HELMET_TYPE = 0;
	public static final int CHESTPLATE_TYPE = 1;
	public static final int LEGGINS_TYPE = 2;
	public static final int BOOTS_TYPE = 3;

	public static final int AXE_TYPE = 0;
	public static final int SHOVEL_TYPE = 1;
	public static final int HOE_TYPE = 2;
	public static final int PICKAXE_TYPE = 3;
	public static final int SWORD_TYPE = 4;
	
	
	/**
	 * Spawn particles (Blocks only)
	 * Just a call of <code>spawnParticles(int speed, String particles, World world, int posX, int posY, int posZ, Random random, double velX, double velY, double velZ)</code>
	 * where velX, velY and velZ are equal to 0D
	 * 
	 * @param speed
	 * @param particles
	 *            (ex: "smoke", "largesmoke", "enchantmenttable", ...)
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param random
	 */
	@SideOnly(Side.CLIENT)
	public static void spawnParticles(int speed, String particles, World world, int posX, int posY, int posZ, Random random)
	{
		spawnParticles(speed, particles, world, posX, posY, posZ, random, 0,0,0);
	}

	/**
	 * Spawn particles (Blocks only)
	 * 
	 * @param speed
	 * @param particles
	 *            (ex: "smoke", "largesmoke", "enchantmenttable", ...)
	 * @param world
	 * @param posX
	 * @param posY
	 * @param posZ
	 * @param random
	 * @param velX Velocity on X-axis
	 * @param velY Velocity on Y-axis
	 * @param velZ Velocity on Z-axis
	 */
	@SideOnly(Side.CLIENT)
	public static void spawnParticles(int speed, String particles, World world, int posX, int posY, int posZ, Random random, double velX, double velY, double velZ)
	{
		float x = (float)posX + random.nextFloat();
		float y = (float)posY + random.nextFloat() * 0.1F;
		float z = (float)posZ + random.nextFloat();

		for(int i = 0; i < speed; i++)
		{
			world.spawnParticle(particles, (double)x, (double)y, (double)z, velX, velY, velZ);
		}
	}


	/**
	 * Adding version checker rewritten by robin4002 original by elias
	 * @param versionUrl (your txt url with the last version number)
	 * @param downloadurl
	 * @param modname
	 * @param actuallyversion
	 */
	public static void registerVersionCheck(String versionURL, String downloadURL, String modName, String currentVersion)
	{
		FFMTVersionChecker.check(versionURL, downloadURL, modName, currentVersion);
	}
	
	/**
	 * Adding version checker using mcmod.info. Put the txt file link in "updateUrl"
	 * @param modid - your modid (no case sensitive)
	 */
	public static void registerVersionCheck(String modid)
	{
		for(ModContainer mod : Loader.instance().getActiveModList())
		{
			if(mod.getModId().equalsIgnoreCase(modid))
			{
				FFMTVersionChecker.check(mod.getMetadata().url, mod.getMetadata().updateUrl, mod.getName(), mod.getVersion());
			}
		}
	}


	/**
	 * Add smelting for blocks/items with metadata
	 * 
	 * @param input
	 * @param metadata
	 * @param output
	 * @param xp
	 */
	public static void addSmeltingWithMetadata(ItemStack input, ItemStack output, float xp)
	{
		try
		{
			FurnaceRecipes.smelting().func_151394_a(input, output, xp);
		} 
		catch(Exception e)
		{
			FFMTLibs.FFMTlog.error("Failed to register smelting");
		}
	}


	/**
	 * Helper for crafting armors
	 * 
	 * @param material
	 * @param type
	 *            Helmet:0 Chestplate:1 Leggings:2 Boots:3
	 * @param output
	 */
	public static void addArmorCrafting(ItemStack material, int type, ItemStack output)
	{
		try
		{
			if(type == 0)
			{
				GameRegistry.addRecipe(output, new Object[]{"XXX", "X X", 'X', material});
			}
			else if(type == 1)
			{
				GameRegistry.addRecipe(output, new Object[]{"X X", "XXX", "XXX", 'X', material});
			}
			else if(type == 2)
			{
				GameRegistry.addRecipe(output, new Object[]{"XXX", "X X", "X X", 'X', material});
			}
			else if(type == 3)
			{
				GameRegistry.addRecipe(output, new Object[]{"X X", "X X", 'X', material});
			}
			else
			{
				FFMTLibs.FFMTlog.error("Failed to register armor crafting, couldn't handle type "+type);
			}
		} 
		catch(Exception e)
		{
			FFMTLibs.FFMTlog.error("Failed to register armor crafting");
		}
	}


	/**
	 * Helper for crafting all armors
	 * 
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
			GameRegistry.addRecipe(outputHelmet, new Object[]{"XXX", "X X", 'X', material});
			GameRegistry.addRecipe(outputChestPlate, new Object[]{"X X", "XXX", "XXX", 'X', material});
			GameRegistry.addRecipe(outputLeggings, new Object[]{"XXX", "X X", "X X", 'X', material});
			GameRegistry.addRecipe(outputBoots, new Object[]{"X X", "X X", 'X', material});
		} 
		catch(Exception e)
		{
			FFMTLibs.FFMTlog.error("Failed to register armor crafting");
		}
	}

	
	/**
	 * Helper for crafting tools
	 * 
	 * @param material
	 * @param type
	 *            Axe:0 Shovel:1 Hoe:2 Pickaxe:3 Sword:4
	 * @param output
	 */
	public static void addToolsCrafting(ItemStack material, int type, ItemStack output, ItemStack stick)
	{
		try
		{
			if(type == 0)
			{
				GameRegistry.addRecipe(output, new Object[]{"XX", "XS", " S", 'X', material, 'S', stick});
				GameRegistry.addRecipe(output, new Object[]{"XX", "SX", "S ", 'X', material, 'S', stick});
			}
			else if(type == 1)
			{
				GameRegistry.addRecipe(output, new Object[]{"X", "S", "S", 'X', material, 'S', stick});
			}
			else if(type == 2)
			{
				GameRegistry.addRecipe(output, new Object[]{"XX", " S", " S", 'X', material, 'S', stick});
				GameRegistry.addRecipe(output, new Object[]{"XX", "S ", "S ", 'X', material, 'S', stick});
			}
			else if(type == 3)
			{
				GameRegistry.addRecipe(output, new Object[]{"XXX", " S ", " S ", 'X', material, 'S', stick});
			}
			else if(type == 4)
			{
				GameRegistry.addRecipe(output, new Object[]{"X", "X", "S", 'X', material, 'S', stick});
			}
			else
			{
				FFMTLibs.FFMTlog.error("Failed to register tools crafting, couldn't handle type "+type);
			}
		} 
		catch(Exception e)
		{
			FFMTLibs.FFMTlog.error("Failed to register tools crafting");
		}
	}

	public static void addAllToolsCrafting(ItemStack material, ItemStack outputAxe, ItemStack outputShovel, ItemStack outputHoe, ItemStack outputPickaxe, ItemStack outputSword, ItemStack stick)
	{
		try
		{
			GameRegistry.addRecipe(outputAxe, new Object[]{"XX", "XS", " S", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputAxe, new Object[]{"XX", "SX", "S ", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputShovel, new Object[]{"X", "S", "S", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputHoe, new Object[]{"XX", " S", " S", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputHoe, new Object[]{"XX", "S ", "S ", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputPickaxe, new Object[]{"XXX", " S ", " S ", 'X', material, 'S', stick});
			GameRegistry.addRecipe(outputSword, new Object[]{"X", "X", "S", 'X', material, 'S', stick});
		} 
		catch(Exception e)
		{
			FFMTLibs.FFMTlog.error("Failed to register tools crafting");
		}
	}
	

	/**
	 * Remove a recipe
	 * 
	 * @param stack 
	 *            The removed ItemStack
	 * @author Moritz
	 * @see http://www.minecraftforge.net/forum/index.php/topic,7146.msg58748.html#msg58748
	 */
	public static void removeRecipe(ItemStack stack)
	{
		List<IRecipe> recipeList = CraftingManager.getInstance().getRecipeList();
		for(int i = 0; i < recipeList.size(); i++)
		{
			IRecipe currentRecipe = recipeList.get(i);
			if(currentRecipe instanceof ShapedRecipes)
			{
				ShapedRecipes shape = (ShapedRecipes)currentRecipe;
				ItemStack output = shape.getRecipeOutput();
				if(ItemStack.areItemStacksEqual(stack, output))
				{
					recipeList.remove(i);
				}
			}

			if(currentRecipe instanceof ShapelessRecipes)
			{
				ShapelessRecipes shapeless = (ShapelessRecipes)currentRecipe;
				ItemStack output = shapeless.getRecipeOutput();
				if(ItemStack.areItemStacksEqual(stack, output))
				{
					recipeList.remove(i);
				}
			}
		}
	}
}