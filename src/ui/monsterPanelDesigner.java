package ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class monsterPanelDesigner {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					monsterPanelDesigner window = new monsterPanelDesigner();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public monsterPanelDesigner() {
		initialize();
	}
	float scale = 1.0f;
	private JFormattedTextField trip1TextField;
	private JFormattedTextField trip2TextField;
	private JFormattedTextField trip3TextField;
	private JFormattedTextField trip4TextField;
	private JFormattedTextField trip5TextField;
	private JFormattedTextField trip6TextField;
	private JFormattedTextField cannonballsLeftTextField;
	
	long currentTime = 0;
	long timerStart = 0;
	long timerStop = 0;
	private JTextField timerTextField;
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/////////////////////
		// Variables
		int width = scale(640); //TODO modify this when moved!
		int height = scale(550); //TODO modify this when moved!
		int panelWidth = width-18;
		int panelHeight = height-40;
		String monsterName = "Dust Devils";
		int monsterCount = 100;
		
		NumberFormat format = NumberFormat.getInstance();
	    NumberFormatter formatter = new NumberFormatter(format);
	    formatter.setValueClass(Integer.class);
	    formatter.setMinimum(0);
	    formatter.setMaximum(Integer.MAX_VALUE);
	    formatter.setAllowsInvalid(false);
	    // If you want the value to be committed on each keystroke instead of focus lost
	    formatter.setCommitsOnValidEdit(true);

		//////////////////////
		// DONT NEED THIS
		
		frame = new JFrame();
		frame.setBounds(100, 100, width, height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		/////////////////////
		// Panel
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, panelWidth, panelHeight); //TODO modify this when moved!
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		/////////////////////
		// Label
		JLabel monsterNameLabel = new JLabel(monsterCount + " " +monsterName);
		monsterNameLabel.setFont(new Font("Tahoma", Font.BOLD, scale(20)));
		monsterNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		int monsterLabelWidth = panelWidth;
		int monsterLabelHeight = scale(24);
		monsterNameLabel.setBounds((width/2)-(monsterLabelWidth/2), scale(10), monsterLabelWidth, monsterLabelHeight);
		panel.add(monsterNameLabel);
		
		//////////////////
		// Finish task button
		JButton finishTaskButton = new JButton("Finish Task!");
		finishTaskButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int profit = 0;
				int cannonballLeft = -1;
				
				if(trip1TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip1TextField.getText().replaceAll(",", ""));
				}
				if(trip2TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip2TextField.getText().replaceAll(",", ""));
				}
				if(trip3TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip3TextField.getText().replaceAll(",", ""));
				}
				if(trip4TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip4TextField.getText().replaceAll(",", ""));
				}
				if(trip5TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip5TextField.getText().replaceAll(",", ""));
				}
				if(trip6TextField.getText().length() != 0) {
					profit += Integer.parseInt(trip6TextField.getText().replaceAll(",", ""));
				}
				
				if(cannonballsLeftTextField.getText().length() != 0) {
					cannonballLeft = Integer.parseInt(cannonballsLeftTextField.getText().replaceAll(",", ""));
				}else {
					// If nothing was entered where cannonballs are left
					cannonballsLeftTextField.setBackground(new Color(255, 0, 0));
				}
				
				//System.out.println(profit);
			}
		});
		finishTaskButton.setVerticalAlignment(SwingConstants.TOP);
		finishTaskButton.setForeground(new Color(255, 255, 255));
		finishTaskButton.setBackground(new Color(0, 153, 51));
		finishTaskButton.setFont(new Font("Tahoma", Font.BOLD, scale(12)));
		finishTaskButton.setBounds(0, panelHeight-scale(50), panelWidth, scale(50));
		panel.add(finishTaskButton);
		
		//////////////////
		// Trips
		int tripPanelWidth = (panelWidth-scale(60))/2;
		int tripPanelHeight = scale(220);
		int tripInputWidth = (tripPanelWidth/2)-scale(15);
		int rowHeight = scale(25);
		Font labelFont = new Font("Tahoma", Font.PLAIN, scale(11));
		
		JPanel tripsPanel = new JPanel();
		tripsPanel.setBackground(new Color(255, 51, 51));
		tripsPanel.setBounds((panelWidth/2)-tripPanelWidth-scale(15), (panelHeight/2)-(tripPanelHeight/2), tripPanelWidth, tripPanelHeight);
		panel.add(tripsPanel);
		tripsPanel.setLayout(null);
		
		
		
		JLabel tripPanelLabel = new JLabel("Trips");
		tripPanelLabel.setFont(new Font("Tahoma", Font.BOLD, scale(12)));
		tripPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tripPanelLabel.setBounds(0, 0, tripPanelWidth, rowHeight);
		tripsPanel.add(tripPanelLabel);
		
		// 30 down
		JLabel trip1Label = new JLabel("Trip #1");
		trip1Label.setFont(labelFont);
		trip1Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip1Label.setBounds(0, scale(30), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip1Label);
		
		trip1TextField = new JFormattedTextField(formatter);
		trip1TextField.setText("");
		trip1TextField.setFont(labelFont);
		trip1TextField.setBounds(tripPanelWidth/2, scale(30), tripInputWidth, rowHeight);
		tripsPanel.add(trip1TextField);
		trip1TextField.setColumns(10);
		
		// 60 down
		
		JLabel trip2Label = new JLabel("Trip #2");
		trip2Label.setFont(labelFont);
		trip2Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip2Label.setBounds(0, scale(60), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip2Label);
		
		trip2TextField = new JFormattedTextField(formatter);
		trip2TextField.setText("");
		trip2TextField.setFont(labelFont);
		trip2TextField.setColumns(10);
		trip2TextField.setBounds(tripPanelWidth/2, scale(60), tripInputWidth, rowHeight);
		tripsPanel.add(trip2TextField);
		
		// 90 down
		JLabel trip3Label = new JLabel("Trip #3");
		trip3Label.setFont(labelFont);
		trip3Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip3Label.setBounds(0, scale(90), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip3Label);
		
		trip3TextField = new JFormattedTextField(formatter);
		trip3TextField.setText("");
		trip3TextField.setFont(labelFont);
		trip3TextField.setColumns(10);
		trip3TextField.setBounds(tripPanelWidth/2, scale(90), tripInputWidth, rowHeight);
		tripsPanel.add(trip3TextField);
		
		// 120 down
		JLabel trip4Label = new JLabel("Trip #4");
		trip4Label.setFont(labelFont);
		trip4Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip4Label.setBounds(0, scale(120), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip4Label);
		
		trip4TextField = new JFormattedTextField(formatter);
		trip4TextField.setText("");
		trip4TextField.setFont(labelFont);
		trip4TextField.setColumns(10);
		trip4TextField.setBounds(tripPanelWidth/2, scale(120), tripInputWidth, rowHeight);
		tripsPanel.add(trip4TextField);
		
		// 150 down
		JLabel trip5Label = new JLabel("Trip #5");
		trip5Label.setFont(labelFont);
		trip5Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip5Label.setBounds(0, scale(150), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip5Label);
		
		trip5TextField = new JFormattedTextField(formatter);
		trip5TextField.setText("");
		trip5TextField.setFont(labelFont);
		trip5TextField.setColumns(10);
		trip5TextField.setBounds(tripPanelWidth/2, scale(150), tripInputWidth, rowHeight);
		tripsPanel.add(trip5TextField);
		
		// 180 down
		JLabel trip6Label = new JLabel("Trip #6");
		trip6Label.setFont(labelFont);
		trip6Label.setHorizontalAlignment(SwingConstants.CENTER);
		trip6Label.setBounds(0, scale(180), tripPanelWidth/2, rowHeight);
		tripsPanel.add(trip6Label);
		
		trip6TextField = new JFormattedTextField(formatter);
		trip6TextField.setText("");
		trip6TextField.setFont(labelFont);
		trip6TextField.setColumns(10);
		trip6TextField.setBounds(tripPanelWidth/2, scale(180), tripInputWidth, rowHeight);
		tripsPanel.add(trip6TextField);
		
		/////////////////////////////////////////////////////////////
		// Other Info Panel
		int infoPanelWidth = (panelWidth-scale(60))/2;
		int infoPanelHeight = scale(220);
		
		
		JPanel otherInfoPanel = new JPanel();
		otherInfoPanel.setBackground(new Color(0, 204, 255));
		otherInfoPanel.setBounds((panelWidth/2)+scale(15), (panelHeight/2)-infoPanelHeight/2, infoPanelWidth, infoPanelHeight);
		panel.add(otherInfoPanel);
		otherInfoPanel.setLayout(null);
		
		JLabel otherInfoLabel = new JLabel("Other Info");
		otherInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfoLabel.setFont(new Font("Tahoma", Font.BOLD, scale(12)));
		otherInfoLabel.setBounds(0, 0, infoPanelWidth, rowHeight);
		otherInfoPanel.add(otherInfoLabel);
		
		//////////////
		// Cannonball Label
		
		
		
		JLabel cannonballsLeftLabel = new JLabel("Cannonballs Left");
		cannonballsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cannonballsLeftLabel.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		cannonballsLeftLabel.setBounds(0, (infoPanelHeight/2)-(rowHeight/2)-scale(45), infoPanelWidth/2, rowHeight);
		otherInfoPanel.add(cannonballsLeftLabel);
		
		cannonballsLeftTextField = new JFormattedTextField(formatter);
		cannonballsLeftTextField.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		cannonballsLeftTextField.setBounds(infoPanelWidth/2, (infoPanelHeight/2)-(rowHeight/2)-scale(45), (infoPanelWidth/2)-scale(15), rowHeight);
		otherInfoPanel.add(cannonballsLeftTextField);
		cannonballsLeftTextField.setColumns(10);
		
		//////////////
		// Timer
		JButton startTimerButton = new JButton("Start Timer");
		startTimerButton.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		startTimerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				startTimer();
			}
		});
		startTimerButton.setBounds(scale(10), (infoPanelHeight/2)-(rowHeight/2), (infoPanelWidth/2)-scale(10), rowHeight);
		otherInfoPanel.add(startTimerButton);
		
		JButton stopTimerButton = new JButton("Stop Timer");
		stopTimerButton.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		stopTimerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				stopTimer();
			}
		});
		stopTimerButton.setBounds(infoPanelWidth/2, (infoPanelHeight/2)-(rowHeight/2), (infoPanelWidth/2)-scale(10), rowHeight);
		otherInfoPanel.add(stopTimerButton);
		
		timerTextField = new JTextField();
		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		timerTextField.setText("00:00:00");
		timerTextField.setFont(new Font("Tahoma", Font.PLAIN, scale(11)));
		timerTextField.setEditable(false);
		timerTextField.setBounds(infoPanelWidth/4, (infoPanelHeight/2)-(rowHeight/2)+rowHeight, (infoPanelWidth/2), rowHeight);
		otherInfoPanel.add(timerTextField);
		timerTextField.setColumns(10);
		
		final Timer timer = new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(final ActionEvent e) {
	        	if(timerStart > 0 && timerStop == 0) {
	        		long millis = System.currentTimeMillis() - timerStart;
	        	    String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
	        	            TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
	        	            TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
	        	    timerTextField.setText(hms);
	        	}else if(timerStop > 0) {

	        	}
	        }

	    });
	    timer.start();
		
	}
	
	public void startTimer() {
		timerStart = System.currentTimeMillis();
	}
	public void stopTimer() {
		timerStop = System.currentTimeMillis();
	}
	
	public int scale(int numberToScale) {
		return Math.round(numberToScale*scale);
	}
	public int scale(float numberToScale) {
		return Math.round(numberToScale*scale);
	}
}
