package panels;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.NumberFormatter;

import objects.Globals;
import ui.SlayerTrackerUI;
import javax.swing.JSlider;
import javax.swing.JSpinner;

import java.awt.BorderLayout;
import javax.swing.JToggleButton;
import javax.swing.SpinnerNumberModel;
import javax.swing.JSeparator;

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
	        	
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(600);
	    		JSpinner farmPatchCountSpinner = new JSpinner();
	    		ArrayList<JFormattedTextField> inputs = new ArrayList<JFormattedTextField>();
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
	    		
	    		JLabel mainLabel = new JLabel();
	    		mainLabel.setText("Settings");
	    		mainLabel.setFont(Globals.massiveFont);
	    		mainLabel.setForeground(Globals.white);
	    		mainLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    		mainLabel.setBounds(0,Globals.scale(5),width,Globals.scale(30));
	    		mainFrame.getContentPane().add(mainLabel);
	    		
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
	    		scaleLabel.setBounds(Globals.scale(15),Globals.scale(50),width/2,Globals.scale(25));
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
	    		currentScaleLabel.setBounds(Globals.scale(260),Globals.scale(50),width/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(currentScaleLabel);
	    		///////////////////////////////////
	    		// Accept button
	    		
	    		JButton acceptChangesButton = new JButton();
	    		acceptChangesButton.setText("Accept Changes");
	    		acceptChangesButton.setBounds(0,height-Globals.scale(25),width,Globals.scale(25));
	    		acceptChangesButton.setBackground(Globals.green);
	    		acceptChangesButton.setMargin(new Insets(0, 0, 0, 0));
	    		acceptChangesButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				mainFrame.setVisible(false);
        				/*
        				  	"Death Rune:", 
		    				"Chaos Rune:", 
		    				"Water Rune:", 
		    				"Herb:",
		    				"Seed:"
        				 */
        				Globals.deathPrice = Integer.parseInt(inputs.get(0).getText());
        				Globals.chaosPrice = Integer.parseInt(inputs.get(1).getText());
        				Globals.waterPrice = Integer.parseInt(inputs.get(2).getText());
        				Globals.herbPrice = Integer.parseInt(inputs.get(3).getText());
        				Globals.seedPrice = Integer.parseInt(inputs.get(4).getText());
        				Globals.numberOfPatches = (int)farmPatchCountSpinner.getValue();
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
				normalThemeButton.setBounds(((width/2)-Globals.scale(115)),Globals.scale(90),Globals.scale(100),Globals.scale(25));
				normalThemeButton.setBackground(Globals.white);
				normalThemeButton.setMargin(new Insets(0, 0, 0, 0));
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
				darkThemeButton.setBounds(((width/2)+Globals.scale(15)),Globals.scale(90),Globals.scale(100),Globals.scale(25));
				darkThemeButton.setBackground(Globals.grey);
				darkThemeButton.setForeground(Globals.white);
				darkThemeButton.setMargin(new Insets(0, 0, 0, 0));
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
				currentTheme.setBounds(((width/2)-((width/2)/2)),Globals.scale(110),width/2,Globals.scale(25));
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
				separator.setBounds(0,Globals.scale(140),width,Globals.scale(10));
				separator.setBackground(Globals.yellow);
				separator.setForeground(Globals.yellow);
				mainFrame.getContentPane().add(separator);
				//////////////////////////////////////////////////////////////////////////////
				// Prices
				
				JLabel pricesLabel = new JLabel();
				pricesLabel.setText("Prices");
				pricesLabel.setFont(Globals.boldFont);
				pricesLabel.setForeground(Globals.white);
				pricesLabel.setHorizontalAlignment(SwingConstants.CENTER);
				pricesLabel.setBounds(0,Globals.scale(150),width,Globals.scale(30));
	    		mainFrame.getContentPane().add(pricesLabel);
				
				
	    		String[] labelText = {
	    				"Death Rune:", 
	    				"Chaos Rune:", 
	    				"Water Rune:", 
	    				"Herb:",
	    				"Seed:"
	    				};
	    		String[] tooltips = {
	    				"The current price of Death Runes",
	    				"The current price of Chaos Runes",
	    				"The current price of Water Runes",
	    				"The current price of Herb you are planting",
	    				"The current price of Seeds of the herb you are planting"
	    		};
	    		String[] text = {
	    				Globals.deathPrice+"",
	    				Globals.chaosPrice+"",
	    				Globals.waterPrice+"",
	    				Globals.herbPrice+"",
	    				Globals.seedPrice+"",
	    		};
	    		for(int i = 0; i < 5; i++) {
	    			JFormattedTextField textField = new JFormattedTextField(formatter);
	    			JLabel label = new JLabel();
	    			int y = Globals.scale(25*i)+Globals.scale(190);
	    			
	    			label.setFont(Globals.mainFont);
	    			label.setForeground(Globals.buttonForground);
	    			label.setHorizontalAlignment(SwingConstants.RIGHT);
	    			label.setText(labelText[i]);
	    			label.setBounds(Globals.scale(10),y,(width/2)-Globals.scale(20),Globals.scale(25));
	    			label.setToolTipText(tooltips[i]);
	    			
	    			textField.setFont(Globals.mainFont);
	    			textField.setBounds(width/2+Globals.scale(10),y,Globals.scale(80),Globals.scale(25));
	    			textField.setToolTipText(tooltips[i]);
	    			textField.setText(text[i]);
	    			
	    			mainFrame.getContentPane().add(label);
	    			mainFrame.getContentPane().add(textField);
	    			inputs.add(textField);
	    		}
				//////////////////////////////////////////////////////////////////////////////
				JSeparator separator2 = new JSeparator();
				separator2.setBounds(0,Globals.scale(340),width,Globals.scale(10));
				separator2.setBackground(Globals.yellow);
				separator2.setForeground(Globals.yellow);
				mainFrame.getContentPane().add(separator2);
				//////////////////////////////////////////////////////////////////////////////
				
				JLabel numOfPatchesLabel = new JLabel();
    			
    			numOfPatchesLabel.setFont(Globals.mainFont);
    			numOfPatchesLabel.setForeground(Globals.buttonForground);
    			numOfPatchesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    			numOfPatchesLabel.setText("How many patches do you run:");
    			numOfPatchesLabel.setBounds(Globals.scale(10),Globals.scale(370),(width/2)-Globals.scale(20),Globals.scale(25));
    			mainFrame.getContentPane().add(numOfPatchesLabel);
    			
    			
    			farmPatchCountSpinner.setForeground(Globals.buttonForground);
    			farmPatchCountSpinner.setBackground(Globals.buttonBackground);
    			farmPatchCountSpinner.setModel(new SpinnerNumberModel(Globals.numberOfPatches, 1, 7, 1));
    			farmPatchCountSpinner.setBounds(width/2+Globals.scale(10),Globals.scale(370),Globals.scale(80),Globals.scale(25));
    			farmPatchCountSpinner.setFont(Globals.mainFont);
    			mainFrame.getContentPane().add(farmPatchCountSpinner);
	    		
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
