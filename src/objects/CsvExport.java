package objects;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class CsvExport {
	
	private String cannonFile = "slayerLogCannon.csv";
	private String burstFile = "slayerLogBurst.csv";
	private String barrageFile = "slayerLogBarrage.csv";
	private String cannonBurstFile = "slayerLogCannonBurst.csv";
	private String cannonballFile = "cannonballLog.csv";
	private String slayerFile = "slayerLog.csv";
	private String tridentFile = "slayerLogTrident.csv";
	
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
		barrageFile = newOutputPath + "\\" + barrageFile;
		tridentFile = newOutputPath + "\\" + tridentFile;
		cannonballFile = newOutputPath+ "\\" + cannonballFile;
		slayerFile = newOutputPath+ "\\" + slayerFile;
		cannonBurstFile = newOutputPath + "\\"+ cannonBurstFile;
	}
	
	/***
	 * Save the current task to log
	 * @param data array of data to save, must follow specific sizes and guidelines
	 */
	public void saveLog(Object[] data) {

		int runes1Used;
		int runes2Used;
		int runes3Used;
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
		}else if(data.length == 6) { // Trident
			//	0		1		2			3			4			  5
			// name, count, profit, Charges left, Current Charges ,  time
			int chargesUsed = ((int)data[4] - (int)data[3]);
			int priceOfCharges = Math.round(chargesUsed*Globals.chargePrice);
			profit = (int)data[2]-priceOfCharges;
			toSave = new Object[] {data[0],data[1],data[2],chargesUsed,priceOfCharges,profit,hms};
			// name, count, net profit, charges used, price of charges, profit, time
			exportFile = tridentFile;
			//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time
		}else if(data.length == 13) { // magic
			//	0		1		2		3		4			5			6				7			8				9			10			11		12	
			// name, count, profit, deathLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterPrice,time
			runes1Used = (int)data[6]- (int)data[3];
			runes2Used = (int)data[7]- (int)data[4];
			runes3Used = (int)data[8]- (int)data[5];
			runeCost = (runes1Used*(int)data[9])+(runes2Used*(int)data[10])+(runes3Used*(int)data[11]);
			profit = (int)data[2]-runeCost;
			toSave = new Object[] {data[0],data[1],data[2],runes1Used,runes2Used,runes3Used,runeCost,profit,hms};
			if(Globals.isBurst()) {
				exportFile = burstFile;
			}else {
				exportFile = barrageFile;
			}
			
			// monsterName,count,loot,deathUsed,chaosUsed,waterUsed,priceOfRunes,profit
		}else if(data.length == 16) {
			//	0		1		2			3				4				5				6			7		8			9				10			11				12			13			14		15
			// name, count, profit, cannonballPrice, cannonballsLeft, cannonballsUsed, deathsLeft, chaosLeft, waterLeft, currentDeath, currentChaos, currentWater, deathPrice, chaosPrice, waterprice,time
			priceOfBalls = Math.round((int)data[5]*(float)data[3]);
			runes1Used = (int)data[9]- (int)data[6];
			runes2Used = (int)data[10]- (int)data[7];
			runes3Used = (int)data[11]- (int)data[8];
			runeCost = (runes1Used*(int)data[12])+(runes2Used*(int)data[13])+(runes3Used*(int)data[14]);
			profit = (int)data[2] - priceOfBalls - runeCost;
			exportFile = cannonBurstFile;
			toSave = new Object[] {data[0],data[1],data[2], data[5], runes1Used, runes2Used, runes3Used, priceOfBalls, runeCost, profit, hms};
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

	public ArrayList<ArrayList<String[]>> getLogs() {
		ArrayList<ArrayList<String[]>> toSend = new ArrayList<ArrayList<String[]>>();
		
		// TODO add the new log URL here
		String[] logOrder = {
				slayerFile,
				cannonFile,
				burstFile,
				barrageFile,
				tridentFile,
				cannonBurstFile
		};
		
		for(int i = 0; i < logOrder.length; i++) {
			ArrayList<String[]> log = new ArrayList<String[]>();
			try {
				String line = "";
				BufferedReader br = new BufferedReader(new FileReader(logOrder[i]));  
				line = br.readLine();
			    while (line != null) {
			        log.add(line.split(","));
			        line = br.readLine();
			    }
	
			    br.close();
			}catch(FileNotFoundException e) {
				log.add(new String[] {"Nothing"});
			}catch (Exception e){
				e.printStackTrace();
			}
			toSend.add(log);
		}
		
				
		return toSend;
	}
	
}
