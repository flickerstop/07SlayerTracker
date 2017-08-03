package panels;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objects.Globals;
import objects.Player;
import ui.SlayerTrackerUI;


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
            	int width = Globals.scale(1000);
        		int height = Globals.scale(550);
        		int panelWidth = width-5;
        		int panelHeight = height-40;
        		
            	JFrame mainFrame = new JFrame("Test");
            	mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/download_icon.png")));
            	mainFrame.setLocationByPlatform(true);
        		mainFrame.setTitle("Slayer Logs\r\n");
        		mainFrame.setBounds(100, 100, width, height);
        		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		mainFrame.getContentPane().setLayout(null);
        		
        		JScrollPane scrollPane = new JScrollPane();
        		scrollPane.setBounds(0, 0, panelWidth, panelHeight-Globals.scale(30));
        		mainFrame.getContentPane().add(scrollPane);
        		
        		//////////////////////////////
        		// change log buttons
        		JButton normalLogButton = new JButton("Normal Slayer Logs");
        		normalLogButton.setFont(Globals.mainFont);
        		normalLogButton.setMargin(new Insets(0, 0, 0, 0));
        		normalLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(normalTable);
        			}
        		});
        		normalLogButton.setBounds(0,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(normalLogButton);
        		
        		
        		JButton cannonLogButton = new JButton("Cannon Slayer Logs");
        		cannonLogButton.setFont(Globals.mainFont);
        		cannonLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonTable);
        			}
        		});
        		cannonLogButton.setBounds((panelWidth/6)*1,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(cannonLogButton);
        		
        		
        		JButton burstLogButton = new JButton("Burst Slayer Logs");
        		burstLogButton.setFont(Globals.mainFont);
        		burstLogButton.setMargin(new Insets(0, 0, 0, 0));
        		burstLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(burstTable);
        			}
        		});
        		burstLogButton.setBounds((panelWidth/6)*2,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(burstLogButton);
        		
        		JButton cannonBurstLogButton = new JButton("Cannon/Burst Slayer Logs");
        		cannonBurstLogButton.setFont(Globals.mainFont);
        		cannonBurstLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonBurstLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonBurstTable);
        			}
        		});
        		cannonBurstLogButton.setBounds((panelWidth/6)*3,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(cannonBurstLogButton);
        		
        		JButton cannonballLogButton = new JButton("Cannonball Purchase Logs");
        		cannonballLogButton.setFont(Globals.mainFont);
        		cannonballLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonballLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonballTable);
        			}
        		});
        		cannonballLogButton.setBounds((panelWidth/6)*4,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(cannonballLogButton);
        		
        		JButton logButton = new JButton("Log of the Logs");
        		logButton.setFont(Globals.mainFont);
        		logButton.setMargin(new Insets(0, 0, 0, 0));
        		logButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(logTable);
        			}
        		});
        		logButton.setBounds((panelWidth/6)*5,panelHeight-Globals.scale(20),panelWidth/6,Globals.scale(20));
        		mainFrame.add(logButton);
        		
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
        		/*normalTable.getColumn("Monster").setCellRenderer(centerRenderer);
        		normalTable.getColumn("Amount").setCellRenderer(rightRenderer);
        		normalTable.getColumn("Loot").setCellRenderer(rightRenderer);
        		normalTable.getColumn("Time").setCellRenderer(centerRenderer);*/
        		normalTable.setDefaultRenderer(Object.class, colourCells());
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
//				cannonTable.getColumn("Monster").setCellRenderer(centerRenderer);
//				cannonTable.getColumn("Amount").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Loot").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Cannonballs Left").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Cannonballs Used").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Cost of Cannonballs").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Profit").setCellRenderer(rightRenderer);
//				cannonTable.getColumn("Time").setCellRenderer(centerRenderer);
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
				/*burstTable.getColumn("Monster").setCellRenderer(centerRenderer);
				burstTable.getColumn("Amount").setCellRenderer(rightRenderer);
				burstTable.getColumn("Loot").setCellRenderer(rightRenderer);
				burstTable.getColumn("Deaths Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Chaos Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Water Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Price of Runes").setCellRenderer(rightRenderer);
				burstTable.getColumn("Profit").setCellRenderer(rightRenderer);
				burstTable.getColumn("Time").setCellRenderer(centerRenderer);*/
				burstTable.setDefaultRenderer(Object.class, colourCells());
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
				/*cannonBurstTable.getColumn("Monster").setCellRenderer(centerRenderer);
				cannonBurstTable.getColumn("Amount").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Loot").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cbs Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Deaths Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Chaos Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Waters Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cost of Cbs").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cost of Runes").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Profit").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Time").setCellRenderer(centerRenderer);*/
				cannonBurstTable.setDefaultRenderer(Object.class, colourCells());
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
				cannonballTable.setFillsViewportHeight(true);
				cannonballTable.getColumn("Total Price").setCellRenderer(rightRenderer);
				cannonballTable.getColumn("Amount of Cannonballs").setCellRenderer(rightRenderer);
				cannonballTable.getColumn("Price per Ball").setCellRenderer(rightRenderer);
				scrollPane.setViewportView(cannonballTable);
        		
        		
                mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mainFrame.setLocationByPlatform(true);
                mainFrame.setVisible(true);
                mainFrame.setResizable(false);
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
				//logModel.addRow(new Object[] {"Cannonballs",amountOfKills[4],totalLoot[0]});
				
				logTable = new JTable(logModel);
				logTable.setFillsViewportHeight(true);
				logTable.getColumn("Type of Log").setCellRenderer(centerRenderer);
				logTable.getColumn("Amount of Kills").setCellRenderer(rightRenderer);
				logTable.getColumn("Total Profit").setCellRenderer(rightRenderer);
				
				
				
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
		            setBackground(table.getBackground());
		            setForeground(table.getForeground());
		            
		        }       
		        return this;
		    }   
		};
	}
}
