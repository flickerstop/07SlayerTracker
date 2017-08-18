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
	
	private static JTable cannonTable;
	private static JTable burstTable;
	private static JTable barrageTable;
	private static JTable tridentTable;
	private static JTable normalTable;
	private static JTable cannonBurstTable;
	private static JTable cannonballTable;
	private static JTable logTable;
	
	
	static boolean hasCannonLog = true;
	static boolean hasNormalLog = true;
	static boolean hasBurstLog = true;
	static boolean hasBarrageLog = true;
	static boolean hasTridentLog = true;
	static boolean hasCannonMagicLog = true;
	static boolean hasCannonballLog = true;
	
	static JButton cannonLogButton;
	static JButton normalLogButton;
	static JButton burstLogButton;
	static JButton barrageLogButton;
	static JButton tridentLogButton;
	static JButton cannonBurstLogButton;
	static JButton cannonballLogButton;
	static JButton logButton;
	
	static int width = Globals.scale(1250);
	static int height = Globals.scale(550);
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build() {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	ArrayList<ArrayList<String[]>> log = Globals.getLogs();
            	
        		int panelHeight = height-Globals.topMenuBarHeight;
        		
            	JFrame mainFrame = new JFrame("Test");
            	mainFrame.getContentPane().setBackground(Globals.panelBackground);
            	mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/download_icon.png")));
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
        		//TODO

        		DefaultTableModel normalModel = new DefaultTableModel(){
					@Override
                    public Class<?> getColumnClass(int column) {
                        switch (column) {
                            case 0:
                                return String.class;
                            case 5:
                                return Float.class;
                            default:
                                return Integer.class;
                        }
                    }
                };
        		normalModel.addColumn("Monster"); 
        		normalModel.addColumn("Amount"); 
        		normalModel.addColumn("Net Profit");
        		normalModel.addColumn("Time");
        		normalModel.addColumn("Slayer Exp");
        		normalModel.addColumn("Exp/min");
        		for(String[] array : log.get(0)) {
        			if(array[0].equals("Nothing")) {
						hasNormalLog = false;
					}
        			Object[] temp = Monsters.getMonster(array[0]);
        			if(temp == null) {
        				//System.err.println("Error in slayerLog with:"+array[0]);
        				temp = Monsters.getMonster(array[0].substring(0, array[0].length() - 1));
        			}
    				if(temp == null) {
        				System.err.println("Error in slayerLog with:"+array[0]);
    				} else {
    				String[] time = array[3].split(":");
        			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
        			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
        			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
    				normalModel.addRow(new Object[]{
    						array[0],
    						Integer.parseInt(array[1]),
    						Integer.parseInt(array[2]),
    						array[3],
    						exp,
    						Float.parseFloat(String.format("%.2f", expPerMin))
    						});
        			}
        		}
        		normalTable = new JTable(normalModel);
        		//normalTable.setAutoCreateRowSorter(true);
        		normalTable.setFillsViewportHeight(true);
        		normalTable.setDefaultRenderer(Object.class, colourCells());
        		normalTable.setDefaultRenderer(String.class, colourCells());
        		normalTable.setDefaultRenderer(Integer.class, colourCells());
        		normalTable.setDefaultRenderer(Float.class, colourCells());
        		normalTable.setAutoCreateRowSorter(true);
        		normalTable.setEnabled(false);
                normalTable.setRowSelectionAllowed(false);
                normalTable.setShowVerticalLines(false);
        		normalTable.setBackground(Globals.panelBackground);
        		normalTable.setForeground(Globals.buttonForground);
        		normalTable.setFont(Globals.mainFont);
        		normalTable.getTableHeader().setFont(Globals.mainFont);
        		normalTable.getTableHeader().setBackground(Globals.panelBackground);
        		normalTable.getTableHeader().setForeground(Globals.buttonForground);
        		
        		scrollPane.setViewportView(normalTable);
				/////////////////////////////////////////
				// Cannon
				//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time
				DefaultTableModel cannonModel = new DefaultTableModel(){
					@Override
                    public Class<?> getColumnClass(int column) {
                        switch (column) {
                            case 0:
                                return String.class;
                            case 9:
                                return Float.class;
                            default:
                                return Integer.class;
                        }
                    }
                };
				cannonModel.addColumn("Monster"); 
				cannonModel.addColumn("Amount"); 
				cannonModel.addColumn("Net Profit");
				cannonModel.addColumn("Cannonballs Left");
				cannonModel.addColumn("Cannonballs Used");
				cannonModel.addColumn("Cost of Cannonballs");
				cannonModel.addColumn("Profit");
				cannonModel.addColumn("Time");
				cannonModel.addColumn("Slayer Exp");
				cannonModel.addColumn("Exp/Min");
				
				for(String[] array : log.get(1)) {
					if(array[0].equals("Nothing")) {
						hasCannonLog = false;
					}
					Object[] temp = Monsters.getMonster(array[0]);
        			if(temp == null) {
        				System.err.println("Error in cannonLog with:"+array[0]);
        			}else {
        				String[] time = array[7].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
        				cannonModel.addRow(new Object[] {
        						array[0],
        						Integer.parseInt(array[1]),
        						Integer.parseInt(array[2]),
        						Integer.parseInt(array[3]),
        						Integer.parseInt(array[4]),
        						Integer.parseInt(array[5]),
        						Integer.parseInt(array[6]),
        						array[7],
        						exp,
        						Float.parseFloat(String.format("%.2f", expPerMin))
        						});
        			}
				}
				cannonTable = new JTable(cannonModel);
				cannonTable.setFillsViewportHeight(true);
				cannonTable.setDefaultRenderer(Object.class, colourCells());
        		cannonTable.setDefaultRenderer(String.class, colourCells());
        		cannonTable.setDefaultRenderer(Integer.class, colourCells());
        		cannonTable.setDefaultRenderer(Float.class, colourCells());
        		cannonTable.setAutoCreateRowSorter(true);
        		cannonTable.setEnabled(false);
        		cannonTable.setRowSelectionAllowed(false);
                cannonTable.setShowVerticalLines(false);
				cannonTable.setBackground(Globals.panelBackground);
				cannonTable.setForeground(Globals.buttonForground);
				cannonTable.setFont(Globals.mainFont);
				cannonTable.getTableHeader().setFont(Globals.mainFont);
				cannonTable.getTableHeader().setBackground(Globals.panelBackground);
				cannonTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(cannonTable);
				/////////////////////////////////////////
				// Burst
				// monsterName,count,loot,deathUsed,chaosUsed,waterUsed,priceOfRunes,profit,time
				DefaultTableModel burstModel = new DefaultTableModel(){
					@Override
                    public Class<?> getColumnClass(int column) {
                        switch (column) {
                            case 0:
                                return String.class;
                            case 10:
                                return Float.class;
                            default:
                                return Integer.class;
                        }
                    }
                };
				burstModel.addColumn("Monster"); 
				burstModel.addColumn("Amount"); 
				burstModel.addColumn("Net Profit");
				burstModel.addColumn("Deaths Used");
				burstModel.addColumn("Chaos Used");
				burstModel.addColumn("Water Used");
				burstModel.addColumn("Price of Runes");
				burstModel.addColumn("Profit");
				burstModel.addColumn("Time");
				burstModel.addColumn("Slayer Exp");
				burstModel.addColumn("Exp/Min");
				for(String[] array : log.get(2)) {
					if(array[0].equals("Nothing")) {
						hasBurstLog = false;
					}
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
        				System.err.println("Error in burstLog with:"+array[0]);
        			}else {
        				String[] time = array[8].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
            			burstModel.addRow(new Object[] {
            					array[0],
            					Integer.parseInt(array[1]),
            					Integer.parseInt(array[2]),
            					Integer.parseInt(array[3]),
            					Integer.parseInt(array[4]),
            					Integer.parseInt(array[5]),
            					Integer.parseInt(array[6]),
            					Integer.parseInt(array[7]),
            					array[8],
            					exp,
            					Float.parseFloat(String.format("%.2f", expPerMin))
            					});
        			}
				}
				burstTable = new JTable(burstModel);
				burstTable.setFillsViewportHeight(true);
				burstTable.setDefaultRenderer(Object.class, colourCells());
        		burstTable.setDefaultRenderer(String.class, colourCells());
        		burstTable.setDefaultRenderer(Integer.class, colourCells());
        		burstTable.setDefaultRenderer(Float.class, colourCells());
        		burstTable.setAutoCreateRowSorter(true);
        		burstTable.setEnabled(false);
        		burstTable.setRowSelectionAllowed(false);
                burstTable.setShowVerticalLines(false);
				burstTable.setBackground(Globals.panelBackground);
				burstTable.setForeground(Globals.buttonForground);
				burstTable.setFont(Globals.mainFont);
				burstTable.getTableHeader().setFont(Globals.mainFont);
				burstTable.getTableHeader().setBackground(Globals.panelBackground);
				burstTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(burstTable);
				/////////////////////////////////////////
				// barrage
				// monsterName,count,loot,deathUsed,chaosUsed,waterUsed,priceOfRunes,profit,time
				DefaultTableModel barrageModel = new DefaultTableModel(){
				@Override
				public Class<?> getColumnClass(int column) {
				switch (column) {
				    case 0:
				        return String.class;
				    case 10:
				        return Float.class;
				    default:
				        return Integer.class;
				}
				}
				};
				barrageModel.addColumn("Monster"); 
				barrageModel.addColumn("Amount"); 
				barrageModel.addColumn("Net Profit");
				barrageModel.addColumn("Deaths Used");
				barrageModel.addColumn("Blood Used");
				barrageModel.addColumn("Water Used");
				barrageModel.addColumn("Price of Runes");
				barrageModel.addColumn("Profit");
				barrageModel.addColumn("Time");
				barrageModel.addColumn("Slayer Exp");
				barrageModel.addColumn("Exp/Min");
				for(String[] array : log.get(5)) {
					if(array[0].equals("Nothing")) {
						hasBarrageLog = false;
					}
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
						System.err.println("Error in barrageLog with:"+array[0]);
					}else {
						String[] time = array[8].split(":");
						long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
						int exp = (Integer.parseInt(array[1])*(int)temp[1]);
						float expPerMin = ((float)exp/((float)timeInMilli))*60000;
						barrageModel.addRow(new Object[] {
								array[0],
								Integer.parseInt(array[1]),
								Integer.parseInt(array[2]),
								Integer.parseInt(array[3]),
								Integer.parseInt(array[4]),
								Integer.parseInt(array[5]),
								Integer.parseInt(array[6]),
								Integer.parseInt(array[7]),
								array[8],
								exp,
								Float.parseFloat(String.format("%.2f", expPerMin))
								});
					}
				}
				barrageTable = new JTable(barrageModel);
				barrageTable.setFillsViewportHeight(true);
				barrageTable.setDefaultRenderer(Object.class, colourCells());
				barrageTable.setDefaultRenderer(String.class, colourCells());
				barrageTable.setDefaultRenderer(Integer.class, colourCells());
				barrageTable.setDefaultRenderer(Float.class, colourCells());
				barrageTable.setAutoCreateRowSorter(true);
				barrageTable.setEnabled(false);
				barrageTable.setRowSelectionAllowed(false);
				barrageTable.setShowVerticalLines(false);
				barrageTable.setBackground(Globals.panelBackground);
				barrageTable.setForeground(Globals.buttonForground);
				barrageTable.setFont(Globals.mainFont);
				barrageTable.getTableHeader().setFont(Globals.mainFont);
				barrageTable.getTableHeader().setBackground(Globals.panelBackground);
				barrageTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(barrageTable);
				/////////////////////////////////////////
				// trident
				// name, count, net profit, charges used, price of charges, profit, time
				DefaultTableModel tridentModel = new DefaultTableModel(){
					@Override
					public Class<?> getColumnClass(int column) {
						switch (column) {
						case 0:
							return String.class;
						case 10:
							return Float.class;
						default:
							return Integer.class;
						}
					}
				};
				tridentModel.addColumn("Monster"); 
				tridentModel.addColumn("Amount"); 
				tridentModel.addColumn("Net Profit");
				tridentModel.addColumn("Charges Used");
				tridentModel.addColumn("Cost of Charges");
				tridentModel.addColumn("Profit");
				tridentModel.addColumn("Time");
				tridentModel.addColumn("Slayer Exp");
				tridentModel.addColumn("Exp/Min");
				for(String[] array : log.get(6)) {
					if(array[0].equals("Nothing")) {
						hasTridentLog = false;
					}
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
						System.err.println("Error in tridentLog with:"+array[0]);
					}else {
						String[] time = array[6].split(":");
						long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
						int exp = (Integer.parseInt(array[1])*(int)temp[1]);
						float expPerMin = ((float)exp/((float)timeInMilli))*60000;
						tridentModel.addRow(new Object[] {
								array[0],
								Integer.parseInt(array[1]),
								Integer.parseInt(array[2]),
								Integer.parseInt(array[3]),
								Integer.parseInt(array[4]),
								Integer.parseInt(array[5]),
								array[6],
								exp,
								Float.parseFloat(String.format("%.2f", expPerMin))
								});
					}
				}
				tridentTable = new JTable(tridentModel);
				tridentTable.setFillsViewportHeight(true);
				tridentTable.setDefaultRenderer(Object.class, colourCells());
				tridentTable.setDefaultRenderer(String.class, colourCells());
				tridentTable.setDefaultRenderer(Integer.class, colourCells());
				tridentTable.setDefaultRenderer(Float.class, colourCells());
				tridentTable.setAutoCreateRowSorter(true);
				tridentTable.setEnabled(false);
				tridentTable.setRowSelectionAllowed(false);
				tridentTable.setShowVerticalLines(false);
				tridentTable.setBackground(Globals.panelBackground);
				tridentTable.setForeground(Globals.buttonForground);
				tridentTable.setFont(Globals.mainFont);
				tridentTable.getTableHeader().setFont(Globals.mainFont);
				tridentTable.getTableHeader().setBackground(Globals.panelBackground);
				tridentTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(tridentTable);
				/////////////////////////////////////////
				// Cannon/burst
				// name, count, loot, cannonballsUsed, deathsUsed, chaosUsed, waterUsed, priceOfBalls, priceOfRunes, profit, time
				DefaultTableModel cannonMagicModel = new DefaultTableModel(){
					@Override
                    public Class<?> getColumnClass(int column) {
                        switch (column) {
                            case 0:
                                return String.class;
                            case 12:
                                return Float.class;
                            default:
                                return Integer.class;
                        }
                    }
                };
				cannonMagicModel.addColumn("Monster"); 
				cannonMagicModel.addColumn("Amount"); 
				cannonMagicModel.addColumn("Net Profit");
				cannonMagicModel.addColumn("Cbs Used");
//				cannonMagicModel.addColumn("Deaths Used");
//				cannonMagicModel.addColumn("Chaos Used");
//				cannonMagicModel.addColumn("Waters Used");
				cannonMagicModel.addColumn("Cost of Cbs");
				cannonMagicModel.addColumn("Cost of Runes");
				cannonMagicModel.addColumn("Profit");
				cannonMagicModel.addColumn("Time");
				cannonMagicModel.addColumn("Slayer Exp");
				cannonMagicModel.addColumn("Exp/Min");
				for(String[] array : log.get(3)) {
					if(array[0].equals("Nothing")) {
						hasCannonMagicLog = false;
					}
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
        				System.err.println("Error in cannonBurstLog with:"+array[0]);
        			}else {
        				String[] time = array[10].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
            			cannonMagicModel.addRow(new Object[] {
            					array[0],
            					Integer.parseInt(array[1]),
            					Integer.parseInt(array[2]),
            					Integer.parseInt(array[3]),
//            					Integer.parseInt(array[4]),
//            					Integer.parseInt(array[5]),
//            					Integer.parseInt(array[6]),
            					Integer.parseInt(array[7]),
            					Integer.parseInt(array[8]),
            					Integer.parseInt(array[9]),
            					array[10],
            					exp,
            					Float.parseFloat(String.format("%.2f", expPerMin))
            					});
        			}
				}
				cannonBurstTable = new JTable(cannonMagicModel);
				cannonBurstTable.setFillsViewportHeight(true);
				cannonBurstTable.setDefaultRenderer(Object.class, colourCells());
				cannonBurstTable.setDefaultRenderer(String.class, colourCells());
				cannonBurstTable.setDefaultRenderer(Integer.class, colourCells());
				cannonBurstTable.setDefaultRenderer(Float.class, colourCells());
				cannonBurstTable.setAutoCreateRowSorter(true);
				cannonBurstTable.setEnabled(false);
				cannonBurstTable.setRowSelectionAllowed(false);
				cannonBurstTable.setShowVerticalLines(false);
				cannonBurstTable.setBackground(Globals.panelBackground);
				cannonBurstTable.setForeground(Globals.buttonForground);
				cannonBurstTable.setFont(Globals.mainFont);
				cannonBurstTable.getTableHeader().setFont(Globals.mainFont);
				cannonBurstTable.getTableHeader().setBackground(Globals.panelBackground);
				cannonBurstTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(cannonBurstTable);
				/////////////////////////////////////////
				// Cannonball
				// name, count, loot, cannonballsUsed, deathsUsed, chaosUsed, waterUsed, priceOfBalls, priceOfRunes, profit, time
				DefaultTableModel cannonballModel = new DefaultTableModel(); 
				cannonballModel.addColumn("Total Price"); 
				cannonballModel.addColumn("Amount of Cannonballs"); 
				cannonballModel.addColumn("Price per Ball");
				for(String[] array : log.get(4)) {
					cannonballModel.addRow(array);
				}
				cannonballTable = new JTable(cannonballModel);
				cannonballTable.setBackground(Globals.panelBackground);
				cannonballTable.setFillsViewportHeight(true);
				cannonballTable.setForeground(Globals.buttonForground);
				cannonballTable.setFont(Globals.mainFont);
				cannonballTable.getTableHeader().setFont(Globals.mainFont);
				cannonballTable.getTableHeader().setBackground(Globals.panelBackground);
				cannonballTable.getTableHeader().setForeground(Globals.buttonForground);
				scrollPane.setViewportView(cannonballTable);
        		
        	
				/////////////////////////////////////////
				// Logs
				
				DefaultTableModel logModel = new DefaultTableModel(); 
				logModel.addColumn("Type of Log"); 
				logModel.addColumn("Amount of Kills"); 
				logModel.addColumn("Total Profit"); 

				int[] amountOfKills = {0,0,0,0,0,0,0};
				int[] totalLoot = {0,0,0,0,0,0,0};
				int absoluteTotalLoot = 0;
				int absoluteAmountOfKills = 0;
				int count = 0;
				
				// Normal slayer
				for(String[] row: log.get(0)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[2]);
				}
				count++;
				// Cannon
				for(String[] row: log.get(1)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[6]);
				}
				count++;
				// burst
				for(String[] row: log.get(2)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[7]);
				}
				count++;
				// Barrage
				for(String[] row: log.get(5)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[7]);
				}
				count++;
				// Trident
				for(String[] row: log.get(6)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[5]);
				}
				count++;
				// cannon&burst
				for(String[] row: log.get(3)) {						
					if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
						break;
					}
					amountOfKills[count] += Integer.parseInt(row[1]);
					totalLoot[count] += Integer.parseInt(row[9]);
				}
				
				for(int i = 0; i<totalLoot.length;i++) {
					absoluteTotalLoot += totalLoot[i];
					absoluteAmountOfKills += amountOfKills[i];
				}
				
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
				
				//////////////////////////////
				// change log buttons
				normalLogButton = new JButton("Normal Slayer Logs");
				normalLogButton.setFont(Globals.mainFont);
				normalLogButton.setMargin(new Insets(0, 0, 0, 0));
				normalLogButton.setForeground(Globals.buttonForground);
				normalLogButton.setBackground(Globals.buttonBackground);
				normalLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(normalTable);
					}
				});
				mainFrame.getContentPane().add(normalLogButton);
				
				
				cannonLogButton = new JButton("Cannon Slayer Logs");
				cannonLogButton.setFont(Globals.mainFont);
				cannonLogButton.setMargin(new Insets(0, 0, 0, 0));
				cannonLogButton.setForeground(Globals.buttonForground);
				cannonLogButton.setBackground(Globals.buttonBackground);
				cannonLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(cannonTable);
					}
				});
				mainFrame.getContentPane().add(cannonLogButton);
				
				
				burstLogButton = new JButton("Burst Slayer Logs");
				burstLogButton.setFont(Globals.mainFont);
				burstLogButton.setMargin(new Insets(0, 0, 0, 0));
				burstLogButton.setForeground(Globals.buttonForground);
				burstLogButton.setBackground(Globals.buttonBackground);
				burstLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(burstTable);
					}
				});
				mainFrame.getContentPane().add(burstLogButton);
				
				barrageLogButton = new JButton("Barrage Slayer Logs");
				barrageLogButton.setFont(Globals.mainFont);
				barrageLogButton.setMargin(new Insets(0, 0, 0, 0));
				barrageLogButton.setForeground(Globals.buttonForground);
				barrageLogButton.setBackground(Globals.buttonBackground);
				barrageLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(barrageTable);
					}
				});
				mainFrame.getContentPane().add(barrageLogButton);
				
				tridentLogButton = new JButton("Trident Slayer Logs");
				tridentLogButton.setFont(Globals.mainFont);
				tridentLogButton.setMargin(new Insets(0, 0, 0, 0));
				tridentLogButton.setForeground(Globals.buttonForground);
				tridentLogButton.setBackground(Globals.buttonBackground);
				tridentLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(tridentTable);
					}
				});
				mainFrame.getContentPane().add(tridentLogButton);
				
				cannonBurstLogButton = new JButton("Cannon/Magic Slayer Logs");
				cannonBurstLogButton.setFont(Globals.mainFont);
				cannonBurstLogButton.setMargin(new Insets(0, 0, 0, 0));
				cannonBurstLogButton.setForeground(Globals.buttonForground);
				cannonBurstLogButton.setBackground(Globals.buttonBackground);
				cannonBurstLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(cannonBurstTable);
					}
				});
				mainFrame.getContentPane().add(cannonBurstLogButton);
				
				cannonballLogButton = new JButton("Cannonball Purchase Logs");
				cannonballLogButton.setFont(Globals.mainFont);
				cannonballLogButton.setMargin(new Insets(0, 0, 0, 0));
				cannonballLogButton.setForeground(Globals.buttonForground);
				cannonballLogButton.setBackground(Globals.buttonBackground);
				cannonballLogButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(cannonballTable);
					}
				});
				mainFrame.getContentPane().add(cannonballLogButton);
				
				logButton = new JButton("Log of the Logs");
				logButton.setFont(Globals.mainFont);
				logButton.setMargin(new Insets(0, 0, 0, 0));
				logButton.setForeground(Globals.buttonForground);
				logButton.setBackground(Globals.buttonBackground);
				logButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						scrollPane.setViewportView(logTable);
					}
				});
				logButton.setBounds(((width-Globals.scale(10))/7)*6+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/7,Globals.scale(20));
				mainFrame.getContentPane().add(logButton);
				/////////////////////////////////////////
				setButtons();
                mainFrame.setLocationByPlatform(true);
				mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);

			}
        });
    }
	
	@SuppressWarnings("serial")
	public static DefaultTableCellRenderer colourCells() {
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
	
	public static void setButtons() {
		int count = 2;
		int i = 0;
		if(hasNormalLog) {
			count++;
		}
		if(hasCannonLog) {
			count++;
		}
		if(hasBurstLog) {
			count++;
		}
		if(hasBarrageLog) {
			count++;
		}
		if(hasCannonMagicLog) {
			count++;
		}
		if(hasTridentLog) {
			count++;
		}

		int buttonSize = (width-Globals.scale(10))/count;
		
		
		if(hasNormalLog) {
			normalLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		if(hasCannonLog) {
			cannonLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		if(hasBurstLog) {
			burstLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		if(hasBarrageLog) {
			barrageLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		if(hasTridentLog) {
			tridentLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		if(hasCannonMagicLog) {
			cannonBurstLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
			i++;
		}
		cannonballLogButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
		i++;
		logButton.setBounds(buttonSize*i+Globals.scale(5),height-Globals.scale(25),buttonSize,Globals.scale(20));
		i++;

	}
}
