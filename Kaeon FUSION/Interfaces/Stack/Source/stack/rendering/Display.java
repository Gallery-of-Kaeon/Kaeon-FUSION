package stack.rendering;

import java.util.ArrayList;

import philosophers_stone.PhilosophersStone;
import stack.utilities.gui.DisplayGUI;

public class Display extends PhilosophersStone {
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(("" + packet.get(0)).equalsIgnoreCase("Render") && ("" + packet.get(1)).equalsIgnoreCase("GUI")) {
			
			ArrayList<Object> namespace = new ArrayList<Object>();
			new DisplayGUI("" + packet.get(2), this, namespace);
			
			return namespace;
		}
		
		if(("" + packet.get(0)).equalsIgnoreCase("Render") && ("" + packet.get(1)).equalsIgnoreCase("Print"))
			DisplayGUI.print("" + packet.get(2));
		
		return null;
	}
}