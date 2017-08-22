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
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import objects.CMLData;
import objects.ExpTracker;
import objects.Globals;
import objects.Monsters;
import ui.SlayerTrackerUI;
import ui.SlayerTrackerUI.FrameDragListener;


public class XPTrackerPanel {
	

	static JFrame mainFrame = new JFrame("Test");
	static JPanel dataPanel;
	static int width = Globals.scale(1550);
	static int height = Globals.scale(125)+(Globals.getNumberOfCMLAccounts()+1)*Globals.scale(28);
	static ExpTracker[] players = null;
	static ArrayList<JLabel> labels = new ArrayList<JLabel>();
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build() {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	
        		
            	
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
				titleLabel.setText("Xp Tracker Panel v"+Globals.versionNumber);
				titleLabel.setForeground(new Color(214, 214, 214));
				titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
				titleLabel.setFont(Globals.mainFont);
				topBar.add(titleLabel);
				//////////////////////////////////////////////////////////////////////////////////////////////////////////
        		
				JLabel dailyGainsInfoLabel = new JLabel("This Colour is for xp you earned today");
				JLabel weeklyGainsInfoLabel = new JLabel("This Colour is for xp you earned this week");
				
				dailyGainsInfoLabel.setBounds(0,Globals.scale(30),width,Globals.scale(40));
				weeklyGainsInfoLabel.setBounds(0,Globals.scale(45),width,Globals.scale(40));
				
				dailyGainsInfoLabel.setFont(Globals.boldFont);
				weeklyGainsInfoLabel.setFont(Globals.boldFont);
				
				dailyGainsInfoLabel.setForeground(Globals.buttonForground);
				weeklyGainsInfoLabel.setForeground(Globals.red);
				
				dailyGainsInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
				weeklyGainsInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
				////////////////////////////////
				
				JButton updateButton = new JButton("Update Data");
				updateButton.setBounds(0,Globals.topMenuBarHeight,Globals.scale(150),Globals.scale(25));
				updateButton.setForeground(Globals.white);
				updateButton.setBackground(Globals.blue);
				updateButton.setFont(Globals.mainFont);
				updateButton.addMouseListener(new MouseAdapter() {
		    		@Override
		    		public void mouseClicked(MouseEvent arg0) {
		    			CMLData.updateMultiple(Globals.getCMLAccounts());
		    			height = Globals.scale(125)+(Globals.getNumberOfCMLAccounts()+1)*Globals.scale(28);
		    			mainFrame.getContentPane().remove(dataPanel);
		    			dataPanel = getData();
		    			mainFrame.getContentPane().add(dataPanel);
		    			mainFrame.setBounds(100, 100, width, height);
		    			mainFrame.repaint();
		    		}
				});
				
				/////////////////////////////////
				mainFrame.getContentPane().add(updateButton);
				mainFrame.getContentPane().add(dailyGainsInfoLabel);
				mainFrame.getContentPane().add(weeklyGainsInfoLabel);
        		
        		
        		
        		
        		dataPanel = getData();
        		mainFrame.getContentPane().add(dataPanel);
        		
                mainFrame.setLocationByPlatform(true);
				mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);

			}
        });
    }
	public static void makeVisible() {
		mainFrame.setVisible(true);

	}
	
	public static ImageIcon getImage(String url) {
		return new ImageIcon(XPTrackerPanel.class.getResource(url));
	}
	public static void setup() {
		LoadingPopUp.rebuild("Loading XP Rates!");
		new Thread(){
            @Override
            public void run() {
            	Globals.startTimer();
            	Globals.outCurrentTime();
				players = CMLData.getMultipleAccounts(Globals.getCMLAccounts());
				Globals.outCurrentTime();
				build();
				Globals.outCurrentTime();
				LoadingPopUp.hide();
            }
		}.start();
	}
	public static boolean isInit() {
		return !(players == null);
	}
	
	public static JPanel getData() {
		players = CMLData.getMultipleAccounts(Globals.getCMLAccounts());
		ImageIcon[] columns = {
				getImage("/images/skills/overall.gif"),
				getImage("/images/skills/attack.gif"),
				getImage("/images/skills/defence.gif"),
				getImage("/images/skills/strength.gif"),
				getImage("/images/skills/hitpoints.gif"),
				getImage("/images/skills/ranged.gif"),
				getImage("/images/skills/prayer.gif"),
				getImage("/images/skills/magic.gif"),
				getImage("/images/skills/cooking.gif"),
				getImage("/images/skills/woodcutting.gif"),
				getImage("/images/skills/fletching.gif"),
				getImage("/images/skills/fishing.gif"),
				getImage("/images/skills/firemaking.gif"),
				getImage("/images/skills/crafting.gif"),
				getImage("/images/skills/smithing.gif"),
				getImage("/images/skills/mining.gif"),
				getImage("/images/skills/herblore.gif"),
				getImage("/images/skills/agility.gif"),
				getImage("/images/skills/thieving.gif"),
				getImage("/images/skills/slayer.gif"),
				getImage("/images/skills/runecrafting.gif"),
				getImage("/images/skills/hunter.gif"),
				getImage("/images/skills/construction.gif")
		};
		
		JPanel outputPanel = new JPanel();
		outputPanel.setBounds(0,0,width,height);
		outputPanel.setLayout(null);
		outputPanel.setBackground(Globals.panelBackground);
		
		int nameWidth = Globals.scale(125);
		int columnWidth = (width-nameWidth)/23;
		int rowHeight = (Globals.mainFont.getSize()*2)+Globals.scale(5);
		int subRowHeight = Globals.mainFont.getSize()+Globals.scale(2);
		
		// Draw the icons
		for(int i = 0; i < 23; i++) {
			JLabel label = new JLabel(columns[i]);
			label.setBounds(nameWidth+(i*columnWidth),Globals.scale(100),columnWidth,rowHeight);
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			outputPanel.add(label);
		}
		
		for(int playerNum = 0; playerNum < players.length; playerNum++) {
			//CMLData.updateMultiple(players);
    		int[] dailyGains = players[playerNum].getDailyGains();
    		int[] weeklyGains = players[playerNum].getWeeklyGains();
    		for(int i = 0; i < 23; i++) {
    			JLabel dailyGainsLabel = new JLabel(NumberFormat.getNumberInstance(Locale.US).format(dailyGains[i]));
    			dailyGainsLabel.setBounds(nameWidth+(i*columnWidth),Globals.scale(100)+(rowHeight*(playerNum+1)),columnWidth,subRowHeight);
    			dailyGainsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    			dailyGainsLabel.setForeground(Globals.buttonForground);
    			dailyGainsLabel.setFont(Globals.mainFont);
    			
    			JLabel weeklyGainsLabel = new JLabel(NumberFormat.getNumberInstance(Locale.US).format(weeklyGains[i]));
    			weeklyGainsLabel.setBounds(nameWidth+(i*columnWidth),Globals.scale(100)+(rowHeight*(playerNum+1))+subRowHeight,columnWidth,subRowHeight);
    			weeklyGainsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    			weeklyGainsLabel.setForeground(Globals.red);
    			weeklyGainsLabel.setFont(Globals.mainFont);
    			//label.setFont(Globals.smallFont);
    			
    			
    			
    			JSeparator line = new JSeparator();
    			line.setForeground(Globals.iconGrey);
    			line.setBackground(Globals.iconGrey);
    			line.setBounds(0,Globals.scale(100)+(rowHeight*(playerNum+1))+rowHeight,width,Globals.scale(2));
    			
    			
    			outputPanel.add(dailyGainsLabel);
    			outputPanel.add(weeklyGainsLabel);
    			outputPanel.add(line);
    		}
    		JLabel accountName = new JLabel(players[playerNum].accountName);
			accountName.setBounds(0,Globals.scale(100)+(rowHeight*(playerNum+1)),nameWidth,rowHeight);
			accountName.setHorizontalAlignment(SwingConstants.CENTER);
			accountName.setForeground(Globals.buttonForground);
			accountName.setFont(Globals.boldFont);
			outputPanel.add(accountName);
		}
		return outputPanel;
	}
	
}
