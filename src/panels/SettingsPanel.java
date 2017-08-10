package panels;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import objects.Globals;
import objects.Monsters;
import ui.SlayerTrackerUI;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

public class SettingsPanel {
	private JFrame mainFrame;
	
	
	public SettingsPanel() {
		mainFrame = new JFrame("Test");
	}
	public void build() {
		/**
		 * @wbp.parser.entryPoint
		 */
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	JComboBox<String> herbTypeComboBox = new JComboBox<String>(Globals.herbTypes);
	        	ArrayList<JComboBox<String>> monstersList = new ArrayList<JComboBox<String>>();
	        	int width = Globals.scale(900);
	        	int panelWidth = width/2;
	    		int height = Globals.scale(520);
	    		JSpinner farmPatchCountSpinner = new JSpinner();
	        	mainFrame.getContentPane().setBackground(Globals.black);
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    	
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(SettingsPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
	    		closeButton.setBackground(new Color(255,0,0));
	    		closeButton.setBounds(width-Globals.scale(15), 0, Globals.scale(15), Globals.scale(15));
	    		closeButton.setMargin(new Insets(0, 0, 0, 0));
	    		closeButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				mainFrame.setVisible(false);
	        		}
	    		});
	    		mainFrame.getContentPane().add(closeButton);
	    		
	    		JLabel settingsLabel = new JLabel();
	    		settingsLabel.setText("Settings");
	    		settingsLabel.setFont(Globals.massiveFont);
	    		settingsLabel.setForeground(Globals.white);
	    		settingsLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    		settingsLabel.setBounds(0,Globals.scale(5),width/2,Globals.scale(30));
	    		mainFrame.getContentPane().add(settingsLabel);
	    		
	    		JLabel monsterButtonLabel = new JLabel();
	    		monsterButtonLabel.setText("Monster Buttons");
	    		monsterButtonLabel.setFont(Globals.massiveFont);
	    		monsterButtonLabel.setForeground(Globals.white);
	    		monsterButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    		monsterButtonLabel.setBounds(width/2,Globals.scale(5),width/2,Globals.scale(30));
	    		mainFrame.getContentPane().add(monsterButtonLabel);
	    		
				///////////////////////////////////////////
				NumberFormat format = NumberFormat.getInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0);
				formatter.setMaximum(Integer.MAX_VALUE);
				formatter.setAllowsInvalid(true);
				// If you want the value to be committed on each keystroke instead of focus lost
				formatter.setCommitsOnValidEdit(true);
				///////////////////////////////////////////
	    		////////////////////////////////////////////
	    		// Scales
	    		JLabel scaleLabel = new JLabel();
	    		JLabel currentScaleLabel = new JLabel();
	    		scaleLabel.setText("Scale: ");
	    		scaleLabel.setFont(Globals.mainFont);
	    		scaleLabel.setForeground(Globals.white);
	    		scaleLabel.setBounds(Globals.scale(15),Globals.scale(50),panelWidth/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(scaleLabel);
	    		
	    		JSlider slider = new JSlider();
	    		slider.setBounds(Globals.scale(50),Globals.scale(50),Globals.scale(200),Globals.scale(25));
	    		slider.setMinimum(50);
	    		slider.setMaximum(300);
	    		slider.setMinorTickSpacing(25);
	    		slider.setMajorTickSpacing(50);
	    		slider.setPaintTicks(true);
	    		slider.setBackground(Globals.black);
	    		slider.setValue((int)(Globals.scale*100));
	    		slider.addChangeListener(new ChangeListener() {
	                @Override
	                public void stateChanged(ChangeEvent e) {
	                	slider.setValue((int)(Math.round(slider.getValue()/25.0) * 25));
	                	currentScaleLabel.setText("Current Scale: " +slider.getValue()+"%");
	                	Globals.setScale(slider.getValue()/100.0f);
	                }
	            });
	    		mainFrame.getContentPane().add(slider);
	    		
	    		currentScaleLabel.setText("Current Scale: " + Globals.getScaleString());
	    		currentScaleLabel.setFont(Globals.mainFont);
	    		currentScaleLabel.setForeground(Globals.white);
	    		currentScaleLabel.setBounds(Globals.scale(260),Globals.scale(50),panelWidth/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(currentScaleLabel);
	    		///////////////////////////////////
	    		// Accept button
	    		
	    		JButton acceptChangesButton = new JButton();
	    		acceptChangesButton.setText("Accept Changes");
	    		acceptChangesButton.setBounds(0,height-Globals.scale(25),width,Globals.scale(25));
	    		acceptChangesButton.setBackground(Globals.green);
	    		acceptChangesButton.setMargin(new Insets(0, 0, 0, 0));
	    		acceptChangesButton.setFont(Globals.mainFont);
	    		acceptChangesButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				mainFrame.setVisible(false);
        				Globals.herbType = (String)herbTypeComboBox.getSelectedItem();
        				
        				int i = 0;
        				for(JComboBox<String> box : monstersList) {
        					Globals.prefMonsters[i] = Monsters.getMonster((String)box.getSelectedItem());
        					//System.out.println(Globals.prefMonsters[i][0]);
        					i++;
        				}
        				SlayerTrackerUI.reload();
	        		}
	    		});
	    		mainFrame.getContentPane().add(acceptChangesButton);
	    		
				///////////////////////////////////
				// Theme buttons
	    		
	    		
	    		JLabel currentTheme = new JLabel();
				JButton normalThemeButton = new JButton();
				JButton darkThemeButton = new JButton();
				
				
				normalThemeButton.setText("Normal Theme");
				normalThemeButton.setBounds(((panelWidth/2)-Globals.scale(115)),Globals.scale(90),Globals.scale(100),Globals.scale(25));
				normalThemeButton.setBackground(Globals.white);
				normalThemeButton.setMargin(new Insets(0, 0, 0, 0));
				normalThemeButton.setFont(Globals.mainFont);
				normalThemeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(normalThemeButton.isEnabled()) {
							Globals.setNormalMode();
							normalThemeButton.setEnabled(false);
							darkThemeButton.setEnabled(true);
							currentTheme.setText("Currently Normal Theme");
						}
					}
				});
				mainFrame.getContentPane().add(normalThemeButton);
				
				
				darkThemeButton.setText("Dark Theme");
				darkThemeButton.setBounds(((panelWidth/2)+Globals.scale(15)),Globals.scale(90),Globals.scale(100),Globals.scale(25));
				darkThemeButton.setBackground(Globals.grey);
				darkThemeButton.setForeground(Globals.white);
				darkThemeButton.setMargin(new Insets(0, 0, 0, 0));
				darkThemeButton.setFont(Globals.mainFont);
				darkThemeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(darkThemeButton.isEnabled()) {
							Globals.setDarkMode();
							normalThemeButton.setEnabled(true);
							darkThemeButton.setEnabled(false);
							currentTheme.setText("Currently Dark Theme");
						}
					}
				});
				mainFrame.getContentPane().add(darkThemeButton);
				
				
				
				currentTheme.setFont(Globals.mainFont);
				currentTheme.setHorizontalAlignment(SwingConstants.CENTER);
				currentTheme.setForeground(Globals.white);
				currentTheme.setBounds(((panelWidth/2)-((panelWidth/2)/2)),Globals.scale(110),panelWidth/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(currentTheme);
	    		
	    		
				if(Globals.isDarkMode) {
					darkThemeButton.setEnabled(false);
					normalThemeButton.setEnabled(true);
					currentTheme.setText("Currently Dark Theme");
				}else {
					normalThemeButton.setEnabled(false);
					darkThemeButton.setEnabled(true);
					currentTheme.setText("Currently Normal Theme");
				}
				
				
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator = new JSeparator();
				separator.setBounds(0,Globals.scale(140),panelWidth,Globals.scale(10));
				separator.setBackground(Globals.yellow);
				separator.setForeground(Globals.yellow);
				mainFrame.getContentPane().add(separator);
				//////////////////////////////////////////////////////////////////////////////
				// Prices
				
				
				
				
				JLabel farmLabel = new JLabel();
				farmLabel.setText("Farm Info");
				farmLabel.setFont(Globals.boldFont);
				farmLabel.setForeground(Globals.white);
				farmLabel.setHorizontalAlignment(SwingConstants.CENTER);
				farmLabel.setBounds(0,Globals.scale(150),panelWidth,Globals.scale(30));
	    		mainFrame.getContentPane().add(farmLabel);
	    		
	    		JLabel herbTypeLabel = new JLabel("Planting: ");
				herbTypeLabel.setBounds(Globals.scale(10),Globals.scale(230),(panelWidth/2)-Globals.scale(20),Globals.scale(25));
				herbTypeLabel.setFont(Globals.mainFont);
				herbTypeLabel.setForeground(Globals.white);
				herbTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				
				herbTypeComboBox.setFont(Globals.mainFont);
				herbTypeComboBox.setBounds(panelWidth/2+Globals.scale(10),Globals.scale(230),Globals.scale(150),Globals.scale(25));
				herbTypeComboBox.setSelectedItem(Globals.herbType);
				mainFrame.getContentPane().add(herbTypeComboBox);
				mainFrame.getContentPane().add(herbTypeLabel);
				
				
				JLabel numOfPatchesLabel = new JLabel();
    			
    			numOfPatchesLabel.setFont(Globals.mainFont);
    			numOfPatchesLabel.setForeground(Globals.white);
    			numOfPatchesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    			numOfPatchesLabel.setText("How many patches do you run:");
    			numOfPatchesLabel.setBounds(Globals.scale(10),Globals.scale(260),(panelWidth/2)-Globals.scale(20),Globals.scale(25));
    			mainFrame.getContentPane().add(numOfPatchesLabel);
    			
    			
    			farmPatchCountSpinner.setForeground(Globals.white);
    			farmPatchCountSpinner.setBackground(Globals.buttonBackground);
    			farmPatchCountSpinner.setModel(new SpinnerNumberModel(Globals.numberOfPatches, 1, 7, 1));
    			farmPatchCountSpinner.setBounds(panelWidth/2+Globals.scale(10),Globals.scale(260),Globals.scale(150),Globals.scale(25));
    			farmPatchCountSpinner.setFont(Globals.mainFont);
    			mainFrame.getContentPane().add(farmPatchCountSpinner);
    			
//				//////////////////////////////////////////////////////////////////////////////
//				JSeparator separator2 = new JSeparator();
//				separator2.setBounds(0,Globals.scale(340),panelWidth,Globals.scale(10));
//				separator2.setBackground(Globals.yellow);
//				separator2.setForeground(Globals.yellow);
//				mainFrame.getContentPane().add(separator2);
//				//////////////////////////////////////////////////////////////////////////////
				
				
	    		
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator3 = new JSeparator();
				separator3.setBounds(0,Globals.scale(350),panelWidth,Globals.scale(10));
				separator3.setBackground(Globals.yellow);
				separator3.setForeground(Globals.yellow);
				mainFrame.getContentPane().add(separator3);
				//////////////////////////////////////////////////////////////////////////////
				
				JLabel discordLabel = new JLabel();
				discordLabel.setText("Got a bug or idea? Click me to open the Discord and share it!");
				discordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				discordLabel.setFont(Globals.mainFont);
				discordLabel.setForeground(Globals.purple);
				discordLabel.setHorizontalAlignment(SwingConstants.CENTER);
				discordLabel.setBounds(0,Globals.scale(400),panelWidth,Globals.scale(30));
				discordLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(Desktop.isDesktopSupported())
						{
							try {
								Desktop.getDesktop().browse(new URI("https://discord.gg/MA5BFcb"));
							} catch (Exception e) {
								// TODO Auto-generated catch block
								System.err.println(e.getMessage());
							}
						}
					}
				});
	    		mainFrame.getContentPane().add(discordLabel);
    			
				JLabel openFileLabel = new JLabel();
				openFileLabel.setText("Click me to open where the files are!");
				openFileLabel.setFont(Globals.smallFont);
				openFileLabel.setForeground(Globals.blue);
				openFileLabel.setHorizontalAlignment(SwingConstants.CENTER);
				openFileLabel.setBounds(0,Globals.scale(430),panelWidth,Globals.scale(30));
				openFileLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							Runtime.getRuntime().exec("explorer.exe /select," + Globals.path);
						} catch (IOException e) {
							System.err.println(e);
						}
					}
				});
	    		mainFrame.getContentPane().add(openFileLabel);
	    		
	    		JLabel openFileWarningLabel = new JLabel();
	    		openFileWarningLabel.setText("Warning! Manually editing files MAY/WILL cause problems!");
	    		openFileWarningLabel.setFont(Globals.smallFont);
	    		openFileWarningLabel.setForeground(Globals.blue);
	    		openFileWarningLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    		openFileWarningLabel.setBounds(0,Globals.scale(440),panelWidth,Globals.scale(30));
	    		openFileWarningLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						try {
							Runtime.getRuntime().exec("explorer.exe /select," + Globals.path);
						} catch (IOException e) {
							System.err.println(e);
						}
					}
				});
	    		mainFrame.getContentPane().add(openFileWarningLabel);
	    		
	    		JLabel thanksLabel = new JLabel();
	    		thanksLabel.setText("Special thanks to my Alpha tester Metalspike0!");
	    		thanksLabel.setFont(Globals.smallFont);
	    		thanksLabel.setForeground(Globals.grey);
	    		thanksLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    		thanksLabel.setBounds(0,height-Globals.scale(50),panelWidth,Globals.scale(10));
	    		mainFrame.getContentPane().add(thanksLabel);
	    		JLabel thanksLabel2 = new JLabel();
	    		thanksLabel2.setText("The finder of bugs, and the man of many great ideas");
	    		thanksLabel2.setFont(Globals.smallFont);
	    		thanksLabel2.setForeground(Globals.grey);
	    		thanksLabel2.setHorizontalAlignment(SwingConstants.CENTER);
	    		thanksLabel2.setBounds(0,height-Globals.scale(40),panelWidth,Globals.scale(10));
	    		mainFrame.getContentPane().add(thanksLabel2);
    			
	    		
	    		
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator4 = new JSeparator();
				separator4.setBounds(panelWidth,Globals.scale(30),Globals.scale(10),height-Globals.scale(30));
				separator4.setBackground(Globals.yellow);
				separator4.setOrientation(SwingConstants.VERTICAL);
				separator4.setForeground(Globals.yellow);
				mainFrame.getContentPane().add(separator4);
				//////////////////////////////////////////////////////////////////////////////
				
				
				
				int firstCol = (int) Math.floor((float)Globals.numberOfMonsters/2.0f);
				int secondCol = (int) Math.ceil((float)Globals.numberOfMonsters/2.0f);
				int monsterCount = 1;
				//System.out.println(firstCol+","+secondCol);
				for(int i = 0; i < firstCol; i++) {
					JLabel tempLabel = new JLabel("#"+monsterCount+":");
					tempLabel.setBounds(panelWidth+Globals.scale(20),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					tempLabel.setFont(Globals.mainFont);
					tempLabel.setForeground(Globals.white);
					JComboBox<String> comboBox = new JComboBox<String>(Monsters.getListOfMonsters());
					comboBox.setFont(Globals.mainFont);
					comboBox.setBounds(panelWidth+Globals.scale(50),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					comboBox.setSelectedItem(Globals.prefMonsters[i][0]);

					monstersList.add(comboBox);
					mainFrame.getContentPane().add(comboBox);
					mainFrame.getContentPane().add(tempLabel);
					monsterCount++;
				}
				
				for(int i = 0; i < secondCol; i++) {
					JLabel tempLabel = new JLabel("#"+monsterCount+":");
					tempLabel.setBounds(panelWidth+Globals.scale(225),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					tempLabel.setFont(Globals.mainFont);
					tempLabel.setForeground(Globals.white);
					JComboBox<String> comboBox = new JComboBox<String>(Monsters.getListOfMonsters());
					comboBox.setFont(Globals.mainFont);
					comboBox.setBounds(panelWidth+Globals.scale(260),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					comboBox.setSelectedItem(Globals.prefMonsters[i+firstCol][0]);
					monstersList.add(comboBox);
					mainFrame.getContentPane().add(comboBox);
					mainFrame.getContentPane().add(tempLabel);
					monsterCount++;
				}
	    		
	    		
	    		mainFrame.setLocationByPlatform(true);
	            mainFrame.setVisible(true);
	            mainFrame.setResizable(false);
				FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
				mainFrame.addMouseListener(frameDragListener);
				mainFrame.addMouseMotionListener(frameDragListener);
				
				
			}
	    });
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
}
