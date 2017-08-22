package testing;
import objects.CMLData;
import objects.ExpTracker;
import objects.Monsters;

public class PlayerTester {
	public static void main (String args[]) {
		//String[] temp = Monsters.getListOfMonsters();
		
//		String[] players = {
//    			"Jr2254",
//    			"metalSpike0",
//    			"fblthp792",
//    			"iSharky",
//    			"Flowerpower9",
//    			"History vR",
//    			"Wind Vortex",
//    			"Zaidious66"
//    	};
		String[] players = {
    			"Jr2254",
    			"Wind Vortex"
    	};
		
		ExpTracker[] player = CMLData.getMultipleAccounts(players);
		
		System.out.println("\n\nJr2254");
		System.out.println(player[0].getPlayerTotals(1));
		System.out.println("\n\nWind");
		System.out.println(player[1].getPlayerTotals(1));
	}
	
	public static void out(String output) {
		System.out.println(output);
	}
	public static void out(int output) {
		System.out.println(output);
	}
	public static void out(float output) {
		System.out.println(output);
	}
}
