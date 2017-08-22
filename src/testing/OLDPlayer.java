package testing;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import objects.CsvExport;
import objects.Globals;


public class OLDPlayer {
	/////////////////////////////
	// SAVE EDIT MODE
	// Is it safe to edit the cannonballs/runes
	private static boolean isSafeEdit = Globals.isSafeEdit; 
	//////////////////////////////
	private static int cannonballs;
	private static int waterRunes;
	private static int chaosRunes;
	private static int deathRunes;
	private static int bloodRunes;
	private static int triCharges;
	private static float avgCannonballPrice;
	private static CsvExport csv;

	/***
	 * Constructor for player object
	 */
	public OLDPlayer() {
		cannonballs = 0;
		waterRunes = 0;
		chaosRunes = 0;
		deathRunes = 0;
		bloodRunes = 0;
		setTridentCharges(0);
		avgCannonballPrice = 0;
		if(isSafeEdit) {
			csv = new CsvExport();
			Globals.savePath = "OLDPlayer.sav";
		}else {
			csv = new CsvExport(Globals.path);
		}
	}

	/***
	 * 
	 * @return amount of cannonballs
	 */
	public int getCannonballs() {
		return cannonballs;
	}

	/***
	 * 
	 * @param amountToAdd amount of cannonballs bought
	 * @param price price for cannonballs
	 */
	public void updateCannonbals(int amountToAdd, int price) {
		csv.updateCannonballLog(amountToAdd, price);
		OLDPlayer.cannonballs += amountToAdd;
		OLDPlayer.avgCannonballPrice = csv.getAvgCannonballPrice();
	}

	/***
	 * Get the amount of water runes
	 * @return amount of water runes
	 */
	public int getWaterRunes() {
		return waterRunes;
	}

	/***
	 * Set the amount of water runes
	 * @param waterRunes amount of water runes
	 */
	public void setWaterRunes(int waterRunes) {
		OLDPlayer.waterRunes = waterRunes;
	}

	/***
	 * Get the amount of chaos runes
	 * @return amount of chaos runes
	 */
	public int getChaosRunes() {
		return chaosRunes;
	}

	/**
	 * Set the amount of chaos runes
	 * @param chaosRunes amount of chaos runes
	 */
	public void setChaosRunes(int chaosRunes) {
		OLDPlayer.chaosRunes = chaosRunes;
	}

	/**
	 * Get the amount of death runes
	 * @return amount of deaths runes
	 */
	public int getDeathRunes() {
		return deathRunes;
	}
	
	public boolean isSafeEdit() {
		return isSafeEdit;
	}

	/**
	 * Set the amount of death runes
	 * @param deathRunes amount of death runes
	 */
	public void setDeathRunes(int deathRunes) {
		OLDPlayer.deathRunes = deathRunes;
	}
	
	/**
	 * Save the player data to file
	 */
	public void save(){
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
	
	/**
	 * Load the player data to variables
	 */
	public void load() {
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
	
	/**
	 * Print the player data nicely
	 */
	public void output() {
		System.out.println(cannonballs + "," + waterRunes + "," + chaosRunes + "," + deathRunes+","+avgCannonballPrice);
	}

	/***
	 * Get the average price of cannonballs
	 * @return average price of cannonballs
	 */
	public float getAvgCannonballPrice() {
		return avgCannonballPrice;
	}

	/***
	 * Set the average price of cannonballs
	 * @param avgCannonballPrice average price of cannonballs
	 */
	public void setAvgCannonballPrice(float avgCannonballPrice) {
		OLDPlayer.avgCannonballPrice = avgCannonballPrice;
	}
	
	/***
	 * Save and finish the current task
	 * @param data array of data to save, must follow specific sizes and guidelines
	 */
	public void finishTask(Object[] data) {
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
			if(Globals.isBurst()) {
				temp = new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], deathRunes, chaosRunes, waterRunes,Globals.deathPrice,Globals.chaosPrice,Globals.waterPrice,data[6]};
				deathRunes = (int)data[3];
				chaosRunes = (int)data[4];
				waterRunes = (int)data[5];
			}else {
				temp = new Object[]{data[0], data[1], data[2], data[3], data[4], data[5], deathRunes, bloodRunes, waterRunes,Globals.deathPrice,Globals.bloodPrice,Globals.waterPrice,data[6]};
				deathRunes = (int)data[3];
				bloodRunes = (int)data[4];
				waterRunes = (int)data[5];
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

	public ArrayList<ArrayList<String[]>> getLogs() {
		return csv.getLogs();
	}

	public int getBloodRunes() {
		return bloodRunes;
	}

	public void setBloodRunes(int bloodRunes) {
		OLDPlayer.bloodRunes = bloodRunes;
	}

	public int getTridentCharges() {
		return triCharges;
	}

	public static void setTridentCharges(int triCharges) {
		OLDPlayer.triCharges = triCharges;
	}
	
}
