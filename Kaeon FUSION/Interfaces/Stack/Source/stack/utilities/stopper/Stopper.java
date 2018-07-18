package stack.utilities.stopper;

import java.util.ArrayList;

import philosophers_stone.PhilosophersStone;
import stack.utilities.gui.DisplayGUI;

public class Stopper extends PhilosophersStone {
	
	public ArrayList<DisplayGUI> guis;
	
	public Stopper() {

		tags.add("Stack");
		tags.add("Stopper");
		
		guis = new ArrayList<DisplayGUI>();
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(("" + packet.get(0)).equalsIgnoreCase("Stop")) {
			
			while(guis.size() > 0)
				guis.remove(0).frame.dispose();
		}
			
		return null;
	}
}