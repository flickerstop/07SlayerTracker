package objects;

import java.util.ArrayList;

public class ExpTracker {
	// 0		1		2
	//Daily, Weekly, Total
	private double[] overall;
	private double[] attack;
	private double[] defence;
	private double[] strength;
	private double[] hitpoints;
	private double[] range;
	private double[] prayer;
	private double[] magic;
	private double[] cooking;
	private double[] woodcutting;
	private double[] fletching;
	private double[] fishing;
	private double[] firemaking;
	private double[] crafting;
	private double[] smithing;
	private double[] mining;
	private double[] herblore;
	private double[] agility;
	private double[] thieving;
	private double[] slayer;
	private double[] farming;
	private double[] runecrafting;
	private double[] hunter;
	private double[] construction;
	public String accountName;
	
	
	public ExpTracker(ArrayList<Double> daily, ArrayList<Double> weekly, ArrayList<Double> total, String account) {
		construction = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		hunter = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		runecrafting = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		farming = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		slayer = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		thieving = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		agility = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		herblore = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		mining = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		smithing = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		crafting = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		firemaking = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		fishing = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		fletching = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		woodcutting = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		cooking = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		magic = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		prayer = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		range = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		hitpoints = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		strength = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		defence = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		attack = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
		overall = new double[]{daily.remove(daily.size()-1), weekly.remove(weekly.size()-1), total.remove(total.size()-1)};
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
	
	public double[] getDailyGains() {
		return new double[] {
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
	
	public double[] getWeeklyGains() {
		return new double[] {
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
	
	public double[] getTotalExp() {
		return new double[] {
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
