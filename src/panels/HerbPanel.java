package panels;

import java.awt.Color;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import objects.Globals;

@SuppressWarnings("serial")
public class HerbPanel extends JPanel{
	private static ArrayList<JFormattedTextField> textFields = new ArrayList<JFormattedTextField>();
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public JPanel build(int panelWidth, int panelHeight) {
		
		int tripPanelWidth = (panelWidth-Globals.scale(60))/2;
		int tripPanelHeight = Globals.scale(300);
		int tripInputWidth = (tripPanelWidth/2)-Globals.scale(15);
		int rowHeight = Globals.scale(25);
		
		/////////////////////////////
		// Formatter
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(true);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);


		JPanel herbsPanel = new JPanel();
		herbsPanel.setBackground(new Color(0, 153, 51));
		herbsPanel.setBounds((panelWidth/2)-tripPanelWidth-Globals.scale(15), (panelHeight/2)-(tripPanelHeight/2), tripPanelWidth, tripPanelHeight);
		herbsPanel.setLayout(null);
		
		
		
		JLabel tripPanelLabel = new JLabel("Herbs");
		tripPanelLabel.setFont(Globals.mainFont);
		tripPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tripPanelLabel.setBounds(0, 0, tripPanelWidth, rowHeight);
		herbsPanel.add(tripPanelLabel);
		
		
		String herbType[] = {"Ranarr", "Irit", "Avantoe", "Kwuarm", "Cadantine", "Lantadyme", "Dwarf Weed", "Other Stuff"};
		for(int i = 0; i<8;i++) {
			JLabel tempLabel = new JLabel(herbType[i]);
			tempLabel.setFont(Globals.mainFont);
			tempLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tempLabel.setBounds(0, Globals.scale(30*(i+1)), tripPanelWidth/2, rowHeight);
			herbsPanel.add(tempLabel);
			
			JFormattedTextField tempTextFeild = new JFormattedTextField(formatter);
			tempTextFeild.setText("");
			tempTextFeild.setFont(Globals.mainFont);
			tempTextFeild.setBounds(tripPanelWidth/2, Globals.scale(30*(i+1)), tripInputWidth, rowHeight);
			herbsPanel.add(tempTextFeild);
			textFields.add(tempTextFeild);
		}
		
		return herbsPanel;
	}
	
	public int calculateHerbs() {
		// prices on 1/8/2017
		int[] prices = {8388,1153,2435,2691,1598,2050,1130,1};
		int totalPrice = 0;
		
		int counter = 0;
		for(JFormattedTextField i:textFields) {
			//System.out.println(counter);
			if(i.getText().replaceAll(",", "").length() == 0) {
				// I might need this for something
			}else {
				totalPrice += Integer.parseInt(i.getText().replaceAll(",", ""))*prices[counter];
			}
			counter++;
		}
		
		
		return totalPrice;
	}
}
