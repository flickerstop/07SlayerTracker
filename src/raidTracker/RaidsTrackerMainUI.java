package raidTracker;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import objects.Globals;


public class RaidsTrackerMainUI {
	
	private static JFrame mainFrame;
	static long timerStart;
	static long timerEnd;
	static boolean timerRunning = false;
	
	// TODO
	static JTextField myPoints = new JTextField();
	static JLabel timerField = new JLabel("00:00:00");
	static ArrayList<JComboBox<String>> rooms = new ArrayList<JComboBox<String>>();
	static JButton startTimer = new JButton();
	static JButton stopTimer = new JButton();
	static JLabel dropLabel = new JLabel("Unique drop:");
	static JLabel teamPointsLabel = new JLabel("Team Points:");
	static JTextField teamPoints = new JTextField();
	static JComboBox<String> dropBox = new JComboBox<String>(Globals.raidDrops);
	/**
	 * @wbp.parser.entryPoint
	 */
	
	
	private static void build() {
		EventQueue.invokeLater(new Runnable()
	    {
	        @Override
	        public void run()
	        {
	        	mainFrame = new JFrame();
	        	int width = Globals.scale(400);
	    		int height = Globals.scale(400);
	    		
	        	mainFrame.getContentPane().setBackground(Globals.black);
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    	
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(RaidsTrackerMainUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
	    		closeButton.setBackground(new Color(255,0,0));
	    		closeButton.setBounds(width-Globals.scale(15), 0, Globals.scale(15), Globals.scale(15));
	    		closeButton.setMargin(new Insets(0, 0, 0, 0));
	    		closeButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
        				System.exit(0);
	        		}
	    		});
	    		mainFrame.getContentPane().add(closeButton);
	    		
	    		///////////////////////////////////////////////////////////////////////////////
	    		
	    		
	    		startTimer.setText("Start Timer");
	    		startTimer.setBackground(Globals.green);
	    		startTimer.setBounds(15, 30, (width/2)-15, 25);
	    		startTimer.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			startTimer();
	        			startTimer.setEnabled(false);
	        			stopTimer.setEnabled(true);
	        		}
	    		});
	    		mainFrame.getContentPane().add(startTimer);
	    		
	    		
	    		stopTimer.setText("Stop Timer");
	    		stopTimer.setBackground(Globals.red);
	    		stopTimer.setBounds((width/2), 30, (width/2)-15, 25);
	    		stopTimer.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			endTimer();
	        			startTimer.setEnabled(true);
	        			stopTimer.setEnabled(false);
	        		}
	    		});
	    		mainFrame.getContentPane().add(stopTimer);
	    		startTimer.setEnabled(true);
    			stopTimer.setEnabled(false);
	    		
	    		
	    		timerField.setBounds(15, 55, width-30, 25);
	    		timerField.setForeground(Globals.white);
	    		timerField.setHorizontalAlignment(SwingConstants.CENTER);
	    		
	    		mainFrame.getContentPane().add(timerField);
	    		
	    		
	    		final Timer timer = new Timer(500, new ActionListener() {
	        		@Override
	        		public void actionPerformed(final ActionEvent e) {
	        			
	        			if(timerRunning) {
	        				long millis = System.currentTimeMillis() - timerStart;
	        			    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	        			            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	        			            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	        			    timerField.setText(hms);
	        			}else {
	        				
	        			}
	        			
	        		}
	        		
	    		});
	    		timer.start();
	    		
	    		/////////////////////////////////////////////////////////////////////////////////////////
	    		//
	    		
	    		int y = 80;
	    		for(int i = 0; i < 6; i++) {
	    			JComboBox<String> room = new JComboBox<String>(Globals.raidBosses);
	    			JLabel label = new JLabel("Room #"+(i+1)+":");
	    			
	    			label.setForeground(Globals.white);
	    			label.setBounds(15,y,(width-45)/2,25);
	    			label.setHorizontalAlignment(SwingConstants.RIGHT);
	    			
	    			room.setForeground(Globals.white);
	    			room.setBackground(Globals.black);
	    			room.setBounds(width/2,y,(width-15)/2,25);
	    			room.setSelectedItem(null);
	    			
	    			mainFrame.getContentPane().add(room);
	    			mainFrame.getContentPane().add(label);
	    			rooms.add(room);
	    			y+=26;
	    		}
				/////////////////////////////////////////////////////////////////////////////////////////
				//
	    		y+=26;
	    		
	    		dropLabel.setForeground(Globals.white);
	    		dropLabel.setBounds(15,y,(width-45)/2,25);
	    		dropLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    		
	    		
	    		dropBox.setForeground(Globals.white);
	    		dropBox.setBackground(Globals.black);
	    		dropBox.setBounds(width/2,y,(width-15)/2,25);
	    		dropBox.setSelectedItem(null);
	    		
	    		mainFrame.getContentPane().add(dropLabel);
    			mainFrame.getContentPane().add(dropBox);
    			y+=26;
				/////////////////////////////////////////////////////////////////////////////////////////
				//
    			y+=26;
    			
    			teamPointsLabel.setForeground(Globals.white);
    			teamPointsLabel.setBounds(15,y,(width-45)/2,25);
    			teamPointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    		
    			
    			teamPoints.setForeground(Globals.white);
    			teamPoints.setBackground(Globals.black);
    			teamPoints.setCaretColor(Globals.white);
    			teamPoints.setBounds(width/2,y,(width-15)/2,25);
    			
    			y+=26;
	    		JLabel myPointsLabel = new JLabel("My Points:");
	    		myPointsLabel.setForeground(Globals.white);
	    		myPointsLabel.setBounds(15,y,(width-45)/2,25);
	    		myPointsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
	    		
	    		
	    		myPoints.setForeground(Globals.white);
	    		myPoints.setBackground(Globals.black);
	    		myPoints.setCaretColor(Globals.white);
	    		myPoints.setBounds(width/2,y,(width-15)/2,25);
	    		
	    		mainFrame.getContentPane().add(teamPointsLabel);
	    		mainFrame.getContentPane().add(myPointsLabel);
	    		mainFrame.getContentPane().add(teamPoints);
	    		mainFrame.getContentPane().add(myPoints);
				/////////////////////////////////////////////////////////////////////////////////////////
				//
	    		
	    		JButton finishButton = new JButton("Finish Raid");
	    		finishButton.setBackground(Globals.blue);
	    		finishButton.setForeground(Globals.white);
	    		finishButton.setBounds(0, height-25, width, 25);
	    		finishButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			if(teamPoints.getText().length() == 0) {
	        				teamPoints.setBackground(Globals.red);
	        			}else if(myPoints.getText().length() == 0) {
	        				myPoints.setBackground(Globals.red);
	        			}else {
	        				teamPoints.setBackground(Globals.black);
	        				myPoints.setBackground(Globals.black);
		        			save();
		        			resetAll();
	        			}
	        		}
	    		});
	    		mainFrame.getContentPane().add(finishButton);
				/////////////////////////////////////////////////////////////////////////////////////////
				//
	    		mainFrame.setLocationByPlatform(true);
	            mainFrame.setVisible(true);
	            mainFrame.setResizable(false);
				
				
				FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
				mainFrame.addMouseListener(frameDragListener);
				mainFrame.addMouseMotionListener(frameDragListener);
				
				
			}
	    });
	}
	
	private static void startTimer() {
		timerStart = System.currentTimeMillis();
		timerRunning = true;
	}
	private static void endTimer() {
		timerEnd = System.currentTimeMillis();
		timerRunning = false;
	}
	private static class FrameDragListener extends MouseAdapter {

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
	
	private static void save() {
		if(timerRunning) {
			endTimer();
		}
		long time = timerEnd - timerStart;
		String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
	            TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
	            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
		
		ArrayList<Object> toSave = new ArrayList<Object>();
		
		for(JComboBox<String> room : rooms) {
			if(room.getSelectedItem() == null) {
				toSave.add("None");
			}else {
				toSave.add(room.getSelectedItem());
			}
		}
		
		if(dropBox.getSelectedItem() == null) {
			dropBox.setSelectedItem("None");
		}
		toSave.add(dropBox.getSelectedItem());
		toSave.add(teamPoints.getText());
		toSave.add(myPoints.getText());
		
		String output = "";
		for(Object item : toSave) {
			output += item;
			output += ",";
		}
		output+=hms;
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(Globals.raidsSavePath, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void resetAll() {
		timerEnd = 0;
		timerStart = 0;
		for(JComboBox<String> room : rooms) {
			room.setSelectedItem(null);
		}
		dropBox.setSelectedItem(null);
		teamPoints.setText(null);
		myPoints.setText(null);
	}
	
	

	public static void showPanel() {
		if(!isInit()) {
			build();
		}else {
			makeVisible();
		}
	}
	private static void makeVisible() {
		mainFrame.setVisible(true);
		mainFrame.setState(Frame.NORMAL);
	}

	private static boolean isInit() {
		if(mainFrame == null) {
			return false;
		}else {
			return true;
		}
	}
}
