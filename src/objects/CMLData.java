package objects;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import panels.LoadingPopUp;

public class CMLData {
//	public static void main(String[] args) {
//		
//		ExpTracker test = getGains("Jr2254");
//		System.out.println(test.getPlayerTotals(1));
//	}
	private static boolean hasAttempted = false;
	
	public static ExpTracker update(String player) {
		
		try {
			URLConnection connection = new URL("http://crystalmathlabs.com/tracker/api.php?type=update&player="+player).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
	
			BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
	
			String line;
			String code = "";
			line = br.readLine();
			while (line != null) {
				code = line;
				line = br.readLine();
			}
			if(!code.equals("1")) {
				System.err.println("Error while attempting to update data for "+player);
				System.err.println("Error code "+code);
				return null;
			}else {
				return getGains(player);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static ExpTracker getGains(String player) {
		ArrayList<Double> daily = new ArrayList<Double>();
		ArrayList<Double> weekly = new ArrayList<Double>();
		ArrayList<Double> total = new ArrayList<Double>();
		try {
			URLConnection connection = new URL("http://crystalmathlabs.com/tracker/api.php?type=track&player="+player+"&time=1d").openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
	
			BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
	
			String line;
			line = br.readLine();
			int count = 0;
			while (line != null) {
				if(count > 0) {
					daily.add(Double.parseDouble(line.split(",")[0]));
				}
				//System.out.println(line);
				line = br.readLine();
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		try {
			URLConnection connection = new URL("http://crystalmathlabs.com/tracker/api.php?type=track&player="+player).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
	
			BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
	
			String line;
			line = br.readLine();
			int count = 0;
			while (line != null) {
				if(count > 0) {
					weekly.add(Double.parseDouble(line.split(",")[0]));
					total.add(Double.parseDouble(line.split(",")[2]));
				}
				//System.out.println(line);
				line = br.readLine();
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return new ExpTracker(daily, weekly, total, player);
	}
	
	public static ExpTracker[] getMultipleAccounts (String[] accounts) {
		//" ~~ "
		
		String url = "[";
		int numberOfAccounts = accounts.length;
		ExpTracker[] output = null;

		// remove spaces
		for(int i = 0; i < accounts.length; i++) {
			accounts[i] = accounts[i].replaceAll("\\ ", "+");
		}
		Globals.outCurrentTime();
		for(int i = 0; i < accounts.length; i++) {
			String temp = "{\"type\":\"track\",\"player\":\""+accounts[i]+"\",\"time\":\"1d\"}";
			temp += ",";
			url += temp;
		}
		for(int i = 0; i < accounts.length; i++) {
			String temp = "{\"type\":\"track\",\"player\":\""+accounts[i]+"\",\"time\":\"7d\"}";
			if(i+1 < accounts.length) {
				temp += ",";
			}
			url += temp;
		}
		url += "]";
		Globals.outCurrentTime();
		url = "http://crystalmathlabs.com/tracker/api.php?multiquery=" + url;
		System.out.println(url);
		ArrayList<ArrayList<Double>> daily = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> weekly = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> total = new ArrayList<ArrayList<Double>>();
		LoadingPopUp.setProgressBar(20);
		LoadingPopUp.setText("Connecting to CML for data");
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			LoadingPopUp.addProgressBar(10);
			BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			//System.out.println("PING");
			String line = "";
			String temp = "";
			while (line != null) {
				line = br.readLine();
				//System.out.println(line);
				temp += line+"z";
				LoadingPopUp.addProgressBar(0.1f);
			}
			String[] calls = temp.split("~~");
			Globals.outCurrentTime();
			for(int i=0;i<calls.length-1;i++) {
				LoadingPopUp.addProgressBar(2);
				ArrayList<Double> dataSet = new ArrayList<Double>();
				ArrayList<Double> dataSet2 = new ArrayList<Double>();
				String[] set = calls[i].split("z");
				for(String row : set) {
					//System.out.println(row);
					if(row.split(",").length < 3) { // first row is always garbage
						continue;
					}
					if(i < numberOfAccounts) {
						dataSet.add(Double.parseDouble(row.split(",")[0]));
					}else {
						dataSet.add(Double.parseDouble(row.split(",")[0]));
						dataSet2.add(Double.parseDouble(row.split(",")[2]));
					}
					//System.out.println(Double.parseInt(row.split(",")[0]));
				}
				//System.out.println("\n\n");
				if(i < numberOfAccounts) {
					daily.add(dataSet);
				}else {
					weekly.add(dataSet);
					total.add(dataSet2);
				}
			}
			Globals.outCurrentTime();
		
		
			output = new ExpTracker[numberOfAccounts];
			for(int i = 0; i < numberOfAccounts; i++) {
				output[i] = new ExpTracker(daily.get(i),weekly.get(i),total.get(i),accounts[i]);
				LoadingPopUp.addProgressBar(2);
			}
		}catch(Exception e) {
			if(hasAttempted) {
				System.err.println("Second attempt failed!!");
				e.printStackTrace();
				return null;
			}else {
				hasAttempted = true;
				LoadingPopUp.setProgressBar(10);
				LoadingPopUp.setText("Error in data, Attempting to update CML then try again!");
				System.err.println("Error attempting to grab players from CML!\n");
				System.err.println("Attempt to update then try again!");
				String [] newList = updateMultiple(accounts);
				LoadingPopUp.addProgressBar(10);
				if(newList == null) {
					LoadingPopUp.setText("Error, Could not update!");
					return null;
				}else {
					return getMultipleAccounts(newList);
				}
			}
		}

		return output;
	}
	
	public static String[] updateMultiple(String[] accounts) {
		LoadingPopUp.addProgressBar(5);
		String url = "[";
		Globals.outCurrentTime();
		ArrayList<String> errors = new ArrayList<String>();
		for(int i = 0; i < accounts.length; i++) {
			String temp = "{\"type\":\"update\",\"player\":\""+accounts[i]+"\"}";
			if(i+1 < accounts.length) {
				temp += ",";
			}
			url += temp;
		}
		url += "]";
		
		url = "http://crystalmathlabs.com/tracker/api.php?multiquery=" + url;
		System.out.println(url);
		try {
			URLConnection connection = new URL(url).openConnection();
			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			connection.connect();
			BufferedReader br  = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
			
			String line;
			line = br.readLine();
			int count = 0;
			
			while (line != null) {
				
				
				if(line.matches("^([\\d]*|[-][\\d]*)$")) {
					if(line.equals("2")) {
						errors.add(accounts[count]);
						System.out.println("PING "+ errors.size());
					}
					count++;
				}
				LoadingPopUp.addProgressBar(1);
				line = br.readLine();
			}
			LoadingPopUp.addProgressBar(5);
			if(errors.size() > 0) {
				
				ArrayList<String> currentList = new ArrayList<String>(Arrays.asList(accounts));
				for(String name : errors) {
					System.err.println(name);
					currentList.remove(name);
				}
				String[] newList = new String[currentList.size()];
				return currentList.toArray(newList);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		Globals.outCurrentTime();
		
		return accounts;
	}
}
