package standard_kaeon_fusion.utilities;

import java.util.ArrayList;

import fusion.FUSION;
import philosophers_stone.PhilosophersStone;
import standard_kaeon_fusion.commands.system.Connect;

public class Stopper extends PhilosophersStone {
	
	public ArrayList<FUSION> fusion;
	public ArrayList<Connect> connections;
	
	public Stopper() {

		tags.add("Standard");
		tags.add("Stopper");
		
		fusion = new ArrayList<FUSION>();
		connections = new ArrayList<Connect>();
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		if(("" + packet.get(0)).equalsIgnoreCase("Stop")) {
			
			while(fusion.size() > 0)
				fusion.remove(0).running = false;
			
			while(connections.size() > 0)
				connections.remove(0).running = false;
		}
		
		return null;
	}
}