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
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import objects.Globals;
import javax.swing.JScrollPane;

public class UpdatesPanel {
	
	private static JFrame mainFrame;
	/**
	 * @wbp.parser.entryPoint
	 */
	
	public static void build() {
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	mainFrame = new JFrame("Test");
	        	String updateText = "Update 0.8.0\n\n" 
	        			+ "- Added the 4 new fossil island wyvern types\n"
	        			+ "- Working on adding CML tracker\n"
	        			+ "\nUpdate 0.7.10\n\n"
	        			+ "- Moved all data from player object to Globals\n"
	        			+ "- Completely removed the player object\n"
	        			+ "- Redid the code for different spells so I can add more easily\n"
	        			+ "- Log buttons dont show if you dont have a log of that type\n"
	        			+ "- Added the ability to use a trident\n"
	        			+ "- Added trident to the logs\n"
	        			+ "- Fixed an issue where you couldn't enter 0 profit on a task\n"
	        			+ "\nUpdate 0.7.9\n\n"
	        			+ "- Can now change spell to barrage or burst\n"
	        			+ "- Barrage log added\n"
	        			+ "- Simple colour change on settings menu\n"
	        			+ "- Can now view update notes\n"
	        			+ "- Removed 3 columns from cannon&magic log\n";
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(200);
	    		
	        	mainFrame.getContentPane().setBackground(Globals.panelBackground);
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    	
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(UpdatesPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
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
	    		
	    		JLabel title = new JLabel("Recent Updates");
	    		title.setFont(Globals.massiveFont);
	    		title.setForeground(Globals.buttonForground);
	    		title.setBounds(Globals.scale(10),0,width,Globals.scale(25));
	    		title.setHorizontalAlignment(SwingConstants.CENTER);
	    		mainFrame.getContentPane().add(title);
	    		
	    		JTextArea text = new JTextArea();
	    		text.setEditable(false);
	    		text.setText(updateText);
	    		text.setBackground(Globals.panelBackground);
	    		text.setForeground(Globals.buttonForground);
	    		text.setFont(Globals.mainFont);
	    		//text.setEnabled(false);
	    		//text.setBounds(Globals.scale(10),Globals.scale(30),width-Globals.scale(20),height-Globals.scale(40));
	    		//mainFrame.getContentPane().add(text);
	    		
	    		JScrollPane scrollPane = new JScrollPane();
	    		scrollPane.setBounds(Globals.scale(10),Globals.scale(30),width-Globals.scale(20),height-Globals.scale(40));
	    		mainFrame.getContentPane().add(scrollPane);
	    		scrollPane.setViewportView(text);
	    		
	    		mainFrame.setLocationByPlatform(true);
	            mainFrame.setVisible(true);
	            mainFrame.setResizable(false);
				
				
				FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
				mainFrame.addMouseListener(frameDragListener);
				mainFrame.addMouseMotionListener(frameDragListener);
				scrollPane.getVerticalScrollBar().setValue(0);
				
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

	public static void makeVisible() {
		mainFrame.setVisible(true);
	}
	
	public static boolean isInit() {
		if(mainFrame == null) {
			return false;
		}else {
			return true;
		}
	}
}
