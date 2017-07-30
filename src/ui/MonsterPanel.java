package ui;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MonsterPanel extends JPanel{

	private String name;
	private ImageIcon image;
	private int count;
	
	public MonsterPanel() {
		name = "";
		image = new ImageIcon();
		count = 0;
	}
	
}
