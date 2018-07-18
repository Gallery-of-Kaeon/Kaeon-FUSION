package kaeon_fusion.utilities.priority;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Priority extends FUSIONUnit {
	
	public Priority() {
		tags.add("Kaeon FUSION");
		tags.add("Priority");
	}
	
	public boolean verify(Element element) {
		return true;
	}
	
	public boolean trickleDown(Element element) {
		
		ElementUtilities.addChildren(element, ElementUtilities.removeChildren(element, "Use"), 0);
		
		return true;
	}
}