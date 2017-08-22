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

import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import objects.Globals;
import objects.JrLabel;
import objects.Monsters;
import ui.SlayerTrackerUI;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextField;
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
	        	ArrayList<JTextField> accountInputs = new ArrayList<JTextField>();
	        	int width = Globals.scale(450+450+450);
	        	int panelWidth = width/3;
	    		int height = Globals.scale(520);
	    		JSpinner farmPatchCountSpinner = new JSpinner();
	    		JComboBox<String> magicTypeBox = new JComboBox<String>(Globals.magicTypes);
	        	mainFrame.getContentPane().setBackground(Globals.panelBackground);
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
	    		
	    		JrLabel settingsLabel = new JrLabel("Settings");
	    		settingsLabel.setMassiveFont();
	    		settingsLabel.setBounds(0,Globals.scale(5),panelWidth,Globals.scale(30));
	    		mainFrame.getContentPane().add(settingsLabel);
	    		
	    		JrLabel monsterButtonLabel = new JrLabel("Task Buttons");
	    		monsterButtonLabel.setMassiveFont();
	    		monsterButtonLabel.setBounds(panelWidth,Globals.scale(5),panelWidth,Globals.scale(30));
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
	    		JrLabel scaleLabel = new JrLabel("Scale: ");
	    		scaleLabel.setLeft();
	    		JrLabel currentScaleLabel = new JrLabel("Current Scale: " + Globals.getScaleString());
	    		scaleLabel.setBounds(Globals.scale(15),Globals.scale(50),panelWidth/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(scaleLabel);
	    		
	    		JSlider slider = new JSlider();
	    		slider.setBounds(Globals.scale(50),Globals.scale(50),Globals.scale(200),Globals.scale(25));
	    		slider.setMinimum(50);
	    		slider.setMaximum(300);
	    		slider.setMinorTickSpacing(25);
	    		slider.setMajorTickSpacing(50);
	    		slider.setPaintTicks(true);
	    		slider.setBackground(Globals.panelBackground);
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
	    		
	    		currentScaleLabel.setBounds(Globals.scale(260),Globals.scale(50),panelWidth/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(currentScaleLabel);
	    		///////////////////////////////////
	    		// Accept button
	    		// TODO accept button
	    		
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
        				Globals.magicType = (String)magicTypeBox.getSelectedItem();
        				int i = 0;
        				for(JComboBox<String> box : monstersList) {
        					Globals.prefMonsters[i] = Monsters.getMonster((String)box.getSelectedItem());
        					i++;
        				}
        				i=0;
        				for(JTextField textField : accountInputs) {
        					Globals.CMLaccounts[i] = textField.getText();
        					i++;
        				}
        				SlayerTrackerUI.reload();
	        		}
	    		});
	    		mainFrame.getContentPane().add(acceptChangesButton);
	    		
				///////////////////////////////////
				// Theme buttons
	    		
	    		
	    		JrLabel currentTheme = new JrLabel();
				JButton normalThemeButton = new JButton("Normal Theme");
				JButton darkThemeButton = new JButton();
				
				
				normalThemeButton.setText("Normal Theme");
				normalThemeButton.setBounds(((panelWidth/2)-Globals.scale(115)),Globals.scale(90),Globals.scale(100),Globals.scale(25));
				normalThemeButton.setBackground(Globals.normalThemeWhite);
				normalThemeButton.setForeground(Globals.black);
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
				darkThemeButton.setBackground(Globals.darkerBlack);
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
				separator.setBackground(Globals.iconGrey);
				separator.setForeground(Globals.iconGrey);
				mainFrame.getContentPane().add(separator);
				//////////////////////////////////////////////////////////////////////////////
				// Prices
				
				
				
				
				JrLabel farmLabel = new JrLabel(Globals.boldFont);
				farmLabel.setText("Farm Info");
				farmLabel.setBounds(0,Globals.scale(150),panelWidth,Globals.scale(30));
	    		mainFrame.getContentPane().add(farmLabel);
	    		
	    		JrLabel herbTypeLabel = new JrLabel("Planting: ");
	    		herbTypeLabel.setRight();
				herbTypeLabel.setBounds(Globals.scale(10),Globals.scale(180),(panelWidth/2)-Globals.scale(20),Globals.scale(25));
				
				herbTypeComboBox.setFont(Globals.mainFont);
				herbTypeComboBox.setBackground(Globals.normalThemeWhite);
				herbTypeComboBox.setBounds(panelWidth/2+Globals.scale(10),Globals.scale(180),Globals.scale(150),Globals.scale(25));
				herbTypeComboBox.setSelectedItem(Globals.herbType);
				mainFrame.getContentPane().add(herbTypeComboBox);
				mainFrame.getContentPane().add(herbTypeLabel);
				
				
				JrLabel numOfPatchesLabel = new JrLabel("How many patches do you run:");
				numOfPatchesLabel.setRight();
    			
    			numOfPatchesLabel.setBounds(Globals.scale(10),Globals.scale(205),(panelWidth/2)-Globals.scale(20),Globals.scale(25));
    			mainFrame.getContentPane().add(numOfPatchesLabel);
    			
    			
    			farmPatchCountSpinner.setForeground(Globals.buttonForground);
    			farmPatchCountSpinner.setBackground(Globals.normalThemeWhite);
    			farmPatchCountSpinner.setModel(new SpinnerNumberModel(Globals.numberOfPatches, 1, 7, 1));
    			farmPatchCountSpinner.setBounds(panelWidth/2+Globals.scale(10),Globals.scale(205),Globals.scale(150),Globals.scale(25));
    			farmPatchCountSpinner.setFont(Globals.mainFont);
    			mainFrame.getContentPane().add(farmPatchCountSpinner);
    			
    			JrLabel otherInfo = new JrLabel("Other Info");
    			otherInfo.setBoldFont();
    			otherInfo.setBounds(0,Globals.scale(250),panelWidth,Globals.scale(30));
	    		mainFrame.getContentPane().add(otherInfo);
	    		
	    		JrLabel magicTypeLabel = new JrLabel("Type of spell:");
	    		magicTypeLabel.setRight();
	    		magicTypeLabel.setBounds(Globals.scale(10),Globals.scale(280),(panelWidth/2)-Globals.scale(20),Globals.scale(25));
    			
	    		mainFrame.getContentPane().add(magicTypeLabel);
    			
    			// TODO work here
    			
    			magicTypeBox.setBounds(panelWidth/2+Globals.scale(10),Globals.scale(280),Globals.scale(150),Globals.scale(25));
    			magicTypeBox.setFont(Globals.mainFont);
    			magicTypeBox.setBackground(Globals.normalThemeWhite);
    			magicTypeBox.setSelectedItem(Globals.magicType);
    			mainFrame.getContentPane().add(magicTypeBox);
    			
//				//////////////////////////////////////////////////////////////////////////////
//				JSeparator separator2 = new JSeparator();
//				separator2.setBounds(0,Globals.scale(340),panelWidth,Globals.scale(10));
//				separator2.setBackground(Globals.iconGrey);
//				separator2.setForeground(Globals.iconGrey);
//				mainFrame.getContentPane().add(separator2);
//				//////////////////////////////////////////////////////////////////////////////
				
				
	    		
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator3 = new JSeparator();
				separator3.setBounds(0,Globals.scale(350),panelWidth,Globals.scale(10));
				separator3.setBackground(Globals.iconGrey);
				separator3.setForeground(Globals.iconGrey);
				mainFrame.getContentPane().add(separator3);
				//////////////////////////////////////////////////////////////////////////////
				
				JrLabel discordLabel = new JrLabel("Got a bug or idea? Click me to open the Discord and share it!");
				discordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
				discordLabel.setColor(Globals.purple);
				discordLabel.setBounds(0,Globals.scale(400),panelWidth,Globals.scale(30));
				discordLabel.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						if(Desktop.isDesktopSupported())
						{
							try {
								Desktop.getDesktop().browse(new URI("https://discord.gg/MA5BFcb"));
							} catch (Exception e) {
							
								System.err.println(e.getMessage());
							}
						}
					}
				});
	    		mainFrame.getContentPane().add(discordLabel);
    			
				JrLabel openFileLabel = new JrLabel("Click me to open where the files are!");
				openFileLabel.setSmallFont();
				openFileLabel.setColor(Globals.blue);
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
	    		
	    		JrLabel openFileWarningLabel = new JrLabel("Warning! Manually editing files MAY/WILL cause problems!");
	    		openFileWarningLabel.setSmallFont();
	    		openFileWarningLabel.setColor(Globals.blue);
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
	    		
	    		JrLabel thanksLabel = new JrLabel("Special thanks to my Alpha tester Metalspike0!");
	    		thanksLabel.setSmallFont();
	    		thanksLabel.setColor(Globals.grey);
	    		thanksLabel.setBounds(0,height-Globals.scale(50),panelWidth,Globals.scale(10));
	    		mainFrame.getContentPane().add(thanksLabel);
	    		JrLabel thanksLabel2 = new JrLabel();
	    		thanksLabel2.setText("The finder of bugs, and the man of many great ideas");
	    		thanksLabel2.setSmallFont();
	    		thanksLabel2.setColor(Globals.grey);
	    		thanksLabel2.setBounds(0,height-Globals.scale(40),panelWidth,Globals.scale(10));
	    		mainFrame.getContentPane().add(thanksLabel2);
    			
	    		
	    		
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator4 = new JSeparator();
				separator4.setBounds(panelWidth,Globals.scale(30),Globals.scale(10),height-Globals.scale(30));
				separator4.setBackground(Globals.iconGrey);
				separator4.setOrientation(SwingConstants.VERTICAL);
				separator4.setForeground(Globals.iconGrey);
				mainFrame.getContentPane().add(separator4);
				//////////////////////////////////////////////////////////////////////////////
				
				
				
				int firstCol = (int) Math.floor((float)Globals.numberOfMonsters/2.0f);
				int secondCol = (int) Math.ceil((float)Globals.numberOfMonsters/2.0f);
				int monsterCount = 1;
				//System.out.println(firstCol+","+secondCol);
				for(int i = 0; i < firstCol; i++) {
					JrLabel tempLabel = new JrLabel("#"+monsterCount+":");
					tempLabel.setLeft();
					tempLabel.setBounds(panelWidth+Globals.scale(20),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					JComboBox<String> comboBox = new JComboBox<String>(Monsters.getListOfMonsters());
					comboBox.setFont(Globals.mainFont);
					comboBox.setBackground(Globals.normalThemeWhite);
					comboBox.setBounds(panelWidth+Globals.scale(50),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					comboBox.setSelectedItem(Globals.prefMonsters[i][0]);

					monstersList.add(comboBox);
					mainFrame.getContentPane().add(comboBox);
					mainFrame.getContentPane().add(tempLabel);
					monsterCount++;
				}
				
				for(int i = 0; i < secondCol; i++) {
					JrLabel tempLabel = new JrLabel("#"+monsterCount+":");
					tempLabel.setLeft();
					tempLabel.setBounds(panelWidth+Globals.scale(225),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					JComboBox<String> comboBox = new JComboBox<String>(Monsters.getListOfMonsters());
					comboBox.setFont(Globals.mainFont);
					comboBox.setBackground(Globals.normalThemeWhite);
					comboBox.setBounds(panelWidth+Globals.scale(260),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					comboBox.setSelectedItem(Globals.prefMonsters[i+firstCol][0]);
					monstersList.add(comboBox);
					mainFrame.getContentPane().add(comboBox);
					mainFrame.getContentPane().add(tempLabel);
					monsterCount++;
				}
	    		
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator5 = new JSeparator();
				separator5.setBounds(panelWidth*2,Globals.scale(30),Globals.scale(10),height-Globals.scale(30));
				separator5.setBackground(Globals.iconGrey);
				separator5.setOrientation(SwingConstants.VERTICAL);
				separator5.setForeground(Globals.iconGrey);
				mainFrame.getContentPane().add(separator5);
				//////////////////////////////////////////////////////////////////////////////
	    		
				int x = (panelWidth*2)+Globals.scale(25);
				
				for(int i = 0; i <17; i++) {
					JrLabel label = new JrLabel("Account #"+i+":");
					label.setRight();
					label.setBounds(x,Globals.scale((25*i)+35),Globals.scale(95),Globals.scale(25));
					
					String accountName = Globals.CMLaccounts[i];
					if(accountName == null || accountName.equals("null")) {
						accountName = "";
					}
					JTextField input = new JTextField(accountName);
					input.setBackground(Globals.normalThemeWhite);
					input.setFont(Globals.mainFont);
					input.setBounds(x+Globals.scale(100),Globals.scale((25*i)+35),Globals.scale(150),Globals.scale(25));
					
					accountInputs.add(input);
					mainFrame.getContentPane().add(label);
					mainFrame.getContentPane().add(input);
				}
				
				
				
				
				
				
				
				/////////////////////////////////////////////////////////////////////////////////
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
