package ui;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.text.NumberFormatter;

import objects.Globals;
import objects.Player;
import panels.FarmRunPanel;
import panels.LogPanel;
import panels.MonsterPanel;
import panels.SettingsPanel;

import javax.swing.JTextPane;
import javax.swing.JLabel;
import java.awt.Frame;

import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

public class SlayerTrackerUI {
	private static JFrame mainFrame;
	int cannonballs = 0;
	private JFormattedTextField amountOfCannonballsBought;
	private JFormattedTextField pricePaidForCannonballs;
	JTextPane deathRuneTextPane;
	JTextPane chaosRuneTextPane;
	JTextPane waterRuneTextPane;
	private JPanel mainPanel;
	private JSpinner monsterCountSpinner;
	static SlayerTrackerUI window;
	Player player = new Player();
	
	JTextPane txtpnCannonballs = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		if(Globals.isSafeEdit) {
			Globals.errorFile = "error.log";
		}
		try {
			PrintStream logFile = new PrintStream(new FileOutputStream(Globals.errorFile, true));
			logFile.println("---------------\n"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))+"\n---------------");
			System.setOut(logFile);
			System.setErr(logFile);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Globals.load();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new SlayerTrackerUI();
					SlayerTrackerUI.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
	}

	/**
	 * Create the application.
	 */
	public SlayerTrackerUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		player.load();
		
		int width = Globals.width;
		
		int height = Globals.height;
		
		int panelWidth = width;
		
		String title = "";
		int numButtonCol = 5;
		int buttonSpace = Globals.scale(10);
		int buttonWidth = ((panelWidth-Globals.scale(30))-((numButtonCol-1)*buttonSpace))/numButtonCol;
		int buttonHeight = Globals.scale(100);
		
		///
		// Button coords
		int row1Y = (Globals.panelHeight-15)-(buttonWidth*1)-(buttonSpace*1);
		int row2Y = (Globals.panelHeight-15)-(buttonWidth*2)-(buttonSpace*2);
		int row3Y = (Globals.panelHeight-15)-(buttonWidth*3)-(buttonSpace*3);
		int col1X = 15;
		int col2X = 15+(buttonWidth+buttonSpace);
		int col3X = 15+(buttonWidth+buttonSpace)*2;
		int col4X = 15+(buttonWidth+buttonSpace)*3;
		int col5X = 15+(buttonWidth+buttonSpace)*4;
		
		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/slayerIcon.png")));
		if(player.isSafeEdit()) {
			title = "Jr2254's Slayer Tracker "+Globals.versionNumber+" - SAFE EDIT MODE\r\n";
		}else {
			title = "Jr2254's Slayer Tracker "+Globals.versionNumber+"\r\n";
		}
		mainFrame.setTitle(title);
		mainFrame.setBounds(100, 100, width, height);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(null);
		
		mainFrame.addWindowListener(new WindowAdapter()
		{
		    public void windowClosing(WindowEvent e)
		    {
		        player.save();
		    }
		});
		
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

		//////////////////////////////
		// Top Bar
		FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
		JPanel topBar = new JPanel();
		topBar.setBounds(0, 0, panelWidth, Globals.topMenuBarHeight);
		topBar.setBackground(Globals.topBarColour);
		topBar.addMouseListener(frameDragListener);
		topBar.addMouseMotionListener(frameDragListener);
		topBar.setLayout(null);
		mainFrame.getContentPane().add(topBar);
		
		JButton closeButton = new JButton();
		closeButton.setFocusPainted(false);
		closeButton.setIcon(new ImageIcon(SlayerTrackerUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
		closeButton.setBackground(new Color(231, 76, 60));
		closeButton.setBounds(panelWidth-Globals.scale(25), 0, Globals.scale(25), Globals.scale(25));
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
		minimizeButton.setBounds(panelWidth-Globals.scale(50), 0, Globals.scale(25), Globals.scale(25));
		minimizeButton.setMargin(new Insets(0, 0, 0, 0));
		minimizeButton.setToolTipText("Minimize Window");
		minimizeButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
				mainFrame.setState(Frame.ICONIFIED);
    		}
		});
		topBar.add(minimizeButton);
		
		JLabel settingsButton = new JLabel();
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/settingsIcon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(Globals.scale(25), Globals.scale(25),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			settingsButton.setIcon(imageIcon);
		}
		settingsButton.setBounds(panelWidth-Globals.scale(80), 0, Globals.scale(25), Globals.scale(25));
		settingsButton.setToolTipText("Settings");
		settingsButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
				SettingsPanel temp = new SettingsPanel();
				temp.build();
    		}
		});
		topBar.add(settingsButton);
		
		
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(Globals.scale(5),0,panelWidth/2,Globals.scale(25));
		titleLabel.setText(title);
		titleLabel.setForeground(new Color(214, 214, 214));
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(Globals.mainFont);
		topBar.add(titleLabel);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Panels

		mainPanel = new JPanel();
		mainPanel.setBackground(Globals.panelBackground);
		mainPanel.setBounds(0, Globals.topMenuBarHeight, panelWidth, Globals.panelHeight);
		mainFrame.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		mainPanel.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e )
	        {
	            updateCannonballs();
	            updateRunes();
	            player.save();
	            monsterCountSpinner.setValue(0);
	        }

//	        public void componentHidden ( ComponentEvent e )
//	        {
//	            //System.out.println ( "Component hidden" );
//	        }
	    } );
		
		JPanel addCannonballsPanel = new JPanel();
		addCannonballsPanel.setBackground(Globals.panelBackground);
		addCannonballsPanel.setBounds(0, 0, panelWidth, Globals.panelHeight);
		mainFrame.getContentPane().add(addCannonballsPanel);
		addCannonballsPanel.setLayout(null);

		JPanel addRunesPanel = new JPanel();
		addRunesPanel.setBackground(Globals.panelBackground);
		addRunesPanel.setBounds(0, 0, panelWidth, Globals.panelHeight);
		mainFrame.getContentPane().add(addRunesPanel);
		addRunesPanel.setLayout(null);
		
		addCannonballsPanel.setVisible(false);
		addRunesPanel.setVisible(false);
//////////////////////////////////////
// ROW 1
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dust Devil
		
		
		
		JButton btnDustDevil = new JButton();
		btnDustDevil.setBackground(Globals.buttonBackground);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Dust_devil.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnDustDevil.setIcon(imageIcon);
		}
		btnDustDevil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openMonsterPanel("Dust Devils",false,true);
			}
		});
		btnDustDevil.setBounds(col1X, row1Y, buttonWidth, buttonHeight);
		mainPanel.add(btnDustDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Smoke Devil
		JButton btnSmokeDevil = new JButton();
		btnSmokeDevil.setBackground(Globals.buttonBackground);
		btnSmokeDevil.setBounds(col2X, row1Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Smoke_devil.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnSmokeDevil.setIcon(imageIcon);
		}
		btnSmokeDevil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				openMonsterPanel("Smoke Devils",true,true);
			}
		});
		mainPanel.add(btnSmokeDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Abby Spec
		JButton btnAbbySpec = new JButton();
		btnAbbySpec.setBackground(Globals.buttonBackground);
		btnAbbySpec.setBounds(col3X, row1Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Aberrant_spectre.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnAbbySpec.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Aberrant Spectres",true,false);
				}
			});
			btnAbbySpec.setIcon(imageIcon);
		}
		
		mainPanel.add(btnAbbySpec);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// other
		JButton otherButton = new JButton();
		otherButton.setBackground(Globals.buttonBackground);
		otherButton.setBounds(col5X, row1Y, buttonWidth, buttonHeight);
		
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/question_mark.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			otherButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("other",false,false);
				}
			});
			otherButton.setIcon(imageIcon);
		}
		mainPanel.add(otherButton);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Bloodveld
		JButton btnBloodveld = new JButton();
		btnBloodveld.setBackground(Globals.buttonBackground);
		btnBloodveld.setBounds(col4X, row1Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Bloodveld.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnBloodveld.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Bloodvelds",true,false);
				}
			});
			btnBloodveld.setIcon(imageIcon);
		}
		mainPanel.add(btnBloodveld);
		
//////////////////////////////////////
// ROW 2
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Blue Dragon
		JButton btnBlueDragon = new JButton();
		btnBlueDragon.setBackground(Globals.buttonBackground);
		btnBlueDragon.setBounds(col1X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Blue_dragon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnBlueDragon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Blue Dragons",true,false);
				}
			});
			btnBlueDragon.setIcon(imageIcon);
		}
		mainPanel.add(btnBlueDragon);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dagannoth
		JButton btnDagannoth = new JButton();
		btnDagannoth.setBackground(Globals.buttonBackground);
		btnDagannoth.setBounds(col2X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Dagannoth.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnDagannoth.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Dagannoths",true,false);
				}
			});
			btnDagannoth.setIcon(imageIcon);
		}
		mainPanel.add(btnDagannoth);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Fire Giant
		JButton btnFireGiant = new JButton();
		btnFireGiant.setBackground(Globals.buttonBackground);
		btnFireGiant.setBounds(col3X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Fire_giant.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnFireGiant.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Fire Giants",true,false);
				}
			});
			btnFireGiant.setIcon(imageIcon);
		}
		mainPanel.add(btnFireGiant);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Gargoyle
		JButton btnGargoyle = new JButton();
		btnGargoyle.setBackground(Globals.buttonBackground);
		btnGargoyle.setBounds(col4X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/garg.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnGargoyle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Gargoyle",false,false);
				}
			});
			btnGargoyle.setIcon(imageIcon);
		}
		mainPanel.add(btnGargoyle);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Greater Demon
		JButton btnGreaterDemon = new JButton();
		btnGreaterDemon.setBackground(Globals.buttonBackground);
		btnGreaterDemon.setBounds(col5X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Greater_demon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnGreaterDemon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Greater Demon",true,false);
				}
			});
			btnGreaterDemon.setIcon(imageIcon);
		}
		mainPanel.add(btnGreaterDemon);
		
//////////////////////////////////////
// ROW 3
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Trolls
		JButton btnTrolls = new JButton();
		btnTrolls.setBackground(Globals.buttonBackground);
		btnTrolls.setBounds(col1X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Ice_troll_female.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnTrolls.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Trolls",true,false);
				}
			});
			btnTrolls.setIcon(imageIcon);
		}
		mainPanel.add(btnTrolls);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Kalphite
		JButton btnKalphite = new JButton();
		btnKalphite.setBackground(Globals.buttonBackground);
		btnKalphite.setBounds(col2X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Kalphite_Worker.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnKalphite.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Kalphites",true,false);
				}
			});
			btnKalphite.setIcon(imageIcon);
		}
		mainPanel.add(btnKalphite);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Lizardman
		JButton btnLizardman = new JButton();
		btnLizardman.setBackground(Globals.buttonBackground);
		btnLizardman.setBounds(col3X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Lizardman.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnLizardman.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Lizardmen",false,true);
				}
			});
			btnLizardman.setIcon(imageIcon);
		}
		mainPanel.add(btnLizardman);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Nechryael
		JButton btnNechryael = new JButton();
		btnNechryael.setBackground(Globals.buttonBackground);
		btnNechryael.setBounds(col4X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Nechryael.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnNechryael.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Nechryaels",false,true);
				}
			});
			btnNechryael.setIcon(imageIcon);
		}
		mainPanel.add(btnNechryael);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Wyvern
		JButton btnWyvern = new JButton();
		btnWyvern.setBackground(Globals.buttonBackground);
		btnWyvern.setBounds(col5X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Skeletal_Wyvern.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnWyvern.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Wyverns",false,false);
				}
			});
			btnWyvern.setIcon(imageIcon);
		}
		mainPanel.add(btnWyvern);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Farm Run);
		
		JButton startFarmRunButton = new JButton("Farm Run Timer");
		startFarmRunButton.setForeground(Globals.buttonForground);
		startFarmRunButton.setBackground(Globals.buttonBackground);
		startFarmRunButton.setFont(Globals.mainFont);
		startFarmRunButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				FarmRunPanel.build(player,false);
			}
		});
		startFarmRunButton.setBounds(panelWidth-Globals.scale(150), Globals.scale(60), Globals.scale(150), Globals.scale(25));
		mainPanel.add(startFarmRunButton);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonballs
		updateCannonballs();
		txtpnCannonballs.setFont(Globals.mainFont);
		txtpnCannonballs.setEditable(false);
		txtpnCannonballs.setBounds(panelWidth-Globals.scale(150), Globals.scale(5), Globals.scale(150), Globals.scale(25));
		mainPanel.add(txtpnCannonballs);
		
		JButton btnAddCannonballs = new JButton("Add Cannonballs");
		btnAddCannonballs.setForeground(Globals.buttonForground);
		btnAddCannonballs.setBackground(Globals.buttonBackground);
		btnAddCannonballs.setFont(Globals.mainFont);
		btnAddCannonballs.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
		mainPanel.setVisible(false);
		addCannonballsPanel.setVisible(true);
		}
		});
		btnAddCannonballs.setBounds(panelWidth-Globals.scale(150), Globals.scale(30), Globals.scale(150), Globals.scale(25));
mainPanel.add(btnAddCannonballs);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Monster Count
		
		monsterCountSpinner = new JSpinner();
		monsterCountSpinner.setForeground(Globals.buttonForground);
		monsterCountSpinner.setBackground(Globals.buttonBackground);
		monsterCountSpinner.setModel(new SpinnerNumberModel(0, 0, 300, 1));
		monsterCountSpinner.setBounds((panelWidth/2)-Globals.scale(150/2), Globals.scale(65), Globals.scale(150), Globals.scale(25));
		monsterCountSpinner.setFont(Globals.mainFont);
		mainPanel.add(monsterCountSpinner);
		
		
		JLabel monsterCountLabel = new JLabel("Number of Monsters");
		monsterCountLabel.setForeground(Globals.buttonForground);
		monsterCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monsterCountLabel.setFont(Globals.boldFont);
		monsterCountLabel.setBounds((panelWidth/2)-Globals.scale(150/2), Globals.scale(40), Globals.scale(150), Globals.scale(25));
		mainPanel.add(monsterCountLabel);
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonball jpane
		
		JLabel lblAmountBought = new JLabel("Amount Bought");
		lblAmountBought.setForeground(Globals.buttonForground);
		lblAmountBought.setFont(Globals.mainFont);
		lblAmountBought.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(100), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(lblAmountBought);
		
		
		amountOfCannonballsBought = new JFormattedTextField(formatter);
		amountOfCannonballsBought.setFont(Globals.mainFont);
		amountOfCannonballsBought.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(130), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(amountOfCannonballsBought);
		amountOfCannonballsBought.setColumns(10);
		
		JLabel lblPricePaid = new JLabel("Price Per Ball");
		lblPricePaid.setForeground(Globals.buttonForground);
		lblPricePaid.setFont(Globals.mainFont);
		lblPricePaid.setBounds((panelWidth/2)+Globals.scale(15), Globals.scale(100), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(lblPricePaid);
		
		pricePaidForCannonballs = new JFormattedTextField(formatter);
		pricePaidForCannonballs.setFont(Globals.mainFont);
		pricePaidForCannonballs.setBounds((panelWidth/2)+Globals.scale(15), Globals.scale(130), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(pricePaidForCannonballs);
		pricePaidForCannonballs.setColumns(10);
		
		
		
		
		
		JButton addCannonballsButton = new JButton("Add Cannonballs");
		addCannonballsButton.setForeground(Globals.buttonForground);
		addCannonballsButton.setBackground(Globals.buttonBackground);
		addCannonballsButton.setFont(Globals.mainFont);
		addCannonballsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(amountOfCannonballsBought.getText().length() > 0 && pricePaidForCannonballs.getText().length() > 0) {
					player.updateCannonbals(Integer.parseInt(amountOfCannonballsBought.getText().replaceAll(",", "")), Integer.parseInt(pricePaidForCannonballs.getText().replaceAll(",", "")));
					updateCannonballs();
					mainPanel.setVisible(true);
					addCannonballsPanel.setVisible(false);
				}
				
			}
		});
		addCannonballsButton.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(200), Globals.scale(230), Globals.scale(25));
		addCannonballsPanel.add(addCannonballsButton);
		
		JButton cancelBuyingCannonBalls = new JButton("Cancel");
		cancelBuyingCannonBalls.setFont(Globals.mainFont);
		cancelBuyingCannonBalls.setBackground(new Color(255, 0, 0));
		cancelBuyingCannonBalls.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(225), Globals.scale(230), Globals.scale(25));
		cancelBuyingCannonBalls.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				mainPanel.setVisible(true);
				addCannonballsPanel.setVisible(false);
			}
		});
		addCannonballsPanel.add(cancelBuyingCannonBalls);
		
		
		////////////////////////////////////////////////////
		// Rune lables
		deathRuneTextPane = new JTextPane();
		deathRuneTextPane.setFont(Globals.mainFont);
		deathRuneTextPane.setBounds(Globals.scale(15), Globals.scale(5), Globals.scale(150), Globals.scale(25));
		deathRuneTextPane.setEditable(false);
		deathRuneTextPane.setText("Death Runes: ");
		mainPanel.add(deathRuneTextPane);
		
		chaosRuneTextPane = new JTextPane();
		chaosRuneTextPane.setFont(Globals.mainFont);
		chaosRuneTextPane.setEditable(false);
		chaosRuneTextPane.setBounds(Globals.scale(15), Globals.scale(30), Globals.scale(150), Globals.scale(25));
		chaosRuneTextPane.setText("Chaos Runes: ");
		mainPanel.add(chaosRuneTextPane);
		
		waterRuneTextPane = new JTextPane();
		waterRuneTextPane.setEditable(false);
		waterRuneTextPane.setFont(Globals.mainFont);
		waterRuneTextPane.setBounds(Globals.scale(15), Globals.scale(55), Globals.scale(150), Globals.scale(25));
		waterRuneTextPane.setText("Water Runes: ");
		mainPanel.add(waterRuneTextPane);
		
		updateRunes();
		
		
		JButton changeRunesButton = new JButton("Change Runes");
		changeRunesButton.setForeground(Globals.buttonForground);
		changeRunesButton.setBackground(Globals.buttonBackground);
		changeRunesButton.setFont(Globals.mainFont);
		changeRunesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addRunesPanel.setVisible(true);
				mainPanel.setVisible(false);
			}
		});
		changeRunesButton.setBounds(Globals.scale(15), Globals.scale(80), Globals.scale(150), Globals.scale(25));
		mainPanel.add(changeRunesButton);
		
		//////////////////////////////////////////////////////
		// Change Runes Panel
		
		JLabel addDeathRunesLabel = new JLabel("Amount of Death Runes");
		addDeathRunesLabel.setForeground(Globals.buttonForground);
		addDeathRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addDeathRunesLabel.setFont(Globals.mainFont);
		addDeathRunesLabel.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(100), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addDeathRunesLabel);
		
		JFormattedTextField addDeathRunesTextField = new JFormattedTextField(formatter);
		addDeathRunesTextField.setText(player.getDeathRunes()+"");
		addDeathRunesTextField.setFont(Globals.mainFont);
		addDeathRunesTextField.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(125), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addDeathRunesTextField);
		addDeathRunesTextField.setColumns(10);
		
		JLabel addChaosRunesLabel = new JLabel("Amount of Chaos Runes");
		addChaosRunesLabel.setForeground(Globals.buttonForground);
		addChaosRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addChaosRunesLabel.setFont(Globals.mainFont);
		addChaosRunesLabel.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(150), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addChaosRunesLabel);
		
		JFormattedTextField addChaosRunesTextField = new JFormattedTextField(formatter);
		addChaosRunesTextField.setText(player.getChaosRunes()+"");
		addChaosRunesTextField.setFont(Globals.mainFont);
		addChaosRunesTextField.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(175), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addChaosRunesTextField);
		addChaosRunesTextField.setColumns(10);
		
		JLabel addWaterRunesLabel = new JLabel("Amount of Water Runes");
		addWaterRunesLabel.setForeground(Globals.buttonForground);
		addWaterRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addWaterRunesLabel.setFont(Globals.mainFont);
		addWaterRunesLabel.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(200), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addWaterRunesLabel);
		
		JFormattedTextField addWaterRunesTextField = new JFormattedTextField(formatter);
		addWaterRunesTextField.setText(player.getWaterRunes()+"");
		addWaterRunesTextField.setFont(Globals.mainFont);
		addWaterRunesTextField.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(225), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(addWaterRunesTextField);
		addWaterRunesTextField.setColumns(10);
		
		JButton acceptChangeRunesButton = new JButton("Change Runes");
		acceptChangeRunesButton.setForeground(Globals.buttonForground);
		acceptChangeRunesButton.setBackground(Globals.buttonBackground);
		acceptChangeRunesButton.setFont(Globals.mainFont);
		acceptChangeRunesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addDeathRunesTextField.getText().length() > 0 &&
						addChaosRunesTextField.getText().length() > 0 &&
						addWaterRunesTextField.getText().length() > 0) {
					player.setDeathRunes(Integer.parseInt(addDeathRunesTextField.getText().replaceAll(",", "")));
					player.setChaosRunes(Integer.parseInt(addChaosRunesTextField.getText().replaceAll(",", "")));
					player.setWaterRunes(Integer.parseInt(addWaterRunesTextField.getText().replaceAll(",", "")));
					updateRunes();
					mainPanel.setVisible(true);
					addRunesPanel.setVisible(false);
				}
			}
		});
		acceptChangeRunesButton.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(250), Globals.scale(150), Globals.scale(25));
		addRunesPanel.add(acceptChangeRunesButton);
		//////////////////////////////////////////////////////
		// View log button
		JButton viewLogButton = new JButton("Logs");
		viewLogButton.setForeground(Globals.buttonForground);
		viewLogButton.setBackground(Globals.buttonBackground);
		viewLogButton.setFont(Globals.smallFont);
		viewLogButton.setMargin(new Insets(0, 0, 0, 0));
		viewLogButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LogPanel.build(player);
			}
		});
		viewLogButton.setBounds(panelWidth-Globals.scale(50), Globals.panelHeight-Globals.scale(25), Globals.scale(50), Globals.scale(25));
		mainPanel.add(viewLogButton);

		
	}
	public void updateCannonballs() {
		txtpnCannonballs.setText("Cannonballs: "+player.getCannonballs());
		//System.out.println(cannonballs);
	}
	
	public void updateRunes() {
		deathRuneTextPane.setText("Death Runes: "+player.getDeathRunes());
		chaosRuneTextPane.setText("Chaos Runes: "+player.getChaosRunes());
		waterRuneTextPane.setText("Water Runes: "+player.getWaterRunes());
	}
	

	
	public void openMonsterPanel(String monsterName, boolean isCannon, boolean isBurst) {
		int count = (int) monsterCountSpinner.getValue();
		if(count == 0) {
			monsterCountSpinner.setBackground(new Color(255, 0, 0));
		}else {
			// This if fixes the issue of unlimited panes being created
			if(mainFrame.getContentPane().getComponentCount() > 3) {
				mainFrame.getContentPane().remove(mainFrame.getContentPane().getComponentCount()-1);
			}
			monsterCountSpinner.setBackground(Globals.buttonBackground);
			mainPanel.setVisible(false);
			MonsterPanel monsterPanel = new MonsterPanel(monsterName, null, count, player);
			mainFrame.getContentPane().add(monsterPanel.build(mainPanel,isCannon,isBurst));
		}
		//System.out.println(mainFrame.getContentPane().getComponentCount());
	}
	public static class FrameDragListener extends MouseAdapter {

        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }

        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }

        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }

        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }
	
	public static void reload() {
		SlayerTrackerUI.mainFrame.setVisible(false);
		Globals.reload();
		window = new SlayerTrackerUI();
		SlayerTrackerUI.mainFrame.setVisible(true);
	}

}
