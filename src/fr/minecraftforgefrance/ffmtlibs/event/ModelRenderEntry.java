package fr.minecraftforgefrance.ffmtlibs.event;

public class ModelRenderEntry
{
	private int texX, texY, dimX, dimY, dimZ, texWidth, texHeight, var, operation, axe;
	private float posX, posY, posZ, rotaX, rotaY, rotaZ, value;
	private boolean isMirror, followHead;

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
	
	public Box getBox()
	{
		return new Box(this.posX, this.posY, this.posZ, this.dimX, this.dimY, this.dimZ);
	}
	
	public int[] texture()
	{
		return new int[] {this.texX, this.texY, this.texWidth, this.texHeight};
	}
	
	public float[] rotation()
	{
		return new float[] {this.rotaX, this.rotaY, this.rotaZ};
	}
	
	public boolean mirror()
	{
		return this.isMirror;
	}
	
	public ModelRotation getModelRota()
	{
		return new ModelRotation(this.followHead, this.var, this.operation, this.value, this.axe);
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
			return this.dimX;
		}
		
		public int dimY()
		{
			return this.dimY;
		}
		
		public int dimZ()
		{
			return this.dimZ;
		}
		
		public float posX()
		{
			return this.posX;
		}
		public float posY()
		{
			return this.posY;
		}
		public float posZ()
		{
			return this.posZ;
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
			return this.operation;
		}
		
		public int var()
		{
			return this.var;
		}
		
		public float value()
		{
			return this.value;
		}
		
		public boolean followHead()
		{
			return this.followHead;
		}
		
		public int axe()
		{
			return this.axe;
		}
	}
}