package testing;
import objects.Player;
import panels.FarmRunPanel;

public class PlayerTester {
	public static void main (String args[]) {
//		Player test = new Player();
//		
//		test.load();
		//test.updateCannonbals(1000, 10);
		
		//out(test.getAvgCannonballPrice());
		FarmRunPanel test = new FarmRunPanel();
		test.build(null,true);
//		int arrayLength = (int) Math.round((Math.random()*100));
//		String[] testArray = new String[arrayLength];
//		for(int i = 0; i < testArray.length; i++) {
//			testArray[i] = i+"";
//		}
//
//		for(int i = 0; i < testArray.length; i++) {
//			out(testArray[i]);
//		}
//		//TODO write every other number in the array
//		
//		
//		
//		out(testArray.length);
//		
////		out(test.getCannonballs());
////		test.finishBurstTask("test monster", 69, 100000, 10, 10, 10);
////		out(test.getCannonballs());
////		test.save();
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
