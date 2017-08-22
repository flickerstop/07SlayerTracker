package objects;

import java.util.ArrayList;

public class ExpTracker {
	// 0		1		2
	//Daily, Weekly, Total
	private int[] overall;
	private int[] attack;
	private int[] defence;
	private int[] strength;
	private int[] hitpoints;
	private int[] range;
	private int[] prayer;
	private int[] magic;
	private int[] cooking;
	private int[] woodcutting;
	private int[] fletching;
	private int[] fishing;
	private int[] firemaking;
	private int[] crafting;
	private int[] smithing;
	private int[] mining;
	private int[] herblore;
	private int[] agility;
	private int[] thieving;
	private int[] slayer;
	private int[] farming;
	private int[] runecrafting;
	private int[] hunter;
	private int[] construction;
	public String accountName;
	
	
	public ExpTracker(ArrayList<Integer> daily, ArrayList<Integer> weekly, ArrayList<Integer> total, String account) {
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
		accountName = account.replaceAll("\\+", " ");
	}

	/***
	 * 
	 * @param dataNum 0 - Daily
	 * @param dataNum 1 - Weekly
	 * @param dataNum 2 - Total
	 * @return String with the data to output with 
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
	
	public int[] getDailyGains() {
		return new int[] {
				overall[0],
				attack[0],
				defence[0],
				strength[0],
				hitpoints[0],
				range[0],
				prayer[0],
				magic[0],
				cooking[0],
				woodcutting[0],
				fletching[0],
				fishing[0],
				firemaking[0],
				crafting[0],
				smithing[0],
				mining[0],
				herblore[0],
				agility[0],
				thieving[0],
				slayer[0],
				farming[0],
				runecrafting[0],
				hunter[0],
				construction[0]
		};
	}
	
	public int[] getWeeklyGains() {
		return new int[] {
				overall[1],
				attack[1],
				defence[1],
				strength[1],
				hitpoints[1],
				range[1],
				prayer[1],
				magic[1],
				cooking[1],
				woodcutting[1],
				fletching[1],
				fishing[1],
				firemaking[1],
				crafting[1],
				smithing[1],
				mining[1],
				herblore[1],
				agility[1],
				thieving[1],
				slayer[1],
				farming[1],
				runecrafting[1],
				hunter[1],
				construction[1]
		};
	}
	
	public int[] getTotalExp() {
		return new int[] {
				overall[2],
				attack[2],
				defence[2],
				strength[2],
				hitpoints[2],
				range[2],
				prayer[2],
				magic[2],
				cooking[2],
				woodcutting[2],
				fletching[2],
				fishing[2],
				firemaking[2],
				crafting[2],
				smithing[2],
				mining[2],
				herblore[2],
				agility[2],
				thieving[2],
				slayer[2],
				farming[2],
				runecrafting[2],
				hunter[2],
				construction[2]
		};
	}
}
