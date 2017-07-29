import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class Player {
	private int cannonballs;
	private int waterRunes;
	private int chaosRunes;
	private int deathRunes;
	
	
	public Player() {
		cannonballs = 0;
		waterRunes = 0;
		chaosRunes = 0;
		deathRunes = 0;
	}


	public int getCannonballs() {
		return cannonballs;
	}


	public void setCannonballs(int cannonballs) {
		this.cannonballs = cannonballs;
	}


	public int getWaterRunes() {
		return waterRunes;
	}


	public void setWaterRunes(int waterRunes) {
		this.waterRunes = waterRunes;
	}


	public int getChaosRunes() {
		return chaosRunes;
	}


	public void setChaosRunes(int chaosRunes) {
		this.chaosRunes = chaosRunes;
	}


	public int getDeathRunes() {
		return deathRunes;
	}


	public void setDeathRunes(int deathRunes) {
		this.deathRunes = deathRunes;
	}
	
	public void save(){
		String output = cannonballs + "," + waterRunes + "," + chaosRunes + "," + deathRunes;
		System.out.print(output);
		try {
			PrintWriter out = new PrintWriter("saveData.dat");
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
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
		    
		} catch (Exception e){
			e.printStackTrace();
		}
		output();
	}
	public void output() {
		System.out.println(cannonballs + "," + waterRunes + "," + chaosRunes + "," + deathRunes);
	}
	
}
