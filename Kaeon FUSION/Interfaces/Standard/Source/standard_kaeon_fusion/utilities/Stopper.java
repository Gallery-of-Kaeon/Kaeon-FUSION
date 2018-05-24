package standard_kaeon_fusion.utilities;

import java.util.ArrayList;

import fusion.FUSION;
import philosophers_stone.PhilosophersStone;

public class Stopper extends PhilosophersStone {
	
	public ArrayList<FUSION> fusion;
	
	public Stopper() {
		
		tags.add("Stopper");
		
		fusion = new ArrayList<FUSION>();
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(("" + packet.get(0)).equalsIgnoreCase("Stop")) {
			
			for(int i = 0; i < fusion.size(); i++)
				fusion.get(i).running = false;
		}
		
		return null;
	}
}