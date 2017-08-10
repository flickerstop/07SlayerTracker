package panels;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.text.NumberFormatter;

import objects.Globals;

public class SaveFarmRun {
	
	
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
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(270);
	    		
	        	mainFrame.getContentPane().setBackground(Globals.panelBackground);
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    	
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(SaveFarmRun.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
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
	    		
	    		JLabel info = new JLabel();
	    		JLabel info2 = new JLabel();
    			
	    		info.setFont(Globals.mainFont);
	    		info.setForeground(Globals.buttonForground);
	    		info.setHorizontalAlignment(SwingConstants.CENTER);
	    		info.setText("Hover over anything for more info");
	    		info.setBounds(0,Globals.scale(10),width,Globals.scale(25));
	    		mainFrame.getContentPane().add(info);
	    		
	    		info2.setFont(Globals.mainFont);
	    		info2.setForeground(Globals.buttonForground);
	    		info2.setHorizontalAlignment(SwingConstants.CENTER);
	    		info2.setText("Use TAB to switch input panels fast");
	    		info2.setBounds(0,Globals.scale(30),width,Globals.scale(25));
	    		mainFrame.getContentPane().add(info2);
	    		
	    		ArrayList<JFormattedTextField> inputs = new ArrayList<JFormattedTextField>();
	    		String[] labelText = {
	    				"Herbs:", 
	    				"Dead Patches:", 
	    				"Resurrected Successfully:", 
	    				"Resurrected Failed:",
	    				"Cured Patches:"
	    				};
	    		String[] tooltips = {
	    				"Number of herbs you ended up with after the run",
	    				"Number of patches that have died this run",
	    				"Number of patches that the resurrect spell casted successfully on",
	    				"Number of patches that the resurrect spell failed on",
	    				"Number of patches that you cured because they were diseased"
	    		};
	    		for(int i = 0; i < 5; i++) {
	    			JFormattedTextField textField = new JFormattedTextField(formatter);
	    			JLabel label = new JLabel();
	    			int y = Globals.scale(25*i)+Globals.scale(80);
	    			
	    			label.setFont(Globals.mainFont);
	    			label.setForeground(Globals.buttonForground);
	    			label.setHorizontalAlignment(SwingConstants.RIGHT);
	    			label.setText(labelText[i]);
	    			label.setBounds(Globals.scale(10),y,(width/2)-Globals.scale(20),Globals.scale(25));
	    			label.setToolTipText(tooltips[i]);
	    			
	    			textField.setFont(Globals.mainFont);
	    			textField.setBounds(width/2+Globals.scale(10),y,Globals.scale(50),Globals.scale(25));
	    			textField.setToolTipText(tooltips[i]);
	    			
	    			mainFrame.getContentPane().add(label);
	    			mainFrame.getContentPane().add(textField);
	    			inputs.add(textField);
	    		}
	    		
	    		
	    		
	    		JButton acceptButton = new JButton();
	    		acceptButton.setBackground(Globals.green);
	    		acceptButton.setText("Finish and Save");
	    		acceptButton.setBounds(0, height-Globals.scale(25), width, Globals.scale(25));
	    		acceptButton.setMargin(new Insets(0, 0, 0, 0));
	    		acceptButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			saveRun(inputs);
	        			mainFrame.setVisible(false);
	        		}
	    		});
	    		mainFrame.getContentPane().add(acceptButton);
	    		
	    		
	    		
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
	
	public static void saveRun(ArrayList<JFormattedTextField> inputs) {
		
		/*
		 * Number of herbs
		 * Number of dead patches
		 * Number successfully resurrected
		 * Number failed resurrected
		 * Number of cured
		 * Resurrect cost
		 * Price of herbs
		 * Price of herbs after costs
		 * Time of day & Date
		 */
		int herbs = Integer.parseInt(inputs.get(0).getText());
		int resed = Integer.parseInt(inputs.get(2).getText());
		int dead = Integer.parseInt(inputs.get(3).getText());
		int numCured = Integer.parseInt(inputs.get(4).getText());
		int resCost = (resed+dead)*Globals.resurrectprice;
		int herbLoot = herbs*Globals.herbPrice;
		int profit = herbLoot-resCost - ((Globals.numberOfPatches-resed-numCured)*Globals.seedPrice);
		Object[] toSave = {
				inputs.get(0).getText(),
				inputs.get(1).getText(),
				inputs.get(2).getText(),
				inputs.get(3).getText(),
				inputs.get(4).getText(),
				resCost,
				herbLoot,
				profit,
				LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"))
		};
		String output = "";
		for(int i = 0; i <toSave.length;i++) {
			output += toSave[i];
			if(i+1 < toSave.length) {
				output += ",";
			}
		}
		try {
			PrintWriter out = new PrintWriter(new FileWriter(Globals.herbRunFile, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
