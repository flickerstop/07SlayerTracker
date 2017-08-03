package panels;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.Globals;
import ui.SlayerTrackerUI;

public class SettingsPanel {
	private JFrame mainFrame;
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public SettingsPanel() {
		mainFrame = new JFrame("Test");
	}
	public void build() {
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(200);
	    		
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
	    		
	    		
	    		////////////////////////////////////////////
	    		// Scales
	    		JLabel scaleLabel = new JLabel();
	    		JLabel currentScaleLabel = new JLabel();
	    		scaleLabel.setText("Scale: ");
	    		scaleLabel.setFont(Globals.mainFont);
	    		scaleLabel.setForeground(Globals.white);
	    		scaleLabel.setBounds(Globals.scale(15),Globals.scale(50),width/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(scaleLabel);
	    		
	    		JButton scale75Button = new JButton();
	    		scale75Button.setText("75%");
	    		scale75Button.setBounds(Globals.scale(50),Globals.scale(50),Globals.scale(50),Globals.scale(25));
	    		scale75Button.setMargin(new Insets(0, 0, 0, 0));
	    		scale75Button.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				Globals.setScale(0.75f);
        				currentScaleLabel.setText("Current Scale: " + Globals.getScaleString());
	        		}
	    		});
	    		mainFrame.getContentPane().add(scale75Button);
	    		
	    		JButton scale100Button = new JButton();
	    		scale100Button.setText("100%");
	    		scale100Button.setBounds(Globals.scale(100),Globals.scale(50),Globals.scale(50),Globals.scale(25));
	    		scale100Button.setMargin(new Insets(0, 0, 0, 0));
	    		scale100Button.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				Globals.setScale(1.0f);
        				currentScaleLabel.setText("Current Scale: " + Globals.getScaleString());
	        		}
	    		});
	    		mainFrame.getContentPane().add(scale100Button);
	    		
	    		JButton scale150Button = new JButton();
	    		scale150Button.setText("150%");
	    		scale150Button.setBounds(Globals.scale(150),Globals.scale(50),Globals.scale(50),Globals.scale(25));
	    		scale150Button.setMargin(new Insets(0, 0, 0, 0));
	    		scale150Button.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				Globals.setScale(1.5f);
        				currentScaleLabel.setText("Current Scale: " + Globals.getScaleString());
	        		}
	    		});
	    		mainFrame.getContentPane().add(scale150Button);
	    		
	    		JButton scale200Button = new JButton();
	    		scale200Button.setText("200%");
	    		scale200Button.setBounds(Globals.scale(200),Globals.scale(50),Globals.scale(50),Globals.scale(25));
	    		scale200Button.setMargin(new Insets(0, 0, 0, 0));
	    		scale200Button.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				Globals.setScale(2.0f);
        				currentScaleLabel.setText("Current Scale: " + Globals.getScaleString());
	        		}
	    		});
	    		mainFrame.getContentPane().add(scale200Button);
	    		
	    		
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
				normalThemeButton.setBounds(((width/2)-Globals.scale(115)),Globals.scale(80),Globals.scale(100),Globals.scale(25));
				normalThemeButton.setBackground(Globals.white);
				normalThemeButton.setMargin(new Insets(0, 0, 0, 0));
				normalThemeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Globals.setNormalMode();
						normalThemeButton.setEnabled(false);
						darkThemeButton.setEnabled(true);
						currentTheme.setText("Currently Normal Theme");
					}
				});
				mainFrame.getContentPane().add(normalThemeButton);
				
				
				darkThemeButton.setText("Dark Theme");
				darkThemeButton.setBounds(((width/2)+Globals.scale(15)),Globals.scale(80),Globals.scale(100),Globals.scale(25));
				darkThemeButton.setBackground(Globals.grey);
				darkThemeButton.setForeground(Globals.white);
				darkThemeButton.setMargin(new Insets(0, 0, 0, 0));
				darkThemeButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						Globals.setDarkMode();
						normalThemeButton.setEnabled(true);
						darkThemeButton.setEnabled(false);
						currentTheme.setText("Currently Dark Theme");
					}
				});
				mainFrame.getContentPane().add(darkThemeButton);
				
				
				currentTheme.setText("Currently Normal Theme");
				currentTheme.setFont(Globals.mainFont);
				currentTheme.setHorizontalAlignment(SwingConstants.CENTER);
				currentTheme.setForeground(Globals.white);
				currentTheme.setBounds(((width/2)-((width/2)/2)),Globals.scale(100),width/2,Globals.scale(25));
	    		mainFrame.getContentPane().add(currentTheme);
	    		
				if(!Globals.isDarkMode) {
					normalThemeButton.setEnabled(false);
				}else {
					darkThemeButton.setEnabled(false);
				}
	    		/////////////////////////////////////
	    		
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
