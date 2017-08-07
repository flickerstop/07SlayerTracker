package objects;

import java.awt.Color;

public class Monsters {
	//  0		1			2					3					4
	// name, slayer Exp, image location, background color, foreground colour
	private static Object[][] monsters = {
			{"No Monster Set",0,"question_mark.png",new Color(173,87,53),new Color(255,255,255)},
			{"Aberrant Spectres",90,"Aberrant_spectre.png",new Color(163, 247, 150),new Color(0,0,0)},
			{"Abyssal Demon",150,"Abyssal_demon.png",new Color(94,23,19),new Color(255,255,255)},
			{"Abyssal Sire",450,"Abyssal_Sire.png",new Color(116,44,30),new Color(255,255,255)},
			{"Ankou",60,"Ankou.png",new Color(114,76,74),new Color(255,255,255)},
			{"Aviansie",90,"Aviansie.png",new Color(168,169,179),new Color(255,255,255)},
			{"Baby Black Dragon",80,"Baby_black_dragon.png",new Color(93,88,88),new Color(255,255,255)},
			{"Barrows Brothers",110,"Verac_the_Defiled.png",new Color(101,41,60),new Color(255,255,255)},
			{"Black Demon",160,"Black_demon.png",new Color(35,33,33),new Color(255,255,255)},
			{"Black Dragon",200,"Black_dragon.png",new Color(0,0,0),new Color(255,255,255)},
			{"Bloodvelds",120,"Bloodveld.png",new Color(204, 106, 99),new Color(255,255,255)},
			{"Blue Dragons",107,"Blue_dragon.png",new Color(15, 47, 255),new Color(255,255,255)},
			{"Brine rat",50,"Brine_rat.png",new Color(151,122,121),new Color(255,255,255)},
			{"Brutal Black Dragon",347,"Brutal_black_dragon.png",new Color(28,25,25),new Color(255,255,255)},
			{"Callisto",312,"Callisto.png",new Color(93,87,87),new Color(255,255,255)},
			{"Cave Horror",55,"Cave_horror.png",new Color(103,103,96),new Color(255,255,255)},
			{"Cave Kraken",125,"Cave_kraken.png",new Color(153,123,39),new Color(255,255,255)},
			{"Kraken (boss)",255,"Cave_kraken.png",new Color(153,123,39),new Color(255,255,255)},
			{"Cerberus",690,"Cerberus.png",new Color(157,38,29),new Color(255,255,255)},
			{"Chaos Elemental",250,"Chaos_Elemental.png",new Color(106,97,120),new Color(255,255,255)},
			{"Chaos Fanatic",253,"Chaos_Fanatic.png",new Color(185,185,175),new Color(255,255,255)},
			{"Commander Zilyana",350,"Commander_Zilyana.png",new Color(84,110,176) ,new Color(255,255,255)},
			{"Crazy archaeologist",275,"Crazy_archaeologist.png",new Color(29,107,123),new Color(255,255,255)},
			{"Dagannoth Kings",331,"Dagannoth_Rex.png",new Color(106,100,86),new Color(255,255,255)},
			{"Dagannoths",90,"Dagannoth.png",new Color(99, 99, 99),new Color(255,255,255)},
			{"Dark Beast",226,"Dark_beast.png",new Color(52,42,30),new Color(255,255,255)},
			{"Demonic Gorilla",408,"Demonic_gorilla.png",new Color(99,188,81),new Color(255,255,255)},
			{"Dust Devils",105,"Dust_devil.png",new Color(255, 196, 94),new Color(255,255,255)},
			{"Elves",108,"Elf_warrior.png",new Color(247, 140, 238),new Color(255,255,255)},
			{"Fire Giants",111,"Fire_giant.png",new Color(255, 72, 56),new Color(255,255,255)},
			{"Gargoyle",105,"garg.png",new Color(61, 61, 61),new Color(255,255,255)},
			{"General Graardor",338,"General_Graardor.png",new Color(92,115,94),new Color(255,255,255)},
			{"Giant Mole",215,"Giant_Mole.png",new Color(86,73,61),new Color(255,255,255)},
			{"Greater Demon",87,"Greater_demon.png",new Color(165, 9, 9),new Color(255,255,255)},
			{"Hellhound",116,"Hellhound.png",new Color(94,42,30),new Color(255,255,255)},
			{"Ice Trolls",100,"Ice_troll_female.png",new Color(212, 214, 216),new Color(255,255,255)},
			{"Iron Dragon",190,"Iron_dragon.png",new Color(73, 73, 73),new Color(255,255,255)},
			{"K'ril Tsutsaroth",351,"K'ril_Tsutsaroth.png",new Color(206,86,61),new Color(255,255,255)},
			{"Kalphite Guardian (lvl 141)",170,"Kalphite_Guardian.png",new Color(62,80,47),new Color(255,255,255)},
			{"Kalphite Queen",535,"Kalphite_Queen.png",new Color(83,97,53),new Color(255,255,255)},
			{"Kalphite Soldier (lvl 85)",90,"Kalphite_Soldier.png",new Color(100,118,83),new Color(255,255,255)},
			{"Kalphite Worker (lvl 28)",40,"Kalphite_Worker.png",new Color(99, 124, 91),new Color(255,255,255)},
			{"King Black Dragon",258,"King_Black_Dragon.png",new Color(47,47,34),new Color(255,255,255)},
			{"Kree'arra",357,"Kree'arra.png",new Color(93,81,58),new Color(255,255,255)},
			{"Kurask",97,"Kurask.png",new Color(53,58,15),new Color(255,255,255)},
			{"Lizardmen Shaman",157,"Lizardman_shaman.png",new Color(179,178,144),new Color(255,255,255)},
			{"Lizardmen",60,"Lizardman.png",new Color(163, 239, 139),new Color(255,255,255)},
			{"Mithril Dragon",273,"Mithril_dragon.png",new Color(136,154,171),new Color(255,255,255)},
			{"Mountain Troll",90,"Mountain_troll.png",new Color(121,111,86),new Color(255,255,255)},
			{"Nechryaels",210,"Nechryael.png",new Color(252, 174, 148),new Color(255,255,255)},
			{"Red Dragon",143,"Red_dragon.png",new Color(113,33,41),new Color(255,255,255)},
			{"Scabarites",85,"Locust_rider_(melee).png",new Color(93,72,33),new Color(255,255,255)},
			{"Scorpia",260,"Scorpia.png",new Color(83,77,77),new Color(255,255,255)},
			{"Smoke Devils",185,"Smoke_devil.png",new Color(124, 120, 112),new Color(255,255,255)},
			{"Spitiual Mage",85,"Spiritual_mage_(Saradomin).png",new Color(63,139,244),new Color(255,255,255)},
			{"Spitiual Ranger",120,"Spiritual_ranger_(Zamorak).png",new Color(140,45,40),new Color(255,255,255)},
			{"Spitiual Warrior",110,"Spiritual_warrior_(Saradomin).png",new Color(102,116,146),new Color(255,255,255)},
			{"Steel Dragon",262,"Steel_dragon.png",new Color(255, 255, 255),new Color(0,0,0)},
			{"Suqah",107,"Suqah.png",new Color(100,108,101),new Color(255,255,255)},
			{"Thermonuclear smoke devil",240,"Smoke_devil.png",new Color(120,127,150),new Color(255,255,255)},
			{"Turoth",80,"Turoth.png",new Color(151,106,104),new Color(255,255,255)},
			{"TzHaar",120,"TzHaar-Xil.png",new Color(41,39,39),new Color(255,255,255)},
			{"Venenatis",389,"Venenatis.png",new Color(131,44,40),new Color(255,255,255)},
			{"Vet'ion",312,"Vet'ion.png",new Color(120,28,118),new Color(255,255,255)},
			{"Waterfiend",128,"Waterfiend.png",new Color(91,155,146),new Color(255,255,255)},
			{"Wyverns",210,"Skeletal_Wyvern.png",new Color(163, 239, 139),new Color(255,255,255)},
			{"Zulrah",500,"Zulrah.png",new Color(187,207,61),new Color(255,255,255)},
			{"Zygomite",70,"Zygomite_(lv_86).png",new Color(173,87,53),new Color(255,255,255)}
			
	};
	
	
	
	public static Object[] getMonster (String monsterName) {
		int i = 0;
		for(Object[] monster : monsters) {
			if(monster[0].equals(monsterName)) {
				return monsters[i];
			}
			i++;
		}
		return null;
	}
	
	public static String[] getListOfMonsters() {
		String[] temp = new String[monsters.length];
		int i = 0;
		for(Object[] monster : monsters) {
			temp[i] = (String)monster[0];
			i++;
		}
		return temp;
	}
	
	public static Object[] getQuestionMark() {
		return new Object[]{"No Monster Set",0,"question_mark.png",new Color(173,87,53),new Color(255,255,255)};
	}
	
}
