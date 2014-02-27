package fr.minecraftforgefrance.ffmtlibs;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.client.registry.ClientRegistry;
import fr.minecraftforgefrance.ffmtlibs.renderer.ITESRInventoryRenderer;
import fr.minecraftforgefrance.ffmtlibs.renderer.InventoryTESRIndex;
import fr.minecraftforgefrance.ffmtlibs.renderer.TileEntityInventorySpecialRenderer;

public class FFMTClientRegistry
{
	/**
	 * Use this render id for your TESR render
	 */
	public static int tesrRenderId;
	public static HashMap<InventoryTESRIndex, ITESRInventoryRenderer> blockByTESR = new HashMap<InventoryTESRIndex, ITESRInventoryRenderer>();

	/**
	 * Register a render in inventory / hand
	 * 
	 * @authors robin4002
	 * @param block
	 *            - object of your block
	 * @param metadata
	 *            - metadata of the block
	 * @param specialRenderer
	 *            - the TileEntitySpecialRenderer class, it should be implemented ITESRInventoryRenderer
	 */
	public static void bindTESRInventoryRender(Block block, int metadata, ITESRInventoryRenderer specialRenderer)
	{
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}

	/**
	 * Register a render in inventory / hand and world (don't use ClientRegistry.bindTileEntitySpecialRenderer if you use it)
	 * 
	 * @authors robin4002
	 * @param block
	 *            - object of your block
	 * @param metadata
	 *            - metadata of the block
	 * @param tileEntityClass
	 *            - the TileEntity class
	 * @param specialRenderer
	 *            - the TileEntitySpecialRenderer class, it should be extends TileEntityInventorySpecialRenderer
	 */
	public static void bindTESRWithInventoryRender(Block block, int metadata, Class<? extends TileEntity> tileEntityClass, TileEntityInventorySpecialRenderer specialRenderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}
}