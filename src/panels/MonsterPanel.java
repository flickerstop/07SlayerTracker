package panels;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.text.NumberFormatter;

import objects.Globals;


public class MonsterPanel{
	private String name = "";
	//private ImageIcon image;
	private int count;
	
	private JFormattedTextField cannonballsLeftTextField;
	private JFormattedTextField rune1LeftTextField;
	private JFormattedTextField rune2LeftTextField;
	private JFormattedTextField rune3LeftTextField;
	JLabel cannonballsLeftLabel = new JLabel("Cannonballs Left");
	JLabel rune1Label = new JLabel();
	JLabel rune2Label = new JLabel();
	JLabel rune3Label = new JLabel();
	JRadioButton r1 = new JRadioButton("Normal");
	JRadioButton r2 = new JRadioButton("Cannon");
	JRadioButton r3 = new JRadioButton("Burst");
	JRadioButton r4 = new JRadioButton("Burst & Cannon");
	ArrayList<JFormattedTextField> trips = new ArrayList<JFormattedTextField>();
	long timerStart = 0;
	long timerStop = 0;
	long pauseTime = 0;
	JLabel monsterNameLabel;
	private JTextField timerTextField;
	private JPanel tripsPanel = new JPanel();
	private JPanel herbPanel;
	JPanel panel;
	
	boolean isCannon = false;
	boolean isMagic = false;
	
	public MonsterPanel() {
		name = "";
		//image = new ImageIcon();
		count = 0;
	}
	
	public MonsterPanel(String monsterName, ImageIcon icon, int count) {
		name = monsterName;
		this.count = count;
	}
	
	public JPanel build(JPanel mainPanel) {
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
		panel = new JPanel();
		panel.setBounds(0, Globals.topMenuBarHeight, width, Globals.panelHeight);
		panel.setLayout(null);
		panel.setBackground(Globals.panelBackground);
		
		/////////////////////
		// Label
		int monsterLabelWidth = width;
		int monsterLabelHeight = Globals.scale(24);
		

		monsterNameLabel = new JLabel(count + " " +name);
		monsterNameLabel.setForeground(Globals.buttonForground);
		monsterNameLabel.setFont(Globals.massiveFont);
		monsterNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monsterNameLabel.setBounds((width/2)-(monsterLabelWidth/2), Globals.scale(10), monsterLabelWidth, monsterLabelHeight);
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
			int runes1Left = -1;
			int runes2Left = -1;
			int runes3Left = -1;
			boolean hasTripBeenEntered = false;

			////////////////
			// Get loot
			if(name == "Aberrant Spectres") {
				profit = HerbPanel.calculateHerbs();
			}else {
				for(JFormattedTextField trip : trips) {
					if(trip.getText().length() != 0) {
						profit += Integer.parseInt(trip.getText().replaceAll(",", ""));
						hasTripBeenEntered = true;
					}
				}
			}
			if(isCannon) {
				///////////////
				// Get cannonballs left
				if(cannonballsLeftTextField.getText().length() != 0) {
					cannonballLeft = Integer.parseInt(cannonballsLeftTextField.getText().replaceAll(",", ""));
					cannonballsLeftTextField.setBackground(new Color(255, 255, 255));
				}else {
					// If nothing was entered where cannonballs are left
					cannonballsLeftTextField.setBackground(new Color(255, 0, 0));
					return;
				}
			}
			if(isMagic) {
				///////////////
				// Get cannonballs left
				if(rune1LeftTextField.getText().length() != 0) {
					runes1Left = Integer.parseInt(rune1LeftTextField.getText().replaceAll(",", ""));
					rune1LeftTextField.setBackground(new Color(255, 255,255));
				}else {
					// If nothing was entered where cannonballs are left
					rune1LeftTextField.setBackground(new Color(255, 0, 0));
					return;
				}
				if(rune2LeftTextField.getText().length() != 0) {
					runes2Left = Integer.parseInt(rune2LeftTextField.getText().replaceAll(",", ""));
					rune2LeftTextField.setBackground(new Color(255, 255,255));
				}else if(Globals.getRuneTypes()[1] == null){
					runes2Left = -1;
				}else {
					// If nothing was entered where cannonballs are left
					rune2LeftTextField.setBackground(new Color(255, 0, 0));
					return;
				}
				if(rune3LeftTextField.getText().length() != 0) {
					runes3Left = Integer.parseInt(rune3LeftTextField.getText().replaceAll(",", ""));
					rune3LeftTextField.setBackground(new Color(255, 255,255));
				}else if(Globals.getRuneTypes()[2] == null){
					runes3Left = -1;
				}else {
					// If nothing was entered where cannonballs are left
					rune3LeftTextField.setBackground(new Color(255, 0, 0));
					return;
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

			
			// player.finishCannonTask(name, count, profit, cannonballLeft);
			if(hasTripBeenEntered) {
				//System.out.println("pong");
				int tripNum = 0;
				for(JFormattedTextField trip : trips) {
					System.out.println("Trip #"+tripNum+": "+ trip.getText());
					tripNum++;
				}
				
				if(name != "Aberrant Spectres") {
					for(JFormattedTextField trip : trips) {
						trip.setBackground(Globals.white);
					}
				}
				if(isCannon && !isMagic) {
					Object[] toSend = {name, count, profit, cannonballLeft, time};
					Globals.finishTask(toSend);
				}else if(!isCannon && isMagic) {
					Object[] toSend = {name,count, profit, runes1Left, runes2Left, runes3Left, time};
					Globals.finishTask(toSend);
				}else if(!isCannon && !isMagic) {
					Object[] toSend = {name, count, profit,time};
					Globals.finishTask(toSend);
				}else if(isCannon && isMagic) {
					Object[] toSend = {name,count, profit, cannonballLeft, runes1Left, runes2Left, runes3Left, time};
					Globals.finishTask(toSend);
				}
				panel.setVisible(false);
				mainPanel.setVisible(true);
			}else {
				for(JFormattedTextField trip : trips) {
					trip.setBackground(Globals.red);
				}
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
		int tripheight = Globals.scale(450);
		int tripInputWidth = (tripwidth/2)-Globals.scale(15);
		int rowHeight = Globals.scale(25);
		
		herbPanel = HerbPanel.build(width, height);
		
		panel.add(herbPanel);
		
		tripsPanel.setVisible(true);
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
		
		
		for(int i = 0; i < 16; i++) {
			JLabel tripLabel = new JLabel("Trip #"+i);
			tripLabel.setFont(Globals.mainFont);
			tripLabel.setHorizontalAlignment(SwingConstants.CENTER);
			tripLabel.setBounds(0, Globals.scale(25*(i+1)), tripwidth/2, rowHeight);
			tripLabel.setForeground(Globals.buttonForground);
			tripsPanel.add(tripLabel);
			
			JFormattedTextField tripTextField = new JFormattedTextField(formatter);
			tripTextField.setText("");
			tripTextField.setFont(Globals.mainFont);
			tripTextField.setBounds(tripwidth/2, Globals.scale(25*(i+1)), tripInputWidth, rowHeight);
			tripsPanel.add(tripTextField);
			trips.add(tripTextField);
		}
		
		if(name == "Aberrant Spectres") {
			System.out.println("Abby");
			herbPanel.setVisible(true);
			tripsPanel.setVisible(false);
		}else {
			System.out.println("Normal");
			herbPanel.setVisible(false);
			tripsPanel.setVisible(true);
		}
		/////////////////////////////////////////////////////////////
		// Other Info Panel
		int infowidth = (width-Globals.scale(60))/2;
		int infoheight = Globals.scale(300);
		
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
		
		
		r1.setBounds(infowidth/2-Globals.scale(80), Globals.scale(30), infowidth, rowHeight);    
		r2.setBounds(infowidth/2-Globals.scale(80), Globals.scale(30)+Globals.scale(20), infowidth, rowHeight); 
		r3.setBounds(infowidth/2-Globals.scale(80), Globals.scale(30)+Globals.scale(20*2), infowidth, rowHeight); 
		r4.setBounds(infowidth/2-Globals.scale(80), Globals.scale(30)+Globals.scale(20*3), infowidth, rowHeight); 
		r1.setBackground(Globals.otherInfoBackground);
		r2.setBackground(Globals.otherInfoBackground);
		r3.setBackground(Globals.otherInfoBackground);
		r4.setBackground(Globals.otherInfoBackground);
		r1.setForeground(Globals.buttonForground);
		r2.setForeground(Globals.buttonForground);
		r3.setForeground(Globals.buttonForground);
		r4.setForeground(Globals.buttonForground);
		r3.setText(Globals.magicType);
		r4.setText(Globals.magicType+"/Cannon");
		r1.setSelected(true);
		// Normal
		r1.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            isCannon = false;
	            isMagic = false;
	            cannonballsLeftTextField.setVisible(false);
	            rune1LeftTextField.setVisible(false);
	            rune2LeftTextField.setVisible(false);
	            rune3LeftTextField.setVisible(false);
	            cannonballsLeftLabel.setVisible(false);
	            rune1Label.setVisible(false);
	            rune2Label.setVisible(false);
	            rune3Label.setVisible(false);
	        }
	    });
		// Cannon
		r2.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            isCannon = true;
	            isMagic = false;
	            cannonballsLeftTextField.setVisible(true);
	            rune1LeftTextField.setVisible(false);
	            rune2LeftTextField.setVisible(false);
	            rune3LeftTextField.setVisible(false);
	            cannonballsLeftLabel.setVisible(true);
	            rune1Label.setVisible(false);
	            rune2Label.setVisible(false);
	            rune3Label.setVisible(false);
	        }
	    });
		// Magic
		r3.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            isCannon = false;
	            isMagic = true;
	            cannonballsLeftTextField.setVisible(false);
	            rune1LeftTextField.setVisible(true);
	            rune2LeftTextField.setVisible(true);
	            rune3LeftTextField.setVisible(true);
	            cannonballsLeftLabel.setVisible(false);
	            rune1Label.setVisible(true);
	            rune2Label.setVisible(true);
	            rune3Label.setVisible(true);
	            if(Globals.getRuneTypes()[1] == null) {
	            	rune2Label.setVisible(false);
	            	rune2LeftTextField.setVisible(false);
	            }
	            if(Globals.getRuneTypes()[2] == null) {
	            	rune3Label.setVisible(false);
	            	rune3LeftTextField.setVisible(false);
	            }
	        }
	    });
		// Cannon/magic
		r4.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            isCannon = true;
	            isMagic = true;
	            cannonballsLeftTextField.setVisible(true);
	            rune1LeftTextField.setVisible(true);
	            rune2LeftTextField.setVisible(true);
	            rune3LeftTextField.setVisible(true);
	            cannonballsLeftLabel.setVisible(true);
	            rune1Label.setVisible(true);
	            rune2Label.setVisible(true);
	            rune3Label.setVisible(true);
	            if(Globals.getRuneTypes()[1] == null) {
	            	rune2Label.setVisible(false);
	            	rune2LeftTextField.setVisible(false);
	            }
	            if(Globals.getRuneTypes()[2] == null) {
	            	rune3Label.setVisible(false);
	            	rune3LeftTextField.setVisible(false);
	            }
	        }
	    });
		ButtonGroup bg = new ButtonGroup();    
		bg.add(r1);
		bg.add(r2);
		bg.add(r3);
		bg.add(r4);
		otherInfoPanel.add(r1);
		otherInfoPanel.add(r2);
		otherInfoPanel.add(r3);
		otherInfoPanel.add(r4);
		
		
		
		cannonballsLeftLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cannonballsLeftLabel.setFont(Globals.mainFont);
		cannonballsLeftLabel.setForeground(Globals.buttonForground);
		cannonballsLeftLabel.setBounds(0, Globals.scale(120), infowidth/2, rowHeight);
		cannonballsLeftLabel.setVisible(false);
		otherInfoPanel.add(cannonballsLeftLabel);
		cannonballsLeftTextField = new JFormattedTextField(formatter);
		cannonballsLeftTextField.setFont(Globals.mainFont);
		cannonballsLeftTextField.setBounds(infowidth/2, Globals.scale(120), (infowidth/2)-Globals.scale(15), rowHeight);
		otherInfoPanel.add(cannonballsLeftTextField);
		cannonballsLeftTextField.setColumns(10);
		cannonballsLeftTextField.setVisible(false);
		
		
		rune1Label.setHorizontalAlignment(SwingConstants.CENTER);
		rune1Label.setFont(Globals.mainFont);
		rune1Label.setForeground(Globals.buttonForground);
		rune1Label.setBounds(0, Globals.scale(120)+rowHeight, infowidth/2, rowHeight);
		rune1Label.setVisible(false);
		otherInfoPanel.add(rune1Label);
		rune1LeftTextField = new JFormattedTextField(formatter);
		rune1LeftTextField.setFont(Globals.mainFont);
		rune1LeftTextField.setBounds(infowidth/2, Globals.scale(120)+rowHeight, (infowidth/2)-Globals.scale(15), rowHeight);
		otherInfoPanel.add(rune1LeftTextField);
		rune1LeftTextField.setColumns(10);
		rune1LeftTextField.setVisible(false);
		
		
		rune2Label.setHorizontalAlignment(SwingConstants.CENTER);
		rune2Label.setFont(Globals.mainFont);
		rune2Label.setForeground(Globals.buttonForground);
		rune2Label.setBounds(0, Globals.scale(120)+rowHeight*2, infowidth/2, rowHeight);
		rune2Label.setVisible(false);
		otherInfoPanel.add(rune2Label);
		rune2LeftTextField = new JFormattedTextField(formatter);
		rune2LeftTextField.setFont(Globals.mainFont);
		rune2LeftTextField.setBounds(infowidth/2, Globals.scale(120)+rowHeight*2, (infowidth/2)-Globals.scale(15), rowHeight);
		otherInfoPanel.add(rune2LeftTextField);
		rune2LeftTextField.setColumns(10);
		rune2LeftTextField.setVisible(false);
		
		
		rune3Label.setHorizontalAlignment(SwingConstants.CENTER);
		rune3Label.setFont(Globals.mainFont);
		rune3Label.setForeground(Globals.buttonForground);
		rune3Label.setVisible(false);
		rune3Label.setBounds(0, Globals.scale(120)+rowHeight*3, infowidth/2, rowHeight);
		otherInfoPanel.add(rune3Label);
		rune3LeftTextField = new JFormattedTextField(formatter);
		rune3LeftTextField.setFont(Globals.mainFont);
		rune3LeftTextField.setBounds(infowidth/2, Globals.scale(120)+rowHeight*3, (infowidth/2)-Globals.scale(15), rowHeight);
		otherInfoPanel.add(rune3LeftTextField);
		rune3LeftTextField.setColumns(10);
		rune3LeftTextField.setVisible(false);
		
		rune1Label.setText(Globals.getRuneTypes()[0]);
		rune2Label.setText(Globals.getRuneTypes()[1]);
		rune3Label.setText(Globals.getRuneTypes()[2]);
		
		//////////////
		// Timer
		JButton startTimerButton = new JButton("Start Timer");
		JButton stopTimerButton = new JButton("Stop Timer");
		JButton restartTimerButton = new JButton("R");
		startTimerButton.setFont(Globals.mainFont);
		startTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			if(startTimerButton.isEnabled()){
				startTimerButton.setEnabled(false);
				stopTimerButton.setEnabled(true);
				restartTimerButton.setEnabled(true);
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
				restartTimerButton.setEnabled(false);
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
		
		restartTimerButton.setFont(Globals.smallFont);
		restartTimerButton.setBackground(Globals.red);
		restartTimerButton.setMargin(new Insets(0, 0, 0, 0));
		restartTimerButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			if(stopTimerButton.isEnabled()){
				startTimerButton.setEnabled(true);
				stopTimerButton.setEnabled(false);
				restartTimerButton.setEnabled(false);
				restartTimer();
			}
		}
		});
		restartTimerButton.setBounds(Globals.scale(35), (infoheight)-(rowHeight+Globals.scale(5))+Globals.scale(5), Globals.scale(15), Globals.scale(15));
		restartTimerButton.setEnabled(false);
		otherInfoPanel.add(restartTimerButton);
		
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
	
	public void restartTimer() {
		timerStart = 0;
		timerStop = 0;
		pauseTime = 0;
		timerTextField.setText("00:00:00");
	}

	public void reset(String monsterName, int count) {
		panel.setVisible(true);
		for(JFormattedTextField trip : trips) {
			trip.setText("");
			trip.setValue(null);
		}
		r1.setSelected(true);
        isCannon = false;
        isMagic = false;
        cannonballsLeftTextField.setVisible(false);
        rune1LeftTextField.setVisible(false);
        rune2LeftTextField.setVisible(false);
        rune3LeftTextField.setVisible(false);
        cannonballsLeftLabel.setVisible(false);
        rune1Label.setVisible(false);
        rune2Label.setVisible(false);
        rune3Label.setVisible(false);
        monsterNameLabel.setText(count + " " +monsterName);
        name = monsterName;
        this.count = count;
        if(monsterName == "Aberrant Spectres") {
			herbPanel.setVisible(true);
			tripsPanel.setVisible(false);
        }else {
			tripsPanel.setVisible(true);
			herbPanel.setVisible(false);
        }
    	restartTimer();
    	cannonballsLeftTextField.setText("");
    	rune1LeftTextField.setText("");
    	rune2LeftTextField.setText("");
    	rune3LeftTextField.setText("");
    	HerbPanel.resetFields();
	}
	
	
}
