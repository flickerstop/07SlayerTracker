package corpTracker;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import objects.GEPrices;
import objects.Globals;
import objects.Item;


public class CorpTrackerMainUI {
	
	private static JFrame mainFrame;
	static JTextArea text = new JTextArea();
	
	static JLabel timerField = new JLabel("00:00:00");
	static JButton startTimer = new JButton();
	static JButton stopTimer = new JButton();
	static int otherDrops = 0;
	static long timerStart;
	static long timerEnd;
	static boolean timerRunning = false;
	static JLabel gph = new JLabel("test");
	static JLabel gphEach = new JLabel("test");
	static JLabel lootTotals = new JLabel("test");
	static JLabel lootTotalsEach = new JLabel("test");
	static double totalLoot = 0;
	
	//public static String savePath = System.getenv("APPDATA")+"\\SlayerTracker\\corpTEST.csv";
	
	static String[] players = {
			"Jr2254",
			"MetalSpike0",
			"Fblthp792",
			"BA Helper"
	};
	
	
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
	        	System.out.print("Built corp panel");
	        	updatePrices();
	        	int width = 370;
	    		int height = 650;
	    		
	    		
	        	mainFrame.getContentPane().setBackground(Globals.black);
	        	mainFrame.setUndecorated(true);
	        	mainFrame.setResizable(false);
	    		mainFrame.setTitle("Basic UI");
	    		mainFrame.setBounds(100, 100, width, height);
	    		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    		mainFrame.getContentPane().setLayout(null);
	    		
	    		
	    		text.setEditable(false);
	    		//text.setBackground(Globals.panelBackground);
	    		//text.setForeground(Globals.buttonForground);
	    		
	    		JButton closeButton = new JButton();
	    		closeButton.setFocusPainted(false);
	    		closeButton.setIcon(new ImageIcon(CorpTrackerMainUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
	    		closeButton.setBackground(new Color(255,0,0));
	    		closeButton.setBounds(width-15, 0, 15, 15);
	    		closeButton.setMargin(new Insets(0, 0, 0, 0));
	    		closeButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			System.exit(0);
	        		}
	    		});
	    		mainFrame.getContentPane().add(closeButton);
	    		
				///////////////////////////////////////////////////////////////////////////////
					   
	    		gph.setBounds(0,5,width/2,20);
	    		gph.setHorizontalAlignment(SwingConstants.CENTER);
	    		gph.setForeground(Globals.iconGrey);
	    		gph.setText("0 gp/h");
	    		mainFrame.getContentPane().add(gph);
	    		
	    		gphEach.setBounds(width/2,5,width/2,20);
	    		gphEach.setHorizontalAlignment(SwingConstants.CENTER);
	    		gphEach.setForeground(Globals.iconGrey);
	    		gphEach.setText("0 gp/h");
	    		mainFrame.getContentPane().add(gphEach);
	    		
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
							
							double calcgph = totalLoot * ((double)3600000/(double)millis);
							
							gph.setText(NumberFormat.getNumberInstance(Locale.US).format(Math.round(calcgph)) + " gp/h");
							gphEach.setText(NumberFormat.getNumberInstance(Locale.US).format(Math.round(calcgph/4)) + " gp/h");
							
						}
					}
				});
				timer.start();
				
				/////////////////////////////////////////////////////////////////////////////////////////
				
				JButton resetButton = new JButton("Save & Reset");
				resetButton.setBounds(0, height-25, width, 25);
				resetButton.setBackground(Globals.blue);
				resetButton.setForeground(Globals.white);
				resetButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						save();
						reset();
					}
				});
				mainFrame.getContentPane().add(resetButton);
				
				/////////////////////////////////////////////////////////////////////////////////////////
	    		
	    		int numOfCol = 6;
	    		int numOfRow = 5;
	    		
	    		int buttonSpaceY = 75;

	    		int spaceBetweenButtons = 10;
	    		
	    		
	    		int buttonWidth = 50;
	    		int buttonHeight = 50;
	    		
	    		int panelY[] = new int[numOfRow];
	    		int panelX[] = new int[numOfCol];
	    		
	    		for(int i = 0; i < numOfRow; i++) {
	    			panelY[i] = buttonSpaceY + spaceBetweenButtons*(i+1) + buttonHeight*(i);
	    		}
	    		for(int i = 0; i < numOfCol; i++) {
	    			panelX[i] = spaceBetweenButtons*(i+1) + buttonWidth*(i);
	    		}
	    		
	    		
	    		int currentPanel = 0;
	    		for(int row = 0; row != numOfRow; row++) {
	    			for(int col = 0; col != numOfCol; col++) {
	    				final int tempMonsterNum = currentPanel;
	    				JButton temp = new JButton();
	    				temp.setBackground(Globals.black);
	    				temp.setBounds(panelX[col], panelY[row], buttonWidth, buttonHeight);
	    				ImageIcon imageIcon = new ImageIcon(CorpTrackerMainUI.class.getResource("/images/bossDrops/"+(String)Globals.corpDrops[currentPanel][2])); // load the image to a imageIcon
	    				Image image = imageIcon.getImage(); // transform it 
	    				Image newimg = image.getScaledInstance(buttonWidth, buttonHeight,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
	    				imageIcon = new ImageIcon(newimg);  // transform it back
	    				temp.setIcon(imageIcon);
    					temp.setToolTipText((String)Globals.corpDrops[currentPanel][0]);
	    				temp.addMouseListener(new MouseAdapter() {
	    					@Override
	    					public void mouseClicked(MouseEvent arg0) {
	    						Globals.corpDrops[tempMonsterNum][3] = (int)Globals.corpDrops[tempMonsterNum][3] + (int)Globals.corpDrops[tempMonsterNum][1];
	    						updateTextField();
	    					}
	    				});
	    				mainFrame.getContentPane().add(temp);
	    				currentPanel++;
	    			}
	    		}
	    		
	    		
	    		text.setText("");
	    		
	    		JButton otherDrop = new JButton("Other Drop");
	    		otherDrop.setBounds(10,380,width-20,20);
	    		otherDrop.setBackground(Globals.black);
	    		otherDrop.setForeground(Globals.white);
	    		otherDrop.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						otherDrops++;
						updateTextField();
					}
				});
	    		mainFrame.getContentPane().add(otherDrop);
	    		
	    		JScrollPane scrollPane = new JScrollPane();
	    		scrollPane.setBounds(10,405,width-20,height-(395+60));
	    		mainFrame.getContentPane().add(scrollPane);
	    		scrollPane.setViewportView(text);
	    		
	    		
	    		
	    		lootTotals.setBounds(0,height-50,width/2,20);
	    		lootTotals.setHorizontalAlignment(SwingConstants.CENTER);
	    		lootTotals.setForeground(Globals.iconGrey);
	    		lootTotals.setText("0 gp");
	    		mainFrame.getContentPane().add(lootTotals);
	    		
	    		lootTotalsEach.setBounds(width/2,height-50,width/2,20);
	    		lootTotalsEach.setHorizontalAlignment(SwingConstants.CENTER);
	    		lootTotalsEach.setForeground(Globals.iconGrey);
	    		lootTotalsEach.setText("0 gp");
	    		mainFrame.getContentPane().add(lootTotalsEach);
	    		
	    		
	    		
	    		mainFrame.setLocationByPlatform(true);
	            mainFrame.setVisible(true);
	            mainFrame.setResizable(false);
				
				
				FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
				mainFrame.addMouseListener(frameDragListener);
				mainFrame.addMouseMotionListener(frameDragListener);
				
				
			}
	    });
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
	
	private static void updateTextField() {
		String output = "";
		totalLoot = 0;
		for(Object[] drop : Globals.corpDrops) {
			if((int)drop[3] > 0) {
				output += drop[3] +" "+ drop[0] + "\n";
				totalLoot += ((int)drop[4] * (int)drop[3]);
			}
		}
		
		if(otherDrops > 0) {
			output += otherDrops + " " + "Other drops\n";
		}
		lootTotals.setText(NumberFormat.getNumberInstance(Locale.US).format(Math.round(totalLoot))+" gp");
		lootTotalsEach.setText(NumberFormat.getNumberInstance(Locale.US).format(Math.round(totalLoot/4))+" gp");
		text.setText(output);
		
		

		System.out.println(output);
	}
	
	private static void startTimer() {
		timerStart = System.currentTimeMillis();
		timerRunning = true;
	}
	private static void endTimer() {
		timerEnd = System.currentTimeMillis();
		timerRunning = false;
	}
	
	private static void reset() {
		timerField.setText("00:00:00");
		text.setText("");
		for(Object[] drop : Globals.corpDrops) {
			drop[3] = 0;
		}
		timerEnd = 0;
		timerStart = 0;
		otherDrops = 0;
		totalLoot = 0;
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
		
		for(Object[] drop : Globals.corpDrops) {
			toSave.add(drop[3]);
		}
		toSave.add(otherDrops);
		
		
		String output = "";
		for(Object item : toSave) {
			output += item;
			output += ",";
		}
		output+=hms;
		
		try {
			PrintWriter out = new PrintWriter(new FileWriter(Globals.corpSavePath, true)); 
			out.println(output);
			out.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void updatePrices() {
		
		for(int i = 0; i < Globals.corpDrops.length; i++) {
			Item currentDrop = GEPrices.getItem(Globals.corpDrops[i][0]+"");
			if(currentDrop == null || currentDrop.getBuyPrice() == 0) {
				continue;
			}
			Globals.corpDrops[i][4] = currentDrop.getBuyPrice();
		}
	}
	
	public static void showPanel() {
		System.out.println(isInit());
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
