package fr.minecraftforgefrance.ffmtlibs;

import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import fr.minecraftforgefrance.ffmtlibs.client.renderer.ITESRInventoryRenderer;
import fr.minecraftforgefrance.ffmtlibs.client.renderer.InventoryTESRIndex;
import fr.minecraftforgefrance.ffmtlibs.client.renderer.TileEntityInventorySpecialRenderer;

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
	 * @author robin4002
	 * @param block Object of your block
	 * @param metadata Metadata of the block
	 * @param specialRenderer The TileEntitySpecialRenderer class, it should be implemented ITESRInventoryRenderer
	 */
	public static void bindTESRInventoryRender(Block block, int metadata, ITESRInventoryRenderer specialRenderer)
	{
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}

	/**
	 * Register a render in inventory / hand and in the world (don't use ClientRegistry.bindTileEntitySpecialRenderer if you use it)
	 *
	 * @author robin4002
	 * @param block Object of your block
	 * @param metadata Metadata of the block
	 * @param tileEntityClass The TileEntity class
	 * @param specialRenderer The TileEntitySpecialRenderer class, it should be extended TileEntityInventorySpecialRenderer
	 */
	public static void bindTESRWithInventoryRender(Block block, int metadata, Class<? extends TileEntity> tileEntityClass, TileEntityInventorySpecialRenderer specialRenderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}
}