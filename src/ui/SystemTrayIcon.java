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

import panels.FarmRunPanel;
import panels.LogPanel;
import panels.SettingsPanel;
import panels.UpdatesPanel;
import panels.XPTrackerPanel;

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
        MenuItem farmRun = new MenuItem("Farm Run");
        MenuItem exit = new MenuItem("Exit");
        
        /////////////////////////////////////////////////////////
        // Check Boxes
        CheckboxMenuItem soundCheckbox = new CheckboxMenuItem("Sound");
        CheckboxMenuItem notificationCheckbox = new CheckboxMenuItem("Notifications");
        trayIcon.setImageAutoSize(true);
        
        /////////////////////////////////////////////////////////
        // Panels menu
        Menu displayMenu = new Menu("Open Window");
        MenuItem mainPanel = new MenuItem("Main Panel");
        MenuItem settingsPanel = new MenuItem("Settings");
        MenuItem logsPanel = new MenuItem("Logs");
        MenuItem updatesPanel = new MenuItem("Updates");
        MenuItem CMLPanel = new MenuItem("CML tracker");

        
        displayMenu.add(mainPanel);
        displayMenu.add(settingsPanel);
        displayMenu.add(logsPanel);
        displayMenu.add(CMLPanel);
        displayMenu.add(updatesPanel);
        /////////////////////////////////////////////////////////
        
        
        soundCheckbox.setState(true);
        notificationCheckbox.setState(true);
        
        //Add components to pop-up menu
        popup.add(aboutItem);
        popup.add(farmRun);
        popup.add(displayMenu);
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
