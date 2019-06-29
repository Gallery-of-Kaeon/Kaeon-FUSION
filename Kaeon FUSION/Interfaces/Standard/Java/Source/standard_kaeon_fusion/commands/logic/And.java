package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class And extends FUSIONUnit {
	
	public And() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("And");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size(); i++) {
			
			if(!Boolean.parseBoolean("" + processed.get(i)))
				return false;
		}
		
		return true;
	}
}