package objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Player {
	private static int cannonballs;
	private static int waterRunes;
	private static int chaosRunes;
	private static int deathRunes;
	private static float avgCannonballPrice;
	private static CsvExport csv;
	private static int deathPrice = 258; // prices on 30/07/2017
	private static int chaosPrice = 101;
	private static int waterPrice = 5;
	
	/***
	 * Constructor for player object
	 */
	public Player() {
		cannonballs = 0;
		waterRunes = 0;
		chaosRunes = 0;
		deathRunes = 0;
		avgCannonballPrice = 0;
		csv = new CsvExport();
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
		this.cannonballs += amountToAdd;
		this.avgCannonballPrice = csv.getAvgCannonballPrice();
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
		this.waterRunes = waterRunes;
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
		this.chaosRunes = chaosRunes;
	}

	/**
	 * Get the amount of death runes
	 * @return amount of deaths runes
	 */
	public int getDeathRunes() {
		return deathRunes;
	}

	/**
	 * Set the amount of death runes
	 * @param deathRunes amount of death runes
	 */
	public void setDeathRunes(int deathRunes) {
		this.deathRunes = deathRunes;
	}
	
	/**
	 * Save the player data to file
	 */
	public void save(){
		String output = cannonballs + "," + waterRunes + "," + chaosRunes + "," + deathRunes+","+avgCannonballPrice;
		//System.out.println(output);
		try {
			PrintWriter out = new PrintWriter("saveData.dat");
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
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader("saveData.dat"));
		    line = br.readLine();
		    
		    br.close();
		    
		    String[] data = line.split(",");
		    cannonballs = Integer.parseInt(data[0]);
		    waterRunes = Integer.parseInt(data[1]);
		    chaosRunes = Integer.parseInt(data[2]);
		    deathRunes = Integer.parseInt(data[3]);
		    avgCannonballPrice = Float.parseFloat(data[4]);
		    
		} catch (Exception e){
			e.printStackTrace();
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
		this.avgCannonballPrice = avgCannonballPrice;
	}
	
	/***
	 * Run when a task is finished
	 * @param monsterName Name of monster killed
	 * @param monsterCount Number of monsters killed
	 * @param loot Price of loot from monster
	 * @param ballsLeft Number of balls left
	 */
	public void finishCannonTask(String monsterName, int monsterCount, int loot, int ballsLeft) {
		csv.saveCannonLog(monsterName, monsterCount, loot, avgCannonballPrice, ballsLeft, (cannonballs-ballsLeft));
		cannonballs = ballsLeft;
		save();
	}
	
	/***
	 * Run when a task is finished
	 * @param monsterName Name of monster killed
	 * @param monsterCount Number of monsters killed
	 * @param loot Price of loot from monster
	 * @param ballsLeft Number of balls left
	 * @param time Time in milliseconds the task took
	 */
	public void finishCannonTask(String monsterName, int monsterCount, int loot, int ballsLeft, long time) {
		System.out.println(cannonballs);
		System.out.println(ballsLeft);
		System.out.println(avgCannonballPrice);
		csv.saveCannonLog(monsterName, monsterCount, loot, avgCannonballPrice, ballsLeft, (cannonballs-ballsLeft),time);
		cannonballs = ballsLeft;
		save();
	}
	
	public void finishBurstTask(String monsterName, int monsterCount, int loot, int deathLeft, int chaosLeft, int waterLeft) {
		csv.saveBurstLog(monsterName, monsterCount, loot, deathLeft, chaosLeft, waterLeft, deathRunes, chaosRunes, waterRunes,deathPrice,chaosPrice,waterPrice);
		
		deathRunes = deathLeft;
		chaosRunes = chaosLeft;
		waterRunes = waterLeft;
		save();
	}
	
}
