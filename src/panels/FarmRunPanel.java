package panels;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import objects.Globals;
import objects.Player;
import ui.SlayerTrackerUI;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FarmRunPanel {
	
	private static JTextField timerTextField;
	
	private static JLabel endTimeLabel;
	private static JFrame mainFrame;
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build(boolean isStandAlone) {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	int rowHeight = Globals.scale(25);
            	
            	int width = Globals.scale(400);
        		int height = Globals.scale(200);
        		int panelWidth = width;
        		int panelHeight = height;
        		
        		mainFrame = new JFrame("Test");
            	mainFrame.setUndecorated(true);
            	mainFrame.getContentPane().setBackground(Globals.panelBackground);
            	mainFrame.setResizable(false);
            	mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(FarmRunPanel.class.getResource("/images/Farming-icon.png")));
        		mainFrame.setTitle("Farm Run");
        		mainFrame.setBounds(100, 100, width, height);
        		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		mainFrame.getContentPane().setLayout(null);
        		
        		
        		endTimeLabel = new JLabel("Start the timer!");
        		endTimeLabel.setFont(Globals.mainFont);
        		endTimeLabel.setForeground(Globals.buttonForground);
        		endTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        		endTimeLabel.setBounds(0, panelHeight/3, panelWidth, rowHeight);
        		mainFrame.getContentPane().add(endTimeLabel);
        		
        		
        		JButton logsButton = new JButton();
        		logsButton.setText("?");
        		logsButton.setFocusPainted(false);
        		logsButton.setBackground(Globals.purple);
        		logsButton.setFont(Globals.smallFont);
        		logsButton.setBounds(panelWidth-Globals.scale(45), 0, Globals.scale(15), Globals.scale(15));
        		logsButton.setMargin(new Insets(0, 0, 0, 0));
        		logsButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			FarmRunData.build();
	        		}
        		});
        		mainFrame.getContentPane().add(logsButton);
        		
        		JButton closeButton = new JButton();
        		closeButton.setFocusPainted(false);
        		closeButton.setIcon(new ImageIcon(FarmRunPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
        		closeButton.setBackground(new Color(255,0,0));
        		closeButton.setFont(Globals.smallFont);
        		closeButton.setBounds(panelWidth-Globals.scale(15), 0, Globals.scale(15), Globals.scale(15));
        		closeButton.setMargin(new Insets(0, 0, 0, 0));
        		closeButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			if(isStandAlone) {
	        				mainFrame.dispatchEvent(new WindowEvent(mainFrame, WindowEvent.WINDOW_CLOSING));
	        			}else {
	        				mainFrame.setVisible(false);
	        			}
	        		}
        		});
        		mainFrame.getContentPane().add(closeButton);
        		JButton minimizeButton = new JButton();
        		minimizeButton.setFocusPainted(false);
        		{
        			ImageIcon imageIcon = new ImageIcon(SlayerTrackerUI.class.getResource("/images/minimize.png")); // load the image to a imageIcon
        			Image image = imageIcon.getImage(); // transform it 
        			Image newimg = image.getScaledInstance(Globals.scale(15), Globals.scale(15),  java.awt.Image.SCALE_DEFAULT); // scale it the smooth way  
        			imageIcon = new ImageIcon(newimg);  // transform it back
        			
        			minimizeButton.setIcon(imageIcon);
        		}
        		minimizeButton.setBackground(new Color(52, 152, 219));
        		minimizeButton.setBounds(panelWidth-Globals.scale(30), 0, Globals.scale(15), Globals.scale(15));
        		minimizeButton.setMargin(new Insets(0, 0, 0, 0));
        		minimizeButton.setToolTipText("Minimize Window");
        		minimizeButton.addMouseListener(new MouseAdapter() {
            		@Override
            		public void mouseClicked(MouseEvent arg0) {
        				mainFrame.setState(Frame.ICONIFIED);
            		}
        		});
        		mainFrame.add(minimizeButton);
        		//////////////
        		// Timer
        		JButton startTimerButton = new JButton("Start Countdown");
        		startTimerButton.setBackground(Globals.blue);
        		JButton stopTimerButton = new JButton("Stop Countdown");
        		stopTimerButton.setBackground(Globals.blue);
        		JButton oneStageTimerButton = new JButton("20 mins");
        		JButton saveRunButton = new JButton("Save Run");
        		startTimerButton.setFont(Globals.mainFont);
        		startTimerButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			if(startTimerButton.isEnabled()) {
		        			stopTimerButton.setEnabled(true);
		        			startTimerButton.setEnabled(false);
		        			oneStageTimerButton.setEnabled(false);
		        			startTimer();
	        			}
	        		}
        		});
        		
        		startTimerButton.setBounds(Globals.scale(10), (panelHeight)-(rowHeight*2+Globals.scale(5)), (panelWidth/2)-Globals.scale(10), rowHeight);
        		mainFrame.getContentPane().add(startTimerButton);
        		
        		
        		stopTimerButton.setFont(Globals.mainFont);
        		stopTimerButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent e) {
	        			if(stopTimerButton.isEnabled()) {
		        			stopTimer();
		        			stopTimerButton.setEnabled(false);
		        			startTimerButton.setEnabled(true);
		        			oneStageTimerButton.setEnabled(true);
	        			}
	        		}
        		});
        		stopTimerButton.setBounds(panelWidth/2, (panelHeight)-(rowHeight*2+Globals.scale(5)), (panelWidth/2)-Globals.scale(10), rowHeight);
        		mainFrame.getContentPane().add(stopTimerButton);
        		
        		timerTextField = new JTextField();
        		timerTextField.setBackground(Globals.panelBackground);
        		timerTextField.setForeground(Globals.buttonForground);
        		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
        		timerTextField.setText("00:00:00");
        		timerTextField.setFont(Globals.mainFont);
        		timerTextField.setEditable(false);
        		timerTextField.setBounds(panelWidth/4, (panelHeight)-(rowHeight+Globals.scale(5)), (panelWidth/2), rowHeight);
        		mainFrame.getContentPane().add(timerTextField);
        		timerTextField.setColumns(10);
        		
        		
        		
        		oneStageTimerButton.setBackground(Globals.green);
        		oneStageTimerButton.setFont(Globals.mediumFont);
        		oneStageTimerButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			if(oneStageTimerButton.isEnabled()) {
		        			stopTimerButton.setEnabled(true);
		        			startTimerButton.setEnabled(false);
		        			oneStageTimerButton.setEnabled(false);
		        			startTimer(1200000);
	        			}
	        		}
        		});
        		oneStageTimerButton.setBounds((panelWidth/4)+(panelWidth/2), (panelHeight)-(rowHeight+Globals.scale(5)),((panelWidth/2)-Globals.scale(20))/2, rowHeight);
        		mainFrame.getContentPane().add(oneStageTimerButton);
        		
        		saveRunButton.setBackground(Globals.yellow);
        		saveRunButton.setFont(Globals.mediumFont);
        		saveRunButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			if(startTimerButton.isEnabled()) {
		        			openSaveFarmRun();
	        			}
	        		}
        		});
        		saveRunButton.setBounds(Globals.scale(10), (panelHeight)-(rowHeight+Globals.scale(5)),((panelWidth/2)-Globals.scale(20))/2, rowHeight);
        		mainFrame.getContentPane().add(saveRunButton);
        		
        		
        		
        		
        		
        		final Timer timer = new Timer(500, new ActionListener() {
        		@Override
        		public void actionPerformed(final ActionEvent e) {
        			
        			// If timer End
        			if(System.currentTimeMillis() > Globals.farmTimerStop && Globals.farmTimerStop != 0) {
        				if(!Globals.clip.isRunning()) {
        					Globals.clip.loop(Clip.LOOP_CONTINUOUSLY);
        				}
        				stopTimerButton.setEnabled(true);
        				timerTextField.setText("00:00:00");
        			}else if(Globals.farmTimerStart > 0 && Globals.farmTimerStop != 0) { // if timer started
        				long millis = Globals.farmTimerStop - System.currentTimeMillis();
        			    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
        			            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        			            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        			    timerTextField.setText(hms);
        			}
        			
        		}
        		
        		});
        		
        		if(Globals.farmTimerStop > 0) {
        			ZonedDateTime startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
        			long todayMillis1 = startOfToday.toEpochSecond() * 1000;
        			long millis = Globals.farmTimerStop - todayMillis1;
        			if(millis > (3600000*12)) {
        				millis -= (3600000*12);
        			}
        		    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
        		            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        		            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        		    endTimeLabel.setText("Farm run at "+hms);
        		    stopTimerButton.setEnabled(true);
        		    startTimerButton.setEnabled(false);
        		}else {
        			stopTimerButton.setEnabled(false);
        			startTimerButton.setEnabled(true);
        		}
        		
        		mainFrame.setLocationByPlatform(true);
                mainFrame.setVisible(true);
                mainFrame.setResizable(false);
                
    			
    			
    			FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
    			mainFrame.addMouseListener(frameDragListener);
    			mainFrame.addMouseMotionListener(frameDragListener);
    			
    			
                timer.start();
			}
        });
	}
	public static void startTimer() {
		int timerLength = 4800000; //4800000;
		Globals.farmTimerStart = System.currentTimeMillis();
		Globals.farmTimerStop = System.currentTimeMillis() + timerLength;
		ZonedDateTime startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
		long todayMillis1 = startOfToday.toEpochSecond() * 1000;
		long millis = (System.currentTimeMillis() - todayMillis1)+timerLength;
		if(millis > (3600000*12)) {
			millis -= (3600000*12);
		}
	    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	    endTimeLabel.setText("Farm run at "+hms);
	}
	public static void startTimer(int length) {
		int timerLength = length; //4800000;
		Globals.farmTimerStart = System.currentTimeMillis();
		Globals.farmTimerStop = System.currentTimeMillis() + timerLength;
		ZonedDateTime startOfToday = LocalDate.now().atStartOfDay(ZoneId.systemDefault());
		long todayMillis1 = startOfToday.toEpochSecond() * 1000;
		long millis = (System.currentTimeMillis() - todayMillis1)+timerLength;
		if(millis > (3600000*12)) {
			millis -= (3600000*12);
		}
	    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	    endTimeLabel.setText("Farm run at "+hms);
	}
	public static void stopTimer() {
		Globals.farmTimerStop = 0;
		Globals.farmTimerStart = 0;
		Globals.clip.stop();
		timerTextField.setText("00:00:00");
		endTimeLabel.setText("Start the timer!");
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
	
	private static void openSaveFarmRun() {
		SaveFarmRun.build();
	}
	
	public static void makeVisible() {
		mainFrame.setVisible(true);
		mainFrame.setState(Frame.NORMAL);
	}
	public static void makeInvisible() {
		mainFrame.setVisible(false);
	}
	public static boolean isVisible() {
		return mainFrame.isVisible();
	}
	public static boolean isInit() {
		if(mainFrame == null) {
			return false;
		}else {
			return true;
		}
	}
}
