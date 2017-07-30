import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CsvExport {
	
	private String cannonFile = "slayerLogCannon.csv";
	private String burstFile = "slayerLogBurst.csv";
	private String cannonballFile = "cannonballLog.csv";
	
	public CsvExport() {
		
	}
	
	/**
	 * Used for testing purposes to output to the same file
	 * 
	 * @param newOutputFile filename to export to (eg. "test.csv")
	 */
	public CsvExport(String newOutputFile) {
		cannonFile = newOutputFile;
		burstFile = newOutputFile;
		cannonballFile = newOutputFile;
	}
	
	/***
	 * Saves the slayer task to the log
	 * @see {@link #save(Object[])}
	 * @param monsterName Name of the monster
	 * @param monsterCount Number of monsters killed
	 * @param lootAmount Price (gp) of the loot
	 * @param cannonballPrice Average Price of cannonballs
	 * @param cannonballsLeft Amount of cannonballs after the task finished
	 * @param cannonballsUsed Amount of cannonballs used during task
	 */
	public void saveCannonLog(String monsterName, int monsterCount, int lootAmount,float cannonballPrice,int cannonballsLeft, int cannonballsUsed) {
		
		int priceOfBalls = cannonballsUsed*Math.round(cannonballPrice);
		int profit = lootAmount - priceOfBalls;
		
		Object[] toSave = {monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit};
		if(validate(toSave) < 0) {
			System.err.println("SOMETHING HAPPENED! ERROR CODE: "+  validate(toSave));
		}else {
			save(toSave);
		}
	}
	
	public void saveBurstLog(String monsterName, int monsterCount, int lootAmount, int deathLeft, int chaosLeft, int waterLeft, int deathRunes, int chaosRunes, int waterRunes, int deathPrice, int chaosPrice, int waterPrice) {
		

		int deathsUsed = deathRunes - deathLeft;
		int chaosUsed = chaosRunes - chaosLeft;
		int watersUsed = waterRunes - waterLeft;
		int runeCost = (((deathsUsed*2)*deathPrice)+((chaosUsed*4)*chaosPrice)+((watersUsed*4)*waterPrice));
		int profit = lootAmount - runeCost;
		
		Object[] toSave = {monsterName,
							monsterCount,
							lootAmount,
							deathsUsed,
							chaosUsed,
							watersUsed,
							runeCost,
							profit};
		if(validate(toSave) < 0) {
			System.err.println("SOMETHING HAPPENED! ERROR CODE: "+  validate(toSave));
		}else {
			save(toSave);
		}
	}
	
	/**
	 * returned: 2 - burst valid<br>
	 * returned: 1 - cannon valid<br>
	 * returned: -1 - Monster name not valid<br>
	 * returned: -2 - Invalid monster count<br>
	 * returned: -3 - Array improper length<br>
	 * returned: -4 - FREE<br>
	 * returned: -5 - loot amount not valid<br>
	 * returned: -6 - balls left not valid<br>
	 * returned: -7 - balls used not valid<br>
	 * returned: -8 - price of balls used not valid<br>
	 * returned: -9 - profit/loss not valid<br>
	 * returned: -10 - rune amounts not valid<br>
	 * returned: -11 - cast amount not valid<br>
	 * returned: -12 - rune cost isn't valid
	 * 
	 * @param test
	 * @param isCannon
	 * 
	 * @return error code
	 */
	private int validate(Object[] test) {
		// check if name "[a-zA-Z]{2,} [a-zA-Z]{2,}|[a-zA-Z]{2,}"
		//Check if it's cannon or burst
		if(test.length == 7) {

			// Check if monster is correct
			if(!test[0].toString().matches("^\\D* \\D*$|^\\D*$")) {
				return -1;
			}
			// Check if monster amount is correct
			if(!test[1].toString().matches("^[0-9]*$")) {
				return -2;
			}
			// Check if loot amount is correct
			if(!test[2].toString().matches("^[0-9]*$")) {
				return -5;
			}
			// Check if balls left valid
			if(!test[3].toString().matches("^[0-9]*$")) {
				return -6;
			}
			// Check if balls used valid
			if(!test[4].toString().matches("^[0-9]*$")) {
				return -7;
			}
			// Check if price of balls used valid
			if(!test[5].toString().matches("^[0-9]*$")) {
				return -8;
			}
			// Check if profit/loss is valid
			if(!test[6].toString().matches("^[0-9]*$|^-[0-9]*$")) {
				return -9;
			}
			return 1;
		}else if(test.length == 8){
			//Check is proper length of 8
			if(test.length != 8) {
				return -4;
			}
			// Check if monster is correct
			if(!test[0].toString().matches("^\\D* \\D*$|^\\D*$")) {
				return -1;
			}
			// Check if monster amount is correct
			if(!test[1].toString().matches("^[0-9]*$")) {
				return -2;
			}
			
			// Check if loot amount is correct
			if(!test[2].toString().matches("^[0-9]*$")) {
				return -5;
			}
			// Check if death used is correct
			if(!test[3].toString().matches("^[0-9]*$")) {
				return -10;
			}
			// Check if chaos used is correct
			if(!test[4].toString().matches("^[0-9]*$")) {
				return -10;
			}
			// Check if water used is correct
			if(!test[5].toString().matches("^[0-9]*$")) {
				return -10;
			}
			// Check if rune cost is correct
			if(!test[6].toString().matches("^[0-9]*$")) {
				return -12;
			}
			// Check if profit/loss is valid
			if(!test[7].toString().matches("^[0-9]*$|^-[0-9]*$")) {
				return -9;
			}
			
			return 2;
		}else {
			return -3;
		}
		
	}
	
	/***
	 * Update the cannonball price log
	 * @param amount Amount of balls bought
	 * @param price Price paid for balls
	 */
	public void updateCannonballLog(int amount, int price) {
		String output = price+","+amount+","+amount/price;
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
		        totalCost += Integer.parseInt(temp[0]);
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
		//cannonball
		String output = "";
		if(toSave.length == 7) {
			output = toSave[0] + ","+
					toSave[1] + ","+
					toSave[2] + ","+
					toSave[3] + ","+
					toSave[4] + ","+
					toSave[5] + ","+
					toSave[6];
			try {
				PrintWriter out = new PrintWriter(new FileWriter(cannonFile, true)); 
				out.println(output);
				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}else if(toSave.length == 8) {
			output = toSave[0] + ","+
					toSave[1] + ","+
					toSave[2] + ","+
					toSave[3] + ","+
					toSave[4] + ","+
					toSave[5] + ","+
					toSave[6] + ","+
					toSave[7];
			try {
				PrintWriter out = new PrintWriter(new FileWriter(burstFile, true)); 
				out.println(output);
				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
}
