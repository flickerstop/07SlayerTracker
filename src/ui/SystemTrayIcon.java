package ui;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import corpTracker.CorpTrackerMainUI;
import geTracker.GETrackerMainUI;
import panels.FarmRunPanel;
import panels.LogPanel;
import panels.SettingsPanel;
import panels.UpdatesPanel;
import panels.XPTrackerPanel;
import raidTracker.RaidsTrackerMainUI;

public class SystemTrayIcon {

	final static TrayIcon trayIcon = new TrayIcon(createImage("/images/slayerIcon.png", "tray icon"));
	public static void initTaskBar() {
		//Check the SystemTray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("SystemTray is not supported");
            return;
        }
        final PopupMenu popup = new PopupMenu();
        //final TrayIcon trayIcon = new TrayIcon(createImage("/images/slayerIcon.png", "tray icon"));
        final SystemTray tray = SystemTray.getSystemTray();
       
        
        trayIcon.setToolTip("System tray icon demo");
        /////////////////////////////////////////////////////////
        // Main Components
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exit = new MenuItem("Exit");
        
        /////////////////////////////////////////////////////////
        // Check Boxes
        CheckboxMenuItem soundCheckbox = new CheckboxMenuItem("Sound");
        CheckboxMenuItem notificationCheckbox = new CheckboxMenuItem("Notifications");
        trayIcon.setImageAutoSize(true);
        
        /////////////////////////////////////////////////////////
        // Slayer Options
        Menu slayerMenu = new Menu("Slayer Windows");
        MenuItem mainPanel = new MenuItem("Main Panel");
        MenuItem logsPanel = new MenuItem("Logs");
        
        
        
        slayerMenu.add(mainPanel);
        slayerMenu.add(logsPanel);
        
        // Farm Options
        Menu farmMenu = new Menu("Farm Run");
        MenuItem farmRun = new MenuItem("Farm Timer");
        MenuItem startRun = new MenuItem("Start Run");
        MenuItem stopRun = new MenuItem("Stop Run");
        MenuItem checkRun = new MenuItem("Time Left");
        
        farmMenu.add(farmRun);
        farmMenu.add(startRun);
        farmMenu.add(stopRun);
        farmMenu.add(checkRun);
        
        // Other Options
        Menu otherMenu = new Menu("Others");
        MenuItem updatesPanel = new MenuItem("Updates");
        MenuItem settingsPanel = new MenuItem("Settings");
        
        otherMenu.add(updatesPanel);
        otherMenu.add(settingsPanel);
        
        // Beta Options
        Menu betaMenu = new Menu("Beta Apps");
        MenuItem CMLPanel = new MenuItem("CML Tracker");
        MenuItem geTrackerPanel = new MenuItem("GE Tracker");
        MenuItem corpPanel = new MenuItem("Corp Tracker");
        MenuItem raidsPanel = new MenuItem("Raids Tracker");
        
        betaMenu.add(CMLPanel);
        betaMenu.add(geTrackerPanel);
        betaMenu.add(corpPanel);
        betaMenu.add(raidsPanel);
        
        
        /////////////////////////////////////////////////////////
        
        
        soundCheckbox.setState(true);
        notificationCheckbox.setState(true);
        
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.add(farmMenu);
        popup.add(slayerMenu);
        popup.add(otherMenu);
        popup.add(betaMenu);
        popup.addSeparator();
//        popup.add(soundCheckbox);
//        popup.add(notificationCheckbox);
//        popup.addSeparator();
        popup.add(exit);
        
       
        trayIcon.setPopupMenu(popup);
        
        /////////////////////////////////////////////////////////////
        // Even listeners
        // About button
        aboutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
            }
        });
        
        // Open Farm Run
        farmRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FarmRunPanel.showPanel();
            }
        });
        
        // Start Farm Run
        startRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FarmRunPanel.startTimer();
            }
        });
        
        // Stop Farm Run
        stopRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	FarmRunPanel.stopTimer();
            }
        });
        
        // Stop Farm Run
        checkRun.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	notification("Time left: " + FarmRunPanel.getTimeLeft());
            }
        });
        
        // Open Main Panel
        mainPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SlayerTrackerUI.openMainPanel();
            }
        });
        
        // Open Settings Panel
        settingsPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	SettingsPanel.showPanel();
            }
        });
        
        // Open Logs Panel
        logsPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	LogPanel.showPanel();
            }
        });
        
        // Open Updates Panel
        updatesPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	UpdatesPanel.showPanel();
            }
        });
        
        // Open CML Panel
        CMLPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	XPTrackerPanel.showPanel();
            }
        });
        
        // Open geTracker Panel
        geTrackerPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	GETrackerMainUI.showPanel();
            }
        });
        
        // Open Corp Tracker Panel
        corpPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	CorpTrackerMainUI.showPanel();
            }
        });
        
        // Open Raids Tracker Panel
        raidsPanel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RaidsTrackerMainUI.showPanel();
            }
        });
        
        // Sound Checkbox
        soundCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
            }
        });
        
        
        // Notification Checkbox
        notificationCheckbox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	trayIcon.displayMessage("Hello, World", "notification demo", MessageType.INFO);
            }
        });
       
        // Exit Program
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	tray.remove(trayIcon);
            	SlayerTrackerUI.exit();
            }
        });
        
        //////////////////////////////////////////////////////////////
        try {
            tray.add(trayIcon);
            
        } catch (AWTException e) {
            System.out.println("TrayIcon could not be added.");
        }
	}
	
	private static Image createImage(String path, String description) {
        URL imageURL = SlayerTrackerUI.class.getResource(path);
        
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
	
	public static void notification(String text) {
		trayIcon.displayMessage("Slayer Tracker", text, MessageType.INFO);
	}
	public static void setFarmTime(String time) {
		
	}
}
