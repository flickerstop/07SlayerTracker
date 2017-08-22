package objects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import panels.LoadingPopUp;


public class GEPrices {
	
	private static ArrayList<Item> items;
	
	private static float loadPercentPerItem = 70.0f/3324.0f; // percent for loading / number of items
	
	public static Item getItem(String itemName) {
		for(Item item : items) {
			if(item.getName().equalsIgnoreCase(itemName)) {
				return item;
			}
		}
		return null;
	}
	
	public static void getPrices() {
		URL url;
	    InputStream is = null;
	    BufferedReader br;
	    String api ="";
	    
	    ArrayList<Item> GE = new ArrayList<Item>();

	    try {
	        url = new URL("https://rsbuddy.com/exchange/summary.json");
	        is = url.openStream();  // throws an IOException
	        br = new BufferedReader(new InputStreamReader(is));

	        api = br.readLine();

	    } catch (Exception e) {
	    	e.printStackTrace();
	    } finally {
	        try {
	            if (is != null) is.close();
	        } catch (IOException ioe) {
	            // nothing to see here
	        }
	    }
	    api = api.substring(1);

	    //Split entire json into item rows
	    String[] apiSplit = api.split("}, ");
	    
	    LoadingPopUp.setProgressBar(20);
	    
	    // LOOP HERE
	    for(int geCount = 0; geCount < apiSplit.length; geCount++) {
	    	//System.out.println("~~~~~~~~~~~~~~~~~\n");
		    Item item = new Item();

		    apiSplit[geCount] = apiSplit[geCount].replaceAll("\\{|:|,|}}", "");
		    
		    String[] data = apiSplit[geCount].split("(\" \")|(\" )|( \")|\"");
		    
		    /*for(int i =0; i<data.length-1;i++) {
		    	out(data[i]);
		    }*/
		    LoadingPopUp.setText("Grabbing GE prices");
		    for(int i =0; i<data.length-1;i++) {
		    	//System.out.println("~~~~~~~~~~~~~~~~~\n");
		    	//out(data[i]);
		    	//out(data[i+1]);
		    	switch(data[i]) {
			    	case "buy_average":
			    		item.setBuyPrice(Integer.parseInt(data[i+1]));
			    		break;
			    	case "id":
			    		item.setId(Integer.parseInt(data[i+1]));
			    		break;
			    	case "members":
			    		item.setMembers(data[i+1]);
			    		break;
			    	case "name":
			    		item.setName(data[i+1]);
			    		break;
			    	case "overall_average":
			    		item.setOverallPrice(Integer.parseInt(data[i+1]));
			    		break;
			    	case "sp":
			    		item.setSp(Integer.parseInt(data[i+1]));
			    		break;
			    	case "sell_average":
			    		item.setSellPrice(Integer.parseInt(data[i+1]));
			    		break;
			    	default:
			    		//out("Do Nothing..");
			    		break;
		    	}
		    }
		    
		    LoadingPopUp.addProgressBar(loadPercentPerItem);
		    GE.add(item);
	    }
	    items = GE;
	}
}
