package geTracker;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import javax.swing.table.DefaultTableModel;

import objects.GEPrices;
import objects.Globals;
import objects.Item;

import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Toolkit;
import java.awt.Font;

public class GETrackerMainUI {

	private static JFrame mainFrame;
	private static JTable table;
	private static float scale = Globals.scale;


	/**
	 * Initialize the contents of the frame.
	 */
	private static void build() {
		System.out.println("PING");
		mainFrame = new JFrame();
		mainFrame.setFont(new Font("Arial", Font.PLAIN, Math.round(12*scale)));
		//System.out.print(frmJrsGeTracker.getFont().toString());
		mainFrame.setResizable(false);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(GETrackerMainUI.class.getResource("/images/download_icon.png")));
		mainFrame.setTitle("Jr2254's GE Tracker");
		mainFrame.setBounds(Math.round(100*scale), Math.round(100*scale), Math.round(564*scale), Math.round(471*scale));
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, Math.round(538*scale), Math.round(427*scale));
		mainFrame.getContentPane().add(scrollPane);
		
		
		
		
		 Object[][] data = new Object[GEPrices.getAmount()][4];
		 
		 int i = 0;
		 for (Item item : GEPrices.getItems()) {
			 data[i][0] = item.getName();
			 data[i][1] = item.getBuyPrice();
			 data[i][2] = item.getSellPrice();
			 data[i][3] = item.getDifference();
			 i++;
		 }
		 
		 /*Object[][] data = {
            {"Name1", 500, 200,10},
            {"Name2", 500, 200,100},
            {"Name3", 600, 300,400},
            {"Name4", 1000, 100,500},
        };*/
        Object[] columns = {"Name", "Buy Price", "Sell Price", "Difference"};
        DefaultTableModel model = new DefaultTableModel(data,columns) {
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return Integer.class;
                    case 2:
                        return Integer.class;
                    case 3:
                        return Integer.class;
                    default:
                        return String.class;
                }
            }
        };
		
        table = new JTable(model);
        table.setRowHeight(Math.round(15*scale));
        table.setEnabled(false);
        table.setFillsViewportHeight(true);
        table.setRowSelectionAllowed(false);
        table.setShowVerticalLines(false);
		table.setBackground(Color.WHITE);
		table.setFont(new Font("Arial", Font.PLAIN, Math.round(12*scale)));
		table.setAutoCreateRowSorter(true);
		scrollPane.setViewportView(table);
		mainFrame.setLocationByPlatform(true);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
	}
	
	public static void showPanel() {
		if(mainFrame == null) {
			build();
		}else {
			mainFrame.setVisible(true);
		}
	}
	
}
