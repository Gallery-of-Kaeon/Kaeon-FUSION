package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;

public class Retrieve extends FUSIONUnit {
	
	public Element retrieved;
	public Object retrieve;
	
	public Retrieve() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		
		if(retrieved != null) {
			
			if(retrieved.parent == element)
				return true;
		}
		
		return element.content.equalsIgnoreCase("Retrieve");
	}
	
	public Object process(Element element, ArrayList<Object> processed) {
		
		if(element.content.equalsIgnoreCase("Retrieve")) {
			
			if(processed.size() > 0) {
				
				if(processed.size() == 1)
					retrieve = processed.get(0);
				
				else
					retrieve = processed;
				
				retrieved = element;
			}
		}
		
		else {
			
			Object object = retrieve;
			
			retrieved = null;
			retrieve = null;
			
			return object;
		}
		
		return null;
	}
}