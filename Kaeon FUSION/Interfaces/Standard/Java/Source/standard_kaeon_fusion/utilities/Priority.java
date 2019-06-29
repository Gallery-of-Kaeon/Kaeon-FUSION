package standard_kaeon_fusion.utilities;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Priority extends FUSIONUnit {
	
	public boolean initialized;
	
	public Priority() {
		tags.add("Standard");
	}
	
	public boolean verify(Element element) {
		return true;
	}
	
	public boolean trickleDown(Element element) {
		
		ElementUtilities.addChildren(element, ElementUtilities.removeChildren(element, "Define"), 0);
		ElementUtilities.addChildren(element, ElementUtilities.removeChildren(element, "Use"), 0);
		
		return true;
	}
	
	public Element jump(Element element, ArrayList<Object> processed) {
		
		if(!initialized) {
			
			Element currentElement = element;
			
			while(currentElement.parent != null) {
				
				int index = ElementUtilities.getIndex(currentElement) + 1;
				
				ArrayList<Element> use = new ArrayList<Element>();
				
				for(int i = index; i < currentElement.parent.children.size(); i++) {
					
					if(currentElement.parent.children.get(i).content.equalsIgnoreCase("Use")) {
						
						use.add(currentElement.parent.children.remove(i));
						
						i--;
					}
				}
				
				ElementUtilities.addChildren(currentElement.parent, use, index);
				
				currentElement = currentElement.parent;
			}
			
			currentElement = element;
			
			while(currentElement.parent != null) {
				
				ElementUtilities.addChildren(
						currentElement.parent,
						ElementUtilities.removeChildren(currentElement.parent, "Define"),
						ElementUtilities.getIndex(currentElement) + 1);
				
				currentElement = currentElement.parent;
			}
			
			initialized = true;
		}
		
		return null;
	}
}