package testing;

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
import java.util.List;
import java.util.Map;

import objects.ExpTracker;

public class CMLTest {
	public static void main(String[] args) {
		
		ExpTracker test = getGains("Jr2254");
		System.out.println(test.getPlayerTotals(1));
	}
	
	public static void update(String player) {
		
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
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ExpTracker getGains(String player) {
		ArrayList<Integer> daily = new ArrayList<Integer>();
		ArrayList<Integer> weekly = new ArrayList<Integer>();
		ArrayList<Integer> total = new ArrayList<Integer>();
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
					daily.add(Integer.parseInt(line.split(",")[0]));
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
					weekly.add(Integer.parseInt(line.split(",")[0]));
					total.add(Integer.parseInt(line.split(",")[2]));
				}
				//System.out.println(line);
				line = br.readLine();
				count++;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}

		return new ExpTracker(daily, weekly, total);
	}
	
}
