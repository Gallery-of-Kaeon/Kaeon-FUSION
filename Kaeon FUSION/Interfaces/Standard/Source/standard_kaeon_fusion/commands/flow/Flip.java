package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;
import java.util.Collections;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Flip extends FUSIONUnit {
	
	public Flip() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Flip");
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		
		if(Boolean.parseBoolean("" + processed.get(0))) {
			
			Element jump = element;
			ArrayList<Integer> indexes = new ArrayList<Integer>();
			
			while(jump.parent != null) {
				
				indexes.add(ElementUtilities.getIndex(jump));
				
				jump = jump.parent;
				
				if(processed.size() > 1) {
					
					if(Boolean.parseBoolean("" + processed.get(1)))
						break;
				}
			}
			
			jump = ElementUtilities.copyElement(jump);
			
			reverseElement(jump);
			
			for(int i = 0; i < indexes.size(); i++)
				jump = jump.children.get(jump.children.size() - indexes.get(i) - 1);
			
			int jumpIndex = ElementUtilities.getIndex(jump);
			
			while(jump.parent != null) {
				
				if(jumpIndex < jump.parent.children.size() - 1)
					return jump.parent.children.get(jumpIndex + 1);
				
				jump = jump.parent;
			}
		}
		
		return null;
	}
	
	public static void reverseElement(Element element) {
		
		for(int i = 0; i < element.children.size(); i++)
			reverseElement(element.children.get(i));
		
		Collections.reverse(element.children);
	}
}