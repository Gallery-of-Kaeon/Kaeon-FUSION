package standard_kaeon_fusion.commands.logic;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Is extends FUSIONUnit {
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Is");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		for(int i = 0; i < processed.size() - 1; i++) {
			
			if(processed.get(i) != processed.get(i + 1))
				return false;
		}
		
		return true;
	}
}