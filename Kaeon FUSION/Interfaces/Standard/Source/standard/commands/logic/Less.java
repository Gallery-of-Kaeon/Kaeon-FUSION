package standard.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Less extends FUSIONUnit {
	
	public Less() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Less");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 1; i < processed.size(); i++) {
			
			if(Double.parseDouble("" + processed.get(i - 1)) >=
					Double.parseDouble("" + processed.get(i))) {
				
				return false;
			}
		}
		
		return true;
	}
}