package objects;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

import ui.SlayerTrackerUI;

public class MonsterPanel{
	private static Font mainFont = SlayerTrackerUI.mainFont;
	private static Font massiveFont = SlayerTrackerUI.massiveFont;
	private String name;
	//private ImageIcon image;
	private int count;
	private float scale;
	
	private JFormattedTextField trip1TextField;
	private JFormattedTextField trip2TextField;
	private JFormattedTextField trip3TextField;
	private JFormattedTextField trip4TextField;
	private JFormattedTextField trip5TextField;
	private JFormattedTextField trip6TextField;
	private JFormattedTextField cannonballsLeftTextField;
	private JFormattedTextField deathRunesLeftTextField;
	private JFormattedTextField chaosRunesLeftTextField;
	private JFormattedTextField waterRunesLeftTextField;
	
	long currentTime = 0;
	long timerStart = 0;
	long timerStop = 0;
	private JTextField timerTextField;
	private Player player;
	private JPanel tripsPanel = new JPanel();
	private HerbPanel herbPanel;
	public MonsterPanel() {
		name = "";
		//image = new ImageIcon();
		count = 0;
		scale = 1.0f;
	}
	
	public MonsterPanel(String monsterName, ImageIcon icon, int count, float scale,Player player) {
		this.player = player;
		name = monsterName;
		//image = icon;
		this.count = count;
		this.scale = scale;
	}
	
	public JPanel build(JPanel mainPanel, boolean isCannon, boolean isBurst) {
				
		/////////////////////
		// Variables
		int width = scale(640); //TODO modify this when moved!
		int height = scale(550); //TODO modify this when moved!
		int panelWidth = width-18;
		int panelHeight = height-40;
		
		NumberFormat format = NumberFormat.getInstance();
		NumberFormatter formatter = new NumberFormatter(format);
		formatter.setValueClass(Integer.class);
		formatter.setMinimum(0);
		formatter.setMaximum(Integer.MAX_VALUE);
		formatter.setAllowsInvalid(true);
		// If you want the value to be committed on each keystroke instead of focus lost
		formatter.setCommitsOnValidEdit(true);
		
		/////////////////////
		// Panel
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, panelWidth, panelHeight); //TODO modify this when moved!
		panel.setLayout(null);
		
		/////////////////////
		// Label
		JLabel monsterNameLabel = new JLabel(count + " " +name);
		monsterNameLabel.setFont(massiveFont);
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
			long time = 0;
			int deathRunesLeft = -1;
			int chaosRunesLeft = -1;
			int waterRunesLeft = -1;
			
			////////////////
			// Get loot
			if(name == "Aberrant Spectres") {
				profit = herbPanel.calculateHerbs();
			}else {
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
			
			}
			if(isCannon) {
				///////////////
				// Get cannonballs left
				if(cannonballsLeftTextField.getText().length() != 0) {
					cannonballLeft = Integer.parseInt(cannonballsLeftTextField.getText().replaceAll(",", ""));
				}else {
					// If nothing was entered where cannonballs are left
					cannonballsLeftTextField.setBackground(new Color(255, 0, 0));
				}
			}
			if(isBurst) {
				///////////////
				// Get cannonballs left
				if(deathRunesLeftTextField.getText().length() != 0) {
					deathRunesLeft = Integer.parseInt(deathRunesLeftTextField.getText().replaceAll(",", ""));
				}else {
					// If nothing was entered where cannonballs are left
					deathRunesLeftTextField.setBackground(new Color(255, 0, 0));
				}
				if(chaosRunesLeftTextField.getText().length() != 0) {
					chaosRunesLeft = Integer.parseInt(chaosRunesLeftTextField.getText().replaceAll(",", ""));
				}else {
					// If nothing was entered where cannonballs are left
					chaosRunesLeftTextField.setBackground(new Color(255, 0, 0));
				}
				if(waterRunesLeftTextField.getText().length() != 0) {
					waterRunesLeft = Integer.parseInt(waterRunesLeftTextField.getText().replaceAll(",", ""));
				}else {
					// If nothing was entered where cannonballs are left
					waterRunesLeftTextField.setBackground(new Color(255, 0, 0));
				}
			}
			////////////
			// Get timer
			if(timerStop > 0) {
				time = timerStop - timerStart;
			}else if(timerStart > 0) {
				time = System.currentTimeMillis() - timerStart;
			}else {
				time = 0;
			}
			//System.out.println(time);
			// If valid end
			/////////////////////////////
			// 3 = task without cannon
			// 5 = cannon 
			// 7 = burst 
			// 8 = cannon/burst
			
			// player.finishCannonTask(name, count, profit, cannonballLeft);
			if(profit != 0) {
				if(isCannon && !isBurst) {
					Object[] toSend = {name, count, profit, cannonballLeft, time};
					player.finishTask(toSend);
				}else if(!isCannon && isBurst) {
					Object[] toSend = {name,count, profit, deathRunesLeft, chaosRunesLeft, waterRunesLeft, time};
					player.finishTask(toSend);
				}else if(!isCannon && !isBurst) {
					Object[] toSend = {name, count, profit,time};
					player.finishTask(toSend);
				}else if(isCannon && isBurst) {
					Object[] toSend = {name,count, profit, cannonballLeft, deathRunesLeft, chaosRunesLeft, waterRunesLeft, time};
					player.finishTask(toSend);
				}
				panel.setVisible(false);
				mainPanel.setVisible(true);
			}
			//System.out.println(profit);
		}
		});
		finishTaskButton.setVerticalAlignment(SwingConstants.TOP);
		finishTaskButton.setForeground(new Color(255, 255, 255));
		finishTaskButton.setBackground(new Color(0, 153, 51));
		finishTaskButton.setFont(mainFont);
		finishTaskButton.setBounds(0, panelHeight-scale(50), panelWidth, scale(50));
		panel.add(finishTaskButton);
		
		//////////////////
		// Trips
		
		int tripPanelWidth = (panelWidth-scale(60))/2;
		int tripPanelHeight = scale(220);
		int tripInputWidth = (tripPanelWidth/2)-scale(15);
		int rowHeight = scale(25);
		
		if(name == "Aberrant Spectres") {
			herbPanel = new HerbPanel();
			panel.add(herbPanel.build(panelWidth, panelHeight));
		}else {
			
			tripsPanel.setBackground(new Color(255, 51, 51));
			tripsPanel.setBounds((panelWidth/2)-tripPanelWidth-scale(15), (panelHeight/2)-(tripPanelHeight/2), tripPanelWidth, tripPanelHeight);
			panel.add(tripsPanel);
			tripsPanel.setLayout(null);
			
			
			
			JLabel tripPanelLabel = new JLabel("Trips");
			tripPanelLabel.setFont(mainFont);
			tripPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tripPanelLabel.setBounds(0, 0, tripPanelWidth, rowHeight);
			tripsPanel.add(tripPanelLabel);
			
			// 30 down
			JLabel trip1Label = new JLabel("Trip #1");
			trip1Label.setFont(mainFont);
			trip1Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip1Label.setBounds(0, scale(30), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip1Label);
			
			trip1TextField = new JFormattedTextField(formatter);
			trip1TextField.setText("");
			trip1TextField.setFont(mainFont);
			trip1TextField.setBounds(tripPanelWidth/2, scale(30), tripInputWidth, rowHeight);
			tripsPanel.add(trip1TextField);
			trip1TextField.setColumns(10);
			
			// 60 down
			
			JLabel trip2Label = new JLabel("Trip #2");
			trip2Label.setFont(mainFont);
			trip2Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip2Label.setBounds(0, scale(60), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip2Label);
			
			trip2TextField = new JFormattedTextField(formatter);
			trip2TextField.setText("");
			trip2TextField.setFont(mainFont);
			trip2TextField.setColumns(10);
			trip2TextField.setBounds(tripPanelWidth/2, scale(60), tripInputWidth, rowHeight);
			tripsPanel.add(trip2TextField);
			
			// 90 down
			JLabel trip3Label = new JLabel("Trip #3");
			trip3Label.setFont(mainFont);
			trip3Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip3Label.setBounds(0, scale(90), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip3Label);
			
			trip3TextField = new JFormattedTextField(formatter);
			trip3TextField.setText("");
			trip3TextField.setFont(mainFont);
			trip3TextField.setColumns(10);
			trip3TextField.setBounds(tripPanelWidth/2, scale(90), tripInputWidth, rowHeight);
			tripsPanel.add(trip3TextField);
			
			// 120 down
			JLabel trip4Label = new JLabel("Trip #4");
			trip4Label.setFont(mainFont);
			trip4Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip4Label.setBounds(0, scale(120), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip4Label);
			
			trip4TextField = new JFormattedTextField(formatter);
			trip4TextField.setText("");
			trip4TextField.setFont(mainFont);
			trip4TextField.setColumns(10);
			trip4TextField.setBounds(tripPanelWidth/2, scale(120), tripInputWidth, rowHeight);
			tripsPanel.add(trip4TextField);
			
			// 150 down
			JLabel trip5Label = new JLabel("Trip #5");
			trip5Label.setFont(mainFont);
			trip5Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip5Label.setBounds(0, scale(150), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip5Label);
			
			trip5TextField = new JFormattedTextField(formatter);
			trip5TextField.setText("");
			trip5TextField.setFont(mainFont);
			trip5TextField.setColumns(10);
			trip5TextField.setBounds(tripPanelWidth/2, scale(150), tripInputWidth, rowHeight);
			tripsPanel.add(trip5TextField);
			
			// 180 down
			JLabel trip6Label = new JLabel("Trip #6");
			trip6Label.setFont(mainFont);
			trip6Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip6Label.setBounds(0, scale(180), tripPanelWidth/2, rowHeight);
			tripsPanel.add(trip6Label);
			
			trip6TextField = new JFormattedTextField(formatter);
			trip6TextField.setText("");
			trip6TextField.setFont(mainFont);
			trip6TextField.setColumns(10);
			trip6TextField.setBounds(tripPanelWidth/2, scale(180), tripInputWidth, rowHeight);
			tripsPanel.add(trip6TextField);
		}
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
		otherInfoLabel.setFont(mainFont);
		otherInfoLabel.setBounds(0, 0, infoPanelWidth, rowHeight);
		otherInfoPanel.add(otherInfoLabel);
		
		if(isCannon) {
			//////////////
			// Cannonball Label
			JLabel cannonballsLeftLabel = new JLabel("Cannonballs Left");
			cannonballsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			cannonballsLeftLabel.setFont(mainFont);
			cannonballsLeftLabel.setBounds(0, scale(30), infoPanelWidth/2, rowHeight);
			otherInfoPanel.add(cannonballsLeftLabel);
			
			cannonballsLeftTextField = new JFormattedTextField(formatter);
			cannonballsLeftTextField.setFont(mainFont);
			cannonballsLeftTextField.setBounds(infoPanelWidth/2, scale(30), (infoPanelWidth/2)-scale(15), rowHeight);
			otherInfoPanel.add(cannonballsLeftTextField);
			cannonballsLeftTextField.setColumns(10);
		
		}
		if(isBurst) {
			JLabel deathRunesLeftLabel = new JLabel("Death Runes Left");
			deathRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			deathRunesLeftLabel.setFont(mainFont);
			deathRunesLeftLabel.setBounds(0, scale(30)+rowHeight, infoPanelWidth/2, rowHeight);
			otherInfoPanel.add(deathRunesLeftLabel);
			
			deathRunesLeftTextField = new JFormattedTextField(formatter);
			deathRunesLeftTextField.setFont(mainFont);
			deathRunesLeftTextField.setBounds(infoPanelWidth/2, scale(30)+rowHeight, (infoPanelWidth/2)-scale(15), rowHeight);
			otherInfoPanel.add(deathRunesLeftTextField);
			deathRunesLeftTextField.setColumns(10);
			
			JLabel chaosRunesLeftLabel = new JLabel("Chaos Runes Left");
			chaosRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			chaosRunesLeftLabel.setFont(mainFont);
			chaosRunesLeftLabel.setBounds(0, scale(30)+rowHeight*2, infoPanelWidth/2, rowHeight);
			otherInfoPanel.add(chaosRunesLeftLabel);
			
			chaosRunesLeftTextField = new JFormattedTextField(formatter);
			chaosRunesLeftTextField.setFont(mainFont);
			chaosRunesLeftTextField.setBounds(infoPanelWidth/2, scale(30)+rowHeight*2, (infoPanelWidth/2)-scale(15), rowHeight);
			otherInfoPanel.add(chaosRunesLeftTextField);
			chaosRunesLeftTextField.setColumns(10);
			
			JLabel waterRunesLeftLabel = new JLabel("Water Runes Left");
			waterRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			waterRunesLeftLabel.setFont(mainFont);
			waterRunesLeftLabel.setBounds(0, scale(30)+rowHeight*3, infoPanelWidth/2, rowHeight);
			otherInfoPanel.add(waterRunesLeftLabel);
			
			waterRunesLeftTextField = new JFormattedTextField(formatter);
			waterRunesLeftTextField.setFont(mainFont);
			waterRunesLeftTextField.setBounds(infoPanelWidth/2, scale(30)+rowHeight*3, (infoPanelWidth/2)-scale(15), rowHeight);
			otherInfoPanel.add(waterRunesLeftTextField);
			waterRunesLeftTextField.setColumns(10);
		}
		//////////////
		// Timer
		JButton startTimerButton = new JButton("Start Timer");
		startTimerButton.setFont(mainFont);
		startTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			startTimer();
		}
		});
		
		startTimerButton.setBounds(scale(10), (infoPanelHeight)-(rowHeight*2+scale(5)), (infoPanelWidth/2)-scale(10), rowHeight);
		otherInfoPanel.add(startTimerButton);
		
		JButton stopTimerButton = new JButton("Stop Timer");
		stopTimerButton.setFont(mainFont);
		stopTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			stopTimer();
		}
		});
		stopTimerButton.setBounds(infoPanelWidth/2, (infoPanelHeight)-(rowHeight*2+scale(5)), (infoPanelWidth/2)-scale(10), rowHeight);
		otherInfoPanel.add(stopTimerButton);
		
		timerTextField = new JTextField();
		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		timerTextField.setText("00:00:00");
		timerTextField.setFont(mainFont);
		timerTextField.setEditable(false);
		timerTextField.setBounds(infoPanelWidth/4, (infoPanelHeight)-(rowHeight+scale(5)), (infoPanelWidth/2), rowHeight);
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
		
		/////////////////////////////////////////////////////////
		// home button
		JButton homeButton = new JButton("H");
		homeButton.setFont(mainFont);
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		
		homeButton.setBounds(scale(2), scale(2), scale(20), scale(20));
		panel.add(homeButton);
		
		
		
		
		
		
		
		
		timer.start();
		return panel;
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
