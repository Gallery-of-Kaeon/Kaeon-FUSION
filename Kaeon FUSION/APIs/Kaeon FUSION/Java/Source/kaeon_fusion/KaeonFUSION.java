package kaeon_fusion;

import java.util.ArrayList;

import fusion.FUSION;
import kaeon_fusion.use.Use;
import one.Element;
import philosophers_stone.PhilosophersStoneUtilities;

public class KaeonFUSION extends FUSION {
	
	public Object toReturn;
	
	public KaeonFUSION() {
		
		tags.add("Kaeon FUSION");
		tags.add("Source");

		PhilosophersStoneUtilities.publiclyConnectMutually(this, new Use());
	}
	
	public Object processKaeonFUSION(Element element) {
		
		super.process(element);
		
		Object temp = toReturn;
		toReturn = null;
		
		return temp;
	}
	
	public Object onCall(ArrayList<Object> packet) {
		
		super.onCall(packet);
		
		if(packet.size() >= 2) {
			
			if(("" + packet.get(0)).equalsIgnoreCase("Kaeon FUSION Return"))
				toReturn = packet.get(1);
		}
		
		return null;
	}
}