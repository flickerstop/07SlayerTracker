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
	
	public void saveCannonLog(String monsterName, int monsterCount, int lootAmount,float cannonballPrice,int cannonballsLeft, int cannonballsUsed) {
		
		int priceOfBalls = cannonballsUsed*Math.round(cannonballPrice);
		int profit = lootAmount - priceOfBalls;
		
		Object[] toSave = {monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit};
		if(validate(toSave , true) < 0) {
			System.err.println("SOMETHING HAPPENED! ERROR CODE: "+  validate(toSave, true));
		}else {
			save(toSave);
		}
	}
	
	/**
	 * returned: 2 - burst valid<br>
	 * returned: 1 - cannon valid<br>
	 * returned: -1 - Monster name not valid<br>
	 * returned: -2 - Invalid monster count<br>
	 * returned: -3 - cannon array imporper length<br>
	 * returned: -4 - Burst array imporper length<br>
	 * returned: -5 - loot amount not valid<br>
	 * returned: -6 - balls left not valid<br>
	 * returned: -7 - balls used not valid<br>
	 * returned: -8 - price of balls used not valid<br>
	 * returned: -9 - profit/loss not valid<br>
	 * returned: -10 - rune amounts not valid<br>
	 * returned: -11 - cast amount not valid<br>
	 * 
	 * @param test
	 * @param isCannon
	 * 
	 * @return error code
	 */
	private int validate(Object[] test, boolean isCannon) {
		// check if name "[a-zA-Z]{2,} [a-zA-Z]{2,}|[a-zA-Z]{2,}"
		//Check if it's cannon or burst
		if(isCannon) {
			//Check is proper length of 7
			if(test.length != 7) {
				return -3;
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
		}else {
			//Check is proper length of 11
			if(test.length != 11) {
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
			// Check if profit/loss is valid
			if(!test[3].toString().matches("^[0-9]*$")) {
				return -9;
			}
			// validate rune amounts
			if(!test[4].toString().matches("^[0-9]*$")||
					!test[5].toString().matches("^[0-9]*$")||
					!test[6].toString().matches("^[0-9]*$")||
					!test[7].toString().matches("^[0-9]*$")||
					!test[8].toString().matches("^[0-9]*$")||
					!test[9].toString().matches("^[0-9]*$")) {
				return -10;
			}
			// Check if cast amount is valid
			if(!test[10].toString().matches("^[0-9]*$")) {
				return -11;
			}
			
			return 2;
		}
		
	}
	
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

		    
		} catch (Exception e){
			e.printStackTrace();
		}
		return totalCost/amountOfEntries;
		
	}
	
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
		}else if(toSave.length == 11) {
			
		}
		
	}
	
}
