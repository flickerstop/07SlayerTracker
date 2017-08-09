package objects;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.sound.sampled.Clip;

public class Globals {
	
	/////////////////////////////
	// SAVE EDIT MODE
	// Is it safe to edit the files
	public static boolean isSafeEdit = true;
	public static String versionNumber = "0.7.5";
	
	public static String path = System.getenv("APPDATA")+"\\SlayerTracker";
	public static String savePath = System.getenv("APPDATA")+"\\SlayerTracker\\player.sav";
	public static String settingsFile = path+"\\settings.sav";
	public static String herbRunFile = path+"\\herbRun.csv";
	public static String errorFile = path+"\\error.log";
	public static String outputFile = path+"\\output.log";
	public static String monstersFile = path+"\\monsters.csv";
	///////
	
	
	
	public static Object[][] prefMonsters;


	public static int numberOfRows = 5;
	public static int numberOfCols = 7;
	public static int numberOfMonsters = numberOfRows * numberOfCols;
	
	public static float scale = 1.0f;
	
	public static int width = scale(640);
	public static int height = scale(550);
	
	public static int topMenuBarHeight = scale(25);
	public static int panelHeight = height-topMenuBarHeight;
	
	public static Font mainFont = new Font("Tahoma", Font.PLAIN, scale(12));
	public static Font boldFont = new Font("Tahoma", Font.BOLD, scale(14));
	public static Font massiveFont = new Font("Tahoma", Font.BOLD, scale(20));
	public static Font smallFont = new Font("Tahoma", Font.BOLD, scale(7));
	public static Font mediumFont = new Font("Tahoma", Font.BOLD, scale(9));
	
	public static Color black = new Color(39,39,39);
	public static Color darkerBlack = new Color(27,27,27);
	public static Color white = new Color(255,255,255);
	public static Color green = new Color(46, 204, 113);
	public static Color grey = new Color(150, 150, 150);
	public static Color iconGrey = new Color(103, 103, 103);
	public static Color blue = new Color(68,187,255);
	public static Color red = new Color(231, 76, 60);
	public static Color yellow = new Color(241, 196, 15);
	public static Color purple = new Color(155, 89, 182);
	
	public static Color buttonBackground = new Color(240, 240, 240);
	public static Color topBarColour = black;
	public static Color panelBackground = new Color(240, 240, 240);
	public static Color buttonForground = new Color(0,0,0);
	public static Color tripsBackground = new Color(255, 51, 51);
	public static Color otherInfoBackground = new Color(0, 204, 255);
	
	public static long farmTimerStart;
	public static long farmTimerStop;
	
	public static Clip clip;
	
	public static String[] herbTypes = {
			"Snapdragon",
			"Ranarr",
			"Torstol",
			"Irit",
			"Kwuarm",
			"Cadantine",
			"Avantoe"
	};
	
	public static boolean isDarkMode = false;

	//////////////////////
	// Farm Run
	public static int herbPrice = 9964;
	public static int resurrectprice = 5561;
	public static int numberOfPatches = 7;
	public static int seedPrice = 45874;
	public static String herbType = "Snapdragon";
	/////////////////////
	// Runes
	public static int deathPrice = 258; // prices on 30/07/2017
	public static int chaosPrice = 101;
	public static int waterPrice = 5;
	
	public static void reload() {
		if(herbType.equals("Ranarr")) {
			herbPrice = GEPrices.getItem("Ranarr weed").getSellPrice();
		}else if(herbType.equals("Irit")) {
			herbPrice = GEPrices.getItem("Irit leaf").getSellPrice();
		}else{
			herbPrice = GEPrices.getItem(herbType).getSellPrice();
		}
		seedPrice = GEPrices.getItem(herbType+" seed").getBuyPrice();

		resurrectprice = (
				(GEPrices.getItem("Soul rune").getBuyPrice()*8)+
				(GEPrices.getItem("nature rune").getBuyPrice()*12)+
				(GEPrices.getItem("blood rune").getBuyPrice()*8)+
				(GEPrices.getItem("earth rune").getBuyPrice()*25)
				);
		width = scale(640);
		height = scale(550);
		topMenuBarHeight = scale(25);
		panelHeight = height-topMenuBarHeight;
		mainFont = new Font("Tahoma", Font.PLAIN, scale(12));
		boldFont = new Font("Tahoma", Font.BOLD, scale(14));
		massiveFont = new Font("Tahoma", Font.BOLD, scale(20));
		smallFont = new Font("Tahoma", Font.BOLD, scale(7));
		mediumFont = new Font("Tahoma", Font.BOLD, scale(9));
		if(isDarkMode) {
			setDarkMode();
		}else {
			setNormalMode();
		}
		save();
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
		buttonBackground = darkerBlack;
		panelBackground = darkerBlack;
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
	
	public static void save() {
		savePrefMonsters();
		
		Object[] toSave = {scale,
				buttonBackground.getRGB(),
				panelBackground.getRGB(),
				buttonForground.getRGB(),
				tripsBackground.getRGB(),
				otherInfoBackground.getRGB(),
				isDarkMode,
				herbPrice,
				resurrectprice,
				numberOfPatches,
				seedPrice,
				deathPrice,
				chaosPrice,
				waterPrice,
				herbType,
				farmTimerStart,
				farmTimerStop
				};
		String output = "";
		for(int i = 0; i <toSave.length;i++) {
			output += toSave[i];
			if(i+1 < toSave.length) {
				output += "&";
			}
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(settingsFile, false)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void load() {
		GEPrices.getPrices();
		if(herbType.equals("Ranarr")) {
			herbPrice = GEPrices.getItem("Ranarr weed").getSellPrice();
		}else if(herbType.equals("Irit")) {
			herbPrice = GEPrices.getItem("Irit leaf").getSellPrice();
		}else {
			herbPrice = GEPrices.getItem(herbType).getSellPrice();
		}
		seedPrice = GEPrices.getItem(herbType+" seed").getBuyPrice();

		resurrectprice = (
				(GEPrices.getItem("Soul rune").getBuyPrice()*8)+
				(GEPrices.getItem("nature rune").getBuyPrice()*12)+
				(GEPrices.getItem("blood rune").getBuyPrice()*8)+
				(GEPrices.getItem("earth rune").getBuyPrice()*25)
				);
		
		if(isSafeEdit) {
			savePath = "player.sav";
			settingsFile = "settings.sav";
			herbRunFile = "herbRun.csv";
			errorFile = "error.log";
			monstersFile = "monsters.csv";
		}
		prefMonsters = new Object[(numberOfRows * numberOfCols)][5];
		getPrefMonsters();
		String line = "";
		try {		
			BufferedReader br = new BufferedReader(new FileReader(settingsFile));
		    line = br.readLine();
		    br.close();
		} catch (FileNotFoundException e){
			save();
			return;
		}catch (Exception e){
			return;
		}
		try {
			String[] temp = line.split("&");
			scale = Float.parseFloat(temp[0]);
			buttonBackground = new Color(Integer.parseInt(temp[1]));
			panelBackground = new Color(Integer.parseInt(temp[2]));
			buttonForground = new Color(Integer.parseInt(temp[3]));
			tripsBackground = new Color(Integer.parseInt(temp[4]));
			otherInfoBackground = new Color(Integer.parseInt(temp[5]));
			isDarkMode = Boolean.parseBoolean(temp[6]);
			//herbPrice = Integer.parseInt(temp[7]);
			//resurrectprice = Integer.parseInt(temp[8]);
			numberOfPatches = Integer.parseInt(temp[9]);
			//seedPrice = Integer.parseInt(temp[10]);
			deathPrice = Integer.parseInt(temp[11]);
			chaosPrice = Integer.parseInt(temp[12]);
			waterPrice = Integer.parseInt(temp[13]);
			herbType = temp[14];
			farmTimerStart = Long.parseLong(temp[15]);
			farmTimerStop = Long.parseLong(temp[16]);
			System.out.println(farmTimerStart);
		}catch(ArrayIndexOutOfBoundsException e) {
			save();
			load();
		}
		
		reload();
	}
	
	public static void getPrefMonsters() {
		String line = "";
		try {		
			BufferedReader br = new BufferedReader(new FileReader(monstersFile));
		    line = br.readLine();
		    br.close();
		} catch (FileNotFoundException e){
			line = null;
		}catch (Exception e){
			line = null;
			System.err.println(e.getMessage());
		}
		
		if(line != null) {
			String[] monstersString = line.split(",");
			
			for(int i = 0; i < numberOfMonsters; i++) {
				prefMonsters[i] = Monsters.getMonster(monstersString[i]);
			}
		}else {
			for(int i = 0; i < numberOfMonsters; i++) {
				prefMonsters[i] = Monsters.getQuestionMark();
			}
		}
	}
	
	public static void savePrefMonsters() {
		String output = "";
		for(Object[] monster : prefMonsters) {
			output += monster[0] + ",";
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(monstersFile, false)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
