package objects;

import java.util.ArrayList;

public class ExpTracker {
	// 0		1		2
	//Daily, Weekly, Total
	public int[] overall;
	public int[] attack;
	public int[] defence;
	public int[] strength;
	public int[] hitpoints;
	public int[] range;
	public int[] prayer;
	public int[] magic;
	public int[] cooking;
	public int[] woodcutting;
	public int[] fletching;
	public int[] fishing;
	public int[] firemaking;
	public int[] crafting;
	public int[] smithing;
	public int[] mining;
	public int[] herblore;
	public int[] agility;
	public int[] thieving;
	public int[] slayer;
	public int[] farming;
	public int[] runecrafting;
	public int[] hunter;
	public int[] construction;
	
	
	public ExpTracker(ArrayList<Integer> daily, ArrayList<Integer> weekly, ArrayList<Integer> total) {
		construction = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		hunter = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		runecrafting = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		farming = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		slayer = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		thieving = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		agility = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		herblore = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		mining = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		smithing = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		crafting = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		firemaking = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		fishing = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		fletching = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		woodcutting = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		cooking = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		magic = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		prayer = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		range = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		hitpoints = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		strength = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		defence = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		attack = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		overall = new int[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
	}
	
	/***
	 * 
	 * @param dataNum 0 - Daily
	 * @param dataNum 1 - Weekly
	 * @param dataNum 2 - Total
	 * @return String with the data to output with stdout
	 */
	
	public String getPlayerTotals(int dataNum) {
		return "Overall: " + overall[dataNum]+ "\n" + 
				 "Attack: " + attack[dataNum]+ "\n" + 
				 "Defece: " + defence[dataNum]+ "\n" + 
				 "Strength: " + strength[dataNum]+ "\n" + 
				 "Hitpoints: " + hitpoints[dataNum]+ "\n" + 
				 "Range: " + range[dataNum]+ "\n" + 
				 "Prayer: " + prayer[dataNum]+ "\n" + 
				 "Magic: " + magic[dataNum]+ "\n" + 
				 "Cooking: " + cooking[dataNum]+ "\n" + 
				 "Woodcutting: " + woodcutting[dataNum]+ "\n" + 
				 "Fletching: " + fletching[dataNum]+ "\n" + 
				 "Fishing:" + fishing[dataNum]+ "\n" + 
				 "Firemaking:" + firemaking[dataNum]+ "\n" + 
				 "Crafting: " + crafting[dataNum]+ "\n" + 
				 "Smithing: " + smithing[dataNum]+ "\n" + 
				 "Mining: " + mining[dataNum]+ "\n" + 
				 "Herblore: " + herblore[dataNum]+ "\n" + 
				 "Agility: " + agility[dataNum]+ "\n" + 
				 "Thieving: " + thieving[dataNum]+ "\n" + 
				 "Slayer: " + slayer[dataNum]+ "\n" + 
				 "Farming: " + farming[dataNum]+ "\n" + 
				 "Runecrafting: " + runecrafting[dataNum]+ "\n" + 
				 "Hunter: " + hunter[dataNum]+ "\n" + 
				 "Construction: " + construction[dataNum];
	}
}
