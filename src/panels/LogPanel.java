package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objects.Globals;
import objects.Monsters;
import ui.SlayerTrackerUI;
import ui.SlayerTrackerUI.FrameDragListener;


public class LogPanel {
	
	static JFrame mainFrame;
	static JTable[] tables;
	static JTable logTable;
	
	static boolean[] hasLog;
	
	static Object[][][] data;
	
	static JButton[] buttons;
	
	static String[] buttonString;
	
	static int width = Globals.scale(1250);
	static int height = Globals.scale(550);
	/**
	 * @wbp.parser.entryPoint
	 */
	private static void build() {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	ArrayList<ArrayList<String[]>> log = Globals.getLogs();
            	
        		int panelHeight = height-Globals.topMenuBarHeight;
        		
        		mainFrame = new JFrame("Test");
            	mainFrame.getContentPane().setBackground(Globals.panelBackground);
        		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/slayerIcon.png")));
            	mainFrame.setLocationByPlatform(true);
        		mainFrame.setTitle("Slayer Logs\r\n");
        		mainFrame.setBounds(100, 100, width, height);
        		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		mainFrame.getContentPane().setLayout(null);
        		mainFrame.setUndecorated(true);
        		mainFrame.setBackground(Globals.panelBackground);
        		
				//////////////////////////////
				// Top Bar
        		
				FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
				JPanel topBar = new JPanel();
				topBar.setBounds(0, 0, width, Globals.topMenuBarHeight);
				topBar.setBackground(Globals.topBarColour);
				topBar.addMouseListener(frameDragListener);
				topBar.addMouseMotionListener(frameDragListener);
				topBar.setLayout(null);
				mainFrame.getContentPane().add(topBar);
				
				JButton closeButton = new JButton();
				closeButton.setFocusPainted(false);
				closeButton.setIcon(new ImageIcon(SlayerTrackerUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
				closeButton.setBackground(new Color(231, 76, 60));
				closeButton.setBounds(width-Globals.scale(25), 0, Globals.scale(25), Globals.scale(25));
				closeButton.setMargin(new Insets(0, 0, 0, 0));
				closeButton.setToolTipText("Close Window");
				closeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
					}
				});
				topBar.add(closeButton);
				
				
				JButton minimizeButton = new JButton();
				minimizeButton.setFocusPainted(false);
				{
					ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/minimize.png")); // load the image to a imageIcon
					Image image = imageIcon.getImage(); // transform it 
					Image newimg = image.getScaledInstance(Globals.scale(25), Globals.scale(25),  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
					imageIcon = new ImageIcon(newimg);  // transform it back
					
					minimizeButton.setIcon(imageIcon);
				}
				minimizeButton.setBackground(new Color(52, 152, 219));
				minimizeButton.setBounds(width-Globals.scale(50), 0, Globals.scale(25), Globals.scale(25));
				minimizeButton.setMargin(new Insets(0, 0, 0, 0));
				minimizeButton.setToolTipText("Minimize Window");
				minimizeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						mainFrame.setState(Frame.ICONIFIED);
					}
				});
				topBar.add(minimizeButton);
			
				
				
				JLabel titleLabel = new JLabel();
				titleLabel.setBounds(Globals.scale(5),0,width/2,Globals.scale(25));
				titleLabel.setText("Logs Panel v"+Globals.versionNumber);
				titleLabel.setForeground(new Color(214, 214, 214));
				titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
				titleLabel.setFont(Globals.mainFont);
				topBar.add(titleLabel);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
        		
        		
        		
        		
        		JScrollPane scrollPane = new JScrollPane();
        		scrollPane.setBounds(Globals.scale(10), Globals.topMenuBarHeight+Globals.scale(10), width-Globals.scale(20), panelHeight-Globals.scale(40));
        		scrollPane.setBackground(Globals.panelBackground);
        		scrollPane.setForeground(Globals.panelBackground);
        		mainFrame.getContentPane().add(scrollPane);
        		
        		
        		
        		
        		/////////////////////////////////////////
        		// Normal Slayer
        		//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time

        		// TODO add new columns for the log
        		Object[][] columns = {
        				//Normal
	        			{"Monster",
	        			"Amount",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/min"},
	        			// Cannon
	        			{"Monster",
	        			"Amount",
	        			"Net Profit",
	        			"Cannonballs Left",
	        			"Cannonballs Used",
	        			"Cost of Cannonballs",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/Min"},
	        			// Burst
	        			{"Monster",
	        			"Amount",
	        			"Net Profit",
	        			"Deaths Used",
	        			"Chaos Used",
	        			"Water Used",
	        			"Price of Runes",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/Min"},
	        			// Barrage
	        			{"Monster",
	        			"Amount",
	        			"Net Profit",
	        			"Deaths Used",
	        			"Blood Used",
	        			"Water Used",
	        			"Price of Runes",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/Min"},
	        			// Trident of seas
	        			{"Monster",
	        			"Amount",
	        			"Net Profit",
	        			"Charges Used",
	        			"Cost of Charges",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/Min"},
	        			// Magic & Cannon
	        			{"Monster",
	        			"Amount",
	        			"Net Profit",
	        			"Cbs Used",
	        			"Rune 1 Used",
	        			"Rune 2 Used",
	        			"Rune 3 Used",
	        			"Cost of Cbs",
	        			"Cost of Runes",
	        			"Profit",
	        			"Time",
	        			"Slayer Exp",
	        			"Exp/Min"}
	        			
        			};
        		hasLog = new boolean[log.size()];
        		tables = new JTable[log.size()];
        		data = new Object[log.size()][][];
        		for(int i = 0; i < log.size(); i++) {
        			data[i] = new Object[log.get(i).size()][];
        			int rowNum = 0;
	        		for(String[] array : log.get(i)) {
	        			data[i][rowNum] = new Object[array.length];
	        			if(array[0].equals("Nothing")) {
							hasLog[i] = false;
							break;
						}else {
							hasLog[i] = true;
						}
	        			Object[] temp = Monsters.getMonster(array[0]);
	        			// If doesn't return monster, knock off last letter and try again
	        			// I do this for people who have been using the tracker for a while
	        			// I originally has s at the end of the monster names
	        			if(temp == null) {
	        				temp = Monsters.getMonster(array[0].substring(0, array[0].length() - 1));
	        			}
	        			// If doesn't find monster after trying once
	    				if(temp == null) {
	    					// Report error in log
	        				System.err.println("Error in slayerLog with:"+array[0]);
	        				temp = Monsters.getErrorMonster();
	    				}
    					// Find which column is time
    					String[] time = null;
    					for(int j = 0; j < array.length; j++) {
    						if(columns[i][j].equals("Time")) {
    							time = array[j].split(":");
    							break;
    						}
    					}
	    				if(time == null) {
	    					System.err.println("Did not find time for column set "+i);
	    				}
	        			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
	        			// Safe to assume the 2nd column is always amount
	        			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
	        			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
	        			
	        			Object[] rowData = new Object[array.length+2];
	        			
	        			for(int column = 0; column < array.length; column++) {
	        				if(column == 0 || columns[i][column].equals("Time")) {
	        					rowData[column] = array[column];
	        				}else {
	        					rowData[column] = Integer.parseInt(array[column]);
	        				}
	        			}
	        			rowData[rowData.length-2] = exp;
	        			rowData[rowData.length-1] = Float.parseFloat(String.format("%.2f", expPerMin));
	    				data[i][rowNum] = rowData;
	        			
	    				rowNum++;
	        		}
	        		JTable table = new JTable(data[i],columns[i]); 
	        		table.setFillsViewportHeight(true);
	        		table.setDefaultRenderer(Object.class, colourCells());
	        		table.setDefaultRenderer(String.class, colourCells());
	        		table.setDefaultRenderer(Integer.class, colourCells());
	        		table.setDefaultRenderer(Float.class, colourCells());
	        		table.setAutoCreateRowSorter(true);
	        		table.setEnabled(false);
	        		table.setRowSelectionAllowed(false);
	        		table.setShowVerticalLines(false);
	        		table.setBackground(Globals.panelBackground);
	        		table.setForeground(Globals.buttonForground);
	        		table.setFont(Globals.mainFont);
	        		table.getTableHeader().setFont(Globals.mainFont);
	        		table.getTableHeader().setBackground(Globals.panelBackground);
	        		table.getTableHeader().setForeground(Globals.buttonForground);
	        		tables[i] = table;
        		}

        		
        	
				/////////////////////////////////////////
				// Logs
				
				DefaultTableModel logModel = new DefaultTableModel(); 
				logModel.addColumn("Type of Log"); 
				logModel.addColumn("Amount of Kills"); 
				logModel.addColumn("Total Profit"); 

				int[] amountOfKills = new int[log.size()];
				int[] totalLoot = new int[log.size()];
				int absoluteTotalLoot = 0;
				int absoluteAmountOfKills = 0;
				
				// set kills and loot to 0
				for(int i =0;i<log.size();i++) {
					amountOfKills[i] = 0;
					totalLoot[i] = 0;
				}
				
				for(int i = 0; i < data.length; i++) {
					if(!hasLog[i]) {
						continue;
					}
					for(Object[] row: data[i]) {						
						if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
							break;
						}
						// get the profit column
						for(int j = 0; j <row.length; j++) {
    						if(columns[i][j].equals("Profit")) {
    							totalLoot[i] += (int)row[j];
    						}
    					}
						amountOfKills[i] += (int)row[1];
						if(totalLoot[i] == 0) {
							System.out.println("PONG");
							System.out.println(row.length);
						}
					}
				}
				
				for(int i = 0; i<totalLoot.length;i++) {
					absoluteTotalLoot += totalLoot[i];
					absoluteAmountOfKills += amountOfKills[i];
				}
				
				// TODO add row for the log of logs for the new log
				logModel.addRow(new Object[] {"Normal Tasks",amountOfKills[0],totalLoot[0]});
				logModel.addRow(new Object[] {"Cannon Tasks",amountOfKills[1],totalLoot[1]});
				logModel.addRow(new Object[] {"Burst Tasks",amountOfKills[2],totalLoot[2]});
				logModel.addRow(new Object[] {"Barrage Tasks",amountOfKills[3],totalLoot[3]});
				logModel.addRow(new Object[] {"Trident Tasks",amountOfKills[4],totalLoot[4]});
				logModel.addRow(new Object[] {"Cannon & Magic Tasks",amountOfKills[5],totalLoot[5]});
				logModel.addRow(new Object[] {"All Tasks",absoluteAmountOfKills,absoluteTotalLoot});
				
				logTable = new JTable(logModel);
				logTable.setFillsViewportHeight(true);
				logTable.setBackground(Globals.panelBackground);
				logTable.setForeground(Globals.buttonForground);
				logTable.setFont(Globals.mainFont);
				logTable.getTableHeader().setFont(Globals.mainFont);
				logTable.getTableHeader().setBackground(Globals.panelBackground);
				logTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(logTable);
				
				//////////////////////////////
				// change log buttons
				
				// TODO add new text for new log
				
				buttonString = new String[]{
						"Normal Slayer Logs",
						"Cannon Slayer Logs",
						"Burst Slayer Logs",
						"Barrage Slayer Logs",
						"Trident Slayer Logs",
						"Cannon/Magic Slayer Logs",
						"Log of the Logs"
				};
				buttons = new JButton[buttonString.length];
				
				for(int i = 0; i < buttonString.length; i++) {
					JButton button = new JButton(buttonString[i]);
					button.setFont(Globals.mainFont);
					button.setMargin(new Insets(0, 0, 0, 0));
					button.setForeground(Globals.buttonForground);
					button.setBackground(Globals.buttonBackground);
					JTable table;
					if(buttonString[i].equals("Log of the Logs")) {
						table = logTable;
					}else {
						table = tables[i];
					}
					button.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
							scrollPane.setViewportView(table);
						}
					});
					mainFrame.getContentPane().add(button);
					buttons[i] = button;
					
				}
				
				// Remove columns I dont want to show
				tables[tables.length-1].removeColumn(tables[tables.length-1].getColumn("Rune 1 Used"));
				tables[tables.length-1].removeColumn(tables[tables.length-1].getColumn("Rune 2 Used"));
				tables[tables.length-1].removeColumn(tables[tables.length-1].getColumn("Rune 3 Used"));
				/////////////////////////////////////////
				setButtons();
                mainFrame.setLocationByPlatform(true);
				mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);

			}
        });
    }
	
	private static DefaultTableCellRenderer colourCells() {
		return new DefaultTableCellRenderer(){
		    @Override
		    public Component getTableCellRendererComponent(JTable table,
		            Object value, boolean isSelected, boolean hasFocus, int row, int col) {

		        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);

		        setHorizontalAlignment(JLabel.RIGHT);
		        row = table.convertRowIndexToModel(row);
		        String status = (String)table.getModel().getValueAt(row, 0);
		        Object[] monster = Monsters.getMonster(status);
		        // Attempt to knock off the last letter
		        if(monster == null) {
		        	monster = Monsters.getMonster(status.substring(0, status.length() - 1));
		        }
		        if (monster != null) {
		            setBackground((Color) monster[3]);
		            setForeground((Color) monster[4]);
		        } else {
		        	
		            setBackground(Globals.panelBackground);
		            setForeground(Globals.buttonForground);
		            
		        }    
		        return this;
		    }   
		};
	}
	
	private static void setButtons() {
		int count = 1;
		for(int i = 0; i < hasLog.length; i++) {
			if(hasLog[i]) {
				count++;
			}
			System.out.println(buttonString[i]+" : "+hasLog[i]);
		}

		int buttonSize = (width-Globals.scale(10))/count;
		int buttonNum = 0;
		for(int i = 0; i < hasLog.length; i++) {
			if(hasLog[i]) {
				buttons[i].setBounds(buttonSize*buttonNum+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
				buttonNum++;
			}
		}
		buttons[buttons.length-1].setBounds(buttonSize*(count-1)+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));

	}

	public static void showPanel() {
		if(!isInit()) {
			build();
		}else {
			makeVisible();
		}
	}
	private static void makeVisible() {
		mainFrame.setVisible(true);
		mainFrame.setState(Frame.NORMAL);
	}

	private static boolean isInit() {
		if(mainFrame == null) {
			return false;
		}else {
			return true;
		}
	}
}
