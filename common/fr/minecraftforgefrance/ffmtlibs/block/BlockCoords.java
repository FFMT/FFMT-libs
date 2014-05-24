package fr.minecraftforgefrance.ffmtlibs.block;

public class BlockCoords
{
	private final int x, y, z;
	public BlockCoords(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
}