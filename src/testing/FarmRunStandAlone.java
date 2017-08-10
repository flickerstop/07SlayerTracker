package testing;

import objects.Globals;
import panels.FarmRunPanel;

public class FarmRunStandAlone {
	public static void main(String args[]) {
		Globals.load();
		FarmRunPanel.build(true);
	}
}
