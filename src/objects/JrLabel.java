package objects;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class JrLabel extends JLabel{
	
	public JrLabel() {
		super();
		super.setFont(Globals.mainFont);
		super.setForeground(Globals.buttonForground);
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
	public JrLabel(String text) {
		super(text);
		super.setFont(Globals.mainFont);
		super.setForeground(Globals.buttonForground);
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
	public JrLabel(Font font) {
		super();
		super.setFont(font);
		super.setForeground(Globals.buttonForground);
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}

	public JrLabel(ImageIcon image) {
		super(image);
		super.setFont(Globals.mainFont);
		super.setForeground(Globals.buttonForground);
		super.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	public void setRight() {
		super.setHorizontalAlignment(SwingConstants.RIGHT);
	}
	
	public void setLeft() {
		super.setHorizontalAlignment(SwingConstants.LEFT);
	}
	
	public void setMassiveFont() {
		super.setFont(Globals.massiveFont);
	}
	
	public void setBoldFont() {
		super.setFont(Globals.boldFont);
	}
	
	public void setSmallFont() {
		super.setFont(Globals.smallFont);
	}
	
	public void setColor(Color color) {
		super.setForeground(color);
	}
	
	@Deprecated
	public void setFont(Font font) {
		super.setFont(font);
	}
	
	@Deprecated
	public void setForeground(Color color) {
		super.setForeground(color);
	}
	
	@Deprecated
	public void setHorizontalAlignment(int alignment) {
		super.setHorizontalAlignment(alignment);
	}
	
}
