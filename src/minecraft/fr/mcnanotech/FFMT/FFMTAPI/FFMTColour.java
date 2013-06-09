package fr.mcnanotech.FFMT.FFMTAPI;

public class FFMTColour {

	/** RED **/
	public static String red;
	public static int redInt;
	
	/** WHITE **/
	public static int whiteInt;
	public static String white;
	
	/** GREEN **/
	public static int greenInt;
	
	
	/** LIGHT GREEN **/
	public static int greenLightInt;
	public static String greenLight;
	
	/** GRAY **/
	public static int grayGuiInt;
	public static String gray;
	
	/** BLACK **/
	public static int blackInt;
	public static String black;
	
	public FFMTColour()
	{
		/** RED **/
		red = "\247c";
		redInt = 0xFF0000;
		
		/** WHITE **/
		whiteInt = 0xFFFFFF;
		white = "\247f";
		
		/** GREEN **/
		greenInt = 0x1B7600;
		greenLightInt = 0x00FF08;
		greenLight = "\247a";
		
		
		/** GRAY (GUI) **/
		grayGuiInt = 0x404040;
		gray = "\2477";
		
		/** BLACK **/
		blackInt = 0;
		black = "\2470";
	}
	
	public static FFMTColour instance()
	{
		return new FFMTColour();
	}
	
}
