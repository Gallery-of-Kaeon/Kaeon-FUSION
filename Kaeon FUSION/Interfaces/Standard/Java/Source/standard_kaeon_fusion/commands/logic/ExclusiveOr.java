package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class ExclusiveOr extends FUSIONUnit {
	
	public ExclusiveOr() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Exclusive Or");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		boolean condition = false;
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(Boolean.parseBoolean("" + processed.get(i))) {
				
				if(!condition)
					condition = true;
				
				else
					return false;
			}
		}
		
		return condition;
	}
}