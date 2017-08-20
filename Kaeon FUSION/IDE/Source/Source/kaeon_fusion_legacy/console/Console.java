package kaeon_fusion_legacy.console;

import java.util.ArrayList;
import java.util.Scanner;

import legacy.philosophers_stone_plus.PhilosophersStonePlus;

public class Console extends PhilosophersStonePlus {
	
	public Console() {
		tag("Console");
	}
	
	@SuppressWarnings("unchecked")
	public Object onCall(ArrayList<Object> packet) {
		
		if(packet.size() != 2)
			return null;
		
		if(!(packet.get(0) instanceof String) || !(packet.get(1) instanceof ArrayList))
			return null;
		
		if(((String) packet.get(0)).equalsIgnoreCase("Console Out"))
			consoleOut((ArrayList<Object>) packet.get(1));
		
		else if(((String) packet.get(0)).equalsIgnoreCase("Console In"))
			return consoleIn((ArrayList<Object>) packet.get(1));
		
		return null;
	}
	
	public void consoleOut(ArrayList<Object> data) {
		
		for(int i = 0; i < data.size(); i++) {
			
			if(data.get(i) != null)
				System.out.print(data.get(i));
		}
	}
	
	@SuppressWarnings("resource")
	public String consoleIn(ArrayList<Object> data) {
		
		consoleOut(data);
		
		return new Scanner(System.in).nextLine();
	}
}