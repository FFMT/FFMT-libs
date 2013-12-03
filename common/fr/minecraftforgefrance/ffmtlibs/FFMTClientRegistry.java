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
	public static int tesrRenderId;
	public static HashMap<InventoryTESRIndex, ITESRInventoryRenderer> blockByTESR = new HashMap<InventoryTESRIndex, ITESRInventoryRenderer>();
	
	public static void bindTESRInventoryRender(Block block, int metadata, ITESRInventoryRenderer specialRenderer)
	{
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}
	
	public static void bindTESRWithInventoryRender(Block block, int metadata, Class<? extends TileEntity> tileEntityClass, TileEntityInventorySpecialRenderer specialRenderer)
	{
		ClientRegistry.bindTileEntitySpecialRenderer(tileEntityClass, specialRenderer);
		blockByTESR.put(new InventoryTESRIndex(block, metadata), specialRenderer);
	}
}