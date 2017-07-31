package ui;
import java.awt.EventQueue;
import java.awt.Image;

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
import java.text.NumberFormat;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.text.NumberFormatter;

import objects.MonsterPanel;
import objects.Player;

import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.SpinnerNumberModel;
import java.awt.Toolkit;

public class SlayerTrackerUI {

	private JFrame frmJrsSlayerTracker;
	int cannonballs = 0;
	private JFormattedTextField amountOfCannonballsBought;
	private JFormattedTextField pricePaidForCannonballs;
	JTextPane deathRuneTextPane;
	JTextPane chaosRuneTextPane;
	JTextPane waterRuneTextPane;
	private JPanel mainPanel;
	private JSpinner monsterCountSpinner;
	Player player = new Player();
	private float scale = 1.0f;
	
	JTextPane txtpnCannonballs = new JTextPane();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SlayerTrackerUI window = new SlayerTrackerUI();
					window.frmJrsSlayerTracker.setVisible(true);
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
		
		int width = scale(640);
		int height = scale(550);
		int panelWidth = width-18;
		int panelHeight = height-40;
		
		int numButtonCol = 5;
		int buttonSpace = scale(10);
		int buttonWidth = ((panelWidth-scale(30))-((numButtonCol-1)*buttonSpace))/numButtonCol;
		int buttonHeight = scale(100);
		
		///
		// Button coords
		int row1Y = (panelHeight-15)-(buttonWidth*1)-(buttonSpace*1);
		int row2Y = (panelHeight-15)-(buttonWidth*2)-(buttonSpace*2);
		int row3Y = (panelHeight-15)-(buttonWidth*3)-(buttonSpace*3);
		int col1X = 15;
		int col2X = 15+(buttonWidth+buttonSpace);
		int col3X = 15+(buttonWidth+buttonSpace)*2;
		int col4X = 15+(buttonWidth+buttonSpace)*3;
		int col5X = 15+(buttonWidth+buttonSpace)*4;
		
		frmJrsSlayerTracker = new JFrame();
		frmJrsSlayerTracker.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/download_icon.png")));
		frmJrsSlayerTracker.setTitle("Jr2254's Slayer Tracker\r\n");
		frmJrsSlayerTracker.setBounds(100, 100, width, height);
		frmJrsSlayerTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJrsSlayerTracker.getContentPane().setLayout(null);
		
		frmJrsSlayerTracker.addWindowListener(new WindowAdapter()
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
		formatter.setAllowsInvalid(false);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Panels

		mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, panelWidth, panelHeight);
		frmJrsSlayerTracker.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		mainPanel.addComponentListener ( new ComponentAdapter ()
	    {
	        public void componentShown ( ComponentEvent e )
	        {
	            updateCannonballs();
	            updateRunes();
	            monsterCountSpinner.setValue(0);
	        }

//	        public void componentHidden ( ComponentEvent e )
//	        {
//	            //System.out.println ( "Component hidden" );
//	        }
	    } );
		
		JPanel addCannonballsPanel = new JPanel();
		addCannonballsPanel.setBounds(0, 0, panelWidth, panelHeight);
		frmJrsSlayerTracker.getContentPane().add(addCannonballsPanel);
		addCannonballsPanel.setLayout(null);

		JPanel addRunesPanel = new JPanel();
		addRunesPanel.setBounds(0, 0, panelWidth, panelHeight);
		frmJrsSlayerTracker.getContentPane().add(addRunesPanel);
		addRunesPanel.setLayout(null);
		
		addCannonballsPanel.setVisible(false);
		addRunesPanel.setVisible(false);
//////////////////////////////////////
// ROW 1
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dust Devil
		
		
		
		JButton btnDustDevil = new JButton("Dust Devil");
		btnDustDevil.setBackground(UIManager.getColor("Button.background"));
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
				openMonsterPanel("Dust Devils");
			}
		});
		btnDustDevil.setBounds(col1X, row1Y, buttonWidth, buttonHeight);
		mainPanel.add(btnDustDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Smoke Devil
		JButton btnSmokeDevil = new JButton("Smoke Devil");
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
				openMonsterPanel("Smoke Devils");
			}
		});
		mainPanel.add(btnSmokeDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Abby Spec
		JButton btnAbbySpec = new JButton("Abby Spec");
		btnAbbySpec.setBounds(col3X, row1Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Aberrant_spectre.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnAbbySpec.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Aberrant Spectres");
				}
			});
			btnAbbySpec.setIcon(imageIcon);
		}
		
		mainPanel.add(btnAbbySpec);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Black Dragon
		JButton btnBlackDragon = new JButton("Black Dragons");
		btnBlackDragon.setBounds(col4X, row1Y, buttonWidth, buttonHeight);
		
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Black_dragon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnBlackDragon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Black Dragons");
				}
			});
			btnBlackDragon.setIcon(imageIcon);
		}
		mainPanel.add(btnBlackDragon);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Bloodveld
		JButton btnBloodveld = new JButton("Bloodveld");
		btnBloodveld.setBounds(col5X, row1Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Bloodveld.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnBloodveld.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Bloodvelds");
				}
			});
			btnBloodveld.setIcon(imageIcon);
		}
		mainPanel.add(btnBloodveld);
		
//////////////////////////////////////
// ROW 2
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Blue Dragon
		JButton btnBlueDragon = new JButton("Blue Dragon");
		btnBlueDragon.setBounds(col1X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Blue_dragon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnBlueDragon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Blue Dragons");
				}
			});
			btnBlueDragon.setIcon(imageIcon);
		}
		mainPanel.add(btnBlueDragon);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dagannoth
		JButton btnDagannoth = new JButton("Dagannoth");
		btnDagannoth.setBounds(col2X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Dagannoth.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnDagannoth.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Dagannoths");
				}
			});
			btnDagannoth.setIcon(imageIcon);
		}
		mainPanel.add(btnDagannoth);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Fire Giant
		JButton btnFireGiant = new JButton("Fire Giant");
		btnFireGiant.setBounds(col3X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Fire_giant.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnFireGiant.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Fire Giants");
				}
			});
			btnFireGiant.setIcon(imageIcon);
		}
		mainPanel.add(btnFireGiant);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Gargoyle
		JButton btnGargoyle = new JButton("Gargoyle");
		btnGargoyle.setBounds(col4X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/garg.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnGargoyle.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Gargoyle");
				}
			});
			btnGargoyle.setIcon(imageIcon);
		}
		mainPanel.add(btnGargoyle);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Greater Demon
		JButton btnGreaterDemon = new JButton("Greater Demon");
		btnGreaterDemon.setBounds(col5X, row2Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Greater_demon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnGreaterDemon.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Greater Demon");
				}
			});
			btnGreaterDemon.setIcon(imageIcon);
		}
		mainPanel.add(btnGreaterDemon);
		
//////////////////////////////////////
// ROW 3
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Trolls
		JButton btnTrolls = new JButton("Trolls");
		btnTrolls.setBounds(col1X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Ice_troll_female.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnTrolls.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Trolls");
				}
			});
			btnTrolls.setIcon(imageIcon);
		}
		mainPanel.add(btnTrolls);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Kalphite
		JButton btnKalphite = new JButton("Kalphite");
		btnKalphite.setBounds(col2X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Kalphite_Worker.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnKalphite.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Kalphites");
				}
			});
			btnKalphite.setIcon(imageIcon);
		}
		mainPanel.add(btnKalphite);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Lizardman
		JButton btnLizardman = new JButton("Lizardman");
		btnLizardman.setBounds(col3X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Lizardman.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnLizardman.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Lizardmen");
				}
			});
			btnLizardman.setIcon(imageIcon);
		}
		mainPanel.add(btnLizardman);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Nechryael
		JButton btnNechryael = new JButton("Nechryael");
		btnNechryael.setBounds(col4X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Nechryael.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnNechryael.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Nechryaels");
				}
			});
			btnNechryael.setIcon(imageIcon);
		}
		mainPanel.add(btnNechryael);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Wyvern
		JButton btnWyvern = new JButton("Wyvern");
		btnWyvern.setBounds(col5X, row3Y, buttonWidth, buttonHeight);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Skeletal_Wyvern.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			btnWyvern.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					openMonsterPanel("Wyverns");
				}
			});
			btnWyvern.setIcon(imageIcon);
		}
		mainPanel.add(btnWyvern);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonballs
		updateCannonballs();
		txtpnCannonballs.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		txtpnCannonballs.setEditable(false);
		txtpnCannonballs.setBounds(panelWidth-scale(150), scale(5), scale(150), scale(25));
		mainPanel.add(txtpnCannonballs);
		
		JButton btnAddCannonballs = new JButton("Add Cannonballs");
		btnAddCannonballs.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		btnAddCannonballs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPanel.setVisible(false);
				addCannonballsPanel.setVisible(true);
			}
		});
		btnAddCannonballs.setBounds(panelWidth-scale(150), scale(30), scale(150), scale(25));
		mainPanel.add(btnAddCannonballs);
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Monster Count
		
		monsterCountSpinner = new JSpinner();
		monsterCountSpinner.setModel(new SpinnerNumberModel(0, 0, 300, 1));
		monsterCountSpinner.setBounds((panelWidth/2)-scale(150/2), scale(65), scale(150), scale(25));
		monsterCountSpinner.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		mainPanel.add(monsterCountSpinner);
		
		
		JLabel monsterCountLabel = new JLabel("Number of Monsters");
		monsterCountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monsterCountLabel.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		monsterCountLabel.setBounds((panelWidth/2)-scale(150/2), scale(40), scale(150), scale(25));
		mainPanel.add(monsterCountLabel);
		
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonball jpane
		
		JLabel lblAmountBought = new JLabel("Amount Bought");
		lblAmountBought.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		lblAmountBought.setBounds((panelWidth/2)-scale(115), scale(100), scale(100), scale(25));
		addCannonballsPanel.add(lblAmountBought);
		
		
		amountOfCannonballsBought = new JFormattedTextField(formatter);
		amountOfCannonballsBought.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		amountOfCannonballsBought.setBounds((panelWidth/2)-scale(115), scale(130), scale(100), scale(25));
		addCannonballsPanel.add(amountOfCannonballsBought);
		amountOfCannonballsBought.setColumns(10);
		
		JLabel lblPricePaid = new JLabel("Price Per Ball");
		lblPricePaid.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		lblPricePaid.setBounds((panelWidth/2)+scale(15), scale(100), scale(100), scale(25));
		addCannonballsPanel.add(lblPricePaid);
		
		pricePaidForCannonballs = new JFormattedTextField(formatter);
		pricePaidForCannonballs.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		pricePaidForCannonballs.setBounds((panelWidth/2)+scale(15), scale(130), scale(100), scale(25));
		addCannonballsPanel.add(pricePaidForCannonballs);
		pricePaidForCannonballs.setColumns(10);
		
		
		
		
		
		JButton addCannonballsButton = new JButton("Add Cannonballs");
		addCannonballsButton.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
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
		addCannonballsButton.setBounds((panelWidth/2)-scale(115), scale(200), scale(230), scale(25));
		addCannonballsPanel.add(addCannonballsButton);
		
		JButton cancelBuyingCannonBalls = new JButton("Cancel");
		cancelBuyingCannonBalls.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		cancelBuyingCannonBalls.setBackground(new Color(255, 0, 0));
		cancelBuyingCannonBalls.setBounds((panelWidth/2)-scale(115), scale(225), scale(230), scale(25));
		addCannonballsPanel.add(cancelBuyingCannonBalls);
		
		
		////////////////////////////////////////////////////
		// Rune lables
		deathRuneTextPane = new JTextPane();
		deathRuneTextPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		deathRuneTextPane.setBounds(scale(15), scale(5), scale(150), scale(25));
		deathRuneTextPane.setEditable(false);
		deathRuneTextPane.setText("Death Runes: ");
		mainPanel.add(deathRuneTextPane);
		
		chaosRuneTextPane = new JTextPane();
		chaosRuneTextPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		chaosRuneTextPane.setEditable(false);
		chaosRuneTextPane.setBounds(scale(15), scale(30), scale(150), scale(25));
		chaosRuneTextPane.setText("Chaos Runes: ");
		mainPanel.add(chaosRuneTextPane);
		
		waterRuneTextPane = new JTextPane();
		waterRuneTextPane.setEditable(false);
		waterRuneTextPane.setFont(new Font("Tahoma", Font.PLAIN, 11));
		waterRuneTextPane.setBounds(scale(15), scale(55), scale(150), scale(25));
		waterRuneTextPane.setText("Water Runes: ");
		mainPanel.add(waterRuneTextPane);
		
		updateRunes();
		
		
		JButton changeRunesButton = new JButton("Change Runes");
		changeRunesButton.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		changeRunesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				addRunesPanel.setVisible(true);
				mainPanel.setVisible(false);
			}
		});
		changeRunesButton.setBounds(scale(15), scale(80), scale(150), scale(25));
		mainPanel.add(changeRunesButton);
		
		//////////////////////////////////////////////////////
		// Change Runes Panel
		
		JLabel addDeathRunesLabel = new JLabel("Amount of Death Runes");
		addDeathRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addDeathRunesLabel.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addDeathRunesLabel.setBounds((panelWidth/2)-scale(50), scale(100), scale(150), scale(25));
		addRunesPanel.add(addDeathRunesLabel);
		
		JFormattedTextField addDeathRunesTextField = new JFormattedTextField(formatter);
		addDeathRunesTextField.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addDeathRunesTextField.setBounds((panelWidth/2)-scale(50), scale(125), scale(150), scale(25));
		addRunesPanel.add(addDeathRunesTextField);
		addDeathRunesTextField.setColumns(10);
		
		JLabel addChaosRunesLabel = new JLabel("Amount of Chaos Runes");
		addChaosRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addChaosRunesLabel.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addChaosRunesLabel.setBounds((panelWidth/2)-scale(50), scale(150), scale(150), scale(25));
		addRunesPanel.add(addChaosRunesLabel);
		
		JFormattedTextField addChaosRunesTextField = new JFormattedTextField(formatter);
		addChaosRunesTextField.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addChaosRunesTextField.setBounds((panelWidth/2)-scale(50), scale(175), scale(150), scale(25));
		addRunesPanel.add(addChaosRunesTextField);
		addChaosRunesTextField.setColumns(10);
		
		JLabel addWaterRunesLabel = new JLabel("Amount of Water Runes");
		addWaterRunesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addWaterRunesLabel.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addWaterRunesLabel.setBounds((panelWidth/2)-scale(50), scale(200), scale(150), scale(25));
		addRunesPanel.add(addWaterRunesLabel);
		
		JFormattedTextField addWaterRunesTextField = new JFormattedTextField(formatter);
		addWaterRunesTextField.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		addWaterRunesTextField.setBounds((panelWidth/2)-scale(50), scale(225), scale(150), scale(25));
		addRunesPanel.add(addWaterRunesTextField);
		addWaterRunesTextField.setColumns(10);
		
		JButton acceptChangeRunesButton = new JButton("Change Runes");
		acceptChangeRunesButton.setFont(new Font("Tahoma", Font.PLAIN, scale(12)));
		acceptChangeRunesButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(addDeathRunesTextField.getText().length() > 0 &&
						addChaosRunesTextField.getText().length() > 0 &&
						addWaterRunesTextField.getText().length() > 0) {
					player.setDeathRunes(Integer.parseInt(addDeathRunesTextField.getText()));
					player.setChaosRunes(Integer.parseInt(addChaosRunesTextField.getText()));
					player.setWaterRunes(Integer.parseInt(addWaterRunesTextField.getText()));
					updateRunes();
					mainPanel.setVisible(true);
					addRunesPanel.setVisible(false);
				}
			}
		});
		acceptChangeRunesButton.setBounds((panelWidth/2)-scale(50), scale(250), scale(150), scale(25));
		addRunesPanel.add(acceptChangeRunesButton);
		//////////////////////////////////////////////////////
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
	
	public int scale(int numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public int scale(float numberToScale) {
		return Math.round(numberToScale*scale);
	}
	
	public void openMonsterPanel(String monsterName) {
		int count = (int) monsterCountSpinner.getValue();
		if(count == 0) {
			monsterCountSpinner.setBackground(new Color(255, 0, 0));
		}else {
			monsterCountSpinner.setBackground(new Color(240, 240, 240));
			mainPanel.setVisible(false);
			MonsterPanel monsterPanel = new MonsterPanel(monsterName, null, count, scale,player);
			frmJrsSlayerTracker.getContentPane().add(monsterPanel.build(mainPanel));
		}
	}
}
