package fr.minecraftforgefrance.ffmtlibs.event;

public class ModelRenderEntry
{
	private static int texX, texY, dimX, dimY, dimZ, texWidth, texHeight, var, operation, axe;
	private static float posX, posY, posZ, rotaX, rotaY, rotaZ, value;
	private static boolean isMirror, followHead;

	public ModelRenderEntry(int texX, int texY, float posX, float posY, float posZ, int dimX, int dimY, int dimZ, float rotaX, float rotaY, float rotaZ, int texWidth, int texHeight, boolean isMirror, boolean followHead, int var, int operation, float value, int axe)
	{
		this.texX = texX;
		this.texY = texY;
		this.dimX = dimX;
		this.dimY = dimY;
		this.dimZ = dimZ;
		this.texWidth = texWidth;
		this.texHeight = texHeight;
		this.var = var;
		this.operation = operation;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
		this.rotaX = rotaX;
		this.rotaY = rotaY;
		this.rotaZ = rotaZ;
		this.value = value;
		this.isMirror = isMirror;
		this.followHead = followHead;
		this.axe = axe;
	}
	
	public static Box getBox()
	{
		return new Box(posX, posY, posZ, dimX, dimY, dimZ);
	}
	
	public static int[] texture()
	{
		return new int[] {texX, texY, texWidth, texHeight};
	}
	
	public static float[] rotation()
	{
		return new float[] {rotaX, rotaY, rotaZ};
	}
	
	public static boolean mirror()
	{
		return isMirror;
	}
	
	public static ModelRotation getModelRota()
	{
		return new ModelRotation(followHead, var, operation, value, axe);
	}
	
	public static class Box
	{
		private int dimX, dimY, dimZ;
		private float posX, posY, posZ;
		
		public Box(float posX, float posY, float posZ, int dimX, int dimY, int dimZ)
		{
			this.dimX = dimX;
			this.dimY = dimY;
			this.dimZ = dimZ;
			this.posX = posX;
			this.posY = posY;
			this.posZ = posZ;
		}
		
		public int dimX()
		{
			return dimX;
		}
		
		public int dimY()
		{
			return dimY;
		}
		
		public int dimZ()
		{
			return dimZ;
		}
		
		public float posX()
		{
			return posX;
		}
		public float posY()
		{
			return posY;
		}
		public float posZ()
		{
			return posZ;
		}
	}
	
	public static class ModelRotation
	{
		private int operation, var, axe;
		private float value;
		private boolean followHead;
		
		public ModelRotation(boolean followHead, int var, int operation, float value, int axe)
		{
			this.var = var;
			this.operation = operation;
			this.followHead = followHead;
			this.value = value;
			this.axe = axe;
		}
		
		public int operation()
		{
			return operation;
		}
		
		public int var()
		{
			return var;
		}
		
		public float value()
		{
			return value;
		}
		
		public boolean followHead()
		{
			return followHead;
		}
		
		public int axe()
		{
			return axe;
		}
	}
}