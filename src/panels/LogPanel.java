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
import java.util.Comparator;

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
import javax.swing.table.TableRowSorter;

import objects.Globals;
import objects.Monsters;
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
            	int width = Globals.scale(1250);
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
        		normalModel.addColumn("Slayer Exp");
        		normalModel.addColumn("Exp/min");
        		for(String[] array : log.get(0)) {
        			Object[] temp = Monsters.getMonster(array[0]);
        			if(temp == null) {
        				System.err.println("Error in slayerLog with:"+array[0]);
        			}else {
        				String[] time = array[3].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
        				normalModel.addRow(new Object[]{array[0],array[1],array[2],array[3],exp,String.format("%.2f", expPerMin)});
        			}
        		}
        		normalTable = new JTable(normalModel);
        		normalTable.setAutoCreateRowSorter(true);
        		normalTable.setFillsViewportHeight(true);
        		normalTable.setDefaultRenderer(Object.class, colourCells());
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
				DefaultTableModel cannonModel = new DefaultTableModel(); 
				cannonModel.addColumn("Monster"); 
				cannonModel.addColumn("Amount"); 
				cannonModel.addColumn("Loot");
				cannonModel.addColumn("Cannonballs Left");
				cannonModel.addColumn("Cannonballs Used");
				cannonModel.addColumn("Cost of Cannonballs");
				cannonModel.addColumn("Profit");
				cannonModel.addColumn("Time");
				cannonModel.addColumn("Slayer Exp");
				cannonModel.addColumn("Exp/Min");
				
				for(String[] array : log.get(1)) {
					Object[] temp = Monsters.getMonster(array[0]);
        			if(temp == null) {
        				System.err.println("Error in cannonLog with:"+array[0]);
        			}else {
        				String[] time = array[7].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
        				cannonModel.addRow(new Object[] {array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],exp,String.format("%.2f", expPerMin)});
        			}
				}
				cannonTable = new JTable(cannonModel);
				cannonTable.setFillsViewportHeight(true);
				cannonTable.setDefaultRenderer(Object.class, colourCells());
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
				burstModel.addColumn("Slayer Exp");
				burstModel.addColumn("Exp/Min");
				for(String[] array : log.get(2)) {
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
        				System.err.println("Error in burstLog with:"+array[0]);
        			}else {
        				String[] time = array[8].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
            			burstModel.addRow(new Object[] {array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],exp,String.format("%.2f", expPerMin)});
        			}
				}
				burstTable = new JTable(burstModel);
				burstTable.setFillsViewportHeight(true);
				burstTable.setDefaultRenderer(Object.class, colourCells());
				burstTable.setBackground(Globals.panelBackground);
				burstTable.setForeground(Globals.buttonForground);
				burstTable.setFont(Globals.mainFont);
				burstTable.getTableHeader().setFont(Globals.mainFont);
				burstTable.getTableHeader().setBackground(Globals.panelBackground);
				burstTable.getTableHeader().setForeground(Globals.buttonForground);
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
				cannonBurstModel.addColumn("Slayer Exp");
				cannonBurstModel.addColumn("Exp/Min");
				for(String[] array : log.get(3)) {
					Object[] temp = Monsters.getMonster(array[0]);
					if(temp == null) {
        				System.err.println("Error in cannonBurstLog with:"+array[0]);
        			}else {
        				String[] time = array[10].split(":");
            			long timeInMilli = (Integer.parseInt(time[0])*3600000)+(Integer.parseInt(time[1])*60000)+(Integer.parseInt(time[2])*1000);
            			int exp = (Integer.parseInt(array[1])*(int)temp[1]);
            			float expPerMin = ((float)exp/((float)timeInMilli))*60000;
            			cannonBurstModel.addRow(new Object[] {array[0],array[1],array[2],array[3],array[4],array[5],array[6],array[7],array[8],array[9],array[10],exp,String.format("%.2f", expPerMin)});
        			}
				}
				cannonBurstTable = new JTable(cannonBurstModel);
				cannonBurstTable.setFillsViewportHeight(true);
				cannonBurstTable.setDefaultRenderer(Object.class, colourCells());
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

				int[] amountOfKills = {0,0,0,0,0};
				int[] totalLoot = {0,0,0,0,0};
				int absoluteTotalLoot = 0;
				int absoluteAmountOfKills = 0;
				int count = 0;
				for(ArrayList<String[]>logs : log) {
					for(String[] row: logs) {						
						if(row == null || row.length == 0 || row[0].equals("Nothing") ) {
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
				logTable.setBackground(Globals.panelBackground);
				logTable.setForeground(Globals.buttonForground);
				logTable.setFont(Globals.mainFont);
				logTable.getTableHeader().setFont(Globals.mainFont);
				logTable.getTableHeader().setBackground(Globals.panelBackground);
				logTable.getTableHeader().setForeground(Globals.buttonForground);
				
			
				
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
		        
		        Object[] monster = Monsters.getMonster(status);
		        
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
}
