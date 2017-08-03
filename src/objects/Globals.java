package objects;

import java.awt.Color;
import java.awt.Font;

public class Globals {
	
	
	public static float scale = 1.0f;
	
	public static int width = scale(640);
	public static int height = scale(550);
	
	public static int topMenuBarHeight = scale(25);
	public static int panelHeight = height-topMenuBarHeight;
	
	public static Font mainFont = new Font("Tahoma", Font.PLAIN, scale(12));
	public static Font boldFont = new Font("Tahoma", Font.BOLD, scale(14));
	public static Font massiveFont = new Font("Tahoma", Font.BOLD, scale(20));
	public static Font smallFont = new Font("Tahoma", Font.BOLD, scale(7));
	
	public static Color black = new Color(39,39,39);
	public static Color white = new Color(255,255,255);
	public static Color green = new Color(46, 204, 113);
	public static Color grey = new Color(150, 150, 150);
	
	public static Color buttonBackground = new Color(240, 240, 240);
	public static Color panelBackground = new Color(240, 240, 240);
	public static Color buttonForground = new Color(0,0,0);
	public static Color tripsBackground = new Color(255, 51, 51);
	public static Color otherInfoBackground = new Color(0, 204, 255);
	
	public static boolean isDarkMode = false;

	/////////////////////////////
	// SAVE EDIT MODE
	// Is it safe to edit the cannonballs/runes
	public static boolean isSafeEdit = false;
	///////

	public static String versionNumber = "0.5.1";
	
	public static void reload() {
		width = scale(640);
		height = scale(550);
		topMenuBarHeight = scale(25);
		panelHeight = height-topMenuBarHeight;
		mainFont = new Font("Tahoma", Font.PLAIN, scale(12));
		boldFont = new Font("Tahoma", Font.BOLD, scale(14));
		massiveFont = new Font("Tahoma", Font.BOLD, scale(20));
		smallFont = new Font("Tahoma", Font.BOLD, scale(7));
		if(isDarkMode) {
			setDarkMode();
		}else {
			setNormalMode();
		}
	}
	public static void setScale (float newScale) {
		scale = newScale;
	}
	
	public static int scale(int numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public static int scale(float numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public static String getScaleString() {
		String temp = (scale*10)+"";
		return temp.replace(".", "")+"%";
	}
	public static void setDarkMode() {
		buttonBackground = black;
		panelBackground = black;
		buttonForground = white;
		tripsBackground = new Color(53, 0, 0);
		otherInfoBackground = new Color(0, 78, 81);
		isDarkMode = true;
	}
	public static void setNormalMode() {
		buttonBackground = new Color(240, 240, 240);
		panelBackground = new Color(240, 240, 240);
		buttonForground = new Color(0,0,0);
		tripsBackground = new Color(255, 51, 51);
		otherInfoBackground = new Color(0, 204, 255);
		isDarkMode = false;
	}
}
