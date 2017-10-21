package standard.commands.core;

import fusion.FUSIONUnit;
import one.Element;

public class Catch extends FUSIONUnit {
	
	public boolean caught;
	
	public Catch() {
		tags.add("Standard");
	}
	
	public boolean deny(Element element) {
		return !element.content.equalsIgnoreCase("Catch") && caught;
	}
	
	public boolean verify(Element element) {
		return element.content.equalsIgnoreCase("Catch");
	}
	
	public boolean trickleDown(Element element) {
		
		boolean wasCaught = caught;
		caught = false;
		
		return wasCaught;
	}
	
	public void handleError(Element element) {
		caught = true;
	}
}