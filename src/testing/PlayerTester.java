package testing;
import objects.Monsters;

public class PlayerTester {
	public static void main (String args[]) {
		String[] temp = Monsters.getListOfMonsters();
		
		for(String i:temp) {
			out(i);
		}
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
