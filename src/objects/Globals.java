package objects;

import java.awt.Color;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.sound.sampled.Clip;

import panels.LoadingPopUp;

public class Globals {
	
	/////////////////////////////
	// SAVE EDIT MODE
	// Is it safe to edit the files
	public static boolean isSafeEdit = true;
	public static String versionNumber = "0.8.0";
	public static boolean showTimers = false;
	private static long startTime;
	
	public static String path = System.getenv("APPDATA")+"\\SlayerTracker";
	public static String savePath = System.getenv("APPDATA")+"\\SlayerTracker\\player.sav";
	public static String settingsFile = path+"\\settings.sav";
	public static String herbRunFile = path+"\\herbRun.csv";
	public static String errorFile = path+"\\error.log";
	public static String outputFile = path+"\\output.log";
	public static String monstersFile = path+"\\monsters.csv";
	///////
	//////////////////////////////////
	// From Player
	
	public static int cannonballs = 0;
	public static int waterRunes = 0;
	public static int chaosRunes = 0;
	public static int deathRunes = 0;
	public static int bloodRunes = 0;
	public static int triCharges = 0;
	public static float avgCannonballPrice = 0;
	private static CsvExport csv;

	
	////////////////////
	
	public static String[] CMLaccounts = {
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null,
			null
	};
	
	
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
	public static Color titleColor = new Color(214, 214, 214);
	public static Color normalThemeWhite =new Color(240, 240, 240);
	
	public static Color buttonBackground = new Color(240, 240, 240);
	public static Color topBarColour = black;
	public static Color panelBackground = new Color(240, 240, 240);
	public static Color buttonForground = new Color(0,0,0);
	public static Color tripsBackground = new Color(255, 51, 51);
	public static Color otherInfoBackground = new Color(0, 204, 255);
	
	public static long farmTimerStart;
	public static long farmTimerStop;
	
	public static String magicType = "Ice Burst";
	
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
	
	public static String[] magicTypes = {
			"Ice Burst",
			"Ice Barrage",
			"Trident of the Seas"
	};
	
	public static String[][] runeCosts = {
			{"Death Runes", "Chaos Runes", "Water Runes"},
			{"Death Runes", "Blood Runes", "Water Runes"},
			{"Charges",null,null}
	};
	
	public static int[] runeAmounts;
	
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
	public static int bloodPrice = 0;
	public static int chargePrice = 0;
	
	public static void reInitData() {
		loadRuneAmounts();
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
		savePlayer();
		ArrayList<Object> toSave = new ArrayList<Object>(Arrays.asList(scale,
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
				farmTimerStop,
				magicType
				));
		for(String account : CMLaccounts) {
			toSave.add(account);
		}
		
		String output = "";
		for(Object item : toSave) {
			output += item;
			output += "&";
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
		outCurrentTime();
		LoadingPopUp.setText("Grabbing Player data");
		if(isSafeEdit) {
			csv = new CsvExport();
			Globals.savePath = "player.sav";
		}else {
			csv = new CsvExport(Globals.path);
		}
		outCurrentTime();
		GEPrices.getPrices();
		outCurrentTime();
		LoadingPopUp.setText("Setting Herb Prices");
		if(herbType.equals("Ranarr")) {
			herbPrice = GEPrices.getItem("Ranarr weed").getSellPrice();
		}else if(herbType.equals("Irit")) {
			herbPrice = GEPrices.getItem("Irit leaf").getSellPrice();
		}else {
			herbPrice = GEPrices.getItem(herbType).getSellPrice();
		}
		seedPrice = GEPrices.getItem(herbType+" seed").getBuyPrice();
		outCurrentTime();
		LoadingPopUp.setText("Setting Rune Prices");
		resurrectprice = (
				(GEPrices.getItem("Soul rune").getBuyPrice()*8)+
				(GEPrices.getItem("nature rune").getBuyPrice()*12)+
				(GEPrices.getItem("blood rune").getBuyPrice()*8)+
				(GEPrices.getItem("earth rune").getBuyPrice()*25)
				);
		
		bloodPrice = GEPrices.getItem("blood rune").getBuyPrice();
		chaosPrice = GEPrices.getItem("chaos rune").getBuyPrice();
		deathPrice = GEPrices.getItem("death rune").getBuyPrice();
		waterPrice = GEPrices.getItem("water rune").getBuyPrice();
		chargePrice = deathPrice + chaosPrice + (GEPrices.getItem("fire rune").getBuyPrice() * 5) + 10;
		outCurrentTime();
		if(isSafeEdit) {
			savePath = "player.sav";
			settingsFile = "settings.sav";
			herbRunFile = "herbRun.csv";
			errorFile = "error.log";
			monstersFile = "monsters.csv";
		}
		prefMonsters = new Object[(numberOfRows * numberOfCols)][5];
		outCurrentTime();
		LoadingPopUp.setText("Loading Prefered Monsters");
		getPrefMonsters();
		outCurrentTime();
		String line = "";
		try {		
			LoadingPopUp.setText("Grabbing User Settings");
			BufferedReader br = new BufferedReader(new FileReader(settingsFile));
		    line = br.readLine();
		    br.close();
		} catch (FileNotFoundException e){
			save();
			return;
		}catch (Exception e){
			return;
		}
		outCurrentTime();
		try {
			LoadingPopUp.setText("Setting User Settings");
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
			magicType = temp[17];
			
			for(int i = 18; i < temp.length;i++) {
				System.out.println(i-17);
				CMLaccounts[i-18] = temp[i];
			}
			//System.out.println(farmTimerStart);
		}catch(ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
			save();
			load();
		}
		outCurrentTime();
		loadPlayer();
		outCurrentTime();
		LoadingPopUp.setText("Finishing up...");
		reInitData();
		outCurrentTime();
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
	
	public static String[] getRuneTypes() {
		int count = 0;
		for(String i:magicTypes) {
			if(i.equals(magicType)) {
				return runeCosts[count];
			}
			count++;
		}
		return null;
	}
	
	public static boolean isBurst() {
		return magicTypes[0]==magicType;
	}
	public static boolean isBarrage() {
		return magicTypes[1]==magicType;
	}
	public static boolean isTrident() {
		return magicTypes[2]==magicType;
	}
	
	public static void updateCannonbals(int amountToAdd, int price) {
		csv.updateCannonballLog(amountToAdd, price);
		cannonballs += amountToAdd;
		avgCannonballPrice = csv.getAvgCannonballPrice();
	}
	
	public static void savePlayer(){
		String output = cannonballs + "," + 
				waterRunes + "," + 
				chaosRunes + "," + 
				deathRunes+","+
				avgCannonballPrice+","+
				bloodRunes+","+
				triCharges;
		//System.out.println(output);
		try {
			PrintWriter out = new PrintWriter(Globals.savePath);
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadPlayer() {
//		System.out.println(savePath);
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(Globals.savePath));
		    line = br.readLine();
		    
		    br.close();
		    
		    String[] data = line.split(",");
		    cannonballs = Integer.parseInt(data[0]);
		    waterRunes = Integer.parseInt(data[1]);
		    chaosRunes = Integer.parseInt(data[2]);
		    deathRunes = Integer.parseInt(data[3]);
		    avgCannonballPrice = Float.parseFloat(data[4]);
		    bloodRunes = Integer.parseInt(data[5]);
		    triCharges = Integer.parseInt(data[6]);
		    
		} catch (FileNotFoundException e){
			new File(Globals.path).mkdir();
			save();
		}catch (Exception e){
			e.printStackTrace();
			save();
		}
		
		//output();
	}
	
	public static void finishTask(Object[] data) {
		// 4 = task without cannon
			// {name, count, profit,time}
		// 5 = cannon 
			// {name, count, profit, cannonballLeft, time}
		// 7 = burst 
			// {name, count, profit, deathRunesLeft, chaosRunesLeft, waterRunesLeft, time}
		// 8 = burst & cannon
			// {name, count, profit, cannonballLeft, deathRunesLeft, chaosRunesLeft, waterRunesLeft, time}
		Object[] toSend = null;
		if(data.length == 4) {
			// without cannon/burst
			Object[] temp = {data[0], data[1], data[2],data[3]};
			toSend = temp;
		}else if(data.length == 5) {
			// cannon
			// name, count, profit, cannonballPrice, cannonballsLeft, cannonballs Used,time
			Object[] temp = {data[0], data[1], data[2], avgCannonballPrice, data[3], (cannonballs-(int)data[3]),data[4]};
			toSend = temp;
			cannonballs = (int)data[3];
		}else if(data.length == 7) {
			Object[] temp;
			// magic
			// name, count, profit, deathLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterPrice,time
			if(isBurst()) {
				temp = new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], deathRunes, chaosRunes, waterRunes,Globals.deathPrice,Globals.chaosPrice,Globals.waterPrice,data[6]};
				deathRunes = (int)data[3];
				chaosRunes = (int)data[4];
				waterRunes = (int)data[5];
			}else if(isBarrage()){
				temp = new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], deathRunes, bloodRunes, waterRunes,Globals.deathPrice,Globals.bloodPrice,Globals.waterPrice,data[6]};
				deathRunes = (int)data[3];
				bloodRunes = (int)data[4];
				waterRunes = (int)data[5];
			}else {
				temp = new Object[]{data[0], data[1], data[2], data[3], triCharges,data[6]};
				triCharges = (int) data[3];
			}
			toSend = temp;
		}
		else if(data.length == 8) {
			// burst/cannon
			// name, count, profit, cannonballPrice, cannonballsLeft, cannonballs Used, deathsLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterprice,time
			Object[] temp;
			if(Globals.isBurst()) {
				temp = new Object[]{data[0], data[1], data[2], avgCannonballPrice, data[3], (cannonballs-(int)data[3]), data[4], data[5], data[6], deathRunes, chaosRunes, waterRunes,Globals.deathPrice,Globals.chaosPrice,Globals.waterPrice,data[7]};
				deathRunes = (int)data[4];
				chaosRunes = (int)data[5];
				waterRunes = (int)data[6];
			}else{
				temp = new Object[]{data[0], data[1], data[2], avgCannonballPrice, data[3], (cannonballs-(int)data[3]), data[4], data[5], data[6], deathRunes, bloodRunes, waterRunes,Globals.deathPrice,Globals.bloodPrice,Globals.waterPrice,data[7]};
				deathRunes = (int)data[4];
				bloodRunes = (int)data[5];
				waterRunes = (int)data[6];
			}
			toSend = temp;
			
			cannonballs = (int)data[3];
		}
		csv.saveLog(toSend);
	}
	
	public static ArrayList<ArrayList<String[]>> getLogs() {
		return csv.getLogs();
	}
	
	public static void loadRuneAmounts() {
		/*
		 * {"Death Runes", "Chaos Runes", "Water Runes"},
			{"Death Runes", "Blood Runes", "Water Runes"},
			{"Charges",null,null}
		 */
		
		// Burst
		if(magicType.equals(magicTypes[0])) {
			runeAmounts = new int[] {deathRunes,chaosRunes,waterRunes};
		}
		// Blitz
		else if (magicType.equals(magicTypes[1])) {
			runeAmounts = new int[] {deathRunes,bloodRunes,waterRunes};
		}
		// Trident
		else if (magicType.equals(magicTypes[2])) {
			runeAmounts = new int[] {triCharges,-1,-1};
		}
	}
	
	public static void updateRuneNumbers(int[] runes) {
		for(int i = 0; i< 3; i++) {
			if(getRuneTypes()[i] == null) {
				continue;
			}
			switch(getRuneTypes()[i]) {
			case "Chaos Runes":
				chaosRunes = runes[i];
				break;
			case "Death Runes":
				deathRunes = runes[i];
				break;
			case "Blood Runes":
				bloodRunes = runes[i];
				break;
			case "Water Runes":
				waterRunes = runes[i];
				break;
			case "Charges":
				triCharges = runes[i];
				break;
			}
		}
	}
	
	public static void startTimer() {
		if(showTimers && !isSafeEdit) {
			showTimers = false;
		}
		if(showTimers) {
			startTime = System.currentTimeMillis();
		}
		
	}
	
	public static void outCurrentTime(String output) {
		if(showTimers) {
			System.err.println(System.currentTimeMillis()-startTime +" @ " + output);
			startTime = System.currentTimeMillis();
		}
	}
	public static void outCurrentTime(int output) {
		if(showTimers) {
			System.err.println(System.currentTimeMillis()-startTime +" @ " + output);
			startTime = System.currentTimeMillis();
		}
	}
	public static void outCurrentTime() {
		StackTraceElement l = new Exception().getStackTrace()[1];
		if(showTimers) {
			System.err.println(System.currentTimeMillis()-startTime +"ms @ " + l.getClassName()+"/"+l.getMethodName()+":"+l.getLineNumber());
			startTime = System.currentTimeMillis();
		}
	}
	public static int getNumberOfCMLAccounts() {
		int count = 0;
		for(String account : CMLaccounts) {
			if(account == null || account.equals("null") || account.equals("")) {
				
			}else {
				count++;
			}
		}
		return count;
	}
	
	public static String[] getCMLAccounts() {
		ArrayList<String> accounts = new ArrayList<String>();
		for(String account: CMLaccounts) {
			if(account == null || account.equals("null") || account.equals("")) {
				
			}else {
				accounts.add(account);
			}
		}
		return accounts.toArray(new String[accounts.size()]);
	}
}
