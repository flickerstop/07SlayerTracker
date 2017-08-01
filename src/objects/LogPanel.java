package objects;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultCaret;

import ui.SlayerTrackerUI;


public class LogPanel {
	
	private static float scale = 1.0f;
	private static JTable cannonTable;
	private static JTable burstTable;
	private static JTable normalTable;
	private static JTable cannonBurstTable;
	private static JTable cannonballTable;
	private static Font mainFont = SlayerTrackerUI.mainFont;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build(float scale, Player player) {
		LogPanel.scale = scale;
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	ArrayList<ArrayList<String[]>> log = player.getLogs();
            	int width = scale(1000);
        		int height = scale(550);
        		int panelWidth = width-5;
        		int panelHeight = height-40;
        		
            	JFrame mainFrame = new JFrame("Test");
            	mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/download_icon.png")));
        		mainFrame.setTitle("Slayer Logs\r\n");
        		mainFrame.setBounds(100, 100, width, height);
        		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		mainFrame.getContentPane().setLayout(null);
        		
        		JScrollPane scrollPane = new JScrollPane();
        		scrollPane.setBounds(0, 0, panelWidth, panelHeight-scale(30));
        		mainFrame.getContentPane().add(scrollPane);
        		
        		//////////////////////////////
        		// change log buttons
        		JButton normalLogButton = new JButton("Normal Slayer Logs");
        		normalLogButton.setFont(mainFont);
        		normalLogButton.setMargin(new Insets(0, 0, 0, 0));
        		normalLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(normalTable);
        			}
        		});
        		normalLogButton.setBounds(0,panelHeight-scale(20),panelWidth/5,scale(20));
        		mainFrame.add(normalLogButton);
        		
        		
        		JButton cannonLogButton = new JButton("Cannon Slayer Logs");
        		cannonLogButton.setFont(mainFont);
        		cannonLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonTable);
        			}
        		});
        		cannonLogButton.setBounds((panelWidth/5)*1,panelHeight-scale(20),panelWidth/5,scale(20));
        		mainFrame.add(cannonLogButton);
        		
        		
        		JButton burstLogButton = new JButton("Burst Slayer Logs");
        		burstLogButton.setFont(mainFont);
        		burstLogButton.setMargin(new Insets(0, 0, 0, 0));
        		burstLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(burstTable);
        			}
        		});
        		burstLogButton.setBounds((panelWidth/5)*2,panelHeight-scale(20),panelWidth/5,scale(20));
        		mainFrame.add(burstLogButton);
        		
        		JButton cannonBurstLogButton = new JButton("Cannon/Burst Slayer Logs");
        		cannonBurstLogButton.setFont(mainFont);
        		cannonBurstLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonBurstLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonBurstTable);
        			}
        		});
        		cannonBurstLogButton.setBounds((panelWidth/5)*3,panelHeight-scale(20),panelWidth/5,scale(20));
        		mainFrame.add(cannonBurstLogButton);
        		
        		JButton cannonballLogButton = new JButton("Cannonball Purchase Logs");
        		cannonballLogButton.setFont(mainFont);
        		cannonballLogButton.setMargin(new Insets(0, 0, 0, 0));
        		cannonballLogButton.addMouseListener(new MouseAdapter() {
        			@Override
        			public void mouseClicked(MouseEvent arg0) {
        				scrollPane.setViewportView(cannonballTable);
        			}
        		});
        		cannonballLogButton.setBounds((panelWidth/5)*4,panelHeight-scale(20),panelWidth/5,scale(20));
        		mainFrame.add(cannonballLogButton);
        		
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
        		normalTable.getColumn("Monster").setCellRenderer(centerRenderer);
        		normalTable.getColumn("Amount").setCellRenderer(rightRenderer);
        		normalTable.getColumn("Loot").setCellRenderer(rightRenderer);
        		normalTable.getColumn("Time").setCellRenderer(centerRenderer);
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
				cannonTable.getColumn("Monster").setCellRenderer(centerRenderer);
				cannonTable.getColumn("Amount").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Loot").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Cannonballs Left").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Cannonballs Used").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Cost of Cannonballs").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Profit").setCellRenderer(rightRenderer);
				cannonTable.getColumn("Time").setCellRenderer(centerRenderer);
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
				burstTable.getColumn("Monster").setCellRenderer(centerRenderer);
				burstTable.getColumn("Amount").setCellRenderer(rightRenderer);
				burstTable.getColumn("Loot").setCellRenderer(rightRenderer);
				burstTable.getColumn("Deaths Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Chaos Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Water Used").setCellRenderer(rightRenderer);
				burstTable.getColumn("Price of Runes").setCellRenderer(rightRenderer);
				burstTable.getColumn("Profit").setCellRenderer(rightRenderer);
				burstTable.getColumn("Time").setCellRenderer(centerRenderer);
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
				cannonBurstTable.getColumn("Monster").setCellRenderer(centerRenderer);
				cannonBurstTable.getColumn("Amount").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Loot").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cbs Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Deaths Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Chaos Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Waters Used").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cost of Cbs").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Cost of Runes").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Profit").setCellRenderer(rightRenderer);
				cannonBurstTable.getColumn("Time").setCellRenderer(centerRenderer);
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

			}
        });
    }
	
	public static int scale(int numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public int scale(float numberToScale) {
		return Math.round(numberToScale*scale);
	}
}
