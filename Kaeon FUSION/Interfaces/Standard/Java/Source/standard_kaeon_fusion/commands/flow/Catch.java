package standard_kaeon_fusion.commands.flow;

import java.util.ArrayList;

import fusion.FUSIONUnit;
import one.Element;
import one.ElementUtilities;

public class Catch extends FUSIONUnit {

	public boolean caught;
	public boolean auto;
	
	public Catch() {
		tags.add("Standard");
		tags.add("Catch");
	}
	
	public boolean deny(Element element) {
		
		Element pseudo = element;
		
		while(pseudo != null) {
			
			if(pseudo.content.equalsIgnoreCase("Pseudo Catch"))
				return false;
			
			pseudo = pseudo.parent;
		}
		
		return
				!element.content.equalsIgnoreCase("Catch") &&
				caught &&
				!auto;
	}
	
	public boolean verify(Element element) {
		
		for(int i = ElementUtilities.getIndex(element) - 1; i >= 0; i--) {
			
			if(element.parent.children.get(i).content.equalsIgnoreCase("In"))
				return false;
		}
		
		return
				element.content.equalsIgnoreCase("Catch") ||
				element.content.equalsIgnoreCase("Pseudo Catch");
	}
	
	public boolean trickleDown(Element element) {
		
		boolean wasCaught = caught;
		
		if(element.content.equalsIgnoreCase("Catch"))
			caught = false;
		
		return wasCaught;
	}
	
	public void handleError(Element element, ArrayList<Object> processed, Exception exception) {
		caught = true;
	}
}