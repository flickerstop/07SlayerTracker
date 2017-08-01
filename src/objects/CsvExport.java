package objects;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class CsvExport {
	
	private String cannonFile = "slayerLogCannon.csv";
	private String burstFile = "slayerLogBurst.csv";
	private String cannonBurstFile = "slayerLogCannonBurst.csv";
	private String cannonballFile = "cannonballLog.csv";
	private String slayerFile = "slayerLog.csv";
	
	public CsvExport() {
		
	}
	
	/**
	 * Used for testing purposes to output to the same file
	 * 
	 * @param newOutputFile filename to export to (eg. "test.csv")
	 */
	public CsvExport(String newOutputPath) {
		cannonFile = newOutputPath + "\\" +cannonFile;
		burstFile = newOutputPath + "\\" + burstFile;
		cannonballFile = newOutputPath+ "\\" + cannonballFile;
		slayerFile = newOutputPath+ "\\" + slayerFile;
		cannonBurstFile = newOutputPath + "\\"+ cannonBurstFile;
	}
	
	/***
	 * Save the current task to log
	 * @param data array of data to save, must follow specific sizes and guidelines
	 */
	public void saveLog(Object[] data) {
		int deathsUsed;
		int chaosUsed;
		int watersUsed;
		int runeCost;
		int priceOfBalls;
		int profit;
		String exportFile = "test.csv";
		Object[] toSave = null;
		long time = (long)data[data.length-1];
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
	            TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
	            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
		if(data.length == 4) {// without cannon/burst
			// {name, count, profit}
			exportFile = slayerFile;
			toSave = new Object[] {data[0],data[1],data[2],hms};
		}else if(data.length == 7) { // cannon
			// name, count, profit, cannonballPrice, cannonballsLeft, cannonballsUsed,time
			priceOfBalls = Math.round((float)data[3]*(int)data[5]);
			profit = (int)data[2]-priceOfBalls;
			toSave = new Object[] {data[0],data[1],data[2],data[4],data[5],priceOfBalls,profit,hms};
			exportFile = cannonFile;
			//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time
		}else if(data.length == 13) { // burst
			//	0		1		2		3		4			5			6				7			8				9			10			11		12	
			// name, count, profit, deathLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterPrice,time
			deathsUsed = (int)data[6]- (int)data[3];
			chaosUsed = (int)data[7]- (int)data[4];
			watersUsed = (int)data[8]- (int)data[5];
			runeCost = (deathsUsed*(int)data[9])+(chaosUsed*(int)data[10])+(watersUsed*(int)data[11]);
			profit = (int)data[2]-runeCost;
			toSave = new Object[] {data[0],data[1],data[2],deathsUsed,chaosUsed,watersUsed,runeCost,profit,hms};
			exportFile = burstFile;
			// monsterName,count,loot,deathUsed,chaosUsed,waterUsed,priceOfRunes,profit
		}else if(data.length == 16) {
			//	0		1		2			3				4				5				6			7		8			9				10			11				12			13			14		15
			// name, count, profit, cannonballPrice, cannonballsLeft, cannonballsUsed, deathsLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterprice,time
			priceOfBalls = Math.round((int)data[5]*(float)data[3]);
			deathsUsed = (int)data[9]- (int)data[6];
			chaosUsed = (int)data[10]- (int)data[7];
			watersUsed = (int)data[11]- (int)data[8];
			runeCost = (deathsUsed*(int)data[12])+(chaosUsed*(int)data[13])+(watersUsed*(int)data[14]);
			profit = (int)data[2] - priceOfBalls - runeCost;
			exportFile = cannonBurstFile;
			toSave = new Object[] {data[0],data[1],data[2], data[5], deathsUsed, chaosUsed, watersUsed, priceOfBalls, runeCost, profit, hms};
			// name, count, loot, cannonballsUsed, deathsUsed, chaosUsed, waterUsed, priceOfBalls, priceOfRunes, profit, time
		}
		
		String output = "";
		for(int i = 0; i <toSave.length;i++) {
			//System.out.println(toSave[i]);
			output += toSave[i];
			if(i+1 < toSave.length) {
				output += ",";
			}
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(exportFile, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Update the cannonball price log
	 * @param amount Amount of balls bought
	 * @param price Price paid for balls
	 */
	public void updateCannonballLog(int amount, int price) {
		String output = price*amount+","+amount+","+price;
		try {
			PrintWriter out = new PrintWriter(new FileWriter(cannonballFile, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/***
	 * Calculate the average price of cannonballs using the saved log
	 * @return average price of cannonballs
	 */
	public float getAvgCannonballPrice() {
		int amountOfEntries = 0;
		int totalCost = 0;
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(cannonballFile));  
			line = br.readLine();
		    while (line != null) {
		        String[] temp = line.split(",");
		        totalCost += Integer.parseInt(temp[2]);
		        amountOfEntries++;
		        line = br.readLine();
		    }

		    br.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return totalCost/amountOfEntries;
		
	}
	
	/***
	 * Save the data to a slayer log. Which log is determined by the length of the array passed
	 * @param toSave Array containing data to save. Must be validated
	 */
	public void save(Object[] toSave){
		
		String output = "";
		for(int i = 0; i <toSave.length;i++) {
			//System.out.println(toSave[i]);
			output += toSave[i];
			if(i+1 < toSave.length) {
				output += ",";
			}
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(cannonFile, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
