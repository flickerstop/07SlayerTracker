import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class SlayerTrackerUI {

	private JFrame frmJrsSlayerTracker;
	int cannonballs = 0;
	private JTextField amountOfCannonballsBought;
	private JTextField pricePaidForCannonballs;
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
		frmJrsSlayerTracker = new JFrame();
		frmJrsSlayerTracker.setTitle("Jr2254's Slayer Tracker\r\n");
		frmJrsSlayerTracker.setBounds(100, 100, 642, 541);
		frmJrsSlayerTracker.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJrsSlayerTracker.getContentPane().setLayout(null);
		
		
		

		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Panels
		
		
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(0, 0, 626, 502);
		frmJrsSlayerTracker.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		
		JPanel addCannonballsPanel = new JPanel();
		addCannonballsPanel.setBounds(0, 0, 626, 502);
		frmJrsSlayerTracker.getContentPane().add(addCannonballsPanel);
		addCannonballsPanel.setLayout(null);
		
		JPanel dustDevilPanel = new JPanel();
		dustDevilPanel.setBackground(Color.ORANGE);
		dustDevilPanel.setBounds(0, 0, 626, 502);
		frmJrsSlayerTracker.getContentPane().add(dustDevilPanel);
		dustDevilPanel.setLayout(null);
		
		
		addCannonballsPanel.setVisible(false);
		dustDevilPanel.setVisible(false);
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dust Devil
		
		
		
		JButton btnDustDevil = new JButton("Dust Devil");
		btnDustDevil.setBackground(UIManager.getColor("Button.background"));
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Dust_devil.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnDustDevil.setIcon(imageIcon);
		}
		btnDustDevil.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPanel.setVisible(false);
				dustDevilPanel.setVisible(true);
			}
		});
		btnDustDevil.setBounds(25, 263, 100, 100);
		mainPanel.add(btnDustDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Smoke Devil
		JButton btnSmokeDevil = new JButton("Smoke Devil");
		btnSmokeDevil.setBounds(135, 263, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Smoke_devil.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnSmokeDevil.setIcon(imageIcon);
		}
		mainPanel.add(btnSmokeDevil);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Abby Spec
		JButton btnAbbySpec = new JButton("Abby Spec");
		btnAbbySpec.setBounds(25, 152, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Aberrant_spectre.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnAbbySpec.setIcon(imageIcon);
		}
		
		mainPanel.add(btnAbbySpec);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Black Dragon
		JButton btnBlackDragon = new JButton("Black Dragon");
		btnBlackDragon.setBounds(135, 152, 100, 100);
		
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Black_dragon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnBlackDragon.setIcon(imageIcon);
		}
		mainPanel.add(btnBlackDragon);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Bloodveld
		JButton btnBloodveld = new JButton("Bloodveld");
		btnBloodveld.setBounds(245, 152, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Bloodveld.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnBloodveld.setIcon(imageIcon);
		}
		mainPanel.add(btnBloodveld);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Blue Dragon
		JButton btnBlueDragon = new JButton("Blue Dragon");
		btnBlueDragon.setBounds(463, 152, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Blue_dragon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnBlueDragon.setIcon(imageIcon);
		}
		mainPanel.add(btnBlueDragon);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Dagannoth
		JButton btnDagannoth = new JButton("Dagannoth");
		btnDagannoth.setBounds(355, 152, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Dagannoth.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnDagannoth.setIcon(imageIcon);
		}
		mainPanel.add(btnDagannoth);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Fire Giant
		JButton btnFireGiant = new JButton("Fire Giant");
		btnFireGiant.setBounds(355, 263, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Fire_giant.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnFireGiant.setIcon(imageIcon);
		}
		mainPanel.add(btnFireGiant);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Gargoyle
		JButton btnGargoyle = new JButton("Gargoyle");
		btnGargoyle.setBounds(245, 263, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/garg.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnGargoyle.setIcon(imageIcon);
		}
		mainPanel.add(btnGargoyle);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Greater Demon
		JButton btnGreaterDemon = new JButton("Greater Demon");
		btnGreaterDemon.setBounds(463, 374, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Greater_demon.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnGreaterDemon.setIcon(imageIcon);
		}
		mainPanel.add(btnGreaterDemon);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Trolls
		JButton btnTrolls = new JButton("Trolls");
		btnTrolls.setBounds(24, 374, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Ice_troll_female.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnTrolls.setIcon(imageIcon);
		}
		mainPanel.add(btnTrolls);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Kalphite
		JButton btnKalphite = new JButton("Kalphite");
		btnKalphite.setBounds(133, 374, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Kalphite_Worker.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnKalphite.setIcon(imageIcon);
		}
		mainPanel.add(btnKalphite);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Lizardman
		JButton btnLizardman = new JButton("Lizardman");
		btnLizardman.setBounds(353, 374, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Lizardman.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnLizardman.setIcon(imageIcon);
		}
		mainPanel.add(btnLizardman);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Nechryael
		JButton btnNechryael = new JButton("Nechryael");
		btnNechryael.setBounds(243, 374, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Nechryael.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnNechryael.setIcon(imageIcon);
		}
		mainPanel.add(btnNechryael);
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Wyvern
		JButton btnWyvern = new JButton("Wyvern");
		btnWyvern.setBounds(465, 263, 100, 100);
		{
			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/Skeletal_Wyvern.png")); // load the image to a imageIcon
			Image image = imageIcon.getImage(); // transform it 
			Image newimg = image.getScaledInstance(100, 100,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
			imageIcon = new ImageIcon(newimg);  // transform it back
			
			btnWyvern.setIcon(imageIcon);
		}
		mainPanel.add(btnWyvern);
		
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		// Cannonballs
		updateCannonballs();
		txtpnCannonballs.setEditable(false);
		txtpnCannonballs.setBounds(473, 11, 143, 20);
		mainPanel.add(txtpnCannonballs);
		
		JButton btnAddCannonballs = new JButton("Add Cannonballs");
		btnAddCannonballs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mainPanel.setVisible(false);
				addCannonballsPanel.setVisible(true);
			}
		});
		btnAddCannonballs.setBounds(474, 31, 142, 23);
		mainPanel.add(btnAddCannonballs);
		
		
		
		amountOfCannonballsBought = new JTextField();
		amountOfCannonballsBought.setBounds(180, 108, 98, 20);
		addCannonballsPanel.add(amountOfCannonballsBought);
		amountOfCannonballsBought.setColumns(10);
		
		pricePaidForCannonballs = new JTextField();
		pricePaidForCannonballs.setBounds(288, 108, 98, 20);
		addCannonballsPanel.add(pricePaidForCannonballs);
		pricePaidForCannonballs.setColumns(10);
		
		JLabel lblAmountBought = new JLabel("Amount Bought");
		lblAmountBought.setBounds(180, 90, 98, 14);
		addCannonballsPanel.add(lblAmountBought);
		
		JLabel lblPricePaid = new JLabel("Price Paid");
		lblPricePaid.setBounds(288, 90, 98, 14);
		addCannonballsPanel.add(lblPricePaid);
		
		JButton addCannonballsButton = new JButton("Add Cannonballs");
		addCannonballsButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cannonballs += Integer.parseInt(amountOfCannonballsBought.getText());
				updateCannonballs();
				mainPanel.setVisible(true);
				addCannonballsPanel.setVisible(false);
			}
		});
		addCannonballsButton.setBounds(180, 139, 206, 43);
		addCannonballsPanel.add(addCannonballsButton);
		
		JButton cancelBuyingCannonBalls = new JButton("Cancel");
		cancelBuyingCannonBalls.setBackground(new Color(255, 0, 0));
		cancelBuyingCannonBalls.setBounds(180, 192, 206, 20);
		addCannonballsPanel.add(cancelBuyingCannonBalls);
	}
	public void updateCannonballs() {
		txtpnCannonballs.setText("Cannonballs: "+cannonballs);
		System.out.println(cannonballs);
	}

}
