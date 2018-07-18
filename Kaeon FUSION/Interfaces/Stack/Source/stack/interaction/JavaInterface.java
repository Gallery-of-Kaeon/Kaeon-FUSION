package stack.interaction;

import java.util.ArrayList;

import aether_stack.Aether;
import philosophers_stone.PhilosophersStone;

public class JavaInterface extends PhilosophersStone {
	
	@SuppressWarnings("unchecked")
	public Object onCall(ArrayList<Object> packet) {
		
		if(!(((String) packet.get(0)).equalsIgnoreCase("Code") ||
				((String) packet.get(0)).equalsIgnoreCase("Module")) ||
				!((String) packet.get(1)).equalsIgnoreCase("Java")) {
			
			return null;
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Code")) {
			// STUB
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Module")) {
			
			System.out.println("hello");
			
			String module = "" + packet.get(2);
			
			ArrayList<Object> arguments =
					packet.size() >= 4 ?
							(ArrayList<Object>) packet.get(3) :
							new ArrayList<Object>();
							
			return Aether.call(module, 0, arguments);
		}
		
		return null;
	}
}