package panels;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objects.Player;
import ui.SlayerTrackerUI;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.ImageIcon;

public class FarmRunPanel {
	private static float scale = SlayerTrackerUI.scale;
	private static Font mainFont = SlayerTrackerUI.mainFont;
	private static Font smallFont = SlayerTrackerUI.smallFont;
	private static long timerStart;
	private static long timerStop;
	private static JTextField timerTextField;
	private static Clip clip;
	private static JLabel endTimeLabel;
	private static Color panelColour = new Color(240,241,245);
	private static Color buttonColour = new Color(68,187,255);
	private static Color textFieldColour = new Color(102,204,153);
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public static void build(Player player,boolean isStandAlone) {
		EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
            	int rowHeight = scale(25);
            	
            	int width = scale(400);
        		int height = scale(200);
        		int panelWidth = width;
        		int panelHeight = height;
        		
            	JFrame mainFrame = new JFrame("Test");
            	mainFrame.setUndecorated(true);
            	mainFrame.getContentPane().setBackground(panelColour);
            	mainFrame.setResizable(false);
            	mainFrame.setIconImage(Toolkit.getDefaultToolkit().getImage(FarmRunPanel.class.getResource("/images/Farming-icon.png")));
        		mainFrame.setTitle("Farm Run");
        		mainFrame.setBounds(100, 100, width, height);
        		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        		mainFrame.getContentPane().setLayout(null);
        		
        		
        		endTimeLabel = new JLabel("Start the timer!");
        		endTimeLabel.setFont(mainFont);
        		endTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        		endTimeLabel.setBounds(0, panelHeight/3, panelWidth, rowHeight);
        		mainFrame.getContentPane().add(endTimeLabel);
        		
        		JButton closeButton = new JButton();
        		closeButton.setFocusPainted(false);
        		closeButton.setIcon(new ImageIcon(FarmRunPanel.class.getResource("/javax/swing/plaf/metal/icons/ocean/paletteClose-pressed.gif")));
        		closeButton.setBackground(new Color(255,0,0));
        		closeButton.setFont(smallFont);
        		closeButton.setBounds(panelWidth-scale(15), 0, scale(15), scale(15));
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
        		//////////////
        		// Timer
        		JButton startTimerButton = new JButton("Start Countdown");
        		startTimerButton.setBackground(buttonColour);
        		JButton stopTimerButton = new JButton("Stop Countdown");
        		stopTimerButton.setBackground(buttonColour);
        		startTimerButton.setFont(mainFont);
        		startTimerButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent arg0) {
	        			stopTimerButton.setEnabled(true);
	        			startTimerButton.setEnabled(false);
	        			startTimer();
	        		}
        		});
        		
        		startTimerButton.setBounds(scale(10), (panelHeight)-(rowHeight*2+scale(5)), (panelWidth/2)-scale(10), rowHeight);
        		mainFrame.getContentPane().add(startTimerButton);
        		
        		
        		stopTimerButton.setFont(mainFont);
        		stopTimerButton.addMouseListener(new MouseAdapter() {
	        		@Override
	        		public void mouseClicked(MouseEvent e) {
	        			stopTimer();
	        			stopTimerButton.setEnabled(false);
	        			startTimerButton.setEnabled(true);
	        		}
        		});
        		stopTimerButton.setBounds(panelWidth/2, (panelHeight)-(rowHeight*2+scale(5)), (panelWidth/2)-scale(10), rowHeight);
        		mainFrame.getContentPane().add(stopTimerButton);
        		
        		timerTextField = new JTextField();
        		timerTextField.setBackground(textFieldColour);
        		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
        		timerTextField.setText("00:00:00");
        		timerTextField.setFont(mainFont);
        		timerTextField.setEditable(false);
        		timerTextField.setBounds(panelWidth/4, (panelHeight)-(rowHeight+scale(5)), (panelWidth/2), rowHeight);
        		mainFrame.getContentPane().add(timerTextField);
        		timerTextField.setColumns(10);
        		
        		final Timer timer = new Timer(500, new ActionListener() {
        		@Override
        		public void actionPerformed(final ActionEvent e) {
        			
        			// If timer End
        			if(System.currentTimeMillis() > timerStop && timerStop != 0) {
        				clip.loop(Clip.LOOP_CONTINUOUSLY);
        				stopTimerButton.setEnabled(true);
        				timerTextField.setText("00:00:00");
        			}else if(timerStart > 0 && timerStop != 0) { // if timer started
        				long millis = timerStop - System.currentTimeMillis();
        			    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
        			            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
        			            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
        			    timerTextField.setText(hms);
        			}
        			
        		}
        		
        		});
        		
        		try {  
	        		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(SlayerTrackerUI.class.getResource("/sounds/alarm.wav"));
	        		clip = AudioSystem.getClip();
	        		clip.open(audioInputStream);
        		}catch(Exception e) {
        			e.printStackTrace();
        		}
        		
        		mainFrame.setLocationByPlatform(true);
                mainFrame.setVisible(true);
                mainFrame.setResizable(false);
                stopTimerButton.setEnabled(false);
    			startTimerButton.setEnabled(true);
    			
    			
    			FrameDragListener frameDragListener = new FrameDragListener(mainFrame);
    			mainFrame.addMouseListener(frameDragListener);
    			mainFrame.addMouseMotionListener(frameDragListener);
    			
    			
                timer.start();
			}
        });
	}
	public static void startTimer() {
		int timerLength = 4800000; //4800000;
		timerStart = System.currentTimeMillis();
		timerStop = System.currentTimeMillis() + timerLength;
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
		timerStop = 0;
		timerStart = 0;
		clip.stop();
		timerTextField.setText("00:00:00");
		endTimeLabel.setText("Start the timer!");
	}
	public static int scale(int numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public int scale(float numberToScale) {
		return Math.round(numberToScale*scale);
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
