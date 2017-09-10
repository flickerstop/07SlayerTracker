package panels;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.Globals;
import ui.SlayerTrackerUI;

import javax.swing.JProgressBar;

public class LoadingPopUp {
	
	private static JFrame mainFrame= new JFrame("Test");;
	/**
	 * @wbp.parser.entryPoint
	 */
	static JProgressBar progressBar;
	static float trueValue = 0.0f;
	static JLabel centerText;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build(String text) {
		
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(200);
	    		
	        	mainFrame.getContentPane().setBackground(new Color(0, 0, 0));
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(SlayerTrackerUI.class.getResource("/images/slayerIcon.png")));
	    		centerText = new JLabel(text);
        		centerText.setFont(Globals.mainFont);
        		centerText.setForeground(Globals.white);
        		centerText.setHorizontalAlignment(SwingConstants.CENTER);
        		centerText.setBounds(0, (height/2)-12, width, 25);
        		mainFrame.getContentPane().add(centerText);
        		
        		progressBar = new JProgressBar();
        		progressBar.setBackground(Color.DARK_GRAY);
        		progressBar.setForeground(Color.GREEN);
        		progressBar.setBounds(0, (height/2)+30, width, 15);
        		mainFrame.getContentPane().add(progressBar);
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(LoadingPopUp.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
	    		closeButton.setBackground(new Color(255,0,0));
	    		closeButton.setBounds(width-Globals.scale(15), 0, Globals.scale(15), Globals.scale(15));
	    		closeButton.setMargin(new Insets(0, 0, 0, 0));
	    		closeButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				mainFrame.setVisible(false);
	        		}
	    		});
	    		//mainFrame.getContentPane().add(closeButton);
	    		
	    		
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
	
	public static void hide() {
		mainFrame.setVisible(false);
	}
	
	public static void setProgressBar(int value) {
		if(value > 100) {
			value = 100;
		}
		progressBar.setValue(value);
		trueValue = value;
	}
	public static void addProgressBar(float value) {
		trueValue += value;
		progressBar.setValue(Math.round(trueValue));
	}
	
	public static void rebuild(String text) {
		mainFrame.setVisible(true);
		setProgressBar(0);
		centerText.setText(text);
	}
	
	public static void setText(String text) {
		centerText.setText(text);
	}
}
