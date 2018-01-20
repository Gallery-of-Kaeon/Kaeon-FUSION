package standard_kaeon_fusion.utilities;

import java.util.ArrayList;
import java.util.Scanner;

import philosophers_stone.PhilosophersStone;

public class Console extends PhilosophersStone {
	
	@SuppressWarnings("resource")
	public Object onCall(ArrayList<Object> packet) {
		
		if(((String) packet.get(0)).equalsIgnoreCase("Log") ||
				((String) packet.get(0)).equalsIgnoreCase("Input")) {
			
			@SuppressWarnings("unchecked")
			ArrayList<Object> processed = (ArrayList<Object>) packet.get(1);
			
			for(Object object : processed)
				System.out.print(object);
		}
		
		if(((String) packet.get(0)).equalsIgnoreCase("Input"))
			return new Scanner(System.in).next();
		
		return null;
	}
}