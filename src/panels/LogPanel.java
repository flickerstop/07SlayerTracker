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
import objects.Player;
import ui.SlayerTrackerUI;
import ui.SlayerTrackerUI.FrameDragListener;


public class LogPanel {
	
	private static JTable cannonTable;
	private static JTable burstTable;
	private static JTable normalTable;
	private static JTable cannonBurstTable;
	private static JTable cannonballTable;
	private static JTable logTable;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build(Player player) {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	ArrayList<ArrayList<String[]>> log = player.getLogs();
            	int width = Globals.scale(1000)+Globals.topMenuBarHeight;
        		int height = Globals.scale(550);
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
        		
        		
        		
        		//////////////////////////////
        		// change log buttons
        		JButton normalLogButton = new JButton("Normal Slayer Logs");
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
        		normalLogButton.setBounds(Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(normalLogButton);
        		
        		
        		JButton cannonLogButton = new JButton("Cannon Slayer Logs");
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
        		cannonLogButton.setBounds(((width-Globals.scale(10))/6)*1+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(cannonLogButton);
        		
        		
        		JButton burstLogButton = new JButton("Burst Slayer Logs");
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
        		burstLogButton.setBounds(((width-Globals.scale(10))/6)*2+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(burstLogButton);
        		
        		JButton cannonBurstLogButton = new JButton("Cannon/Burst Slayer Logs");
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
        		cannonBurstLogButton.setBounds(((width-Globals.scale(10))/6)*3+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(cannonBurstLogButton);
        		
        		JButton cannonballLogButton = new JButton("Cannonball Purchase Logs");
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
        		cannonballLogButton.setBounds(((width-Globals.scale(10))/6)*4+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(cannonballLogButton);
        		
        		JButton logButton = new JButton("Log of the Logs");
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
        		logButton.setBounds(((width-Globals.scale(10))/6)*5+Globals.scale(5),height-Globals.scale(25),(width-Globals.scale(10))/6,Globals.scale(20));
        		mainFrame.getContentPane().add(logButton);
        		
        		/////////////////////////////////////////
        		// Normal Slayer
        		//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time
        		DefaultTableModel normalModel = new DefaultTableModel(); 
        		normalModel.addColumn("Monster"); 
        		normalModel.addColumn("Amount"); 
        		normalModel.addColumn("Loot");
        		normalModel.addColumn("Time");
        		for(String[] array : log.get(0)) {
        			normalModel.addRow(array);
        		}
        		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        		rightRenderer.setHorizontalAlignment( JLabel.RIGHT );
        		normalTable = new JTable(normalModel);
        		normalTable.setFillsViewportHeight(true);
        		normalTable.setDefaultRenderer(Object.class, colourCells());
        		normalTable.setBackground(Globals.panelBackground);
        		normalTable.setForeground(Globals.buttonForground);
        		scrollPane.setViewportView(normalTable);
				/////////////////////////////////////////
				// Cannon
				//monsterName,monsterCount,lootAmount,cannonballsLeft,cannonballsUsed,priceOfBalls,profit,time
				DefaultTableModel cannonModel = new DefaultTableModel(); 
				cannonModel.addColumn("Monster"); 
				cannonModel.addColumn("Amount"); 
				cannonModel.addColumn("Loot");
				cannonModel.addColumn("Cannonballs Left");
				cannonModel.addColumn("Cannonballs Used");
				cannonModel.addColumn("Cost of Cannonballs");
				cannonModel.addColumn("Profit");
				cannonModel.addColumn("Time");
				for(String[] array : log.get(1)) {
					cannonModel.addRow(array);
				}
				cannonTable = new JTable(cannonModel);
				cannonTable.setFillsViewportHeight(true);
				cannonTable.setDefaultRenderer(Object.class, colourCells());
				cannonTable.setBackground(Globals.panelBackground);
				cannonTable.setForeground(Globals.buttonForground);
				scrollPane.setViewportView(cannonTable);
				/////////////////////////////////////////
				// Burst
				// monsterName,count,loot,deathUsed,chaosUsed,waterUsed,priceOfRunes,profit
				DefaultTableModel burstModel = new DefaultTableModel(); 
				burstModel.addColumn("Monster"); 
				burstModel.addColumn("Amount"); 
				burstModel.addColumn("Loot");
				burstModel.addColumn("Deaths Used");
				burstModel.addColumn("Chaos Used");
				burstModel.addColumn("Water Used");
				burstModel.addColumn("Price of Runes");
				burstModel.addColumn("Profit");
				burstModel.addColumn("Time");
				for(String[] array : log.get(2)) {
					burstModel.addRow(array);
				}
				burstTable = new JTable(burstModel);
				burstTable.setFillsViewportHeight(true);
				burstTable.setDefaultRenderer(Object.class, colourCells());
				burstTable.setBackground(Globals.panelBackground);
				burstTable.setForeground(Globals.buttonForground);
				scrollPane.setViewportView(burstTable);
				/////////////////////////////////////////
				// Cannon/burst
				// name, count, loot, cannonballsUsed, deathsUsed, chaosUsed, waterUsed, priceOfBalls, priceOfRunes, profit, time
				DefaultTableModel cannonBurstModel = new DefaultTableModel(); 
				cannonBurstModel.addColumn("Monster"); 
				cannonBurstModel.addColumn("Amount"); 
				cannonBurstModel.addColumn("Loot");
				cannonBurstModel.addColumn("Cbs Used");
				cannonBurstModel.addColumn("Deaths Used");
				cannonBurstModel.addColumn("Chaos Used");
				cannonBurstModel.addColumn("Waters Used");
				cannonBurstModel.addColumn("Cost of Cbs");
				cannonBurstModel.addColumn("Cost of Runes");
				cannonBurstModel.addColumn("Profit");
				cannonBurstModel.addColumn("Time");
				for(String[] array : log.get(3)) {
					cannonBurstModel.addRow(array);
				}
				cannonBurstTable = new JTable(cannonBurstModel);
				cannonBurstTable.setFillsViewportHeight(true);
				cannonBurstTable.setDefaultRenderer(Object.class, colourCells());
				cannonBurstTable.setBackground(Globals.panelBackground);
				cannonBurstTable.setForeground(Globals.buttonForground);
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
				cannonballTable.getColumn("Total Price").setCellRenderer(rightRenderer);
				cannonballTable.getColumn("Amount of Cannonballs").setCellRenderer(rightRenderer);
				cannonballTable.getColumn("Price per Ball").setCellRenderer(rightRenderer);
				cannonballTable.setForeground(Globals.buttonForground);
				scrollPane.setViewportView(cannonballTable);
        		
        	
				/////////////////////////////////////////
				// Logs
				
				DefaultTableModel logModel = new DefaultTableModel(); 
				logModel.addColumn("Type of Log"); 
				logModel.addColumn("Amount of Kills"); 
				logModel.addColumn("Total Profit"); 

				int[] amountOfKills = {0,0,0,0,0};
				int[] totalLoot = {0,0,0,0,0};
				int absoluteTotalLoot = 0;
				int absoluteAmountOfKills = 0;
				int count = 0;
				for(ArrayList<String[]>logs : log) {
					for(String[] row: logs) {
						if(row[0].equals("Nothing")) {
							break;
						}
						amountOfKills[count] += Integer.parseInt(row[1]);
						totalLoot[count] += Integer.parseInt(row[2]);
					}
					count++;
				}
				for(int i = 0; i<totalLoot.length;i++) {
					absoluteTotalLoot += totalLoot[i];
					absoluteAmountOfKills += amountOfKills[i];
				}
				
				logModel.addRow(new Object[] {"Normal Tasks",amountOfKills[0],totalLoot[0]});
				logModel.addRow(new Object[] {"Cannon Tasks",amountOfKills[1],totalLoot[1]});
				logModel.addRow(new Object[] {"Burst Tasks",amountOfKills[2],totalLoot[2]});
				logModel.addRow(new Object[] {"Burst/Cannon Tasks",amountOfKills[3],totalLoot[3]});
				logModel.addRow(new Object[] {"All Tasks",absoluteAmountOfKills,absoluteTotalLoot});
				
				logTable = new JTable(logModel);
				logTable.setFillsViewportHeight(true);
				logTable.getColumn("Type of Log").setCellRenderer(centerRenderer);
				logTable.getColumn("Amount of Kills").setCellRenderer(rightRenderer);
				logTable.getColumn("Total Profit").setCellRenderer(rightRenderer);
				logTable.setBackground(Globals.panelBackground);
				logTable.setForeground(Globals.buttonForground);
				
			
				
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
		        String status = (String)table.getModel().getValueAt(row, 0);
		        // trolls 			- (212, 214, 216)
		        // kalp 			- (99, 124, 91)
		        // lizardmen 		- (163, 239, 139)
		        // nech 			- (252, 174, 148)
		        // daggs 			- (99, 99, 99)
		        // fire giant 		- (255, 72, 56)
		        // gargs 			- (61, 61, 61)
		        // greater demon 	- (165, 9, 9)
		        // dust devil 		- (255, 196, 94)
		        // smoke devil 		- (124, 120, 112)
		        // abby Specs		- (163, 247, 150)
		        // bloodveld 		- (204, 106, 99)
		        // Elves 			- (247, 140, 238)
		        // Iron Dragons 	- (73, 73, 73)
		        // Steel Dragons	- (255, 255, 255)
		        // Black Dragons	- (255, 255, 255)
		        // blue drag 		- (15, 47, 255)
		        // wyvern 			- (163, 239, 139)
		        if ("Trolls".equalsIgnoreCase(status)) {
		            setBackground(new Color(212, 214, 216));
		            setForeground(new Color(0,0,0));
		        }else if ("Kalphite".equalsIgnoreCase(status)||"Kalphites".equalsIgnoreCase(status)) {
		            setBackground(new Color(99, 124, 91));
		            setForeground(new Color(255,255,255));
		        }else if ("Lizardman".equalsIgnoreCase(status)||"Lizardmen".equalsIgnoreCase(status)) {
		            setBackground(new Color(163, 239, 139));
		            setForeground(new Color(0,0,0));
		        }else if ("Nechryael".equalsIgnoreCase(status)||"Nechryaels".equalsIgnoreCase(status)) {
		            setBackground(new Color(252, 174, 148));
		            setForeground(new Color(0,0,0));
		        }else if ("Dagannoth".equalsIgnoreCase(status)||"Dagannoths".equalsIgnoreCase(status)) {
		            setBackground(new Color(99, 99, 99));
		            setForeground(new Color(255,255,255));
		        }else if ("Fire Giants".equalsIgnoreCase(status)) {
		            setBackground(new Color(255, 72, 56));
		            setForeground(new Color(255,255,255));
		        }else if ("Gargoyle".equalsIgnoreCase(status)||"Gargoyles".equalsIgnoreCase(status)) {
		            setBackground(new Color(61, 61, 61));
		            setForeground(new Color(255,255,255));
		        }else if ("Greater Demon".equalsIgnoreCase(status)||"Greater Demons".equalsIgnoreCase(status)) {
		            setBackground(new Color(165, 9, 9));
		            setForeground(new Color(255,255,255));
		        }else if ("Dust Devil".equalsIgnoreCase(status)||"Dust Devils".equalsIgnoreCase(status)) {
		            setBackground(new Color(255, 196, 94));
		            setForeground(new Color(0,0,0));
		        }else if ("Smoke Devil".equalsIgnoreCase(status)||"Smoke Devils".equalsIgnoreCase(status)) {
		            setBackground(new Color(124, 120, 112));
		            setForeground(new Color(255,255,255));
		        }else if ("Aberrant Spectres".equalsIgnoreCase(status)) {
		            setBackground(new Color(163, 247, 150));
		            setForeground(new Color(0,0,0));
		        }else if ("Bloodvelds".equalsIgnoreCase(status)) {
		            setBackground(new Color(204, 106, 99));
		            setForeground(new Color(255,255,255));
		        }else if ("Elves".equalsIgnoreCase(status)||"Elf".equalsIgnoreCase(status)||"Elfs".equalsIgnoreCase(status)) {
		            setBackground(new Color(247, 140, 238));
		            setForeground(new Color(255,255,255));
		        }else if ("Iron Dragons".equalsIgnoreCase(status)||"Iron Dragon".equalsIgnoreCase(status)) {
		            setBackground(new Color(73, 73, 73));
		            setForeground(new Color(255,255,255));
		        }else if ("Steel Dragons".equalsIgnoreCase(status)||"Steel Dragon".equalsIgnoreCase(status)) {
		            setBackground(new Color(255, 255, 255));
		            setForeground(new Color(0,0,0));
		        }else if ("Black Dragons".equalsIgnoreCase(status)||"Black Dragon".equalsIgnoreCase(status)) {
		            setBackground(new Color(0,0,0));
		            setForeground(new Color(255,255,255));
		        }else if ("Blue Dragons".equalsIgnoreCase(status)||"Blue Dragon".equalsIgnoreCase(status)) {
		            setBackground(new Color(15, 47, 255));
		            setForeground(new Color(255,255,255));
		        }else if ("Wyverns".equalsIgnoreCase(status)||"Wyvern".equalsIgnoreCase(status)) {
		            setBackground(new Color(163, 239, 139));
		            setForeground(new Color(255,255,255));
		        } else {
		            setBackground(Globals.panelBackground);
		            setForeground(Globals.buttonForground);
		            
		        }       
		        return this;
		    }   
		};
	}
}
