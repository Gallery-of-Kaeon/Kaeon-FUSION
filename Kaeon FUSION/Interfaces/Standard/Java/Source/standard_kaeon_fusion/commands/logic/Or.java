package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Or extends FUSIONUnit {
	
	public Or() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Or");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(Boolean.parseBoolean("" + processed.get(i)))
				return true;
		}
		
		return false;
	}
}