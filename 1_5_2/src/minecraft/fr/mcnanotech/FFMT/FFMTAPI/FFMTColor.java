package fr.mcnanotech.FFMT.FFMTAPI;

/**
 * @author elias54, kevin_68
 */
public class FFMTColor
{

	/** BLACK **/
	public static int blackInt;
	public static String black;

	/** DARK BLUE **/
	public static int darkBlueInt;
	public static String darkBlue;

	/** DARK GREEN **/
	public static int darkGreenInt;
	public static String darkGreen;

	/** DARK AQUA **/
	public static int darkAquaInt;
	public static String darkAqua;

	/** DARK RED **/
	public static int darkRedInt;
	public static String darkRed;

	/** PURPLE **/
	public static int purpleInt;
	public static String purple;

	/** GOLD **/
	public static int goldInt;
	public static String gold;

	/** GRAY **/
	public static int grayInt;
	public static String gray;

	/** DARK GRAY **/
	public static int darkGrayInt;
	public static String darkGray;

	/** BLUE **/
	public static int blueInt;
	public static String blue;

	/** GREEN **/
	public static int greenInt;
	public static String green;

	/** AQUA **/
	public static int aquaInt;
	public static String aqua;

	/** RED **/
	public static int redInt;
	public static String red;

	/** LIGHT PURPLE **/
	public static int lightPurpleInt;
	public static String lightPurple;

	/** YELLOW **/
	public static int yellowInt;
	public static String yellow;

	/** WHITE **/
	public static int whiteInt;
	public static String white;
	
	/** SPECIALS **/
	public static String obfuscated;
	public static String bold;
	public static String strike;
	public static String underline;
	public static String italic;

	public FFMTColor()
	{
		/** BLACK **/
		blackInt = 0;
		black = "§0";

		/** DARK BLUE **/
		darkBlueInt = 170;
		darkBlue = "§1";

		/** DARK GREEN **/
		darkGreenInt = 170000;
		darkGreen = "§2";

		/** DARK AQUA **/
		darkAquaInt = 170170;
		darkAqua = "§3";

		/** DARK RED **/
		darkRedInt = 170000000;
		darkRed = "§4";

		/** PURPLE **/
		purpleInt = 170000170;
		purple = "§5";

		/** GOLD **/
		goldInt = 255170000;
		gold = "§6";

		/** GRAY **/
		grayInt = 170170170;
		gray = "§7";

		/** DARK GRAY **/
		darkGrayInt = 85085085;
		darkGray = "§8";

		/** BLUE **/
		blueInt = 85085255;
		blue = "§9";

		/** GREEN **/
		greenInt = 85255085;
		green = "§a";

		/** AQUA **/
		aquaInt = 85255255;
		aqua = "§b";

		/** RED **/
		redInt = 255085085;
		red = "§c";

		/** LIGHT PURPLE **/
		lightPurpleInt = 255085255;
		lightPurple = "§d";

		/** YELLOW **/
		yellowInt = 255255085;
		yellow = "§e";

		/** WHITE **/
		whiteInt = 255255255;
		white = "§f";
		
		/** SPECIALS **/
		obfuscated = "§k";
		bold = "§l";
		strike = "§m";
		underline = "§n";
		italic = "§o";
	}

	public static FFMTColor instance()
	{
		return new FFMTColor();
	}

}
