package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class LessOrEqual extends FUSIONUnit {
	
	public LessOrEqual() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Less or Equal");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 1; i < processed.size(); i++) {
			
			if(Double.parseDouble("" + processed.get(i - 1)) >
					Double.parseDouble("" + processed.get(i))) {
				
				return false;
			}
		}
		
		return true;
	}
}