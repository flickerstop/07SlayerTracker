
public class PlayerTester {
	public static void main (String args[]) {
		Player test = new Player();
		
		test.load();
		//test.updateCannonbals(1000, 10);
		
		//out(test.getAvgCannonballPrice());
		
		out(test.getCannonballs());
		test.finishBurstTask("test monster", 69, 100000, 10, 10, 10);
		out(test.getCannonballs());
		test.save();
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
