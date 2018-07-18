package kaeon_origin.utilties;

import java.util.ArrayList;

import philosophers_stone.PhilosophersStone;

public class Utilities {
	
	public static PhilosophersStone getArgumentStone(ArrayList<Object> arguments) {
		
		PhilosophersStone argumentStone = new PhilosophersStone() {
			
			public Object onCall(ArrayList<Object> packet) {
				return ((String) packet.get(0)).equalsIgnoreCase("Arguments") ? arguments : null;
			}
		};
		
		argumentStone.tags.add("Arguments");
		
		return argumentStone;
	}
}