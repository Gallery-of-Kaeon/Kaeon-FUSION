package standard_kaeon_fusion.commands.core;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Catch extends FUSIONUnit {
	
	public boolean caught;
	
	public Catch() {
		tags.add("Standard");
	}
	
	public boolean deny(Element element) {
		return !element.content.equalsIgnoreCase("Catch") && caught;
	}
	
	public boolean verify(Element element) {
		
		for(int i = ElementUtilities.getIndex(element) - 1; i >= 0; i--) {
			
			if(element.parent.children.get(i).content.equalsIgnoreCase("In"))
				return false;
		}
		
		return element.content.equalsIgnoreCase("Catch");
	}
	
	public boolean trickleDown(Element element) {
		
		boolean wasCaught = caught;
		caught = false;
		
		return wasCaught;
	}
	
	public void handleError(Element element, Exception exception) {
		caught = true;
	}
}