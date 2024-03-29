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
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.text.NumberFormatter;

import objects.Globals;
import objects.JrLabel;
import panels.LoadingPopUp;
import panels.FarmRunPanel;
import panels.LogPanel;
import panels.MonsterPanel;
import panels.SettingsPanel;
import panels.UpdatesPanel;
import panels.XPTrackerPanel;

import javax.swing.JTextPane;
import java.awt.Frame;

import javax.swing.JSpinner;
import javax.swing.Timer;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

public class SlayerTrackerUI {
	private static JFrame mainFrame;
	private JFormattedTextField amountOfCannonballsBought;
	private JFormattedTextField pricePaidForCannonballs;
	private JTextPane runes1TextPane;
	private JTextPane runes2TextPane;
	private JTextPane runes3TextPane;
	private JPanel mainPanel;
	private JSpinner monsterCountSpinner;
	private static SlayerTrackerUI window;
	private MonsterPanel monsterPanel;
	
	JTextPane txtpnCannonballs = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		LoadingPopUp.build("LOADING... PLEASE WAIT");

		if(Globals.isSafeEdit) {
			Globals.startTimer();
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
			Globals.outCurrentTime();
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoadingPopUp.setProgressBar(10);
		Globals.load();
		Globals.outCurrentTime();
		LoadingPopUp.setProgressBar(95);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoadingPopUp.setProgressBar(100);
					window = new SlayerTrackerUI();
					Globals.outCurrentTime();
					SystemTrayIcon.initTaskBar();
					mainFrame.setVisible(true);
					LoadingPopUp.hide();
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
		
		int width = Globals.width;
		
		int height = Globals.height;
		
		int panelWidth = width;
		
		String title = "";


		
		
		
		mainFrame = new JFrame();
		mainFrame.setUndecorated(true);
		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/slayerIcon.png")));
		if(Globals.isSafeEdit) {
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
				//mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
    			mainFrame.setVisible(false);
    		}
		});
		topBar.add(closeButton);
		
		
		JButton minimizeButton = new JButton();
		minimizeButton.setFocusPainted(false);

		minimizeButton.setIcon(scaleImage("/images/minimize.png"));
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
		
		JrLabel settingsButton = new JrLabel();
		settingsButton.setIcon(scaleImage("/images/settingsIcon.png"));
		settingsButton.setBounds(panelWidth-Globals.scale(80), 0, Globals.scale(25), Globals.scale(25));
		settingsButton.setToolTipText("Settings");
		settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		settingsButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
				SettingsPanel.showPanel();
    		}
		});
		topBar.add(settingsButton);
		

		JrLabel logsButton = new JrLabel();
		logsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		logsButton.setIcon(scaleImage("/images/logs_icon.png"));
		logsButton.setBounds(panelWidth-Globals.scale(150), 0, Globals.scale(25), Globals.scale(25));
		logsButton.setToolTipText("Settings");
		logsButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			LogPanel.showPanel();
    		}
		});
		topBar.add(logsButton);
		
		JrLabel farmButton = new JrLabel();
		farmButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		farmButton.setIcon(scaleImage("/images/Farming_icon.png"));
		farmButton.setBounds(panelWidth-Globals.scale(185), 0, Globals.scale(25), Globals.scale(25));
		farmButton.setToolTipText("Farm Run");
		farmButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			FarmRunPanel.showPanel();
    		}
		});
		topBar.add(farmButton);
		
		JrLabel updateButton = new JrLabel();
		updateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateButton.setIcon(scaleImage("/images/update_icon.png"));
		updateButton.setBounds(panelWidth-Globals.scale(115), 0, Globals.scale(25), Globals.scale(25));
		updateButton.setToolTipText("Recent Updates");
		updateButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			UpdatesPanel.showPanel();
    		}
		});
		topBar.add(updateButton);
		
		//TODO
		JrLabel xptrackerButton = new JrLabel();
		xptrackerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		xptrackerButton.setIcon(scaleImage("/images/update_icon.png"));
		xptrackerButton.setBounds(panelWidth-Globals.scale(225), 0, Globals.scale(25), Globals.scale(25));
		xptrackerButton.setToolTipText("Farm Run");
		xptrackerButton.addMouseListener(new MouseAdapter() {
    		@Override
    		public void mouseClicked(MouseEvent arg0) {
    			XPTrackerPanel.showPanel();
    		}
		});
		//topBar.add(xptrackerButton);
		
		Globals.outCurrentTime();
		
		JrLabel titleLabel = new JrLabel(title);
		titleLabel.setBounds(Globals.scale(5),0,panelWidth/2,Globals.scale(25));
		titleLabel.setText(title);
		titleLabel.setColor(Globals.titleColor);
		titleLabel.setLeft();
		topBar.add(titleLabel);
		
		JrLabel farmRunLabel = new JrLabel("00:00:00");
		farmRunLabel.setBounds(0,0,panelWidth,Globals.scale(25));
		farmRunLabel.setColor(Globals.iconGrey);
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
	            Globals.save();
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
		
		
		JrLabel monsterCountLabel = new JrLabel("Number of Monsters");
		monsterCountLabel.setBoldFont();
		monsterCountLabel.setBounds((panelWidth/2)-Globals.scale(150/2), Globals.scale(40), Globals.scale(150), Globals.scale(25));
		mainPanel.add(monsterCountLabel);
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonball jpane
		
		JrLabel lblAmountBought = new JrLabel("Amount Bought");
		lblAmountBought.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(100), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(lblAmountBought);
		
		
		amountOfCannonballsBought = new JFormattedTextField(formatter);
		amountOfCannonballsBought.setFont(Globals.mainFont);
		amountOfCannonballsBought.setBounds((panelWidth/2)-Globals.scale(115), Globals.scale(130), Globals.scale(100), Globals.scale(25));
		addCannonballsPanel.add(amountOfCannonballsBought);
		amountOfCannonballsBought.setColumns(10);
		
		JrLabel lblPricePaid = new JrLabel("Price Per Ball");
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
					Globals.updateCannonbals(Integer.parseInt(amountOfCannonballsBought.getText().replaceAll(",", "")), Integer.parseInt(pricePaidForCannonballs.getText().replaceAll(",", "")));
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
		// Rune lables TODO
		// Ice burst
		
		runes1TextPane = new JTextPane();
		runes1TextPane.setFont(Globals.mainFont);
		runes1TextPane.setBounds(Globals.scale(10), Globals.scale(5), Globals.scale(140), Globals.scale(25));
		runes1TextPane.setEditable(false);
		runes1TextPane.setText(Globals.getRuneTypes()[0]);
		mainPanel.add(runes1TextPane);
		
		runes2TextPane = new JTextPane();
		runes2TextPane.setFont(Globals.mainFont);
		runes2TextPane.setEditable(false);
		runes2TextPane.setBounds(Globals.scale(10), Globals.scale(30), Globals.scale(140), Globals.scale(25));
		runes2TextPane.setText(Globals.getRuneTypes()[1]);
		mainPanel.add(runes2TextPane);
		
		runes3TextPane = new JTextPane();
		runes3TextPane.setEditable(false);
		runes3TextPane.setFont(Globals.mainFont);
		runes3TextPane.setBounds(Globals.scale(10), Globals.scale(55), Globals.scale(140), Globals.scale(25));
		runes3TextPane.setText(Globals.getRuneTypes()[2]);
		mainPanel.add(runes3TextPane);

		if(Globals.getRuneTypes()[1] == null) {
			runes2TextPane.setVisible(false);
		}
		if(Globals.getRuneTypes()[2] == null) {
			runes3TextPane.setVisible(false);
		}
		
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
		
		Globals.outCurrentTime();
		ArrayList<JFormattedTextField> inputs = new ArrayList<JFormattedTextField>();

		// Loop for the 3 different rune types
		for (int i = 0; i < 3; i++) {
			JrLabel runesLabel = new JrLabel("Amount of "+Globals.getRuneTypes()[i]);
			runesLabel.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(100+(i*50)), Globals.scale(150), Globals.scale(25));
			addRunesPanel.add(runesLabel);
			
			JFormattedTextField runesTextField = new JFormattedTextField(formatter);
			// Death runes
			
			runesTextField.setText(Globals.runeAmounts[i]+"");
			
			runesTextField.setFont(Globals.mainFont);
			runesTextField.setBounds((panelWidth/2)-Globals.scale(50), Globals.scale(125+(i*50)), Globals.scale(150), Globals.scale(25));
			addRunesPanel.add(runesTextField);
			runesTextField.setColumns(10);
			inputs.add(runesTextField);
			
			if(Globals.getRuneTypes()[i] == null) {
				runesLabel.setVisible(false);
				runesTextField.setVisible(false);
			}
		}
		
		JButton acceptChangeRunesButton = new JButton("Change Runes");
		acceptChangeRunesButton.setForeground(Globals.buttonForground);
		acceptChangeRunesButton.setBackground(Globals.buttonBackground);
		acceptChangeRunesButton.setFont(Globals.mainFont);
		acceptChangeRunesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				for(JFormattedTextField i : inputs) {
					if(i.getText().length() == 0) {
						i.setBackground(Globals.red);
						return;
					}else {
						i.setBackground(Globals.white);
					}
				}
				
				int[] temp = {Integer.parseInt(inputs.get(0).getText().replaceAll(",", "")),
						Integer.parseInt(inputs.get(1).getText().replaceAll(",", "")),
						Integer.parseInt(inputs.get(2).getText().replaceAll(",", ""))
				};
				Globals.updateRuneNumbers(temp);
				updateRunes();
				mainPanel.setVisible(true);
				addRunesPanel.setVisible(false);

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
		Globals.outCurrentTime();
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
    				SystemTrayIcon.setFarmTime("00:00:00");
    				
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
		Globals.outCurrentTime();
	}
	public void updateCannonballs() {
		txtpnCannonballs.setText("Cannonballs: "+Globals.cannonballs);
		//System.out.println(cannonballs);
	}
	
	public void updateRunes() {
		Globals.loadRuneAmounts();
		runes1TextPane.setText(Globals.getRuneTypes()[0]+": "+Globals.runeAmounts[0]);
		runes2TextPane.setText(Globals.getRuneTypes()[1]+": "+Globals.runeAmounts[1]);
		runes3TextPane.setText(Globals.getRuneTypes()[2]+": "+Globals.runeAmounts[2]);
		runes2TextPane.setVisible(true);
		runes3TextPane.setVisible(true);
		if(Globals.getRuneTypes()[1] == null) {
			runes2TextPane.setVisible(false);
		}
		if(Globals.getRuneTypes()[2] == null) {
			runes3TextPane.setVisible(false);
		}
	}
	

	
	public void openMonsterPanel(String monsterName) {
		int count = (int) monsterCountSpinner.getValue();
		if(count == 0) {
			monsterCountSpinner.setBackground(new Color(255, 0, 0));
		}else {
			monsterCountSpinner.setBackground(Globals.buttonBackground);
			mainPanel.setVisible(false);
			if(monsterPanel== null) {
				monsterPanel = new MonsterPanel(monsterName, null, count);
				mainFrame.getContentPane().add(monsterPanel.build(mainPanel));
			}else {
				monsterPanel.reset(monsterName,count);
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
		mainFrame.setVisible(false);
		Globals.reInitData();
		window = new SlayerTrackerUI();
		mainFrame.setVisible(true);
	}
	
	public static void exit() {
		mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	}
	
	public static void openMainPanel() {
		mainFrame.setVisible(true);
	}
	
	private static ImageIcon scaleImage(String path) {
		ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource(path)); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(Globals.scale(25), Globals.scale(25),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageIcon = new ImageIcon(newimg);  // transform it back
		return imageIcon;
	}

}
