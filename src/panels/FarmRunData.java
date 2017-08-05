package panels;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import objects.Globals;

public class FarmRunData {
	
	/**
	 * @wbp.parser.entryPoint
	 */
	
	
	public static void build() {
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	JFrame mainFrame = new JFrame("Test");
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(270);
	    		
	        	mainFrame.getContentPane().setBackground(new Color(0, 0, 0));
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    	
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(FarmRunData.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
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
	    		
	    		
	    		/*
	    		 * Number of herbs
	    		 * Number of dead patches
	    		 * Number successfully resurrected
	    		 * Number failed resurrected
	    		 * Resurrect cost
	    		 * Price of herbs
	    		 * Price of herbs after costs
	    		 * Time of day & Date
	    		 */
	    		int numberOfHerbs = 0;
	    		int numberOfDeadPatches = 0;
	    		int numberOfSucRes = 0;
	    		int numberOfFailRes = 0;
	    		int resCost = 0;
	    		int newProfit = 0;
	    		int totalProfit = 0;
	    		int numberOfRuns = 0;
	    		int highestCollected = Integer.MIN_VALUE;
	    		int lowestCollected = Integer.MAX_VALUE;
	    		
	    		
	    		ArrayList<String[]> data = getData();
	    		for(String[] i : data) {
	    			numberOfHerbs += Integer.parseInt(i[0]);
		    		numberOfDeadPatches += Integer.parseInt(i[1]);
		    		numberOfSucRes += Integer.parseInt(i[2]);
		    		numberOfFailRes += Integer.parseInt(i[3]);
		    		resCost += Integer.parseInt(i[4]);
		    		newProfit += Integer.parseInt(i[5]);
		    		totalProfit += Integer.parseInt(i[6]);
	    			
		    		if(Integer.parseInt(i[0]) > highestCollected) {
		    			highestCollected = Integer.parseInt(i[0]);
		    		}
		    		if(Integer.parseInt(i[0]) < lowestCollected) {
		    			lowestCollected = Integer.parseInt(i[0]);
		    		}
		    		
	    			numberOfRuns++;
	    		}
	    		float averageCollected = (float)numberOfHerbs/(float)numberOfRuns;
	    		float deathChance = numberOfDeadPatches/(float)(numberOfRuns*Globals.numberOfPatches);
	    		//System.out.println((float)numberOfDeadPatches+"/("+numberOfRuns+"*"+Globals.numberOfPatches+")");
	    		String[] leftLabels = {
	    			"Number of runs:",
	    			"Number of herbs:",
	    			"Highest Collected:",
	   	    		"Lowest Collected:",
	   	    		"Average Collected",
	   	    		"Number of dead patches:",
	   	    		"Death Chance:",
	   	    		"Number successfully resurrected:",
	   	    		"Number failed resurrected:",
	   	    		"Resurrect cost:",
	   	    		"Net Profit:",
	   	    		"Profit:"
	   	    		
	   	    		
	    		};
	    		
	    		Object[] rightLabels = {
	    				numberOfRuns,
	    				numberOfHerbs,
	    				highestCollected,
		    			lowestCollected,
		    			averageCollected,
			    		numberOfDeadPatches,
			    		(deathChance*100)+"%",
			    		numberOfSucRes,
			    		numberOfFailRes,
			    		resCost,
			    		newProfit,
			    		totalProfit
		    			
		    			
		    		};
	    		for(int i = 0; i<11; i++) {
	    			int y = Globals.scale(15)+Globals.scale(20*i);
	    			JLabel leftLabel = new JLabel();
	    			JLabel rightLabel = new JLabel();
	    			
	    			leftLabel.setText(leftLabels[i]);
	    			rightLabel.setText(rightLabels[i]+"");
	    			leftLabel.setForeground(Globals.buttonForground);
	    			rightLabel.setForeground(Globals.red);
	    			leftLabel.setBounds(0,y,(width/2)-Globals.scale(5),Globals.scale(25));
	    			rightLabel.setBounds(width/2+Globals.scale(5),y,width/2,Globals.scale(25));
	    			leftLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    			rightLabel.setHorizontalAlignment(SwingConstants.LEFT);
	    			leftLabel.setFont(Globals.mainFont);
	    			rightLabel.setFont(Globals.mainFont);
	    			
	    			mainFrame.getContentPane().add(leftLabel);
	    			mainFrame.getContentPane().add(rightLabel);
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
	
	public static ArrayList<String[]> getData(){
		
		
		ArrayList<String[]> log = new ArrayList<String[]>();
		try {
			String line = "";
			BufferedReader br = new BufferedReader(new FileReader(Globals.herbRunFile));  
			line = br.readLine();
		    while (line != null) {
		    	log.add(line.split(","));
		        line = br.readLine();
		    }

		    br.close();
		}catch(FileNotFoundException e) {
			return null;
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return log;
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
