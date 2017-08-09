package ui;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
import javax.swing.Timer;
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
	MonsterPanel monsterPanel;
	
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
			PrintStream outFile = new PrintStream(new FileOutputStream(Globals.outputFile, true));
			outFile.println("---------------\n"+LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))+"\n---------------");
			if(!Globals.isSafeEdit) {
				System.setOut(outFile);
				System.setErr(logFile);
			}
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
		        Globals.save();
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
		

		JLabel logsButton = new JLabel();
		logsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/logs_icon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(Globals.scale(25), Globals.scale(25),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			logsButton.setIcon(imageIcon);
		}
		logsButton.setBounds(panelWidth-Globals.scale(115), 0, Globals.scale(25), Globals.scale(25));
		logsButton.setToolTipText("Settings");
		logsButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			LogPanel.build(player);
    		}
		});
		topBar.add(logsButton);
		
		JLabel farmButton = new JLabel();
		farmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Farming_icon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(Globals.scale(25), Globals.scale(25),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			farmButton.setIcon(imageIcon);
		}
		farmButton.setBounds(panelWidth-Globals.scale(150), 0, Globals.scale(25), Globals.scale(25));
		farmButton.setToolTipText("Farm Run");
		farmButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			if(!FarmRunPanel.isInit()) {
    				FarmRunPanel.build(false);
    			}else {
    				FarmRunPanel.makeVisible();
    			}
    		}
		});
		topBar.add(farmButton);
		
		
		JLabel titleLabel = new JLabel();
		titleLabel.setBounds(Globals.scale(5),0,panelWidth/2,Globals.scale(25));
		titleLabel.setText(title);
		titleLabel.setForeground(new Color(214, 214, 214));
		titleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		titleLabel.setFont(Globals.mainFont);
		topBar.add(titleLabel);
		
		JLabel farmRunLabel = new JLabel();
		farmRunLabel.setBounds(0,0,panelWidth,Globals.scale(25));
		farmRunLabel.setText("00:00:00");
		farmRunLabel.setForeground(Globals.iconGrey);
		farmRunLabel.setHorizontalAlignment(SwingConstants.CENTER);
		farmRunLabel.setFont(Globals.mainFont);
		topBar.add(farmRunLabel);
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
		
		JPanel addRunesPanel = new JPanel();
		addRunesPanel.setBackground(Globals.panelBackground);
		addRunesPanel.setBounds(0, 0, panelWidth, Globals.panelHeight);
		mainFrame.getContentPane().add(addRunesPanel);
		addRunesPanel.setLayout(null);
		
		JPanel addCannonballsPanel = new JPanel();
		addCannonballsPanel.setBackground(Globals.panelBackground);
		addCannonballsPanel.setBounds(0, 0, panelWidth, Globals.panelHeight);
		mainFrame.getContentPane().add(addCannonballsPanel);
		addCannonballsPanel.setLayout(null);

		
		
		addCannonballsPanel.setVisible(false);
		addRunesPanel.setVisible(false);
		
		///
		// Button coords
		
		int buttonSpaceY = Globals.scale(105);
		
		int buttonSpaceHeight = Globals.panelHeight-buttonSpaceY;
		int spaceBetweenButtons = Globals.scale(10);
		
		int buttonWidth = (panelWidth-(spaceBetweenButtons*(Globals.numberOfCols+1)))/Globals.numberOfCols;
		int buttonHeight = (buttonSpaceHeight-(spaceBetweenButtons*(Globals.numberOfRows+1)))/Globals.numberOfRows;
		
		int y[] = new int[Globals.numberOfRows];
		int x[] = new int[Globals.numberOfCols];
		
		for(int i = 0; i < Globals.numberOfRows; i++) {
			y[i] = buttonSpaceY + spaceBetweenButtons*(i+1) + buttonHeight*(i);
		}
		for(int i = 0; i < Globals.numberOfCols; i++) {
			x[i] = spaceBetweenButtons*(i+1) + buttonWidth*(i);
		}
		
		int monsterNum = 0;
		for(int row = 0; row != Globals.numberOfRows; row++) {
			for(int col = 0; col != Globals.numberOfCols; col++) {
				final int tempMonsterNum = monsterNum;
				JButton temp = new JButton();
				temp.setBackground(Globals.buttonBackground);
				temp.setBounds(x[col], y[row], buttonWidth, buttonHeight);
				ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/"+(String)Globals.prefMonsters[monsterNum][2])); // load the image to a imageIcon
				Image image = imageIcon.getImage(); // transform it 
				Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
				imageIcon = new ImageIcon(newimg);  // transform it back
				temp.setIcon(imageIcon);
				if(((String)Globals.prefMonsters[tempMonsterNum][0]).equals("No Monster Set")) {
					temp.setToolTipText("No monster set, open the settings to add a monster here!");
				}else {
					temp.setToolTipText((String)Globals.prefMonsters[monsterNum][0]);
				}
				temp.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(!((String)Globals.prefMonsters[tempMonsterNum][0]).equals("No Monster Set")) {
							openMonsterPanel((String)Globals.prefMonsters[tempMonsterNum][0]);
						}
					}
				});
				mainPanel.add(temp);
				monsterNum++;
			}
		}
	

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonballs
		updateCannonballs();
		txtpnCannonballs.setFont(Globals.mainFont);
		txtpnCannonballs.setEditable(false);
		txtpnCannonballs.setBounds(panelWidth-Globals.scale(150), Globals.scale(5), Globals.scale(140), Globals.scale(25));
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
		btnAddCannonballs.setBounds(panelWidth-Globals.scale(150), Globals.scale(30), Globals.scale(140), Globals.scale(25));
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
		deathRuneTextPane.setBounds(Globals.scale(10), Globals.scale(5), Globals.scale(140), Globals.scale(25));
		deathRuneTextPane.setEditable(false);
		deathRuneTextPane.setText("Death Runes: ");
		mainPanel.add(deathRuneTextPane);
		
		chaosRuneTextPane = new JTextPane();
		chaosRuneTextPane.setFont(Globals.mainFont);
		chaosRuneTextPane.setEditable(false);
		chaosRuneTextPane.setBounds(Globals.scale(10), Globals.scale(30), Globals.scale(140), Globals.scale(25));
		chaosRuneTextPane.setText("Chaos Runes: ");
		mainPanel.add(chaosRuneTextPane);
		
		waterRuneTextPane = new JTextPane();
		waterRuneTextPane.setEditable(false);
		waterRuneTextPane.setFont(Globals.mainFont);
		waterRuneTextPane.setBounds(Globals.scale(10), Globals.scale(55), Globals.scale(140), Globals.scale(25));
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
				mainPanel.setVisible(false);
				addRunesPanel.setVisible(true);
			}
		});
		changeRunesButton.setBounds(Globals.scale(10), Globals.scale(80), Globals.scale(140), Globals.scale(25));
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
		
		try {  
    		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SlayerTrackerUI.class.getResource("/sounds/alarm.wav"));
    		if(Globals.clip == null) {
        		Globals.clip = AudioSystem.getClip();
        		Globals.clip.open(audioInputStream);
    		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		////////////
		// Farming timer
		final Timer timer = new Timer(500, new ActionListener() {
    		@Override
    		public void actionPerformed(final ActionEvent e) {
    			
    			// If timer End
    			if(System.currentTimeMillis() > Globals.farmTimerStop && Globals.farmTimerStop != 0) {
    				if(!Globals.clip.isRunning()) {
    					Globals.clip.loop(Clip.LOOP_CONTINUOUSLY);
    				}
    				farmRunLabel.setText("00:00:00");
    				
    			}else if(Globals.farmTimerStart > 0 && Globals.farmTimerStop != 0) { // if timer started
    				long millis = Globals.farmTimerStop - System.currentTimeMillis();
    			    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
    			            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
    			            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    			    farmRunLabel.setText(hms);
    			}
    			if(Globals.farmTimerStart == 0 || Globals.farmTimerStop == 0) {
    				farmRunLabel.setText("00:00:00");
    			}
    			
    		}
    		
		});
		timer.start();
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
	

	
	public void openMonsterPanel(String monsterName) {
		int count = (int) monsterCountSpinner.getValue();
		if(count == 0) {
			monsterCountSpinner.setBackground(new Color(255, 0, 0));
		}else {
			monsterCountSpinner.setBackground(Globals.buttonBackground);
			mainPanel.setVisible(false);
			if(monsterPanel== null) {
				monsterPanel = new MonsterPanel(monsterName, null, count, player);
				mainFrame.getContentPane().add(monsterPanel.build(mainPanel));
			}else {
				monsterPanel.reset(monsterName,count, player);
			}
		}
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
