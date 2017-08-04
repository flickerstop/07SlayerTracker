package panels;
import java.awt.Color;
import java.awt.Insets;
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

import objects.Globals;
import objects.Player;

public class MonsterPanel{
	private String name;
	//private ImageIcon image;
	private int count;
	
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
	private JTextField monsterNameTextField = null;
	long currentTime = 0;
	long timerStart = 0;
	long timerStop = 0;
	long pauseTime = 0;
	private JTextField timerTextField;
	private Player player;
	private JPanel tripsPanel = new JPanel();
	private HerbPanel herbPanel;
	
	public MonsterPanel() {
		name = "";
		//image = new ImageIcon();
		count = 0;
	}
	
	public MonsterPanel(String monsterName, ImageIcon icon, int count, Player player) {
		this.player = player;
		name = monsterName;
		//image = icon;
		this.count = count;
	}
	
	public JPanel build(JPanel mainPanel, boolean isCannon, boolean isBurst) {
		 
		/////////////////////
		// Variables
		int width = Globals.width;
		int height = Globals.height;
		
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
		panel.setBounds(0, Globals.topMenuBarHeight, width, Globals.panelHeight);
		panel.setLayout(null);
		panel.setBackground(Globals.panelBackground);
		
		/////////////////////
		// Label
		int monsterLabelWidth = width;
		int monsterLabelHeight = Globals.scale(24);
		
		if(name == "other") {
			JLabel countLabel = new JLabel(count+"  ");
			countLabel.setFont(Globals.massiveFont);
			countLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			countLabel.setForeground(Globals.buttonForground);
			countLabel.setBounds((width/2)-(monsterLabelWidth/4), Globals.scale(10), monsterLabelWidth/4, monsterLabelHeight);
			panel.add(countLabel);
			monsterNameTextField = new JTextField();
			monsterNameTextField.setText("Monster Name");
			monsterNameTextField.setFont(Globals.massiveFont);
			monsterNameTextField.setBounds((width/2), Globals.scale(10), monsterLabelWidth/4, monsterLabelHeight);
			panel.add(monsterNameTextField);
		}else {
			JLabel monsterNameLabel = new JLabel(count + " " +name);
			monsterNameLabel.setForeground(Globals.buttonForground);
			monsterNameLabel.setFont(Globals.massiveFont);
			monsterNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
			monsterNameLabel.setBounds((width/2)-(monsterLabelWidth/2), Globals.scale(10), monsterLabelWidth, monsterLabelHeight);
			panel.add(monsterNameLabel);
		}
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
			time += pauseTime;
			//System.out.println(time);
			// If valid end
			/////////////////////////////
			// 3 = task without cannon
			// 5 = cannon 
			// 7 = burst 
			// 8 = cannon/burst
			
			// player.finishCannonTask(name, count, profit, cannonballLeft);
			if(profit != 0) {
				if(name == "other") {
					if(!monsterNameTextField.getText().equals("Monster Name")) {
						name = monsterNameTextField.getText();
						Object[] toSend = {name, count, profit,time};
						player.finishTask(toSend);
					}
				}else if(isCannon && !isBurst) {
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
		finishTaskButton.setFont(Globals.mainFont);
		finishTaskButton.setBounds(0, height-Globals.scale(50), width, Globals.scale(50));
		panel.add(finishTaskButton);
		
		//////////////////
		// Trips
		
		int tripwidth = (width-Globals.scale(60))/2;
		int tripheight = Globals.scale(220);
		int tripInputWidth = (tripwidth/2)-Globals.scale(15);
		int rowHeight = Globals.scale(25);
		
		if(name == "Aberrant Spectres") {
			herbPanel = new HerbPanel();
			panel.add(herbPanel.build(width, height));
		}else {
			
			tripsPanel.setBackground(Globals.tripsBackground);
			tripsPanel.setBounds((width/2)-tripwidth-Globals.scale(15), (height/2)-(tripheight/2), tripwidth, tripheight);
			panel.add(tripsPanel);
			tripsPanel.setLayout(null);
			
			
			
			JLabel tripPanelLabel = new JLabel("Trips");
			tripPanelLabel.setForeground(Globals.buttonForground);
			tripPanelLabel.setFont(Globals.mainFont);
			tripPanelLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tripPanelLabel.setBounds(0, 0, tripwidth, rowHeight);
			tripsPanel.add(tripPanelLabel);
			
			// 30 down
			JLabel trip1Label = new JLabel("Trip #1");
			trip1Label.setFont(Globals.mainFont);
			trip1Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip1Label.setBounds(0, Globals.scale(30), tripwidth/2, rowHeight);
			tripsPanel.add(trip1Label);
			
			trip1TextField = new JFormattedTextField(formatter);
			trip1TextField.setText("");
			trip1Label.setForeground(Globals.buttonForground);
			trip1TextField.setFont(Globals.mainFont);
			trip1TextField.setBounds(tripwidth/2, Globals.scale(30), tripInputWidth, rowHeight);
			tripsPanel.add(trip1TextField);
			trip1TextField.setColumns(10);
			
			// 60 down
			
			JLabel trip2Label = new JLabel("Trip #2");
			trip2Label.setFont(Globals.mainFont);
			trip2Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip2Label.setBounds(0, Globals.scale(60), tripwidth/2, rowHeight);
			tripsPanel.add(trip2Label);
			
			trip2TextField = new JFormattedTextField(formatter);
			trip2TextField.setText("");
			trip2TextField.setFont(Globals.mainFont);
			trip2Label.setForeground(Globals.buttonForground);
			trip2TextField.setColumns(10);
			trip2TextField.setBounds(tripwidth/2, Globals.scale(60), tripInputWidth, rowHeight);
			tripsPanel.add(trip2TextField);
			
			// 90 down
			JLabel trip3Label = new JLabel("Trip #3");
			trip3Label.setFont(Globals.mainFont);
			trip3Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip3Label.setBounds(0, Globals.scale(90), tripwidth/2, rowHeight);
			tripsPanel.add(trip3Label);
			
			trip3TextField = new JFormattedTextField(formatter);
			trip3TextField.setText("");
			trip3TextField.setFont(Globals.mainFont);
			trip3Label.setForeground(Globals.buttonForground);
			trip3TextField.setColumns(10);
			trip3TextField.setBounds(tripwidth/2, Globals.scale(90), tripInputWidth, rowHeight);
			tripsPanel.add(trip3TextField);
			
			// 120 down
			JLabel trip4Label = new JLabel("Trip #4");
			trip4Label.setFont(Globals.mainFont);
			trip4Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip4Label.setBounds(0, Globals.scale(120), tripwidth/2, rowHeight);
			tripsPanel.add(trip4Label);
			
			trip4TextField = new JFormattedTextField(formatter);
			trip4TextField.setText("");
			trip4TextField.setFont(Globals.mainFont);
			trip4Label.setForeground(Globals.buttonForground);
			trip4TextField.setColumns(10);
			trip4TextField.setBounds(tripwidth/2, Globals.scale(120), tripInputWidth, rowHeight);
			tripsPanel.add(trip4TextField);
			
			// 150 down
			JLabel trip5Label = new JLabel("Trip #5");
			trip5Label.setFont(Globals.mainFont);
			trip5Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip5Label.setBounds(0, Globals.scale(150), tripwidth/2, rowHeight);
			tripsPanel.add(trip5Label);
			
			trip5TextField = new JFormattedTextField(formatter);
			trip5TextField.setText("");
			trip5TextField.setFont(Globals.mainFont);
			trip5Label.setForeground(Globals.buttonForground);
			trip5TextField.setColumns(10);
			trip5TextField.setBounds(tripwidth/2, Globals.scale(150), tripInputWidth, rowHeight);
			tripsPanel.add(trip5TextField);
			
			// 180 down
			JLabel trip6Label = new JLabel("Trip #6");
			trip6Label.setFont(Globals.mainFont);
			trip6Label.setHorizontalAlignment(SwingConstants.CENTER);
			trip6Label.setBounds(0, Globals.scale(180), tripwidth/2, rowHeight);
			tripsPanel.add(trip6Label);
			
			trip6TextField = new JFormattedTextField(formatter);
			trip6TextField.setText("");
			trip6TextField.setFont(Globals.mainFont);
			trip6Label.setForeground(Globals.buttonForground);
			trip6TextField.setColumns(10);
			trip6TextField.setBounds(tripwidth/2, Globals.scale(180), tripInputWidth, rowHeight);
			tripsPanel.add(trip6TextField);
		}
		/////////////////////////////////////////////////////////////
		// Other Info Panel
		int infowidth = (width-Globals.scale(60))/2;
		int infoheight = Globals.scale(220);
		
		
		JPanel otherInfoPanel = new JPanel();
		otherInfoPanel.setBackground(Globals.otherInfoBackground);
		otherInfoPanel.setBounds((width/2)+Globals.scale(15), (height/2)-infoheight/2, infowidth, infoheight);
		panel.add(otherInfoPanel);
		otherInfoPanel.setLayout(null);
		
		JLabel otherInfoLabel = new JLabel("Other Info");
		otherInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		otherInfoLabel.setFont(Globals.mainFont);
		otherInfoLabel.setForeground(Globals.buttonForground);
		otherInfoLabel.setBounds(0, 0, infowidth, rowHeight);
		otherInfoPanel.add(otherInfoLabel);
		
		if(isCannon) {
			//////////////
			// Cannonball Label
			JLabel cannonballsLeftLabel = new JLabel("Cannonballs Left");
			cannonballsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			cannonballsLeftLabel.setFont(Globals.mainFont);
			cannonballsLeftLabel.setForeground(Globals.buttonForground);
			cannonballsLeftLabel.setBounds(0, Globals.scale(30), infowidth/2, rowHeight);
			otherInfoPanel.add(cannonballsLeftLabel);
			
			cannonballsLeftTextField = new JFormattedTextField(formatter);
			cannonballsLeftTextField.setFont(Globals.mainFont);
			cannonballsLeftTextField.setBounds(infowidth/2, Globals.scale(30), (infowidth/2)-Globals.scale(15), rowHeight);
			otherInfoPanel.add(cannonballsLeftTextField);
			cannonballsLeftTextField.setColumns(10);
		
		}
		if(isBurst) {
			JLabel deathRunesLeftLabel = new JLabel("Death Runes Left");
			deathRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			deathRunesLeftLabel.setFont(Globals.mainFont);
			deathRunesLeftLabel.setForeground(Globals.buttonForground);
			deathRunesLeftLabel.setBounds(0, Globals.scale(30)+rowHeight, infowidth/2, rowHeight);
			otherInfoPanel.add(deathRunesLeftLabel);
			
			deathRunesLeftTextField = new JFormattedTextField(formatter);
			deathRunesLeftTextField.setFont(Globals.mainFont);
			deathRunesLeftTextField.setBounds(infowidth/2, Globals.scale(30)+rowHeight, (infowidth/2)-Globals.scale(15), rowHeight);
			otherInfoPanel.add(deathRunesLeftTextField);
			deathRunesLeftTextField.setColumns(10);
			
			JLabel chaosRunesLeftLabel = new JLabel("Chaos Runes Left");
			chaosRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			chaosRunesLeftLabel.setFont(Globals.mainFont);
			chaosRunesLeftLabel.setForeground(Globals.buttonForground);
			chaosRunesLeftLabel.setBounds(0, Globals.scale(30)+rowHeight*2, infowidth/2, rowHeight);
			otherInfoPanel.add(chaosRunesLeftLabel);
			
			chaosRunesLeftTextField = new JFormattedTextField(formatter);
			chaosRunesLeftTextField.setFont(Globals.mainFont);
			chaosRunesLeftTextField.setBounds(infowidth/2, Globals.scale(30)+rowHeight*2, (infowidth/2)-Globals.scale(15), rowHeight);
			otherInfoPanel.add(chaosRunesLeftTextField);
			chaosRunesLeftTextField.setColumns(10);
			
			JLabel waterRunesLeftLabel = new JLabel("Water Runes Left");
			waterRunesLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
			waterRunesLeftLabel.setFont(Globals.mainFont);
			waterRunesLeftLabel.setForeground(Globals.buttonForground);
			waterRunesLeftLabel.setBounds(0, Globals.scale(30)+rowHeight*3, infowidth/2, rowHeight);
			otherInfoPanel.add(waterRunesLeftLabel);
			
			waterRunesLeftTextField = new JFormattedTextField(formatter);
			waterRunesLeftTextField.setFont(Globals.mainFont);
			waterRunesLeftTextField.setBounds(infowidth/2, Globals.scale(30)+rowHeight*3, (infowidth/2)-Globals.scale(15), rowHeight);
			otherInfoPanel.add(waterRunesLeftTextField);
			waterRunesLeftTextField.setColumns(10);
		}
		//////////////
		// Timer
		JButton startTimerButton = new JButton("Start Timer");
		JButton stopTimerButton = new JButton("Stop Timer");
		startTimerButton.setFont(Globals.mainFont);
		startTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(startTimerButton.isEnabled()){
				startTimerButton.setEnabled(false);
				stopTimerButton.setEnabled(true);
				startTimer();
			}
		}
		});
		
		startTimerButton.setBounds(Globals.scale(10), (infoheight)-(rowHeight*2+Globals.scale(5)), (infowidth/2)-Globals.scale(10), rowHeight);
		otherInfoPanel.add(startTimerButton);
		
		
		stopTimerButton.setFont(Globals.mainFont);
		stopTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(stopTimerButton.isEnabled()){
				startTimerButton.setEnabled(true);
				stopTimerButton.setEnabled(false);
				stopTimer();
			}
		}
		});
		stopTimerButton.setBounds(infowidth/2, (infoheight)-(rowHeight*2+Globals.scale(5)), (infowidth/2)-Globals.scale(10), rowHeight);
		stopTimerButton.setEnabled(false);
		otherInfoPanel.add(stopTimerButton);
		
		timerTextField = new JTextField();
		timerTextField.setHorizontalAlignment(SwingConstants.CENTER);
		timerTextField.setText("00:00:00");
		timerTextField.setFont(Globals.mainFont);
		timerTextField.setEditable(false);
		timerTextField.setBounds(infowidth/4, (infoheight)-(rowHeight+Globals.scale(5)), (infowidth/2), rowHeight);
		otherInfoPanel.add(timerTextField);
		timerTextField.setColumns(10);
		
		final Timer timer = new Timer(500, new ActionListener() {
		@Override
		public void actionPerformed(final ActionEvent e) {
			if(timerStart > 0 && timerStop == 0) {
				long millis = (System.currentTimeMillis() - timerStart)+pauseTime;
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
		homeButton.setFont(Globals.smallFont);
		homeButton.setMargin(new Insets(0, 0, 0, 0));
		homeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				panel.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		
		homeButton.setBounds(Globals.scale(2), Globals.scale(2), Globals.scale(20), Globals.scale(20));
		panel.add(homeButton);
		
		
		
		
		
		
		
		
		timer.start();
		return panel;
	}
	public void startTimer() {
		if(timerStop == 0) { // if the stop button was never clicked
			timerStart = System.currentTimeMillis();
		}if(timerStop > 0) { // if stop was clicked
			pauseTime += timerStop - timerStart;
			timerStart = System.currentTimeMillis();
			timerStop = 0;
		}
	}
	public void stopTimer() {
		if (timerStart != 0) { // if timer has started
			timerStop = System.currentTimeMillis();
		}
	}
	
	
}
